package com.pmarlen.jsf;

import com.pmarlen.jsf.model.EntradaSalidaLazyDataModel;
import com.pmarlen.model.Constants;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;

@ManagedBean(name="historicoPedidosVentaMB")
@SessionScoped
public class HistoricoPedidosVentaMB  implements Serializable{
	private transient static Logger logger = Logger.getLogger(HistoricoPedidosVentaMB.class.getName());
	
	private EntradaSalidaLazyDataModel lazyModel;
	
	@ManagedProperty(value = "#{editarPedidoVentaMB}")
	private EditarPedidoVentaMB editarPedidoVentaMB;	
	
	//ArrayList<EntradaSalidaQuickView> pedidosVentas;
	private int viewRows;
	
	@PostConstruct
	public void init(){
		logger.trace("init:");
		lazyModel = new EntradaSalidaLazyDataModel(Constants.TIPO_MOV_SALIDA_ALMACEN_VENTA,1,false);
		viewRows = 25;
	}
	
	public void refrescar(){
		logger.trace("refrescar:");
		lazyModel = new EntradaSalidaLazyDataModel(Constants.TIPO_MOV_SALIDA_ALMACEN_VENTA,1,false);
	}

	public void setEditarPedidoVentaMB(EditarPedidoVentaMB editarPedidoVentaMB) {
		this.editarPedidoVentaMB = editarPedidoVentaMB;
	}
	
	public void editar(int id){
		logger.trace("editar:id="+id);
		editarPedidoVentaMB.editar(id);
	}
	public String editarPedidoAction(int pedidoVentaId){
		logger.trace("editarPedidoAction:pedidoVentaId="+pedidoVentaId);
		return editarPedidoVentaMB.editar(pedidoVentaId);
	}
		
	public int getSizeList(){
		logger.trace("getSizeList()");
		return lazyModel.getRowCount();
	}

	public int getViewRows() {
		logger.trace("getViewRows()");
		return viewRows;
	}

	public void setViewRows(int viewRows) {
		logger.trace("setViewRows("+viewRows+")");
		this.viewRows = viewRows;
	}

	public String getImporteMoneda(double f){
		return Constants.getImporteMoneda(f);
	}

	public EntradaSalidaLazyDataModel getLazyModel() {
		return lazyModel;
	}
	
}
