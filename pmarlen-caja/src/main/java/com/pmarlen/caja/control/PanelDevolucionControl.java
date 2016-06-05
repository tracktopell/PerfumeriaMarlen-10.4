package com.pmarlen.caja.control;

import com.pmarlen.backend.model.Producto;
import com.pmarlen.caja.dao.MemoryDAO;
import com.pmarlen.caja.model.PedidoVentaDetalleTableItem;
import com.pmarlen.caja.model.DevolucionDetalleTableModel;
import com.pmarlen.caja.model.ImporteCellRender;
import com.pmarlen.caja.model.PedidoVentaDetalleTableModel;
import com.pmarlen.caja.view.FramePrincipal;
import com.pmarlen.caja.view.PanelDevolucion;
import com.pmarlen.caja.view.ProductoCellRender;
import com.pmarlen.caja.view.TerminarVentaDlg;
import com.pmarlen.model.Constants;
import com.pmarlen.rest.dto.ESD;
import com.pmarlen.rest.dto.ES_ESD;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
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
	private DevolucionDetalleTableModel  devTM;
	private PedidoVentaDetalleTableModel devTM2;
	private boolean editando       = false;
	private boolean estadoChecando = false;
	private FramePrincipal framePrincipal = null;
	private String ticketBuscar  = null;
	public PanelDevolucionControl(PanelDevolucion panelDevolucion) {
		this.panelDevolucion = panelDevolucion;
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
		panelDevolucion.getDetalleVentaJTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				boolean valueIsAdjusting = e.getValueIsAdjusting();
				if (!valueIsAdjusting) {
					updateSelectedRow();
				}
			}
		});
		panelDevolucion.getDetalleVentaJTable().setRowHeight(50);
		panelDevolucion.getDetalleVentaJTable().updateUI();

		panelDevolucion.getDetalleVentaJTableDev().addMouseListener(this);
		panelDevolucion.getDetalleVentaJTableDev().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
		panelDevolucion.getDetalleVentaJTableDev().getColumnModel().getColumn(1).setCellRenderer(productoCellRender);
		panelDevolucion.getDetalleVentaJTableDev().getColumnModel().getColumn(2).setCellRenderer(importeCellRender);
		panelDevolucion.getDetalleVentaJTableDev().getColumnModel().getColumn(3).setCellRenderer(importeCellRender);		
		panelDevolucion.getDetalleVentaJTableDev().addComponentListener(new JTableColumnAutoResizeHelper(new int[]{6,62,14,18}));
		panelDevolucion.getDetalleVentaJTableDev().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				boolean valueIsAdjusting = e.getValueIsAdjusting();				
				if (!valueIsAdjusting) {
					updateSelectedRow2();
				}
			}
		});
		panelDevolucion.getDetalleVentaJTableDev().setRowHeight(50);
		panelDevolucion.getDetalleVentaJTableDev().updateUI();

		this.panelDevolucion.getTicket().addActionListener(this);
		this.panelDevolucion.getCodigoBuscar().addActionListener(this);
		this.panelDevolucion.getBuscar().addActionListener(this);
		this.panelDevolucion.getTerminar().addActionListener(this);
		this.panelDevolucion.getCancelar().addActionListener(this);
		this.panelDevolucion.getDevolver().addActionListener(this);
		
		editando       = true;
		estadoChecando = true;
	}
	
	public void estadoInicial() {		
		
		ApplicationLogic.getInstance().getVentaSesion().nuevaSesionVenta();
		devTM.setDetalleVentaTableItemList(detalleVentaTableItemList);
		
		panelDevolucion.getTipoAlmacen().clearSelection();
		panelDevolucion.getDetalleVentaJTable().updateUI();
		estadoChecando = false;				
		renderTotal();
		panelDevolucion.getTicket().setText("");
		panelDevolucion.getCodigoBuscar().setText("");
		panelDevolucion.getTicket().requestFocus();
		panelDevolucion.getDevolver().setEnabled(false);
		panelDevolucion.getTicket().setEnabled(true);
		panelDevolucion.getBuscar().setEnabled(true);
					
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
	
	//private Producto productoBuscar = new Producto();
	private void codigoBuscar_ActionPerformed() {
		String codigoBuscar = panelDevolucion.getCodigoBuscar().getText().trim();
		
		logger.info("[USER]->codigoBuscar_ActionPerformed:codigoBuscar=->" + codigoBuscar+"<-");
				
	}
	

	void terminar_ActionPerformed() {
		logger.info("[USER]->terminar_ActionPerformed");
		/*
		if (ApplicationLogic.getInstance().getVentaSesion().getDetalleVentaTableItemList().size() == 0) {
			JOptionPane.showMessageDialog(FramePrincipalControl.getInstance().getFramePrincipal(),
					"Cuando termine de agregar más productos, podra terminar esta venta", "Terminar Venta",
					JOptionPane.WARNING_MESSAGE);

			panelDevolucion.getCodigoBuscar().requestFocus();
			
			return;
		}

		try {
			TerminarVentaDlg tvDlg = new TerminarVentaDlg(FramePrincipalControl.getInstance().getFramePrincipal(), true);
			TerminarVentaControl tvdControl = new TerminarVentaControl(tvDlg);

			tvdControl.estadoInicial();
			
			if(!tvdControl.isCierreCorrecto()){
				return;
			}
			
			pedidoVenta = ApplicationLogic.getInstance().getVentaSesion().getVenta();
			
			logger.debug("terminar_ActionPerformed(): TERMINADA Y ANTES DE IMPRIMIR TICKET:"+pedidoVenta.getEs().getNt()+", ImporteRecibido="+pedidoVenta.getEs().getIr()+", MP="+pedidoVenta.getEs().getMp());
			
			//TotalesCalculados ct = LogicaFinaciera.calculaTotales(esesdLAstForPrint.getEs(), esesdLAstForPrint.getEsdList(), true, 0.0);
			//logger.info("terminar_ActionPerformed:-->>LogicaFinaciera.calculaTotales: TotalesCalculados:\n"+ct);
			
			logger.debug("terminar_ActionPerformed(): TICKET:"+pedidoVenta.getEs().getNt()+", ImporteRecibido="+pedidoVenta.getEs().getIr()+", MP="+pedidoVenta.getEs().getMp());
			
			if (ApplicationLogic.getInstance().isPrintingEnabled()) {
				new Thread() {
					@Override
					public void run() {
						logger.debug("terminar_ActionPerformed(): despues de cerrar dialogo: TICKET:"+pedidoVenta.getEs().getNt()+", ImporteRecibido="+pedidoVenta.getEs().getIr()+", MP="+pedidoVenta.getEs().getMp());
						
					}
				}.start();
			}

			estadoInicial();

		} catch (Exception ex) {
			ex.printStackTrace(System.err);
			JOptionPane.showMessageDialog(FramePrincipalControl.getInstance().getFramePrincipal(), ex.getMessage(), "Venta", JOptionPane.ERROR_MESSAGE);

		} finally {
			panelDevolucion.getCodigoBuscar().requestFocus();
		}
		*/
	}
	
	
	void cancelar_ActionPerformed() {
		logger.info("[USER]->cancelar_ActionPerformed()");
		int r = JOptionPane.showConfirmDialog(FramePrincipalControl.getInstance().getFramePrincipal(), "¿Cancelar la Devolucion Actual?", "Devolucion", JOptionPane.YES_NO_OPTION);
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
				detalleVentaTableItemList2.add(new PedidoVentaDetalleTableItem(pvdDev));
				pvdDev.getPvd().setDev(pvdDev.getPvd().getDev()+1);
				
				panelDevolucion.getDetalleVentaJTable().updateUI();
				panelDevolucion.getDetalleVentaJTableDev().updateUI();
				
				if(pvdDev.getPvd().getDev() >= pvdDev.getPvd().getC()){
					panelDevolucion.getCodigoBuscar().requestFocus();
					panelDevolucion.getDevolver().setEnabled(false);
				}
			}
		} else {
			panelDevolucion.getDevolver().setEnabled(false);
		}
	}
	
	private void buscarEnServidor(){
		new Thread(){
			public void run(){
				pedidoVenta = null;
				try {
					pedidoVenta = MemoryDAO.getTicket(ticketBuscar);
					logger.info("[USER]->buscarEnServidor(): devolucion="+pedidoVenta);
					List<ESD> esdList = pedidoVenta.getEsdList();
					for(ESD esd: esdList){
						Producto producto = MemoryDAO.fastSearchProducto(esd.getCb()).reverse();
						PedidoVentaDetalleTableItem pvdti = new PedidoVentaDetalleTableItem(producto, esd, esd.getTa());
						detalleVentaTableItemList.add(pvdti);
					}
					
					panelDevolucion.getDetalleVentaJTable().updateUI();
					panelDevolucion.getTicket().setEnabled(false);
					panelDevolucion.getBuscar().setEnabled(false);
					panelDevolucion.getCodigoBuscar().requestFocus();
					panelDevolucion.getDevolver().setEnabled(false);
					renderTotal();
				}catch(Exception e){
					logger.error("->buscarEnServidor:", e);
					JOptionPane.showMessageDialog(getFramePrincipal(), "No se encotro Ticket:"+ticketBuscar, "BUSCAR", JOptionPane.WARNING_MESSAGE);
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
		logger.info("renderTotal(): devolucion.getEs().getEd()="+pedidoVenta.getEs().getEd());
		panelDevolucion.getNumArt()    .setText(String.valueOf(pedidoVenta.getEs().getEd()));		
		final double st = pedidoVenta.getEs().getSt1() + pedidoVenta.getEs().getStO() + pedidoVenta.getEs().getStR();
		logger.info("renderTotal(): st="+st);
		panelDevolucion.getSubtotal()  .setText(Constants.df2Decimal.format(st));
		double desc = pedidoVenta.getEs().getPdc() * st / 100.0;
		logger.info("renderTotal(): desc="+desc);
		panelDevolucion.getDescuento() .setText(pedidoVenta.getEs().getPdc()+"% = "+Constants.df2Decimal.format(desc));
		final double t = st - desc; 
		logger.info("renderTotal(): t="+t);
		panelDevolucion.getTotal().setText(Constants.df2Decimal.format(t));
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
		return estadoChecando;
	}

	public boolean isEditando() {
		return editando;
	}

}
