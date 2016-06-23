package com.pmarlen.rest.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alfredo
 */
public class ES_ESD implements Serializable{
	public static final byte STATUS_SYNC_LOCAL=0;
	public static final byte STATUS_SYNC_SENT =1;
	public static final byte STATUS_SYNC_ERROR=2;
	private int s;
	private ES es;
	private List<ESD> esdList;

	public ES_ESD() {
		this.es = new ES();
		this.esdList = new ArrayList<ESD>();
		this.s  = STATUS_SYNC_LOCAL;
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

	public void setS(int s) {
		this.s = s;
	}

	public int getS() {
		return s;
	}

    @Override
    public String toString() {
        return "ES_ESD{s="+s+",es="+es+",esd="+esdList+"}";
    }
    
    
}
