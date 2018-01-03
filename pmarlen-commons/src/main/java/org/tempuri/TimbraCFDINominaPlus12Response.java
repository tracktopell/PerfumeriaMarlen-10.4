
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
 *         &lt;element name="TimbraCFDINominaPlus12Result" type="{http://tempuri.org/}TimbradoPlusNominaResponse" minOccurs="0"/>
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
    "timbraCFDINominaPlus12Result"
})
@XmlRootElement(name = "TimbraCFDINominaPlus12Response")
public class TimbraCFDINominaPlus12Response {

    @XmlElement(name = "TimbraCFDINominaPlus12Result")
    protected TimbradoPlusNominaResponse timbraCFDINominaPlus12Result;

    /**
     * Obtiene el valor de la propiedad timbraCFDINominaPlus12Result.
     * 
     * @return
     *     possible object is
     *     {@link TimbradoPlusNominaResponse }
     *     
     */
    public TimbradoPlusNominaResponse getTimbraCFDINominaPlus12Result() {
        return timbraCFDINominaPlus12Result;
    }

    /**
     * Define el valor de la propiedad timbraCFDINominaPlus12Result.
     * 
     * @param value
     *     allowed object is
     *     {@link TimbradoPlusNominaResponse }
     *     
     */
    public void setTimbraCFDINominaPlus12Result(TimbradoPlusNominaResponse value) {
        this.timbraCFDINominaPlus12Result = value;
    }

}
