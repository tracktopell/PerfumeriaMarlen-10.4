
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para DatosComercioExteriorTimbrado complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DatosComercioExteriorTimbrado">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TipodeComprobante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FormadePago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MetododePago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Serie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Folio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FolioFiscalOriginal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fecha" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Subtotal" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Total" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Descuento" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Moneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoCambio" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="CondicionesDePago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LugarDeExpedicion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MotivoTraslado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "DatosComercioExteriorTimbrado", propOrder = {
    "tipodeComprobante",
    "formadePago",
    "metododePago",
    "serie",
    "folio",
    "folioFiscalOriginal",
    "fecha",
    "subtotal",
    "total",
    "descuento",
    "moneda",
    "tipoCambio",
    "condicionesDePago",
    "lugarDeExpedicion",
    "cuenta",
    "motivoTraslado",
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
public class DatosComercioExteriorTimbrado {

    @XmlElement(name = "TipodeComprobante")
    protected String tipodeComprobante;
    @XmlElement(name = "FormadePago")
    protected String formadePago;
    @XmlElement(name = "MetododePago")
    protected String metododePago;
    @XmlElement(name = "Serie")
    protected String serie;
    @XmlElement(name = "Folio")
    protected String folio;
    @XmlElement(name = "FolioFiscalOriginal")
    protected String folioFiscalOriginal;
    @XmlElement(name = "Fecha", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecha;
    @XmlElement(name = "Subtotal")
    protected double subtotal;
    @XmlElement(name = "Total")
    protected double total;
    @XmlElement(name = "Descuento")
    protected double descuento;
    @XmlElement(name = "Moneda")
    protected String moneda;
    @XmlElement(name = "TipoCambio")
    protected double tipoCambio;
    @XmlElement(name = "CondicionesDePago")
    protected String condicionesDePago;
    @XmlElement(name = "LugarDeExpedicion")
    protected String lugarDeExpedicion;
    @XmlElement(name = "Cuenta")
    protected String cuenta;
    @XmlElement(name = "MotivoTraslado")
    protected String motivoTraslado;
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
     * Obtiene el valor de la propiedad motivoTraslado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivoTraslado() {
        return motivoTraslado;
    }

    /**
     * Define el valor de la propiedad motivoTraslado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivoTraslado(String value) {
        this.motivoTraslado = value;
    }

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
