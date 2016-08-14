package com.pmarlen.backend.model.quickviews;

import com.pmarlen.backend.model.Multimedio;
import com.pmarlen.backend.model.Producto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alfredo
 */
public class InventarioSucursalQuickView extends Producto {
	//==========================================================================
	/**
	 * Almacen 1ra cantidad
	 */
    private int    a1c;
	
	/**
	 * Almacen 1ra precio
	 */
    private double a1p;
	
	//==========================================================================
	/**
	 * Almacen Opo cantidad
	 */
    private int    aOc;
	
	/**
	 * Almacen Opo precio
	 */
    private double aOp;
	
	//==========================================================================
	/**
	 * Almacen Reg cantidad
	 */
    private int    aRc;
	
	/**
	 * Almacen Reg precio
	 */
    private double aRp;
	
	private Integer a1o2x1;
	
	private Integer aOo2x1;
	
	private Integer aRo2x1;
	
	//==========================================================================

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
	 * @return the a1o2x1
	 */
	public Integer getA1o2x1() {
		return a1o2x1;
	}

	/**
	 * @param a1o2x1 the a1o2x1 to set
	 */
	public void setA1o2x1(Integer a1o2x1) {
		this.a1o2x1 = a1o2x1;
	}

	/**
	 * @return the aOo2x1
	 */
	public Integer getaOo2x1() {
		return aOo2x1;
	}

	/**
	 * @param aOo2x1 the aOo2x1 to set
	 */
	public void setaOo2x1(Integer aOo2x1) {
		this.aOo2x1 = aOo2x1;
	}

	/**
	 * @return the aRo2x1
	 */
	public Integer getaRo2x1() {
		return aRo2x1;
	}

	/**
	 * @param aRo2x1 the aRo2x1 to set
	 */
	public void setaRo2x1(Integer aRo2x1) {
		this.aRo2x1 = aRo2x1;
	}
}
