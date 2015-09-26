/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.caja.model;

import com.pmarlen.backend.model.EntradaSalidaDetalle;
import com.pmarlen.rest.dto.DES;
import com.pmarlen.rest.dto.P;

/**
 *
 * @author alfredo
 */
public class PedidoVentaDetalleTableItem {
	private P producto;
	private DES pvd;
	private int tipoAlmacen;

	public PedidoVentaDetalleTableItem(P producto, DES pvd, int tipoAlmacen) {
		this.producto = producto;
		this.pvd = pvd;
		this.pvd.setC(1);
		this.tipoAlmacen = tipoAlmacen;
	}
	
	
	public int getCantidad() {
		return pvd.getC();
	}

	public void setCantidad(int cantidad) {
		this.pvd.setC(cantidad);
	}
	
	public String getCodigo() {
		return producto.getCb();
	}

	public String getShortDesc() {
		return producto.getCb()+" "+producto.getN()+"/"+producto.getP()+"("+producto.getC()+producto.getUm()+")";
	}
	
	public Double getPrecioVenta() {
		return producto.getA1p();
	}
	
	public Double getI() {
		return pvd.getC() * producto.getA1p();
	}

	public P getProducto() {
		return producto;
	}

	public DES getPvd() {
		return pvd;
	}

	public void setTipoAlmacen(int tipoAlmacen) {
		throw new IllegalStateException("No se debe de cambiar el almacen");
	}

	public int getTipoAlmacen() {
		return tipoAlmacen;
	}	
}
