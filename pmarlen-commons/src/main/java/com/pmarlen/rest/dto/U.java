package com.pmarlen.rest.dto;

import com.pmarlen.backend.model.quickviews.UsuarioQuickView;
import com.pmarlen.model.Constants;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alfredo
 */
public class U {
	private String e;
	private int a;
	private String n;
	private String p;
	private List<String> perfiles;
	
	private boolean inRoleRoot;
	private boolean inRoleAdmin;
	private boolean inRoleFinances;
	private boolean inRolePMarlenUser;
	private boolean inRoleSales;
	private boolean inRoleStock;	

	public U() {
	}

	@SuppressWarnings("OverridableMethodCallInConstructor")
	public U(UsuarioQuickView uqv) {
		this();
		this.e = uqv.getEmail();
		this.a = uqv.getAbilitado();
		this.n = uqv.getNombreCompleto();
		this.p = uqv.getPassword();
		this.setPerfiles(uqv.getRoleList());
	}

	

	/**
	 * @return the e
	 */
	public String getE() {
		return e;
	}

	/**
	 * @param e the e to set
	 */
	public void setE(String e) {
		this.e = e;
	}

	/**
	 * @return the a
	 */
	public int getA() {
		return a;
	}

	/**
	 * @param a the a to set
	 */
	public void setA(int a) {
		this.a = a;
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
	 * @return the p
	 */
	public String getP() {
		return p;
	}

	/**
	 * @param p the p to set
	 */
	public void setP(String p) {
		this.p = p;
	}

	/**
	 * @return the perfiles
	 */
	public List<String> getPerfiles() {
		return perfiles;
	}

	/**
	 * @param r the perfiles to set
	 */
	public void setPerfiles(List<String> ra) {
		for(String p: ra){
			if(p.equalsIgnoreCase(Constants.PERFIL_ROOT)){
				inRoleRoot = true;
			} else if(p.equalsIgnoreCase(Constants.PERFIL_ADMIN)){
				inRoleAdmin = true;
			} else if(p.equalsIgnoreCase(Constants.PERFIL_FINANCES)){
				inRoleFinances = true;
			} else if(p.equalsIgnoreCase(Constants.PERFIL_PMARLENUSER)){
				inRolePMarlenUser = true;
			} else if(p.equalsIgnoreCase(Constants.PERFIL_SALES)){
				inRoleSales = true;
			} else if(p.equalsIgnoreCase(Constants.PERFIL_STOCK)){
				inRoleStock = true;
			}
		}
		this.perfiles = ra;
	}
	
	
	
	public boolean getPlaysAsAdmin(){
		return inRoleRoot || inRoleAdmin && getA()==1;
	}	
	public boolean getPlaysAsFinances(){
		return inRoleRoot || inRoleFinances && getA()==1;
	}
	public boolean getPlaysAsPMarlenUser(){
		return inRoleRoot || inRolePMarlenUser && getA()==1;
	}
	public boolean getPlaysAsSales(){
		return inRoleRoot || inRoleSales && getA()==1;
	}
	public boolean getPlaysAsStock(){
		return inRoleRoot || inRoleStock && getA()==1;
	}
	public boolean getPlaysAsRoot(){
		return inRoleRoot;
	}

	@Override
	public String toString() {
		return n;
	}
	
}
