
package com.pmarlen.digifactws20160707.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrendamientoEnFideicomiso complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrendamientoEnFideicomiso">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IncluirComplemento" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="PagoFiduciario" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Rendimiento" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Deducciones" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="MontoRetencion" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="MontoFibras" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="MontoOtrosConceptos" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="DesripcionMontoOtrosConceptos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrendamientoEnFideicomiso", propOrder = {
    "incluirComplemento",
    "pagoFiduciario",
    "rendimiento",
    "deducciones",
    "montoRetencion",
    "montoFibras",
    "montoOtrosConceptos",
    "desripcionMontoOtrosConceptos"
})
public class ArrendamientoEnFideicomiso {

    @XmlElement(name = "IncluirComplemento")
    protected boolean incluirComplemento;
    @XmlElement(name = "PagoFiduciario")
    protected double pagoFiduciario;
    @XmlElement(name = "Rendimiento")
    protected double rendimiento;
    @XmlElement(name = "Deducciones")
    protected double deducciones;
    @XmlElement(name = "MontoRetencion")
    protected double montoRetencion;
    @XmlElement(name = "MontoFibras")
    protected double montoFibras;
    @XmlElement(name = "MontoOtrosConceptos")
    protected double montoOtrosConceptos;
    @XmlElement(name = "DesripcionMontoOtrosConceptos")
    protected String desripcionMontoOtrosConceptos;

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
     * Obtiene el valor de la propiedad pagoFiduciario.
     * 
     */
    public double getPagoFiduciario() {
        return pagoFiduciario;
    }

    /**
     * Define el valor de la propiedad pagoFiduciario.
     * 
     */
    public void setPagoFiduciario(double value) {
        this.pagoFiduciario = value;
    }

    /**
     * Obtiene el valor de la propiedad rendimiento.
     * 
     */
    public double getRendimiento() {
        return rendimiento;
    }

    /**
     * Define el valor de la propiedad rendimiento.
     * 
     */
    public void setRendimiento(double value) {
        this.rendimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad deducciones.
     * 
     */
    public double getDeducciones() {
        return deducciones;
    }

    /**
     * Define el valor de la propiedad deducciones.
     * 
     */
    public void setDeducciones(double value) {
        this.deducciones = value;
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
     * Obtiene el valor de la propiedad montoFibras.
     * 
     */
    public double getMontoFibras() {
        return montoFibras;
    }

    /**
     * Define el valor de la propiedad montoFibras.
     * 
     */
    public void setMontoFibras(double value) {
        this.montoFibras = value;
    }

    /**
     * Obtiene el valor de la propiedad montoOtrosConceptos.
     * 
     */
    public double getMontoOtrosConceptos() {
        return montoOtrosConceptos;
    }

    /**
     * Define el valor de la propiedad montoOtrosConceptos.
     * 
     */
    public void setMontoOtrosConceptos(double value) {
        this.montoOtrosConceptos = value;
    }

    /**
     * Obtiene el valor de la propiedad desripcionMontoOtrosConceptos.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesripcionMontoOtrosConceptos() {
        return desripcionMontoOtrosConceptos;
    }

    /**
     * Define el valor de la propiedad desripcionMontoOtrosConceptos.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesripcionMontoOtrosConceptos(String value) {
        this.desripcionMontoOtrosConceptos = value;
    }

}
