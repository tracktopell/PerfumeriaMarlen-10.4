package com.pmarlen.jsf;

import com.pmarlen.jsf.model.EntradaSalidaLazyDataModel;
import com.pmarlen.model.Constants;
import java.io.Serializable;

import org.apache.log4j.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="devolucionesVentaMB")
@SessionScoped
public class DevolucionesVentaMB  implements Serializable {
	private transient static Logger logger = Logger.getLogger(DevolucionesVentaMB.class.getSimpleName());
	
	@ManagedProperty(value = "#{editarDevolucionMB}")
	private EditarDevolucionMB editarDevolucionMB;
	
	private EntradaSalidaLazyDataModel lazyModel;	private int viewRows;
	
	@PostConstruct
	public void init(){
		logger.trace("init:");
		lazyModel = new EntradaSalidaLazyDataModel(Constants.TIPO_MOV_ENTRADA_ALMACEN_DEVOLUCION,1,true);
		viewRows = 25;
	}
	
	public void refrescar(){
		logger.trace("refrescar:");
		lazyModel = new EntradaSalidaLazyDataModel(Constants.TIPO_MOV_ENTRADA_ALMACEN_DEVOLUCION,1,true);
	}

	public void setEditarDevolucionMB(EditarDevolucionMB editarDevolucionMB) {
		this.editarDevolucionMB = editarDevolucionMB;
	}
	
	public void editar(int id){
		logger.trace("editar:id="+id);
		editarDevolucionMB.editar(id);
	}
	public String editarPedidoAction(int pedidoVentaId){
		logger.trace("editarPedidoAction:pedidoVentaId="+pedidoVentaId);
		return editarDevolucionMB.editar(pedidoVentaId);
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
