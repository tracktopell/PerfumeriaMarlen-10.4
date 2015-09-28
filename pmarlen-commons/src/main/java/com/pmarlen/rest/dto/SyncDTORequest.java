package com.pmarlen.rest.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author alfredo
 */
public class SyncDTORequest implements Serializable{
	private IAmAliveDTORequest iAmAliveDTORequest;
	private List<EntradaSalidaConDetalle> escdList;

	public SyncDTORequest() {
	}
	
	

	/**
	 * @return the iAmAliveDTORequest
	 */
	public IAmAliveDTORequest getiAmAliveDTORequest() {
		return iAmAliveDTORequest;
	}

	/**
	 * @param iAmAliveDTORequest the iAmAliveDTORequest to set
	 */
	public void setiAmAliveDTORequest(IAmAliveDTORequest iAmAliveDTORequest) {
		this.iAmAliveDTORequest = iAmAliveDTORequest;
	}

	/**
	 * @return the escdList
	 */
	public List<EntradaSalidaConDetalle> getEscdList() {
		return escdList;
	}

	/**
	 * @param escdList the escdList to set
	 */
	public void setEscdList(List<EntradaSalidaConDetalle> escdList) {
		this.escdList = escdList;
	}
}
