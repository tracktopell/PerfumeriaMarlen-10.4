package com.pmarlen.caja.control;

import com.google.gson.Gson;
import com.pmarlen.backend.model.EntradaSalidaDetalle;
import com.pmarlen.backend.model.Producto;
import com.pmarlen.backend.model.quickviews.InventarioSucursalQuickView;
import com.pmarlen.businesslogic.GeneradorNumTicket;
import com.pmarlen.businesslogic.reports.TextReporter;
import com.pmarlen.caja.dao.ESFileSystemJsonDAO;
import com.pmarlen.caja.dao.MemoryDAO;
import com.pmarlen.caja.model.ImporteCellRender;
import com.pmarlen.caja.model.PedidoVentaDetalleTableItem;
import com.pmarlen.caja.model.PedidoVentaDetalleTableModel;
import com.pmarlen.caja.view.PanelVenta;
import com.pmarlen.caja.view.ProductoCellRender;
import com.pmarlen.caja.view.TerminarVentaDlg;
import com.pmarlen.model.Constants;
import com.pmarlen.model.OSValidator;
import com.pmarlen.rest.dto.ES;
import com.pmarlen.rest.dto.ESD;
import com.pmarlen.rest.dto.ES_ESD;
import com.pmarlen.rest.dto.I;
import com.pmarlen.ticket.TicketPrinteService;
import com.pmarlen.ticket.bluetooth.TicketBlueToothPrinter;
import com.pmarlen.ticket.systemprinter.SendFileToSystemPrinter;
import com.pmarlen.ticket.systemprinter.TicketSystemPrinter;
import com.pmarlen.ticket.systemprinter.UnixSendToLP;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
public class PanelVentaControl implements ActionListener, TableModelListener, MouseListener {
	private static Logger logger = Logger.getLogger(PanelVentaControl.class.getName());
	private PanelVenta panelVenta;
	private ArrayList<PedidoVentaDetalleTableItem> detalleVentaTableItemList;
	private DecimalFormat df;
	private TicketPrinteService ticketPrinteService;
	private boolean estadoChecando = false;
	private ES_ESD venta;
	
	public PanelVentaControl(PanelVenta panelVenta) {
		this.panelVenta = panelVenta;
		PedidoVentaDetalleTableModel x = (PedidoVentaDetalleTableModel) this.panelVenta.getDetalleVentaJTable().getModel();
		x.addTableModelListener(this);
		logger.debug("->>table columns=" + panelVenta.getDetalleVentaJTable().getColumnCount());

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

		detalleVentaTableItemList = (x).getDetalleVentaTableItemList();

		this.panelVenta.getCodigoBuscar().addActionListener(this);
		this.panelVenta.getTerminar().addActionListener(this);
		this.panelVenta.getCancelar().addActionListener(this);
		this.panelVenta.getChechar().addActionListener(this);

		df = new DecimalFormat("$ ###,###,##0.00");
		
		ticketPrinteService = TicketSystemPrinter.getInstance();
		ticketPrinteService.setApplicationLogic(ApplicationLogic.getInstance());
	}

