
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
 *         &lt;element name="nominarequest" type="{http://tempuri.org/}CFDINominaRequest33" minOccurs="0"/>
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
@XmlRootElement(name = "GeneraCFDINomina12V33")
public class GeneraCFDINomina12V33 {

    protected CFDINominaRequest33 nominarequest;

    /**
     * Obtiene el valor de la propiedad nominarequest.
     * 
     * @return
     *     possible object is
     *     {@link CFDINominaRequest33 }
     *     
     */
    public CFDINominaRequest33 getNominarequest() {
        return nominarequest;
    }

    /**
     * Define el valor de la propiedad nominarequest.
     * 
     * @param value
     *     allowed object is
     *     {@link CFDINominaRequest33 }
     *     
     */
    public void setNominarequest(CFDINominaRequest33 value) {
        this.nominarequest = value;
    }

}
