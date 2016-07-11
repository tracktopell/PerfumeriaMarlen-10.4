
package com.pmarlen.digifactws20160707.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DatosRetencion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DatosRetencion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FechaExpedicion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Folio" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ClaveRetenciones" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DescripcionRetencion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MesInicial" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MesFin" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Ejercicio" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MontoTotalOperacion" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="MontoTotalGravado" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="MontoTotalExcento" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="MontoTotalRetenciones" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatosRetencion", propOrder = {
    "fechaExpedicion",
    "folio",
    "claveRetenciones",
    "descripcionRetencion",
    "mesInicial",
    "mesFin",
    "ejercicio",
    "montoTotalOperacion",
    "montoTotalGravado",
    "montoTotalExcento",
    "montoTotalRetenciones"
})
@XmlSeeAlso({
    DatosConstanciaRetencion.class
})
public class DatosRetencion {

    @XmlElement(name = "FechaExpedicion")
    protected String fechaExpedicion;
    @XmlElement(name = "Folio")
    protected int folio;
    @XmlElement(name = "ClaveRetenciones")
    protected int claveRetenciones;
    @XmlElement(name = "DescripcionRetencion")
    protected String descripcionRetencion;
    @XmlElement(name = "MesInicial")
    protected int mesInicial;
    @XmlElement(name = "MesFin")
    protected int mesFin;
    @XmlElement(name = "Ejercicio")
    protected int ejercicio;
    @XmlElement(name = "MontoTotalOperacion")
    protected double montoTotalOperacion;
    @XmlElement(name = "MontoTotalGravado")
    protected double montoTotalGravado;
    @XmlElement(name = "MontoTotalExcento")
    protected double montoTotalExcento;
    @XmlElement(name = "MontoTotalRetenciones")
    protected double montoTotalRetenciones;

    /**
     * Obtiene el valor de la propiedad fechaExpedicion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaExpedicion() {
        return fechaExpedicion;
    }

    /**
     * Define el valor de la propiedad fechaExpedicion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaExpedicion(String value) {
        this.fechaExpedicion = value;
    }

    /**
     * Obtiene el valor de la propiedad folio.
     * 
     */
    public int getFolio() {
        return folio;
    }

    /**
     * Define el valor de la propiedad folio.
     * 
     */
    public void setFolio(int value) {
        this.folio = value;
    }

    /**
     * Obtiene el valor de la propiedad claveRetenciones.
     * 
     */
    public int getClaveRetenciones() {
        return claveRetenciones;
    }

    /**
     * Define el valor de la propiedad claveRetenciones.
     * 
     */
    public void setClaveRetenciones(int value) {
        this.claveRetenciones = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcionRetencion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionRetencion() {
        return descripcionRetencion;
    }

    /**
     * Define el valor de la propiedad descripcionRetencion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionRetencion(String value) {
        this.descripcionRetencion = value;
    }

    /**
     * Obtiene el valor de la propiedad mesInicial.
     * 
     */
    public int getMesInicial() {
        return mesInicial;
    }

    /**
     * Define el valor de la propiedad mesInicial.
     * 
     */
    public void setMesInicial(int value) {
        this.mesInicial = value;
    }

    /**
     * Obtiene el valor de la propiedad mesFin.
     * 
     */
    public int getMesFin() {
        return mesFin;
    }

    /**
     * Define el valor de la propiedad mesFin.
     * 
     */
    public void setMesFin(int value) {
        this.mesFin = value;
    }

    /**
     * Obtiene el valor de la propiedad ejercicio.
     * 
     */
    public int getEjercicio() {
        return ejercicio;
    }

    /**
     * Define el valor de la propiedad ejercicio.
     * 
     */
    public void setEjercicio(int value) {
        this.ejercicio = value;
    }

    /**
     * Obtiene el valor de la propiedad montoTotalOperacion.
     * 
     */
    public double getMontoTotalOperacion() {
        return montoTotalOperacion;
    }

    /**
     * Define el valor de la propiedad montoTotalOperacion.
     * 
     */
    public void setMontoTotalOperacion(double value) {
        this.montoTotalOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad montoTotalGravado.
     * 
     */
    public double getMontoTotalGravado() {
        return montoTotalGravado;
    }

    /**
     * Define el valor de la propiedad montoTotalGravado.
     * 
     */
    public void setMontoTotalGravado(double value) {
        this.montoTotalGravado = value;
    }

    /**
     * Obtiene el valor de la propiedad montoTotalExcento.
     * 
     */
    public double getMontoTotalExcento() {
        return montoTotalExcento;
    }

    /**
     * Define el valor de la propiedad montoTotalExcento.
     * 
     */
    public void setMontoTotalExcento(double value) {
        this.montoTotalExcento = value;
    }

    /**
     * Obtiene el valor de la propiedad montoTotalRetenciones.
     * 
     */
    public double getMontoTotalRetenciones() {
        return montoTotalRetenciones;
    }

    /**
     * Define el valor de la propiedad montoTotalRetenciones.
     * 
     */
    public void setMontoTotalRetenciones(double value) {
        this.montoTotalRetenciones = value;
    }

}
