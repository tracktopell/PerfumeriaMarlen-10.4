package com.pmarlen.caja.control;

import com.pmarlen.caja.dao.MemoryDAO;
import com.pmarlen.caja.view.CierreCajaJFrame;
import com.pmarlen.model.Constants;
import com.pmarlen.model.GeneradorDeToken;
import com.pmarlen.rest.dto.U;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.text.ParseException;
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
	private CierreCajaJFrame cierreCajaDialog;	
	private double saldoInicial;
	private double saldoFinal;
	private double saldoEstimado;
	private double saldoNeto;
	private double diferencia;
	private String usuarioAutorizo;
	private String observaciones;
	private boolean cierreCorrecto;
	public static final int OBSERVACIONES_MAX_LENGTH = 255;
	
	public CierreCajaControl(CierreCajaJFrame dialogLogin) {
		this.cierreCajaDialog = dialogLogin;
		this.cierreCajaDialog.getSaldoFinal().addFocusListener(this);
		this.cierreCajaDialog.getSaldoFinal().addActionListener(this);
		this.cierreCajaDialog.getAceptar() .addActionListener(this);
		this.cierreCajaDialog.getCancelar().addActionListener(this);
		this.cierreCajaDialog.getObservaciones().addFocusListener(this);
		this.cierreCajaDialog.getObservaciones().addActionListener(this);
		this.cierreCajaDialog.getGeneraFrase().addActionListener(this);
		this.cierreCajaDialog.getToken().addFocusListener(this);
		this.cierreCajaDialog.getToken().addActionListener(this);
	}

	public CierreCajaJFrame getCierreCajaJFrame() {
		return cierreCajaDialog;
	}
	
	public void estadoInicial(){
		saldoInicial  = ApplicationLogic.getInstance().getCorteCajaDTO().getSaldoInicial().doubleValue();
		//saldoEstimado = saldoInicial + ApplicationLogic.getInstance().getSaldoFinalEstimado();
		saldoEstimado = saldoInicial;
		buscarSaldoFinalEstimado();
		saldoNeto     = saldoEstimado - saldoInicial;
		logger.debug("estadoInicial:saldoInicial="+saldoInicial+", saldoEstimado="+saldoEstimado+", saldoNeto="+saldoNeto);
		observaciones = null;
		cierreCorrecto = false;
		cierreCajaDialog.getSaldoInicial().setText(Constants.df2Decimal.format(saldoInicial));
		cierreCajaDialog.getEstimado().setText(Constants.df2Decimal.format(saldoEstimado));
		cierreCajaDialog.getNeto().setText(Constants.df2Decimal.format(saldoNeto));
		cierreCajaDialog.getAceptar().setEnabled(false);
		
		cierreCajaDialog.getCierreAnormalPanel().setEnabled(false);
		cierreCajaDialog.getUsuarioAutorizo().setModel(getAdministradores());
		cierreCajaDialog.setVisible(true);
	}
	
	private void buscarSaldoFinalEstimado(){
		new Thread(){
			public void run(){
				try {
					saldoEstimado = saldoInicial + ApplicationLogic.getInstance().getSaldoFinalEstimado();
					cierreCajaDialog.getEstimado().setText(Constants.df2Decimal.format(saldoEstimado));
					saldoNeto     = saldoEstimado - saldoInicial;
					cierreCajaDialog.getNeto().setText(Constants.df2Decimal.format(saldoNeto));		
					logger.debug("buscarSaldoFinalEstimado:saldoInicial="+saldoInicial+", saldoEstimado="+saldoEstimado+", saldoNeto="+saldoNeto);
				}catch(IOException ioe){
					JOptionPane.showMessageDialog(cierreCajaDialog, "NO SE PUEDE OBTENER EL SALDO FINAL ESTIMADO", "CIERRE CAJA", JOptionPane.ERROR_MESSAGE);
				}
			}
		}.start();
	}
	
	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cierreCajaDialog.getSaldoFinal()) {
			saldoFinal_focusLost();
		} if (e.getSource() == cierreCajaDialog.getAceptar()) {
			aceptar_ActionPerformed();
		} else if (e.getSource() == cierreCajaDialog.getCancelar()) {
			cancelar_ActionPerformed();
		} else if (e.getSource() == cierreCajaDialog.getGeneraFrase()) {
			generaFrase_ActionPerformed();
		}		
	}
	
	private void aceptar_ActionPerformed(){
		logger.info("[USER]->aceptar_ActionPerformed()");
		
		if(validateAll()) {
			cierreCajaDialog.getAceptar().setEnabled(true);
		} else {
			cierreCajaDialog.getAceptar().setEnabled(false);
		}
		
		registraCirreCaja();
		
		cierreCajaDialog.dispose();
		cierreCajaDialog = null;
	}
	
	private void generaFrase_ActionPerformed() {
		GeneradorDeToken gt = new GeneradorDeToken();		
		cierreCajaDialog.getFrase().setText(gt.getFrase());
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
				JOptionPane.showMessageDialog(cierreCajaDialog, ve.getMessage(), "ACEPTAR", JOptionPane.ERROR_MESSAGE);
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
		cierreCajaDialog.dispose();
		cierreCajaDialog = null;
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
		MemoryDAO.iniciaEnvioCierreCaja();
		cierreCorrecto=true;
	}

	@Override
	public void focusGained(FocusEvent fe) {
		
	}

	@Override
	public void focusLost(FocusEvent fe) {
		if (fe.getSource() == cierreCajaDialog.getSaldoInicial()) {
			saldoFinal_focusLost();
		}
	}
	
	public void setFontBigest() {
		cierreCajaDialog.setFont(new java.awt.Font("Tahoma", 0, 24));
	}

	@Override
	public void validate() throws ValidacionCamposException {
		String saldoFinalValue = cierreCajaDialog.getSaldoFinal().getText().replace("$", "").replace(",", "").trim();
		
		try {
			saldoFinal = Double.parseDouble(saldoFinalValue);		
		} catch(NumberFormatException nfe){
			throw new ValidacionCamposException("EL SALDO DEBE SER UN IMPORTE DE MONEDA", cierreCajaDialog.getSaldoInicial());
		}
		
		if(saldoFinal< 0 || saldoFinal > 100000 ) {
			throw new ValidacionCamposException("EL SALDO NO PARECE SER UN IMPORTE RAZONABLE: DEBE SER $0.00 A $99,999.99", cierreCajaDialog.getSaldoInicial());
		}
		double saldoEstimadoRedondeado = saldoEstimado;
		try {
			saldoEstimadoRedondeado = Constants.df2Decimal.parse(cierreCajaDialog.getEstimado().getText()).doubleValue();
		}catch(ParseException pe){
		}
		double difReal     = saldoFinal - saldoEstimadoRedondeado;
		double factorPerdida = 0.0;
		
		cierreCajaDialog.getDiferencia().setText(Constants.df2Decimal.format(difReal));
		boolean grave=false;
		if(difReal < 0 ) {
			factorPerdida = (difReal*-1)/saldoNeto;
			logger.info("validate:factorPerdida="+factorPerdida);
			
			if(saldoFinal < saldoInicial || factorPerdida<=0.1 || (difReal*-1) > 100.0){
				cierreCajaDialog.getDiferencia().setBackground(Color.RED);
				grave =true;
			} else {
				cierreCajaDialog.getDiferencia().setBackground(cierreCajaDialog.getSaldoInicial().getBackground());
				grave =false;
			}
			
			if( !cierreCajaDialog.getCierreAnormalPanel().isEnabled()) {
				cierreCajaDialog.getCierreAnormalPanel().setEnabled(true);
				throw new ValidacionCamposException("DEBE ESCRIBIR LA RAZÓN DE LA DIFERENCIA Y PEDIR AUTORIZACIÓN", cierreCajaDialog.getObservaciones());
			} else {
				observaciones = cierreCajaDialog.getObservaciones().getText().trim();
				
				if(observaciones.length() > 0 ){
					if(observaciones.length() > OBSERVACIONES_MAX_LENGTH) {
						observaciones = observaciones.substring(0, OBSERVACIONES_MAX_LENGTH-3)+"...";
					} else if(observaciones.length() <= 10) {
						throw new ValidacionCamposException("DEBE ESCRIBIR UNA RAZÓN CON COHERENCIA Y EXPLICITA, NO PUEDE SER UNA(S) SIMPLE(S) PALABRA(S)", cierreCajaDialog.getObservaciones());	
					}
				} else {
					throw new ValidacionCamposException("DEBE ESCRIBIR LA RAZÓN DE LA DIFERENCIA, NO PUEDE QUEDAR EN BLANCO ", cierreCajaDialog.getObservaciones());	
				}
			}
			
			if(grave){
				if(cierreCajaDialog.getUsuarioAutorizo().getSelectedIndex()==0){
					throw new ValidacionCamposException("DEBE ELEGIR QUIÉN AUTORIZARÁ", cierreCajaDialog.getUsuarioAutorizo());	
				}
				String fraseGenerada = cierreCajaDialog.getFrase().getText().trim();

				if(fraseGenerada.length() == 0){
					throw new ValidacionCamposException("DEBE OBTENER FRASE CON EL BOTÓN", cierreCajaDialog.getGeneraFrase());	
				}

				String tokenEscrito = cierreCajaDialog.getToken().getText().trim();			
				if(cierreCajaDialog.getToken().getText().trim().length() == 0 ) {
					throw new ValidacionCamposException("LLAME A SU ADMINISTRADOR QUE ELIGIO PARA OBTENER EL TOKEN SEGUN LA FRASE", cierreCajaDialog.getToken());	
				} else if(! tokenEscrito.matches("[0-9]{6}")){
					throw new ValidacionCamposException("EL TOKEN DEBE SER 6 DIGITOS", cierreCajaDialog.getToken());	
				} else {
					GeneradorDeToken gt=new GeneradorDeToken();
					String tokenObtenido = gt.getToken(fraseGenerada);
					if(!gt.isValid(fraseGenerada, tokenEscrito)) {
						logger.debug("validate:tokenObtenido="+tokenObtenido+", tokenEscrito="+tokenEscrito);
						throw new ValidacionCamposException("EL TOKEN ES INVALIDO", cierreCajaDialog.getToken());	
					}
				}
			}
		}
	}

	private void 	saldoFinal_focusLost() {
		logger.info("[USER]->saldoFinal_focusLost():saldoFinal="+cierreCajaDialog.getSaldoFinal().getText());
		if(validateAll()) {
			cierreCajaDialog.getAceptar().setEnabled(true);
		} else {
			cierreCajaDialog.getAceptar().setEnabled(false);
		}
	}

	public boolean isCierreCorrecto() {
		return cierreCorrecto;
	}

	private ComboBoxModel getAdministradores() {
		List<U> usuarioList = MemoryDAO.getPaqueteSinc().getUsuarioList();	
		logger.debug("getAdministradores:"+usuarioList);
		Vector<U> v= new Vector<U>();
		U seleccioneUsuario = new U();
		seleccioneUsuario.setE(null);
		seleccioneUsuario.setN("-- SELECCIONE --");
		v.add(seleccioneUsuario);
		for(U u:usuarioList){
			logger.debug("getAdministradores:\t->U:"+u+", ADMIN?"+u.getPlaysAsAdmin()+", Perfiles:"+u.getPerfiles());
			if(u.getPlaysAsAdmin()) {
				v.add(u);
			}
		}
		
		ComboBoxModel m =  new DefaultComboBoxModel(v);
		
		return m;
	}
	
	
}
