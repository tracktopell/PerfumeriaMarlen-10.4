
package com.pmarlen.digifactws2018.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para InteresesHipotecarios complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="InteresesHipotecarios">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IncluirComplemento" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="CreditoDeInstitucion" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="SaldoInsoluto" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="DeducibleDelCredito" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="MontoInteresesNominalesDevegados" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="MontoInteresesNominalesDevegadosYPagados" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="MontoInteresesReales" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="NumeroDeContrato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InteresesHipotecarios", propOrder = {
    "incluirComplemento",
    "creditoDeInstitucion",
    "saldoInsoluto",
    "deducibleDelCredito",
    "montoInteresesNominalesDevegados",
    "montoInteresesNominalesDevegadosYPagados",
    "montoInteresesReales",
    "numeroDeContrato"
})
public class InteresesHipotecarios {

    @XmlElement(name = "IncluirComplemento")
    protected boolean incluirComplemento;
    @XmlElement(name = "CreditoDeInstitucion")
    protected boolean creditoDeInstitucion;
    @XmlElement(name = "SaldoInsoluto")
    protected double saldoInsoluto;
    @XmlElement(name = "DeducibleDelCredito")
    protected double deducibleDelCredito;
    @XmlElement(name = "MontoInteresesNominalesDevegados")
    protected double montoInteresesNominalesDevegados;
    @XmlElement(name = "MontoInteresesNominalesDevegadosYPagados")
    protected double montoInteresesNominalesDevegadosYPagados;
    @XmlElement(name = "MontoInteresesReales")
    protected double montoInteresesReales;
    @XmlElement(name = "NumeroDeContrato")
    protected String numeroDeContrato;

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
     * Obtiene el valor de la propiedad creditoDeInstitucion.
     * 
     */
    public boolean isCreditoDeInstitucion() {
        return creditoDeInstitucion;
    }

    /**
     * Define el valor de la propiedad creditoDeInstitucion.
     * 
     */
    public void setCreditoDeInstitucion(boolean value) {
        this.creditoDeInstitucion = value;
    }

    /**
     * Obtiene el valor de la propiedad saldoInsoluto.
     * 
     */
    public double getSaldoInsoluto() {
        return saldoInsoluto;
    }

    /**
     * Define el valor de la propiedad saldoInsoluto.
     * 
     */
    public void setSaldoInsoluto(double value) {
        this.saldoInsoluto = value;
    }

    /**
     * Obtiene el valor de la propiedad deducibleDelCredito.
     * 
     */
    public double getDeducibleDelCredito() {
        return deducibleDelCredito;
    }

    /**
     * Define el valor de la propiedad deducibleDelCredito.
     * 
     */
    public void setDeducibleDelCredito(double value) {
        this.deducibleDelCredito = value;
    }

    /**
     * Obtiene el valor de la propiedad montoInteresesNominalesDevegados.
     * 
     */
    public double getMontoInteresesNominalesDevegados() {
        return montoInteresesNominalesDevegados;
    }

    /**
     * Define el valor de la propiedad montoInteresesNominalesDevegados.
     * 
     */
    public void setMontoInteresesNominalesDevegados(double value) {
        this.montoInteresesNominalesDevegados = value;
    }

    /**
     * Obtiene el valor de la propiedad montoInteresesNominalesDevegadosYPagados.
     * 
     */
    public double getMontoInteresesNominalesDevegadosYPagados() {
        return montoInteresesNominalesDevegadosYPagados;
    }

    /**
     * Define el valor de la propiedad montoInteresesNominalesDevegadosYPagados.
     * 
     */
    public void setMontoInteresesNominalesDevegadosYPagados(double value) {
        this.montoInteresesNominalesDevegadosYPagados = value;
    }

    /**
     * Obtiene el valor de la propiedad montoInteresesReales.
     * 
     */
    public double getMontoInteresesReales() {
        return montoInteresesReales;
    }

    /**
     * Define el valor de la propiedad montoInteresesReales.
     * 
     */
    public void setMontoInteresesReales(double value) {
        this.montoInteresesReales = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroDeContrato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroDeContrato() {
        return numeroDeContrato;
    }

    /**
     * Define el valor de la propiedad numeroDeContrato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroDeContrato(String value) {
        this.numeroDeContrato = value;
    }

}
