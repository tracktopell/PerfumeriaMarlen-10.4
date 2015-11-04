package com.pmarlen.caja.control;

import com.pmarlen.backend.model.Cliente;
import com.pmarlen.backend.model.FormaDePago;
import com.pmarlen.backend.model.MetodoDePago;
import com.pmarlen.caja.dao.MemoryDAO;
import com.pmarlen.caja.view.CierreCajaJFrame;
import com.pmarlen.caja.view.TerminarVentaDlg;
import com.pmarlen.model.Constants;
import com.pmarlen.model.GeneradorDeToken;
import com.pmarlen.rest.dto.U;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
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
public class TerminarVentaControl implements ActionListener , ItemListener , FocusListener, ValidadorDeCampos{
	private static Logger logger = Logger.getLogger(TerminarVentaControl.class.getName());
	private TerminarVentaDlg terminarVentaDlg;	
	private double diferencia;
	private boolean cierreCorrecto;	
	private double subTotal;
	private double descuento;
	private double total;
	private double recibido;
	private double cargo;
	private String autorizacion;

	public TerminarVentaControl(TerminarVentaDlg terminarVentaDlg, double subTotal, double descuento, double total) {
		this.terminarVentaDlg = terminarVentaDlg;
		this.subTotal = subTotal;
		this.descuento = descuento;
		this.total = total;
		
		this.terminarVentaDlg.getCliente().setModel(getClientes());
		this.terminarVentaDlg.getMetodoDePago().setModel(getMetodosDePago());
		this.terminarVentaDlg.getMetodoDePago().addItemListener(this);
		this.terminarVentaDlg.getAceptar() .addActionListener(this);
		this.terminarVentaDlg.getCancelar().addActionListener(this);
	}

	public TerminarVentaDlg getCierreCajaJFrame() {
		return terminarVentaDlg;
	}
	
