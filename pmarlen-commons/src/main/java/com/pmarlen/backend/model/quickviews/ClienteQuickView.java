package com.pmarlen.backend.model.quickviews;

import com.pmarlen.backend.model.Cliente;
import com.pmarlen.model.Constants;

/**
 *
 * @author alfredo
 */
public class ClienteQuickView extends Cliente{

	public ClienteQuickView() {
		super();
	}
	
	public String getDireccion() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(super.getCalle());
		if(super.getNumExterior()!=null && super.getNumExterior().trim().length()>1) sb.append(", NO. EXT. ").append(super.getNumExterior()).append(", ");
		if(super.getNumInterior()!=null && super.getNumInterior().trim().length()>1) sb.append("NO. INT. ").append(super.getNumInterior()).append(", ");
		if(super.getReferencia()!=null && super.getReferencia().trim().length()>1) sb.append(" (").append(super.getNumInterior()).append("), ");
		if(super.getColonia()!=null && super.getColonia().trim().length()>1) sb.append(super.getColonia()).append(", ");
		if(super.getCiudad()!=null && super.getCiudad().trim().length()>1) {
			if(!super.getCiudad().equalsIgnoreCase(super.getMunicipio())) {
				if(!super.getCiudad().toUpperCase().contains("CIUDAD") && !super.getCiudad().toUpperCase().startsWith("CD.")){
					sb.append("CIUDAD ").append(super.getCiudad()).append(", ");
				} else {
					sb.append(super.getCiudad()).append(", ");
				}				
			}
		}
		sb.append(super.getMunicipio()).append(", ");
		sb.append(super.getEstado()).append(", ");
		sb.append("C.P. ").append(super.getCp());
		
		return sb.toString().toUpperCase();
		
	}
}
