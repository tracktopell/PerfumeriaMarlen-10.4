
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
 *         &lt;element name="GeneraPDFResult" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
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
    "generaPDFResult"
})
@XmlRootElement(name = "GeneraPDFResponse")
public class GeneraPDFResponse {

    @XmlElement(name = "GeneraPDFResult")
    protected byte[] generaPDFResult;

    /**
     * Obtiene el valor de la propiedad generaPDFResult.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getGeneraPDFResult() {
        return generaPDFResult;
    }

    /**
     * Define el valor de la propiedad generaPDFResult.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setGeneraPDFResult(byte[] value) {
        this.generaPDFResult = value;
    }

}
