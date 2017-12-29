
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
 *         &lt;element name="GeneraTFDv2Result" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "generaTFDv2Result"
})
@XmlRootElement(name = "GeneraTFDv2Response")
public class GeneraTFDv2Response {

    @XmlElement(name = "GeneraTFDv2Result")
    protected String generaTFDv2Result;

    /**
     * Obtiene el valor de la propiedad generaTFDv2Result.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeneraTFDv2Result() {
        return generaTFDv2Result;
    }

    /**
     * Define el valor de la propiedad generaTFDv2Result.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeneraTFDv2Result(String value) {
        this.generaTFDv2Result = value;
    }

}
