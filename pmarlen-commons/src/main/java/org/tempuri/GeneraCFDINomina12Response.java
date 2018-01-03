
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
 *         &lt;element name="GeneraCFDINomina12Result" type="{http://tempuri.org/}CFDINominaResponse" minOccurs="0"/>
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
    "generaCFDINomina12Result"
})
@XmlRootElement(name = "GeneraCFDINomina12Response")
public class GeneraCFDINomina12Response {

    @XmlElement(name = "GeneraCFDINomina12Result")
    protected CFDINominaResponse generaCFDINomina12Result;

    /**
     * Obtiene el valor de la propiedad generaCFDINomina12Result.
     * 
     * @return
     *     possible object is
     *     {@link CFDINominaResponse }
     *     
     */
    public CFDINominaResponse getGeneraCFDINomina12Result() {
        return generaCFDINomina12Result;
    }

    /**
     * Define el valor de la propiedad generaCFDINomina12Result.
     * 
     * @param value
     *     allowed object is
     *     {@link CFDINominaResponse }
     *     
     */
    public void setGeneraCFDINomina12Result(CFDINominaResponse value) {
        this.generaCFDINomina12Result = value;
    }

}
