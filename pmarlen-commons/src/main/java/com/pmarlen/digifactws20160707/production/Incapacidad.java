
package com.pmarlen.digifactws20160707.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Incapacidad complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Incapacidad">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DiasIncapacidad" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TipoIncapacidad" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Descuento" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Incapacidad", propOrder = {
    "diasIncapacidad",
    "tipoIncapacidad",
    "descuento"
})
public class Incapacidad {

    @XmlElement(name = "DiasIncapacidad")
    protected double diasIncapacidad;
    @XmlElement(name = "TipoIncapacidad")
    protected int tipoIncapacidad;
    @XmlElement(name = "Descuento")
    protected double descuento;

    /**
     * Obtiene el valor de la propiedad diasIncapacidad.
     * 
     */
    public double getDiasIncapacidad() {
        return diasIncapacidad;
    }

    /**
     * Define el valor de la propiedad diasIncapacidad.
     * 
     */
    public void setDiasIncapacidad(double value) {
        this.diasIncapacidad = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoIncapacidad.
     * 
     */
    public int getTipoIncapacidad() {
        return tipoIncapacidad;
    }

    /**
     * Define el valor de la propiedad tipoIncapacidad.
     * 
     */
    public void setTipoIncapacidad(int value) {
        this.tipoIncapacidad = value;
    }

    /**
     * Obtiene el valor de la propiedad descuento.
     * 
     */
    public double getDescuento() {
        return descuento;
    }

    /**
     * Define el valor de la propiedad descuento.
     * 
     */
    public void setDescuento(double value) {
        this.descuento = value;
    }

}
