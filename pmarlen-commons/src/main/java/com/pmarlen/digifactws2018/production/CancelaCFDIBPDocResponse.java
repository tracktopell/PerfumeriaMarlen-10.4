
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
 *         &lt;element name="CancelaCFDIBPDocResult" type="{https://cfd.sicofi.com.mx}CFDICancelationBPResponse" minOccurs="0"/>
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
    "cancelaCFDIBPDocResult"
})
@XmlRootElement(name = "CancelaCFDIBPDocResponse")
public class CancelaCFDIBPDocResponse {

    @XmlElement(name = "CancelaCFDIBPDocResult")
    protected CFDICancelationBPResponse cancelaCFDIBPDocResult;

    /**
     * Obtiene el valor de la propiedad cancelaCFDIBPDocResult.
     * 
     * @return
     *     possible object is
     *     {@link CFDICancelationBPResponse }
     *     
     */
    public CFDICancelationBPResponse getCancelaCFDIBPDocResult() {
        return cancelaCFDIBPDocResult;
    }

    /**
     * Define el valor de la propiedad cancelaCFDIBPDocResult.
     * 
     * @param value
     *     allowed object is
     *     {@link CFDICancelationBPResponse }
     *     
     */
    public void setCancelaCFDIBPDocResult(CFDICancelationBPResponse value) {
        this.cancelaCFDIBPDocResult = value;
    }

}
