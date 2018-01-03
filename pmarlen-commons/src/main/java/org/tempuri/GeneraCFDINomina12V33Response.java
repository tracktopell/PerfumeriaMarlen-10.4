
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
 *         &lt;element name="GeneraCFDINomina12V33Result" type="{http://tempuri.org/}CFDINominaResponse33" minOccurs="0"/>
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
    "generaCFDINomina12V33Result"
})
@XmlRootElement(name = "GeneraCFDINomina12V33Response")
public class GeneraCFDINomina12V33Response {

    @XmlElement(name = "GeneraCFDINomina12V33Result")
    protected CFDINominaResponse33 generaCFDINomina12V33Result;

    /**
     * Obtiene el valor de la propiedad generaCFDINomina12V33Result.
     * 
     * @return
     *     possible object is
     *     {@link CFDINominaResponse33 }
     *     
     */
    public CFDINominaResponse33 getGeneraCFDINomina12V33Result() {
        return generaCFDINomina12V33Result;
    }

    /**
     * Define el valor de la propiedad generaCFDINomina12V33Result.
     * 
     * @param value
     *     allowed object is
     *     {@link CFDINominaResponse33 }
     *     
     */
    public void setGeneraCFDINomina12V33Result(CFDINominaResponse33 value) {
        this.generaCFDINomina12V33Result = value;
    }

}
