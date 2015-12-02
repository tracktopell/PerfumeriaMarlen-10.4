/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.caja.control;

import com.pmarlen.caja.dao.MemoryDAO;
import com.pmarlen.caja.model.Notificacion;
import com.pmarlen.caja.view.AperturaCajaJFrame;
import com.pmarlen.caja.view.CierreCajaJFrame;
import com.pmarlen.caja.view.DialogConfiguracionBTImpresora;
import com.pmarlen.caja.view.FramePrincipal;
import com.pmarlen.caja.view.PanelVenta;
import com.pmarlen.caja.view.PanelVentas;
import com.pmarlen.model.Constants;
import com.pmarlen.rest.dto.CorteCajaDTO;
import com.pmarlen.rest.dto.U;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

/**
 *
 * @author alfredo
 */
public class FramePrincipalControl implements ActionListener{
	private static Logger logger = Logger.getLogger(FramePrincipalControl.class.getName());
	private FramePrincipal    framePrincipal;
	private PanelVentaControl panelVentaControl;
	private PanelVentasControl panelVentasControl;
	
	private static FramePrincipalControl instance;
	/**
	 * @return the instance
	 */
	public static FramePrincipalControl getInstance() {
		if(instance == null){
			instance = new FramePrincipalControl();
		}
		return instance;
	}
	
