
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
 *         &lt;element name="TimbraCFDIV33Result" type="{http://tempuri.org/}TimbraCFDI33Response" minOccurs="0"/>
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
    "timbraCFDIV33Result"
})
@XmlRootElement(name = "TimbraCFDIV33Response")
public class TimbraCFDIV33Response {

    @XmlElement(name = "TimbraCFDIV33Result")
    protected TimbraCFDI33Response timbraCFDIV33Result;

    /**
     * Obtiene el valor de la propiedad timbraCFDIV33Result.
     * 
     * @return
     *     possible object is
     *     {@link TimbraCFDI33Response }
     *     
     */
    public TimbraCFDI33Response getTimbraCFDIV33Result() {
        return timbraCFDIV33Result;
    }

    /**
     * Define el valor de la propiedad timbraCFDIV33Result.
     * 
     * @param value
     *     allowed object is
     *     {@link TimbraCFDI33Response }
     *     
     */
    public void setTimbraCFDIV33Result(TimbraCFDI33Response value) {
        this.timbraCFDIV33Result = value;
    }

}
