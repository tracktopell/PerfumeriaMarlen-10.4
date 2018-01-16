package com.pmarlen.businesslogic.reports;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Updated parser t Soupport CFD 3.3 and previous.
 * @author alfredo
 */
public class ParseCFD33XML {

	public static HashMap parseCFDXML(InputStream isXML) {
		
		HashMap<String,String> result = null;
				
		result = new HashMap<String,String>();
		
		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(isXML);
			
			Element rootElement = doc.getDocumentElement();

			rootElement.normalize();
            
            result.put("ComprobanteVersion" ,getAttributeIgnoreCase(rootElement,"Version"));
			result.put("folio"              ,getAttributeIgnoreCase(rootElement,"Version"));
			result.put("sello"              ,getAttributeIgnoreCase(rootElement,"Sello"));
			result.put("subTotal"           ,getAttributeIgnoreCase(rootElement,"SubTotal"));
			result.put("total"              ,getAttributeIgnoreCase(rootElement,"Total"));
			result.put("serie"              ,getAttributeIgnoreCase(rootElement,"Serie"));
			result.put("fecha"              ,getAttributeIgnoreCase(rootElement,"Fecha"));
			
			NodeList nListe = doc.getElementsByTagName("cfdi:Emisor");

            String emisorRFC=null;
			for (int temp = 0; temp < nListe.getLength(); temp++) {

				Node nNode = nListe.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElemente = (Element) nNode;
                    result.put("emisorRFC",getAttributeIgnoreCase(eElemente,"Rfc"));							
				}
			}
			

			NodeList nListr = doc.getElementsByTagName("cfdi:Receptor");
			
            String receptorRFC=null;
			for (int temp = 0; temp < nListr.getLength(); temp++) {

				Node nNode = nListr.item(temp);

				//System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElementr = (Element) nNode;
                    result.put("receptorRFC",getAttributeIgnoreCase(eElementr,"Rfc"));
				}
			}
			
			NodeList nList = doc.getElementsByTagName("cfdi:Complemento");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				//System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					NodeList elementsByTagName = eElement.getElementsByTagName("tfd:TimbreFiscalDigital");
					for (int tempx = 0; tempx < elementsByTagName.getLength(); tempx++) {

						Node nNodex = elementsByTagName.item(tempx);

						//System.out.println("\nCurrent Element :" + nNodex.getNodeName());

						if (nNode.getNodeType() == Node.ELEMENT_NODE) {
							Element eElementx = (Element) nNodex;
																	
							result.put("version",           getAttributeIgnoreCase(eElementx,"Version"));
							result.put("UUID",              getAttributeIgnoreCase(eElementx,"UUID"));							
							result.put("FechaTimbrado",     getAttributeIgnoreCase(eElementx,"FechaTimbrado"));
							result.put("selloCFD",          getAttributeIgnoreCase(eElementx,"SelloCFD"));
							result.put("selloSAT",          getAttributeIgnoreCase(eElementx,"SelloSAT"));
							result.put("noCertificadoSAT",  getAttributeIgnoreCase(eElementx,"NoCertificadoSAT"));
							
						}
					}				
				}
			}
			
			String strCadenaOriginal = "|"+result.get("version")+"|"+result.get("UUID")+"|"+result.get("FechaTimbrado")+"|"+result.get("selloCFD")+"|"+result.get("noCertificadoSAT")+"|";
			
			String QR = "?re=+"+emisorRFC+"+&rr=+"+receptorRFC+"+&tt=+"+result.get("total")+"+&id=+"+result.get("UUID");
			
			result.put("cadenaOriginal",strCadenaOriginal);
			result.put("QR",QR);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
    
    private static String getAttributeIgnoreCase(Element eElementr,String key){
        String v = eElementr.getAttribute(key);
        if(v == null || (v!=null && v.trim().length()==0) ){
            v = eElementr.getAttribute(key.toLowerCase().substring(0, 1)+key.substring(1));
        }
        return v;
    }
}