	public void estadoInicial(){
		this.terminarVentaDlg.getSubtotal().setText(Constants.dfCurrency.format(subTotal));
		this.terminarVentaDlg.getDescuento().setText(Constants.dfCurrency.format(descuento));
		this.terminarVentaDlg.getTotal().setText(Constants.dfCurrency.format(total));
		this.terminarVentaDlg.getSubtotal().setText(Constants.dfCurrency.format(subTotal));
		
		this.terminarVentaDlg.getCliente().setSelectedIndex(0);
		this.terminarVentaDlg.getMetodoDePago().setSelectedIndex(0);
		
		terminarVentaDlg.setVisible(true);
	}
	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == terminarVentaDlg.getAceptar()) {
			aceptar_ActionPerformed();
		} else if (e.getSource() == terminarVentaDlg.getCancelar()) {
			cancelar_ActionPerformed();
		}		
	}
	
	private void aceptar_ActionPerformed(){
		logger.info("[USER]->aceptar_ActionPerformed()");
		if(validateAll()) {
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
				if(componentWithError instanceof JTextField) {
					((JTextField)componentWithError).setText("");
				}
				//componentWithError.requestFocus();
				return false;
			}
		}
		return true;
	}
	
	private void cancelar_ActionPerformed(){
		logger.info("[USER]->cancelar_ActionPerformed()");
		terminarVentaDlg.dispose();
		terminarVentaDlg = null;
	}
	
	private void registrarVenta(){
		cierreCorrecto=true;
	}

	@Override
	public void focusGained(FocusEvent fe) {
		
	}

	@Override
	public void focusLost(FocusEvent fe) {
		
	}
	
	public void setFontBigest() {
		terminarVentaDlg.setFont(new java.awt.Font("Tahoma", 0, 24));
	}

	@Override
	public void validate() throws ValidacionCamposException {
		MetodoDePago mdpSelected = (MetodoDePago)terminarVentaDlg.getMetodoDePago().getSelectedItem();
		String recibidoText = terminarVentaDlg.getRecibido().getText().replace("$", "").replace(",", "").trim();
		String cargoText    = terminarVentaDlg.getCargo().getText().replace("$", "").replace(",", "").trim();
		autorizacion = terminarVentaDlg.getAutorizacion().getText().trim();
		if(mdpSelected.getId() == Constants.ID_MDP_EFECTIVO) {
			try {
				recibido = Double.parseDouble(recibidoText);			
			} catch(NumberFormatException nfe){
				throw new ValidacionCamposException("EL IMORTE RECIBIDO DEBE SER UN IMPORTE DE MONEDA", terminarVentaDlg.getRecibido());
			}

			if(recibido< 0 || recibido > 100000 ) {
				throw new ValidacionCamposException("EL IMORTE RECIBIDO NO PARECE SER UN IMPORTE RAZONABLE: DEBE SER $0.01 A $99,999.99", terminarVentaDlg.getRecibido());
			}

			double difReal     = recibido - total;

			if(difReal < 0 ) {
				throw new ValidacionCamposException("EL IMORTE RECIBIDO DEBE SER >= AL TOTAL", terminarVentaDlg.getRecibido());
			}
			
			this.terminarVentaDlg.getCambio().setText(Constants.dfCurrency.format(difReal));
			
		} else  if (mdpSelected.getId() == Constants.ID_MDP_TARJETA){
			try {
				cargo = Double.parseDouble(cargoText);			
			} catch(NumberFormatException nfe){
				throw new ValidacionCamposException("EL IMORTE DEL CARGO DEBE SER UN IMPORTE DE MONEDA", terminarVentaDlg.getCargo());
			}
			
			if(cargo != total ) {
				throw new ValidacionCamposException("EL IMORTE DEL CARGO DEBE SER = AL TOTAL", terminarVentaDlg.getCargo());
			}
			
			if(autorizacion.length() < 4) {
				throw new ValidacionCamposException("EL NO. DE AUTORIZACION DEBEN SER >= 4 DIGITOS", terminarVentaDlg.getAutorizacion());
			}
		} else if ( mdpSelected.getId() == Constants.ID_MDP_EFECTIVO_Y_TARJETA){
			try {
				recibido = Double.parseDouble(recibidoText);			
			} catch(NumberFormatException nfe){
				throw new ValidacionCamposException("EL IMORTE RECIBIDO DEBE SER UN IMPORTE DE MONEDA", terminarVentaDlg.getRecibido());
			}

			if(recibido< 0 || recibido > 100000 ) {
				throw new ValidacionCamposException("EL IMORTE RECIBIDO NO PARECE SER UN IMPORTE RAZONABLE: DEBE SER $0.01 A $99,999.99", terminarVentaDlg.getRecibido());
			}

			try {
				cargo = Double.parseDouble(cargoText);			
			} catch(NumberFormatException nfe){
				throw new ValidacionCamposException("EL IMORTE DEL CARGO DEBE SER UN IMPORTE DE MONEDA", terminarVentaDlg.getCargo());
			}
			
			double difReal     = (recibido + cargo ) - total;

			if(difReal < 0 ) {
				throw new ValidacionCamposException("EL IMORTE RECIBIDO + EL CARGO DEBE SER >= AL TOTAL", terminarVentaDlg.getRecibido());
			}
			
			if(autorizacion.length() < 4) {
				throw new ValidacionCamposException("EL NO. DE AUTORIZACION DEBEN SER >= 4 DIGITOS", terminarVentaDlg.getAutorizacion());
			}
		}
	}

	private void recibido_focusLost() {
		logger.info("[USER]->recibido_focusLost():recibido="+terminarVentaDlg.getRecibido().getText());
		if(validateAll()) {
			terminarVentaDlg.getAceptar().setEnabled(true);
		} else {
			terminarVentaDlg.getAceptar().setEnabled(false);
		}
	}

	public boolean isCierreCorrecto() {
		return cierreCorrecto;
	}

	private ComboBoxModel getClientes() {
		List<Cliente> clienteList = MemoryDAO.getPaqueteSinc().getClienteList();
		Vector<Cliente> v= new Vector<Cliente>();
		logger.debug("getClientes:");
		for(Cliente c:clienteList){
			logger.debug("getClientes:\t 1)for() : c="+c);
			if(c.getId() == Constants.ID_CLIENTE_MOSTRADOR) {	
				v.add(c);
			}
		}
		
		for(Cliente c:clienteList){			
			logger.debug("getClientes:\t 2)for() : c="+c);
			if(c.getId() != Constants.ID_CLIENTE_MOSTRADOR) {
				v.add(c);
			}			
		}
		
		ComboBoxModel m =  new DefaultComboBoxModel(v);
		
		return m;
	}

	private ComboBoxModel getFormasDePago() {
		logger.debug("getFormasDePago:");
		List<FormaDePago> formaDePagoList = MemoryDAO.getPaqueteSinc().getFormaDePagoList();
		Vector<FormaDePago> v= new Vector<FormaDePago>();
		
		for(FormaDePago fdp:formaDePagoList){
			logger.debug("getFormasDePago:\t 1)for() : fdp="+fdp);
			if(fdp.getId() == Constants.ID_FDP_1SOLA_E) {
				v.add(fdp);
			}
		}
		
		for(FormaDePago fdp:formaDePagoList){
			logger.debug("getFormasDePago:\t 1)for() : fdp="+fdp);
			if(fdp.getId() != Constants.ID_FDP_1SOLA_E) {
				v.add(fdp);
			}
		}
		
		ComboBoxModel m =  new DefaultComboBoxModel(v);
		
		return m;
	}
	
	private ComboBoxModel getMetodosDePago() {
		logger.debug("getMetodosDePago:");
		List<MetodoDePago> formaDePagoList = MemoryDAO.getPaqueteSinc().getMetodoDePagoList();
		Vector<MetodoDePago> v= new Vector<MetodoDePago>();
		
		for(MetodoDePago mdp:formaDePagoList){
			logger.debug("getMetodosDePago:\t 1)for() : mdp="+mdp);
			if(mdp.getId() == Constants.ID_MDP_EFECTIVO || mdp.getId() == Constants.ID_MDP_TARJETA) {
				v.add(mdp);
			}
		}
		ComboBoxModel m =  new DefaultComboBoxModel(v);
		
		return m;
	}

	int getClienteId() {
		logger.debug("getClienteId:terminarVentaDlg="+terminarVentaDlg);
		logger.debug("getClienteId:terminarVentaDlg.getCliente()="+terminarVentaDlg.getCliente());
		Cliente cteSelected = (Cliente)terminarVentaDlg.getCliente().getSelectedItem();
		logger.debug("getClienteId:cteSelected.getId()="+cteSelected.getId());
		return cteSelected.getId();
	}
	
	int getMetodoDePagoId() {
		MetodoDePago mdpSelected = (MetodoDePago)terminarVentaDlg.getMetodoDePago().getSelectedItem();
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
		if(e.getSource() == terminarVentaDlg.getMetodoDePago() && e.getStateChange() == ItemEvent.SELECTED) {
			
			this.terminarVentaDlg.getRecibido().setText("");
			this.terminarVentaDlg.getCambio().setText("");
			this.terminarVentaDlg.getCargo().setText("");
			this.terminarVentaDlg.getAutorizacion().setText("");
			
			if(((MetodoDePago)e.getItem()).getId() == Constants.ID_MDP_EFECTIVO){
				this.terminarVentaDlg.getRecibido().setEnabled(true);
				this.terminarVentaDlg.getCambio().setEnabled(true);
				this.terminarVentaDlg.getCargo().setEnabled(false);
				this.terminarVentaDlg.getAutorizacion().setEnabled(true);
			} else if(((MetodoDePago)e.getItem()).getId() == Constants.ID_MDP_TARJETA){
				this.terminarVentaDlg.getRecibido().setEnabled(false);
				this.terminarVentaDlg.getCambio().setEnabled(false);
				this.terminarVentaDlg.getCargo().setEnabled(true);
				this.terminarVentaDlg.getAutorizacion().setEnabled(true);
			} else if(((MetodoDePago)e.getItem()).getId() == Constants.ID_MDP_EFECTIVO_Y_TARJETA){
				this.terminarVentaDlg.getRecibido().setEnabled(true);
				this.terminarVentaDlg.getCambio().setEnabled(false);
				this.terminarVentaDlg.getCargo().setEnabled(true);
				this.terminarVentaDlg.getAutorizacion().setEnabled(true);
			}
		}
	}
	
}
