package com.pmarlen.jsf;

import com.pmarlen.jsf.model.EntradaSalidaLazyDataModel;
import com.pmarlen.model.Constants;
import java.io.Serializable;

import org.apache.log4j.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="historicoComprasMB")
@SessionScoped
public class HistoricoComprasMB  implements Serializable {
	private transient static Logger logger = Logger.getLogger(HistoricoComprasMB.class.getSimpleName());
	
	private EntradaSalidaLazyDataModel lazyModel;
		
	@ManagedProperty(value = "#{editarComprasMB}")
	private EditarCompraMB editarComprasMB;
	
	private int viewRows;
	
	@PostConstruct
	public void init(){
		logger.trace("init:");
		lazyModel = new EntradaSalidaLazyDataModel(Constants.TIPO_MOV_ENTRADA_ALMACEN_COMPRA,1,false);
		viewRows = 25;
	}
	
	public void refrescar(){
		logger.trace("refrescar:");
		lazyModel = new EntradaSalidaLazyDataModel(Constants.TIPO_MOV_ENTRADA_ALMACEN_COMPRA,1,false);
	}

	public void setEditarComprasMB(EditarCompraMB editarComprasMB) {
		this.editarComprasMB = editarComprasMB;
	}
	
	public void editarCompra(int devolucionId){
		logger.trace("editar:devolucionId="+devolucionId);
		editarComprasMB.editar(devolucionId);
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
