
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
 *         &lt;element name="CambiaStatusMagnaResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "cambiaStatusMagnaResult"
})
@XmlRootElement(name = "CambiaStatusMagnaResponse")
public class CambiaStatusMagnaResponse {

    @XmlElement(name = "CambiaStatusMagnaResult")
    protected String cambiaStatusMagnaResult;

    /**
     * Obtiene el valor de la propiedad cambiaStatusMagnaResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCambiaStatusMagnaResult() {
        return cambiaStatusMagnaResult;
    }

    /**
     * Define el valor de la propiedad cambiaStatusMagnaResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCambiaStatusMagnaResult(String value) {
        this.cambiaStatusMagnaResult = value;
    }

}
