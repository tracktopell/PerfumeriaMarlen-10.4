package com.pmarlen.caja.control;

import com.pmarlen.backend.model.Almacen;
import com.pmarlen.backend.model.quickviews.InventarioSucursalQuickView;
import com.pmarlen.businesslogic.reports.TextReporter;
import com.pmarlen.caja.Main;
import com.pmarlen.caja.dao.MemoryDAO;
import com.pmarlen.caja.model.ImporteCellRender;
import com.pmarlen.caja.model.PedidoVentaDetalleTableItem;
import com.pmarlen.caja.model.PedidoVentaDetalleTableModel;
import com.pmarlen.caja.model.VentaSesion;
import com.pmarlen.caja.view.PanelVenta;
import com.pmarlen.caja.view.ProductoCellRender;
import com.pmarlen.caja.view.TerminarVentaDlg;
import com.pmarlen.caja.view.TokenFrame;
import com.pmarlen.model.Constants;
import com.pmarlen.model.JarpeReportsInfoDTO;
import com.pmarlen.model.OSValidator;
import com.pmarlen.rest.dto.ESD;
import com.pmarlen.rest.dto.ES_ESD;
import com.pmarlen.rest.dto.I;
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
import java.util.HashMap;
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
public class PanelVentaControl implements ActionListener, TableModelListener, MouseListener {
	private static Logger logger = Logger.getLogger(PanelVentaControl.class.getName());
	private PanelVenta panelVenta;
	private boolean estadoChecando = false;
	private PedidoVentaDetalleTableModel pvdTM;
	private ES_ESD esesdLAstForPrint;
	
