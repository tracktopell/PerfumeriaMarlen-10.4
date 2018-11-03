package com.pmarlen.jsf;

import com.pmarlen.backend.dao.ClienteDAO;
import com.pmarlen.backend.dao.DAOException;
import com.pmarlen.backend.dao.FormaDePagoDAO;
import com.pmarlen.backend.dao.MetodoDePagoDAO;
import com.pmarlen.backend.model.Cliente;
import com.pmarlen.backend.model.FormaDePago;
import com.pmarlen.backend.model.MetodoDePago;
import com.pmarlen.jsf.model.EntradaSalidaFasterLazyDataModel;
import com.pmarlen.model.Constants;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

@ManagedBean(name="historicoPedidosVentaMB")
//@SessionScoped
@ViewScoped
public class HistoricoPedidosVentaMB  implements Serializable{
	private transient static Logger logger = Logger.getLogger(HistoricoPedidosVentaMB.class.getName());
	
	private EntradaSalidaFasterLazyDataModel lazyModel;
	
	@ManagedProperty(value = "#{editarPedidoVentaMB}")
	private EditarPedidoVentaMB editarPedidoVentaMB;	
	
	private int viewRows;
	
	@PostConstruct
	public void init(){
		logger.trace("init:");
		viewRows = 25;
		lazyModel = new EntradaSalidaFasterLazyDataModel(Constants.TIPO_MOV_SALIDA_ALMACEN_VENTA,1,false,viewRows);
	}
	
	public void refrescar(){
		logger.trace("refrescar:");
		lazyModel = new EntradaSalidaFasterLazyDataModel(Constants.TIPO_MOV_SALIDA_ALMACEN_VENTA,1,false,viewRows);
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

	public EntradaSalidaFasterLazyDataModel getLazyModel() {
		return lazyModel;
	}
	
	public String getCliente(Integer idX){
		Cliente x = new Cliente();		
		try{
			x.setId(idX);
			x = ClienteDAO.getInstance().findBy(x);
			logger.info("-->>getCliente("+idX+"):"+x.getRazonSocial());
			return x.getRazonSocial();
		}catch(DAOException de){
			logger.error("-->>getCliente:"+de.getMessage());			
			return String.valueOf(idX);
		}		
	}

	public String getFormaDePago(Integer idX){
		FormaDePago x = new FormaDePago();		
		try{
			x.setId(idX);
			x = FormaDePagoDAO.getInstance().findBy(x);
			logger.info("-->>getFormaDePago("+idX+"):"+x.getDescripcion());
			return x.getDescripcion();
		}catch(DAOException de){
			logger.error("-->>getFormaDePago:"+de.getMessage());			
			return String.valueOf(idX);
		}
	}
	
	public String getMetodoDePago(Integer idX){
		MetodoDePago x = new MetodoDePago();		
		try{
			x.setId(idX);
			x = MetodoDePagoDAO.getInstance().findBy(x);
			logger.info("-->>getMetodoDePago("+idX+"):"+x.getDescripcion());
			return x.getDescripcion();
		}catch(DAOException de){
			logger.error("-->>getMetodoDePago:"+de.getMessage());			
			return String.valueOf(idX);
		}
	}
	
	
}
