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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	private void guardar_ActionPerformed(){
		logger.info("[USER]->guardar_ActionPerformed");
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
	}
	
	private void cancelar_ActionPerformed(){
		logger.info("[USER]->cancelar_ActionPerformed");
		this.dlg.dispose();
		configuring = false;		
	}	
}