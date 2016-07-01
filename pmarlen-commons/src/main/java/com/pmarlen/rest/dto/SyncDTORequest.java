package com.pmarlen.rest.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author alfredo
 */
public class SyncDTORequest implements Serializable{
	private IAmAliveDTORequest iAmAliveDTORequest;
	private List<ES_ESD> escdList;

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
	public List<ES_ESD> getEscdList() {
		return escdList;
	}

	/**
	 * @param escdList the escdList to set
	 */
	public void setEscdList(List<ES_ESD> escdList) {
		this.escdList = escdList;
	}

    @Override
    public String toString() {
        return "SyncDTORequest{iAmAliveDTORequest.CorteCajaDTO="+iAmAliveDTORequest.getCorteCajaDTO()+", escdList.size()="+escdList.size()+"}";
    }
    
    
}
