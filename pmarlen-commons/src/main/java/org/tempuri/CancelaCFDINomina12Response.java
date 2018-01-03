
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
 *         &lt;element name="CancelaCFDINomina12Result" type="{http://tempuri.org/}CFDINominaCancelacionResponse" minOccurs="0"/>
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
    "cancelaCFDINomina12Result"
})
@XmlRootElement(name = "CancelaCFDINomina12Response")
public class CancelaCFDINomina12Response {

    @XmlElement(name = "CancelaCFDINomina12Result")
    protected CFDINominaCancelacionResponse cancelaCFDINomina12Result;

    /**
     * Obtiene el valor de la propiedad cancelaCFDINomina12Result.
     * 
     * @return
     *     possible object is
     *     {@link CFDINominaCancelacionResponse }
     *     
     */
    public CFDINominaCancelacionResponse getCancelaCFDINomina12Result() {
        return cancelaCFDINomina12Result;
    }

    /**
     * Define el valor de la propiedad cancelaCFDINomina12Result.
     * 
     * @param value
     *     allowed object is
     *     {@link CFDINominaCancelacionResponse }
     *     
     */
    public void setCancelaCFDINomina12Result(CFDINominaCancelacionResponse value) {
        this.cancelaCFDINomina12Result = value;
    }

}
