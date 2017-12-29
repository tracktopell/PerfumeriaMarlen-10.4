
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
 *         &lt;element name="GeneraAuxiliarFoliosResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "generaAuxiliarFoliosResult"
})
@XmlRootElement(name = "GeneraAuxiliarFoliosResponse")
public class GeneraAuxiliarFoliosResponse {

    @XmlElement(name = "GeneraAuxiliarFoliosResult")
    protected String generaAuxiliarFoliosResult;

    /**
     * Obtiene el valor de la propiedad generaAuxiliarFoliosResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeneraAuxiliarFoliosResult() {
        return generaAuxiliarFoliosResult;
    }

    /**
     * Define el valor de la propiedad generaAuxiliarFoliosResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeneraAuxiliarFoliosResult(String value) {
        this.generaAuxiliarFoliosResult = value;
    }

}