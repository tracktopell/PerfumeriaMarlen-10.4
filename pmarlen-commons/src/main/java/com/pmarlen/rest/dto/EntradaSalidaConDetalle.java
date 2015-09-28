package com.pmarlen.rest.dto;

import com.pmarlen.backend.model.EntradaSalida;
import com.pmarlen.backend.model.EntradaSalidaDetalle;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alfredo
 */
public class EntradaSalidaConDetalle {
	private EntradaSalida es;
	private List<EntradaSalidaDetalle> esd;

	public EntradaSalidaConDetalle() {
		this.es = new EntradaSalida();
		this.esd = new ArrayList<EntradaSalidaDetalle>();
	}

	public EntradaSalidaConDetalle(EntradaSalida es, List<EntradaSalidaDetalle> esd) {
		this.es = es;
		this.esd = esd;
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
	public List<EntradaSalidaDetalle> getEsd() {
		return esd;
	}

	/**
	 * @param esd the esd to set
	 */
	public void setEsd(List<EntradaSalidaDetalle> esd) {
		this.esd = esd;
	}
	
}
