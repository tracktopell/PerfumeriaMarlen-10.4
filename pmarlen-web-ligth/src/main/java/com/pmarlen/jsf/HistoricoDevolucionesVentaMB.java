package com.pmarlen.jsf;

import com.pmarlen.backend.dao.ClienteDAO;
import com.pmarlen.backend.dao.DAOException;
import com.pmarlen.backend.dao.EntradaSalidaDAO;
import com.pmarlen.backend.dao.FormaDePagoDAO;
import com.pmarlen.backend.dao.MetodoDePagoDAO;
import com.pmarlen.backend.dao.ProductoDAO;
import com.pmarlen.backend.model.Cliente;
import com.pmarlen.backend.model.FormaDePago;
import com.pmarlen.backend.model.MetodoDePago;
import com.pmarlen.backend.model.Producto;
import com.pmarlen.backend.model.quickviews.EntradaSalidaDetalleQuickView;
import com.pmarlen.backend.model.quickviews.EntradaSalidaQuickView;
import com.pmarlen.jsf.model.EntradaSalidaLazyDataModel;
import com.pmarlen.model.Constants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import org.primefaces.event.ReorderEvent;

@ManagedBean(name="historicoDevolucionesVentaMB")
@SessionScoped
public class HistoricoDevolucionesVentaMB  {
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
