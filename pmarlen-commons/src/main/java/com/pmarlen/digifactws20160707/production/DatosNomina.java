
package com.pmarlen.digifactws20160707.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para DatosNomina complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DatosNomina">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TipodeComprobante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FormadePago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MetododePago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Serie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Folio" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Fecha" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Subtotal" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Total" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Descuento" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="EmailMensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MotivoDescuento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Moneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoCambio" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="CondicionesDePago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DatosAdicionales" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MensajePDF" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LugarDeExpedicion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TBN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StatusCFD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FolioFiscalOriginal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SerieFolioFiscalOriginal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaFolioFiscalOriginal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MontoFolioFiscalOriginal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Complementos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RegistroPatronal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaInicialPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaFinalPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumDiasPagados" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Antiguedad" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="PercepcionTotalGravado" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="PercepcionTotalExento" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="DeduccionTotalGravado" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="DeduccionTotalExento" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatosNomina", propOrder = {
    "tipodeComprobante",
    "formadePago",
    "metododePago",
    "serie",
    "folio",
    "fecha",
    "subtotal",
    "total",
    "descuento",
    "emailMensaje",
    "motivoDescuento",
    "moneda",
    "tipoCambio",
    "condicionesDePago",
    "datosAdicionales",
    "mensajePDF",
    "lugarDeExpedicion",
    "cuenta",
    "tbn",
    "statusCFD",
    "folioFiscalOriginal",
    "serieFolioFiscalOriginal",
    "fechaFolioFiscalOriginal",
    "montoFolioFiscalOriginal",
    "complementos",
    "registroPatronal",
    "fechaPago",
    "fechaInicialPago",
    "fechaFinalPago",
    "numDiasPagados",
    "antiguedad",
    "percepcionTotalGravado",
    "percepcionTotalExento",
    "deduccionTotalGravado",
    "deduccionTotalExento"
})
public class DatosNomina {

