
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ImpuestosCP complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ImpuestosCP">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Retenciones" type="{http://tempuri.org/}ArrayOfRetencionesCP" minOccurs="0"/>
 *         &lt;element name="Traslados" type="{http://tempuri.org/}ArrayOfTrasladosCP" minOccurs="0"/>
 *         &lt;element name="TotalImpuestosRetenidos" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TotalImpuestosTrasladados" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImpuestosCP", propOrder = {
    "retenciones",
    "traslados",
    "totalImpuestosRetenidos",
    "totalImpuestosTrasladados"
})
public class ImpuestosCP {

    @XmlElement(name = "Retenciones")
    protected ArrayOfRetencionesCP retenciones;
    @XmlElement(name = "Traslados")
    protected ArrayOfTrasladosCP traslados;
    @XmlElement(name = "TotalImpuestosRetenidos")
    protected double totalImpuestosRetenidos;
    @XmlElement(name = "TotalImpuestosTrasladados")
    protected double totalImpuestosTrasladados;

    /**
     * Obtiene el valor de la propiedad retenciones.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRetencionesCP }
     *     
     */
    public ArrayOfRetencionesCP getRetenciones() {
        return retenciones;
    }

    /**
     * Define el valor de la propiedad retenciones.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRetencionesCP }
     *     
     */
    public void setRetenciones(ArrayOfRetencionesCP value) {
        this.retenciones = value;
    }

    /**
     * Obtiene el valor de la propiedad traslados.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTrasladosCP }
     *     
     */
    public ArrayOfTrasladosCP getTraslados() {
        return traslados;
    }

    /**
     * Define el valor de la propiedad traslados.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTrasladosCP }
     *     
     */
    public void setTraslados(ArrayOfTrasladosCP value) {
        this.traslados = value;
    }

    /**
     * Obtiene el valor de la propiedad totalImpuestosRetenidos.
     * 
     */
    public double getTotalImpuestosRetenidos() {
        return totalImpuestosRetenidos;
    }

    /**
     * Define el valor de la propiedad totalImpuestosRetenidos.
     * 
     */
    public void setTotalImpuestosRetenidos(double value) {
        this.totalImpuestosRetenidos = value;
    }

    /**
     * Obtiene el valor de la propiedad totalImpuestosTrasladados.
     * 
     */
    public double getTotalImpuestosTrasladados() {
        return totalImpuestosTrasladados;
    }

    /**
     * Define el valor de la propiedad totalImpuestosTrasladados.
     * 
     */
    public void setTotalImpuestosTrasladados(double value) {
        this.totalImpuestosTrasladados = value;
    }

}
