
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
 *         &lt;element name="TimbraCFDIPlusV33Result" type="{http://tempuri.org/}CFDITimbradoPlusReponse" minOccurs="0"/>
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
    "timbraCFDIPlusV33Result"
})
@XmlRootElement(name = "TimbraCFDIPlusV33Response")
public class TimbraCFDIPlusV33Response {

    @XmlElement(name = "TimbraCFDIPlusV33Result")
    protected CFDITimbradoPlusReponse timbraCFDIPlusV33Result;

    /**
     * Obtiene el valor de la propiedad timbraCFDIPlusV33Result.
     * 
     * @return
     *     possible object is
     *     {@link CFDITimbradoPlusReponse }
     *     
     */
    public CFDITimbradoPlusReponse getTimbraCFDIPlusV33Result() {
        return timbraCFDIPlusV33Result;
    }

    /**
     * Define el valor de la propiedad timbraCFDIPlusV33Result.
     * 
     * @param value
     *     allowed object is
     *     {@link CFDITimbradoPlusReponse }
     *     
     */
    public void setTimbraCFDIPlusV33Result(CFDITimbradoPlusReponse value) {
        this.timbraCFDIPlusV33Result = value;
    }

}
