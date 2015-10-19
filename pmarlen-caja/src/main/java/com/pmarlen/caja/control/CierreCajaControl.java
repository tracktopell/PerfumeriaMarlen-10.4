package com.pmarlen.caja.control;

import com.pmarlen.caja.dao.MemoryDAO;
import com.pmarlen.caja.view.CierreCajaJFrame;
import com.pmarlen.model.Constants;
import com.pmarlen.model.GeneradorDeToken;
import com.pmarlen.rest.dto.U;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
public class CierreCajaControl implements ActionListener , FocusListener, ValidadorDeCampos{
	private static Logger logger = Logger.getLogger(CierreCajaControl.class.getName());
	private CierreCajaJFrame aperturaCajaDialog;	
	private double saldoInicial;
	private double saldoFinal;
	private double saldoEstimado;
	private double diferencia;
	private String usuarioAutorizo;
	private String observaciones;
	private boolean cierreCorrecto;
	public static final int OBSERVACIONES_MAX_LENGTH = 64;
	
	public CierreCajaControl(CierreCajaJFrame dialogLogin) {
		this.aperturaCajaDialog = dialogLogin;
		this.aperturaCajaDialog.getSaldoFinal().addFocusListener(this);
		this.aperturaCajaDialog.getSaldoFinal().addActionListener(this);
		this.aperturaCajaDialog.getAceptar() .addActionListener(this);
		this.aperturaCajaDialog.getCancelar().addActionListener(this);
		this.aperturaCajaDialog.getObservaciones().addFocusListener(this);
		this.aperturaCajaDialog.getObservaciones().addActionListener(this);
		this.aperturaCajaDialog.getGeneraFrase().addActionListener(this);
		this.aperturaCajaDialog.getToken().addFocusListener(this);
		this.aperturaCajaDialog.getToken().addActionListener(this);
	}

	public CierreCajaJFrame getCierreCajaJFrame() {
		return aperturaCajaDialog;
	}
	
