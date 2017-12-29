
package com.pmarlen.digifactws2018.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CuentaCatalogo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CuentaCatalogo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodigoAgrupador" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="NumeroCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NombreCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SubCuentaDe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nivel" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Naturaleza" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CuentaCatalogo", propOrder = {
    "codigoAgrupador",
    "numeroCuenta",
    "nombreCuenta",
    "subCuentaDe",
    "nivel",
    "naturaleza"
})
public class CuentaCatalogo {

    @XmlElement(name = "CodigoAgrupador")
    protected double codigoAgrupador;
    @XmlElement(name = "NumeroCuenta")
    protected String numeroCuenta;
    @XmlElement(name = "NombreCuenta")
    protected String nombreCuenta;
    @XmlElement(name = "SubCuentaDe")
    protected String subCuentaDe;
    @XmlElement(name = "Nivel")
    protected int nivel;
    @XmlElement(name = "Naturaleza")
    protected String naturaleza;

    /**
     * Obtiene el valor de la propiedad codigoAgrupador.
     * 
     */
    public double getCodigoAgrupador() {
        return codigoAgrupador;
    }

    /**
     * Define el valor de la propiedad codigoAgrupador.
     * 
     */
    public void setCodigoAgrupador(double value) {
        this.codigoAgrupador = value;
    }

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
     * Obtiene el valor de la propiedad nombreCuenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreCuenta() {
        return nombreCuenta;
    }

    /**
     * Define el valor de la propiedad nombreCuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreCuenta(String value) {
        this.nombreCuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad subCuentaDe.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubCuentaDe() {
        return subCuentaDe;
    }

    /**
     * Define el valor de la propiedad subCuentaDe.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubCuentaDe(String value) {
        this.subCuentaDe = value;
    }

    /**
     * Obtiene el valor de la propiedad nivel.
     * 
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * Define el valor de la propiedad nivel.
     * 
     */
    public void setNivel(int value) {
        this.nivel = value;
    }

    /**
     * Obtiene el valor de la propiedad naturaleza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNaturaleza() {
        return naturaleza;
    }

    /**
     * Define el valor de la propiedad naturaleza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNaturaleza(String value) {
        this.naturaleza = value;
    }

}
