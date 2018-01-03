
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CFDIRequestComplementoDePago complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CFDIRequestComplementoDePago">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Usuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Contrasena" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DatosCFDI" type="{http://tempuri.org/}DatosCFDI" minOccurs="0"/>
 *         &lt;element name="CFDIRelacion" type="{http://tempuri.org/}CFDISRelacionados" minOccurs="0"/>
 *         &lt;element name="ReceptorCFDI" type="{http://tempuri.org/}ReceptorCFDI" minOccurs="0"/>
 *         &lt;element name="ConceptosCFD" type="{http://tempuri.org/}ConceptosCFDI" minOccurs="0"/>
 *         &lt;element name="Addenda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Pago" type="{http://tempuri.org/}ComplementoPagos" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CFDIRequestComplementoDePago", propOrder = {
    "usuario",
    "contrasena",
    "datosCFDI",
    "cfdiRelacion",
    "receptorCFDI",
    "conceptosCFD",
    "addenda",
    "pago"
})
public class CFDIRequestComplementoDePago {

    @XmlElement(name = "Usuario")
    protected String usuario;
    @XmlElement(name = "Contrasena")
    protected String contrasena;
    @XmlElement(name = "DatosCFDI")
    protected DatosCFDI datosCFDI;
    @XmlElement(name = "CFDIRelacion")
    protected CFDISRelacionados cfdiRelacion;
    @XmlElement(name = "ReceptorCFDI")
    protected ReceptorCFDI receptorCFDI;
    @XmlElement(name = "ConceptosCFD")
    protected ConceptosCFDI conceptosCFD;
    @XmlElement(name = "Addenda")
    protected String addenda;
    @XmlElement(name = "Pago")
    protected ComplementoPagos pago;

    /**
     * Obtiene el valor de la propiedad usuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Define el valor de la propiedad usuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuario(String value) {
        this.usuario = value;
    }

    /**
     * Obtiene el valor de la propiedad contrasena.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Define el valor de la propiedad contrasena.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrasena(String value) {
        this.contrasena = value;
    }

    /**
     * Obtiene el valor de la propiedad datosCFDI.
     * 
     * @return
     *     possible object is
     *     {@link DatosCFDI }
     *     
     */
    public DatosCFDI getDatosCFDI() {
        return datosCFDI;
    }

    /**
     * Define el valor de la propiedad datosCFDI.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosCFDI }
     *     
     */
    public void setDatosCFDI(DatosCFDI value) {
        this.datosCFDI = value;
    }

    /**
     * Obtiene el valor de la propiedad cfdiRelacion.
     * 
     * @return
     *     possible object is
     *     {@link CFDISRelacionados }
     *     
     */
    public CFDISRelacionados getCFDIRelacion() {
        return cfdiRelacion;
    }

    /**
     * Define el valor de la propiedad cfdiRelacion.
     * 
     * @param value
     *     allowed object is
     *     {@link CFDISRelacionados }
     *     
     */
    public void setCFDIRelacion(CFDISRelacionados value) {
        this.cfdiRelacion = value;
    }

    /**
     * Obtiene el valor de la propiedad receptorCFDI.
     * 
     * @return
     *     possible object is
     *     {@link ReceptorCFDI }
     *     
     */
    public ReceptorCFDI getReceptorCFDI() {
        return receptorCFDI;
    }

    /**
     * Define el valor de la propiedad receptorCFDI.
     * 
     * @param value
     *     allowed object is
     *     {@link ReceptorCFDI }
     *     
     */
    public void setReceptorCFDI(ReceptorCFDI value) {
        this.receptorCFDI = value;
    }

    /**
     * Obtiene el valor de la propiedad conceptosCFD.
     * 
     * @return
     *     possible object is
     *     {@link ConceptosCFDI }
     *     
     */
    public ConceptosCFDI getConceptosCFD() {
        return conceptosCFD;
    }

    /**
     * Define el valor de la propiedad conceptosCFD.
     * 
     * @param value
     *     allowed object is
     *     {@link ConceptosCFDI }
     *     
     */
    public void setConceptosCFD(ConceptosCFDI value) {
        this.conceptosCFD = value;
    }

    /**
     * Obtiene el valor de la propiedad addenda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddenda() {
        return addenda;
    }

    /**
     * Define el valor de la propiedad addenda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddenda(String value) {
        this.addenda = value;
    }

    /**
     * Obtiene el valor de la propiedad pago.
     * 
     * @return
     *     possible object is
     *     {@link ComplementoPagos }
     *     
     */
    public ComplementoPagos getPago() {
        return pago;
    }

    /**
     * Define el valor de la propiedad pago.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplementoPagos }
     *     
     */
    public void setPago(ComplementoPagos value) {
        this.pago = value;
    }

}
