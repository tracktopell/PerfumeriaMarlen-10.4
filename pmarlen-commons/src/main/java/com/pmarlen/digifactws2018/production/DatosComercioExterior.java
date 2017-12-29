
package com.pmarlen.digifactws2018.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DatosComercioExterior complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DatosComercioExterior">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TipoOperacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ClaveDePedimento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CertificadoOrigen" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="NumCertificadoOrigen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroExportacionConfiable" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Incoterm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SubDivision" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Observaciones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoCambioUSD" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TotalUSD" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatosComercioExterior", propOrder = {
    "tipoOperacion",
    "claveDePedimento",
    "certificadoOrigen",
    "numCertificadoOrigen",
    "numeroExportacionConfiable",
    "incoterm",
    "subDivision",
    "observaciones",
    "tipoCambioUSD",
    "totalUSD"
})
public class DatosComercioExterior {

    @XmlElement(name = "TipoOperacion")
    protected String tipoOperacion;
    @XmlElement(name = "ClaveDePedimento")
    protected String claveDePedimento;
    @XmlElement(name = "CertificadoOrigen")
    protected int certificadoOrigen;
    @XmlElement(name = "NumCertificadoOrigen")
    protected String numCertificadoOrigen;
    @XmlElement(name = "NumeroExportacionConfiable")
    protected String numeroExportacionConfiable;
    @XmlElement(name = "Incoterm")
    protected String incoterm;
    @XmlElement(name = "SubDivision")
    protected int subDivision;
    @XmlElement(name = "Observaciones")
    protected String observaciones;
    @XmlElement(name = "TipoCambioUSD")
    protected double tipoCambioUSD;
    @XmlElement(name = "TotalUSD")
    protected double totalUSD;

    /**
     * Obtiene el valor de la propiedad tipoOperacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoOperacion() {
        return tipoOperacion;
    }

    /**
     * Define el valor de la propiedad tipoOperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoOperacion(String value) {
        this.tipoOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad claveDePedimento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaveDePedimento() {
        return claveDePedimento;
    }

    /**
     * Define el valor de la propiedad claveDePedimento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaveDePedimento(String value) {
        this.claveDePedimento = value;
    }

    /**
     * Obtiene el valor de la propiedad certificadoOrigen.
     * 
     */
    public int getCertificadoOrigen() {
        return certificadoOrigen;
    }

    /**
     * Define el valor de la propiedad certificadoOrigen.
     * 
     */
    public void setCertificadoOrigen(int value) {
        this.certificadoOrigen = value;
    }

    /**
     * Obtiene el valor de la propiedad numCertificadoOrigen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumCertificadoOrigen() {
        return numCertificadoOrigen;
    }

    /**
     * Define el valor de la propiedad numCertificadoOrigen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumCertificadoOrigen(String value) {
        this.numCertificadoOrigen = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroExportacionConfiable.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroExportacionConfiable() {
        return numeroExportacionConfiable;
    }

    /**
     * Define el valor de la propiedad numeroExportacionConfiable.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroExportacionConfiable(String value) {
        this.numeroExportacionConfiable = value;
    }

    /**
     * Obtiene el valor de la propiedad incoterm.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncoterm() {
        return incoterm;
    }

    /**
     * Define el valor de la propiedad incoterm.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncoterm(String value) {
        this.incoterm = value;
    }

    /**
     * Obtiene el valor de la propiedad subDivision.
     * 
     */
    public int getSubDivision() {
        return subDivision;
    }

    /**
     * Define el valor de la propiedad subDivision.
     * 
     */
    public void setSubDivision(int value) {
        this.subDivision = value;
    }

    /**
     * Obtiene el valor de la propiedad observaciones.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Define el valor de la propiedad observaciones.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservaciones(String value) {
        this.observaciones = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoCambioUSD.
     * 
     */
    public double getTipoCambioUSD() {
        return tipoCambioUSD;
    }

    /**
     * Define el valor de la propiedad tipoCambioUSD.
     * 
     */
    public void setTipoCambioUSD(double value) {
        this.tipoCambioUSD = value;
    }

    /**
     * Obtiene el valor de la propiedad totalUSD.
     * 
     */
    public double getTotalUSD() {
        return totalUSD;
    }

    /**
     * Define el valor de la propiedad totalUSD.
     * 
     */
    public void setTotalUSD(double value) {
        this.totalUSD = value;
    }

}
