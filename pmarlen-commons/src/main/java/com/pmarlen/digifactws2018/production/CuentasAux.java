
package com.pmarlen.digifactws2018.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CuentasAux complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CuentasAux">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NumeroCta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DescripcionCta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoInicial" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="SaldoFinal" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Detalles" type="{https://cfd.sicofi.com.mx}ArrayOfDetalleAuxCuentas" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CuentasAux", propOrder = {
    "numeroCta",
    "descripcionCta",
    "saldoInicial",
    "saldoFinal",
    "detalles"
})
public class CuentasAux {

    @XmlElement(name = "NumeroCta")
    protected String numeroCta;
    @XmlElement(name = "DescripcionCta")
    protected String descripcionCta;
    @XmlElement(name = "SaldoInicial")
    protected double saldoInicial;
    @XmlElement(name = "SaldoFinal")
    protected double saldoFinal;
    @XmlElement(name = "Detalles")
    protected ArrayOfDetalleAuxCuentas detalles;

    /**
     * Obtiene el valor de la propiedad numeroCta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCta() {
        return numeroCta;
    }

    /**
     * Define el valor de la propiedad numeroCta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCta(String value) {
        this.numeroCta = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcionCta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionCta() {
        return descripcionCta;
    }

    /**
     * Define el valor de la propiedad descripcionCta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionCta(String value) {
        this.descripcionCta = value;
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

    /**
     * Obtiene el valor de la propiedad detalles.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDetalleAuxCuentas }
     *     
     */
    public ArrayOfDetalleAuxCuentas getDetalles() {
        return detalles;
    }

    /**
     * Define el valor de la propiedad detalles.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDetalleAuxCuentas }
     *     
     */
    public void setDetalles(ArrayOfDetalleAuxCuentas value) {
        this.detalles = value;
    }

}
