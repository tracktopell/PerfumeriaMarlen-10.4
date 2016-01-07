package com.pmarlen.caja.control;

import com.pmarlen.backend.model.Almacen;
import com.pmarlen.backend.model.quickviews.InventarioSucursalQuickView;
import com.pmarlen.businesslogic.LogicaFinaciera;
import com.pmarlen.businesslogic.TotalesCalculados;
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
import java.util.ArrayList;
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
		StringBuilder sbNoEncontrados=new StringBuilder();
		I productoEncontrado = null;
		I productoEncontradoSinExistencia = null;
		
		logger.debug("\tcodigoBuscar_ActionPerformed: panelVenta.getTipoAlmacen="+panelVenta.getTipoAlmacen());
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
					logger.trace("\t\t\tcodigoBuscar_ActionPerformed:producto encontrado:"+productoEncontrado);
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
				if(!estadoChecando){
					ESD esd = new ESD();
					esd.setTa(tipoAlmacenSeleccionado);					
					esd.setA(tipoAlmacen.get(tipoAlmacenSeleccionado).getId());
					esd.setC(1);
					boolean existencia=false;
					
					if(		productoEncontrado.getA1c() <= 0 ||
							productoEncontrado.getaRc() <= 0 ||
							productoEncontrado.getaOc() <= 0    ) {
						throw new IllegalStateException("NO HAY CANTIDAD SUFICIENTE EN ALMACEN PARA ="+productoEncontrado.getCb());
					}
					
					
					if(tipoAlmacenSeleccionado == Constants.ALMACEN_PRINCIPAL && productoEncontrado.getA1c() > 0) {
						esd.setP(productoEncontrado.getA1p());
						existencia = true;
					} else if(tipoAlmacenSeleccionado == Constants.ALMACEN_REGALIAS ) {
						esd.setP(productoEncontrado.getaRp());
						existencia = true;
					} else if(tipoAlmacenSeleccionado == Constants.ALMACEN_OPORTUNIDAD ) {
						esd.setP(productoEncontrado.getaOp());
						existencia = true;
					} else {
						throw new IllegalStateException("NO HAY TIPO ALMACEN SELECCIONADO: tipoAlmacenSeleccionado="+tipoAlmacenSeleccionado);
					}
					
					if(existencia) {
						esd.setCb(productoEncontrado.getCb());
						PedidoVentaDetalleTableItem detalleVentaTableItemNuevo = new PedidoVentaDetalleTableItem(productoEncontrado.reverse(), esd, tipoAlmacenSeleccionado);
						logger.debug("\t\t=> + "+esd.getC()+" x "+esd.getCb()+"("+tipoAlmacenSeleccionado+") ["+esd.getA()+"]");
						ApplicationLogic.getInstance().getVentaSesion().getDetalleVentaTableItemList().add(detalleVentaTableItemNuevo);
						contAgregados++;
					} else {
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
							panelVenta.getCodigoBuscar().setBackground(Color.red);
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
			logger.debug("terminar_ActionPerformed(): Generando DTO Venta");
			esesdLAstForPrint = ApplicationLogic.getInstance().getVentaSesion().getVenta();
			
			//TotalesCalculados ct = LogicaFinaciera.calculaTotales(esesdLAstForPrint.getEs(), esesdLAstForPrint.getEsdList(), true, 0.0);
			//logger.info("terminar_ActionPerformed:-->>LogicaFinaciera.calculaTotales: TotalesCalculados:\n"+ct);
			
			logger.debug("terminar_ActionPerformed(): TICKET:"+esesdLAstForPrint.getEs().getNt());
			
			if (ApplicationLogic.getInstance().isPrintingEnabled()) {
				new Thread() {
					@Override
					public void run() {
						logger.debug("terminar_ActionPerformed(): despues de cerrar dialogo:");
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
	
	
	void cancelar_ActionPerformed() {
		logger.info("[USER]->cancelar_ActionPerformed()");
		int r = JOptionPane.showConfirmDialog(FramePrincipalControl.getInstance().getFramePrincipal(), "¿Cancelar la Venta Actual?", "Venta", JOptionPane.YES_NO_OPTION);
		if (r == JOptionPane.YES_OPTION) {
			estadoInicial();
			panelVenta.getCodigoBuscar().requestFocus();
		}
	}
	
	void checar_ActionPerformed(){
		estadoChecando = ! estadoChecando;
		actualizarEstadoChecado();
		panelVenta.getCodigoBuscar().requestFocus();
	}

	private void actualizarEstadoChecado() {
		logger.info("[USER]->actualizarEstadoChecado="+estadoChecando);
		if(estadoChecando) {
			panelVenta.getChechar().setText("AGREGAR [ F9 ]");			
			panelVenta.getCodigoBuscar().setBackground(Color.YELLOW);
		} else {
			panelVenta.getChechar().setText("CHECAR [ F9 ]");
			panelVenta.getCodigoBuscar().setBackground(Color.WHITE);
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
		panelVenta.getDescuento() .setText(Constants.df2Decimal.format(ApplicationLogic.getInstance().getVentaSesion().getDescuentoCalculado()));
		panelVenta.getDescuento() .setToolTipText(Constants.df2Decimal.format(ApplicationLogic.getInstance().getVentaSesion().getTotalBrutoDescontable())+" * "+
				Constants.df2Decimal.format(ApplicationLogic.getInstance().getVentaSesion().getDescuentoFactor()));
		panelVenta.getTotal()     .setText(Constants.df2Decimal.format(ApplicationLogic.getInstance().getVentaSesion().getTotal()));
		logger.debug("renderTotal:subTotal = "+stTT);
		logger.debug("renderTotal:descuento= "+ApplicationLogic.getInstance().getVentaSesion().getDescuentoCalculado());
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
				ApplicationLogic.getInstance().getVentaSesion().getDetalleVentaTableItemList().remove(selectedRow);
				panelVenta.getDetalleVentaJTable().updateUI();
				renderTotal();
			}
		}
		panelVenta.getCodigoBuscar().requestFocus();

	}

	void ventaeliminarProdMenu() {
		int selectedRow = panelVenta.getDetalleVentaJTable().getSelectedRow();
		if (selectedRow >= 0) {
			ApplicationLogic.getInstance().getVentaSesion().getDetalleVentaTableItemList().remove(selectedRow);
			panelVenta.getDetalleVentaJTable().updateUI();
			renderTotal();

			panelVenta.getDetalleVentaJTable().getSelectionModel().clearSelection();
			panelVenta.getCodigoBuscar().requestFocus();
		}
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
			//logger.debug("->ApplicationLogic.getInstance().getVentaSesion().getDetalleVentaTableItemList()="+ApplicationLogic.getInstance().getVentaSesion().getDetalleVentaTableItemList());			
			//logger.debug("->ticketPrinteService:esesd.getEs()="+esesd.getEs());
			//logger.debug("->ticketPrinteService:esesd.getEsdList()="+esesd.getEsdList());
			if(Main.dinamicTrace){
				TextReporter.DEBUG =true;
			} else {
				TextReporter.DEBUG =false;
			}
			logger.debug("ticketPrinteService:esesdLAstForPrint.getEs().getNt()="+esesdLAstForPrint.getEs().getNt());
			final JarpeReportsInfoDTO infoDTOTicket = VentaSesion.generaJarpeReportsInfoDTOTicket(esesdLAstForPrint);
			logger.debug("ticketPrinteService:fileTicket="+infoDTOTicket);			
			String fileTicket = TextReporter.generateTicketTXT(infoDTOTicket);			
			logger.debug("ticketPrinteService:fileTicket="+fileTicket);
			
			logger.debug("imprimirTicket:ticketFileName:"+fileTicket);
		
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
