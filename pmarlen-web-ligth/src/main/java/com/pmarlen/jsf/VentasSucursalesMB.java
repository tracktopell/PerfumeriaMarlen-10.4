package com.pmarlen.jsf;

import com.pmarlen.backend.dao.ClienteDAO;
import com.pmarlen.backend.dao.DAOException;
import com.pmarlen.backend.dao.EntradaSalidaDAO;
import com.pmarlen.backend.dao.EntradaSalidaDAO;
import com.pmarlen.backend.dao.FormaDePagoDAO;
import com.pmarlen.backend.dao.MetodoDePagoDAO;
import com.pmarlen.backend.dao.ProductoDAO;
import com.pmarlen.backend.dao.SucursalDAO;
import com.pmarlen.backend.model.Cliente;
import com.pmarlen.backend.model.FormaDePago;
import com.pmarlen.backend.model.MetodoDePago;
import com.pmarlen.backend.model.Producto;
import com.pmarlen.backend.model.Sucursal;
import com.pmarlen.backend.model.quickviews.EntradaSalidaDetalleQuickView;
import com.pmarlen.backend.model.quickviews.EntradaSalidaQuickView;
import com.pmarlen.jsf.model.EntradaSalidaLazyDataModel;
import com.pmarlen.model.Constants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import org.apache.log4j.Logger;
import org.primefaces.event.ReorderEvent;

@ManagedBean(name="ventasSucursalesMB")
@ViewScoped
public class VentasSucursalesMB implements Serializable {
	private transient static Logger logger = Logger.getLogger(VentasSucursalesMB.class.getSimpleName());
	private int		sucursalId = -1;		
	private ArrayList<SelectItem> sucursalesList;
	
	@ManagedProperty(value = "#{editarPedidoVentaMB}")
	private EditarPedidoVentaMB editarPedidoVentaMB;	
	
	private EntradaSalidaLazyDataModel lazyModel;	private int viewRows;
	
	@PostConstruct
	public void init(){
		logger.debug("init:");
		getSucursalesList();		
		viewRows = 25;
	}

	public void refrescar(){
		logger.trace("refrescar:");
		lazyModel = null;
	}

	public void onSucursalChange() {
		logger.debug("onSucursalChange:sucursalId="+sucursalId);
		lazyModel = null;
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
		if(lazyModel == null) {
			logger.debug("getLazyModel:sucursalId="+sucursalId);
			lazyModel = new EntradaSalidaLazyDataModel(Constants.TIPO_MOV_SALIDA_ALMACEN_VENTA,sucursalId,true);
		}
		return lazyModel;
	}
	
	public void setSucursalId(int sucursalId) {
		this.sucursalId = sucursalId;
	}

	public int getSucursalId() {
		return sucursalId;
	}
	
	public ArrayList<SelectItem> getSucursalesList() {
		if(sucursalesList==null){
			sucursalesList = new ArrayList<SelectItem>();
			List<Sucursal> sucursales=null;
			try {
				sucursales=(List<Sucursal>) SucursalDAO.getInstance().findAll();
			}catch(DAOException de){
				logger.error(de.getMessage());			
			}
			
			if(sucursales != null){				
				for(Sucursal s:sucursales){
					if(s.getIdPadre() != null){						
						sucursalesList.add(new SelectItem(s.getId(),s.getNombre()));			
					}
				}
				sucursalId = (Integer)sucursalesList.get(0).getValue();
			}
		}
		return sucursalesList;
	}
	
}
