
package com.pmarlen.digifactws2018.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ImpuestoRetencion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ImpuestoRetencion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BaseRetencion" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Impuesto" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MontoRetencion" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TipoPagoRetencion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImpuestoRetencion", propOrder = {
    "baseRetencion",
    "impuesto",
    "montoRetencion",
    "tipoPagoRetencion"
})
public class ImpuestoRetencion {

    @XmlElement(name = "BaseRetencion")
    protected double baseRetencion;
    @XmlElement(name = "Impuesto")
    protected int impuesto;
    @XmlElement(name = "MontoRetencion")
    protected double montoRetencion;
    @XmlElement(name = "TipoPagoRetencion")
    protected String tipoPagoRetencion;

    /**
     * Obtiene el valor de la propiedad baseRetencion.
     * 
     */
    public double getBaseRetencion() {
        return baseRetencion;
    }

    /**
     * Define el valor de la propiedad baseRetencion.
     * 
     */
    public void setBaseRetencion(double value) {
        this.baseRetencion = value;
    }

    /**
     * Obtiene el valor de la propiedad impuesto.
     * 
     */
    public int getImpuesto() {
        return impuesto;
    }

    /**
     * Define el valor de la propiedad impuesto.
     * 
     */
    public void setImpuesto(int value) {
        this.impuesto = value;
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
     * Obtiene el valor de la propiedad tipoPagoRetencion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoPagoRetencion() {
        return tipoPagoRetencion;
    }

    /**
     * Define el valor de la propiedad tipoPagoRetencion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoPagoRetencion(String value) {
        this.tipoPagoRetencion = value;
    }

}
