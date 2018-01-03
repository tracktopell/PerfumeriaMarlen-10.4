
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
 *         &lt;element name="TimbraCFDIComercioExteriorplus11Result" type="{http://tempuri.org/}TimbradoPlusComercioExteriorResponse" minOccurs="0"/>
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
    "timbraCFDIComercioExteriorplus11Result"
})
@XmlRootElement(name = "TimbraCFDIComercioExteriorplus11Response")
public class TimbraCFDIComercioExteriorplus11Response {

    @XmlElement(name = "TimbraCFDIComercioExteriorplus11Result")
    protected TimbradoPlusComercioExteriorResponse timbraCFDIComercioExteriorplus11Result;

    /**
     * Obtiene el valor de la propiedad timbraCFDIComercioExteriorplus11Result.
     * 
     * @return
     *     possible object is
     *     {@link TimbradoPlusComercioExteriorResponse }
     *     
     */
    public TimbradoPlusComercioExteriorResponse getTimbraCFDIComercioExteriorplus11Result() {
        return timbraCFDIComercioExteriorplus11Result;
    }

    /**
     * Define el valor de la propiedad timbraCFDIComercioExteriorplus11Result.
     * 
     * @param value
     *     allowed object is
     *     {@link TimbradoPlusComercioExteriorResponse }
     *     
     */
    public void setTimbraCFDIComercioExteriorplus11Result(TimbradoPlusComercioExteriorResponse value) {
        this.timbraCFDIComercioExteriorplus11Result = value;
    }

}
