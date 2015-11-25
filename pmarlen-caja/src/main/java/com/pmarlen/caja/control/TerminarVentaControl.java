package com.pmarlen.caja.control;

import com.pmarlen.backend.model.Cliente;
import com.pmarlen.backend.model.FormaDePago;
import com.pmarlen.backend.model.MetodoDePago;
import com.pmarlen.businesslogic.GeneradorNumTicket;
import com.pmarlen.caja.dao.ESFileSystemJsonDAO;
import com.pmarlen.caja.dao.MemoryDAO;
import com.pmarlen.caja.model.PedidoVentaDetalleTableItem;
import com.pmarlen.caja.view.CierreCajaJFrame;
import com.pmarlen.caja.view.TerminarVentaDlg;
import com.pmarlen.model.Constants;
import com.pmarlen.model.GeneradorDeToken;
import com.pmarlen.rest.dto.ESD;
import com.pmarlen.rest.dto.ES_ESD;
import com.pmarlen.rest.dto.U;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;
import org.apache.log4j.Logger;

/**
 *
 * @author tracktopell
 */
public class TerminarVentaControl implements ActionListener, ItemListener, FocusListener, ValidadorDeCampos {

	private static Logger logger = Logger.getLogger(TerminarVentaControl.class.getName());
	private TerminarVentaDlg terminarVentaDlg;
	private double diferencia;
	private boolean cierreCorrecto;
	private double subTotal;
	private double descuento;
	private double total;
	private double totalRedondeado;
	private double recibido;
	private double cargo;
	private String autorizacion;
	ArrayList<PedidoVentaDetalleTableItem> pvd;
	ES_ESD venta;

	public TerminarVentaControl(TerminarVentaDlg terminarVentaDlg, double subTotal, double descuento, double total, ArrayList<PedidoVentaDetalleTableItem> pvd) {
		this.terminarVentaDlg = terminarVentaDlg;
		this.subTotal = subTotal;
		this.descuento = descuento;
		this.total = total;
		this.pvd = pvd;

		this.terminarVentaDlg.getCliente().setModel(getClientes());
		this.terminarVentaDlg.getMetodoDePago().setModel(getMetodosDePago());
		this.terminarVentaDlg.getMetodoDePago().addItemListener(this);

		this.terminarVentaDlg.getRecibido().addActionListener(this);
		this.terminarVentaDlg.getCargo().addActionListener(this);
		this.terminarVentaDlg.getAutorizacion().addActionListener(this);

		//this.terminarVentaDlg.getRecibido().addFocusListener(this);
		//this.terminarVentaDlg.getCargo().addFocusListener(this);
		//this.terminarVentaDlg.getAutorizacion().addFocusListener(this);

		this.terminarVentaDlg.getAceptar().addActionListener(this);
		this.terminarVentaDlg.getCancelar().addActionListener(this);
	}

	public TerminarVentaDlg getCierreCajaJFrame() {
		return terminarVentaDlg;
	}

