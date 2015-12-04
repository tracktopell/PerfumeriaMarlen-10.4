/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.caja.model;

import com.pmarlen.backend.model.EntradaSalida;
import com.pmarlen.backend.model.quickviews.EntradaSalidaQuickView;
import com.pmarlen.model.Constants;
import com.pmarlen.rest.dto.ES_ESD;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author alfredo
 */
public class VentaTableModel implements TableModel{
	
	private static String[] columnNames = new String [] {
			"TICKET","# ELEMENTOS" ,"FECHA", "SUBTOTAL","DESCUENTO","TOTAL"
	};
	private static Class[] columnClasses = new Class [] {
			String.class,Integer.class,String.class,String.class,String.class,String.class
	};
	
	private List<ES_ESD> ventaList;
	
	private List<TableModelListener> tableModelListenerList;
	
	public VentaTableModel(){
		this.ventaList = new ArrayList<ES_ESD>();
		tableModelListenerList = new ArrayList<TableModelListener> ();
	}
	
	public VentaTableModel(List<ES_ESD> ventaList){
		this.tableModelListenerList = new ArrayList<TableModelListener> ();
		this.ventaList			= ventaList;
	}

	public void setVentaList(List<ES_ESD> ventaList) {
		this.ventaList = ventaList;
	}
	
	@Override
	public int getRowCount() {
		return ventaList.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnClasses[columnIndex];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		//return columnIndex==0;
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		if(columnIndex == 0)
			return ventaList.get(rowIndex).getEs().getNt();
		else if(columnIndex == 1)
			return ventaList.get(rowIndex).getEs().getnElem();
		else if(columnIndex == 2)
			return Constants.sdfLogFile.format(new Date(ventaList.get(rowIndex).getEs().getFc()));			
		else if(columnIndex == 3)
			return Constants.dfCurrency.format(ventaList.get(rowIndex).getEs().getStot());
		else if(columnIndex == 4)
			return Constants.dfCurrency.format(ventaList.get(rowIndex).getEs().getDesc());
		else if(columnIndex == 5)
			return Constants.dfCurrency.format(ventaList.get(rowIndex).getEs().getTot());
		else
			throw new IndexOutOfBoundsException("for column:"+columnIndex);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {		
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		tableModelListenerList.add(l);
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		tableModelListenerList.remove(l);
	}

	/**
	 * @return the detalleVentaTableItemList
	 */
	public List<ES_ESD> getVentaList() {
		return ventaList;
	}

	private void callListeners() {
		for(TableModelListener tml: tableModelListenerList){
			tml.tableChanged(new TableModelEvent(this));
		}
	}
	
}
