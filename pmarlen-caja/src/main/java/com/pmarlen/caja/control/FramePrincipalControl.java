/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.caja.control;

import com.pmarlen.caja.view.DialogConfiguracionBTImpresora;
import com.pmarlen.caja.view.FramePrincipal;
import com.pmarlen.caja.view.PanelProductos;
import com.pmarlen.caja.view.PanelVenta;
import com.pmarlen.caja.view.PanelVentas;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	private PanelProductosControl panelProductosControl;
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
		
		framePrincipal.setTitle("PerfumeriaMarlen - caja ("+ApplicationLogic.getInstance().getVersion()+")");
		
		panelVentaControl  = new PanelVentaControl ((PanelVenta)framePrincipal.getPanelVenta());
		
		panelProductosControl = new PanelProductosControl((PanelProductos)framePrincipal.getPanelProductos()) ;

		panelVentasControl = new PanelVentasControl((PanelVentas)framePrincipal.getPanelVentas()) ;

		framePrincipal.getProductosMenu().addActionListener(this);
		
		framePrincipal.getVentasMenu().addActionListener(this);
		
		framePrincipal.getSalirMenu().addActionListener(this);
		
		//---------------------------------------------------
		
		framePrincipal.getVentaActualMenu().addActionListener(this);
		
		framePrincipal.getVentaTerminarMenu().addActionListener(this);
		
		framePrincipal.getVentaeliminarProdMenu().addActionListener(this);
		
		framePrincipal.getVentaCancelarMenu().addActionListener(this);
		//---------------------------------------------------
		
		framePrincipal.getNegocioConfigMenu().addActionListener(this);

		framePrincipal.getUsuarioAdminMenu().addActionListener(this);

		framePrincipal.getUsuarioCajaMenu().addActionListener(this);

		framePrincipal.getImpresoraBTMenu().addActionListener(this);
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
		
		
	}
	
	public void estadoInicial() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {				
				framePrincipal.setVisible(true);
				framePrincipal.updateStatusWest();
				panelVentaControl.estadoInicial();				
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
		relojRunning=true;
		Date fecha;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		while(relojRunning) {
			try{
				Thread.sleep(1000);
				String hora=sdf.format(new Date());
				framePrincipal.getStatusCenter().setText(hora);
				framePrincipal.updateStatus();
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
		if(e.getSource() == framePrincipal.getProductosMenu()){
			productosMenu_actionPerformed();
		} else if(e.getSource() == framePrincipal.getVentasMenu()){
			ventasMenu_actionPerformed();
		} else if(e.getSource() == framePrincipal.getSalirMenu()){
			salirMenu_actionPerformed();
		} else if(e.getSource() == framePrincipal.getVentaActualMenu()){
			ventaActualMenu_actionPerformed();
		} else if(e.getSource() == framePrincipal.getVentaTerminarMenu()){
			ventaTerminar_actionPerformed();
		} else if(e.getSource() == framePrincipal.getVentaeliminarProdMenu()){
			ventaeliminarProdMenu_actionPerformed();
		} else if(e.getSource() == framePrincipal.getVentaCancelarMenu()){
			ventaCancelarMenu_actionPerformed();
		} else if(e.getSource() == framePrincipal.getNegocioConfigMenu()){
			negocioConfigMenu_actionPerformed();
		} else if(e.getSource() == framePrincipal.getUsuarioAdminMenu()){
			usuarioAdminMenu_actionPerformed();
		} else if(e.getSource() == framePrincipal.getUsuarioCajaMenu()){
			usuarioCajaMenu_actionPerformed();
		} else if(e.getSource() == framePrincipal.getImpresoraBTMenu()){
			impresoraBTMenu_actionPerformed();
		}
		
	}

	private void ventaeliminarProdMenu_actionPerformed() {
		panelVentaControl.ventaeliminarProdMenu();
	}

	private void ventaCancelarMenu_actionPerformed() {
		int r = JOptionPane.showConfirmDialog(framePrincipal, "¿Realmente desea Cancelar la Venta ?", "Venta",JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(r == JOptionPane.OK_OPTION){
			panelVentaControl.estadoInicial();
		}
	}

	private void productosMenu_actionPerformed() {
		panelProductosControl.estadoInicial();
		((CardLayout)framePrincipal.getPanels().getLayout()).show(framePrincipal.getPanels(), "panelProductos");
	}

	private void ventasMenu_actionPerformed() {
		panelVentasControl.estadoInicial();
		((CardLayout)framePrincipal.getPanels().getLayout()).show(framePrincipal.getPanels(), "panelVentas");
	}

	private void salirMenu_actionPerformed() {
		framePrincipal.dispose();
		System.exit(0);
	}

	private void ventaActualMenu_actionPerformed() {
		((CardLayout)framePrincipal.getPanels().getLayout()).show(framePrincipal.getPanels(), "panelVenta");
		panelVentaControl.verVentaActual();
	}

	private void ventaTerminar_actionPerformed() {
		((CardLayout)framePrincipal.getPanels().getLayout()).show(framePrincipal.getPanels(), "panelVenta");
	}	
	
	private void negocioConfigMenu_actionPerformed() {
		DialogConfiguracionSistemaControl.getInstance(framePrincipal).estadoInicial();
	}
	
	private void impresoraBTMenu_actionPerformed() {
		DialogConfiguracionBTImpresoraControl.getInstance(framePrincipal).estadoInicial();		
	}

	private void usuarioAdminMenu_actionPerformed() {
		DialogConfiguracionPasswordControl.getInstance(framePrincipal,
				DialogConfiguracionPasswordControl.UPDATE_FOR_ADMIN).estadoInicial();
	}

	private void usuarioCajaMenu_actionPerformed() {
		DialogConfiguracionPasswordControl.getInstance(framePrincipal,
				DialogConfiguracionPasswordControl.UPDATE_FOR_USER).estadoInicial();
	}	

	public void enableAndDisableAdminControls() {
		framePrincipal.updateStatusWest();
		final PanelProductos pp = (PanelProductos)framePrincipal.getPanelProductos();		
		framePrincipal.getConfigMenu().setEnabled(true);
		pp.getEditar().setEnabled(true);
		pp.getEliminar().setEnabled(true);
		pp.getNuevo().setEnabled(true);

	}

	public void setEnabledVentasMenus(boolean e){
		
		framePrincipal.getVentaTerminarMenu().setEnabled(e);
		
		framePrincipal.getVentaeliminarProdMenu().setEnabled(e);
		
		framePrincipal.getVentaCancelarMenu().setEnabled(e);
	}
	
	public void setFontBigest() {
		framePrincipal.setFont(new java.awt.Font("Tahoma", 0, 24));
	}
}
