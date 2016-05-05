/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pmarlen.jsf;

import java.io.Serializable;
import org.apache.log4j.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author alfredo
 */
@ManagedBean(name="navigationMB")
@RequestScoped
public class NavigationMB  implements Serializable{
	protected static transient Logger logger = Logger.getLogger(NavigationMB.class.getName());

	@ManagedProperty(value = "#{pedidosVentaMB}")
	PedidosVentaMB pedidosVentaMB;
	
	@ManagedProperty(value = "#{historicoPedidosVentaMB}")
	HistoricoPedidosVentaMB historicoPedidosVentaMB;
	
	@ManagedProperty(value = "#{pedidoVentaMB}")
	PedidoVentaMB pedidoVentaMB;
	
	@ManagedProperty(value = "#{comprasMB}")
	ComprasMB comprasMB;
	
	@ManagedProperty(value = "#{historicoComprasMB}")
	HistoricoComprasMB historicoComprasMB;
	
	@ManagedProperty(value = "#{nuevaCompraMB}")
	NuevaCompraMB nuevaCompraMB;
	
	@ManagedProperty(value = "#{nuevaCompraMB}")
	DevolucionesVentaMB devolucionesVentaMB;
	
	@ManagedProperty(value = "#{nuevaCompraMB}")
	HistoricoDevolucionesVentaMB historicoDevolucionesVentaMB;
	
	@ManagedProperty(value = "#{nuevaDevolucionMB}")
	NuevaDevolucionMB nuevaDevolucionMB;
	
	public NavigationMB() {
		logger.trace("Cosntructor");
	}
	
	public String pedidosVenta() {
		logger.trace("pedidosVenta");
		pedidosVentaMB.refrescar();
		return "pedidosVenta";
	}
	
	public String historicoPedidosVenta() {
		logger.trace("historicoPedidosVenta");
		historicoPedidosVentaMB.refrescar();
		return "historicoPedidosVenta";
	}
	
	public String pedidoVenta() {
		logger.trace("pedidoVenta");
		return "pedidoVenta";
	}
	
	
	public String compras() {
		logger.trace("compras");
		pedidosVentaMB.refrescar();
		return "pedidosVenta";
	}
	
	public String historicoCompras() {
		logger.trace("historicoCompras");
		historicoPedidosVentaMB.refrescar();
		return "historicoCompras";
	}
	
	public String nuevaCompra() {
		logger.trace("nuevaCompra");
		return "nuevaCompra";
	}	
	
	public void setPedidosVentaMB(PedidosVentaMB pedidosVentaMB) {
		this.pedidosVentaMB = pedidosVentaMB;
	}

	public void setHistoricoPedidosVentaMB(HistoricoPedidosVentaMB historicoPedidosVentaMB) {
		this.historicoPedidosVentaMB = historicoPedidosVentaMB;
	}

	public void setPedidoVentaMB(PedidoVentaMB pedidoVentaMB) {
		this.pedidoVentaMB = pedidoVentaMB;
	}

	public void setComprasMB(ComprasMB comprasMB) {
		this.comprasMB = comprasMB;
	}

	public void setHistoricoComprasMB(HistoricoComprasMB historicoComprasMB) {
		this.historicoComprasMB = historicoComprasMB;
	}

	public void setNuevaCompraMB(NuevaCompraMB nuevaCompraMB) {
		this.nuevaCompraMB = nuevaCompraMB;
	}
	
	
	
}
