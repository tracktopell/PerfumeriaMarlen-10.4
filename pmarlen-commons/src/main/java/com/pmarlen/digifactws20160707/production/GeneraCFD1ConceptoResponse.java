
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
 *         &lt;element name="GeneraCFD_1_ConceptoResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "generaCFD1ConceptoResult"
})
@XmlRootElement(name = "GeneraCFD_1_ConceptoResponse")
public class GeneraCFD1ConceptoResponse {

    @XmlElement(name = "GeneraCFD_1_ConceptoResult")
    protected String generaCFD1ConceptoResult;

    /**
     * Obtiene el valor de la propiedad generaCFD1ConceptoResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeneraCFD1ConceptoResult() {
        return generaCFD1ConceptoResult;
    }

    /**
     * Define el valor de la propiedad generaCFD1ConceptoResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeneraCFD1ConceptoResult(String value) {
        this.generaCFD1ConceptoResult = value;
    }

}
