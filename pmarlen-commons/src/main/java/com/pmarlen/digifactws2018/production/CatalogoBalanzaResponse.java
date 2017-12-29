
package com.pmarlen.digifactws2018.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CatalogoBalanzaResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CatalogoBalanzaResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="XMLCatalogo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="XMLBalanza" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ErrorCatalogo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ErrorBalanza" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CatalogoCorrecto" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="BalanzaCorrecto" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CatalogoBalanzaResponse", propOrder = {
    "xmlCatalogo",
    "xmlBalanza",
    "errorCatalogo",
    "errorBalanza",
    "catalogoCorrecto",
    "balanzaCorrecto"
})
public class CatalogoBalanzaResponse {

    @XmlElement(name = "XMLCatalogo")
    protected String xmlCatalogo;
    @XmlElement(name = "XMLBalanza")
    protected String xmlBalanza;
    @XmlElement(name = "ErrorCatalogo")
    protected String errorCatalogo;
    @XmlElement(name = "ErrorBalanza")
    protected String errorBalanza;
    @XmlElement(name = "CatalogoCorrecto")
    protected boolean catalogoCorrecto;
    @XmlElement(name = "BalanzaCorrecto")
    protected boolean balanzaCorrecto;

    /**
     * Obtiene el valor de la propiedad xmlCatalogo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXMLCatalogo() {
        return xmlCatalogo;
    }

    /**
     * Define el valor de la propiedad xmlCatalogo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXMLCatalogo(String value) {
        this.xmlCatalogo = value;
    }

    /**
     * Obtiene el valor de la propiedad xmlBalanza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXMLBalanza() {
        return xmlBalanza;
    }

    /**
     * Define el valor de la propiedad xmlBalanza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXMLBalanza(String value) {
        this.xmlBalanza = value;
    }

    /**
     * Obtiene el valor de la propiedad errorCatalogo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorCatalogo() {
        return errorCatalogo;
    }

    /**
     * Define el valor de la propiedad errorCatalogo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorCatalogo(String value) {
        this.errorCatalogo = value;
    }

    /**
     * Obtiene el valor de la propiedad errorBalanza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorBalanza() {
        return errorBalanza;
    }

    /**
     * Define el valor de la propiedad errorBalanza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorBalanza(String value) {
        this.errorBalanza = value;
    }

    /**
     * Obtiene el valor de la propiedad catalogoCorrecto.
     * 
     */
    public boolean isCatalogoCorrecto() {
        return catalogoCorrecto;
    }

    /**
     * Define el valor de la propiedad catalogoCorrecto.
     * 
     */
    public void setCatalogoCorrecto(boolean value) {
        this.catalogoCorrecto = value;
    }

    /**
     * Obtiene el valor de la propiedad balanzaCorrecto.
     * 
     */
    public boolean isBalanzaCorrecto() {
        return balanzaCorrecto;
    }

    /**
     * Define el valor de la propiedad balanzaCorrecto.
     * 
     */
    public void setBalanzaCorrecto(boolean value) {
        this.balanzaCorrecto = value;
    }

}
