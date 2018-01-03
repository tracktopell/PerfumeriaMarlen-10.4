
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
 *         &lt;element name="nominarequest" type="{http://tempuri.org/}TimbradoPlusNominaRequest" minOccurs="0"/>
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
@XmlRootElement(name = "TimbraCFDINominaPlus12")
public class TimbraCFDINominaPlus12 {

    protected TimbradoPlusNominaRequest nominarequest;

    /**
     * Obtiene el valor de la propiedad nominarequest.
     * 
     * @return
     *     possible object is
     *     {@link TimbradoPlusNominaRequest }
     *     
     */
    public TimbradoPlusNominaRequest getNominarequest() {
        return nominarequest;
    }

    /**
     * Define el valor de la propiedad nominarequest.
     * 
     * @param value
     *     allowed object is
     *     {@link TimbradoPlusNominaRequest }
     *     
     */
    public void setNominarequest(TimbradoPlusNominaRequest value) {
        this.nominarequest = value;
    }

}
