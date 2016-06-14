package com.pmarlen.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pmarlen.backend.model.EntradaSalidaDetalle;
import com.pmarlen.backend.model.quickviews.EntradaSalidaDetalleQuickView;
import java.io.Serializable;

/**
 *
 * @author alfredo
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ESD implements Serializable{
	private String cb;
	private Integer id;
	private int a;
	private int ta;
	private int c;
	private double p;
	private Integer dev;
	private Integer esIdDev;
	
	public EntradaSalidaDetalleQuickView reverse(){
		EntradaSalidaDetalleQuickView esd=new EntradaSalidaDetalleQuickView();
		esd.setProductoCodigoBarras(cb);
		esd.setAlmacenId(a);
		esd.setCantidad(c);
		esd.setApTipoAlmacen(ta);
		esd.setPrecioVenta(p);
		if(dev != null){
			esd.setDev(dev);
		} else {
			esd.setDev(0);
		}
		if(esIdDev != null){
			esd.setEsIdDev(esIdDev);
		}
		return esd;
	}

	public ESD() {
	}

	public ESD(ESD x) {
		this.cb = x.cb;
		this.a  = x.a;
		this.ta = x.ta;
		this.c  = x.c;
		this.p  = x.p;
		this.dev = x.dev;
		this.esIdDev = x.esIdDev;
	}
	
	public ESD(EntradaSalidaDetalle esd) {
		id = esd.getId()!=null?esd.getId().intValue():0;
		cb = esd.getProductoCodigoBarras();
		a  = esd.getAlmacenId();
		c  = esd.getCantidad();
		p  = esd.getPrecioVenta();
		dev = esd.getDev();
		esIdDev = esd.getEsIdDev();
	}
	
	public ESD(EntradaSalidaDetalleQuickView esdqv) {
		id = esdqv.getId()!=null?esdqv.getId().intValue():0;
		cb = esdqv.getProductoCodigoBarras();
		a  = esdqv.getAlmacenId();
		c  = esdqv.getCantidad();
		p  = esdqv.getPrecioVenta();
		dev = esdqv.getDev()!=null?esdqv.getDev().intValue():0;
		esIdDev = esdqv.getEsIdDev()!=null?esdqv.getEsIdDev().intValue():0;
		ta = esdqv.getApTipoAlmacen();
	}

	public Integer getId() {
		return id;
	}
	
	/**
	 * @return the cb
	 */
	public String getCb() {
		return cb;
	}

	/**
	 * @param cb the cb to set
	 */
	public void setCb(String cb) {
		this.cb = cb;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getA() {
		return a;
	}
	
	/**
	 * @return the ta
	 */
	public int getTa() {
		return ta;
	}

	/**
	 * @param a the ta to set
	 */
	public void setTa(int a) {
		this.ta = a;
	}

	/**
	 * @return the c
	 */
	public int getC() {
		return c;
	}

	/**
	 * @param c the c to set
	 */
	public void setC(int c) {
		this.c = c;
	}

	/**
	 * @return the p
	 */
	public double getP() {
		return p;
	}

	/**
	 * @param p the p to set
	 */
	public void setP(double p) {
		this.p = p;
	}

	public Integer getDev() {
		return dev;
	}

	public void setDev(Integer dev) {
		this.dev = dev;
	}

	/**
	 * @return the esIdDev
	 */
	public Integer getEsIdDev() {
		return esIdDev;
	}

	/**
	 * @param esIdDev the esIdDev to set
	 */
	public void setEsIdDev(Integer esIdDev) {
		this.esIdDev = esIdDev;
	}
}
