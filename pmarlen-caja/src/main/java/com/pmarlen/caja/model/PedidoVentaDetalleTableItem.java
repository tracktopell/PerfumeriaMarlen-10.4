package com.pmarlen.caja.model;

import com.pmarlen.backend.model.EntradaSalidaDetalle;
import com.pmarlen.backend.model.Producto;

/**
 *
 * @author alfredo
 */
public class PedidoVentaDetalleTableItem {
	private Producto producto;
	private EntradaSalidaDetalle pvd;
	private int tipoAlmacen;

	public PedidoVentaDetalleTableItem(Producto producto, EntradaSalidaDetalle pvd, int tipoAlmacen) {
		this.producto = producto;
		this.pvd = pvd;
		this.pvd.setCantidad(1);
		this.tipoAlmacen = tipoAlmacen;
	}
	
	public String getShortDesc() {
		return producto.getCodigoBarras()+" "+producto.getNombre()+"/"+producto.getPresentacion()+"("+producto.getContenido()+producto.getUnidadMedida()+")";
	}
	
	public Double getPrecioVenta() {
		return pvd.getPrecioVenta();
	}
	
	public Double getI() {
		return pvd.getCantidad()* pvd.getPrecioVenta();
	}

	public Producto getProducto() {
		return producto;
	}

	public EntradaSalidaDetalle getPvd() {
		return pvd;
	}

	public void setTipoAlmacen(int tipoAlmacen) {
		throw new IllegalStateException("No se debe de cambiar el almacen");
	}

	public int getTipoAlmacen() {
		return tipoAlmacen;
	}	
}
