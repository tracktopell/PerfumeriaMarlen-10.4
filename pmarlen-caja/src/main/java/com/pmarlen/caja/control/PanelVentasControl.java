package com.pmarlen.caja.control;

import com.pmarlen.caja.dao.ESFileSystemJsonDAO;
import com.pmarlen.caja.model.VentaTableModel;
import com.pmarlen.caja.view.PanelVentas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
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
	//private ImporteCellRender importeCellRender;
	//private FechaCellRender fechaCellRender;
	public PanelVentasControl(PanelVentas panelVentas) {
		this.panelVentas = panelVentas;
		ventasTM = new VentaTableModel();
		this.panelVentas.getVentasJTable().setModel(ventasTM);
		ventasTM.setVentaList(ESFileSystemJsonDAO.getEsList());
		ventasTM.addTableModelListener( this);
		panelVentas.getVentasJTable().addMouseListener(this);
		panelVentas.getVentasJTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		panelVentas.getVentasJTable().getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		panelVentas.getVentasJTable().getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
		panelVentas.getVentasJTable().getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		panelVentas.getVentasJTable().getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		//panelVentas.getVentasJTable().getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		//panelVentas.getVentasJTable().getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		panelVentas.getVentasJTable().getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
		panelVentas.getVentasJTable().getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
		panelVentas.getVentasJTable().getColumnModel().getColumn(8).setCellRenderer(rightRenderer);
		logger.debug("->>table columns="+panelVentas.getVentasJTable().getColumnCount());
	}
	
	public void refrescar() {		
		final int tw = panelVentas.getVentasJTable().getWidth();
		int[] cws = new int[]{5,15,5,5,10,30,10,10,10};
		int cw;
		logger.debug("->panelVentas.getVentasJTable().getSize()="+tw);
		int i=0;
		for(int cwi : cws) {
			cw = (tw * cwi)/100;
			logger.trace("->\tpanelVentas.getVentasJTable().column["+i+"] PreferredWidth:"+cw);
		
			panelVentas.getVentasJTable().getColumnModel().getColumn(i++).setPreferredWidth(cw);
		}		
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