	public void estadoInicial(){
		saldoInicial  = ApplicationLogic.getInstance().getCorteCajaDTO().getSaldoInicial().doubleValue();
		saldoEstimado = ApplicationLogic.getInstance().getSaldoFinalEstimado();
		observaciones = null;
		cierreCorrecto = false;
		aperturaCajaDialog.getSaldoInicial().setText(Constants.dfMoneda.format(saldoEstimado));
		aperturaCajaDialog.getAceptar().setEnabled(false);
		
		aperturaCajaDialog.getCierreAnormalPanel().setEnabled(false);
		aperturaCajaDialog.getUsuarioAutorizo().setModel(getAdministradores());
		aperturaCajaDialog.setVisible(true);
	}
	
	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == aperturaCajaDialog.getSaldoFinal()) {
			saldoFinal_focusLost();
		} if (e.getSource() == aperturaCajaDialog.getAceptar()) {
			aceptar_ActionPerformed();
		} else if (e.getSource() == aperturaCajaDialog.getCancelar()) {
			cancelar_ActionPerformed();
		} else if (e.getSource() == aperturaCajaDialog.getGeneraFrase()) {
			generaFrase_ActionPerformed();
		}		
	}
	
	private void aceptar_ActionPerformed(){
		logger.info("[USER]->aceptar_ActionPerformed()");
		
		if(validateAll()) {
			aperturaCajaDialog.getAceptar().setEnabled(true);
		} else {
			aperturaCajaDialog.getAceptar().setEnabled(false);
		}
		
		registraCirreCaja();
		
		aperturaCajaDialog.dispose();
		aperturaCajaDialog = null;
	}
	
	private void generaFrase_ActionPerformed() {
		GeneradorDeToken gt = new GeneradorDeToken();		
		aperturaCajaDialog.getFrase().setText(gt.getFrase());
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
	
	private void registraCirreCaja(){
		ApplicationLogic.getInstance().getCorteCajaDTO().setUsuarioEmail(ApplicationLogic.getInstance().getLogged().getE());
		ApplicationLogic.getInstance().getCorteCajaDTO().setSucursalId(MemoryDAO.getSucursalId());
		ApplicationLogic.getInstance().getCorteCajaDTO().setCaja(MemoryDAO.getNumCaja());
		ApplicationLogic.getInstance().getCorteCajaDTO().setFecha(System.currentTimeMillis());
		ApplicationLogic.getInstance().getCorteCajaDTO().setTipoEvento(Constants.TIPO_EVENTO_CIERRE);
		ApplicationLogic.getInstance().getCorteCajaDTO().setSaldoInicial(saldoInicial);
		ApplicationLogic.getInstance().getCorteCajaDTO().setSaldoFinal(saldoFinal);		
		ApplicationLogic.getInstance().getCorteCajaDTO().setComentarios(observaciones);
		ApplicationLogic.getInstance().getCorteCajaDTO().setUsuarioAutorizo(usuarioAutorizo);
		
		MemoryDAO.saveCorteCajaDTO(ApplicationLogic.getInstance().getCorteCajaDTO());
		MemoryDAO.backupCorteCajaDTO(ApplicationLogic.getInstance().getCorteCajaDTO());
		
		cierreCorrecto=true;
	}

	@Override
	public void focusGained(FocusEvent fe) {
		
	}

	@Override
	public void focusLost(FocusEvent fe) {
		if (fe.getSource() == aperturaCajaDialog.getSaldoInicial()) {
			saldoFinal_focusLost();
		}
	}
	
	public void setFontBigest() {
		aperturaCajaDialog.setFont(new java.awt.Font("Tahoma", 0, 24));
	}

	@Override
	public void validate() throws ValidacionCamposException {
		String saldoFinalValue = aperturaCajaDialog.getSaldoFinal().getText().replace("$", "").replace(",", "").trim();
		
		try {
			saldoFinal = Double.parseDouble(saldoFinalValue);			
		} catch(NumberFormatException nfe){
			throw new ValidacionCamposException("EL SALDO DEBE SER UN IMPORTE DE MONEDA", aperturaCajaDialog.getSaldoInicial());
		}
		
		if(saldoFinal< 0 || saldoFinal > 100000 ) {
			throw new ValidacionCamposException("EL SALDO NO PARECE SER UN IMPORTE RAZONABLE: DEBE SER $0.00 A $99,999.99", aperturaCajaDialog.getSaldoInicial());
		}
		
		
		double difReal     = saldoFinal    - saldoInicial;
		double difEstimada = saldoEstimado - saldoInicial;		
		double difRelativa = difReal - difEstimada;
		// 10 = 12 - 2
		// 11 = 13 - 2
		// -1 = 10 - 11
		
		if(difRelativa < 0 ) {
			if( !aperturaCajaDialog.getCierreAnormalPanel().isEnabled()) {
				aperturaCajaDialog.getCierreAnormalPanel().setEnabled(true);
				throw new ValidacionCamposException("DEBE ESCRIBIR LA RAZÓN DE LA DIFERENCIA Y PEDIR AUTORIZACIÓN", aperturaCajaDialog.getCierreAnormalPanel());
			} else {
				observaciones = aperturaCajaDialog.getObservaciones().getText().trim();
				
				if(observaciones.length() > 0 ){
					if(observaciones.length() > OBSERVACIONES_MAX_LENGTH) {
						observaciones = observaciones.substring(0, OBSERVACIONES_MAX_LENGTH);
					} else if(observaciones.length() > OBSERVACIONES_MAX_LENGTH) {
					
					}
				} else {
						
						
				}	

			}
		}
	}

	private void 	saldoFinal_focusLost() {
		logger.info("[USER]->saldoInicial_focusLost():saldoInicial="+aperturaCajaDialog.getSaldoInicial().getText());
		if(validateAll()) {
			aperturaCajaDialog.getAceptar().setEnabled(true);
		} else {
			aperturaCajaDialog.getAceptar().setEnabled(false);
		}
	}

	public boolean isCierreCorrecto() {
		return cierreCorrecto;
	}

	private ComboBoxModel getAdministradores() {
		List<U> usuarioList = MemoryDAO.getPaqueteSinc().getUsuarioList();		
		Vector<U> v= new Vector<U>();
		U seleccioneUsuario = new U();
		seleccioneUsuario.setE(null);
		seleccioneUsuario.setN("-- SELECCIONE --");
		v.add(seleccioneUsuario);
		for(U u:usuarioList){
			if(u.getPlaysAsAdmin()) {
				v.add(u);
			}
		}
		
		ComboBoxModel m =  new DefaultComboBoxModel();
		
		return m;
	}
	
	
}
