
package com.pmarlen.digifactws20160707.production;

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
 *         &lt;element name="GeneraPDFRetencionTimbradoResult" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
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
    "generaPDFRetencionTimbradoResult"
})
@XmlRootElement(name = "GeneraPDFRetencionTimbradoResponse")
public class GeneraPDFRetencionTimbradoResponse {

    @XmlElement(name = "GeneraPDFRetencionTimbradoResult")
    protected byte[] generaPDFRetencionTimbradoResult;

    /**
     * Obtiene el valor de la propiedad generaPDFRetencionTimbradoResult.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getGeneraPDFRetencionTimbradoResult() {
        return generaPDFRetencionTimbradoResult;
    }

    /**
     * Define el valor de la propiedad generaPDFRetencionTimbradoResult.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setGeneraPDFRetencionTimbradoResult(byte[] value) {
        this.generaPDFRetencionTimbradoResult = value;
    }

}
