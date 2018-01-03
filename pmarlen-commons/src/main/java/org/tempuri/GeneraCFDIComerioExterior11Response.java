
package org.tempuri;

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
 *         &lt;element name="GeneraCFDIComerioExterior11Result" type="{http://tempuri.org/}CFDIComercioExteriorResponse" minOccurs="0"/>
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
    "generaCFDIComerioExterior11Result"
})
@XmlRootElement(name = "GeneraCFDIComerioExterior11Response")
public class GeneraCFDIComerioExterior11Response {

    @XmlElement(name = "GeneraCFDIComerioExterior11Result")
    protected CFDIComercioExteriorResponse generaCFDIComerioExterior11Result;

    /**
     * Obtiene el valor de la propiedad generaCFDIComerioExterior11Result.
     * 
     * @return
     *     possible object is
     *     {@link CFDIComercioExteriorResponse }
     *     
     */
    public CFDIComercioExteriorResponse getGeneraCFDIComerioExterior11Result() {
        return generaCFDIComerioExterior11Result;
    }

    /**
     * Define el valor de la propiedad generaCFDIComerioExterior11Result.
     * 
     * @param value
     *     allowed object is
     *     {@link CFDIComercioExteriorResponse }
     *     
     */
    public void setGeneraCFDIComerioExterior11Result(CFDIComercioExteriorResponse value) {
        this.generaCFDIComerioExterior11Result = value;
    }

}
