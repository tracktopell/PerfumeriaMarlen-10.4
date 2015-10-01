package com.pmarlen.rest.dto;

import com.pmarlen.backend.model.Almacen;
import com.pmarlen.backend.model.Cliente;
import com.pmarlen.backend.model.FormaDePago;
import com.pmarlen.backend.model.MetodoDePago;
import com.pmarlen.backend.model.Sucursal;
import com.pmarlen.backend.model.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alfredo
 */
public class SyncDTOPackage {
	public static final int SYNC_OK   = 0;
	public static final int SYNC_FAIL = 1;
	
	private int     syncDBStatus;
	private List<Integer> listIndexProccessed;
	private List<I> inventarioSucursalList;
	private List<Usuario> usuarioList;
	private List<Cliente> clienteList;
	private List<MetodoDePago> metodoDePagoList;
	private List<FormaDePago> formaDePagoList;
	private Sucursal sucursal;
	private List<Almacen> almacenList;

	public SyncDTOPackage() {
		listIndexProccessed =  new ArrayList<Integer>();
	}
	
	public void processingES(int i){
		listIndexProccessed.add(i);
	}

	public List<Integer> getListIndexProccessed() {
		return listIndexProccessed;
	}
	

	@Override
	public String toString() {
		return "SyncDTOPackage{ syncDBStatus=" + syncDBStatus+
				", listIndexProccessed="+listIndexProccessed+
				", inventarioSucursalList.length="+inventarioSucursalList.size()+
				", usuarioList.size="+usuarioList.size()+
				", clienteList.size="+clienteList.size()+
				", metodoDePagoList.size="+metodoDePagoList.size()+
				", formaDePagoList.size="+formaDePagoList.size()+
				", sucursal="+sucursal+
				", almacenList.size="+almacenList.size()+
				" }";
	}

	public int getSyncDBStatus() {
		return syncDBStatus;
	}

	public void setSyncDBStatus(int syncDBStatus) {
		this.syncDBStatus = syncDBStatus;
	}
	
	

	/**
	 * @return the inventarioSucursalList
	 */
	public List<I> getInventarioSucursalList() {
		return inventarioSucursalList;
	}

	/**
	 * @param inventarioSucursalList the inventarioSucursalList to set
	 */
	public void setInventarioSucursalList(List<I> inventarioSucursalList) {
		this.inventarioSucursalList = inventarioSucursalList;
	}

	/**
	 * @return the usuarioList
	 */
	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}

	/**
	 * @param usuarioList the usuarioList to set
	 */
	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}

	/**
	 * @return the clienteList
	 */
	public List<Cliente> getClienteList() {
		return clienteList;
	}

	/**
	 * @param clienteList the clienteList to set
	 */
	public void setClienteList(List<Cliente> clienteList) {
		this.clienteList = clienteList;
	}

	/**
	 * @return the metodoDePagoList
	 */
	public List<MetodoDePago> getMetodoDePagoList() {
		return metodoDePagoList;
	}

	/**
	 * @param metodoDePagoList the metodoDePagoList to set
	 */
	public void setMetodoDePagoList(List<MetodoDePago> metodoDePagoList) {
		this.metodoDePagoList = metodoDePagoList;
	}

	/**
	 * @return the formaDePagoList
	 */
	public List<FormaDePago> getFormaDePagoList() {
		return formaDePagoList;
	}

	/**
	 * @param formaDePagoList the formaDePagoList to set
	 */
	public void setFormaDePagoList(List<FormaDePago> formaDePagoList) {
		this.formaDePagoList = formaDePagoList;
	}

	/**
	 * @return the sucursal
	 */
	public Sucursal getSucursal() {
		return sucursal;
	}

	/**
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * @return the almacenList
	 */
	public List<Almacen> getAlmacenList() {
		return almacenList;
	}

	/**
	 * @param almacenList the almacenList to set
	 */
	public void setAlmacenList(List<Almacen> almacenList) {
		this.almacenList = almacenList;
	}
}