	private FramePrincipalControl() {
		
		framePrincipal = new FramePrincipal();
		
		framePrincipal.setTitle("PML30-CAJA ("+ApplicationLogic.getInstance().getVersion()+")");
		
		framePrincipal.getSesionMenu().addActionListener(this);
		
		framePrincipal.getAbrirSesion().addActionListener(this);
		
		framePrincipal.getCerrarSesion().addActionListener(this);
		
		panelVentaControl  = new PanelVentaControl ((PanelVenta)framePrincipal.getPanelVenta());
		
		panelVentasControl = new PanelVentasControl((PanelVentas)framePrincipal.getPanelVentas()) ;

		framePrincipal.getProductosMenu().addActionListener(this);
		
		framePrincipal.getVentasMenu().addActionListener(this);
		
		framePrincipal.getVentaModoMenu().addActionListener(this);
		
		framePrincipal.getSalirMenu().addActionListener(this);
		
		//---------------------------------------------------
		
		framePrincipal.getVentaActualMenu().addActionListener(this);
		
		framePrincipal.getVentaLineaMenu().addActionListener(this);
		
		framePrincipal.getVentaOportunidadMenu().addActionListener(this);
		
		framePrincipal.getVentaRegaliasMenu().addActionListener(this);
		
		framePrincipal.getVentaTerminarMenu().addActionListener(this);
		
		framePrincipal.getVentaeliminarProdMenu().addActionListener(this);
		
		framePrincipal.getVentaCancelarMenu().addActionListener(this);
		//---------------------------------------------------
		
		framePrincipal.getNotificacionesMenu().addActionListener(this);
		
		framePrincipal.getNegocioConfigMenu().addActionListener(this);
		
		//---------------------------------------------------
		framePrincipal.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				//logger.debug("-->>new Size:"+framePrincipal.getSize());
				int  w=(int)framePrincipal.getSize().getWidth();
				int sw=(70*w)/100;
				framePrincipal.getPanelVenta().getSplitPanel().setDividerLocation(sw);
			}
		});
		
		//---------------------------------------------------
		framePrincipal.setDefaultCloseOperation(framePrincipal.EXIT_ON_CLOSE);
		
		framePrincipal.getConfigMenu().setEnabled(false);
		//---------------------------------------------------
		
		framePrincipal.getNotificaciones().addActionListener(this);
		
	}
	
	public void estadoInicial() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				logger.debug("estadoInicial():START");
				CorteCajaDTO lastSavedCC = ApplicationLogic.getInstance().getLastSavedCC();
				if(lastSavedCC != null && lastSavedCC.getTipoEvento() == Constants.TIPO_EVENTO_APERTURA){
					abrirSesionNueva();
					final long dt = System.currentTimeMillis() - lastSavedCC.getFecha();					
					logger.debug("estadoInicial(): APERTURA DE CAJA YA INICIADA: DIFF lastSavedCC:"+Constants.getDiff(dt)+" ( "+System.currentTimeMillis()+"-"+lastSavedCC.getFecha()+" = "+dt+ ")");
				} else {
					((CardLayout)framePrincipal.getPanels().getLayout()).show(framePrincipal.getPanels(), "panelSesion");
					logger.debug("estadoInicial(): NUEVA SESION");
				}
				logger.debug("estadoInicial():setVisible(true) --------------------------------------[    V E N T A N A     V I S I B L E ]-----------------------------------");
				framePrincipal.setVisible(true);
				logger.debug("estadoInicial():updateStatusWest()");
				updateStatusWest();
				framePrincipal.setExtendedState( framePrincipal.getExtendedState()|JFrame.MAXIMIZED_BOTH );
				panelVentaControl.estadoInicial();
				logger.debug("estadoInicial():END");
			}
		});
	}
	
	private boolean relojRunning=false;
	
	public void iniciaReloj(){
		new Thread(){

			@Override
			public void run() {
				procesoReloj();
			}
			
		}.start();
	}
	
	private void procesoReloj(){
		logger.debug("procesoReloj:");
		relojRunning=true;
		Date fecha;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		while(relojRunning) {
			try{
				logger.trace("procesoReloj:sleep(1000)");
				Thread.sleep(1000);
				String hora=sdf.format(new Date());
				framePrincipal.getStatusCenter().setText(hora);
				logger.trace("procesoReloj:->framePrincipal.updateStatus():MemoryDAO.getSyncPollState()="+MemoryDAO.getSyncPollState());
				framePrincipal.updateStatus();
				
				int nsl=ApplicationLogic.getInstance().getNotificacionesSinLeer();
				boolean nn=ApplicationLogic.getInstance().isNuevaNotificacion();
				framePrincipal.getNotificaciones().setText(String.valueOf(nsl));
				if(nn){
					framePrincipal.getNotificaciones().setForeground(Color.RED);
				} else {
					framePrincipal.getNotificaciones().setForeground(framePrincipal.getForeground());
				}				
			}catch(InterruptedException ie){
				ie.printStackTrace(System.err);
				framePrincipal.getStatusCenter().setText("interrupted :(");
			}
		}
	}

	public FramePrincipal getFramePrincipal() {
		return framePrincipal;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == framePrincipal.getSesionMenu()){
			sesionMenu_actionPerformed();
		} else if(e.getSource() == framePrincipal.getAbrirSesion()){
			abrirSesion_actionPerformed();
		} else if(e.getSource() == framePrincipal.getCerrarSesion()){
			cerrarSesion_actionPerformed();
		} else if(e.getSource() == framePrincipal.getVentasMenu()){
			ventasMenu_actionPerformed();
		} else if(e.getSource() == framePrincipal.getVentaLineaMenu()){
			ventaLineaMenu_actionPerformed();
		} else if(e.getSource() == framePrincipal.getVentaOportunidadMenu()){
			ventaOportunidadMenu_actionPerformed();
		} else if(e.getSource() == framePrincipal.getVentaRegaliasMenu()){
			ventaRegaliasMenu_actionPerformed();
		} else if(e.getSource() == framePrincipal.getSalirMenu()){
			salirMenu_actionPerformed();
		} else if(e.getSource() == framePrincipal.getVentaActualMenu()){
			ventaActualMenu_actionPerformed();
		} else if(e.getSource() == framePrincipal.getVentaModoMenu()){
			ventaModoMenu_actionPerformed();
		} else if(e.getSource() == framePrincipal.getVentaTerminarMenu()){
			ventaTerminar_actionPerformed();
		} else if(e.getSource() == framePrincipal.getVentaeliminarProdMenu()){
			ventaeliminarProdMenu_actionPerformed();
		} else if(e.getSource() == framePrincipal.getVentaCancelarMenu()){
			ventaCancelarMenu_actionPerformed();
		} else if(e.getSource() == framePrincipal.getNotificaciones() || e.getSource() == framePrincipal.getNotificacionesMenu()){
			notificaciones_actionPerformed();
		} else {
		}
		
	}

	AperturaCajaJFrame  acDlg = null;
	AperturaCajaControl acc   = null;
	
	private void sesionMenu_actionPerformed(){
		((CardLayout)framePrincipal.getPanels().getLayout()).show(framePrincipal.getPanels(), "panelSesion");
	}
	
	private void abrirSesion_actionPerformed() {
		logger.info("[USER]->abrirSesion_actionPerformed():");
		acDlg = new AperturaCajaJFrame(framePrincipal);
		acc =  new AperturaCajaControl(acDlg);
		new Thread(){
			public void run(){
				acc.estadoInicial();
				if(acc.isAperturaCorrecta()){
					abrirSesionNueva();
					acDlg = null;
					acc   = null;
				}
			}
		}.start();
	}
	
	private void abrirSesionNueva(){
		framePrincipal.enableMinimalComponents();
		ventaActualMenu_actionPerformed();
	}

	private void cerrarSesion_actionPerformed() {
		//JOptionPane.showMessageDialog(framePrincipal, "NO IMPLEMENTADO", "CERRAR", JOptionPane.ERROR_MESSAGE);
		CierreCajaJFrame cierreCajaDialog = new CierreCajaJFrame(framePrincipal);
		CierreCajaControl cierreCajaControl = new CierreCajaControl(cierreCajaDialog);
		cierreCajaControl.estadoInicial();
		if(cierreCajaControl.isCierreCorrecto()){
			framePrincipal.dispose();
			
			while(MemoryDAO.isEnviandoCierreCaja()) {
				try {
					Thread.sleep(1000);
					logger.debug("ESPERANDO Envio de Cierre de Caja");
				}catch(InterruptedException ie){
					
				}
			}
			
			if(MemoryDAO.isEnviandoCierreCorrectmente()) {
				logger.debug("Envio correcto, iniciaAppCorteCajaDTO, cerrando");
				ApplicationLogic.getInstance().iniciaAppCorteCajaDTO();
			} else {
				logger.debug("Envio de Cierre no se envio, :(");
			}
			
			System.exit(0);
		} else {
		
		}
	}
	
	private void ventaeliminarProdMenu_actionPerformed() {
		panelVentaControl.ventaeliminarProdMenu();
	}

	private void ventaCancelarMenu_actionPerformed() {
		panelVentaControl.cancelar_ActionPerformed();
	}

	private void ventasMenu_actionPerformed() {
		panelVentasControl.estadoInicial();
		((CardLayout)framePrincipal.getPanels().getLayout()).show(framePrincipal.getPanels(), "panelVentas");
	}
	
	private void ventaLineaMenu_actionPerformed() {
		panelVentaControl.ventaLinea();
	}
	
	private void ventaOportunidadMenu_actionPerformed() {
		panelVentaControl.ventaOportunidad();
	}
	
	private void ventaRegaliasMenu_actionPerformed() {
		panelVentaControl.ventaRegalias();
	}

	private void salirMenu_actionPerformed() {
		framePrincipal.dispose();
		System.exit(0);
	}

	private void ventaActualMenu_actionPerformed() {
		((CardLayout)framePrincipal.getPanels().getLayout()).show(framePrincipal.getPanels(), "panelVenta");
		panelVentaControl.verVentaActual();
	}
	
	private void ventaModoMenu_actionPerformed() {
		((CardLayout)framePrincipal.getPanels().getLayout()).show(framePrincipal.getPanels(), "panelVenta");
		panelVentaControl.checar_ActionPerformed();
	}

	private void ventaTerminar_actionPerformed() {
		((CardLayout)framePrincipal.getPanels().getLayout()).show(framePrincipal.getPanels(), "panelVenta");
		panelVentaControl.terminar_ActionPerformed();
	}
		
	private void impresoraBTMenu_actionPerformed() {
		DialogConfiguracionBTImpresoraControl.getInstance(framePrincipal).estadoInicial();		
	}
	
	public void enableAndDisableAdminControls() {
		U u = ApplicationLogic.getInstance().getLogged();
		if(u.getPlaysAsAdmin()) {
			eanbleAdminControls();
		}
		final List<String> pl = u.getPerfiles();
		logger.debug("enableAndDisableAdminControls:");
		for(String  p: pl){
			logger.debug("enableAndDisableAdminControls:\t->Perfil:"+p);
		}
		logger.debug("enableAndDisableAdminControls:CorteCajaDTO:"+ApplicationLogic.getInstance().getCorteCajaDTO());		
	}
	
	private void eanbleAdminControls(){
		logger.debug("eanbleAdminControls():");
		framePrincipal.getConfigMenu().setEnabled(true);
	}
	
	public void setEnabledVentasMenus(boolean e){		
		framePrincipal.getVentaTerminarMenu().setEnabled(e);
		framePrincipal.getVentaeliminarProdMenu().setEnabled(e);		
		framePrincipal.getVentaCancelarMenu().setEnabled(e);
	}
	
	public void setFontBigest() {
		framePrincipal.setFont(new java.awt.Font("Tahoma", 0, 24));
	}
		
	public void updateStatusWest() {		
		U logged = ApplicationLogic.getInstance().getLogged();
		if( logged != null) {
			framePrincipal.getStatusWest().setText(MemoryDAO.getCajaGlobalInfo()+"/"+logged.getE());
		} else {
			framePrincipal.getStatusWest().setText(MemoryDAO.getCajaGlobalInfo());
		}		
	}
	
	private void notificaciones_actionPerformed(){
		Iterator<Notificacion> notificaciones = ApplicationLogic.getInstance().getNotificaciones();
		StringBuilder sb=new StringBuilder("");
		int nc=0;
		while(notificaciones.hasNext()){
			Notificacion nt = notificaciones.next();			
			sb.append(nt.toString()).append("\n");
			nt.setVista(true);
			nc++;
		}
		if(nc==0){
			JOptionPane.showMessageDialog(framePrincipal, "Na hay notificaciones.", "Notificaciones", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(framePrincipal, sb.toString(), "Notificaciones", JOptionPane.WARNING_MESSAGE);
		}
	}
}
