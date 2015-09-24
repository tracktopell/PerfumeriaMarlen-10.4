/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.caja.control;

import com.pmarlen.backend.model.EntradaSalida;
import com.pmarlen.backend.model.EntradaSalidaDetalle;
import com.pmarlen.caja.model.ImporteCellRender;
import com.pmarlen.caja.dao.MemoryDAO;
import com.pmarlen.caja.model.PedidoVentaDetalleTableItem;
import com.pmarlen.caja.model.PedidoVentaDetalleTableModel;
import com.pmarlen.caja.view.PanelVenta;
import com.pmarlen.caja.view.ProductoCellRender;
import com.pmarlen.model.Constants;
import com.pmarlen.rest.dto.P;
import com.pmarlen.ticket.TicketPrinteService;
import com.pmarlen.ticket.bluetooth.TicketBlueToothPrinter;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
		ticketPrinteService = TicketBlueToothPrinter.getInstance();
		ticketPrinteService.setApplicationLogic(ApplicationLogic.getInstance());
	}

	public void estadoInicial() {
		if (detalleVentaTableItemList.size() > 0) {
			detalleVentaTableItemList.clear();
		}
		panelVenta.getDetalleVentaJTable().updateUI();
		panelVenta.resetInfoForProducto(null);
		estadoChecando = false;
		actualizarEstadoChecado();
		renderTotal();
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
	
	//private P productoBuscar = new P();

	private void codigoBuscar_ActionPerformed() {
		String codigoBuscar = panelVenta.getCodigoBuscar().getText().trim();
		
		logger.info("[USER]->codigoBuscar_ActionPerformed:codigoBuscar=->" + codigoBuscar+"<-");
		P productoEncontrado = null;
		try{
			productoEncontrado = MemoryDAO.fastSearchProducto(codigoBuscar);
		}catch(Exception e){
		
		}
		logger.debug("=>codigoBuscar_ActionPerformed:productoEncontrado=" + productoEncontrado+", oferta?");

		if (productoEncontrado != null) {
			panelVenta.resetInfoForProducto(productoEncontrado);
			
			if(!estadoChecando){
				EntradaSalidaDetalle pvd = new EntradaSalidaDetalle();

				pvd.setAlmacenId(ApplicationLogic.getInstance().getAlmacen().getId());
				pvd.setCantidad(1);
				int ta=ApplicationLogic.getInstance().getAlmacen().getTipoAlmacen();
				if(ta == Constants.ALMACEN_PRINCIPAL) {
					pvd.setPrecioVenta(productoEncontrado.getA1p());
				} else if(ta == Constants.ALMACEN_REGALIAS) {
					pvd.setPrecioVenta(productoEncontrado.getaRp());
				} else if(ta == Constants.ALMACEN_OPORTUNIDAD) {
					pvd.setPrecioVenta(productoEncontrado.getaOp());
				} else {

				}
				pvd.setProductoCodigoBarras(productoEncontrado.getCb());
				PedidoVentaDetalleTableItem detalleVentaTableItemNuevo = new PedidoVentaDetalleTableItem(productoEncontrado, pvd, ta);
				int idx = 0;

				idx = detalleVentaTableItemList.size();
				detalleVentaTableItemList.add(detalleVentaTableItemNuevo);

				panelVenta.getCodigoBuscar().setText("");
				panelVenta.getDetalleVentaJTable().getSelectionModel().setSelectionInterval(idx, idx);
				panelVenta.getDetalleVentaJTable().updateUI();
				renderTotal();
			} else {
				panelVenta.getCodigoBuscar().setText("");			
			}
		} else {
			new Thread() {
				@Override
				public void run() {
					Color pc = panelVenta.getCodigoBuscar().getBackground();
					panelVenta.getCodigoBuscar().setBackground(Color.red);
					try {
						for (int i = 0; i < 5; i++) {
							panelVenta.getCodigoBuscar().setBackground(Color.red);
							Thread.sleep(350);
							panelVenta.getCodigoBuscar().setBackground(pc);
							Thread.sleep(50);
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
		logger.info("=>terminar_ActionPerformed()");
		if (detalleVentaTableItemList.size() == 0) {
			JOptionPane.showMessageDialog(FramePrincipalControl.getInstance().getFramePrincipal(),
					"Cuando termine de agregar más productos, podra terminar esta venta", "Terminar Venta",
					JOptionPane.WARNING_MESSAGE);

			panelVenta.getCodigoBuscar().requestFocus();

			return;
		}

		try {
			final EntradaSalida venta = new EntradaSalida();
			final ArrayList<EntradaSalidaDetalle> detalleVentaList = new ArrayList<EntradaSalidaDetalle>();

			for (PedidoVentaDetalleTableItem dvil : detalleVentaTableItemList) {
				//detalleVentaList.add(new EntradaSalidaDetalle(0, 0, dvil.getCodigo(), dvil.getCantidad(), dvil.getPrecioVenta()));
				EntradaSalidaDetalle pvd= new EntradaSalidaDetalle();
				pvd.setCantidad(dvil.getCantidad());
				pvd.setProductoCodigoBarras(dvil.getCodigo());
				detalleVentaList.add(pvd);
			}

			//pedidoVentaDAO.insert(venta,detalleVentaList);

			JOptionPane.showMessageDialog(FramePrincipalControl.getInstance().getFramePrincipal(), "Se guardo Correctamente, ...Imprimiendo ticket", "Venta", JOptionPane.INFORMATION_MESSAGE);
			if (ApplicationLogic.getInstance().isPrintingEnabled()) {
				new Thread() {
					@Override
					public void run() {
						imprimirTicket(venta, detalleVentaList);
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

	private void cancelar_ActionPerformed() {
		logger.info("=>cancelar_ActionPerformed()");
		int r = JOptionPane.showConfirmDialog(FramePrincipalControl.getInstance().getFramePrincipal(), "¿Cancelar la Venta Actual?", "Venta", JOptionPane.YES_NO_OPTION);
		if (r == JOptionPane.YES_OPTION) {
			estadoInicial();
			panelVenta.getCodigoBuscar().requestFocus();
		}
	}
	
	private void checar_ActionPerformed(){
		estadoChecando = ! estadoChecando;
		actualizarEstadoChecado();
	}

	private void actualizarEstadoChecado() {
		if(estadoChecando) {
			panelVenta.getChechar().setText("AGREGAR [ F3 ]");
			panelVenta.getCodigoBuscar().setForeground(Color.ORANGE);
		} else {
			panelVenta.getChechar().setText("CHECAR [ F3 ]");
			panelVenta.getCodigoBuscar().setForeground(Color.BLUE);
		}
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		panelVenta.getDetalleVentaJTable().updateUI();
		renderTotal();
	}

	private void renderTotal() {
		double total = 0.0;
		if (detalleVentaTableItemList.size() > 0) {
			FramePrincipalControl.getInstance().setEnabledVentasMenus(true);

			this.panelVenta.getTerminar().setEnabled(true);
			this.panelVenta.getCancelar().setEnabled(true);
		} else {
			FramePrincipalControl.getInstance().setEnabledVentasMenus(false);
			this.panelVenta.getTerminar().setEnabled(false);
			this.panelVenta.getCancelar().setEnabled(false);
		}

		for (PedidoVentaDetalleTableItem dvti : detalleVentaTableItemList) {
			total += dvti.getImporete();
		}
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
			PedidoVentaDetalleTableItem prod = detalleVentaTableItemList.get(sr);
			panelVenta.resetInfoForProducto(prod.getProducto());
		} else {
			panelVenta.resetInfoForProducto(null);
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
		int cantidad = dvti.getCantidad();		
		EntradaSalidaDetalle pvd = dvti.getPvd();		
		P p = dvti.getProducto();
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
				
				detalleVentaTableItemList.get(selectedRow).setCantidad(nuevaCantidad);
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

	private void imprimirTicket(EntradaSalida venta, List<EntradaSalidaDetalle> detalleVentaList) {
		HashMap<String, String> extraInformation = new HashMap<String, String>();

		extraInformation.put("recibimos", "100000.45");
		extraInformation.put("cambio", "2323.00");

		boolean printed = false;
		try {
			Object ticketFile = ticketPrinteService.generateTicket(venta, (ArrayList<EntradaSalidaDetalle>) detalleVentaList, extraInformation);
			ticketPrinteService.sendToPrinter(ticketFile);
			printed = true;
		} catch (IOException ioe) {
			//ioe.printStackTrace(System.err);
			JOptionPane.showMessageDialog(FramePrincipalControl.getInstance().getFramePrincipal(), "Error al imprimir Ticket", "Imprimir Ticket", JOptionPane.ERROR_MESSAGE);
		} finally {
			logger.debug("------------->>> DESPUES DE IMPRIMIR TICKET");
			if (!printed) {
				logger.debug("------------->>> ERROR AL IMPRIMIR");
				//t.printStackTrace(System.err);
				JOptionPane.showMessageDialog(FramePrincipalControl.getInstance().getFramePrincipal(), "Error grave al imprimir Ticket", "Imprimir Ticket", JOptionPane.ERROR_MESSAGE);
			}
			panelVenta.getCodigoBuscar().requestFocus();
		}
	}
}
