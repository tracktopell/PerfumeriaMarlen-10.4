
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CFDIComercioExteriorResponse33 complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CFDIComercioExteriorResponse33">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="XMLComercioExterior" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ErrorComercioExterior" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ComercioExteriorCorrecto" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CFDIComercioExteriorResponse33", propOrder = {
    "xmlComercioExterior",
    "codigoError",
    "errorComercioExterior",
    "comercioExteriorCorrecto"
})
public class CFDIComercioExteriorResponse33 {

    @XmlElement(name = "XMLComercioExterior")
    protected String xmlComercioExterior;
    @XmlElement(name = "CodigoError")
    protected String codigoError;
    @XmlElement(name = "ErrorComercioExterior")
    protected String errorComercioExterior;
    @XmlElement(name = "ComercioExteriorCorrecto")
    protected boolean comercioExteriorCorrecto;

    /**
     * Obtiene el valor de la propiedad xmlComercioExterior.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXMLComercioExterior() {
        return xmlComercioExterior;
    }

    /**
     * Define el valor de la propiedad xmlComercioExterior.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXMLComercioExterior(String value) {
        this.xmlComercioExterior = value;
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
     * Obtiene el valor de la propiedad errorComercioExterior.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorComercioExterior() {
        return errorComercioExterior;
    }

    /**
     * Define el valor de la propiedad errorComercioExterior.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorComercioExterior(String value) {
        this.errorComercioExterior = value;
    }

    /**
     * Obtiene el valor de la propiedad comercioExteriorCorrecto.
     * 
     */
    public boolean isComercioExteriorCorrecto() {
        return comercioExteriorCorrecto;
    }

    /**
     * Define el valor de la propiedad comercioExteriorCorrecto.
     * 
     */
    public void setComercioExteriorCorrecto(boolean value) {
        this.comercioExteriorCorrecto = value;
    }

}
