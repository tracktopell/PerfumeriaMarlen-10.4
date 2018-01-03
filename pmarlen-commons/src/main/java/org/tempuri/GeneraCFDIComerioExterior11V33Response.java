
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
 *         &lt;element name="GeneraCFDIComerioExterior11V33Result" type="{http://tempuri.org/}CFDIComercioExteriorResponse33" minOccurs="0"/>
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
    "generaCFDIComerioExterior11V33Result"
})
@XmlRootElement(name = "GeneraCFDIComerioExterior11V33Response")
public class GeneraCFDIComerioExterior11V33Response {

    @XmlElement(name = "GeneraCFDIComerioExterior11V33Result")
    protected CFDIComercioExteriorResponse33 generaCFDIComerioExterior11V33Result;

    /**
     * Obtiene el valor de la propiedad generaCFDIComerioExterior11V33Result.
     * 
     * @return
     *     possible object is
     *     {@link CFDIComercioExteriorResponse33 }
     *     
     */
    public CFDIComercioExteriorResponse33 getGeneraCFDIComerioExterior11V33Result() {
        return generaCFDIComerioExterior11V33Result;
    }

    /**
     * Define el valor de la propiedad generaCFDIComerioExterior11V33Result.
     * 
     * @param value
     *     allowed object is
     *     {@link CFDIComercioExteriorResponse33 }
     *     
     */
    public void setGeneraCFDIComerioExterior11V33Result(CFDIComercioExteriorResponse33 value) {
        this.generaCFDIComerioExterior11V33Result = value;
    }

}
