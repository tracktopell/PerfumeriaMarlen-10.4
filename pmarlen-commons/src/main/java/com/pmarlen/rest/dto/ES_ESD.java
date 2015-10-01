package com.pmarlen.rest.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alfredo
 */
public class ES_ESD {
	private ES es;
	private List<ESD> esdList;

	public ES_ESD() {
		this.es = new ES();
		this.esdList = new ArrayList<ESD>();
	}

	public ES_ESD(ES es, List<ESD> esd) {
		this.es = es;
		this.esdList = esd;
	}
	

	/**
	 * @return the es
	 */
	public ES getEs() {
		return es;
	}

	/**
	 * @param es the es to set
	 */
	public void setEs(ES es) {
		this.es = es;
	}

	/**
	 * @return the esd
	 */
	public List<ESD> getEsdList() {
		return esdList;
	}

	/**
	 * @param esd the esd to set
	 */
	public void setEsdList(List<ESD> esd) {
		this.esdList = esd;
	}
	
}
