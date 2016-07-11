
package com.pmarlen.digifactws20160707.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para PagosAExtranjeros complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PagosAExtranjeros">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IncluirComplemento" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="EsBeneficiario" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="PaisResidencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConceptoPago" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DescripcionConcepto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RFC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CURP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RazonSocial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PagosAExtranjeros", propOrder = {
    "incluirComplemento",
    "esBeneficiario",
    "paisResidencia",
    "conceptoPago",
    "descripcionConcepto",
    "rfc",
    "curp",
    "razonSocial"
})
public class PagosAExtranjeros {

    @XmlElement(name = "IncluirComplemento")
    protected boolean incluirComplemento;
    @XmlElement(name = "EsBeneficiario")
    protected boolean esBeneficiario;
    @XmlElement(name = "PaisResidencia")
    protected String paisResidencia;
    @XmlElement(name = "ConceptoPago")
    protected int conceptoPago;
    @XmlElement(name = "DescripcionConcepto")
    protected String descripcionConcepto;
    @XmlElement(name = "RFC")
    protected String rfc;
    @XmlElement(name = "CURP")
    protected String curp;
    @XmlElement(name = "RazonSocial")
    protected String razonSocial;

    /**
     * Obtiene el valor de la propiedad incluirComplemento.
     * 
     */
    public boolean isIncluirComplemento() {
        return incluirComplemento;
    }

    /**
     * Define el valor de la propiedad incluirComplemento.
     * 
     */
    public void setIncluirComplemento(boolean value) {
        this.incluirComplemento = value;
    }

    /**
     * Obtiene el valor de la propiedad esBeneficiario.
     * 
     */
    public boolean isEsBeneficiario() {
        return esBeneficiario;
    }

    /**
     * Define el valor de la propiedad esBeneficiario.
     * 
     */
    public void setEsBeneficiario(boolean value) {
        this.esBeneficiario = value;
    }

    /**
     * Obtiene el valor de la propiedad paisResidencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaisResidencia() {
        return paisResidencia;
    }

    /**
     * Define el valor de la propiedad paisResidencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaisResidencia(String value) {
        this.paisResidencia = value;
    }

    /**
     * Obtiene el valor de la propiedad conceptoPago.
     * 
     */
    public int getConceptoPago() {
        return conceptoPago;
    }

    /**
     * Define el valor de la propiedad conceptoPago.
     * 
     */
    public void setConceptoPago(int value) {
        this.conceptoPago = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcionConcepto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionConcepto() {
        return descripcionConcepto;
    }

    /**
     * Define el valor de la propiedad descripcionConcepto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionConcepto(String value) {
        this.descripcionConcepto = value;
    }

    /**
     * Obtiene el valor de la propiedad rfc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRFC() {
        return rfc;
    }

    /**
     * Define el valor de la propiedad rfc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRFC(String value) {
        this.rfc = value;
    }

    /**
     * Obtiene el valor de la propiedad curp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCURP() {
        return curp;
    }

    /**
     * Define el valor de la propiedad curp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCURP(String value) {
        this.curp = value;
    }

    /**
     * Obtiene el valor de la propiedad razonSocial.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * Define el valor de la propiedad razonSocial.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRazonSocial(String value) {
        this.razonSocial = value;
    }

}
