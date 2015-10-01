package com.pmarlen.rest.dto;

import com.pmarlen.backend.model.quickviews.InventarioSucursalQuickView;

/**
 *
 * @author alfredo
 */
public class I {
	int    a1c;
	double a1p;
	int    aOc;
	double aOp;
	int    aRc;
	double aRp;
	String pr;
	String in;
	String li;
	String n;
	String cb;
	String ma;
	String ps;
	String ab;
	int    uxc;
	String ct;
	String um;
	String ue;
	double cs;
	
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
	
	
}