package com.pmarlen.backend.model;

import java.util.HashMap;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author alfredo estrada
 */
public class MonitorDeCajas implements java.io.Serializable {
    private static final long serialVersionUID = 1912977278;

	private static HashMap<String,InfoCaja> installedInfoCajaMap;
	
	static{
		installedInfoCajaMap = new HashMap<>();		
	}
	
	public static void init(){
		installedInfoCajaMap.put("pms1c1",new InfoCaja("pms1c1","?", "?", new PMarlenUSBDevice[]{
			new PMarlenUSBDevice("05e0:1200", "Symbol Technologies Bar Code Scanner",	USBDeviceType.BARCODE_SCANNER),
			new PMarlenUSBDevice("04b8:0e03", "Seiko Epson Corp.",						USBDeviceType.EPSON_PRINTER),
			new PMarlenUSBDevice("046d:c534", "Logitech, Inc. Unifying Receiver",		USBDeviceType.KEYBORAD_WIRELESS)
		}));
		
		installedInfoCajaMap.put("pms1c2",new InfoCaja("pms1c2", "?", "?", new PMarlenUSBDevice[]{
			new PMarlenUSBDevice("05e0:1200", "Symbol Technologies Bar Code Scanner",	USBDeviceType.BARCODE_SCANNER),
			new PMarlenUSBDevice("04b8:0e15", "Seiko Epson Corp.",						USBDeviceType.EPSON_PRINTER),
			new PMarlenUSBDevice("046d:c534", "Logitech, Inc. Unifying Receiver",		USBDeviceType.KEYBORAD_WIRELESS)
		}));
		//----------------------------------------------------------------------
		installedInfoCajaMap.put("pms2c1",new InfoCaja("pms2c1", "?", "?", new PMarlenUSBDevice[]{
			new PMarlenUSBDevice("058f:6364", "Alcor Micro Corp. AU6477 Card Reader Controller",	USBDeviceType.BARCODE_SCANNER),
			new PMarlenUSBDevice("04b8:0e03", "Seiko Epson Corp.",									USBDeviceType.EPSON_PRINTER),
			new PMarlenUSBDevice("046d:c534", "Logitech, Inc. Unifying Receiver",					USBDeviceType.KEYBORAD_WIRELESS)
		}));
		installedInfoCajaMap.put("pms2c2",new InfoCaja("pms2c2", "?", "?", new PMarlenUSBDevice[]{
			new PMarlenUSBDevice("05e0:1200", "Symbol Technologies Bar Code Scanner",	USBDeviceType.BARCODE_SCANNER),
			new PMarlenUSBDevice("04b8:0e03", "Seiko Epson Corp.",						USBDeviceType.EPSON_PRINTER),
			new PMarlenUSBDevice("046d:c534", "Logitech, Inc. Unifying Receiver",		USBDeviceType.KEYBORAD_WIRELESS)
		}));
		//----------------------------------------------------------------------		
		installedInfoCajaMap.put("pms3c1",new InfoCaja("pms3c1", "?", "?", new PMarlenUSBDevice[]{
			new PMarlenUSBDevice("0416:5011", "Winbond Electronics Corp. Virtual Com Port",		USBDeviceType.EQUAL_PRINTER),
			new PMarlenUSBDevice("0c2e:0200", "Metrologic Instruments MS7120 Barcode Scanner",	USBDeviceType.BARCODE_SCANNER),
			new PMarlenUSBDevice("046d:c534", "Logitech, Inc. Unifying Receiver",				USBDeviceType.KEYBORAD_WIRELESS)
		}));
		installedInfoCajaMap.put("pms3c2",new InfoCaja("pms3c2", "?", "?", new PMarlenUSBDevice[]{
			new PMarlenUSBDevice("0416:5011", "Winbond Electronics Corp. Virtual Com Port",		USBDeviceType.EQUAL_PRINTER),
			new PMarlenUSBDevice("0c2e:0200", "Metrologic Instruments MS7120 Barcode Scanner",	USBDeviceType.BARCODE_SCANNER),
			new PMarlenUSBDevice("046d:c534", "Logitech, Inc. Unifying Receiver",				USBDeviceType.KEYBORAD_WIRELESS)
		}));
		//----------------------------------------------------------------------
		installedInfoCajaMap.put("pms4c1",new InfoCaja("pms4c1", "?", "?", new PMarlenUSBDevice[]{
			new PMarlenUSBDevice("05e0:1200", "Symbol Technologies Bar Code Scanner",	USBDeviceType.BARCODE_SCANNER),
			new PMarlenUSBDevice("04b8:0e15", "Seiko Epson Corp.",						USBDeviceType.EPSON_PRINTER),
			new PMarlenUSBDevice("046d:c534", "Logitech, Inc. Unifying Receiver",		USBDeviceType.KEYBORAD_WIRELESS)
		}));
		installedInfoCajaMap.put("pms4c2",new InfoCaja("pms4c2", "?", "?", new PMarlenUSBDevice[]{
			new PMarlenUSBDevice("04b4:0100", "Cypress Semiconductor Corp. Cino FuzzyScan F760-B",	USBDeviceType.BARCODE_SCANNER),
			new PMarlenUSBDevice("04b8:0e15", "Seiko Epson Corp.",									USBDeviceType.EPSON_PRINTER),
			new PMarlenUSBDevice("046d:c534", "Logitech, Inc. Unifying Receiver",					USBDeviceType.KEYBORAD_WIRELESS)
		}));
		//----------------------------------------------------------------------
		installedInfoCajaMap.put("pms5c1",new InfoCaja("pms5c1", "?", "?", new PMarlenUSBDevice[]{
			new PMarlenUSBDevice("05e0:1200", "Symbol Technologies Bar Code Scanner",	USBDeviceType.BARCODE_SCANNER),
			new PMarlenUSBDevice("04b8:0e15", "Seiko Epson Corp.",						USBDeviceType.EPSON_PRINTER),
			new PMarlenUSBDevice("046d:c534", "Logitech, Inc. Unifying Receiver",		USBDeviceType.KEYBORAD_WIRELESS)
		}));
		installedInfoCajaMap.put("pms5c2",new InfoCaja("pms5c2", "?", "?", new PMarlenUSBDevice[]{
			new PMarlenUSBDevice("05e0:1200", "Symbol Technologies Bar Code Scanner",	USBDeviceType.BARCODE_SCANNER),
			new PMarlenUSBDevice("04b8:0e15", "Seiko Epson Corp.",						USBDeviceType.EPSON_PRINTER),
			new PMarlenUSBDevice("046d:c534", "Logitech, Inc. Unifying Receiver",		USBDeviceType.KEYBORAD_WIRELESS)
		}));
		//----------------------------------------------------------------------
		installedInfoCajaMap.put("pms6c1",new InfoCaja("pms6c1", "?", "?", new PMarlenUSBDevice[]{
			new PMarlenUSBDevice("05e0:1200", "Symbol Technologies Bar Code Scanner",		USBDeviceType.BARCODE_SCANNER),
			new PMarlenUSBDevice("0416:5011", "Winbond Electronics Corp. Virtual Com Port",	USBDeviceType.EQUAL_PRINTER),
			new PMarlenUSBDevice("046d:c534", "Logitech, Inc. Unifying Receiver",			USBDeviceType.KEYBORAD_WIRELESS)
		}));
		installedInfoCajaMap.put("pms6c2",new InfoCaja("pms6c2", "?", "?", new PMarlenUSBDevice[]{
			new PMarlenUSBDevice("05e0:1200", "Symbol Technologies Bar Code Scanner",		USBDeviceType.BARCODE_SCANNER),
			new PMarlenUSBDevice("0416:5011", "Winbond Electronics Corp. Virtual Com Port",	USBDeviceType.EQUAL_PRINTER),
			new PMarlenUSBDevice("046d:c534", "Logitech, Inc. Unifying Receiver",			USBDeviceType.KEYBORAD_WIRELESS)
		}));
		//----------------------------------------------------------------------
		installedInfoCajaMap.put("pms7c1",new InfoCaja("pms7c1", "?", "?", new PMarlenUSBDevice[]{
			new PMarlenUSBDevice("05e0:1200", "Symbol Technologies Bar Code Scanner",	USBDeviceType.BARCODE_SCANNER),
			new PMarlenUSBDevice("04b8:0e15", "Seiko Epson Corp.",						USBDeviceType.EPSON_PRINTER),
			new PMarlenUSBDevice("046d:c534", "Logitech, Inc. Unifying Receiver",		USBDeviceType.KEYBORAD_WIRELESS)
		}));
		installedInfoCajaMap.put("pms7c2",new InfoCaja("pms7c2", "?", "?", new PMarlenUSBDevice[]{
			new PMarlenUSBDevice("05e0:1200", "Symbol Technologies Bar Code Scanner",	USBDeviceType.BARCODE_SCANNER),
			new PMarlenUSBDevice("04b8:0e15", "Seiko Epson Corp.",						USBDeviceType.EPSON_PRINTER),
			new PMarlenUSBDevice("046d:c534", "Logitech, Inc. Unifying Receiver",		USBDeviceType.KEYBORAD_WIRELESS)
		}));
		
		
	}
	
	public static Set<String> getKeySet(){
		final Set<String> keySet = installedInfoCajaMap.keySet();
		return keySet;
	}
	
	public static InfoCaja getInfoCaja(String cveSucCaja){
		return installedInfoCajaMap.get(cveSucCaja);
	}
	
}
