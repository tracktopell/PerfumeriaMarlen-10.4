package com.pmarlen.rest.dto;

import com.pmarlen.backend.model.EntradaSalidaDetalle;

/**
 *
 * @author alfredo
 */
public class ESD {
	private String cb;
	private int a;
	private int ta;
	private int c;
	private double p;
	private String dev;
	
	public EntradaSalidaDetalle reverse(){
		EntradaSalidaDetalle esd=new EntradaSalidaDetalle();
		esd.setProductoCodigoBarras(cb);
		esd.setAlmacenId(a);
		esd.setCantidad(c);
		esd.setPrecioVenta(p);
		esd.setDev(dev);
		return esd;
	}

	public ESD() {
	}
	
	public ESD(EntradaSalidaDetalle esd) {
		cb = esd.getProductoCodigoBarras();
		a  = esd.getAlmacenId();
		c  = esd.getCantidad();
		p  = esd.getPrecioVenta();
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
}
