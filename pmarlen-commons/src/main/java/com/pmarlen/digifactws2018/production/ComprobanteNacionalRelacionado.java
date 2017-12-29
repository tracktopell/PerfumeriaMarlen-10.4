
package com.pmarlen.digifactws2018.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ComprobanteNacionalRelacionado complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ComprobanteNacionalRelacionado">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CFDSerie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CFDFolio" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MontoTotal" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="RFC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Moneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoCambio" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComprobanteNacionalRelacionado", propOrder = {
    "cfdSerie",
    "cfdFolio",
    "montoTotal",
    "rfc",
    "moneda",
    "tipoCambio"
})
@XmlSeeAlso({
    ComprobanteNacionalRelacionadoAuxiliar.class
})
public class ComprobanteNacionalRelacionado {

    @XmlElement(name = "CFDSerie")
    protected String cfdSerie;
    @XmlElement(name = "CFDFolio")
    protected int cfdFolio;
    @XmlElement(name = "MontoTotal")
    protected double montoTotal;
    @XmlElement(name = "RFC")
    protected String rfc;
    @XmlElement(name = "Moneda")
    protected String moneda;
    @XmlElement(name = "TipoCambio")
    protected double tipoCambio;

    /**
     * Obtiene el valor de la propiedad cfdSerie.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCFDSerie() {
        return cfdSerie;
    }

    /**
     * Define el valor de la propiedad cfdSerie.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCFDSerie(String value) {
        this.cfdSerie = value;
    }

    /**
     * Obtiene el valor de la propiedad cfdFolio.
     * 
     */
    public int getCFDFolio() {
        return cfdFolio;
    }

    /**
     * Define el valor de la propiedad cfdFolio.
     * 
     */
    public void setCFDFolio(int value) {
        this.cfdFolio = value;
    }

    /**
     * Obtiene el valor de la propiedad montoTotal.
     * 
     */
    public double getMontoTotal() {
        return montoTotal;
    }

    /**
     * Define el valor de la propiedad montoTotal.
     * 
     */
    public void setMontoTotal(double value) {
        this.montoTotal = value;
    }

    /**
     * Obtiene el valor de la propiedad rfc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRFC() {
        return rfc;
    }

    /**
     * Define el valor de la propiedad rfc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRFC(String value) {
        this.rfc = value;
    }

    /**
     * Obtiene el valor de la propiedad moneda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMoneda() {
        return moneda;
    }

    /**
     * Define el valor de la propiedad moneda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMoneda(String value) {
        this.moneda = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoCambio.
     * 
     */
    public double getTipoCambio() {
        return tipoCambio;
    }

    /**
     * Define el valor de la propiedad tipoCambio.
     * 
     */
    public void setTipoCambio(double value) {
        this.tipoCambio = value;
    }

}