	public void estadoInicial() {
		if (detalleVentaTableItemList.size() > 0) {
			detalleVentaTableItemList.clear();
		}
		panelVenta.getDetalleVentaJTable().updateUI();
		panelVenta.resetInfoForProducto(null,0);
		estadoChecando = false;		
		actualizarEstadoChecado();
		renderTotal();
		this.panelVenta.getCodigoBuscar().requestFocus();
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
		for(String lineCB: linesCBArr){
			logger.debug("\tcodigoBuscar_ActionPerformed:line->"+lineCB+"<-");
			String[] codesCB = lineCB.split("\\s");
			for(String cdLineCB: codesCB){
				logger.debug("\t\tcodigoBuscar_ActionPerformed:code->"+cdLineCB+"<-");
				productoEncontrado = MemoryDAO.fastSearchProducto(cdLineCB);
				if(productoEncontrado == null){
					logger.debug("\t\t\tcodigoBuscar_ActionPerformed:producto no encontrado:"+cdLineCB);
					sbNoEncontrados.append(cdLineCB).append(" ");
				} else {
					logger.debug("\t\t\tcodigoBuscar_ActionPerformed:producto encontrado:"+productoEncontrado);
					encontrados.add(productoEncontrado);
				}
			}
		}
		
		if(encontrados.size() > 0) {
			productoEncontrado =  null;
			for(I i:encontrados){
				productoEncontrado = i;
				
				if(!estadoChecando){
					int ta=ApplicationLogic.getInstance().getAlmacen().getTipoAlmacen();
					ESD esd = new ESD();

					esd.setA(ApplicationLogic.getInstance().getAlmacen().getId());
					esd.setC(ta);				
					if(ta == Constants.ALMACEN_PRINCIPAL) {
						esd.setP(productoEncontrado.getA1p());
					} else if(ta == Constants.ALMACEN_REGALIAS) {
						esd.setP(productoEncontrado.getaRp());
					} else if(ta == Constants.ALMACEN_OPORTUNIDAD) {
						esd.setP(productoEncontrado.getaOp());
					} else {

					}
					esd.setCb(productoEncontrado.getCb());
					PedidoVentaDetalleTableItem detalleVentaTableItemNuevo = new PedidoVentaDetalleTableItem(productoEncontrado.reverse(), esd, ta);
					int idx = 0;

					idx = detalleVentaTableItemList.size();
					detalleVentaTableItemList.add(detalleVentaTableItemNuevo);
	
					panelVenta.getDetalleVentaJTable().getSelectionModel().setSelectionInterval(idx, idx);
					panelVenta.getDetalleVentaJTable().updateUI();
					renderTotal();
				}
			}
			panelVenta.getCodigoBuscar().setText("");
			panelVenta.resetInfoForProducto(productoEncontrado.reverse(),1);
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
		
	}

	private void terminar_ActionPerformed() {
		logger.info("terminar_ActionPerformed()");
		if (detalleVentaTableItemList.size() == 0) {
			JOptionPane.showMessageDialog(FramePrincipalControl.getInstance().getFramePrincipal(),
					"Cuando termine de agregar más productos, podra terminar esta venta", "Terminar Venta",
					JOptionPane.WARNING_MESSAGE);

			panelVenta.getCodigoBuscar().requestFocus();
			
			return;
		}

		try {
			TerminarVentaDlg tvDlg = new TerminarVentaDlg(FramePrincipalControl.getInstance().getFramePrincipal(), true);
			TerminarVentaControl tvdControl = new TerminarVentaControl(tvDlg, total, 0.0, total,detalleVentaTableItemList);

			tvdControl.estadoInicial();
			
			if(!tvdControl.isCierreCorrecto()){
				return;
			}
			logger.info("terminar_ActionPerformed(): IMPRIMIENDO ...");
			venta = tvdControl.venta;
			
			if (ApplicationLogic.getInstance().isPrintingEnabled()) {
				new Thread() {
					@Override
					public void run() {
						logger.info("terminar_ActionPerformed(): despues de cerrar dialogo:");
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
		logger.info("=>cancelar_ActionPerformed()");
		int r = JOptionPane.showConfirmDialog(FramePrincipalControl.getInstance().getFramePrincipal(), "¿Cancelar la Venta Actual?", "Venta", JOptionPane.YES_NO_OPTION);
		if (r == JOptionPane.YES_OPTION) {
			estadoInicial();
			panelVenta.getCodigoBuscar().requestFocus();
		}
	}
	
	void checar_ActionPerformed(){
		estadoChecando = ! estadoChecando;
		actualizarEstadoChecado();
	}

	private void actualizarEstadoChecado() {
		if(estadoChecando) {
			panelVenta.getChechar().setText("AGREGAR [ F3 ]");			
			panelVenta.getCodigoBuscar().setBackground(Color.YELLOW);
		} else {
			panelVenta.getChechar().setText("CHECAR [ F3 ]");
			panelVenta.getCodigoBuscar().setBackground(Color.WHITE);
		}
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		panelVenta.getDetalleVentaJTable().updateUI();
		renderTotal();
	}
	
	double total = 0.0;
	private void renderTotal() {
		total = 0.0;
		
		if (detalleVentaTableItemList.size() > 0) {
			FramePrincipalControl.getInstance().setEnabledVentasMenus(true);

			this.panelVenta.getTerminar().setEnabled(true);
			this.panelVenta.getCancelar().setEnabled(true);
		} else {
			FramePrincipalControl.getInstance().setEnabledVentasMenus(false);
			this.panelVenta.getTerminar().setEnabled(false);
			this.panelVenta.getCancelar().setEnabled(false);
		}
		int np=0;
		for (PedidoVentaDetalleTableItem dvti : detalleVentaTableItemList) {
			np += dvti.getPvd().getC();
			total += dvti.getI();
		}
		panelVenta.getNumArt().setText(String.valueOf(np));
		panelVenta.getTotal().setText(df.format(total));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == panelVenta.getDetalleVentaJTable()) {
			//logger.debug("->>mouseClicked:"+e.getClickCount()+" "+panelVenta.getDetalleVentaJTable().getSelectedRow()+","+panelVenta.getDetalleVentaJTable().getSelectedColumn());
			if (e.getClickCount() == 2 && panelVenta.getDetalleVentaJTable().getSelectedColumn() == 0) {
				editarCantidad(panelVenta.getDetalleVentaJTable().getSelectedRow());
			}
		}
	}

	private void updateSelectedRow() {
		int sr = panelVenta.getDetalleVentaJTable().getSelectedRow();
		logger.debug("=>Selected:sr=" + sr);
		if(sr != -1){
			PedidoVentaDetalleTableItem pvdti = detalleVentaTableItemList.get(sr);
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
		final PedidoVentaDetalleTableItem dvti = detalleVentaTableItemList.get(selectedRow);
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
				detalleVentaTableItemList.remove(selectedRow);
				panelVenta.getDetalleVentaJTable().updateUI();
				renderTotal();
			}
		}
		panelVenta.getCodigoBuscar().requestFocus();

	}

	void ventaeliminarProdMenu() {
		int selectedRow = panelVenta.getDetalleVentaJTable().getSelectedRow();
		if (selectedRow >= 0) {
			detalleVentaTableItemList.remove(selectedRow);
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
		HashMap<String, String> extraInformation = new HashMap<String, String>();
		logger.debug("imprimirTicket: venta.getEs().getIr()="+venta.getEs().getIr());
		
		extraInformation.put("recibimos", Constants.dfCurrency.format(venta.getEs().getIr()));
		extraInformation.put("cambio"   , Constants.dfCurrency.format(total - venta.getEs().getIr()));

		boolean printed = false;
		try {
			ArrayList<EntradaSalidaDetalle> pvdList = new ArrayList<EntradaSalidaDetalle>();
			for(ESD esd:venta.getEsdList()){
				pvdList.add(esd.reverse());
			}
			
			logger.debug("->imprimirTicket:ticketPrinteService.generateTicket:venta.getEs().reverse()="+venta.getEs().reverse());
			logger.debug("->imprimirTicket:ticketPrinteService.generateTicket:pvdList="+pvdList);
			logger.debug("->imprimirTicket:ticketPrinteService.generateTicket:extraInformation="+extraInformation);
			
			TextReporter.setColumns(MemoryDAO.getTextSystemPrinterColumns());
			
			String fileTicket = TextReporter.generateTicket(venta.getEs().reverse(), pvdList, extraInformation);
			
			logger.debug("->ticketPrinteService:fileTicket="+fileTicket);
			
			//SendFileToSystemPrinter.printFile(fileTicket);
			logger.info("imprimirTicket:ticketFileName:"+fileTicket);
		
			logger.info("imprimirTicket:OSValidator.isUnix()?:"+OSValidator.isUnix());
			logger.info("imprimirTicket:OSValidator.isMac():"+OSValidator.isMac());

			if(OSValidator.isUnix() || OSValidator.isMac()){
				logger.info("imprimirTicket: calling UnixSendToLP.printFile");
				UnixSendToLP.printFile((String)fileTicket);
			}else{
				logger.info("imprimirTicket: calling SendFileToSystemPrinter.printFile");
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
			venta = null;
		}
	}
}
