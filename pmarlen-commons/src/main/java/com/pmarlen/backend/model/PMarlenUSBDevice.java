package com.pmarlen.backend.model;

/**
 *
 * @author alfredo estrada
 */
public class PMarlenUSBDevice  implements java.io.Serializable {
	private static final long serialVersionUID = 1912977272;
	private String usbID;
	private String usbDesc;
	private USBDeviceType type;
	private boolean connected;
	
	public PMarlenUSBDevice() {
	}

	public PMarlenUSBDevice(String usbID, String usbDesc, USBDeviceType type) {
		this.usbID = usbID;
		this.usbDesc = usbDesc;
		this.type = type;
		this.connected = false;
	}
	
	/**
	 * @return the usbID
	 */
	public String getUsbID() {
		return usbID;
	}

	/**
	 * @param usbID the usbID to set
	 */
	public void setUsbID(String usbID) {
		this.usbID = usbID;
	}

	/**
	 * @return the usbDesc
	 */
	public String getUsbDesc() {
		return usbDesc;
	}

	/**
	 * @param usbDesc the usbDesc to set
	 */
	public void setUsbDesc(String usbDesc) {
		this.usbDesc = usbDesc;
	}

	/**
	 * @return the type
	 */
	public USBDeviceType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(USBDeviceType type) {
		this.type = type;
	}

	/**
	 * @param connected 
	 */
	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	/**
	 * @return connected
	 */
	public boolean isConnected() {
		return connected;
	}
	
	
	@Override
	public String toString() {
		return "PMarlenUSBDevice{" + "usbID=" + usbID + ", usbDesc=" + usbDesc + ", type=" + type + ", connected=" + connected + '}';
	}
    
	
}
