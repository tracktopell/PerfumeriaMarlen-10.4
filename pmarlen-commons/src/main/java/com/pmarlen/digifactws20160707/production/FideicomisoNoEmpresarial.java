
package com.pmarlen.digifactws20160707.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para FideicomisoNoEmpresarial complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="FideicomisoNoEmpresarial">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IncluirComplemento" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="MontoIngresos" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ProporcionIngresos" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ProporcionParticipacionIngresos" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ConceptoIngresos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MontoEgresos" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ProporcionDeducciones" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ProporcionParticipacionEgresos" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ConceptoDeducciones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MontoRetencion" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="DescripcionRetencion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FideicomisoNoEmpresarial", propOrder = {
    "incluirComplemento",
    "montoIngresos",
    "proporcionIngresos",
    "proporcionParticipacionIngresos",
    "conceptoIngresos",
    "montoEgresos",
    "proporcionDeducciones",
    "proporcionParticipacionEgresos",
    "conceptoDeducciones",
    "montoRetencion",
    "descripcionRetencion"
})
public class FideicomisoNoEmpresarial {

    @XmlElement(name = "IncluirComplemento")
    protected boolean incluirComplemento;
    @XmlElement(name = "MontoIngresos")
    protected double montoIngresos;
    @XmlElement(name = "ProporcionIngresos")
    protected double proporcionIngresos;
    @XmlElement(name = "ProporcionParticipacionIngresos")
    protected double proporcionParticipacionIngresos;
    @XmlElement(name = "ConceptoIngresos")
    protected String conceptoIngresos;
    @XmlElement(name = "MontoEgresos")
    protected double montoEgresos;
    @XmlElement(name = "ProporcionDeducciones")
    protected double proporcionDeducciones;
    @XmlElement(name = "ProporcionParticipacionEgresos")
    protected double proporcionParticipacionEgresos;
    @XmlElement(name = "ConceptoDeducciones")
    protected String conceptoDeducciones;
    @XmlElement(name = "MontoRetencion")
    protected double montoRetencion;
    @XmlElement(name = "DescripcionRetencion")
    protected String descripcionRetencion;

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
     * Obtiene el valor de la propiedad montoIngresos.
     * 
     */
    public double getMontoIngresos() {
        return montoIngresos;
    }

    /**
     * Define el valor de la propiedad montoIngresos.
     * 
     */
    public void setMontoIngresos(double value) {
        this.montoIngresos = value;
    }

    /**
     * Obtiene el valor de la propiedad proporcionIngresos.
     * 
     */
    public double getProporcionIngresos() {
        return proporcionIngresos;
    }

    /**
     * Define el valor de la propiedad proporcionIngresos.
     * 
     */
    public void setProporcionIngresos(double value) {
        this.proporcionIngresos = value;
    }

    /**
     * Obtiene el valor de la propiedad proporcionParticipacionIngresos.
     * 
     */
    public double getProporcionParticipacionIngresos() {
        return proporcionParticipacionIngresos;
    }

    /**
     * Define el valor de la propiedad proporcionParticipacionIngresos.
     * 
     */
    public void setProporcionParticipacionIngresos(double value) {
        this.proporcionParticipacionIngresos = value;
    }

    /**
     * Obtiene el valor de la propiedad conceptoIngresos.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConceptoIngresos() {
        return conceptoIngresos;
    }

    /**
     * Define el valor de la propiedad conceptoIngresos.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConceptoIngresos(String value) {
        this.conceptoIngresos = value;
    }

    /**
     * Obtiene el valor de la propiedad montoEgresos.
     * 
     */
    public double getMontoEgresos() {
        return montoEgresos;
    }

    /**
     * Define el valor de la propiedad montoEgresos.
     * 
     */
    public void setMontoEgresos(double value) {
        this.montoEgresos = value;
    }

    /**
     * Obtiene el valor de la propiedad proporcionDeducciones.
     * 
     */
    public double getProporcionDeducciones() {
        return proporcionDeducciones;
    }

    /**
     * Define el valor de la propiedad proporcionDeducciones.
     * 
     */
    public void setProporcionDeducciones(double value) {
        this.proporcionDeducciones = value;
    }

    /**
     * Obtiene el valor de la propiedad proporcionParticipacionEgresos.
     * 
     */
    public double getProporcionParticipacionEgresos() {
        return proporcionParticipacionEgresos;
    }

    /**
     * Define el valor de la propiedad proporcionParticipacionEgresos.
     * 
     */
    public void setProporcionParticipacionEgresos(double value) {
        this.proporcionParticipacionEgresos = value;
    }

    /**
     * Obtiene el valor de la propiedad conceptoDeducciones.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConceptoDeducciones() {
        return conceptoDeducciones;
    }

    /**
     * Define el valor de la propiedad conceptoDeducciones.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConceptoDeducciones(String value) {
        this.conceptoDeducciones = value;
    }

    /**
     * Obtiene el valor de la propiedad montoRetencion.
     * 
     */
    public double getMontoRetencion() {
        return montoRetencion;
    }

    /**
     * Define el valor de la propiedad montoRetencion.
     * 
     */
    public void setMontoRetencion(double value) {
        this.montoRetencion = value;
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

}
