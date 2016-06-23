package com.pmarlen.caja.control;

import com.pmarlen.backend.model.Producto;
import com.pmarlen.businesslogic.GeneradorNumTicket;
import com.pmarlen.businesslogic.reports.TextReporter;
import com.pmarlen.caja.Main;
import com.pmarlen.caja.dao.ESFileSystemJsonDAO;
import com.pmarlen.caja.dao.MemoryDAO;
import com.pmarlen.caja.model.PedidoVentaDetalleTableItem;
import com.pmarlen.caja.model.DevolucionDetalleTableModel;
import com.pmarlen.caja.model.ImporteCellRender;
import com.pmarlen.caja.model.PedidoVentaDetalleTableModel;
import com.pmarlen.caja.model.VentaSesion;
import com.pmarlen.caja.view.FramePrincipal;
import com.pmarlen.caja.view.PanelDevolucion;
import com.pmarlen.caja.view.ProductoCellRender;
import com.pmarlen.caja.view.TokenFrame;
import com.pmarlen.model.Constants;
import com.pmarlen.model.JarpeReportsInfoDTO;
import com.pmarlen.model.OSValidator;
import com.pmarlen.rest.dto.ESD;
import com.pmarlen.rest.dto.ES_ESD;
import com.pmarlen.ticket.systemprinter.SendFileToSystemPrinter;
import com.pmarlen.ticket.systemprinter.UnixSendToLP;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import org.apache.log4j.Logger;

/**
 *
 * @author alfredo
 */
public class PanelDevolucionControl implements ActionListener, TableModelListener, MouseListener {
	private static Logger logger = Logger.getLogger(PanelDevolucionControl.class.getName());
	private PanelDevolucion panelDevolucion;
	private ArrayList<PedidoVentaDetalleTableItem> detalleVentaTableItemList;
	private ArrayList<PedidoVentaDetalleTableItem> detalleVentaTableItemList2;
	private ES_ESD pedidoVenta;
	private ES_ESD devolucion;
	private ES_ESD devolucionImpr;
	private DevolucionDetalleTableModel  devTM;
	private PedidoVentaDetalleTableModel devTM2;
	private boolean estadoChecando = false;
	private FramePrincipal framePrincipal = null;
	private String ticketBuscar  = null;
    private double descOriginal = 0.0;
	
