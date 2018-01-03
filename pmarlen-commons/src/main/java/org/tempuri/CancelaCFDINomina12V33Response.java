
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
 *         &lt;element name="CancelaCFDINomina12V33Result" type="{http://tempuri.org/}CFDINominaCancelacionResponse33" minOccurs="0"/>
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
    "cancelaCFDINomina12V33Result"
})
@XmlRootElement(name = "CancelaCFDINomina12V33Response")
public class CancelaCFDINomina12V33Response {

    @XmlElement(name = "CancelaCFDINomina12V33Result")
    protected CFDINominaCancelacionResponse33 cancelaCFDINomina12V33Result;

    /**
     * Obtiene el valor de la propiedad cancelaCFDINomina12V33Result.
     * 
     * @return
     *     possible object is
     *     {@link CFDINominaCancelacionResponse33 }
     *     
     */
    public CFDINominaCancelacionResponse33 getCancelaCFDINomina12V33Result() {
        return cancelaCFDINomina12V33Result;
    }

    /**
     * Define el valor de la propiedad cancelaCFDINomina12V33Result.
     * 
     * @param value
     *     allowed object is
     *     {@link CFDINominaCancelacionResponse33 }
     *     
     */
    public void setCancelaCFDINomina12V33Result(CFDINominaCancelacionResponse33 value) {
        this.cancelaCFDINomina12V33Result = value;
    }

}
