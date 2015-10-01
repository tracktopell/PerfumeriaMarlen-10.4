package com.pmarlen.rest.dto;

import com.pmarlen.backend.model.EntradaSalida;
import com.pmarlen.backend.model.EntradaSalidaDetalle;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alfredo
 */
public class ES_ESD {
	private EntradaSalida es;
	private List<EntradaSalidaDetalle> esdList;

	public ES_ESD() {
		this.es = new EntradaSalida();
		this.esdList = new ArrayList<EntradaSalidaDetalle>();
	}

	public ES_ESD(EntradaSalida es, List<EntradaSalidaDetalle> esd) {
		this.es = es;
		this.esdList = esd;
	}
	

	/**
	 * @return the es
	 */
	public EntradaSalida getEs() {
		return es;
	}

	/**
	 * @param es the es to set
	 */
	public void setEs(EntradaSalida es) {
		this.es = es;
	}

	/**
	 * @return the esd
	 */
	public List<EntradaSalidaDetalle> getEsdList() {
		return esdList;
	}

	/**
	 * @param esd the esd to set
	 */
	public void setEsdList(List<EntradaSalidaDetalle> esd) {
		this.esdList = esd;
	}
	
}