    @XmlElement(name = "TipodeComprobante")
    protected String tipodeComprobante;
    @XmlElement(name = "FormadePago")
    protected String formadePago;
    @XmlElement(name = "MetododePago")
    protected String metododePago;
    @XmlElement(name = "Serie")
    protected String serie;
    @XmlElement(name = "Folio")
    protected int folio;
    @XmlElement(name = "Fecha", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecha;
    @XmlElement(name = "Subtotal")
    protected double subtotal;
    @XmlElement(name = "Total")
    protected double total;
    @XmlElement(name = "Descuento")
    protected double descuento;
    @XmlElement(name = "EmailMensaje")
    protected String emailMensaje;
    @XmlElement(name = "MotivoDescuento")
    protected String motivoDescuento;
    @XmlElement(name = "Moneda")
    protected String moneda;
    @XmlElement(name = "TipoCambio")
    protected double tipoCambio;
    @XmlElement(name = "CondicionesDePago")
    protected String condicionesDePago;
    @XmlElement(name = "DatosAdicionales")
    protected String datosAdicionales;
    @XmlElement(name = "MensajePDF")
    protected String mensajePDF;
    @XmlElement(name = "LugarDeExpedicion")
    protected String lugarDeExpedicion;
    @XmlElement(name = "Cuenta")
    protected String cuenta;
    @XmlElement(name = "TBN")
    protected String tbn;
    @XmlElement(name = "StatusCFD")
    protected String statusCFD;
    @XmlElement(name = "FolioFiscalOriginal")
    protected String folioFiscalOriginal;
    @XmlElement(name = "SerieFolioFiscalOriginal")
    protected String serieFolioFiscalOriginal;
    @XmlElement(name = "FechaFolioFiscalOriginal")
    protected String fechaFolioFiscalOriginal;
    @XmlElement(name = "MontoFolioFiscalOriginal")
    protected String montoFolioFiscalOriginal;
    @XmlElement(name = "Complementos")
    protected String complementos;
    @XmlElement(name = "RegistroPatronal")
    protected String registroPatronal;
    @XmlElement(name = "FechaPago")
    protected String fechaPago;
    @XmlElement(name = "FechaInicialPago")
    protected String fechaInicialPago;
    @XmlElement(name = "FechaFinalPago")
    protected String fechaFinalPago;
    @XmlElement(name = "NumDiasPagados")
    protected double numDiasPagados;
    @XmlElement(name = "Antiguedad")
    protected int antiguedad;
    @XmlElement(name = "PercepcionTotalGravado")
    protected double percepcionTotalGravado;
    @XmlElement(name = "PercepcionTotalExento")
    protected double percepcionTotalExento;
    @XmlElement(name = "DeduccionTotalGravado")
    protected double deduccionTotalGravado;
    @XmlElement(name = "DeduccionTotalExento")
    protected double deduccionTotalExento;

    /**
     * Obtiene el valor de la propiedad tipodeComprobante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipodeComprobante() {
        return tipodeComprobante;
    }

    /**
     * Define el valor de la propiedad tipodeComprobante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipodeComprobante(String value) {
        this.tipodeComprobante = value;
    }

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
     * Obtiene el valor de la propiedad emailMensaje.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailMensaje() {
        return emailMensaje;
    }

    /**
     * Define el valor de la propiedad emailMensaje.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailMensaje(String value) {
        this.emailMensaje = value;
    }

    /**
     * Obtiene el valor de la propiedad motivoDescuento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivoDescuento() {
        return motivoDescuento;
    }

    /**
     * Define el valor de la propiedad motivoDescuento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivoDescuento(String value) {
        this.motivoDescuento = value;
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
     * Obtiene el valor de la propiedad mensajePDF.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensajePDF() {
        return mensajePDF;
    }

    /**
     * Define el valor de la propiedad mensajePDF.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensajePDF(String value) {
        this.mensajePDF = value;
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
     * Obtiene el valor de la propiedad cuenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuenta() {
        return cuenta;
    }

    /**
     * Define el valor de la propiedad cuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuenta(String value) {
        this.cuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad tbn.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTBN() {
        return tbn;
    }

    /**
     * Define el valor de la propiedad tbn.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTBN(String value) {
        this.tbn = value;
    }

    /**
     * Obtiene el valor de la propiedad statusCFD.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusCFD() {
        return statusCFD;
    }

    /**
     * Define el valor de la propiedad statusCFD.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusCFD(String value) {
        this.statusCFD = value;
    }

    /**
     * Obtiene el valor de la propiedad folioFiscalOriginal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolioFiscalOriginal() {
        return folioFiscalOriginal;
    }

    /**
     * Define el valor de la propiedad folioFiscalOriginal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolioFiscalOriginal(String value) {
        this.folioFiscalOriginal = value;
    }

    /**
     * Obtiene el valor de la propiedad serieFolioFiscalOriginal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerieFolioFiscalOriginal() {
        return serieFolioFiscalOriginal;
    }

    /**
     * Define el valor de la propiedad serieFolioFiscalOriginal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerieFolioFiscalOriginal(String value) {
        this.serieFolioFiscalOriginal = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaFolioFiscalOriginal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaFolioFiscalOriginal() {
        return fechaFolioFiscalOriginal;
    }

    /**
     * Define el valor de la propiedad fechaFolioFiscalOriginal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaFolioFiscalOriginal(String value) {
        this.fechaFolioFiscalOriginal = value;
    }

    /**
     * Obtiene el valor de la propiedad montoFolioFiscalOriginal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMontoFolioFiscalOriginal() {
        return montoFolioFiscalOriginal;
    }

    /**
     * Define el valor de la propiedad montoFolioFiscalOriginal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMontoFolioFiscalOriginal(String value) {
        this.montoFolioFiscalOriginal = value;
    }

    /**
     * Obtiene el valor de la propiedad complementos.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComplementos() {
        return complementos;
    }

    /**
     * Define el valor de la propiedad complementos.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComplementos(String value) {
        this.complementos = value;
    }

    /**
     * Obtiene el valor de la propiedad registroPatronal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistroPatronal() {
        return registroPatronal;
    }

    /**
     * Define el valor de la propiedad registroPatronal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistroPatronal(String value) {
        this.registroPatronal = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaPago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaPago() {
        return fechaPago;
    }

    /**
     * Define el valor de la propiedad fechaPago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaPago(String value) {
        this.fechaPago = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaInicialPago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaInicialPago() {
        return fechaInicialPago;
    }

    /**
     * Define el valor de la propiedad fechaInicialPago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaInicialPago(String value) {
        this.fechaInicialPago = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaFinalPago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaFinalPago() {
        return fechaFinalPago;
    }

    /**
     * Define el valor de la propiedad fechaFinalPago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaFinalPago(String value) {
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
     * Obtiene el valor de la propiedad antiguedad.
     * 
     */
    public int getAntiguedad() {
        return antiguedad;
    }

    /**
     * Define el valor de la propiedad antiguedad.
     * 
     */
    public void setAntiguedad(int value) {
        this.antiguedad = value;
    }

    /**
     * Obtiene el valor de la propiedad percepcionTotalGravado.
     * 
     */
    public double getPercepcionTotalGravado() {
        return percepcionTotalGravado;
    }

    /**
     * Define el valor de la propiedad percepcionTotalGravado.
     * 
     */
    public void setPercepcionTotalGravado(double value) {
        this.percepcionTotalGravado = value;
    }

    /**
     * Obtiene el valor de la propiedad percepcionTotalExento.
     * 
     */
    public double getPercepcionTotalExento() {
        return percepcionTotalExento;
    }

    /**
     * Define el valor de la propiedad percepcionTotalExento.
     * 
     */
    public void setPercepcionTotalExento(double value) {
        this.percepcionTotalExento = value;
    }

    /**
     * Obtiene el valor de la propiedad deduccionTotalGravado.
     * 
     */
    public double getDeduccionTotalGravado() {
        return deduccionTotalGravado;
    }

    /**
     * Define el valor de la propiedad deduccionTotalGravado.
     * 
     */
    public void setDeduccionTotalGravado(double value) {
        this.deduccionTotalGravado = value;
    }

    /**
     * Obtiene el valor de la propiedad deduccionTotalExento.
     * 
     */
    public double getDeduccionTotalExento() {
        return deduccionTotalExento;
    }

    /**
     * Define el valor de la propiedad deduccionTotalExento.
     * 
     */
    public void setDeduccionTotalExento(double value) {
        this.deduccionTotalExento = value;
    }

}
