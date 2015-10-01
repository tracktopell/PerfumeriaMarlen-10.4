package com.pmarlen.rest.dto;

import com.pmarlen.backend.model.quickviews.InventarioSucursalQuickView;

/**
 *
 * @author alfredo
 */
public class I {
	private int    a1c;
	private double a1p;
	private int    aOc;
	private double aOp;
	private int    aRc;
	private double aRp;
	private String pr;
	private String in;
	private String li;
	private String n;
	private String cb;
	private String ma;
	private String ps;
	private String ab;
	private int    uxc;
	private String ct;
	private String um;
	private String ue;
	private double cs;
	
	public I(InventarioSucursalQuickView s){
		a1c = s.getA1c();
		a1p = s.getA1p();
		aOc = s.getaOc();
		aOp = s.getaOp();
		aRc = s.getaRc();
		aRp = s.getaRp();
		pr  = s.getPresentacion();
		in  = s.getIndustria();
		li  = s.getLinea();
		n   = s.getNombre();
		cb  = s.getCodigoBarras();
		ma  = s.getMarca();
		ps  = s.getPresentacion();
		ab  = s.getAbrebiatura();
		uxc = s.getUnidadesXCaja();
		ct  = s.getContenido();
		um  = s.getUnidadMedida();
		ue  = s.getUnidadEmpaque();
		cs  = s.getCosto();		
	}
	
	public InventarioSucursalQuickView reverse(){
		InventarioSucursalQuickView s=new InventarioSucursalQuickView();
		
		s.setA1c(a1c);
		s.setA1p(a1p);
		s.setaOc(aOc);
		s.setaOp(aOp);
		s.setaRc(aRc);
		s.setaRp(aRp);
		s.setPresentacion(pr);
		s.setIndustria(in);
		s.setLinea(li);
		s.setNombre(n);
		s.setCodigoBarras(cb);
		s.setMarca(ma);
		s.setPresentacion(ps);
		s.setAbrebiatura(ab);
		s.setUnidadesXCaja(uxc);
		s.setContenido(ct);
		s.setUnidadMedida(um);
		s.setUnidadEmpaque(ue);
		s.setCosto(cs);		
		
		return s;
	}

	/**
	 * @return the a1c
	 */
	public int getA1c() {
		return a1c;
	}

	/**
	 * @param a1c the a1c to set
	 */
	public void setA1c(int a1c) {
		this.a1c = a1c;
	}

	/**
	 * @return the a1p
	 */
	public double getA1p() {
		return a1p;
	}

	/**
	 * @param a1p the a1p to set
	 */
	public void setA1p(double a1p) {
		this.a1p = a1p;
	}

	/**
	 * @return the aOc
	 */
	public int getaOc() {
		return aOc;
	}

	/**
	 * @param aOc the aOc to set
	 */
	public void setaOc(int aOc) {
		this.aOc = aOc;
	}

	/**
	 * @return the aOp
	 */
	public double getaOp() {
		return aOp;
	}

	/**
	 * @param aOp the aOp to set
	 */
	public void setaOp(double aOp) {
		this.aOp = aOp;
	}

	/**
	 * @return the aRc
	 */
	public int getaRc() {
		return aRc;
	}

	/**
	 * @param aRc the aRc to set
	 */
	public void setaRc(int aRc) {
		this.aRc = aRc;
	}

	/**
	 * @return the aRp
	 */
	public double getaRp() {
		return aRp;
	}

	/**
	 * @param aRp the aRp to set
	 */
	public void setaRp(double aRp) {
		this.aRp = aRp;
	}

	/**
	 * @return the pr
	 */
	public String getPr() {
		return pr;
	}

	/**
	 * @param pr the pr to set
	 */
	public void setPr(String pr) {
		this.pr = pr;
	}

	/**
	 * @return the in
	 */
	public String getIn() {
		return in;
	}

	/**
	 * @param in the in to set
	 */
	public void setIn(String in) {
		this.in = in;
	}

	/**
	 * @return the li
	 */
	public String getLi() {
		return li;
	}

	/**
	 * @param li the li to set
	 */
	public void setLi(String li) {
		this.li = li;
	}

	/**
	 * @return the n
	 */
	public String getN() {
		return n;
	}

	/**
	 * @param n the n to set
	 */
	public void setN(String n) {
		this.n = n;
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

	/**
	 * @return the ma
	 */
	public String getMa() {
		return ma;
	}

	/**
	 * @param ma the ma to set
	 */
	public void setMa(String ma) {
		this.ma = ma;
	}

	/**
	 * @return the ps
	 */
	public String getPs() {
		return ps;
	}

	/**
	 * @param ps the ps to set
	 */
	public void setPs(String ps) {
		this.ps = ps;
	}

	/**
	 * @return the ab
	 */
	public String getAb() {
		return ab;
	}

	/**
	 * @param ab the ab to set
	 */
	public void setAb(String ab) {
		this.ab = ab;
	}

	/**
	 * @return the uxc
	 */
	public int getUxc() {
		return uxc;
	}

	/**
	 * @param uxc the uxc to set
	 */
	public void setUxc(int uxc) {
		this.uxc = uxc;
	}

	/**
	 * @return the ct
	 */
	public String getCt() {
		return ct;
	}

	/**
	 * @param ct the ct to set
	 */
	public void setCt(String ct) {
		this.ct = ct;
	}

	/**
	 * @return the um
	 */
	public String getUm() {
		return um;
	}

	/**
	 * @param um the um to set
	 */
	public void setUm(String um) {
		this.um = um;
	}

	/**
	 * @return the ue
	 */
	public String getUe() {
		return ue;
	}

	/**
	 * @param ue the ue to set
	 */
	public void setUe(String ue) {
		this.ue = ue;
	}

	/**
	 * @return the cs
	 */
	public double getCs() {
		return cs;
	}

	/**
	 * @param cs the cs to set
	 */
	public void setCs(double cs) {
		this.cs = cs;
	}
	
	
}