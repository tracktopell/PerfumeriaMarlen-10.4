
package com.pmarlen.digifactws20160707.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Intereses complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Intereses">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IncluirComplemento" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="SistemaFinanciero" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="RetiroIntereses" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="OperacionesFinancieras" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="InteresNominal" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="InteresReal" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Perdida" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Intereses", propOrder = {
    "incluirComplemento",
    "sistemaFinanciero",
    "retiroIntereses",
    "operacionesFinancieras",
    "interesNominal",
    "interesReal",
    "perdida"
})
public class Intereses {

    @XmlElement(name = "IncluirComplemento")
    protected boolean incluirComplemento;
    @XmlElement(name = "SistemaFinanciero")
    protected boolean sistemaFinanciero;
    @XmlElement(name = "RetiroIntereses")
    protected boolean retiroIntereses;
    @XmlElement(name = "OperacionesFinancieras")
    protected boolean operacionesFinancieras;
    @XmlElement(name = "InteresNominal")
    protected double interesNominal;
    @XmlElement(name = "InteresReal")
    protected double interesReal;
    @XmlElement(name = "Perdida")
    protected double perdida;

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
     * Obtiene el valor de la propiedad retiroIntereses.
     * 
     */
    public boolean isRetiroIntereses() {
        return retiroIntereses;
    }

    /**
     * Define el valor de la propiedad retiroIntereses.
     * 
     */
    public void setRetiroIntereses(boolean value) {
        this.retiroIntereses = value;
    }

    /**
     * Obtiene el valor de la propiedad operacionesFinancieras.
     * 
     */
    public boolean isOperacionesFinancieras() {
        return operacionesFinancieras;
    }

    /**
     * Define el valor de la propiedad operacionesFinancieras.
     * 
     */
    public void setOperacionesFinancieras(boolean value) {
        this.operacionesFinancieras = value;
    }

    /**
     * Obtiene el valor de la propiedad interesNominal.
     * 
     */
    public double getInteresNominal() {
        return interesNominal;
    }

    /**
     * Define el valor de la propiedad interesNominal.
     * 
     */
    public void setInteresNominal(double value) {
        this.interesNominal = value;
    }

    /**
     * Obtiene el valor de la propiedad interesReal.
     * 
     */
    public double getInteresReal() {
        return interesReal;
    }

    /**
     * Define el valor de la propiedad interesReal.
     * 
     */
    public void setInteresReal(double value) {
        this.interesReal = value;
    }

    /**
     * Obtiene el valor de la propiedad perdida.
     * 
     */
    public double getPerdida() {
        return perdida;
    }

    /**
     * Define el valor de la propiedad perdida.
     * 
     */
    public void setPerdida(double value) {
        this.perdida = value;
    }

}
