package com.pmarlen.jsf;

import com.pmarlen.jsf.model.EntradaSalidaLazyDataModel;
import com.pmarlen.model.Constants;
import java.io.Serializable;

import org.apache.log4j.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="traspasosSucMB")
@SessionScoped
public class TraspasosSucMB  implements Serializable {
	private transient static Logger logger = Logger.getLogger(TraspasosSucMB.class.getSimpleName());
	
	private boolean soloSurtidos;
	
	@ManagedProperty(value = "#{editarTraspasoSucMB}")
	private EditarTraspasoSucMB editarTraspasoSucMB;
	
	private EntradaSalidaLazyDataModel lazyModel;	private int viewRows;
	
	@PostConstruct
	public void init(){
		logger.trace("init:");
		lazyModel = new EntradaSalidaLazyDataModel(Constants.TIPO_MOV_SALIDA_TRASPASO,1,true);
		viewRows = 25;
		soloSurtidos = true;
	}
	
	public void refrescar(){
		logger.trace("refrescar:");
		lazyModel = new EntradaSalidaLazyDataModel(Constants.TIPO_MOV_SALIDA_TRASPASO,1,true);
	}

	public void setEditarTraspasoSucMB(EditarTraspasoSucMB editarTraspasoSucMB) {
		this.editarTraspasoSucMB = editarTraspasoSucMB;
	}
	
	public void editar(int id){
		logger.debug("editar:id="+id);
		editarTraspasoSucMB.editar(id);
	}
	public String editarPedidoAction(int pedidoVentaId){
		logger.trace("editarPedidoAction:pedidoVentaId="+pedidoVentaId);
		return editarTraspasoSucMB.editar(pedidoVentaId);
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

	/**
	 * @return the soloSurtidos
	 */
	public boolean isSoloSurtidos() {
		return soloSurtidos;
	}

	/**
	 * @param soloSurtidos the soloSurtidos to set
	 */
	public void setSoloSurtidos(boolean soloSurtidos) {
		this.soloSurtidos = soloSurtidos;
	}

	
}
