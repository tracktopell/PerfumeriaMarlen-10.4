
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para SubContratacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="SubContratacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RfcLabora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PorcentajeTiempo" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubContratacion", propOrder = {
    "rfcLabora",
    "porcentajeTiempo"
})
public class SubContratacion {

    @XmlElement(name = "RfcLabora")
    protected String rfcLabora;
    @XmlElement(name = "PorcentajeTiempo")
    protected double porcentajeTiempo;

    /**
     * Obtiene el valor de la propiedad rfcLabora.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRfcLabora() {
        return rfcLabora;
    }

    /**
     * Define el valor de la propiedad rfcLabora.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRfcLabora(String value) {
        this.rfcLabora = value;
    }

    /**
     * Obtiene el valor de la propiedad porcentajeTiempo.
     * 
     */
    public double getPorcentajeTiempo() {
        return porcentajeTiempo;
    }

    /**
     * Define el valor de la propiedad porcentajeTiempo.
     * 
     */
    public void setPorcentajeTiempo(double value) {
        this.porcentajeTiempo = value;
    }

}
