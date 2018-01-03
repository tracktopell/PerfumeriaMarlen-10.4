
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
 *         &lt;element name="GeneraCFDPlusV33ComplementoDePagosResult" type="{http://tempuri.org/}CFDITimbradoPlusReponseComplementoDePagos" minOccurs="0"/>
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
    "generaCFDPlusV33ComplementoDePagosResult"
})
@XmlRootElement(name = "GeneraCFDPlusV33ComplementoDePagosResponse")
public class GeneraCFDPlusV33ComplementoDePagosResponse {

    @XmlElement(name = "GeneraCFDPlusV33ComplementoDePagosResult")
    protected CFDITimbradoPlusReponseComplementoDePagos generaCFDPlusV33ComplementoDePagosResult;

    /**
     * Obtiene el valor de la propiedad generaCFDPlusV33ComplementoDePagosResult.
     * 
     * @return
     *     possible object is
     *     {@link CFDITimbradoPlusReponseComplementoDePagos }
     *     
     */
    public CFDITimbradoPlusReponseComplementoDePagos getGeneraCFDPlusV33ComplementoDePagosResult() {
        return generaCFDPlusV33ComplementoDePagosResult;
    }

    /**
     * Define el valor de la propiedad generaCFDPlusV33ComplementoDePagosResult.
     * 
     * @param value
     *     allowed object is
     *     {@link CFDITimbradoPlusReponseComplementoDePagos }
     *     
     */
    public void setGeneraCFDPlusV33ComplementoDePagosResult(CFDITimbradoPlusReponseComplementoDePagos value) {
        this.generaCFDPlusV33ComplementoDePagosResult = value;
    }

}
