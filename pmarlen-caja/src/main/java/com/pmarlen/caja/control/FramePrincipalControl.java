/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.caja.control;

import com.pmarlen.caja.dao.MemoryDAO;
import com.pmarlen.caja.model.Notificacion;
import com.pmarlen.caja.model.SyncUpdateListener;
import com.pmarlen.caja.model.VentaSesion;
import com.pmarlen.caja.view.AperturaCajaJFrame;
import com.pmarlen.caja.view.CierreCajaJFrame;

import com.pmarlen.caja.view.FramePrincipal;
import com.pmarlen.caja.view.PanelDevolucion;
import com.pmarlen.caja.view.PanelVenta;
import com.pmarlen.caja.view.PanelVentas;
import com.pmarlen.caja.view.ParamsConfigDialog;
import com.pmarlen.model.Constants;
import com.pmarlen.rest.dto.CorteCajaDTO;
import com.pmarlen.rest.dto.U;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Desktop;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author alfredo
 */
public class FramePrincipalControl implements ActionListener,SyncUpdateListener,WindowListener{
	private static Logger logger = Logger.getLogger(FramePrincipalControl.class.getName());
	private FramePrincipal    framePrincipal;
	private PanelVentaControl panelVentaControl;
	private PanelVentasControl panelVentasControl;
	private PanelDevolucionControl panelDevolucionControl;
	boolean coloridoNotificacion=true;
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
		panelVentasControl = new PanelVentasControl((PanelVentas)framePrincipal.getPanelVentas());
		panelDevolucionControl = new PanelDevolucionControl((PanelDevolucion)framePrincipal.getPanelDevolucion());		
		framePrincipal.getVentasMenu().addActionListener(this);
		framePrincipal.getVentaModoMenu().addActionListener(this);
		framePrincipal.getSalirMenu().addActionListener(this);
		//---------------------------------------------------		
		framePrincipal.getPreferenciasMenu().addActionListener(this);
		framePrincipal.getVentaActualMenu().addActionListener(this);
		framePrincipal.getVentaLineaMenu().addActionListener(this);
		framePrincipal.getVentaOportunidadMenu().addActionListener(this);
		framePrincipal.getVentaRegaliasMenu().addActionListener(this);
		framePrincipal.getVentaTerminarMenu().addActionListener(this);
		framePrincipal.getVentaeliminarProdMenu().addActionListener(this);
		framePrincipal.getVentaCancelarMenu().addActionListener(this);
		//---------------------------------------------------
		framePrincipal.getNuevaDevolMenu().addActionListener(this);
		framePrincipal.getTerminarDevolMenu().addActionListener(this);
		framePrincipal.getCancelarDevolMenu().addActionListener(this);
        //---------------------------------------------------
        framePrincipal.getLogDEBUG().addActionListener(this);
		framePrincipal.getLogINFO().addActionListener(this);
        framePrincipal.getLogTRACE().addActionListener(this);
		//---------------------------------------------------
		framePrincipal.getNotificacionesMenu().addActionListener(this);
        //---------------------------------------------------
        framePrincipal.getPortalMenu().addActionListener(this);
        framePrincipal.getManualMenu().addActionListener(this);
        framePrincipal.getEmailSoporteMenu().addActionListener(this);
        framePrincipal.getAcercaDeMenu().addActionListener(this);
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
		framePrincipal.setDefaultCloseOperation(framePrincipal.DO_NOTHING_ON_CLOSE);
		framePrincipal.addWindowListener(this);
		framePrincipal.getConfigMenu().setEnabled(false);
		//---------------------------------------------------
		framePrincipal.getNotificaciones().addActionListener(this);
		framePrincipal.getCerrando().setVisible(false);
	}
    
    private void renderHistoricoSesiones(){
        TreeSet<CorteCajaDTO> hs= MemoryDAO.readHistoricoCorteCaja();
        Object[] columnNames=new String[]{"FECHA","EVENTO","USUARIO","SALDO INICIAL","SALDO FINAL","COMENTARIOS"};
        Object[][] data = new Object[hs.size()][];
        int rhs=0;
        for(CorteCajaDTO cc: hs){
            data[rhs++] = cc.toStringArray();
        }        
        framePrincipal.getCorteCajaTable().setModel(new DefaultTableModel(data, columnNames));
        framePrincipal.getCorteCajaTable().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    }
    
	private static int nei=0;
	public void estadoInicial() {
		logger.debug("estadoInicial("+(nei++)+")");
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				logger.debug("\testadoInicial():START");
				CorteCajaDTO lastAperturaCC = MemoryDAO.readLastSavedCorteCajaDTOApertura();                
                CorteCajaDTO lastCC         = MemoryDAO.readLastSavedCorteCajaDTO();
                CorteCajaDTO firstCC        = MemoryDAO.readFirstSavedCorteCajaDTOIniciada();
                if(lastAperturaCC == null && lastCC != null){
                    logger.debug("\testadoInicial(): ================ CAJA NUNCA SE CERRO ==============");
                    logger.debug("\testadoInicial(): \tFIX: firstCC="+firstCC);
                    CorteCajaDTO cierreCC   = new CorteCajaDTO(firstCC);
                    cierreCC.setFecha(firstCC.getFecha()-500000);
                    cierreCC.setTipoEvento(Constants.TIPO_EVENTO_CIERRE);
                    MemoryDAO.updateCorteCajaDTO(cierreCC);
                    logger.debug("\testadoInicial(): \tFIX: CIERRE GUARDADO: cierreCC="+cierreCC);
                    
                    CorteCajaDTO nuevoInicioCC   = new CorteCajaDTO(firstCC);
                    nuevoInicioCC.setFecha(firstCC.getFecha()-400000);
                    nuevoInicioCC.setTipoEvento(Constants.TIPO_EVENTO_AP_INICIADA);
                    MemoryDAO.updateCorteCajaDTO(nuevoInicioCC);
                    logger.debug("\testadoInicial(): \tFIX: NUEVO INICIO GUARDADO: nuevoInicioCC="+nuevoInicioCC);
                    
                    CorteCajaDTO aperturaCC = new CorteCajaDTO(firstCC);
                    aperturaCC.setFecha(firstCC.getFecha()-300000);
                    aperturaCC.setSaldoInicial(0.0);
                    aperturaCC.setTipoEvento(Constants.TIPO_EVENTO_APERTURA);
                    MemoryDAO.updateCorteCajaDTO(aperturaCC);
                    logger.debug("\testadoInicial(): \tFIX: APERTURA GUARDADA: aperturaCC="+aperturaCC);
                    
                    lastAperturaCC = MemoryDAO.readLastSavedCorteCajaDTOApertura();
                    logger.debug("\testadoInicial(): \tFIX: (first)lastAperturaCC="+lastAperturaCC);
                    lastCC         = MemoryDAO.readLastSavedCorteCajaDTO();
                    logger.debug("\testadoInicial(): \tFIX: NORMAL: lastCC="+lastCC);
                    logger.debug("\testadoInicial(): \tFIX: ===========================================");
                }
                CorteCajaDTO lastCierreCC   = MemoryDAO.readLastSavedCorteCajaDTOCierre();
                logger.debug("\testadoInicial():lastAperturaCC="+lastAperturaCC);
                logger.debug("\testadoInicial():lastCierreCC  ="+lastCierreCC);
                logger.debug("\testadoInicial():lastCC        ="+lastCC);
                
                boolean sesionSigueAbierta = false;
                if(lastCierreCC!=null && lastCierreCC.getFecha()<lastAperturaCC.getFecha()){
                    sesionSigueAbierta = true;
                }
                logger.debug("\testadoInicial():sesionSigueAbierta ="+sesionSigueAbierta);
                
				if(lastAperturaCC!=null){
                    if( ! sesionSigueAbierta ){
                        sesionMenu_actionPerformed();
                        logger.debug("\testadoInicial(): SE DEBE ABRIR UNA NUEVA SESION");
                    } else {
                        abrirSesionNueva();					
                        logger.debug("\testadoInicial(): APERTURA DE CAJA YA INICIADA: LISTO PARA OPERAR");
                    }
                } else {
                    logger.debug("\testadoInicial(): NO hay apertura Valida");
                    System.exit(10);
                }
				logger.debug("\testadoInicial():setVisible(true) --------------------------------------[    V E N T A N A     V I S I B L E ]-----------------------------------");
				framePrincipal.setVisible(true);
				logger.debug("\testadoInicial():updateStatusWest()");
				updateStatusWest();
				framePrincipal.setExtendedState( framePrincipal.getExtendedState()|JFrame.MAXIMIZED_BOTH );
				panelVentaControl.estadoInicial();
                framePrincipal.getNivelLog().clearSelection();
                if(LogManager.getRootLogger().getLevel() == Level.INFO){
                    framePrincipal.getLogINFO().setSelected(true);
                } else if(LogManager.getRootLogger().getLevel() == Level.DEBUG){
                    framePrincipal.getLogDEBUG().setSelected(true);
                } else if(LogManager.getRootLogger().getLevel() == Level.TRACE){
                    framePrincipal.getLogTRACE().setSelected(true);
                }

				logger.debug("\testadoInicial():END");
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
		final Color backgroundC = framePrincipal.getNotificaciones().getBackground();
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
					coloridoNotificacion = !coloridoNotificacion;
					if(coloridoNotificacion){
						//framePrincipal.getNotificaciones().setForeground(Color.RED);
						framePrincipal.getNotificaciones().setBackground(Color.RED);
					}else{
						//framePrincipal.getNotificaciones().setForeground(backgroundC);
						framePrincipal.getNotificaciones().setBackground(backgroundC);
					}
				} else {
					framePrincipal.getNotificaciones().setBackground(backgroundC);					
				}				
				framePrincipal.getNotificaciones().updateUI();
			}catch(InterruptedException ie){
				ie.printStackTrace(System.err);
				framePrincipal.getStatusCenter().setText(":(");
				framePrincipal.getNotificaciones().setBackground(backgroundC);
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
		} else if(e.getSource() == framePrincipal.getPreferenciasMenu()){
			preferenciasMenu_actionPerformed();
		} else if(e.getSource() == framePrincipal.getNuevaDevolMenu()){
			nuevaDevolMenu_actionPerformed();
		} else if(e.getSource() == framePrincipal.getTerminarDevolMenu()){
			terminarDevolMenu_actionPerformed();
		} else if(e.getSource() == framePrincipal.getCancelarDevolMenu()){
			cancelarDevolMenu_actionPerformed();
		} else if(e.getSource() == framePrincipal.getLogINFO()){
			ApplicationLogic.setLogLevelToINFO();
		} else if(e.getSource() == framePrincipal.getLogDEBUG()){
			ApplicationLogic.setLogLevelToDEBUG();
		} else if(e.getSource() == framePrincipal.getLogTRACE()){
			ApplicationLogic.setLogLevelToTRACE();
		} else if(e.getSource() == framePrincipal.getPortalMenu()){
			portalMenu_actionPerformed();
		} else if(e.getSource() == framePrincipal.getManualMenu()){
			manualMenu_actionPerformed();
		} else if(e.getSource() == framePrincipal.getAcercaDeMenu()){
			acercaDeMenu_actionPerformed();
		} else if(e.getSource() == framePrincipal.getEmailSoporteMenu()){
			emailSoporteMenu_actionPerformed();
		} else {
			logger.error("actionPerformed: ActionEvent missing ? e="+e);
		}
	}

	AperturaCajaJFrame  acDlg = null;
	AperturaCajaControl acc   = null;
	
	private void sesionMenu_actionPerformed(){
        new Thread(){
            @Override
            public void run() {
                renderHistoricoSesiones();
            }            
        }.start();
		((CardLayout)framePrincipal.getPanels().getLayout()).show(framePrincipal.getPanels(), "panelSesion");
	}
	
	private void abrirSesion_actionPerformed() {
		logger.info("[USER]->abrirSesion_actionPerformed:");
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
		logger.info("[USER]->cerrarSesion_actionPerformed:");
		
		framePrincipal.getAbrirSesion().setEnabled(false);
		framePrincipal.getCerrarSesion().setEnabled(false);
		
		CierreCajaJFrame cierreCajaDialog = new CierreCajaJFrame(framePrincipal);
		CierreCajaControl cierreCajaControl = new CierreCajaControl(cierreCajaDialog);
		
		framePrincipal.getAbrirSesion().setVisible(false);
		framePrincipal.getCerrarSesion().setVisible(false);		
		
		framePrincipal.getCerrando().setText(" ");		
		framePrincipal.getCerrando().setVisible(true);
		framePrincipal.getCerrando().updateUI();
		
		cierreCajaControl.estadoInicial();
		
		framePrincipal.getCerrando().setText("...ENVIANDO, ESPERE");		
		framePrincipal.getCerrando().setVisible(true);
		framePrincipal.getCerrando().updateUI();
		
		if(cierreCajaControl.isCierreCorrecto()){
			cierreCajaDialog.dispose();	
			java.awt.EventQueue.invokeLater(new Runnable() {
				public void run() {
					esperarAEnviar();
				}
			});			
		} else {
			framePrincipal.getCerrando().setText(" ");		
			framePrincipal.getCerrando().setVisible(false);
			framePrincipal.getCerrando().updateUI();
			
			framePrincipal.getCerrarSesion().setVisible(true);		
			framePrincipal.getCerrarSesion().setEnabled(true);		
		}
	}

	private void esperarAEnviar() throws HeadlessException {
		
		framePrincipal.getAbrirSesion().setVisible(false);
		
		framePrincipal.getCerrarSesion().setEnabled(false);
		
		framePrincipal.getCerrando().setText("CERRANDO SESIÓN EN SERVIDOR, ESPERE ...");
		
		framePrincipal.getCerrando().setVisible(true);
		
		int intentosEnviar=240;
		int numInt=0;
		for(numInt=0;MemoryDAO.isEnviandoCierreCaja()&& numInt<intentosEnviar;numInt++) {
			try {
				Thread.sleep(250);
				logger.debug("ESPERANDO["+numInt+"] Esperando respuesta de Envio de Cierre de Caja");
			}catch(InterruptedException ie){
				
			}
		}
		
		if(MemoryDAO.isEnviandoCierreCorrectmente()) {
			logger.debug("Envio correcto, iniciaAppCorteCajaDTO, cerrando");
			
			framePrincipal.dispose();
			System.exit(0);
			
		} else {
			logger.debug("Envio de Cierre no se envio, :(");
			
			JOptionPane.showMessageDialog(framePrincipal, "No se pudo enviar al Servidor, pero la Aplicación se cerrará", "CERRAR SESIÓN", JOptionPane.INFORMATION_MESSAGE);
			
			framePrincipal.dispose();
			System.exit(1);
		}
	}
	
	private void ventaeliminarProdMenu_actionPerformed() {		
		panelVentaControl.ventaEliminarProdMenu();
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
		logger.info("[USER]->salirMenu_actionPerformed: ->dispose, exit0");
		controlledExit();
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
		logger.debug("enableAndDisableAdminControls: u:"+u+", u.getPlaysAsAdmin():"+u.getPlaysAsAdmin());
		
		if(u.getPlaysAsAdmin()) {
			eanbleAdminControls();
		}
		final List<String> pl = u.getPerfiles();
		
		for(String  p: pl){
			logger.debug("enableAndDisableAdminControls:\t->Perfil:"+p);
			if(p.equalsIgnoreCase(Constants.PERFIL_ROOT) || p.equalsIgnoreCase(Constants.PERFIL_ADMIN)){
				logger.debug("\tenableAndDisableAdminControls:->Fucking Forced? ("+(!u.getPlaysAsAdmin())+"): enableAndDisableAdminControls:\t->Perfil:"+p);
				eanbleAdminControls();
			}
		}
		logger.debug("enableAndDisableAdminControls:CorteCajaDTO:"+ApplicationLogic.getInstance().getCorteCajaDTO());		
	}
	
	private void eanbleAdminControls(){
		logger.debug("eanbleAdminControls():");
		framePrincipal.getConfigMenu().setEnabled(true);
		framePrincipal.getPreferenciasMenu().setEnabled(true);
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
		logger.info("[USER]->notificaciones_actionPerformed:");
		Iterator<Notificacion> notificaciones = ApplicationLogic.getInstance().getNotificaciones();
		StringBuilder sb=new StringBuilder("");
		int nc=0;
		while(notificaciones.hasNext()){
			Notificacion nt = notificaciones.next();			
			//sb.append(nt.toString()).append("\n");
			sb.append(nt.getMensaje()).append("\n");
			nt.setVista(true);
			nc++;
		}
		logger.debug("notificaciones_actionPerformed:nc="+nc);
		if(nc==0){
			JOptionPane.showMessageDialog(framePrincipal, "Na hay notificaciones.", "Notificaciones", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(framePrincipal, sb.toString(), "Notificaciones", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void coneccionMenu_actionPerformed() {
		logger.info("[USER]->coneccionMenu_actionPerformed:");
	}
	
	private void preferenciasMenu_actionPerformed(){
		logger.info("[USER]->preferenciasMenu_actionPerformed:");
		ParamsConfigDialog dlg =  new ParamsConfigDialog(framePrincipal);
		ParamsConfigDialogControl pcd=new ParamsConfigDialogControl(dlg);
		pcd.estadoInicial();
	}

	private void nuevaDevolMenu_actionPerformed() {
		logger.info("[USER]->nuevaDevolMenu_actionPerformed:");
		((CardLayout)framePrincipal.getPanels().getLayout()).show(framePrincipal.getPanels(), "panelDevolucion");
		if(! panelDevolucionControl.isEstadoChecando()){
			panelDevolucionControl.estadoInicial();
		}
	}
	
	private void terminarDevolMenu_actionPerformed() {
		logger.info("[USER]->terminarDevolMenu_actionPerformed:");
		((CardLayout)framePrincipal.getPanels().getLayout()).show(framePrincipal.getPanels(), "panelDevolucion");
		panelDevolucionControl.terminar_ActionPerformed();
	}
	
	private void cancelarDevolMenu_actionPerformed() {
		logger.info("[USER]->cancelarDevolMenu_actionPerformed:");
		((CardLayout)framePrincipal.getPanels().getLayout()).show(framePrincipal.getPanels(), "panelDevolucion");
		panelDevolucionControl.cancelar_ActionPerformed();		
	}

	@Override
	public void updateSyncInfo() {
		updateStatusWest();
	}
	
	public void updateProhibidaVentOpo(boolean estatus){
		panelVentaControl.abilitarVentaOportunidad(estatus);
	}
	
	public void updateProhibidaVentReg(boolean estatus){
		panelVentaControl.abilitarVentaRegalias(estatus);
	}

	@Override
	public void resetAll() {
		logger.info("resetAll:");
		JOptionPane.showMessageDialog(framePrincipal, "SE REINICIARA TOTALMENTE LA APLICACION DE CAJA", "REINICIAR", JOptionPane.WARNING_MESSAGE);
		MemoryDAO.resetAll();
		System.exit(16);
	}

    private void portalMenu_actionPerformed() {
        if(Desktop.isDesktopSupported()){
            try{
                URI uriPortal = new URI(MemoryDAO.getServerContext());
                Desktop.getDesktop().browse(uriPortal);
            }catch(IOException ioE){
                logger.error("no se pudo abrir URI", ioE);
            }catch(URISyntaxException ioE){
                logger.error("La URI esta mal", ioE);
            }
        } else{
            logger.error("La caracteristica por Java AWT Desktop, no esta habilitada");
            JOptionPane.showMessageDialog(framePrincipal, "La caracteristica por Java AWT Desktop, no esta habilitada","Ir a Portal",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void manualMenu_actionPerformed() {
        if(Desktop.isDesktopSupported()){
            try{
                URI uriPortal = new URI(MemoryDAO.getServerContext());
                Desktop.getDesktop().browse(uriPortal);
            }catch(IOException ioE){
                logger.error("no se pudo abrir URI", ioE);
            }catch(URISyntaxException ioE){
                logger.error("La URI esta mal", ioE);
            }
        } else{
            logger.error("La caracteristica por Java AWT Desktop, no esta habilitada");
            JOptionPane.showMessageDialog(framePrincipal, "La caracteristica por Java AWT Desktop, no esta habilitada","Ir a Manual",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void acercaDeMenu_actionPerformed() {
        JOptionPane.showMessageDialog(framePrincipal, 
                "PML30-CAJA ("+ApplicationLogic.getInstance().getVersion()+")", 
                "...Acerca de",JOptionPane.INFORMATION_MESSAGE);
    }

    private void emailSoporteMenu_actionPerformed() {
        if(Desktop.isDesktopSupported()){
            try{
                URI uriPortal = new URI("mailto:aestrada@perfumeriamarlen.com.mx?subject=Ayuda_desde_PML30-caja&cc=dleon@perfumeriamarlen.com.mx");
                Desktop.getDesktop().mail(uriPortal);
            }catch(IOException ioE){
                logger.error("no se pudo abrir URI", ioE);
            }catch(URISyntaxException ioE){
                logger.error("La URI esta mal", ioE);
            }
        } else{
            logger.error("La caracteristica por Java AWT Desktop, no esta habilitada");
            JOptionPane.showMessageDialog(framePrincipal, "La caracteristica por Java AWT Desktop, no esta habilitada","Email Soporte",JOptionPane.ERROR_MESSAGE);
        }
    }

	private void controlledExit(){
		VentaSesion ventaSesion = ApplicationLogic.getInstance().getVentaSesion();
		if(ventaSesion.getNumElemVta()>0){
			
			ventaActualMenu_actionPerformed();
			
			JOptionPane.showMessageDialog(framePrincipal,
					"  No se puede Cerrar, ahún tiene una venta activa,\n"+
					"debe terminar esta para poder cerrar la aplicación.","CERRAR",JOptionPane.WARNING_MESSAGE);
		} else {
			framePrincipal.dispose();
			System.exit(0);
		}
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		logger.info("[USER]->windowOpened:");
	}

	@Override
	public void windowClosing(WindowEvent e) {
		logger.info("[USER]->windowClosing:");
		controlledExit();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		logger.info("[USER]->windowClosed:");
	}

	@Override
	public void windowIconified(WindowEvent e) {
		logger.info("[USER]->windowIconified:");
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		logger.info("[USER]->windowDeiconified:");
	}

	@Override
	public void windowActivated(WindowEvent e) {
		logger.info("[USER]->windowActivated:");
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		logger.info("[USER]->windowDeactivated:");
	}
}
