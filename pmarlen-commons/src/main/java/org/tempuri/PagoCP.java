
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para PagoCP complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PagoCP">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FechaPago" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="FormaDePagoP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MonedaP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoCambioP" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Monto" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="NumOperacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RfcEmisorCtaOrd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NomBancoOrdExt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CtaOrdenante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RfcEmisorCtaBen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CtaBeneficiario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoCadPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CertPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CadPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SelloPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DoctoRelacionado" type="{http://tempuri.org/}ArrayOfDoctoRelacionadoCP" minOccurs="0"/>
 *         &lt;element name="Impuestos" type="{http://tempuri.org/}ArrayOfImpuestosCP" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PagoCP", propOrder = {
    "fechaPago",
    "formaDePagoP",
    "monedaP",
    "tipoCambioP",
    "monto",
    "numOperacion",
    "rfcEmisorCtaOrd",
    "nomBancoOrdExt",
    "ctaOrdenante",
    "rfcEmisorCtaBen",
    "ctaBeneficiario",
    "tipoCadPago",
    "certPago",
    "cadPago",
    "selloPago",
    "doctoRelacionado",
    "impuestos"
})
public class PagoCP {

    @XmlElement(name = "FechaPago", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaPago;
    @XmlElement(name = "FormaDePagoP")
    protected String formaDePagoP;
    @XmlElement(name = "MonedaP")
    protected String monedaP;
    @XmlElement(name = "TipoCambioP")
    protected double tipoCambioP;
    @XmlElement(name = "Monto")
    protected double monto;
    @XmlElement(name = "NumOperacion")
    protected String numOperacion;
    @XmlElement(name = "RfcEmisorCtaOrd")
    protected String rfcEmisorCtaOrd;
    @XmlElement(name = "NomBancoOrdExt")
    protected String nomBancoOrdExt;
    @XmlElement(name = "CtaOrdenante")
    protected String ctaOrdenante;
    @XmlElement(name = "RfcEmisorCtaBen")
    protected String rfcEmisorCtaBen;
    @XmlElement(name = "CtaBeneficiario")
    protected String ctaBeneficiario;
    @XmlElement(name = "TipoCadPago")
    protected String tipoCadPago;
    @XmlElement(name = "CertPago")
    protected String certPago;
    @XmlElement(name = "CadPago")
    protected String cadPago;
    @XmlElement(name = "SelloPago")
    protected String selloPago;
    @XmlElement(name = "DoctoRelacionado")
    protected ArrayOfDoctoRelacionadoCP doctoRelacionado;
    @XmlElement(name = "Impuestos")
    protected ArrayOfImpuestosCP impuestos;

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
     * Obtiene el valor de la propiedad formaDePagoP.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormaDePagoP() {
        return formaDePagoP;
    }

    /**
     * Define el valor de la propiedad formaDePagoP.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormaDePagoP(String value) {
        this.formaDePagoP = value;
    }

    /**
     * Obtiene el valor de la propiedad monedaP.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonedaP() {
        return monedaP;
    }

    /**
     * Define el valor de la propiedad monedaP.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonedaP(String value) {
        this.monedaP = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoCambioP.
     * 
     */
    public double getTipoCambioP() {
        return tipoCambioP;
    }

    /**
     * Define el valor de la propiedad tipoCambioP.
     * 
     */
    public void setTipoCambioP(double value) {
        this.tipoCambioP = value;
    }

    /**
     * Obtiene el valor de la propiedad monto.
     * 
     */
    public double getMonto() {
        return monto;
    }

    /**
     * Define el valor de la propiedad monto.
     * 
     */
    public void setMonto(double value) {
        this.monto = value;
    }

    /**
     * Obtiene el valor de la propiedad numOperacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumOperacion() {
        return numOperacion;
    }

    /**
     * Define el valor de la propiedad numOperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumOperacion(String value) {
        this.numOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad rfcEmisorCtaOrd.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRfcEmisorCtaOrd() {
        return rfcEmisorCtaOrd;
    }

    /**
     * Define el valor de la propiedad rfcEmisorCtaOrd.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRfcEmisorCtaOrd(String value) {
        this.rfcEmisorCtaOrd = value;
    }

    /**
     * Obtiene el valor de la propiedad nomBancoOrdExt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomBancoOrdExt() {
        return nomBancoOrdExt;
    }

    /**
     * Define el valor de la propiedad nomBancoOrdExt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomBancoOrdExt(String value) {
        this.nomBancoOrdExt = value;
    }

    /**
     * Obtiene el valor de la propiedad ctaOrdenante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCtaOrdenante() {
        return ctaOrdenante;
    }

    /**
     * Define el valor de la propiedad ctaOrdenante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCtaOrdenante(String value) {
        this.ctaOrdenante = value;
    }

    /**
     * Obtiene el valor de la propiedad rfcEmisorCtaBen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRfcEmisorCtaBen() {
        return rfcEmisorCtaBen;
    }

    /**
     * Define el valor de la propiedad rfcEmisorCtaBen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRfcEmisorCtaBen(String value) {
        this.rfcEmisorCtaBen = value;
    }

    /**
     * Obtiene el valor de la propiedad ctaBeneficiario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCtaBeneficiario() {
        return ctaBeneficiario;
    }

    /**
     * Define el valor de la propiedad ctaBeneficiario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCtaBeneficiario(String value) {
        this.ctaBeneficiario = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoCadPago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoCadPago() {
        return tipoCadPago;
    }

    /**
     * Define el valor de la propiedad tipoCadPago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoCadPago(String value) {
        this.tipoCadPago = value;
    }

    /**
     * Obtiene el valor de la propiedad certPago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertPago() {
        return certPago;
    }

    /**
     * Define el valor de la propiedad certPago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertPago(String value) {
        this.certPago = value;
    }

    /**
     * Obtiene el valor de la propiedad cadPago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCadPago() {
        return cadPago;
    }

    /**
     * Define el valor de la propiedad cadPago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCadPago(String value) {
        this.cadPago = value;
    }

    /**
     * Obtiene el valor de la propiedad selloPago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelloPago() {
        return selloPago;
    }

    /**
     * Define el valor de la propiedad selloPago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelloPago(String value) {
        this.selloPago = value;
    }

    /**
     * Obtiene el valor de la propiedad doctoRelacionado.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDoctoRelacionadoCP }
     *     
     */
    public ArrayOfDoctoRelacionadoCP getDoctoRelacionado() {
        return doctoRelacionado;
    }

    /**
     * Define el valor de la propiedad doctoRelacionado.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDoctoRelacionadoCP }
     *     
     */
    public void setDoctoRelacionado(ArrayOfDoctoRelacionadoCP value) {
        this.doctoRelacionado = value;
    }

    /**
     * Obtiene el valor de la propiedad impuestos.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfImpuestosCP }
     *     
     */
    public ArrayOfImpuestosCP getImpuestos() {
        return impuestos;
    }

    /**
     * Define el valor de la propiedad impuestos.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfImpuestosCP }
     *     
     */
    public void setImpuestos(ArrayOfImpuestosCP value) {
        this.impuestos = value;
    }

}
