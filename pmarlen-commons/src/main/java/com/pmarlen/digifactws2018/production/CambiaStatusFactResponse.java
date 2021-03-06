
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
 *         &lt;element name="CambiaStatusFactResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "cambiaStatusFactResult"
})
@XmlRootElement(name = "CambiaStatusFactResponse")
public class CambiaStatusFactResponse {

    @XmlElement(name = "CambiaStatusFactResult")
    protected String cambiaStatusFactResult;

    /**
     * Obtiene el valor de la propiedad cambiaStatusFactResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCambiaStatusFactResult() {
        return cambiaStatusFactResult;
    }

    /**
     * Define el valor de la propiedad cambiaStatusFactResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCambiaStatusFactResult(String value) {
        this.cambiaStatusFactResult = value;
    }

}
