
package com.pmarlen.digifactws2018.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Transferencia complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Transferencia">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CuentaOrigen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Numero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BancoOrigenNacional" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="BancoOrigenExtranjero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroCuentaDestino" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BancoDestinoNacional" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="BancoDestinoExtranjero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fecha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Beneficiario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RFC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Monto" type="{http://www.w3.org/2001/XMLSchema}double"/>
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
@XmlType(name = "Transferencia", propOrder = {
    "cuentaOrigen",
    "numero",
    "bancoOrigenNacional",
    "bancoOrigenExtranjero",
    "numeroCuentaDestino",
    "bancoDestinoNacional",
    "bancoDestinoExtranjero",
    "fecha",
    "beneficiario",
    "rfc",
    "monto",
    "moneda",
    "tipoCambio"
})
public class Transferencia {

    @XmlElement(name = "CuentaOrigen")
    protected String cuentaOrigen;
    @XmlElement(name = "Numero")
    protected String numero;
    @XmlElement(name = "BancoOrigenNacional")
    protected int bancoOrigenNacional;
    @XmlElement(name = "BancoOrigenExtranjero")
    protected String bancoOrigenExtranjero;
    @XmlElement(name = "NumeroCuentaDestino")
    protected String numeroCuentaDestino;
    @XmlElement(name = "BancoDestinoNacional")
    protected int bancoDestinoNacional;
    @XmlElement(name = "BancoDestinoExtranjero")
    protected String bancoDestinoExtranjero;
    @XmlElement(name = "Fecha")
    protected String fecha;
    @XmlElement(name = "Beneficiario")
    protected String beneficiario;
    @XmlElement(name = "RFC")
    protected String rfc;
    @XmlElement(name = "Monto")
    protected double monto;
    @XmlElement(name = "Moneda")
    protected String moneda;
    @XmlElement(name = "TipoCambio")
    protected double tipoCambio;

    /**
     * Obtiene el valor de la propiedad cuentaOrigen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuentaOrigen() {
        return cuentaOrigen;
    }

    /**
     * Define el valor de la propiedad cuentaOrigen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuentaOrigen(String value) {
        this.cuentaOrigen = value;
    }

    /**
     * Obtiene el valor de la propiedad numero.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Define el valor de la propiedad numero.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumero(String value) {
        this.numero = value;
    }

    /**
     * Obtiene el valor de la propiedad bancoOrigenNacional.
     * 
     */
    public int getBancoOrigenNacional() {
        return bancoOrigenNacional;
    }

    /**
     * Define el valor de la propiedad bancoOrigenNacional.
     * 
     */
    public void setBancoOrigenNacional(int value) {
        this.bancoOrigenNacional = value;
    }

    /**
     * Obtiene el valor de la propiedad bancoOrigenExtranjero.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBancoOrigenExtranjero() {
        return bancoOrigenExtranjero;
    }

    /**
     * Define el valor de la propiedad bancoOrigenExtranjero.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBancoOrigenExtranjero(String value) {
        this.bancoOrigenExtranjero = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroCuentaDestino.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCuentaDestino() {
        return numeroCuentaDestino;
    }

    /**
     * Define el valor de la propiedad numeroCuentaDestino.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCuentaDestino(String value) {
        this.numeroCuentaDestino = value;
    }

    /**
     * Obtiene el valor de la propiedad bancoDestinoNacional.
     * 
     */
    public int getBancoDestinoNacional() {
        return bancoDestinoNacional;
    }

    /**
     * Define el valor de la propiedad bancoDestinoNacional.
     * 
     */
    public void setBancoDestinoNacional(int value) {
        this.bancoDestinoNacional = value;
    }

    /**
     * Obtiene el valor de la propiedad bancoDestinoExtranjero.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBancoDestinoExtranjero() {
        return bancoDestinoExtranjero;
    }

    /**
     * Define el valor de la propiedad bancoDestinoExtranjero.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBancoDestinoExtranjero(String value) {
        this.bancoDestinoExtranjero = value;
    }

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecha(String value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad beneficiario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeneficiario() {
        return beneficiario;
    }

    /**
     * Define el valor de la propiedad beneficiario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeneficiario(String value) {
        this.beneficiario = value;
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
     * Obtiene el valor de la propiedad monto.
     * 
     */
    public double getMonto() {
        return monto;
    }

    /**
     * Define el valor de la propiedad monto.
     * 
     */
    public void setMonto(double value) {
        this.monto = value;
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