	public void estadoInicial() {
		this.terminarVentaDlg.getSubtotal().setText(Constants.dfCurrency.format(subTotal));
		this.terminarVentaDlg.getDescuento().setText(Constants.dfCurrency.format(descuento));
		this.terminarVentaDlg.getTotal().setText(Constants.dfCurrency.format(total));
		try{
			totalRedondeado = Constants.dfCurrency.parse(Constants.dfCurrency.format(total)).doubleValue();
		}catch(ParseException pe){
			totalRedondeado = total;
		}
		this.terminarVentaDlg.getSubtotal().setText(Constants.dfCurrency.format(subTotal));

		this.terminarVentaDlg.getCliente().setSelectedIndex(0);
		this.terminarVentaDlg.getMetodoDePago().setSelectedIndex(0);

		this.terminarVentaDlg.getRecibido().setEnabled(true);
		this.terminarVentaDlg.getCambio().setEnabled(true);
		this.terminarVentaDlg.getCargo().setEnabled(false);
		this.terminarVentaDlg.getAutorizacion().setEnabled(false);

		new Thread() {
			public void run() {
				try {
					Thread.sleep(2000);
					terminarVentaDlg.getRecibido().requestFocus();
				} catch (InterruptedException ie) {
				}
			}
		}.start();
		terminarVentaDlg.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == terminarVentaDlg.getRecibido()) {
			recibido_ActionPerformed();
		} else if (e.getSource() == terminarVentaDlg.getCargo()) {
			cargo_ActionPerformed();
		} else if (e.getSource() == terminarVentaDlg.getAutorizacion()) {
			autorizacion_ActionPerformed();
		} else if (e.getSource() == terminarVentaDlg.getAceptar()) {
			aceptar_ActionPerformed();
		} else if (e.getSource() == terminarVentaDlg.getCancelar()) {
			cancelar_ActionPerformed();
		}
	}

	private void aceptar_ActionPerformed() {
		logger.info("[USER]->aceptar_ActionPerformed()");
		if (validateAll()) {
			registrarVenta();
			terminarVentaDlg.dispose();
			//terminarVentaDlg = null;
		}
	}

	private boolean validateAll() {
		JComponent componentWithError;
		try {
			validate();
		} catch (ValidacionCamposException ve) {
			componentWithError = ve.getSource();
			if (componentWithError != null) {
				javax.swing.UIManager.put("OptionPane.font", new FontUIResource(new java.awt.Font("Tahoma", 0, 24)));
				javax.swing.UIManager.put("JOptionPane.font", new FontUIResource(new java.awt.Font("Tahoma", 0, 24)));
				JOptionPane.showMessageDialog(terminarVentaDlg, ve.getMessage(), "ACEPTAR", JOptionPane.ERROR_MESSAGE);
				if (componentWithError instanceof JTextField) {
					((JTextField) componentWithError).setText("");
				}
				//componentWithError.requestFocus();
				return false;
			}
		}
		return true;
	}

	private void cancelar_ActionPerformed() {
		logger.info("[USER]->cancelar_ActionPerformed()");
		terminarVentaDlg.dispose();
		terminarVentaDlg = null;
	}

	private void registrarVenta() {

		final ArrayList<ESD> detalleVentaList = new ArrayList<ESD>();

		for (PedidoVentaDetalleTableItem dvil : pvd) {
			detalleVentaList.add(dvil.getPvd());
		}
		int cteId = 1;
		int formaPagoId = 1;
		String numTicket = GeneradorNumTicket.getNumTicket(MemoryDAO.getSucursalId(), MemoryDAO.getNumCaja(), cteId, total);
		venta = new ES_ESD();

		venta.getEs().setTm(Constants.TIPO_MOV_SALIDA_ALMACEN_VENTA);
		venta.getEs().setJ(MemoryDAO.getNumCaja());
		venta.getEs().setC(getClienteId());
		venta.getEs().setFc(System.currentTimeMillis());
		venta.getEs().setFp(formaPagoId);
		venta.getEs().setIr(getRecibido());
		venta.getEs().setMp(getMetodoDePagoId());
		venta.getEs().setAmc(getAutorizacion());
		venta.getEs().setI(Constants.IVA);
		venta.getEs().setS(MemoryDAO.getSucursalId());
		venta.getEs().setNt(numTicket);
		venta.getEsdList().addAll(detalleVentaList);
		venta.getEs().setU(ApplicationLogic.getInstance().getLogged().getE());

		logger.debug("terminar_ActionPerformed:before commit, venta=" + venta);

		ESFileSystemJsonDAO.commit(venta);

		//JOptionPane.showMessageDialog(FramePrincipalControl.getInstance().getFramePrincipal(), "Se guardo Correctamente, ...Imprimiendo ticket", "Venta", JOptionPane.INFORMATION_MESSAGE);
		cierreCorrecto = true;
	}

	@Override
	public void focusGained(FocusEvent fe) {

	}

	@Override
	public void focusLost(FocusEvent fe) {
		if (fe.getSource() == terminarVentaDlg.getRecibido()) {
			recibido_ActionPerformed();
		} else if (fe.getSource() == terminarVentaDlg.getCargo()) {
			cargo_ActionPerformed();
		} else if (fe.getSource() == terminarVentaDlg.getAutorizacion()) {
			autorizacion_ActionPerformed();
		}
	}

	public void setFontBigest() {
		terminarVentaDlg.setFont(new java.awt.Font("Tahoma", 0, 24));
	}

	@Override
	public void validate() throws ValidacionCamposException {
		MetodoDePago mdpSelected = (MetodoDePago) terminarVentaDlg.getMetodoDePago().getSelectedItem();
		String recibidoText = terminarVentaDlg.getRecibido().getText().replace("$", "").replace(",", "").trim();
		String cargoText = terminarVentaDlg.getCargo().getText().replace("$", "").replace(",", "").trim();
		autorizacion = terminarVentaDlg.getAutorizacion().getText().trim();
		if (mdpSelected.getId() == Constants.ID_MDP_EFECTIVO) {
			try {
				recibido = Double.parseDouble(recibidoText);
			} catch (NumberFormatException nfe) {
				this.terminarVentaDlg.getRecibido().setText("");
				this.terminarVentaDlg.getCambio().setText("");
				throw new ValidacionCamposException("EL IMORTE RECIBIDO DEBE SER UN IMPORTE DE MONEDA", terminarVentaDlg.getRecibido());
			}

			if (recibido < 0 || recibido > 100000) {
				this.terminarVentaDlg.getRecibido().setText("");
				this.terminarVentaDlg.getCambio().setText("");
				throw new ValidacionCamposException("EL IMORTE RECIBIDO NO PARECE SER UN IMPORTE RAZONABLE: DEBE SER $0.01 A $99,999.99", terminarVentaDlg.getRecibido());
			}

			double difReal = recibido - total;

			if (difReal < 0) {
				this.terminarVentaDlg.getRecibido().setText("");
				this.terminarVentaDlg.getCambio().setText("");
				throw new ValidacionCamposException("EL IMORTE RECIBIDO DEBE SER >= AL TOTAL", terminarVentaDlg.getRecibido());
			}

			this.terminarVentaDlg.getCambio().setText(Constants.dfCurrency.format(difReal));

		} else if (mdpSelected.getId() == Constants.ID_MDP_TARJETA) {
			/*
			try {
				cargo = Double.parseDouble(cargoText);
			} catch (NumberFormatException nfe) {
				this.terminarVentaDlg.getCargo().setText("");
				throw new ValidacionCamposException("EL IMORTE DEL CARGO DEBE SER UN IMPORTE DE MONEDA", terminarVentaDlg.getCargo());
			}
			
			if (cargo != totalRedondeado) {
				this.terminarVentaDlg.getCargo().setText("");
				throw new ValidacionCamposException("EL IMORTE DEL CARGO DEBE SER = AL TOTAL", terminarVentaDlg.getCargo());
			}
			*/
			if (autorizacion.length() < 4) {
				this.terminarVentaDlg.getAutorizacion().setText("");
				throw new ValidacionCamposException("EL NO. DE AUTORIZACION DEBEN SER >= 4 DIGITOS", terminarVentaDlg.getAutorizacion());
			}
		} else if (mdpSelected.getId() == Constants.ID_MDP_EFECTIVO_Y_TARJETA) {
			try {
				recibido = Double.parseDouble(recibidoText);
			} catch (NumberFormatException nfe) {
				this.terminarVentaDlg.getRecibido().setText("");
				this.terminarVentaDlg.getCambio().setText("");
				throw new ValidacionCamposException("EL IMORTE RECIBIDO DEBE SER UN IMPORTE DE MONEDA", terminarVentaDlg.getRecibido());
			}

			if (recibido < 0 || recibido > 100000) {
				this.terminarVentaDlg.getRecibido().setText("");
				this.terminarVentaDlg.getCambio().setText("");
				throw new ValidacionCamposException("EL IMORTE RECIBIDO NO PARECE SER UN IMPORTE RAZONABLE: DEBE SER $0.01 A $99,999.99", terminarVentaDlg.getRecibido());
			}

			try {
				cargo = Double.parseDouble(cargoText);
			} catch (NumberFormatException nfe) {
				this.terminarVentaDlg.getCargo().setText("");
				throw new ValidacionCamposException("EL IMORTE DEL CARGO DEBE SER UN IMPORTE DE MONEDA", terminarVentaDlg.getCargo());
			}

			double difReal = (recibido + cargo) - totalRedondeado;

			if (difReal < 0) {
				this.terminarVentaDlg.getCargo().setText("");
				this.terminarVentaDlg.getRecibido().setText("");
				this.terminarVentaDlg.getCambio().setText("");
				throw new ValidacionCamposException("EL IMORTE RECIBIDO + EL CARGO DEBE SER >= AL TOTAL", terminarVentaDlg.getRecibido());
			}

			if (autorizacion.length() < 4) {
				this.terminarVentaDlg.getAutorizacion().setText("");
				throw new ValidacionCamposException("EL NO. DE AUTORIZACION DEBEN SER >= 4 DIGITOS", terminarVentaDlg.getAutorizacion());
			}
		}
	}

	private void recibido_focusLost() {
		logger.info("[USER]->recibido_focusLost():recibido=" + terminarVentaDlg.getRecibido().getText());
		if (validateAll()) {

		} else {

		}
	}

	private void recibido_ActionPerformed() {
		logger.info("[USER]->recibido_ActionPerformed():recibido=" + terminarVentaDlg.getRecibido().getText());
		if (validateAll()) {

		} else {

		}

	}

	private void cargo_ActionPerformed() {
		logger.info("[USER]->cargo_ActionPerformed():recibido=" + terminarVentaDlg.getRecibido().getText());
		if (validateAll()) {

		} else {

		}

	}

	private void autorizacion_ActionPerformed() {
		logger.info("[USER]->autorizacion_ActionPerformed():recibido=" + terminarVentaDlg.getRecibido().getText());
		if (validateAll()) {

		} else {

		}

	}

	public boolean isCierreCorrecto() {
		return cierreCorrecto;
	}

	private ComboBoxModel getClientes() {
		List<Cliente> clienteList = MemoryDAO.getPaqueteSinc().getClienteList();
		Vector<Cliente> v = new Vector<Cliente>();
		logger.debug("getClientes:");
		for (Cliente c : clienteList) {
			logger.trace("getClientes:\t 1)for() : c=" + c);
			if (c.getId() == Constants.ID_CLIENTE_MOSTRADOR) {
				v.add(c);
			}
		}

		for (Cliente c : clienteList) {
			logger.trace("getClientes:\t 2)for() : c=" + c);
			if (c.getId() != Constants.ID_CLIENTE_MOSTRADOR) {
				v.add(c);
			}
		}

		ComboBoxModel m = new DefaultComboBoxModel(v);

		return m;
	}

	private ComboBoxModel getFormasDePago() {
		logger.debug("getFormasDePago:");
		List<FormaDePago> formaDePagoList = MemoryDAO.getPaqueteSinc().getFormaDePagoList();
		Vector<FormaDePago> v = new Vector<FormaDePago>();

		for (FormaDePago fdp : formaDePagoList) {
			logger.trace("getFormasDePago:\t 1)for() : fdp=" + fdp);
			if (fdp.getId() == Constants.ID_FDP_1SOLA_E) {
				v.add(fdp);
			}
		}

		for (FormaDePago fdp : formaDePagoList) {
			logger.trace("getFormasDePago:\t 1)for() : fdp=" + fdp);
			if (fdp.getId() != Constants.ID_FDP_1SOLA_E) {
				v.add(fdp);
			}
		}

		ComboBoxModel m = new DefaultComboBoxModel(v);

		return m;
	}

	private ComboBoxModel getMetodosDePago() {
		logger.debug("getMetodosDePago:");
		List<MetodoDePago> formaDePagoList = MemoryDAO.getPaqueteSinc().getMetodoDePagoList();
		Vector<MetodoDePago> v = new Vector<MetodoDePago>();

		for (MetodoDePago mdp : formaDePagoList) {
			logger.trace("getMetodosDePago:\t 1)for() : mdp=" + mdp);
			if (mdp.getId() == Constants.ID_MDP_EFECTIVO || mdp.getId() == Constants.ID_MDP_TARJETA) {
				v.add(mdp);
			}
		}
		ComboBoxModel m = new DefaultComboBoxModel(v);

		return m;
	}

	int getClienteId() {
		logger.debug("getClienteId:terminarVentaDlg=" + terminarVentaDlg);
		logger.debug("getClienteId:terminarVentaDlg.getCliente()=" + terminarVentaDlg.getCliente());
		Cliente cteSelected = (Cliente) terminarVentaDlg.getCliente().getSelectedItem();
		logger.debug("getClienteId:cteSelected.getId()=" + cteSelected.getId());
		return cteSelected.getId();
	}

	int getMetodoDePagoId() {
		MetodoDePago mdpSelected = (MetodoDePago) terminarVentaDlg.getMetodoDePago().getSelectedItem();
		return mdpSelected.getId();
	}

	public double getRecibido() {
		return recibido;
	}

	public String getAutorizacion() {
		return autorizacion;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == terminarVentaDlg.getMetodoDePago() && e.getStateChange() == ItemEvent.SELECTED) {

			this.terminarVentaDlg.getRecibido().setText("");
			this.terminarVentaDlg.getCambio().setText("");
			this.terminarVentaDlg.getCargo().setText("");
			this.terminarVentaDlg.getAutorizacion().setText("");

			if (((MetodoDePago) e.getItem()).getId() == Constants.ID_MDP_EFECTIVO) {
				this.terminarVentaDlg.getRecibido().setEnabled(true);
				this.terminarVentaDlg.getCambio().setEnabled(true);
				this.terminarVentaDlg.getCargo().setEnabled(false);
				this.terminarVentaDlg.getAutorizacion().setEnabled(true);
			} else if (((MetodoDePago) e.getItem()).getId() == Constants.ID_MDP_TARJETA) {
				
				this.terminarVentaDlg.getRecibido().setEnabled(false);
				this.terminarVentaDlg.getCambio().setEnabled(false);
				
				this.terminarVentaDlg.getCargo().setEnabled(false);
				this.terminarVentaDlg.getCargo().setText(Constants.dfCurrency.format(total));
				
				this.terminarVentaDlg.getAutorizacion().setEnabled(true);
			} else if (((MetodoDePago) e.getItem()).getId() == Constants.ID_MDP_EFECTIVO_Y_TARJETA) {
				this.terminarVentaDlg.getRecibido().setEnabled(true);
				this.terminarVentaDlg.getCambio().setEnabled(false);
				this.terminarVentaDlg.getCargo().setEnabled(true);
				this.terminarVentaDlg.getAutorizacion().setEnabled(true);
			}
		}
	}
}