	public PanelDevolucionControl(PanelDevolucion panelDevolucion) {
		this.panelDevolucion = panelDevolucion;
//		
		panelDevolucion.getDetalleVentaJTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				boolean valueIsAdjusting = e.getValueIsAdjusting();
				if (!valueIsAdjusting) {
					updateSelectedRow();
				}
			}
		});
		
		panelDevolucion.getDetalleVentaJTableDev().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				boolean valueIsAdjusting = e.getValueIsAdjusting();				
				if (!valueIsAdjusting) {
					updateSelectedRow2();
				}
			}
		});
		
		this.panelDevolucion.getTicket().addActionListener(this);
		this.panelDevolucion.getCodigoBuscar().addActionListener(this);
		this.panelDevolucion.getBuscar().addActionListener(this);
		this.panelDevolucion.getTerminar().addActionListener(this);
		this.panelDevolucion.getCancelar().addActionListener(this);
		this.panelDevolucion.getDevolver().addActionListener(this);
		
		estadoChecando = false;
	}
	
	public void estadoInicial() {
		
		logger.debug("->estadoInicial()");
        descOriginal = 0.0;
		this.detalleVentaTableItemList  =  new ArrayList<PedidoVentaDetalleTableItem> ();
		this.detalleVentaTableItemList2 =  new ArrayList<PedidoVentaDetalleTableItem> ();

		this.devTM  = new DevolucionDetalleTableModel();
		this.devTM2 = new PedidoVentaDetalleTableModel();
		
		this.devTM.setDetalleVentaTableItemList(this.detalleVentaTableItemList);
		this.devTM2.setDetalleVentaTableItemList(this.detalleVentaTableItemList2);
		
		this.panelDevolucion.getDetalleVentaJTable().setModel(this.devTM);
		this.panelDevolucion.getDetalleVentaJTableDev().setModel(this.devTM2);
		
		this.devTM.addTableModelListener(this);
		this.devTM2.addTableModelListener(this);
		
		Color bgc= panelDevolucion.getDetalleVentaJTable().getBackground();
		Color fgc= panelDevolucion.getDetalleVentaJTable().getForeground();
		Color sbgc= panelDevolucion.getDetalleVentaJTable().getSelectionBackground();
		Color sfgc= panelDevolucion.getDetalleVentaJTable().getSelectionForeground();
		
		final ImporteCellRender importeCellRender   = new ImporteCellRender();
		final ProductoCellRender productoCellRender = new ProductoCellRender();
		
		productoCellRender.setColors(bgc, fgc, sbgc, sfgc);

		importeCellRender.setHorizontalAlignment(SwingConstants.RIGHT);
		panelDevolucion.getDetalleVentaJTable().addMouseListener(this);
		panelDevolucion.getDetalleVentaJTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
		panelDevolucion.getDetalleVentaJTable().getColumnModel().getColumn(2).setCellRenderer(productoCellRender);
		panelDevolucion.getDetalleVentaJTable().getColumnModel().getColumn(3).setCellRenderer(importeCellRender);
		panelDevolucion.getDetalleVentaJTable().getColumnModel().getColumn(4).setCellRenderer(importeCellRender);		
		panelDevolucion.getDetalleVentaJTable().addComponentListener(new JTableColumnAutoResizeHelper(new int[]{8,8,54,13,17}));
		panelDevolucion.getDetalleVentaJTable().setRowHeight(50);
		panelDevolucion.getDetalleVentaJTable().updateUI();

		panelDevolucion.getDetalleVentaJTableDev().addMouseListener(this);
		panelDevolucion.getDetalleVentaJTableDev().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
		panelDevolucion.getDetalleVentaJTableDev().getColumnModel().getColumn(1).setCellRenderer(productoCellRender);
		panelDevolucion.getDetalleVentaJTableDev().getColumnModel().getColumn(2).setCellRenderer(importeCellRender);
		panelDevolucion.getDetalleVentaJTableDev().getColumnModel().getColumn(3).setCellRenderer(importeCellRender);		
		panelDevolucion.getDetalleVentaJTableDev().addComponentListener(new JTableColumnAutoResizeHelper(new int[]{6,62,14,18}));
		panelDevolucion.getDetalleVentaJTableDev().setRowHeight(50);
		panelDevolucion.getDetalleVentaJTableDev().updateUI();
				
		//renderTotal();
		panelDevolucion.getSucursal().setText("");
		panelDevolucion.getSucursal().setBackground(panelDevolucion.getCaja().getBackground());
		panelDevolucion.getTicket().setText("");		
		panelDevolucion.getCodigoBuscar().setText("");		
		panelDevolucion.getTicket().setText("");
		panelDevolucion.getAtendio().setText("");
		panelDevolucion.getFecha().setText("");		
		
		panelDevolucion.getNumArt().setText("");
		panelDevolucion.getNumArtDev().setText("");
		panelDevolucion.getSubtotal().setText("");
		panelDevolucion.getDescuento().setToolTipText(null);		
		panelDevolucion.getDescuento().setText("");		
		panelDevolucion.getTotal().setText("");
		panelDevolucion.getTotalDev().setText("");
		panelDevolucion.getMotivoDev().setText("");
		panelDevolucion.getMotivoDev().setEnabled(true);
		
		panelDevolucion.getTicket().requestFocus();
		panelDevolucion.getDevolver().setEnabled(false);
		panelDevolucion.getTicket().setEnabled(true);
		panelDevolucion.getBuscar().setEnabled(true);
		
		panelDevolucion.getTerminar().setEnabled(false);

		estadoChecando = false;
	}

	public FramePrincipal getFramePrincipal() {
		if(this.framePrincipal ==  null){
			this.framePrincipal = FramePrincipalControl.getInstance().getFramePrincipal();
		}
		return this.framePrincipal;
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == panelDevolucion.getCodigoBuscar()) {
			codigoBuscar_ActionPerformed();
		} else if (e.getSource() == this.panelDevolucion.getTerminar()) {
			terminar_ActionPerformed();
		} else if (e.getSource() == this.panelDevolucion.getCancelar()) {
			cancelar_ActionPerformed();
		} else if (e.getSource() == this.panelDevolucion.getBuscar()) {
			buscar_ActionPerformed();
		} else if (e.getSource() == this.panelDevolucion.getDevolver()) {
			devolver_ActionPerformed();
		} else if (e.getSource() == this.panelDevolucion.getTicket()) {
			buscar_ActionPerformed();
		}
	}
	
	
	private int     indexBuscado;
	private boolean codigoEncontrado;
	
	private void codigoBuscar_ActionPerformed() {
		String codigoBuscar = panelDevolucion.getCodigoBuscar().getText().trim();
		
		logger.info("[USER]->codigoBuscar_ActionPerformed:codigoBuscar=->" + codigoBuscar+"<-");
		
		int i=0;
		indexBuscado=-1;
		codigoEncontrado=false;
		for(PedidoVentaDetalleTableItem dvt:detalleVentaTableItemList){
			if(dvt.getProducto().getCodigoBarras().equals(codigoBuscar) ){
				codigoEncontrado=true;
				logger.debug("codigoBuscar_ActionPerformed:codigoBarras="+dvt.getProducto().getCodigoBarras()+", dev="+dvt.getPvd().getDev()+", cant="+dvt.getPvd().getC());
				if(dvt.getPvd().getDev()<dvt.getPvd().getC()){
					indexBuscado =  i;
					break;
				}				
			}
			i++;
		}
		
		if(codigoEncontrado && indexBuscado >= 0){
			logger.debug("codigoBuscar_ActionPerformed:indexBuscado="+indexBuscado);
			panelDevolucion.getDetalleVentaJTable().getSelectionModel().setSelectionInterval(indexBuscado, indexBuscado);
			scrollToVisible(panelDevolucion.getDetalleVentaJTable(), indexBuscado, indexBuscado);
			panelDevolucion.getCodigoBuscar().setText(null);
			devolver_ActionPerformed();
		} else {
			new Thread() {
				@Override
				public void run() {
					
					Color pc = panelDevolucion.getCodigoBuscar().getBackground();
					Color xc = null;
					if(codigoEncontrado){
						xc = Color.ORANGE;
					} else {
						xc = Color.RED;
					}
					
					try {
						for (int i = 0; i < 3; i++) {
							panelDevolucion.getCodigoBuscar().setBackground(xc);
							Thread.sleep(500);
							panelDevolucion.getCodigoBuscar().setBackground(pc);
							Thread.sleep(100);
						}
					} catch (InterruptedException ex) {
						
					} finally {
						panelDevolucion.getCodigoBuscar().setBackground(pc);
						panelDevolucion.getCodigoBuscar().setText("");
					}
				}
			}.start();
		}
	}
	
	public void scrollToVisible(JTable table, int rowIndex, int vColIndex) {
        if (!(table.getParent() instanceof JViewport)) {
            return;
        }
        JViewport viewport = (JViewport)table.getParent();

        // This rectangle is relative to the table where the
        // northwest corner of cell (0,0) is always (0,0).
        Rectangle rect = table.getCellRect(rowIndex, vColIndex, true);

        // The location of the viewport relative to the table
        Point pt = viewport.getViewPosition();

        // Translate the cell location so that it is relative
        // to the view, assuming the northwest corner of the
        // view is (0,0)
        rect.setLocation(rect.x-pt.x, rect.y-pt.y);

        table.scrollRectToVisible(rect);

        // Scroll the area into view
        //viewport.scrollRectToVisible(rect);
    }
	

	void terminar_ActionPerformed() {
		logger.info("[USER]->terminar_ActionPerformed: estadoChecando="+estadoChecando+", detalleVentaTableItemList2.size()="+detalleVentaTableItemList2.size());
		
		if(!estadoChecando || detalleVentaTableItemList2.size()==0 ){
			return;
		}
		
		final String motivoCompleto = panelDevolucion.getMotivoDev().getText().trim();
		
		if(motivoCompleto.length()<10){
			JOptionPane.showMessageDialog(FramePrincipalControl.getInstance().getFramePrincipal(), "Tiene que escribir un motivo de esta devolución", "Terminar Devolución", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		TokenFrame tf = new TokenFrame(FramePrincipalControl.getInstance().getFramePrincipal());
		
		tf.setVisible(true);
		
		if(!tf.isAccepted()){
			logger.debug("terminar_ActionPerformed: No se autorizo!");
			return;
		}
		
		int idSuc = pedidoVenta.getEs().getS();
		logger.debug("terminar_ActionPerformed(): idSuc=\n"+idSuc);
		devolucion.getEs().setS(idSuc);
		devolucion.getEs().setTm(Constants.TIPO_MOV_ENTRADA_ALMACEN_DEVOLUCION);
		devolucion.getEs().setJ (pedidoVenta.getEs().getJ());
		devolucion.getEs().setFc(System.currentTimeMillis());
		devolucion.getEs().setFc(System.currentTimeMillis());
		devolucion.getEs().setU(ApplicationLogic.getInstance().getLogged().getE());
		devolucion.getEs().setEsDev(pedidoVenta.getEs().getId());
		String numTicket = null;
		Date fechaDev = new Date(devolucion.getEs().getFc());
		
		numTicket = GeneradorNumTicket.getNumTicket(fechaDev,devolucion.getEs().getS(),devolucion.getEs().getJ());
		logger.debug("terminar_ActionPerformed: Ticket Generado con :fechaDev="+fechaDev+", Suc="+devolucion.getEs().getS()+", Caja="+devolucion.getEs().getJ()+", ");
		
		devolucion.getEs().setNt(numTicket);		
		devolucion.getEs().setC(Constants.ID_CLIENTE_MOSTRADOR);
		devolucion.getEs().setFp(Constants.ID_FDP_1SOLA_E);
		devolucion.getEs().setMp(Constants.ID_MDP_EFECTIVO);
		devolucion.getEs().setIr(0.0);		
		devolucion.getEs().setAmc("");
		
		ESFileSystemJsonDAO.commit(devolucion);

		JOptionPane.showMessageDialog(FramePrincipalControl.getInstance().getFramePrincipal(), "SE PROCESO LA DEVOLUCUÓN", "Terminar Devolución", JOptionPane.INFORMATION_MESSAGE);
		
		devolucionImpr = devolucion;
		
		if (ApplicationLogic.getInstance().isPrintingEnabled()) {
			new Thread() {
				@Override
				public void run() {
					logger.debug("terminar_ActionPerformed(): despues de cerrar dialogo: TICKET:"+devolucionImpr.getEs().getNt()+", ImporteRecibido="+devolucionImpr.getEs().getIr()+", MP="+devolucionImpr.getEs().getMp());
					imprimirTicket();
				}
			}.start();
		}

		estadoInicial();
	}
	
	
	void cancelar_ActionPerformed() {
		logger.info("[USER]->cancelar_ActionPerformed()");
		int r = JOptionPane.showConfirmDialog(FramePrincipalControl.getInstance().getFramePrincipal(), "¿Cancelar la Devolución Actual?", "Devolución", JOptionPane.YES_NO_OPTION);
		if (r == JOptionPane.YES_OPTION) {
			estadoInicial();
			panelDevolucion.getTicket().requestFocus();
		}
	}
	
	void buscar_ActionPerformed(){		
		ticketBuscar = panelDevolucion.getTicket().getText().trim();
		logger.info("[USER]->buscar_ActionPerformed(): ticketBuscar="+ticketBuscar);
		
		
		if(ticketBuscar.length()> 10){
			buscarEnServidor();
		}
	}
	
	void devolver_ActionPerformed(){		
		logger.info("[USER]->devolver_ActionPerformed()");
		int sr = panelDevolucion.getDetalleVentaJTable().getSelectedRow();
		logger.debug("=>devolver_ActionPerformed:Selected:sr=" + sr);
		if(sr != -1){			
			PedidoVentaDetalleTableItem pvdDev = detalleVentaTableItemList.get(sr);
			logger.debug("=>devolver_ActionPerformed:"+pvdDev.getPvd().getDev()+" < "+pvdDev.getPvd().getC()+" ?");
            
            if(pvdDev.getPvd().getDev() < pvdDev.getPvd().getC() ){
				PedidoVentaDetalleTableItem pvdiDR = null;
				
				for(PedidoVentaDetalleTableItem pvdiDX:detalleVentaTableItemList2){
					if(	pvdiDX.getProducto().getCodigoBarras().equals(pvdDev.getProducto().getCodigoBarras()) &&
						pvdiDX.getTipoAlmacen() == pvdDev.getTipoAlmacen()) {
						pvdiDR = pvdiDX;
					}
				}
				
				if(pvdiDR == null){
					pvdiDR = new PedidoVentaDetalleTableItem(pvdDev);
					
					pvdiDR.getPvd().setC(1);                    
					pvdiDR.getPvd().setEsIdDev(pvdDev.getPvd().getId());
					pvdiDR.getPvd().setDev(0);
					
					devolucion.getEsdList().add(pvdiDR.getPvd());
					detalleVentaTableItemList2.add(pvdiDR);
					panelDevolucion.getTerminar().setEnabled(true);
					
				} else {
					pvdiDR.getPvd().setC(pvdiDR.getPvd().getC() + 1);
				}
				
				pvdDev.getPvd().setDev(pvdDev.getPvd().getDev() + 1);
				
				panelDevolucion.getDetalleVentaJTable().updateUI();
				panelDevolucion.getDetalleVentaJTableDev().updateUI();
				
				if(pvdDev.getPvd().getDev() >= pvdDev.getPvd().getC()){
					panelDevolucion.getCodigoBuscar().requestFocus();
					panelDevolucion.getDevolver().setEnabled(false);
				}
				
				renderTotalDev();
			}
		} else {
			panelDevolucion.getDevolver().setEnabled(false);
		}
	}
	
	private void buscarEnServidor(){
		new Thread(){
			public void run(){
				pedidoVenta = null;
				String ticketBuscarReal = null;
				try {
					logger.info("[USER]->buscarEnServidor():ticketBuscar="+ticketBuscar);
					ticketBuscarReal = ticketBuscar.replace("-","");					
					pedidoVenta = MemoryDAO.getTicket(ticketBuscarReal);
					
					if(pedidoVenta.getEs().getTm() != Constants.TIPO_MOV_SALIDA_ALMACEN_VENTA) {
						throw new IllegalArgumentException("NO ES UNA VENTA: TimpoMovimiento Encontrado:"+pedidoVenta.getEs().getTm());
					}				
					panelDevolucion.getTicket().setText(ticketBuscarReal);
				}catch(Exception e){
					logger.error("->buscarEnServidor:", e);
					JOptionPane.showMessageDialog(getFramePrincipal(), "No se encotró la VENTA en el SERVIDOR con El Ticket #:"+ticketBuscarReal, "BUSCAR", JOptionPane.ERROR_MESSAGE);
					panelDevolucion.getTicket().setText("");
					panelDevolucion.getTicket().requestFocus();
					return;
				}
				devolucion  = new ES_ESD();
				devolucion.getEs().setPdc(pedidoVenta.getEs().getPdc());
				devolucion.getEs().setPde(pedidoVenta.getEs().getPde());
				logger.debug("buscarEnServidor(): ventaOrigen="+pedidoVenta);
				int idSuc = pedidoVenta.getEs().getS();
				logger.debug("buscarEnServidor(): idSuc="+idSuc);
				devolucion.getEs().setS(idSuc);
				devolucion.getEs().setTm(Constants.TIPO_MOV_ENTRADA_ALMACEN_DEVOLUCION);
				devolucion.getEs().setEsDev(pedidoVenta.getEs().getId());
                descOriginal = 0.0;
                if(pedidoVenta.getEs().getPdc()+pedidoVenta.getEs().getPde() > 0){
                    descOriginal = (pedidoVenta.getEs().getPdc()+pedidoVenta.getEs().getPde())/100.0;
                    logger.debug("buscarEnServidor(): descOriginal="+descOriginal);
                }
				String sn = pedidoVenta.getEs().getSn();

				if(sn != null && sn.contains(Constants.PM_SADECV)){
					sn = sn.replace(Constants.PM_SADECV, "... ");
				}					
				panelDevolucion.getAtendio().setText(pedidoVenta.getEs().getU());
				panelDevolucion.getSucursal().setText(sn);
				Date fecha = new Date(pedidoVenta.getEs().getFc());
				panelDevolucion.getCaja().setText(String.valueOf(pedidoVenta.getEs().getJ()));
				panelDevolucion.getFecha().setText(Constants.sdfShortDateTime.format(fecha));
				List<ESD> esdList = pedidoVenta.getEsdList();
				boolean quedaQueDevolver = false;
				for(ESD esd: esdList){

					if(esd.getDev()<esd.getC()){
						quedaQueDevolver = true;
					}

					Producto producto = MemoryDAO.fastSearchProducto(esd.getCb()).reverse();
					PedidoVentaDetalleTableItem pvdti = new PedidoVentaDetalleTableItem(producto, esd, esd.getTa());
					detalleVentaTableItemList.add(pvdti);
				}
				
				renderTotal();
				estadoChecando = true;
				panelDevolucion.getDetalleVentaJTable().updateUI();

				panelDevolucion.getTicket().setEnabled(false);
				panelDevolucion.getBuscar().setEnabled(false);
				panelDevolucion.getDevolver().setEnabled(false);	
				

				if(idSuc == MemoryDAO.getSucursalId()){
					if( quedaQueDevolver ){
						panelDevolucion.getSucursal().setBackground(Color.GREEN);
						panelDevolucion.getCodigoBuscar().setEnabled(true);
						panelDevolucion.getDetalleVentaJTable().setEnabled(true);						
						panelDevolucion.getCodigoBuscar().requestFocus();
						
						panelDevolucion.getMotivoDev().setText("");
						panelDevolucion.getMotivoDev().setEnabled(true);
		
					} else{
						panelDevolucion.getSucursal().setBackground(Color.ORANGE);
						panelDevolucion.getCodigoBuscar().setEnabled(false);
						panelDevolucion.getDetalleVentaJTable().setEnabled(false);

						panelDevolucion.getTerminar().setEnabled(false);
					}
				} else {
					JOptionPane.showMessageDialog(getFramePrincipal(), "La VENTA con El Ticket #:"+ticketBuscarReal+", No fue hecha en esta sucursal", "BUSCAR", JOptionPane.WARNING_MESSAGE);
					panelDevolucion.getSucursal().setBackground(Color.RED);				
					panelDevolucion.getCodigoBuscar().setEnabled(false);
					panelDevolucion.getDetalleVentaJTable().setEnabled(false);

					panelDevolucion.getTerminar().setEnabled(false);
				}
			}
		}.start();	
	}


	@Override
	public void tableChanged(TableModelEvent e) {
		panelDevolucion.getDetalleVentaJTable().updateUI();
		renderTotal();
	}
	
	private void renderTotal() {		
		final List<ESD> esdList = pedidoVenta.getEsdList();
		double subTotal     = 0.0;
		double subTotal1ra  = 0.0;
		double subTotalOpo  = 0.0;
		double subTotalReg  = 0.0;
		double importeDesc  = 0.0;
		double importe      = 0.0;
		double total             = 0.0;
		int    numProds = 0;
		for(ESD esd: esdList){
			numProds += esd.getC(); 
			int tipoAlmacen = esd.getTa();
			importe = esd.getC() * esd.getP();
			subTotal += importe;
			if(tipoAlmacen == Constants.ALMACEN_PRINCIPAL){
				subTotal1ra += importe;
			} else if(tipoAlmacen == Constants.ALMACEN_OPORTUNIDAD){
				subTotalOpo += importe;
			} else if(tipoAlmacen == Constants.ALMACEN_REGALIAS){
				subTotalReg += importe;
			}
		}
		
		importeDesc = ((pedidoVenta.getEs().getPdc() + pedidoVenta.getEs().getPde()) * subTotal1ra ) /100.0;
		total = subTotal - importeDesc;
		
		logger.info("renderTotal(): numProds="+numProds);
		pedidoVenta.getEs().setEd(numProds);
		
		logger.info("renderTotal(): devolucion.getEs().getEd()="+pedidoVenta.getEs().getEd());
		panelDevolucion.getNumArt()    .setText(String.valueOf(pedidoVenta.getEs().getEd()));		
		
		logger.info("renderTotal(): subTotal1ra += importe;="+subTotal);
		panelDevolucion.getSubtotal()  .setText(Constants.df2Decimal.format(subTotal));
		
		logger.info("renderTotal(): importeDesc="+importeDesc);
		String descPorc = pedidoVenta.getEs().getPdc()+"% + "+pedidoVenta.getEs().getPde()+"%";
		panelDevolucion.getDescuento() .setToolTipText(descPorc);		
		panelDevolucion.getDescuento() .setText(Constants.df2Decimal.format(importeDesc));
		
		logger.info("renderTotal(): total="+total);
		panelDevolucion.getTotal().setText(Constants.df2Decimal.format(total));
		
	}

	private void renderTotalDev() {
		final List<ESD> esdList = devolucion.getEsdList();
		double subTotal     = 0.0;
		double subTotal1ra  = 0.0;
		double subTotalOpo  = 0.0;
		double subTotalReg  = 0.0;
		double importeDesc  = 0.0;
		double importe      = 0.0;
		double total             = 0.0;
		int    numProds = 0;
		for(ESD esd: esdList){
			numProds += esd.getC(); 
			int tipoAlmacen = esd.getTa();
			importe = esd.getC() * esd.getP();
			subTotal += importe;
			if(tipoAlmacen == Constants.ALMACEN_PRINCIPAL){
				subTotal1ra += importe;
			} else if(tipoAlmacen == Constants.ALMACEN_OPORTUNIDAD){
				subTotalOpo += importe;
			} else if(tipoAlmacen == Constants.ALMACEN_REGALIAS){
				subTotalReg += importe;
			}
		}
		
		importeDesc = descOriginal * subTotal1ra ;
		total = subTotal - importeDesc;
		
		logger.info("renderTotalDev(): numProds="+numProds);
		devolucion.getEs().setEd(numProds);
		devolucion.getEs().setnElem(numProds);		
		
		logger.info("renderTotalDev(): devolucion.getEs().getEd()="+devolucion.getEs().getEd());
		panelDevolucion.getNumArtDev().setText(String.valueOf(devolucion.getEs().getEd()));		
		
		logger.info("renderTotalDev(): subTotal1ra += importe;="+subTotal);
		logger.info("renderTotalDev(): importeDesc="+importeDesc);
		
		logger.info("renderTotalDev(): total="+total);
		devolucion.getEs().setTot(total);
		panelDevolucion.getTotalDev().setText(Constants.df2Decimal.format(total));
		
	}
	
	void imprimirTicket() {
		
		boolean printed = false;
		try {
			if(Main.dinamicDebug){
				TextReporter.DEBUG = true;
			} else {
				TextReporter.DEBUG = false;
			}
			
			TextReporter.setColumns(MemoryDAO.getTextSystemPrinterColumns());
			
			if(Main.dinamicTrace){
				TextReporter.DEBUG =true;
			} else {
				TextReporter.DEBUG =false;
			}
			logger.debug("imprimirTicket:devolucion.getEs().getNt()="+devolucionImpr.getEs().getNt()+", ImporteREcibido="+devolucionImpr.getEs().getIr()+", MP:"+devolucionImpr.getEs().getMp());
			
			JarpeReportsInfoDTO infoDTOTicket = VentaSesion.generaJarpeReportsInfoDTOTicket(devolucionImpr);
			logger.debug("imprimirTicket:fileTicket="+infoDTOTicket);			
			byte[] generateTicketTXTBytes = TextReporter.generateTicketTXTBytes(infoDTOTicket);
			logger.debug("imprimirTicket:fileTicket:{\n"+new String(generateTicketTXTBytes)+"\n}");
			Date today = new Date();
			
			String fileTicket     = "TICKET_"+infoDTOTicket.getParameters().get("venta.ticket")+"_" + Constants.sdfThinDate.format(today) + ".txt";
			String dirPrintTicket = "./";
			FileOutputStream fos = null;
			String realTicketFileName = null;
			try{
				realTicketFileName = dirPrintTicket+fileTicket;
				fos = new FileOutputStream(realTicketFileName);
				
				logger.debug("imprimirTicket:ticketFileName:"+realTicketFileName);
				
				fos.write(generateTicketTXTBytes);
				fos.flush();
				fos.close();
				
				logger.debug("imprimirTicket:OSValidator.isUnix()?:"+OSValidator.isUnix());
				logger.debug("imprimirTicket:OSValidator.isMac():"+OSValidator.isMac());
				
				if(OSValidator.isUnix() || OSValidator.isMac()){
					logger.debug("imprimirTicket: calling UnixSendToLP.printFile");
					UnixSendToLP.printFile((String)fileTicket);
				}else{
					logger.debug("imprimirTicket: calling SendFileToSystemPrinter.printFile");
					SendFileToSystemPrinter.printFile((String)fileTicket);
				}
				printed = true;
			}catch(Exception e){
				printed = false;
				throw new Exception(e.getMessage());
			}
		} catch (Exception ioe) {
			logger.error("imprimiedo",ioe);
			//JOptionPane.showMessageDialog(FramePrincipalControl.getInstance().getFramePrincipal(), "Error al imprimir Ticket", "Imprimir Ticket", JOptionPane.ERROR_MESSAGE);
		} finally {
			logger.debug("DESPUES DE IMPRIMIR TICKET: printed?"+printed);
			if (!printed) {
				logger.debug("ERROR AL IMPRIMIR");
				//t.printStackTrace(System.err);
				JOptionPane.showMessageDialog(FramePrincipalControl.getInstance().getFramePrincipal(), "Error grave al imprimir Ticket", "Imprimir Ticket", JOptionPane.ERROR_MESSAGE);
			}			
		}
	}


	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == panelDevolucion.getDetalleVentaJTable()) {
			logger.info("[USER]->mouseClicked:"+e.getClickCount()+" "+panelDevolucion.getDetalleVentaJTable().getSelectedRow()+","+panelDevolucion.getDetalleVentaJTable().getSelectedColumn());
			if (e.getClickCount() == 2 && panelDevolucion.getDetalleVentaJTable().getSelectedColumn() == 0) {
				
			}
		}
	}

	private void updateSelectedRow() {
		int sr = panelDevolucion.getDetalleVentaJTable().getSelectedRow();
		logger.debug("=>updateSelectedRow:Selected:sr=" + sr);
		if(sr != -1){
			PedidoVentaDetalleTableItem pvdDev = detalleVentaTableItemList.get(sr);
			logger.debug("=>updateSelectedRow:"+pvdDev.getPvd().getDev()+" < "+pvdDev.getPvd().getC()+" ?");
			if(pvdDev.getPvd().getDev() < pvdDev.getPvd().getC() ){
				panelDevolucion.getDevolver().setEnabled(true);
			} else {
				panelDevolucion.getDevolver().setEnabled(false);
			}
		} else {
			panelDevolucion.getDevolver().setEnabled(false);
		}
	}

	private void updateSelectedRow2() {
		int sr = panelDevolucion.getDetalleVentaJTableDev().getSelectedRow();
		logger.debug("=>updateSelectedRow2:Selected:sr=" + sr);
		if(sr != -1){
		
		} else {
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	void verVentaActual() {
		panelDevolucion.getCodigoBuscar().requestFocus();
	}

	public boolean isEstadoChecando() {
		logger.debug("isEditando:estadoChecando="+estadoChecando);
		return estadoChecando;
	}

}
