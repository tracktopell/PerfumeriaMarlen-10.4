
package com.pmarlen.digifactws2018.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Premios complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Premios">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IncluirComplemento" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="EntidadFederativa" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MontoTotalPago" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="MontoTotalPagoGravado" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="MontoTotalPagoExento" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Premios", propOrder = {
    "incluirComplemento",
    "entidadFederativa",
    "montoTotalPago",
    "montoTotalPagoGravado",
    "montoTotalPagoExento"
})
public class Premios {

    @XmlElement(name = "IncluirComplemento")
    protected boolean incluirComplemento;
    @XmlElement(name = "EntidadFederativa")
    protected int entidadFederativa;
    @XmlElement(name = "MontoTotalPago")
    protected double montoTotalPago;
    @XmlElement(name = "MontoTotalPagoGravado")
    protected double montoTotalPagoGravado;
    @XmlElement(name = "MontoTotalPagoExento")
    protected double montoTotalPagoExento;

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
     * Obtiene el valor de la propiedad entidadFederativa.
     * 
     */
    public int getEntidadFederativa() {
        return entidadFederativa;
    }

    /**
     * Define el valor de la propiedad entidadFederativa.
     * 
     */
    public void setEntidadFederativa(int value) {
        this.entidadFederativa = value;
    }

    /**
     * Obtiene el valor de la propiedad montoTotalPago.
     * 
     */
    public double getMontoTotalPago() {
        return montoTotalPago;
    }

    /**
     * Define el valor de la propiedad montoTotalPago.
     * 
     */
    public void setMontoTotalPago(double value) {
        this.montoTotalPago = value;
    }

    /**
     * Obtiene el valor de la propiedad montoTotalPagoGravado.
     * 
     */
    public double getMontoTotalPagoGravado() {
        return montoTotalPagoGravado;
    }

    /**
     * Define el valor de la propiedad montoTotalPagoGravado.
     * 
     */
    public void setMontoTotalPagoGravado(double value) {
        this.montoTotalPagoGravado = value;
    }

    /**
     * Obtiene el valor de la propiedad montoTotalPagoExento.
     * 
     */
    public double getMontoTotalPagoExento() {
        return montoTotalPagoExento;
    }

    /**
     * Define el valor de la propiedad montoTotalPagoExento.
     * 
     */
    public void setMontoTotalPagoExento(double value) {
        this.montoTotalPagoExento = value;
    }

}
