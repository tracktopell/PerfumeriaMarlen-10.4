
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para DatosNominaTimbrado33 complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DatosNominaTimbrado33">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FormadePago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CondicionesDePago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MetododePago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Serie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Folio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fecha" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Subtotal" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Descuento" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Moneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoCambio" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="LugarDeExpedicion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoNomina" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaPago" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="FechaInicialPago" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="FechaFinalPago" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="NumDiasPagados" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TotalPercepciones" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TotalDeducciones" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TotalOtrosPagos" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Total" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatosNominaTimbrado33", propOrder = {
    "formadePago",
    "condicionesDePago",
    "metododePago",
    "serie",
    "folio",
    "fecha",
    "subtotal",
    "descuento",
    "moneda",
    "tipoCambio",
    "lugarDeExpedicion",
    "tipoNomina",
    "fechaPago",
    "fechaInicialPago",
    "fechaFinalPago",
    "numDiasPagados",
    "totalPercepciones",
    "totalDeducciones",
    "totalOtrosPagos",
    "total"
})
public class DatosNominaTimbrado33 {

    @XmlElement(name = "FormadePago")
    protected String formadePago;
    @XmlElement(name = "CondicionesDePago")
    protected String condicionesDePago;
    @XmlElement(name = "MetododePago")
    protected String metododePago;
    @XmlElement(name = "Serie")
    protected String serie;
    @XmlElement(name = "Folio")
    protected String folio;
    @XmlElement(name = "Fecha", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecha;
    @XmlElement(name = "Subtotal")
    protected double subtotal;
    @XmlElement(name = "Descuento")
    protected double descuento;
    @XmlElement(name = "Moneda")
    protected String moneda;
    @XmlElement(name = "TipoCambio")
    protected double tipoCambio;
    @XmlElement(name = "LugarDeExpedicion")
    protected String lugarDeExpedicion;
    @XmlElement(name = "TipoNomina")
    protected String tipoNomina;
    @XmlElement(name = "FechaPago", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaPago;
    @XmlElement(name = "FechaInicialPago", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaInicialPago;
    @XmlElement(name = "FechaFinalPago", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaFinalPago;
    @XmlElement(name = "NumDiasPagados")
    protected double numDiasPagados;
    @XmlElement(name = "TotalPercepciones")
    protected double totalPercepciones;
    @XmlElement(name = "TotalDeducciones")
    protected double totalDeducciones;
    @XmlElement(name = "TotalOtrosPagos")
    protected double totalOtrosPagos;
    @XmlElement(name = "Total")
    protected double total;

    /**
     * Obtiene el valor de la propiedad formadePago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormadePago() {
        return formadePago;
    }

    /**
     * Define el valor de la propiedad formadePago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormadePago(String value) {
        this.formadePago = value;
    }

    /**
     * Obtiene el valor de la propiedad condicionesDePago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCondicionesDePago() {
        return condicionesDePago;
    }

    /**
     * Define el valor de la propiedad condicionesDePago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCondicionesDePago(String value) {
        this.condicionesDePago = value;
    }

    /**
     * Obtiene el valor de la propiedad metododePago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMetododePago() {
        return metododePago;
    }

    /**
     * Define el valor de la propiedad metododePago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMetododePago(String value) {
        this.metododePago = value;
    }

    /**
     * Obtiene el valor de la propiedad serie.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerie() {
        return serie;
    }

    /**
     * Define el valor de la propiedad serie.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerie(String value) {
        this.serie = value;
    }

    /**
     * Obtiene el valor de la propiedad folio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolio() {
        return folio;
    }

    /**
     * Define el valor de la propiedad folio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolio(String value) {
        this.folio = value;
    }

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFecha() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFecha(XMLGregorianCalendar value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad subtotal.
     * 
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * Define el valor de la propiedad subtotal.
     * 
     */
    public void setSubtotal(double value) {
        this.subtotal = value;
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

    /**
     * Obtiene el valor de la propiedad lugarDeExpedicion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLugarDeExpedicion() {
        return lugarDeExpedicion;
    }

    /**
     * Define el valor de la propiedad lugarDeExpedicion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLugarDeExpedicion(String value) {
        this.lugarDeExpedicion = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoNomina.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoNomina() {
        return tipoNomina;
    }

    /**
     * Define el valor de la propiedad tipoNomina.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoNomina(String value) {
        this.tipoNomina = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaPago.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaPago() {
        return fechaPago;
    }

    /**
     * Define el valor de la propiedad fechaPago.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaPago(XMLGregorianCalendar value) {
        this.fechaPago = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaInicialPago.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaInicialPago() {
        return fechaInicialPago;
    }

    /**
     * Define el valor de la propiedad fechaInicialPago.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaInicialPago(XMLGregorianCalendar value) {
        this.fechaInicialPago = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaFinalPago.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaFinalPago() {
        return fechaFinalPago;
    }

    /**
     * Define el valor de la propiedad fechaFinalPago.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaFinalPago(XMLGregorianCalendar value) {
        this.fechaFinalPago = value;
    }

    /**
     * Obtiene el valor de la propiedad numDiasPagados.
     * 
     */
    public double getNumDiasPagados() {
        return numDiasPagados;
    }

    /**
     * Define el valor de la propiedad numDiasPagados.
     * 
     */
    public void setNumDiasPagados(double value) {
        this.numDiasPagados = value;
    }

    /**
     * Obtiene el valor de la propiedad totalPercepciones.
     * 
     */
    public double getTotalPercepciones() {
        return totalPercepciones;
    }

    /**
     * Define el valor de la propiedad totalPercepciones.
     * 
     */
    public void setTotalPercepciones(double value) {
        this.totalPercepciones = value;
    }

    /**
     * Obtiene el valor de la propiedad totalDeducciones.
     * 
     */
    public double getTotalDeducciones() {
        return totalDeducciones;
    }

    /**
     * Define el valor de la propiedad totalDeducciones.
     * 
     */
    public void setTotalDeducciones(double value) {
        this.totalDeducciones = value;
    }

    /**
     * Obtiene el valor de la propiedad totalOtrosPagos.
     * 
     */
    public double getTotalOtrosPagos() {
        return totalOtrosPagos;
    }

    /**
     * Define el valor de la propiedad totalOtrosPagos.
     * 
     */
    public void setTotalOtrosPagos(double value) {
        this.totalOtrosPagos = value;
    }

    /**
     * Obtiene el valor de la propiedad total.
     * 
     */
    public double getTotal() {
        return total;
    }

    /**
     * Define el valor de la propiedad total.
     * 
     */
    public void setTotal(double value) {
        this.total = value;
    }

}
