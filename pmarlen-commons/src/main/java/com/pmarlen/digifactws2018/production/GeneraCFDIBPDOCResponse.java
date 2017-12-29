
package com.pmarlen.digifactws2018.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GeneraCFDIBPDOCResult" type="{https://cfd.sicofi.com.mx}CFDIBPResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "generaCFDIBPDOCResult"
})
@XmlRootElement(name = "GeneraCFDIBPDOCResponse")
public class GeneraCFDIBPDOCResponse {

    @XmlElement(name = "GeneraCFDIBPDOCResult")
    protected CFDIBPResponse generaCFDIBPDOCResult;

    /**
     * Obtiene el valor de la propiedad generaCFDIBPDOCResult.
     * 
     * @return
     *     possible object is
     *     {@link CFDIBPResponse }
     *     
     */
    public CFDIBPResponse getGeneraCFDIBPDOCResult() {
        return generaCFDIBPDOCResult;
    }

    /**
     * Define el valor de la propiedad generaCFDIBPDOCResult.
     * 
     * @param value
     *     allowed object is
     *     {@link CFDIBPResponse }
     *     
     */
    public void setGeneraCFDIBPDOCResult(CFDIBPResponse value) {
        this.generaCFDIBPDOCResult = value;
    }

}
