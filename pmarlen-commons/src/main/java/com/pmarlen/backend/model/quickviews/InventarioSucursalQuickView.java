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
}
