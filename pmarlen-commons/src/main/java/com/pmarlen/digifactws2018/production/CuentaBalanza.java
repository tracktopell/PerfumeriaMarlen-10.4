
package com.pmarlen.digifactws2018.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CuentaBalanza complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CuentaBalanza">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NumeroCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoInicial" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Debe" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Haber" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="SaldoFinal" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CuentaBalanza", propOrder = {
    "numeroCuenta",
    "saldoInicial",
    "debe",
    "haber",
    "saldoFinal"
})
public class CuentaBalanza {

    @XmlElement(name = "NumeroCuenta")
    protected String numeroCuenta;
    @XmlElement(name = "SaldoInicial")
    protected double saldoInicial;
    @XmlElement(name = "Debe")
    protected double debe;
    @XmlElement(name = "Haber")
    protected double haber;
    @XmlElement(name = "SaldoFinal")
    protected double saldoFinal;

    /**
     * Obtiene el valor de la propiedad numeroCuenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * Define el valor de la propiedad numeroCuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCuenta(String value) {
        this.numeroCuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad saldoInicial.
     * 
     */
    public double getSaldoInicial() {
        return saldoInicial;
    }

    /**
     * Define el valor de la propiedad saldoInicial.
     * 
     */
    public void setSaldoInicial(double value) {
        this.saldoInicial = value;
    }

    /**
     * Obtiene el valor de la propiedad debe.
     * 
     */
    public double getDebe() {
        return debe;
    }

    /**
     * Define el valor de la propiedad debe.
     * 
     */
    public void setDebe(double value) {
        this.debe = value;
    }

    /**
     * Obtiene el valor de la propiedad haber.
     * 
     */
    public double getHaber() {
        return haber;
    }

    /**
     * Define el valor de la propiedad haber.
     * 
     */
    public void setHaber(double value) {
        this.haber = value;
    }

    /**
     * Obtiene el valor de la propiedad saldoFinal.
     * 
     */
    public double getSaldoFinal() {
        return saldoFinal;
    }

    /**
     * Define el valor de la propiedad saldoFinal.
     * 
     */
    public void setSaldoFinal(double value) {
        this.saldoFinal = value;
    }

}
