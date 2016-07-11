
package com.pmarlen.digifactws20160707.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Mercancia complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Mercancia">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NoIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FraccionArancelaria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CantidadAduana" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="UnidadAduana" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ValorUnitarioAduana" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ValorDolares" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="DescripcionesEspecificas" type="{https://cfd.sicofi.com.mx}ArrayOfDescripcionEspecifica" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Mercancia", propOrder = {
    "noIdentificacion",
    "fraccionArancelaria",
    "cantidadAduana",
    "unidadAduana",
    "valorUnitarioAduana",
    "valorDolares",
    "descripcionesEspecificas"
})
public class Mercancia {

    @XmlElement(name = "NoIdentificacion")
    protected String noIdentificacion;
    @XmlElement(name = "FraccionArancelaria")
    protected String fraccionArancelaria;
    @XmlElement(name = "CantidadAduana")
    protected double cantidadAduana;
    @XmlElement(name = "UnidadAduana")
    protected String unidadAduana;
    @XmlElement(name = "ValorUnitarioAduana")
    protected double valorUnitarioAduana;
    @XmlElement(name = "ValorDolares")
    protected double valorDolares;
    @XmlElement(name = "DescripcionesEspecificas")
    protected ArrayOfDescripcionEspecifica descripcionesEspecificas;

    /**
     * Obtiene el valor de la propiedad noIdentificacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoIdentificacion() {
        return noIdentificacion;
    }

    /**
     * Define el valor de la propiedad noIdentificacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoIdentificacion(String value) {
        this.noIdentificacion = value;
    }

    /**
     * Obtiene el valor de la propiedad fraccionArancelaria.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFraccionArancelaria() {
        return fraccionArancelaria;
    }

    /**
     * Define el valor de la propiedad fraccionArancelaria.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFraccionArancelaria(String value) {
        this.fraccionArancelaria = value;
    }

    /**
     * Obtiene el valor de la propiedad cantidadAduana.
     * 
     */
    public double getCantidadAduana() {
        return cantidadAduana;
    }

    /**
     * Define el valor de la propiedad cantidadAduana.
     * 
     */
    public void setCantidadAduana(double value) {
        this.cantidadAduana = value;
    }

    /**
     * Obtiene el valor de la propiedad unidadAduana.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnidadAduana() {
        return unidadAduana;
    }

    /**
     * Define el valor de la propiedad unidadAduana.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnidadAduana(String value) {
        this.unidadAduana = value;
    }

    /**
     * Obtiene el valor de la propiedad valorUnitarioAduana.
     * 
     */
    public double getValorUnitarioAduana() {
        return valorUnitarioAduana;
    }

    /**
     * Define el valor de la propiedad valorUnitarioAduana.
     * 
     */
    public void setValorUnitarioAduana(double value) {
        this.valorUnitarioAduana = value;
    }

    /**
     * Obtiene el valor de la propiedad valorDolares.
     * 
     */
    public double getValorDolares() {
        return valorDolares;
    }

    /**
     * Define el valor de la propiedad valorDolares.
     * 
     */
    public void setValorDolares(double value) {
        this.valorDolares = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcionesEspecificas.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDescripcionEspecifica }
     *     
     */
    public ArrayOfDescripcionEspecifica getDescripcionesEspecificas() {
        return descripcionesEspecificas;
    }

    /**
     * Define el valor de la propiedad descripcionesEspecificas.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDescripcionEspecifica }
     *     
     */
    public void setDescripcionesEspecificas(ArrayOfDescripcionEspecifica value) {
        this.descripcionesEspecificas = value;
    }

}
