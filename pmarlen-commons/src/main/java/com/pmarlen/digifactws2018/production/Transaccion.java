
package com.pmarlen.digifactws2018.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Transaccion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Transaccion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NumeroCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Concepto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Debe" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Haber" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Nacionales" type="{https://cfd.sicofi.com.mx}ArrayOfComprobanteNacional" minOccurs="0"/>
 *         &lt;element name="Relacionados" type="{https://cfd.sicofi.com.mx}ArrayOfComprobanteNacionalRelacionado" minOccurs="0"/>
 *         &lt;element name="Extranjeros" type="{https://cfd.sicofi.com.mx}ArrayOfComprobanteExtranjero" minOccurs="0"/>
 *         &lt;element name="Cheques" type="{https://cfd.sicofi.com.mx}ArrayOfCheque" minOccurs="0"/>
 *         &lt;element name="Transferencias" type="{https://cfd.sicofi.com.mx}ArrayOfTransferencia" minOccurs="0"/>
 *         &lt;element name="OtrosMetodosPago" type="{https://cfd.sicofi.com.mx}ArrayOfOtroMetodoPago" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Transaccion", propOrder = {
    "numeroCuenta",
    "descripcion",
    "concepto",
    "debe",
    "haber",
    "nacionales",
    "relacionados",
    "extranjeros",
    "cheques",
    "transferencias",
    "otrosMetodosPago"
})
public class Transaccion {

    @XmlElement(name = "NumeroCuenta")
    protected String numeroCuenta;
    @XmlElement(name = "Descripcion")
    protected String descripcion;
    @XmlElement(name = "Concepto")
    protected String concepto;
    @XmlElement(name = "Debe")
    protected double debe;
    @XmlElement(name = "Haber")
    protected double haber;
    @XmlElement(name = "Nacionales")
    protected ArrayOfComprobanteNacional nacionales;
    @XmlElement(name = "Relacionados")
    protected ArrayOfComprobanteNacionalRelacionado relacionados;
    @XmlElement(name = "Extranjeros")
    protected ArrayOfComprobanteExtranjero extranjeros;
    @XmlElement(name = "Cheques")
    protected ArrayOfCheque cheques;
    @XmlElement(name = "Transferencias")
    protected ArrayOfTransferencia transferencias;
    @XmlElement(name = "OtrosMetodosPago")
    protected ArrayOfOtroMetodoPago otrosMetodosPago;

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
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad concepto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConcepto() {
        return concepto;
    }

    /**
     * Define el valor de la propiedad concepto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConcepto(String value) {
        this.concepto = value;
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
     * Obtiene el valor de la propiedad nacionales.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfComprobanteNacional }
     *     
     */
    public ArrayOfComprobanteNacional getNacionales() {
        return nacionales;
    }

    /**
     * Define el valor de la propiedad nacionales.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfComprobanteNacional }
     *     
     */
    public void setNacionales(ArrayOfComprobanteNacional value) {
        this.nacionales = value;
    }

    /**
     * Obtiene el valor de la propiedad relacionados.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfComprobanteNacionalRelacionado }
     *     
     */
    public ArrayOfComprobanteNacionalRelacionado getRelacionados() {
        return relacionados;
    }

    /**
     * Define el valor de la propiedad relacionados.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfComprobanteNacionalRelacionado }
     *     
     */
    public void setRelacionados(ArrayOfComprobanteNacionalRelacionado value) {
        this.relacionados = value;
    }

    /**
     * Obtiene el valor de la propiedad extranjeros.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfComprobanteExtranjero }
     *     
     */
    public ArrayOfComprobanteExtranjero getExtranjeros() {
        return extranjeros;
    }

    /**
     * Define el valor de la propiedad extranjeros.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfComprobanteExtranjero }
     *     
     */
    public void setExtranjeros(ArrayOfComprobanteExtranjero value) {
        this.extranjeros = value;
    }

    /**
     * Obtiene el valor de la propiedad cheques.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCheque }
     *     
     */
    public ArrayOfCheque getCheques() {
        return cheques;
    }

    /**
     * Define el valor de la propiedad cheques.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCheque }
     *     
     */
    public void setCheques(ArrayOfCheque value) {
        this.cheques = value;
    }

    /**
     * Obtiene el valor de la propiedad transferencias.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTransferencia }
     *     
     */
    public ArrayOfTransferencia getTransferencias() {
        return transferencias;
    }

    /**
     * Define el valor de la propiedad transferencias.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTransferencia }
     *     
     */
    public void setTransferencias(ArrayOfTransferencia value) {
        this.transferencias = value;
    }

    /**
     * Obtiene el valor de la propiedad otrosMetodosPago.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfOtroMetodoPago }
     *     
     */
    public ArrayOfOtroMetodoPago getOtrosMetodosPago() {
        return otrosMetodosPago;
    }

    /**
     * Define el valor de la propiedad otrosMetodosPago.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfOtroMetodoPago }
     *     
     */
    public void setOtrosMetodosPago(ArrayOfOtroMetodoPago value) {
        this.otrosMetodosPago = value;
    }

}
