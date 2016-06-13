/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.rest.dto;

import com.pmarlen.backend.model.EntradaSalida;
import com.pmarlen.backend.model.quickviews.EntradaSalidaQuickView;
import java.sql.Timestamp;

/**
 *
 * @author alfredo
 */
public class ES {
	private Integer id;
	private int tm;
	private int s;
	private String sn;
	private long fc;
	private String u;
	private int c;
	private int fp;
	private int j;
	private double i;
	private String nt;	
	private int mp;	
	private double ir;
	private String amc;
	private int pdc;	
	private int pde;	
	private double st1;
	private double stO;
	private double stR;
	private double desc;
	private double tot;
	private int    nElem;	
	private int    ed;
	private Integer esDev;
	private String esTicketDev;
	
	public EntradaSalidaQuickView reverse(){
		EntradaSalidaQuickView es = new EntradaSalidaQuickView();
		es.setTipoMov(tm);
		es.setSucursalId(s);
		es.setFechaCreo(new Timestamp(fc));
		es.setUsuarioEmailCreo(u);		
		es.setClienteId(c);
		es.setFormaDePagoId(fp);
		es.setMetodoDePagoId(mp);
		es.setCaja(j);
		es.setFactorIva(i);
		es.setNumeroTicket(nt);
		es.setAprobacionVisaMastercard(amc);
		es.setImporteRecibido(ir);
		es.setPorcentajeDescuentoCalculado(pdc);
		es.setPorcentajeDescuentoExtra(pde);
		if(pdc>0 || pde>0){
			es.setAutorizaDescuento(1);
		}
		es.setSubTotal1ra(st1);
		es.setSubTotalOpo(stO);
		es.setSubTotalReg(stR);
		es.setTotal(tot);
		es.setNumElementos(ed);
		es.setTotProds(nElem);		
		es.setEsIdDev(esDev);

		return es;
	}

	public ES() {
	}
	
	public ES(EntradaSalida x) {
		this.id = x.getId();	
		this.tm = x.getTipoMov();
		this.s = x.getSucursalId();		
		this.fc = x.getFechaCreo().getTime();
		this.u = x.getUsuarioEmailCreo();
		this.c = x.getClienteId();
		this.fp = x.getFormaDePagoId();
		this.j = x.getCaja();
		this.i = x.getImporteRecibido();
		this.nt = x.getNumeroTicket();
		this.mp = x.getMetodoDePagoId();
		this.ir = x.getImporteRecibido();
		this.amc = x.getAprobacionVisaMastercard();
		this.pdc = x.getPorcentajeDescuentoCalculado();
		this.pde = x.getPorcentajeDescuentoExtra();
//		this.stot = 0;
//		this.desc = 0;
//		this.tot = x.getTotal();
//		this.nElem = 0;
//		this.esTicketDev = null;
	}
	
	public ES(EntradaSalidaQuickView x) {
		this.id = x.getId();
		this.tm = x.getTipoMov();
		this.s = x.getSucursalId();
		this.sn = x.getSucursalNombre();
		this.fc = x.getFechaCreo().getTime();
		this.u = x.getUsuarioEmailCreo();
		this.c = x.getClienteId();
		this.fp = x.getFormaDePagoId();
		this.j = x.getCaja();
		this.i = x.getImporteRecibido();
		this.nt = x.getNumeroTicket();
		this.mp = x.getMetodoDePagoId();
		this.ir = x.getImporteRecibido();
		this.amc = x.getAprobacionVisaMastercard();
		this.pdc = x.getPorcentajeDescuentoCalculado();
		this.pde = x.getPorcentajeDescuentoExtra();
//		this.stot = 0;
//		this.desc = 0;
//		this.tot = x.getTotal();
		this.nElem = x.getNumElementos();
		this.ed    = x.getElemDet()!=null?x.getElemDet().intValue():0;
//		this.esTicketDev = null;
	}

	/**
	 * @return the tm
	 */
	public int getTm() {
		return tm;
	}

	/**
	 * @param tm the tm to set
	 */
	public void setTm(int tm) {
		this.tm = tm;
	}

