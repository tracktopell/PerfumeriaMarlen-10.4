package com.pmarlen.backend.model.quickviews;

import com.pmarlen.model.Constants;

/**
 *
 * @author alfredo
 */
public class PerfiQuickView {
	private String perfil;
	private String descripcion;

	public PerfiQuickView(String perfil) {
		this.perfil = perfil;
		this.descripcion = Constants.getDescPerfil(perfil);
	}

	/**
	 * @return the perfil
	 */
	public String getPerfil() {
		return perfil;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

}
