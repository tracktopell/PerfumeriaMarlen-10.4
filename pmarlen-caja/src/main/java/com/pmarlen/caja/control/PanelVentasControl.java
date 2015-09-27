/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.caja.control;

import com.pmarlen.backend.model.EntradaSalida;
import com.pmarlen.backend.model.EntradaSalidaDetalle;
import com.pmarlen.backend.model.quickviews.EntradaSalidaQuickView;
import com.pmarlen.caja.model.FechaCellRender;
import com.pmarlen.caja.model.ImporteCellRender;
import com.pmarlen.caja.model.VentaTableModel;
import com.pmarlen.caja.view.PanelVentas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.Hashtable;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import org.apache.log4j.Logger;

/**
 *
 * @author alfredo
 */
public class PanelVentasControl implements ActionListener,TableModelListener,MouseListener{
	private static Logger logger = Logger.getLogger(PanelVentasControl.class.getName());
	private PanelVentas panelVentas;
	private DecimalFormat df;
	private VentaTableModel ventasTM;
	private ImporteCellRender importeCellRender;
	private FechaCellRender fechaCellRender;
	public PanelVentasControl(PanelVentas panelVentas) {
		this.panelVentas = panelVentas;
		ventasTM = (VentaTableModel)this.panelVentas.getVentasJTable().getModel();
		ventasTM.addTableModelListener( this);
		panelVentas.getVentasJTable().addMouseListener(this);
		panelVentas.getVentasJTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		logger.debug("->>table columns="+panelVentas.getVentasJTable().getColumnCount());
		
		importeCellRender = new ImporteCellRender();
		fechaCellRender = new FechaCellRender();
		
		importeCellRender.setHorizontalAlignment(SwingConstants.RIGHT);		
	}
	
	public void refrescar() {
		
		panelVentas.getVentasJTable().setModel(ventasTM);
		final int tw = panelVentas.getVentasJTable().getWidth();
		int[] cws = new int[]{10,60,30};
		int cw;
		logger.debug("->panelVentas.getVentasJTable().getSize()="+tw);
		int i=0;
		for(int cwi : cws) {
			cw = (tw * cwi)/100;
			logger.trace("->\tpanelVentas.getVentasJTable().column["+i+"] PreferredWidth:"+cw);
		
			panelVentas.getVentasJTable().getColumnModel().getColumn(i++).setPreferredWidth(cw);
		}
		
		panelVentas.getVentasJTable().getColumnModel().getColumn(1).setCellRenderer(fechaCellRender);		
		panelVentas.getVentasJTable().getColumnModel().getColumn(2).setCellRenderer(importeCellRender);		
		panelVentas.getVentasJTable().updateUI();
	}
	
	public void estadoInicial(){
		refrescar();
		panelVentas.getVentasJTable().updateUI();		
		panelVentas.getVentasJTable().getSelectionModel().clearSelection();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}


	@Override
	public void tableChanged(TableModelEvent e) {
		panelVentas.getVentasJTable().updateUI();		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}	
}
