
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CFDITimbradoPlusRequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CFDITimbradoPlusRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Usuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Contrasena" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CFDIRelacionTP" type="{http://tempuri.org/}CFDISRelacionados" minOccurs="0"/>
 *         &lt;element name="DatosCFDITP" type="{http://tempuri.org/}DatosCFDITimbradoPlus" minOccurs="0"/>
 *         &lt;element name="EmisorCFDITP" type="{http://tempuri.org/}EmisorCFDITimbradoPlus" minOccurs="0"/>
 *         &lt;element name="ReceptorCFDITP" type="{http://tempuri.org/}ReceptorCFDITimbradoPlus" minOccurs="0"/>
 *         &lt;element name="ConceptosCFDITP" type="{http://tempuri.org/}ConceptosTimbradoPlus" minOccurs="0"/>
 *         &lt;element name="Addenda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CFDITimbradoPlusRequest", propOrder = {
    "usuario",
    "contrasena",
    "cfdiRelacionTP",
    "datosCFDITP",
    "emisorCFDITP",
    "receptorCFDITP",
    "conceptosCFDITP",
    "addenda"
})
public class CFDITimbradoPlusRequest {

    @XmlElement(name = "Usuario")
    protected String usuario;
    @XmlElement(name = "Contrasena")
    protected String contrasena;
    @XmlElement(name = "CFDIRelacionTP")
    protected CFDISRelacionados cfdiRelacionTP;
    @XmlElement(name = "DatosCFDITP")
    protected DatosCFDITimbradoPlus datosCFDITP;
    @XmlElement(name = "EmisorCFDITP")
    protected EmisorCFDITimbradoPlus emisorCFDITP;
    @XmlElement(name = "ReceptorCFDITP")
    protected ReceptorCFDITimbradoPlus receptorCFDITP;
    @XmlElement(name = "ConceptosCFDITP")
    protected ConceptosTimbradoPlus conceptosCFDITP;
    @XmlElement(name = "Addenda")
    protected String addenda;

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
     * Obtiene el valor de la propiedad cfdiRelacionTP.
     * 
     * @return
     *     possible object is
     *     {@link CFDISRelacionados }
     *     
     */
    public CFDISRelacionados getCFDIRelacionTP() {
        return cfdiRelacionTP;
    }

    /**
     * Define el valor de la propiedad cfdiRelacionTP.
     * 
     * @param value
     *     allowed object is
     *     {@link CFDISRelacionados }
     *     
     */
    public void setCFDIRelacionTP(CFDISRelacionados value) {
        this.cfdiRelacionTP = value;
    }

    /**
     * Obtiene el valor de la propiedad datosCFDITP.
     * 
     * @return
     *     possible object is
     *     {@link DatosCFDITimbradoPlus }
     *     
     */
    public DatosCFDITimbradoPlus getDatosCFDITP() {
        return datosCFDITP;
    }

    /**
     * Define el valor de la propiedad datosCFDITP.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosCFDITimbradoPlus }
     *     
     */
    public void setDatosCFDITP(DatosCFDITimbradoPlus value) {
        this.datosCFDITP = value;
    }

    /**
     * Obtiene el valor de la propiedad emisorCFDITP.
     * 
     * @return
     *     possible object is
     *     {@link EmisorCFDITimbradoPlus }
     *     
     */
    public EmisorCFDITimbradoPlus getEmisorCFDITP() {
        return emisorCFDITP;
    }

    /**
     * Define el valor de la propiedad emisorCFDITP.
     * 
     * @param value
     *     allowed object is
     *     {@link EmisorCFDITimbradoPlus }
     *     
     */
    public void setEmisorCFDITP(EmisorCFDITimbradoPlus value) {
        this.emisorCFDITP = value;
    }

    /**
     * Obtiene el valor de la propiedad receptorCFDITP.
     * 
     * @return
     *     possible object is
     *     {@link ReceptorCFDITimbradoPlus }
     *     
     */
    public ReceptorCFDITimbradoPlus getReceptorCFDITP() {
        return receptorCFDITP;
    }

    /**
     * Define el valor de la propiedad receptorCFDITP.
     * 
     * @param value
     *     allowed object is
     *     {@link ReceptorCFDITimbradoPlus }
     *     
     */
    public void setReceptorCFDITP(ReceptorCFDITimbradoPlus value) {
        this.receptorCFDITP = value;
    }

    /**
     * Obtiene el valor de la propiedad conceptosCFDITP.
     * 
     * @return
     *     possible object is
     *     {@link ConceptosTimbradoPlus }
     *     
     */
    public ConceptosTimbradoPlus getConceptosCFDITP() {
        return conceptosCFDITP;
    }

    /**
     * Define el valor de la propiedad conceptosCFDITP.
     * 
     * @param value
     *     allowed object is
     *     {@link ConceptosTimbradoPlus }
     *     
     */
    public void setConceptosCFDITP(ConceptosTimbradoPlus value) {
        this.conceptosCFDITP = value;
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

}
