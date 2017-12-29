
package com.pmarlen.digifactws2018.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para Concepto complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Concepto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Cantidad" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Unidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Precio" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="NumDocAduanero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaExpAduana" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="FechaAduanera" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Aduana" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CuentaPredial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DatosAdicionales" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Importe" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="NoIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Complemento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Concepto", propOrder = {
    "cantidad",
    "unidad",
    "descripcion",
    "precio",
    "numDocAduanero",
    "fechaExpAduana",
    "fechaAduanera",
    "aduana",
    "cuentaPredial",
    "datosAdicionales",
    "importe",
    "noIdentificacion",
    "complemento"
})
public class Concepto {

    @XmlElement(name = "Cantidad")
    protected double cantidad;
    @XmlElement(name = "Unidad")
    protected String unidad;
    @XmlElement(name = "Descripcion")
    protected String descripcion;
    @XmlElement(name = "Precio")
    protected double precio;
    @XmlElement(name = "NumDocAduanero")
    protected String numDocAduanero;
    @XmlElement(name = "FechaExpAduana", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaExpAduana;
    @XmlElement(name = "FechaAduanera")
    protected String fechaAduanera;
    @XmlElement(name = "Aduana")
    protected String aduana;
    @XmlElement(name = "CuentaPredial")
    protected String cuentaPredial;
    @XmlElement(name = "DatosAdicionales")
    protected String datosAdicionales;
    @XmlElement(name = "Importe")
    protected double importe;
    @XmlElement(name = "NoIdentificacion")
    protected String noIdentificacion;
    @XmlElement(name = "Complemento")
    protected String complemento;

    /**
     * Obtiene el valor de la propiedad cantidad.
     * 
     */
    public double getCantidad() {
        return cantidad;
    }

    /**
     * Define el valor de la propiedad cantidad.
     * 
     */
    public void setCantidad(double value) {
        this.cantidad = value;
    }

    /**
     * Obtiene el valor de la propiedad unidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnidad() {
        return unidad;
    }

    /**
     * Define el valor de la propiedad unidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnidad(String value) {
        this.unidad = value;
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
     * Obtiene el valor de la propiedad precio.
     * 
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Define el valor de la propiedad precio.
     * 
     */
    public void setPrecio(double value) {
        this.precio = value;
    }

    /**
     * Obtiene el valor de la propiedad numDocAduanero.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumDocAduanero() {
        return numDocAduanero;
    }

    /**
     * Define el valor de la propiedad numDocAduanero.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumDocAduanero(String value) {
        this.numDocAduanero = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaExpAduana.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaExpAduana() {
        return fechaExpAduana;
    }

    /**
     * Define el valor de la propiedad fechaExpAduana.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaExpAduana(XMLGregorianCalendar value) {
        this.fechaExpAduana = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaAduanera.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaAduanera() {
        return fechaAduanera;
    }

    /**
     * Define el valor de la propiedad fechaAduanera.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaAduanera(String value) {
        this.fechaAduanera = value;
    }

    /**
     * Obtiene el valor de la propiedad aduana.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAduana() {
        return aduana;
    }

    /**
     * Define el valor de la propiedad aduana.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAduana(String value) {
        this.aduana = value;
    }

    /**
     * Obtiene el valor de la propiedad cuentaPredial.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuentaPredial() {
        return cuentaPredial;
    }

    /**
     * Define el valor de la propiedad cuentaPredial.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuentaPredial(String value) {
        this.cuentaPredial = value;
    }

    /**
     * Obtiene el valor de la propiedad datosAdicionales.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatosAdicionales() {
        return datosAdicionales;
    }

    /**
     * Define el valor de la propiedad datosAdicionales.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatosAdicionales(String value) {
        this.datosAdicionales = value;
    }

    /**
     * Obtiene el valor de la propiedad importe.
     * 
     */
    public double getImporte() {
        return importe;
    }

    /**
     * Define el valor de la propiedad importe.
     * 
     */
    public void setImporte(double value) {
        this.importe = value;
    }

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
     * Obtiene el valor de la propiedad complemento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * Define el valor de la propiedad complemento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComplemento(String value) {
        this.complemento = value;
    }

}
