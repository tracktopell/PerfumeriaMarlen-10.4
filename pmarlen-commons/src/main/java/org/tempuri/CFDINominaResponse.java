
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CFDINominaResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CFDINominaResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="XMLNomina" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ErrorNomina" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NominaCorrecta" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CFDINominaResponse", propOrder = {
    "xmlNomina",
    "codigoError",
    "errorNomina",
    "nominaCorrecta"
})
public class CFDINominaResponse {

    @XmlElement(name = "XMLNomina")
    protected String xmlNomina;
    @XmlElement(name = "CodigoError")
    protected String codigoError;
    @XmlElement(name = "ErrorNomina")
    protected String errorNomina;
    @XmlElement(name = "NominaCorrecta")
    protected boolean nominaCorrecta;

    /**
     * Obtiene el valor de la propiedad xmlNomina.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXMLNomina() {
        return xmlNomina;
    }

    /**
     * Define el valor de la propiedad xmlNomina.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXMLNomina(String value) {
        this.xmlNomina = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoError.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoError() {
        return codigoError;
    }

    /**
     * Define el valor de la propiedad codigoError.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoError(String value) {
        this.codigoError = value;
    }

    /**
     * Obtiene el valor de la propiedad errorNomina.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorNomina() {
        return errorNomina;
    }

    /**
     * Define el valor de la propiedad errorNomina.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorNomina(String value) {
        this.errorNomina = value;
    }

    /**
     * Obtiene el valor de la propiedad nominaCorrecta.
     * 
     */
    public boolean isNominaCorrecta() {
        return nominaCorrecta;
    }

    /**
     * Define el valor de la propiedad nominaCorrecta.
     * 
     */
    public void setNominaCorrecta(boolean value) {
        this.nominaCorrecta = value;
    }

}
