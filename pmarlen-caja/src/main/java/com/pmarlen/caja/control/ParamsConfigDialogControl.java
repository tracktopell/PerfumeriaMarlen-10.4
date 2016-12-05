/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pmarlen.caja.control;

import com.pmarlen.caja.dao.MemoryDAO;
import com.pmarlen.caja.model.Caja;
import com.pmarlen.caja.model.Sucursal;
import com.pmarlen.caja.view.ParamsConfigDialog;
import com.pmarlen.rest.dto.CorteCajaDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

/**
 *
 * @author alfredo
 */
public class ParamsConfigDialogControl implements ActionListener{
	private Logger logger = Logger.getLogger(ParamsConfigDialogControl.class.getName());
	private boolean configuring=false;
	private boolean paramatersConfigured=false;
	private ParamsConfigDialog dlg;	
	
	public ParamsConfigDialogControl() {
		logger.debug("ParamsConfigDialogControl()");
		this.dlg = new ParamsConfigDialog();
		dlg.getGuardar().addActionListener(this);
		dlg.getCancelar().addActionListener(this);		
	}

	public ParamsConfigDialogControl(ParamsConfigDialog dlg) {
		logger.debug("ParamsConfigDialogControl(ParamsConfigDialog)");
		this.dlg = dlg;
		dlg.getGuardar().addActionListener(this);
		dlg.getCancelar().addActionListener(this);		
	}
	
	
	
	public void estadoInicial(){
		configuring = true;
		logger.debug("estadoInicial:configuring="+configuring);
		this.dlg.getServer().setText(MemoryDAO.getProperties().getProperty("host").trim());
		this.dlg.getPort().setText(MemoryDAO.getProperties().getProperty("port").trim());
		this.dlg.getDropBoxDir().setText(MemoryDAO.getProperties().getProperty("dropboxdir"));
		this.dlg.getSucursal().setSelectedItem(new Sucursal(Integer.parseInt(MemoryDAO.getProperties().getProperty("sucursal"))));
		this.dlg.getCaja().setSelectedItem    (new Caja    (Integer.parseInt(MemoryDAO.getProperties().getProperty("caja"))));
		this.dlg.setVisible(true);
	}
	public boolean isConfiguring() {
			return configuring;
	}

	public boolean getParamatersConfigured() {
		return paramatersConfigured;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == dlg.getGuardar()) {
			guardar_ActionPerformed();
		} else if (e.getSource() == dlg.getCancelar()) {
			cancelar_ActionPerformed();
		} else {
		
		}
	}
	boolean requestResetAction = false;
	private void guardar_ActionPerformed(){
		logger.info("[USER]->guardar_ActionPerformed");
		CorteCajaDTO cc = MemoryDAO.readLastSavedCorteCajaDTO();
		if(	(! MemoryDAO.getProperties().getProperty("sucursal").equals(String.valueOf(((Sucursal)this.dlg.getSucursal().getSelectedItem()).getId())) ||
			 ! MemoryDAO.getProperties().getProperty("sucursal").equals(String.valueOf(((Caja)    this.dlg.getCaja()    .getSelectedItem()).getId())) ) ){
			logger.info("===>> RESET ACTION");
			
			if(MemoryDAO.isInAperturaCorteCajaDTO()){
				JOptionPane.showMessageDialog(dlg, "EXISTE UNA SESION DE DE CAJA INICIADA,\n PRIMERO DEBE CERRAR CAJA PARA CAMBIAR SUCURSAL/CAJA", "GUARDAD", JOptionPane.WARNING_MESSAGE);
			} else {
				requestResetAction = true;
			}
		}		
		
		MemoryDAO.getProperties().setProperty("host",this.dlg.getServer().getText().trim());
		MemoryDAO.getProperties().setProperty("port",this.dlg.getPort().getText().trim());
		MemoryDAO.getProperties().setProperty("dropboxdir",this.dlg.getDropBoxDir().getText().trim());
		MemoryDAO.getProperties().setProperty("sucursal",String.valueOf(((Sucursal)this.dlg.getSucursal().getSelectedItem()).getId()));
		MemoryDAO.getProperties().setProperty("caja"    ,String.valueOf(((Caja)    this.dlg.getCaja()    .getSelectedItem()).getId()));
		
		MemoryDAO.persistPRoperties();
		
		this.dlg.dispose();
		configuring = false;
		paramatersConfigured = true;
		this.dlg = null;
		starResetAction();
	}
	
	private void starResetAction(){
		new Thread(){
			@Override
			public void run() {
				resetAction();
			}			
		}.start();
	}
	
	private void resetAction(){
		int resp=JOptionPane.showConfirmDialog(dlg, "¿ESTA SEGURO DE REINCIAR DATOS PARA LA NUEVA SUC/CAJA ?", "REINCIAR", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if(resp == JOptionPane.YES_OPTION){
			MemoryDAO.stopPooling();
			File fileRequestReset = new File("RESET.do");
			try {
				fileRequestReset.createNewFile();				
			} catch(IOException ioe){
				logger.error("No se puede crear RESET.do",ioe);
			}
			MemoryDAO.resuqestResetAll();
			System.exit(5);
		}
	}
	
	private void cancelar_ActionPerformed(){
		logger.info("[USER]->cancelar_ActionPerformed");
		this.dlg.dispose();
		configuring = false;		
	}
}