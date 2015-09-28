/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pmarlen.rest.dto;

import com.pmarlen.backend.model.Almacen;
import com.pmarlen.backend.model.Cliente;
import com.pmarlen.backend.model.FormaDePago;
import com.pmarlen.backend.model.MetodoDePago;
import com.pmarlen.backend.model.Producto;
import com.pmarlen.backend.model.Sucursal;
import com.pmarlen.backend.model.Usuario;
import com.pmarlen.backend.model.quickviews.ClienteQuickView;
import com.pmarlen.backend.model.quickviews.InventarioSucursalQuickView;
import java.util.List;

/**
 *
 * @author alfredo
 */
public class SyncDTOPackage {
	private List<InventarioSucursalQuickView> inventarioSucursalQVList;
	private List<Usuario> usuarioList;
	private List<ClienteQuickView> clienteList;
	private List<MetodoDePago> metodoDePagoList;
	private List<FormaDePago> formaDePagoList;
	private Sucursal sucursal;
	private List<Almacen> almacenList;
	

	@Override
	public String toString() {
		return "inventarioSucursalQVList.length="+inventarioSucursalQVList.size()+",usuarioList.length="+usuarioList.size()+
				",clienteList="+clienteList.size()+",metodoDePagoList.length="+metodoDePagoList.size()+
				",formaDePagoList="+formaDePagoList.size()+",sucursal="+sucursal;
	}

	/**
	 * @return the inventarioSucursalQVList
	 */
	public List<InventarioSucursalQuickView> getInventarioSucursalQVList() {
		return inventarioSucursalQVList;
	}

	/**
	 * @param inventarioSucursalQVList the inventarioSucursalQVList to set
	 */
	public void setInventarioSucursalQVList(List<InventarioSucursalQuickView> inventarioSucursalQVList) {
		this.inventarioSucursalQVList = inventarioSucursalQVList;
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
	public List<ClienteQuickView> getClienteList() {
		return clienteList;
	}

	/**
	 * @param clienteList the clienteList to set
	 */
	public void setClienteList(List<ClienteQuickView> clienteList) {
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
