
package com.pmarlen.digifactws2018.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Dividendos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Dividendos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IncluirComplemento" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Clave" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MontoRetencionNacional" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="MontoRetencionExtranjero" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="MontoDividendoExtranjero" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TipoDividendo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MontoISRNacional" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="DividendoAcumulableNacional" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="DividendoAcumulableExtranjero" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ProporcionRem" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Dividendos", propOrder = {
    "incluirComplemento",
    "clave",
    "montoRetencionNacional",
    "montoRetencionExtranjero",
    "montoDividendoExtranjero",
    "tipoDividendo",
    "montoISRNacional",
    "dividendoAcumulableNacional",
    "dividendoAcumulableExtranjero",
    "proporcionRem"
})
public class Dividendos {

    @XmlElement(name = "IncluirComplemento")
    protected boolean incluirComplemento;
    @XmlElement(name = "Clave")
    protected int clave;
    @XmlElement(name = "MontoRetencionNacional")
    protected double montoRetencionNacional;
    @XmlElement(name = "MontoRetencionExtranjero")
    protected double montoRetencionExtranjero;
    @XmlElement(name = "MontoDividendoExtranjero")
    protected double montoDividendoExtranjero;
    @XmlElement(name = "TipoDividendo")
    protected String tipoDividendo;
    @XmlElement(name = "MontoISRNacional")
    protected double montoISRNacional;
    @XmlElement(name = "DividendoAcumulableNacional")
    protected double dividendoAcumulableNacional;
    @XmlElement(name = "DividendoAcumulableExtranjero")
    protected double dividendoAcumulableExtranjero;
    @XmlElement(name = "ProporcionRem")
    protected double proporcionRem;

    /**
     * Obtiene el valor de la propiedad incluirComplemento.
     * 
     */
    public boolean isIncluirComplemento() {
        return incluirComplemento;
    }

    /**
     * Define el valor de la propiedad incluirComplemento.
     * 
     */
    public void setIncluirComplemento(boolean value) {
        this.incluirComplemento = value;
    }

    /**
     * Obtiene el valor de la propiedad clave.
     * 
     */
    public int getClave() {
        return clave;
    }

    /**
     * Define el valor de la propiedad clave.
     * 
     */
    public void setClave(int value) {
        this.clave = value;
    }

    /**
     * Obtiene el valor de la propiedad montoRetencionNacional.
     * 
     */
    public double getMontoRetencionNacional() {
        return montoRetencionNacional;
    }

    /**
     * Define el valor de la propiedad montoRetencionNacional.
     * 
     */
    public void setMontoRetencionNacional(double value) {
        this.montoRetencionNacional = value;
    }

    /**
     * Obtiene el valor de la propiedad montoRetencionExtranjero.
     * 
     */
    public double getMontoRetencionExtranjero() {
        return montoRetencionExtranjero;
    }

    /**
     * Define el valor de la propiedad montoRetencionExtranjero.
     * 
     */
    public void setMontoRetencionExtranjero(double value) {
        this.montoRetencionExtranjero = value;
    }

    /**
     * Obtiene el valor de la propiedad montoDividendoExtranjero.
     * 
     */
    public double getMontoDividendoExtranjero() {
        return montoDividendoExtranjero;
    }

    /**
     * Define el valor de la propiedad montoDividendoExtranjero.
     * 
     */
    public void setMontoDividendoExtranjero(double value) {
        this.montoDividendoExtranjero = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoDividendo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDividendo() {
        return tipoDividendo;
    }

    /**
     * Define el valor de la propiedad tipoDividendo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDividendo(String value) {
        this.tipoDividendo = value;
    }

    /**
     * Obtiene el valor de la propiedad montoISRNacional.
     * 
     */
    public double getMontoISRNacional() {
        return montoISRNacional;
    }

    /**
     * Define el valor de la propiedad montoISRNacional.
     * 
     */
    public void setMontoISRNacional(double value) {
        this.montoISRNacional = value;
    }

    /**
     * Obtiene el valor de la propiedad dividendoAcumulableNacional.
     * 
     */
    public double getDividendoAcumulableNacional() {
        return dividendoAcumulableNacional;
    }

    /**
     * Define el valor de la propiedad dividendoAcumulableNacional.
     * 
     */
    public void setDividendoAcumulableNacional(double value) {
        this.dividendoAcumulableNacional = value;
    }

    /**
     * Obtiene el valor de la propiedad dividendoAcumulableExtranjero.
     * 
     */
    public double getDividendoAcumulableExtranjero() {
        return dividendoAcumulableExtranjero;
    }

    /**
     * Define el valor de la propiedad dividendoAcumulableExtranjero.
     * 
     */
    public void setDividendoAcumulableExtranjero(double value) {
        this.dividendoAcumulableExtranjero = value;
    }

    /**
     * Obtiene el valor de la propiedad proporcionRem.
     * 
     */
    public double getProporcionRem() {
        return proporcionRem;
    }

    /**
     * Define el valor de la propiedad proporcionRem.
     * 
     */
    public void setProporcionRem(double value) {
        this.proporcionRem = value;
    }

}
