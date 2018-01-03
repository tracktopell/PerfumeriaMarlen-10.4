
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para JubilacionPensionRetiro complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="JubilacionPensionRetiro">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TotalUnaExhibicion" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TotalParcialidad" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="MontoDiario" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="IngresoAcumulable" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="IngresoNoAcumulable" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "JubilacionPensionRetiro", propOrder = {
    "totalUnaExhibicion",
    "totalParcialidad",
    "montoDiario",
    "ingresoAcumulable",
    "ingresoNoAcumulable"
})
public class JubilacionPensionRetiro {

    @XmlElement(name = "TotalUnaExhibicion")
    protected double totalUnaExhibicion;
    @XmlElement(name = "TotalParcialidad")
    protected double totalParcialidad;
    @XmlElement(name = "MontoDiario")
    protected double montoDiario;
    @XmlElement(name = "IngresoAcumulable")
    protected double ingresoAcumulable;
    @XmlElement(name = "IngresoNoAcumulable")
    protected double ingresoNoAcumulable;

    /**
     * Obtiene el valor de la propiedad totalUnaExhibicion.
     * 
     */
    public double getTotalUnaExhibicion() {
        return totalUnaExhibicion;
    }

    /**
     * Define el valor de la propiedad totalUnaExhibicion.
     * 
     */
    public void setTotalUnaExhibicion(double value) {
        this.totalUnaExhibicion = value;
    }

    /**
     * Obtiene el valor de la propiedad totalParcialidad.
     * 
     */
    public double getTotalParcialidad() {
        return totalParcialidad;
    }

    /**
     * Define el valor de la propiedad totalParcialidad.
     * 
     */
    public void setTotalParcialidad(double value) {
        this.totalParcialidad = value;
    }

    /**
     * Obtiene el valor de la propiedad montoDiario.
     * 
     */
    public double getMontoDiario() {
        return montoDiario;
    }

    /**
     * Define el valor de la propiedad montoDiario.
     * 
     */
    public void setMontoDiario(double value) {
        this.montoDiario = value;
    }

    /**
     * Obtiene el valor de la propiedad ingresoAcumulable.
     * 
     */
    public double getIngresoAcumulable() {
        return ingresoAcumulable;
    }

    /**
     * Define el valor de la propiedad ingresoAcumulable.
     * 
     */
    public void setIngresoAcumulable(double value) {
        this.ingresoAcumulable = value;
    }

    /**
     * Obtiene el valor de la propiedad ingresoNoAcumulable.
     * 
     */
    public double getIngresoNoAcumulable() {
        return ingresoNoAcumulable;
    }

    /**
     * Define el valor de la propiedad ingresoNoAcumulable.
     * 
     */
    public void setIngresoNoAcumulable(double value) {
        this.ingresoNoAcumulable = value;
    }

}
