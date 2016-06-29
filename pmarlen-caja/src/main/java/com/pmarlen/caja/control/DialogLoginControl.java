package com.pmarlen.caja.control;

import com.pmarlen.backend.model.Usuario;
import com.pmarlen.caja.dao.MemoryDAO;
import com.pmarlen.caja.view.DialogLogin;
import com.pmarlen.model.Constants;
import com.pmarlen.rest.dto.CorteCajaDTO;
import com.pmarlen.rest.dto.U;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JOptionPane;
import javax.swing.plaf.FontUIResource;
import org.apache.log4j.Logger;

/**
 *
 * @author tracktopell
 */
public class DialogLoginControl implements ActionListener , FocusListener{
	public static final int MAX_INTENTOS = 3;
	private static final String DOMAIN_NAME="@perfumeriamarlen.com.mx";
	
	private Logger logger = Logger.getLogger(DialogLoginControl.class.getName());
	private DialogLogin dialogLogin;
	private boolean leggedIn;

	private int intentos;
	private U logged;
	
	public DialogLoginControl(DialogLogin dialogLogin) {
		this.dialogLogin = dialogLogin;
		this.dialogLogin.getEmail()   .addFocusListener(this);
		this.dialogLogin.getEmail()   .addActionListener(this);
		this.dialogLogin.getAceptar() .addActionListener(this);
		this.dialogLogin.getPassword().addActionListener(this);
	}

	public void estadoInicial(){
		logger.debug("estadoInicial():setVisible(true) --------------------------------------[    V E N T A N A     V I S I B L E ]-----------------------------------");
		dialogLogin.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == dialogLogin.getAceptar()) {
			aceptar_ActionPerformed();
		} else if (e.getSource() == dialogLogin.getEmail()) {
			aceptar_ActionPerformed();
		} else if (e.getSource() == dialogLogin.getPassword()) {
			aceptar_ActionPerformed();
		}		
	}
	
	private void aceptar_ActionPerformed(){
		logger.info("[USER]->aceptar_ActionPerformed()");
		if(!validate()){			
			javax.swing.UIManager.put("OptionPane.font", new FontUIResource(new java.awt.Font("Tahoma", 0, 24))); 			
			javax.swing.UIManager.put("JOptionPane.font", new FontUIResource(new java.awt.Font("Tahoma", 0, 24))); 			
			JOptionPane.showMessageDialog(dialogLogin, "Complete los campos por favor", "ENTRAR", JOptionPane.WARNING_MESSAGE);
			dialogLogin.getEmail().requestFocus();
			
			return;
		}
		if(MemoryDAO.getPaqueteSinc()==null){
			JOptionPane.showMessageDialog(dialogLogin, "NO SE HA PODIDO CONECTAR LA 1RA VEZ PARA DESCARGAR DATOS\nVERIFIQUE LA CONECCIÓN A INTERNET Y AL SERVIDOR", "ENTRAR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(!autheticate()){
			dialogLogin.getPassword().setText("");
			javax.swing.UIManager.put("OptionPane.font", new FontUIResource(new java.awt.Font("Tahoma", 0, 24))); 			
			javax.swing.UIManager.put("JOptionPane.font", new FontUIResource(new java.awt.Font("Tahoma", 0, 24))); 			
			JOptionPane.showMessageDialog(dialogLogin, "Correo/Contraseña incorrecta", "ENTRAR", JOptionPane.ERROR_MESSAGE);
			dialogLogin.getPassword().requestFocus();
			intentos++;
			if(intentos>=3){
				leggedIn = false;
				dialogLogin.dispose();
			}
		} else {
			leggedIn = true;
			
			ApplicationLogic.getInstance().setLogged(logged);
			dialogLogin.dispose();
		}
	}
	
	private boolean validate(){
		
		String passwordValue = new String(dialogLogin.getPassword().getPassword());
		if(passwordValue.trim().length()==0 || dialogLogin.getEmail().getText().trim().length()==0){
			return false;
		}
		return 	true;	
		
	}
	
	private boolean autheticate(){
		
		String passwordValue = new String(dialogLogin.getPassword().getPassword());
		if(passwordValue.trim().length()<1){
			return false;
		}
		logged = ApplicationLogic.getInstance().checkForUser(dialogLogin.getEmail().getText(),passwordValue);
		if(logged != null){
			CorteCajaDTO lastSavedCC = ApplicationLogic.getInstance().getLastSavedCC();
			
			ApplicationLogic.getInstance().getCorteCajaDTO().setCaja(MemoryDAO.getNumCaja());
			ApplicationLogic.getInstance().getCorteCajaDTO().setSucursalId(MemoryDAO.getSucursalId());
			ApplicationLogic.getInstance().getCorteCajaDTO().setFecha(System.currentTimeMillis());	
			ApplicationLogic.getInstance().getCorteCajaDTO().setUsuarioEmail(logged.getE());
			
			if(lastSavedCC == null ) {
				ApplicationLogic.getInstance().getCorteCajaDTO().setTipoEvento(Constants.TIPO_EVENTO_AUTENTICADO);
			} else {
//				if(lastSavedCC.getTipoEvento() == Constants.TIPO_EVENTO_APERTURA){
//					ApplicationLogic.getInstance().getCorteCajaDTO().setTipoEvento(Constants.TIPO_EVENTO_APERTURA);
//					ApplicationLogic.getInstance().getCorteCajaDTO().setSaldoInicial(lastSavedCC.getSaldoInicial());
//					logger.debug("autheticate: SE INICIO , RECUPERANDO DE SESION ABIERTA ("+lastSavedCC.getUsuarioEmail()+") y Entro:"+logged.getE());
//				} else {
					ApplicationLogic.getInstance().getCorteCajaDTO().setTipoEvento(Constants.TIPO_EVENTO_AUTENTICADO);
					logger.debug("autheticate: SE AUTENTICO");
//					logger.debug("autheticate: SE INICIO , PERO NUNCA SE AUNTENTICO ANTERIORMENTE");
//				}
			}
			
			MemoryDAO.saveCorteCajaDTO(ApplicationLogic.getInstance().getCorteCajaDTO());
			MemoryDAO.backupCorteCajaDTO(ApplicationLogic.getInstance().getCorteCajaDTO());
			
			return 	true;	
		}else{
			return false;
		}
		
		
	}

	public boolean isLoggedIn() {
		return logged != null;
	}

	public U getLogged() {
		return logged;
	}
	
	@Override
	public void focusGained(FocusEvent fe) {
		
	}

	@Override
	public void focusLost(FocusEvent fe) {
		if (fe.getSource() == dialogLogin.getEmail()) {
			email_FocusLost();
		}
	}
	
	
	private void email_FocusLost(){
		String emailValue = dialogLogin.getEmail().getText().trim();
		if(emailValue.trim().length()>2 && !emailValue.contains(DOMAIN_NAME)){
			dialogLogin.getEmail().setText(emailValue+DOMAIN_NAME);
		}
	}
	
	public void setFontBigest() {
		dialogLogin.setFont(new java.awt.Font("Tahoma", 0, 24));
	}

	public DialogLogin getDialogLogin() {
		return dialogLogin;
	}

}