	public PanelVentaControl(PanelVenta panelVenta) {
		this.panelVenta = panelVenta;
		
		pvdTM = new PedidoVentaDetalleTableModel();
		this.panelVenta.getDetalleVentaJTable().setModel(pvdTM);
		pvdTM.addTableModelListener(this);
		logger.debug("PanelVentaControl: table columns=" + panelVenta.getDetalleVentaJTable().getColumnCount());

		Color bgc= panelVenta.getDetalleVentaJTable().getBackground();
		Color fgc= panelVenta.getDetalleVentaJTable().getForeground();
		Color sbgc= panelVenta.getDetalleVentaJTable().getSelectionBackground();
		Color sfgc= panelVenta.getDetalleVentaJTable().getSelectionForeground();
		
		final ImporteCellRender importeCellRender   = new ImporteCellRender();
		final ProductoCellRender productoCellRender = new ProductoCellRender();
		productoCellRender.setColors(bgc, fgc, sbgc, sfgc);

		importeCellRender.setHorizontalAlignment(SwingConstants.RIGHT);
		panelVenta.getDetalleVentaJTable().addMouseListener(this);
		panelVenta.getDetalleVentaJTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		panelVenta.getDetalleVentaJTable().getColumnModel().getColumn(1).setCellRenderer(productoCellRender);
		panelVenta.getDetalleVentaJTable().getColumnModel().getColumn(2).setCellRenderer(importeCellRender);
		panelVenta.getDetalleVentaJTable().getColumnModel().getColumn(3).setCellRenderer(importeCellRender);
		
		panelVenta.getDetalleVentaJTable().addComponentListener(new JTableColumnAutoResizeHelper(
				new int[]{6,62,14,18}));
		
		panelVenta.getDetalleVentaJTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				boolean valueIsAdjusting = e.getValueIsAdjusting();
				//logger.debug("=>TableSelection:"+fi+"->"+li+", adj?"+valueIsAdjusting);
				if (!valueIsAdjusting) {
					updateSelectedRow();
				}
			}
		});

		this.panelVenta.getCodigoBuscar().addActionListener(this);
		this.panelVenta.getTerminar().addActionListener(this);
		this.panelVenta.getCancelar().addActionListener(this);
		this.panelVenta.getChechar().addActionListener(this);
		
		//abilitarVentaOportunidad(false);
		//abilitarVentaRegalias(false);
	}
	
	public void abilitarVentaOportunidad(boolean status){
		if(!status){
			this.panelVenta.getDesdeOportunidad().setVisible(false);
			this.panelVenta.getTipoAlmacen().remove(this.panelVenta.getDesdeOportunidad());
		}else{
			this.panelVenta.getDesdeOportunidad().setVisible(true);
			this.panelVenta.getTipoAlmacen().add(this.panelVenta.getDesdeOportunidad());
		}	
	}
	public void abilitarVentaRegalias(boolean status){
		if(!status){
			this.panelVenta.getDesdeRegalias().setVisible(false);
			this.panelVenta.getTipoAlmacen().remove(this.panelVenta.getDesdeRegalias());
		}else{
			this.panelVenta.getDesdeRegalias().setVisible(true);
			this.panelVenta.getTipoAlmacen().add(this.panelVenta.getDesdeRegalias());
		}	
	}
	public void estadoInicial() {		
		
		ApplicationLogic.getInstance().getVentaSesion().nuevaSesionVenta();
		pvdTM.setDetalleVentaTableItemList(ApplicationLogic.getInstance().getVentaSesion().getDetalleVentaTableItemList());
		
		panelVenta.getTipoAlmacen().clearSelection();		
		panelVenta.getDesdeLinea().setSelected(true);
		panelVenta.getDesdeOportunidad().setSelected(false);		
		panelVenta.getDetalleVentaJTable().updateUI();
		panelVenta.resetInfoForProducto(null,0);
		estadoChecando = false;		
		actualizarEstadoChecado();
		renderTotal();
		panelVenta.getCodigoBuscar().setText("");
		panelVenta.getCodigoBuscar().requestFocus();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == panelVenta.getCodigoBuscar()) {
			codigoBuscar_ActionPerformed();
		} else if (e.getSource() == this.panelVenta.getTerminar()) {
			terminar_ActionPerformed();
		} else if (e.getSource() == this.panelVenta.getCancelar()) {
			cancelar_ActionPerformed();
		} else if (e.getSource() == this.panelVenta.getChechar()) {
			checar_ActionPerformed();
		} 

	}
	
	//private Producto productoBuscar = new Producto();
	private void codigoBuscar_ActionPerformed() {
		String codigoBuscar = panelVenta.getCodigoBuscar().getText().trim();
		
		logger.info("[USER]->codigoBuscar_ActionPerformed:codigoBuscar=->" + codigoBuscar+"<-");
		String[] linesCBArr = codigoBuscar.split("(\\r?\\n)|([ ]+)");
		List<I> encontrados=new ArrayList<I>();
		StringBuilder sbNoEncontrados   = new StringBuilder();
		StringBuilder sbNoHayExistencia = new StringBuilder();
		I productoEncontrado = null;
		I productoEncontradoSinExistencia = null;
		
		logger.debug("\tcodigoBuscar_ActionPerformed: panelVenta.getTipoAlmacen="+panelVenta.getTipoAlmacen().getSelection().getSelectedObjects());
		int contAgregados=0;
		int tipoAlmacenSeleccionado =	panelVenta.getDesdeLinea().isSelected()			?	Constants.ALMACEN_PRINCIPAL:
										panelVenta.getDesdeOportunidad().isSelected()	?	Constants.ALMACEN_OPORTUNIDAD:
										panelVenta.getDesdeRegalias().isSelected()		?	Constants.ALMACEN_REGALIAS:
																							Constants.ALMACEN_PRINCIPAL;
		logger.debug("\tcodigoBuscar_ActionPerformed: tipoAlmacenSeleccionado="+tipoAlmacenSeleccionado);
		
		for(String lineCB: linesCBArr){
			logger.trace("\tcodigoBuscar_ActionPerformed:line->"+lineCB+"<-");
			String[] codesCB = lineCB.split("\\s");
			for(String cdLineCB: codesCB){
				logger.trace("\t\tcodigoBuscar_ActionPerformed:code->"+cdLineCB+"<-");
				productoEncontrado = MemoryDAO.fastSearchProducto(cdLineCB);
				if(productoEncontrado == null){
					logger.trace("\t\t\tcodigoBuscar_ActionPerformed:producto no encontrado:"+cdLineCB);
					sbNoEncontrados.append(cdLineCB).append(" ");					
				} else {
					logger.info("\t\t\tcodigoBuscar_ActionPerformed:producto encontrado:"+productoEncontrado);
					if(productoEncontrado.getA1o2x1()!=null||productoEncontrado.getaOo2x1()!=null||productoEncontrado.getaRo2x1()!=null){
						logger.info("\t\t\tcodigoBuscar_ActionPerformed:\t=>producto EN OFERTA:{"+productoEncontrado.getA1o2x1()+","+productoEncontrado.getaOo2x1()+","+productoEncontrado.getaRo2x1()+"}");
					}
					encontrados.add(productoEncontrado);
				}
			}
		}
		
		if(encontrados.size() > 0) {
			productoEncontrado =  null;
			productoEncontradoSinExistencia = null;
			final HashMap<Integer, Almacen> tipoAlmacen = ApplicationLogic.getInstance().getTipoAlmacen();
			for(I i:encontrados){
				productoEncontrado = i;
				
				final ArrayList<PedidoVentaDetalleTableItem> detalleVentaTableItemList = ApplicationLogic.getInstance().getVentaSesion().getDetalleVentaTableItemList();
				int cantA=0;
				for(PedidoVentaDetalleTableItem dvpi: detalleVentaTableItemList){
					if(dvpi.getProducto().getCodigoBarras().equals(productoEncontrado.getCb()) && dvpi.getTipoAlmacen() == tipoAlmacenSeleccionado){
						cantA += dvpi.getPvd().getC();
					}
				}
				
				if(!estadoChecando){
					ESD esd = new ESD();
					esd.setTa(tipoAlmacenSeleccionado);
					esd.setCb(productoEncontrado.getCb());
					esd.setA(tipoAlmacen.get(tipoAlmacenSeleccionado).getId());
					esd.setC(1);
					
					boolean huboExistencia=false;
					int cantChek = cantA;
					boolean oferta2X1=false;
					if(productoEncontrado.getA1o2x1()!=null||productoEncontrado.getaOo2x1()!=null||productoEncontrado.getaRo2x1()!=null){
						cantChek = cantA * 2;
						oferta2X1=true;
					}
					if(tipoAlmacenSeleccionado == Constants.ALMACEN_PRINCIPAL) {
						if((productoEncontrado.getA1c() - cantA ) >= 1){
							esd.setP(productoEncontrado.getA1p());
							huboExistencia = true;
						}
					} else if(tipoAlmacenSeleccionado == Constants.ALMACEN_REGALIAS ) {
						if(( productoEncontrado.getaRc() - cantA ) >=1){
							esd.setP(productoEncontrado.getaRp());
							huboExistencia = true;
						}
						
					} else if(tipoAlmacenSeleccionado == Constants.ALMACEN_OPORTUNIDAD ) {
						if((productoEncontrado.getaOc() - cantA ) >=1){
							esd.setP(productoEncontrado.getaOp());
							huboExistencia = true;
						}						
					}

					if(huboExistencia) {
						esd.setCb(productoEncontrado.getCb());
						if(oferta2X1){
							
							PedidoVentaDetalleTableItem detalleVentaTableItemNuevo = new PedidoVentaDetalleTableItem(productoEncontrado.reverse(), esd, tipoAlmacenSeleccionado, true, false);
							logger.debug("\t\t=> + "+esd.getC()+" x "+esd.getCb()+"("+tipoAlmacenSeleccionado+") ["+esd.getA()+"]");
							ApplicationLogic.getInstance().getVentaSesion().getDetalleVentaTableItemList().add(detalleVentaTableItemNuevo);
							contAgregados++;
							
							ESD esdR = new ESD(esd);
							esdR.setP(0.0);
							
							PedidoVentaDetalleTableItem detalleVentaTableItemOferta = new PedidoVentaDetalleTableItem(productoEncontrado.reverse(), esdR, tipoAlmacenSeleccionado, true, true);
							ApplicationLogic.getInstance().getVentaSesion().getDetalleVentaTableItemList().add(detalleVentaTableItemOferta);
							contAgregados++;
						
						}else{
							PedidoVentaDetalleTableItem detalleVentaTableItemNuevo = new PedidoVentaDetalleTableItem(productoEncontrado.reverse(), esd, tipoAlmacenSeleccionado);
							logger.debug("\t\t=> + "+esd.getC()+" x "+esd.getCb()+"("+tipoAlmacenSeleccionado+") ["+esd.getA()+"]");
							ApplicationLogic.getInstance().getVentaSesion().getDetalleVentaTableItemList().add(detalleVentaTableItemNuevo);
							contAgregados++;
						}
						
					} else {
						logger.debug("\t\t=>   "+esd.getC()+" x "+esd.getCb()+"("+tipoAlmacenSeleccionado+") ["+esd.getA()+"] NO HAY EXISTENCIA: {"+productoEncontrado.getA1c()+" | "+productoEncontrado.getaRc()+" | "+productoEncontrado.getaOc()+" } + "+cantA);
						sbNoHayExistencia.append(productoEncontrado.getCb()).append(" ");
						productoEncontradoSinExistencia = productoEncontrado;
					}
				}
			}
			if(contAgregados>0){
				panelVenta.getDetalleVentaJTable().updateUI();
				int idx = 0;
				idx = ApplicationLogic.getInstance().getVentaSesion().getDetalleVentaTableItemList().size()-1;
				
				renderTotal();			
				panelVenta.getDetalleVentaJTable().getSelectionModel().setSelectionInterval(idx, idx);
				scrollToVisible(panelVenta.getDetalleVentaJTable(),idx,1);
			}
			panelVenta.getCodigoBuscar().setText("");
			panelVenta.resetInfoForProducto(productoEncontrado.reverse(),tipoAlmacenSeleccionado);
		}
		
		final String noEncotrados = sbNoEncontrados.toString().trim();
		final String noHayExistencia = sbNoHayExistencia.toString().trim();
		if(noEncotrados.length() > 1) {
			new Thread() {
				@Override
				public void run() {
					panelVenta.getCodigoBuscar().setText(noEncotrados);
					Color pc = panelVenta.getCodigoBuscar().getBackground();
					panelVenta.getCodigoBuscar().setBackground(Color.red);
					logger.debug("\t\t\tcodigoBuscar_ActionPerformed:ROJO:["+noEncotrados+"]");
					try {
						for (int i = 0; i < 3; i++) {
							panelVenta.getCodigoBuscar().setBackground(Color.RED);
							Thread.sleep(500);
							panelVenta.getCodigoBuscar().setBackground(pc);
							Thread.sleep(100);
						}
					} catch (InterruptedException ex) {
						
					} finally {
						panelVenta.getCodigoBuscar().setBackground(pc);
						panelVenta.getCodigoBuscar().setText("");
					}
				}
			}.start();
		} else if(noHayExistencia.length() > 1) {
			new Thread() {
				@Override
				public void run() {
					panelVenta.getCodigoBuscar().setText(noHayExistencia);
					Color pc = panelVenta.getCodigoBuscar().getBackground();
					panelVenta.getCodigoBuscar().setBackground(Color.red);
					logger.debug("\t\t\tcodigoBuscar_ActionPerformed:ORANGE:["+noEncotrados+"]");
					try {
						for (int i = 0; i < 3; i++) {
							panelVenta.getCodigoBuscar().setBackground(Color.ORANGE);
							Thread.sleep(500);
							panelVenta.getCodigoBuscar().setBackground(pc);
							Thread.sleep(100);
						}
					} catch (InterruptedException ex) {
						
					} finally {
						panelVenta.getCodigoBuscar().setBackground(pc);
						panelVenta.getCodigoBuscar().setText("");
					}
				}
			}.start();
		}
		
		if(!estadoChecando && productoEncontradoSinExistencia!=null) {
			JOptionPane.showMessageDialog(FramePrincipalControl.getInstance().getFramePrincipal(),
					"ESTE PRODUCTO ["+productoEncontradoSinExistencia.getCb()+"] NO TIENE EXISTENCIA SUFICIENTE", 
					"AGREGAR PRODUCTO",
					JOptionPane.WARNING_MESSAGE);

			panelVenta.getCodigoBuscar().requestFocus();
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
		logger.info("[USER]->terminar_ActionPerformed");
		if (ApplicationLogic.getInstance().getVentaSesion().getDetalleVentaTableItemList().size() == 0) {
			JOptionPane.showMessageDialog(FramePrincipalControl.getInstance().getFramePrincipal(),
					"Cuando termine de agregar más productos, podra terminar esta venta", "Terminar Venta",
					JOptionPane.WARNING_MESSAGE);

			panelVenta.getCodigoBuscar().requestFocus();
			
			return;
		}

		try {
			TerminarVentaDlg tvDlg = new TerminarVentaDlg(FramePrincipalControl.getInstance().getFramePrincipal(), true);
			TerminarVentaControl tvdControl = new TerminarVentaControl(tvDlg);

			tvdControl.estadoInicial();
			
			if(!tvdControl.isCierreCorrecto()){
				return;
			}
			
			esesdLAstForPrint = ApplicationLogic.getInstance().getVentaSesion().getVenta();
			
			logger.debug("terminar_ActionPerformed(): TERMINADA Y ANTES DE IMPRIMIR TICKET:"+esesdLAstForPrint.getEs().getNt()+", ImporteRecibido="+esesdLAstForPrint.getEs().getIr()+", MP="+esesdLAstForPrint.getEs().getMp());
			
			//TotalesCalculados ct = LogicaFinaciera.calculaTotales(esesdLAstForPrint.getEs(), esesdLAstForPrint.getEsdList(), true, 0.0);
			//logger.info("terminar_ActionPerformed:-->>LogicaFinaciera.calculaTotales: TotalesCalculados:\n"+ct);
			
			logger.debug("terminar_ActionPerformed(): TICKET:"+esesdLAstForPrint.getEs().getNt()+", ImporteRecibido="+esesdLAstForPrint.getEs().getIr()+", MP="+esesdLAstForPrint.getEs().getMp());
			
			if (ApplicationLogic.getInstance().isPrintingEnabled()) {
				new Thread() {
					@Override
					public void run() {
						logger.debug("terminar_ActionPerformed(): despues de cerrar dialogo: TICKET:"+esesdLAstForPrint.getEs().getNt()+", ImporteRecibido="+esesdLAstForPrint.getEs().getIr()+", MP="+esesdLAstForPrint.getEs().getMp());
						imprimirTicket();
					}
				}.start();
			}

			estadoInicial();

		} catch (Exception ex) {
			ex.printStackTrace(System.err);
			JOptionPane.showMessageDialog(FramePrincipalControl.getInstance().getFramePrincipal(), ex.getMessage(), "Venta", JOptionPane.ERROR_MESSAGE);
		} finally {
			panelVenta.getCodigoBuscar().requestFocus();
		}
	}
	// infinutum : t5517071411 / Aqwert56
	// reporte: 40643773 3 a 5 dias 018001230000
	
	void cancelar_ActionPerformed() {
		logger.info("[USER]->cancelar_ActionPerformed:");
		int r = JOptionPane.showConfirmDialog(FramePrincipalControl.getInstance().getFramePrincipal(), "   ¿ Cancelar la Venta Actual ?\nNecesitara autorización por TOKEN.", "Venta", JOptionPane.YES_NO_OPTION);
		if (r == JOptionPane.YES_OPTION) {
			TokenFrame tf = new TokenFrame(FramePrincipalControl.getInstance().getFramePrincipal());

			tf.setVisible(true);

			if(tf.isAccepted()){
				autorizadoPorToken = true;
				logger.info("[USER]->cancelar_ActionPerformed:: Se autorizo.");
				
				estadoInicial();
				panelVenta.getCodigoBuscar().requestFocus();

			} else {
				logger.debug("[USER]->cancelar_ActionPerformed: NO Se autorizo.");
				JOptionPane.showMessageDialog(FramePrincipalControl.getInstance().getFramePrincipal(), "ESTE INCIDENTE SE REPORTARA", "Venta", JOptionPane.YES_NO_OPTION);
			}
		} else {
			logger.debug("[USER]->cancelar_ActionPerformed: NO CANCELO.");
			JOptionPane.showMessageDialog(FramePrincipalControl.getInstance().getFramePrincipal(), "ESTE INCIDENTE SE REPORTARA", "Venta", JOptionPane.YES_NO_OPTION);
		}
	}
	
	boolean autorizadoPorToken = false;
	
	
	void checar_ActionPerformed(){
		estadoChecando = ! estadoChecando;
		actualizarEstadoChecado();
		panelVenta.getCodigoBuscar().requestFocus();
	}

	private void actualizarEstadoChecado() {
		int pvw  = (int)panelVenta.getSize().getWidth();
		int opvw = (pvw * 70)/100;
		logger.info("[USER]->actualizarEstadoChecado="+estadoChecando+" | DL="+panelVenta.getSplitPanel().getDividerLocation()+" ("+pvw+","+panelVenta.getSplitPanel().getMaximumDividerLocation()+")("+pvw+","+opvw+")");
		if(estadoChecando) {
			panelVenta.getChechar().setText("AGREGAR [ F9 ]");			
			panelVenta.getCodigoBuscar().setBackground(Color.YELLOW);
			panelVenta.getSplitPanel().setDividerLocation(1);
		} else {
			panelVenta.getChechar().setText("CHECAR [ F9 ]");
			panelVenta.getCodigoBuscar().setBackground(Color.WHITE);
			panelVenta.getSplitPanel().setDividerLocation(opvw);
		}
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		panelVenta.getDetalleVentaJTable().updateUI();
		renderTotal();
	}
	
	private void renderTotal() {
		
		ApplicationLogic.getInstance().getVentaSesion().calcularTotales();
		
		panelVenta.getNumArt()    .setText(String.valueOf(ApplicationLogic.getInstance().getVentaSesion().getNumElemVta()));
		panelVenta.getNumArt()    .setToolTipText(ApplicationLogic.getInstance().getVentaSesion().getNumElemSinDescVta()+"+"+ApplicationLogic.getInstance().getVentaSesion().getNunElemDescontablesVta());
		final String stTT = Constants.df2Decimal.format(ApplicationLogic.getInstance().getVentaSesion().getTotalBrutoDescontable()) + " + "+
				Constants.df2Decimal.format(ApplicationLogic.getInstance().getVentaSesion().getTotalBrutoFijo()) + " = " +
				Constants.df2Decimal.format(ApplicationLogic.getInstance().getVentaSesion().getTotalBruto());
		panelVenta.getSubtotal()  .setToolTipText(
				stTT);
		panelVenta.getSubtotal()  .setText(Constants.df2Decimal.format(ApplicationLogic.getInstance().getVentaSesion().getTotalBruto()));
		
		panelVenta.getDescuento() .setText(ApplicationLogic.getInstance().getVentaSesion().getPorcentajeDescuentoCalculado()+"% = "+Constants.df2Decimal.format(ApplicationLogic.getInstance().getVentaSesion().getDescuentoCalculado()));
		panelVenta.getDescuento() .setToolTipText(Constants.df2Decimal.format(ApplicationLogic.getInstance().getVentaSesion().getTotalBrutoDescontable())+" * "+
				Constants.df2Decimal.format(ApplicationLogic.getInstance().getVentaSesion().getDescuentoFactor()));
		
		
		panelVenta.getTotal()     .setText(Constants.df2Decimal.format(ApplicationLogic.getInstance().getVentaSesion().getTotal()));
		logger.debug("renderTotal:subTotal = "+stTT);
		logger.debug("renderTotal:descuento= "+ApplicationLogic.getInstance().getVentaSesion().getDescuentoCalculado()+
				" = " +ApplicationLogic.getInstance().getVentaSesion().getPorcentajeDescuentoCalculado()+
				"% + "+ApplicationLogic.getInstance().getVentaSesion().getPorcentajeDescuentoExtra()+"% ");
		logger.debug("renderTotal:="+Constants.df2Decimal.format(ApplicationLogic.getInstance().getVentaSesion().getTotal())+" => "+ApplicationLogic.getInstance().getVentaSesion().getTotalRedondeado2Dec());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == panelVenta.getDetalleVentaJTable()) {
			logger.info("[USER]->mouseClicked:"+e.getClickCount()+" "+panelVenta.getDetalleVentaJTable().getSelectedRow()+","+panelVenta.getDetalleVentaJTable().getSelectedColumn());
			if (e.getClickCount() == 2 && panelVenta.getDetalleVentaJTable().getSelectedColumn() == 0) {
				editarCantidad(panelVenta.getDetalleVentaJTable().getSelectedRow());
			}
		}
	}

	private void updateSelectedRow() {
		int sr = panelVenta.getDetalleVentaJTable().getSelectedRow();
		logger.debug("=>Selected:sr=" + sr);
		if(sr != -1){
			PedidoVentaDetalleTableItem pvdti = ApplicationLogic.getInstance().getVentaSesion().getDetalleVentaTableItemList().get(sr);
			panelVenta.resetInfoForProducto((InventarioSucursalQuickView)pvdti.getProducto(),pvdti.getTipoAlmacen());
		} else {
			panelVenta.resetInfoForProducto(null,0);
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

	private void editarCantidad(int selectedRow) {
		logger.info("[USER]->editarCantidad:selectedRow="+selectedRow);
		final PedidoVentaDetalleTableItem dvti = ApplicationLogic.getInstance().getVentaSesion().getDetalleVentaTableItemList().get(selectedRow);
		if(dvti.isOferta2x1() && dvti.isRegalo()){
			JOptionPane.showMessageDialog(panelVenta, "NO SE PUEDE CAMBIAR ESTA CANTIDAD", "Cambiar la Cantidad ...", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		int cantidad = dvti.getPvd().getC();		
		ESD pvd = dvti.getPvd();		
		InventarioSucursalQuickView p = (InventarioSucursalQuickView)dvti.getProducto();
		int ta = dvti.getTipoAlmacen();		
		int max = -1;
		if(ta == Constants.ALMACEN_PRINCIPAL){
			max = p.getA1c();
		} else if(ta == Constants.ALMACEN_REGALIAS){
			max = p.getaRc();
		} else if(ta == Constants.ALMACEN_OPORTUNIDAD){
			max = p.getaOc();
		}
		
		Object result = JOptionPane.showInputDialog(panelVenta, "Cambiar la Cantidad [0, "+max+"]:", cantidad);
		if (result != null) {
			Integer nuevaCantidad = new Integer(result.toString().trim());
			
			if (nuevaCantidad > 0 && nuevaCantidad <= max) {
				
				dvti.getPvd().setC(nuevaCantidad);
				panelVenta.getDetalleVentaJTable().updateUI();
				renderTotal();
			} else if (nuevaCantidad > max) {
				JOptionPane.showMessageDialog(panelVenta, "No puede agregegar > "+max, "Modificar", JOptionPane.WARNING_MESSAGE);
			} else if(nuevaCantidad == 0){
				ventaEliminarProd(selectedRow);
			}
		}
		panelVenta.getCodigoBuscar().requestFocus();

	}
	
	void ventaEliminarProdMenu() { 
		int selectedRow = panelVenta.getDetalleVentaJTable().getSelectedRow();
		ventaEliminarProd(selectedRow);
	}
	
	private void ventaEliminarProd(int selectedRow) { 
		final int selectedRowForDelete = selectedRow;
		new Thread(){
			@Override
			public void run() {
				autorizadoPorToken=false;
				int r = JOptionPane.showConfirmDialog(FramePrincipalControl.getInstance().getFramePrincipal(), "¿ Eliminar el producto del pedido actual?\nNecesitara autorización por TOKEN.", "Venta", JOptionPane.YES_NO_OPTION);

				if (r == JOptionPane.YES_OPTION) {
					TokenFrame tf = new TokenFrame(FramePrincipalControl.getInstance().getFramePrincipal());

					tf.setVisible(true);

					if(tf.isAccepted()){
						autorizadoPorToken = true;
						logger.info("[USER]->cancelar_ActionPerformed:: Se autorizo.");
						
						ApplicationLogic.getInstance().getVentaSesion().getDetalleVentaTableItemList().remove(selectedRowForDelete);
						panelVenta.getDetalleVentaJTable().updateUI();
						renderTotal();

					} else {
						logger.debug("[USER]->cancelar_ActionPerformed: NO Se autorizo.");
						JOptionPane.showMessageDialog(FramePrincipalControl.getInstance().getFramePrincipal(), 
								"\tESTE INCIDENTE SE REPORTARA\nSi solo necesitaba checar precio, NO lo agregue\nUtilice esta funcion de checar.", "Venta", JOptionPane.YES_NO_OPTION);
					}
				} else {
					logger.debug("[USER]->cancelar_ActionPerformed: NO CANCELO.");
					JOptionPane.showMessageDialog(FramePrincipalControl.getInstance().getFramePrincipal(), 
							"\tESTE INCIDENTE SE REPORTARA\nSi solo necesitaba checar precio, NO lo agregue\nUtilice esta funcion de checar.", "Venta", JOptionPane.YES_NO_OPTION);
				}
			}
		}.start();
	}

	void verVentaActual() {
		panelVenta.getCodigoBuscar().requestFocus();
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
			logger.debug("imprimirTicket:esesdLAstForPrint.getEs().getNt()="+esesdLAstForPrint.getEs().getNt()+", ImporteREcibido="+esesdLAstForPrint.getEs().getIr()+", MP:"+esesdLAstForPrint.getEs().getMp());
			
			JarpeReportsInfoDTO infoDTOTicket = VentaSesion.generaJarpeReportsInfoDTOTicket(esesdLAstForPrint);
			logger.trace("imprimirTicket:fileTicket="+infoDTOTicket);			
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

	void ventaLinea() {
		panelVenta.getDesdeLinea().setSelected(true);
		panelVenta.getCodigoBuscar().requestFocus();
	}

	void ventaOportunidad() {
		panelVenta.getDesdeOportunidad().setSelected(true);
		panelVenta.getCodigoBuscar().requestFocus();
	}

	void ventaRegalias() {
		panelVenta.getDesdeRegalias().setSelected(true);
		panelVenta.getCodigoBuscar().requestFocus();
	}
}
