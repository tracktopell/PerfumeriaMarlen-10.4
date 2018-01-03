
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ConceptoTicket complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConceptoTicket">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ClaveProdServ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NoIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cantidad" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="claveUnidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Unidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ValorUnitario" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Importe" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Descuento" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="DatosAdicionales" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Traslados" type="{http://tempuri.org/}ArrayOfImpuestoTrasladadoTicket" minOccurs="0"/>
 *         &lt;element name="Retenciones" type="{http://tempuri.org/}ArrayOfImpuestoRetenidoTicket" minOccurs="0"/>
 *         &lt;element name="InformacionAduana" type="{http://tempuri.org/}ArrayOfInformacionAduaneraTicket" minOccurs="0"/>
 *         &lt;element name="Predial" type="{http://tempuri.org/}CuentaPredialTicket" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConceptoTicket", propOrder = {
    "claveProdServ",
    "noIdentificacion",
    "cantidad",
    "claveUnidad",
    "unidad",
    "descripcion",
    "valorUnitario",
    "importe",
    "descuento",
    "datosAdicionales",
    "traslados",
    "retenciones",
    "informacionAduana",
    "predial"
})
public class ConceptoTicket {

    @XmlElement(name = "ClaveProdServ")
    protected String claveProdServ;
    @XmlElement(name = "NoIdentificacion")
    protected String noIdentificacion;
    @XmlElement(name = "Cantidad")
    protected double cantidad;
    protected String claveUnidad;
    @XmlElement(name = "Unidad")
    protected String unidad;
    @XmlElement(name = "Descripcion")
    protected String descripcion;
    @XmlElement(name = "ValorUnitario")
    protected double valorUnitario;
    @XmlElement(name = "Importe")
    protected double importe;
    @XmlElement(name = "Descuento")
    protected double descuento;
    @XmlElement(name = "DatosAdicionales")
    protected String datosAdicionales;
    @XmlElement(name = "Traslados")
    protected ArrayOfImpuestoTrasladadoTicket traslados;
    @XmlElement(name = "Retenciones")
    protected ArrayOfImpuestoRetenidoTicket retenciones;
    @XmlElement(name = "InformacionAduana")
    protected ArrayOfInformacionAduaneraTicket informacionAduana;
    @XmlElement(name = "Predial")
    protected CuentaPredialTicket predial;

    /**
     * Obtiene el valor de la propiedad claveProdServ.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaveProdServ() {
        return claveProdServ;
    }

    /**
     * Define el valor de la propiedad claveProdServ.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaveProdServ(String value) {
        this.claveProdServ = value;
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
     * Obtiene el valor de la propiedad claveUnidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaveUnidad() {
        return claveUnidad;
    }

    /**
     * Define el valor de la propiedad claveUnidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaveUnidad(String value) {
        this.claveUnidad = value;
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
     * Obtiene el valor de la propiedad valorUnitario.
     * 
     */
    public double getValorUnitario() {
        return valorUnitario;
    }

    /**
     * Define el valor de la propiedad valorUnitario.
     * 
     */
    public void setValorUnitario(double value) {
        this.valorUnitario = value;
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
     * Obtiene el valor de la propiedad descuento.
     * 
     */
    public double getDescuento() {
        return descuento;
    }

    /**
     * Define el valor de la propiedad descuento.
     * 
     */
    public void setDescuento(double value) {
        this.descuento = value;
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
     * Obtiene el valor de la propiedad traslados.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfImpuestoTrasladadoTicket }
     *     
     */
    public ArrayOfImpuestoTrasladadoTicket getTraslados() {
        return traslados;
    }

    /**
     * Define el valor de la propiedad traslados.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfImpuestoTrasladadoTicket }
     *     
     */
    public void setTraslados(ArrayOfImpuestoTrasladadoTicket value) {
        this.traslados = value;
    }

    /**
     * Obtiene el valor de la propiedad retenciones.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfImpuestoRetenidoTicket }
     *     
     */
    public ArrayOfImpuestoRetenidoTicket getRetenciones() {
        return retenciones;
    }

    /**
     * Define el valor de la propiedad retenciones.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfImpuestoRetenidoTicket }
     *     
     */
    public void setRetenciones(ArrayOfImpuestoRetenidoTicket value) {
        this.retenciones = value;
    }

    /**
     * Obtiene el valor de la propiedad informacionAduana.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfInformacionAduaneraTicket }
     *     
     */
    public ArrayOfInformacionAduaneraTicket getInformacionAduana() {
        return informacionAduana;
    }

    /**
     * Define el valor de la propiedad informacionAduana.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfInformacionAduaneraTicket }
     *     
     */
    public void setInformacionAduana(ArrayOfInformacionAduaneraTicket value) {
        this.informacionAduana = value;
    }

    /**
     * Obtiene el valor de la propiedad predial.
     * 
     * @return
     *     possible object is
     *     {@link CuentaPredialTicket }
     *     
     */
    public CuentaPredialTicket getPredial() {
        return predial;
    }

    /**
     * Define el valor de la propiedad predial.
     * 
     * @param value
     *     allowed object is
     *     {@link CuentaPredialTicket }
     *     
     */
    public void setPredial(CuentaPredialTicket value) {
        this.predial = value;
    }

}
