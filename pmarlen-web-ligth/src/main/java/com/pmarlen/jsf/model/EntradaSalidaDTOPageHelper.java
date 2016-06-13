/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.jsf.model;

import com.pmarlen.backend.model.quickviews.PagerInfo;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author alfredo
 */
public class EntradaSalidaDTOPageHelper implements Serializable{
	private int       tipoMov;
	private int       sucursalId;
	private boolean   active;
	private PagerInfo pagerInfo;
	private Timestamp fechaInicial;
	private Timestamp fechaFinal;
	private Double    importeTotal;

	public EntradaSalidaDTOPageHelper(int tipoMov, int sucursalId, boolean active, PagerInfo pagerInfo, Timestamp fechaInicial, Timestamp fechaFinal, Double importeTotal) {
		this.tipoMov = tipoMov;
		this.sucursalId = sucursalId;
		this.active = active;
		this.pagerInfo = pagerInfo;
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.importeTotal = importeTotal;
	}
	

	/**
	 * @return the tipoMov
	 */
	public int getTipoMov() {
		return tipoMov;
	}

	/**
	 * @param tipoMov the tipoMov to set
	 */
	public void setTipoMov(int tipoMov) {
		this.tipoMov = tipoMov;
	}

	/**
	 * @return the sucursalId
	 */
	public int getSucursalId() {
		return sucursalId;
	}

	/**
	 * @param sucursalId the sucursalId to set
	 */
	public void setSucursalId(int sucursalId) {
		this.sucursalId = sucursalId;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the pagerInfo
	 */
	public PagerInfo getPagerInfo() {
		return pagerInfo;
	}

	/**
	 * @param pagerInfo the pagerInfo to set
	 */
	public void setPagerInfo(PagerInfo pagerInfo) {
		this.pagerInfo = pagerInfo;
	}

	/**
	 * @return the fechaInicial
	 */
	public Timestamp getFechaInicial() {
		return fechaInicial;
	}

	/**
	 * @param fechaInicial the fechaInicial to set
	 */
	public void setFechaInicial(Timestamp fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	/**
	 * @return the fechaFinal
	 */
	public Timestamp getFechaFinal() {
		return fechaFinal;
	}

	/**
	 * @param fechaFinal the fechaFinal to set
	 */
	public void setFechaFinal(Timestamp fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	/**
	 * @return the importeTotal
	 */
	public Double getImporteTotal() {
		return importeTotal;
	}

	/**
	 * @param importeTotal the importeTotal to set
	 */
	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}
}
