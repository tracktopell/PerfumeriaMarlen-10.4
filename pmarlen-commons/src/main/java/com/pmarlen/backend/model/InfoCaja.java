package com.pmarlen.backend.model;

import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author alfreod estrada
 */
public class InfoCaja implements java.io.Serializable {
	private static final long serialVersionUID = 1912977271;

	private String	cveSucCaja;
	private String	version;
	private String	userAuthenticated;
	private HashMap<String,PMarlenUSBDevice> usbDeviceMap;

	public InfoCaja() {
	}

	public InfoCaja(String cveSucCaja, String version, String userAuthenticated, PMarlenUSBDevice[] allDevices) {
		this.cveSucCaja = cveSucCaja;
		this.version = version;
		this.userAuthenticated = userAuthenticated;
		this.usbDeviceMap = new HashMap<String,PMarlenUSBDevice>();
		for(PMarlenUSBDevice d: allDevices){
			this.usbDeviceMap.put(d.getUsbID(), d);
		}
	}
	
	
	/**
	 * @return the cveSucCaja
	 */
	public String getCveSucCaja() {
		return cveSucCaja;
	}

	/**
	 * @param cveSucCaja the cveSucCaja to set
	 */
	public void setCveSucCaja(String cveSucCaja) {
		this.cveSucCaja = cveSucCaja;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the userAuthenticated
	 */
	public String getUserAuthenticated() {
		return userAuthenticated;
	}

	/**
	 * @param userAuthenticated the userAuthenticated to set
	 */
	public void setUserAuthenticated(String userAuthenticated) {
		this.userAuthenticated = userAuthenticated;
	}

	/**
	 * @return the usbDeviceMap
	 */
	public HashMap<String,PMarlenUSBDevice> getUsbDeviceMap() {
		return usbDeviceMap;
	}

	/**
	 * @param usbDeviceMap the usbDeviceMap to set
	 */
	public void setUsbDeviceMap(HashMap<String,PMarlenUSBDevice> usbDeviceMap) {
		this.usbDeviceMap = usbDeviceMap;
	}

	@Override
	public String toString() {
		return "InfoCaja{" + "cveSucCaja=" + cveSucCaja + ", version=" + version + ", userAuthenticated=" + userAuthenticated + ", usbDeviceMap=" + usbDeviceMap + '}';
	}

	public void turnOffAllUSB(){
		for(PMarlenUSBDevice d:usbDeviceMap.values()){
			d.setConnected(false);
		}
	}

	private List<PMarlenUSBDevice> allAllDevices=null;
	
	public List<PMarlenUSBDevice> getAllDevices(){
		if(allAllDevices == null){
			allAllDevices = new ArrayList<PMarlenUSBDevice>();
			for(PMarlenUSBDevice u:usbDeviceMap.values()){
				allAllDevices.add(u);
			}
		}
		return allAllDevices;
	}
}
