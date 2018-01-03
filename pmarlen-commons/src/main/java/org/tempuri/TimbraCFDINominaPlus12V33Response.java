
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
 *         &lt;element name="TimbraCFDINominaPlus12V33Result" type="{http://tempuri.org/}TimbradoPlusNominaResponse33" minOccurs="0"/>
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
    "timbraCFDINominaPlus12V33Result"
})
@XmlRootElement(name = "TimbraCFDINominaPlus12V33Response")
public class TimbraCFDINominaPlus12V33Response {

    @XmlElement(name = "TimbraCFDINominaPlus12V33Result")
    protected TimbradoPlusNominaResponse33 timbraCFDINominaPlus12V33Result;

    /**
     * Obtiene el valor de la propiedad timbraCFDINominaPlus12V33Result.
     * 
     * @return
     *     possible object is
     *     {@link TimbradoPlusNominaResponse33 }
     *     
     */
    public TimbradoPlusNominaResponse33 getTimbraCFDINominaPlus12V33Result() {
        return timbraCFDINominaPlus12V33Result;
    }

    /**
     * Define el valor de la propiedad timbraCFDINominaPlus12V33Result.
     * 
     * @param value
     *     allowed object is
     *     {@link TimbradoPlusNominaResponse33 }
     *     
     */
    public void setTimbraCFDINominaPlus12V33Result(TimbradoPlusNominaResponse33 value) {
        this.timbraCFDINominaPlus12V33Result = value;
    }

}
