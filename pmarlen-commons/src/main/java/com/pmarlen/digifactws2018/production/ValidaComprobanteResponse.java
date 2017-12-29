
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
 *         &lt;element name="ValidaComprobanteResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "validaComprobanteResult"
})
@XmlRootElement(name = "ValidaComprobanteResponse")
public class ValidaComprobanteResponse {

    @XmlElement(name = "ValidaComprobanteResult")
    protected String validaComprobanteResult;

    /**
     * Obtiene el valor de la propiedad validaComprobanteResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidaComprobanteResult() {
        return validaComprobanteResult;
    }

    /**
     * Define el valor de la propiedad validaComprobanteResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidaComprobanteResult(String value) {
        this.validaComprobanteResult = value;
    }

}
