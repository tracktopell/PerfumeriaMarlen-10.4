package com.pmarlen.caja.control;

import com.pmarlen.caja.dao.MemoryDAO;
import com.pmarlen.caja.view.AperturaCajaJFrame;
import com.pmarlen.model.Constants;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;
import org.apache.log4j.Logger;

/**
 *
 * @author tracktopell
 */
public class AperturaCajaControl implements ActionListener , FocusListener, ValidadorDeCampos{
	private static Logger logger = Logger.getLogger(AperturaCajaControl.class.getName());
	private AperturaCajaJFrame aperturaCajaDialog;	
	private double saldoInicial;
	private String observaciones;
	private boolean aperturaCorrecta;
	public static final int OBSERVACIONES_MAX_LENGTH = 64;
	
	public AperturaCajaControl(AperturaCajaJFrame dialogLogin) {
		this.aperturaCajaDialog = dialogLogin;
		this.aperturaCajaDialog.getSaldoInicial()   .addFocusListener(this);
		this.aperturaCajaDialog.getSaldoInicial()   .addActionListener(this);
		this.aperturaCajaDialog.getAceptar() .addActionListener(this);
		this.aperturaCajaDialog.getCancelar().addActionListener(this);
	}

	public AperturaCajaJFrame getAperturaCajaJFrame() {
		return aperturaCajaDialog;
	}
	
	public void estadoInicial(){
		saldoInicial = 0.0;
		observaciones = null;
		aperturaCorrecta = false;
		aperturaCajaDialog.getAceptar().setEnabled(false);
		aperturaCajaDialog.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == aperturaCajaDialog.getSaldoInicial()) {
			saldoInicial_focusLost();
		} if (e.getSource() == aperturaCajaDialog.getAceptar()) {
			aceptar_ActionPerformed();
		} else if (e.getSource() == aperturaCajaDialog.getCancelar()) {
			cancelar_ActionPerformed();
		}		
	}
	
	private void aceptar_ActionPerformed(){
		logger.info("[USER]->aceptar_ActionPerformed()");
		
		if(validateAll()) {
			aperturaCajaDialog.getAceptar().setEnabled(true);
		} else {
			aperturaCajaDialog.getAceptar().setEnabled(false);
		}
		
		registraAperturaCaja();
		
		aperturaCajaDialog.dispose();
		aperturaCajaDialog = null;
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
				JOptionPane.showMessageDialog(aperturaCajaDialog, ve.getMessage(), "ACEPTAR", JOptionPane.ERROR_MESSAGE);
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
		aperturaCajaDialog.dispose();
		aperturaCajaDialog = null;
	}
	
	private void registraAperturaCaja(){
		ApplicationLogic.getInstance().getCorteCajaDTO().setUsuarioEmail(ApplicationLogic.getInstance().getLogged().getE());
		ApplicationLogic.getInstance().getCorteCajaDTO().setSucursalId(MemoryDAO.getSucursalId());
		ApplicationLogic.getInstance().getCorteCajaDTO().setCaja(MemoryDAO.getNumCaja());
		ApplicationLogic.getInstance().getCorteCajaDTO().setFecha(System.currentTimeMillis());
		ApplicationLogic.getInstance().getCorteCajaDTO().setTipoEvento(Constants.TIPO_EVENTO_APERTURA);
		ApplicationLogic.getInstance().getCorteCajaDTO().setSaldoInicial(saldoInicial);
		ApplicationLogic.getInstance().getCorteCajaDTO().setSaldoFinal(null);		
		ApplicationLogic.getInstance().getCorteCajaDTO().setComentarios(observaciones);
		
		MemoryDAO.saveCorteCajaDTO(ApplicationLogic.getInstance().getCorteCajaDTO());
		MemoryDAO.backupCorteCajaDTO(ApplicationLogic.getInstance().getCorteCajaDTO());
		
		aperturaCorrecta=true;
	}

	@Override
	public void focusGained(FocusEvent fe) {
		
	}

	@Override
	public void focusLost(FocusEvent fe) {
		if (fe.getSource() == aperturaCajaDialog.getSaldoInicial()) {
			saldoInicial_focusLost();
		}
	}
	
	public void setFontBigest() {
		aperturaCajaDialog.setFont(new java.awt.Font("Tahoma", 0, 24));
	}

	@Override
	public void validate() throws ValidacionCamposException {
		String saldoInicialValue = aperturaCajaDialog.getSaldoInicial().getText().replace("$", "").replace(",", "").trim();
		
		try {
			saldoInicial = Double.parseDouble(saldoInicialValue);			
		} catch(NumberFormatException nfe){
			throw new ValidacionCamposException("EL SALDO DEBE SER UN IMPORTE DE MONEDA", aperturaCajaDialog.getSaldoInicial());
		}
		
		if(saldoInicial< 0 || saldoInicial > 10000 ) {
			throw new ValidacionCamposException("EL SALDO NO PARECE SER UN IMPORTE RAZONABLE: DEBE SER $0.00 A $9,999.99", aperturaCajaDialog.getSaldoInicial());
		}
		
		String observaciones = aperturaCajaDialog.getObservaciones().getText().trim();
		
		if(observaciones.length() > 0 ){
			if(observaciones.length() > OBSERVACIONES_MAX_LENGTH) {
				observaciones = observaciones.substring(0, OBSERVACIONES_MAX_LENGTH);
			}
		}		
	}

	private void saldoInicial_focusLost() {
		logger.info("[USER]->saldoInicial_focusLost():saldoInicial="+aperturaCajaDialog.getSaldoInicial().getText());
		if(validateAll()) {
			aperturaCajaDialog.getAceptar().setEnabled(true);
		} else {
			aperturaCajaDialog.getAceptar().setEnabled(false);
		}
	}

	public boolean isAperturaCorrecta() {
		return aperturaCorrecta;
	}
	
	
}
