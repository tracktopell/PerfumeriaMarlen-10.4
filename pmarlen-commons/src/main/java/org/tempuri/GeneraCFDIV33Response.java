
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
 *         &lt;element name="GeneraCFDIV33Result" type="{http://tempuri.org/}CFDIResponse" minOccurs="0"/>
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
    "generaCFDIV33Result"
})
@XmlRootElement(name = "GeneraCFDIV33Response")
public class GeneraCFDIV33Response {

    @XmlElement(name = "GeneraCFDIV33Result")
    protected CFDIResponse generaCFDIV33Result;

    /**
     * Obtiene el valor de la propiedad generaCFDIV33Result.
     * 
     * @return
     *     possible object is
     *     {@link CFDIResponse }
     *     
     */
    public CFDIResponse getGeneraCFDIV33Result() {
        return generaCFDIV33Result;
    }

    /**
     * Define el valor de la propiedad generaCFDIV33Result.
     * 
     * @param value
     *     allowed object is
     *     {@link CFDIResponse }
     *     
     */
    public void setGeneraCFDIV33Result(CFDIResponse value) {
        this.generaCFDIV33Result = value;
    }

}
