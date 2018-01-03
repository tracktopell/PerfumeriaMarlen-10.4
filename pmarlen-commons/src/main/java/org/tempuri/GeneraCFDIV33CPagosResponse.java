
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
 *         &lt;element name="GeneraCFDIV33CPagosResult" type="{http://tempuri.org/}CFDIResponseComplementoDePagos" minOccurs="0"/>
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
    "generaCFDIV33CPagosResult"
})
@XmlRootElement(name = "GeneraCFDIV33CPagosResponse")
public class GeneraCFDIV33CPagosResponse {

    @XmlElement(name = "GeneraCFDIV33CPagosResult")
    protected CFDIResponseComplementoDePagos generaCFDIV33CPagosResult;

    /**
     * Obtiene el valor de la propiedad generaCFDIV33CPagosResult.
     * 
     * @return
     *     possible object is
     *     {@link CFDIResponseComplementoDePagos }
     *     
     */
    public CFDIResponseComplementoDePagos getGeneraCFDIV33CPagosResult() {
        return generaCFDIV33CPagosResult;
    }

    /**
     * Define el valor de la propiedad generaCFDIV33CPagosResult.
     * 
     * @param value
     *     allowed object is
     *     {@link CFDIResponseComplementoDePagos }
     *     
     */
    public void setGeneraCFDIV33CPagosResult(CFDIResponseComplementoDePagos value) {
        this.generaCFDIV33CPagosResult = value;
    }

}
