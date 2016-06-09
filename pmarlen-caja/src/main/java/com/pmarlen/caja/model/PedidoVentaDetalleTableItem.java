package com.pmarlen.caja.model;

import com.pmarlen.backend.model.Producto;
import com.pmarlen.rest.dto.ESD;

/**
 *
 * @author alfredo
 */
public class PedidoVentaDetalleTableItem {
	private Producto producto;
	private ESD pvd;
	private int tipoAlmacen;

	public PedidoVentaDetalleTableItem(Producto producto, ESD esd, int tipoAlmacen) {
		this.producto = producto;
		this.pvd = esd;
		//this.pvd.setC(esd.getC());
		this.tipoAlmacen = tipoAlmacen;
	}

	public PedidoVentaDetalleTableItem(PedidoVentaDetalleTableItem c) {
		this.producto    = c.producto;
		this.pvd         = new ESD(c.getPvd());		
		this.tipoAlmacen = c.getTipoAlmacen();
	}
	
	public String getShortDesc() {
		return producto.getCodigoBarras()+" "+producto.getNombre()+"/"+producto.getPresentacion()+"("+producto.getContenido()+producto.getUnidadMedida()+")";
	}
	
	public Double getPrecioVenta() {
		return pvd.getP();
	}
	
	public Double getI() {
		return pvd.getC()* pvd.getP();
	}

	public Producto getProducto() {
		return producto;
	}

	public ESD getPvd() {
		return pvd;
	}

	public void setTipoAlmacen(int tipoAlmacen) {
		throw new IllegalStateException("No se debe de cambiar el almacen");
	}

	public int getTipoAlmacen() {
		return tipoAlmacen;
	}

	@Override
	public String toString() {
		return producto.getCodigoBarras()+"\n"+producto.getNombre()+"/"+producto.getPresentacion()+"\n("+producto.getContenido()+producto.getUnidadMedida()+")";
	}
}
