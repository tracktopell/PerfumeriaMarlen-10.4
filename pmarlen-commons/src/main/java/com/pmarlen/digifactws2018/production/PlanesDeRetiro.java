
package com.pmarlen.digifactws2018.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para PlanesDeRetiro complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PlanesDeRetiro">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IncluirComplemento" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="SistemaFinanciero" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="MontoAportaciones" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="MontoIntereses" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="RetiroRecursosYRendimientos" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="MontoRetiro" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="MontoExento" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="MontoExcedente" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="RetiroInmediato" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="MontoRetiroInmediato" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlanesDeRetiro", propOrder = {
    "incluirComplemento",
    "sistemaFinanciero",
    "montoAportaciones",
    "montoIntereses",
    "retiroRecursosYRendimientos",
    "montoRetiro",
    "montoExento",
    "montoExcedente",
    "retiroInmediato",
    "montoRetiroInmediato"
})
public class PlanesDeRetiro {

    @XmlElement(name = "IncluirComplemento")
    protected boolean incluirComplemento;
    @XmlElement(name = "SistemaFinanciero")
    protected boolean sistemaFinanciero;
    @XmlElement(name = "MontoAportaciones")
    protected double montoAportaciones;
    @XmlElement(name = "MontoIntereses")
    protected double montoIntereses;
    @XmlElement(name = "RetiroRecursosYRendimientos")
    protected boolean retiroRecursosYRendimientos;
    @XmlElement(name = "MontoRetiro")
    protected double montoRetiro;
    @XmlElement(name = "MontoExento")
    protected double montoExento;
    @XmlElement(name = "MontoExcedente")
    protected double montoExcedente;
    @XmlElement(name = "RetiroInmediato")
    protected boolean retiroInmediato;
    @XmlElement(name = "MontoRetiroInmediato")
    protected double montoRetiroInmediato;

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
     * Obtiene el valor de la propiedad sistemaFinanciero.
     * 
     */
    public boolean isSistemaFinanciero() {
        return sistemaFinanciero;
    }

    /**
     * Define el valor de la propiedad sistemaFinanciero.
     * 
     */
    public void setSistemaFinanciero(boolean value) {
        this.sistemaFinanciero = value;
    }

    /**
     * Obtiene el valor de la propiedad montoAportaciones.
     * 
     */
    public double getMontoAportaciones() {
        return montoAportaciones;
    }

    /**
     * Define el valor de la propiedad montoAportaciones.
     * 
     */
    public void setMontoAportaciones(double value) {
        this.montoAportaciones = value;
    }

    /**
     * Obtiene el valor de la propiedad montoIntereses.
     * 
     */
    public double getMontoIntereses() {
        return montoIntereses;
    }

    /**
     * Define el valor de la propiedad montoIntereses.
     * 
     */
    public void setMontoIntereses(double value) {
        this.montoIntereses = value;
    }

    /**
     * Obtiene el valor de la propiedad retiroRecursosYRendimientos.
     * 
     */
    public boolean isRetiroRecursosYRendimientos() {
        return retiroRecursosYRendimientos;
    }

    /**
     * Define el valor de la propiedad retiroRecursosYRendimientos.
     * 
     */
    public void setRetiroRecursosYRendimientos(boolean value) {
        this.retiroRecursosYRendimientos = value;
    }

    /**
     * Obtiene el valor de la propiedad montoRetiro.
     * 
     */
    public double getMontoRetiro() {
        return montoRetiro;
    }

    /**
     * Define el valor de la propiedad montoRetiro.
     * 
     */
    public void setMontoRetiro(double value) {
        this.montoRetiro = value;
    }

    /**
     * Obtiene el valor de la propiedad montoExento.
     * 
     */
    public double getMontoExento() {
        return montoExento;
    }

    /**
     * Define el valor de la propiedad montoExento.
     * 
     */
    public void setMontoExento(double value) {
        this.montoExento = value;
    }

    /**
     * Obtiene el valor de la propiedad montoExcedente.
     * 
     */
    public double getMontoExcedente() {
        return montoExcedente;
    }

    /**
     * Define el valor de la propiedad montoExcedente.
     * 
     */
    public void setMontoExcedente(double value) {
        this.montoExcedente = value;
    }

    /**
     * Obtiene el valor de la propiedad retiroInmediato.
     * 
     */
    public boolean isRetiroInmediato() {
        return retiroInmediato;
    }

    /**
     * Define el valor de la propiedad retiroInmediato.
     * 
     */
    public void setRetiroInmediato(boolean value) {
        this.retiroInmediato = value;
    }

    /**
     * Obtiene el valor de la propiedad montoRetiroInmediato.
     * 
     */
    public double getMontoRetiroInmediato() {
        return montoRetiroInmediato;
    }

    /**
     * Define el valor de la propiedad montoRetiroInmediato.
     * 
     */
    public void setMontoRetiroInmediato(double value) {
        this.montoRetiroInmediato = value;
    }

}
