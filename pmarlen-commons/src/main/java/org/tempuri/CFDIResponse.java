
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CFDIResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CFDIResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="XMLCFDI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ErrorCFDI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CFDICorrecto" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CFDIResponse", propOrder = {
    "xmlcfdi",
    "codigoError",
    "errorCFDI",
    "cfdiCorrecto"
})
public class CFDIResponse {

    @XmlElement(name = "XMLCFDI")
    protected String xmlcfdi;
    @XmlElement(name = "CodigoError")
    protected String codigoError;
    @XmlElement(name = "ErrorCFDI")
    protected String errorCFDI;
    @XmlElement(name = "CFDICorrecto")
    protected boolean cfdiCorrecto;

    /**
     * Obtiene el valor de la propiedad xmlcfdi.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXMLCFDI() {
        return xmlcfdi;
    }

    /**
     * Define el valor de la propiedad xmlcfdi.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXMLCFDI(String value) {
        this.xmlcfdi = value;
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
     * Obtiene el valor de la propiedad errorCFDI.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorCFDI() {
        return errorCFDI;
    }

    /**
     * Define el valor de la propiedad errorCFDI.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorCFDI(String value) {
        this.errorCFDI = value;
    }

    /**
     * Obtiene el valor de la propiedad cfdiCorrecto.
     * 
     */
    public boolean isCFDICorrecto() {
        return cfdiCorrecto;
    }

    /**
     * Define el valor de la propiedad cfdiCorrecto.
     * 
     */
    public void setCFDICorrecto(boolean value) {
        this.cfdiCorrecto = value;
    }

}
