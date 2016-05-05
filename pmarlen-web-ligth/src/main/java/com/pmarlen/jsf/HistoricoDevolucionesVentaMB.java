package com.pmarlen.jsf;

import com.pmarlen.jsf.model.EntradaSalidaLazyDataModel;
import com.pmarlen.model.Constants;
import java.io.Serializable;

import org.apache.log4j.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="historicoDevolucionesVentaMB")
@SessionScoped
public class HistoricoDevolucionesVentaMB  implements Serializable {
	private transient static Logger logger = Logger.getLogger(HistoricoDevolucionesVentaMB.class.getSimpleName());
	
	private EntradaSalidaLazyDataModel lazyModel;
		
	@ManagedProperty(value = "#{editarDevolucionMB}")
	private EditarDevolucionMB editarDevolucionMB;
	
	private int viewRows;
	
	@PostConstruct
	public void init(){
		logger.trace("init:");
		lazyModel = new EntradaSalidaLazyDataModel(Constants.TIPO_MOV_SALIDA_DEVOLUCION,1,false);
		viewRows = 25;
	}
	
	public void refrescar(){
		logger.trace("refrescar:");
		lazyModel = new EntradaSalidaLazyDataModel(Constants.TIPO_MOV_SALIDA_ALMACEN_VENTA,1,false);
	}

	public void setEditarDevolucionMB(EditarDevolucionMB editarDevolucionMB) {
		this.editarDevolucionMB = editarDevolucionMB;
	}
	
	public void editar(int devolucionId){
		logger.trace("editar:devolucionId="+devolucionId);
		editarDevolucionMB.editar(devolucionId);
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
