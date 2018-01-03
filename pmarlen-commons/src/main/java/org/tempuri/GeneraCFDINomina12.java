
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="nominarequest" type="{http://tempuri.org/}CFDINominaRequest" minOccurs="0"/>
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
    "nominarequest"
})
@XmlRootElement(name = "GeneraCFDINomina12")
public class GeneraCFDINomina12 {

    protected CFDINominaRequest nominarequest;

    /**
     * Obtiene el valor de la propiedad nominarequest.
     * 
     * @return
     *     possible object is
     *     {@link CFDINominaRequest }
     *     
     */
    public CFDINominaRequest getNominarequest() {
        return nominarequest;
    }

    /**
     * Define el valor de la propiedad nominarequest.
     * 
     * @param value
     *     allowed object is
     *     {@link CFDINominaRequest }
     *     
     */
    public void setNominarequest(CFDINominaRequest value) {
        this.nominarequest = value;
    }

}
