
package com.pmarlen.digifactws2018.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DetalleAuxCuentas complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DetalleAuxCuentas">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Fecha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumIdentificadorPoliza" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Concepto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Debe" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Haber" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DetalleAuxCuentas", propOrder = {
    "fecha",
    "numIdentificadorPoliza",
    "concepto",
    "debe",
    "haber"
})
public class DetalleAuxCuentas {

    @XmlElement(name = "Fecha")
    protected String fecha;
    @XmlElement(name = "NumIdentificadorPoliza")
    protected String numIdentificadorPoliza;
    @XmlElement(name = "Concepto")
    protected String concepto;
    @XmlElement(name = "Debe")
    protected double debe;
    @XmlElement(name = "Haber")
    protected double haber;

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
     * Obtiene el valor de la propiedad numIdentificadorPoliza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumIdentificadorPoliza() {
        return numIdentificadorPoliza;
    }

    /**
     * Define el valor de la propiedad numIdentificadorPoliza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumIdentificadorPoliza(String value) {
        this.numIdentificadorPoliza = value;
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

}
