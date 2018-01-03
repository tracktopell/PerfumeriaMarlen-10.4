
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
 *         &lt;element name="CancelaCFDIV33Result" type="{http://tempuri.org/}CFDIv33CancelacionResponse" minOccurs="0"/>
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
    "cancelaCFDIV33Result"
})
@XmlRootElement(name = "CancelaCFDIV33Response")
public class CancelaCFDIV33Response {

    @XmlElement(name = "CancelaCFDIV33Result")
    protected CFDIv33CancelacionResponse cancelaCFDIV33Result;

    /**
     * Obtiene el valor de la propiedad cancelaCFDIV33Result.
     * 
     * @return
     *     possible object is
     *     {@link CFDIv33CancelacionResponse }
     *     
     */
    public CFDIv33CancelacionResponse getCancelaCFDIV33Result() {
        return cancelaCFDIV33Result;
    }

    /**
     * Define el valor de la propiedad cancelaCFDIV33Result.
     * 
     * @param value
     *     allowed object is
     *     {@link CFDIv33CancelacionResponse }
     *     
     */
    public void setCancelaCFDIV33Result(CFDIv33CancelacionResponse value) {
        this.cancelaCFDIV33Result = value;
    }

}