	/**
	 * @return the s
	 */
	public int getS() {
		return s;
	}

	/**
	 * @param s the s to set
	 */
	public void setS(int s) {
		this.s = s;
	}

	/**
	 * @return the fc
	 */
	public long getFc() {
		return fc;
	}

	/**
	 * @param fc the fc to set
	 */
	public void setFc(long fc) {
		this.fc = fc;
	}

	/**
	 * @return the u
	 */
	public String getU() {
		return u;
	}

	/**
	 * @param u the u to set
	 */
	public void setU(String u) {
		this.u = u;
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
	 * @return the fp
	 */
	public int getFp() {
		return fp;
	}

	/**
	 * @param fp the fp to set
	 */
	public void setFp(int fp) {
		this.fp = fp;
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}
	
	/**
	 * @return the j
	 */
	public int getJ() {
		return j;
	}

	/**
	 * @param j the j to set
	 */
	public void setJ(int j) {
		this.j = j;
	}

	/**
	 * @return the i
	 */
	public double getI() {
		return i;
	}

	/**
	 * @param i the i to set
	 */
	public void setI(double i) {
		this.i = i;
	}

	/**
	 * @return the nt
	 */
	public String getNt() {
		return nt;
	}

	/**
	 * @param nt the nt to set
	 */
	public void setNt(String nt) {
		this.nt = nt;
	}

	/**
	 * @return the ir
	 */
	public double getIr() {
		return ir;
	}

	/**
	 * @param ir the ir to set
	 */
	public void setIr(double ir) {
		this.ir = ir;
	}

	/**
	 * @return the amc
	 */
	public String getAmc() {
		return amc;
	}

	/**
	 * @param amc the amc to set
	 */
	public void setAmc(String amc) {
		this.amc = amc;
	}

	/**
	 * @return the pdc
	 */
	public int getPdc() {
		return pdc;
	}

	/**
	 * @param pdc the pdc to set
	 */
	public void setPdc(int pdc) {
		this.pdc = pdc;
	}

	/**
	 * @return the pde
	 */
	public int getPde() {
		return pde;
	}

	/**
	 * @param pde the pde to set
	 */
	public void setPde(int pde) {
		this.pde = pde;
	}

	/**
	 * @return the desc
	 */
	public double getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(double desc) {
		this.desc = desc;
	}

	/**
	 * @return the tot
	 */
	public double getTot() {
		return tot;
	}

	/**
	 * @param tot the tot to set
	 */
	public void setTot(double tot) {
		this.tot = tot;
	}

	/**
	 * @return the nElem
	 */
	public int getnElem() {
		return nElem;
	}

	/**
	 * @param nElem the nElem to set
	 */
	public void setnElem(int nElem) {
		this.nElem = nElem;
	}

	public void setEsTicketDev(String esTicketDev) {
		this.esTicketDev = esTicketDev;
	}

	public String getEsTicketDev() {
		return esTicketDev;
	}

	/**
	 * @return the st1
	 */
	public double getSt1() {
		return st1;
	}

	/**
	 * @param st1 the st1 to set
	 */
	public void setSt1(double st1) {
		this.st1 = st1;
	}

	/**
	 * @return the stO
	 */
	public double getStO() {
		return stO;
	}

	/**
	 * @param stO the stO to set
	 */
	public void setStO(double stO) {
		this.stO = stO;
	}

	/**
	 * @return the stR
	 */
	public double getStR() {
		return stR;
	}

	/**
	 * @param stR the stR to set
	 */
	public void setStR(double stR) {
		this.stR = stR;
	}

	/**
	 * @return the ed
	 */
	public int getEd() {
		return ed;
	}

	/**
	 * @param ed the ed to set
	 */
	public void setEd(int ed) {
		this.ed = ed;
	}

	public String getSn() {
		return sn;
	}	

	/**
	 * @return the esDev
	 */
	public Integer getEsDev() {
		return esDev;
	}

	/**
	 * @param esDev the esDev to set
	 */
	public void setEsDev(Integer esDev) {
		this.esDev = esDev;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
}
