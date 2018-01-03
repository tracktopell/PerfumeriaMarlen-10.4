
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Deducciones complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Deducciones">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TotalOtrasDeducciones" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TotalImpuestosRetenidos" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Deduccion" type="{http://tempuri.org/}ArrayOfDeduccion" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Deducciones", propOrder = {
    "totalOtrasDeducciones",
    "totalImpuestosRetenidos",
    "deduccion"
})
public class Deducciones {

    @XmlElement(name = "TotalOtrasDeducciones")
    protected double totalOtrasDeducciones;
    @XmlElement(name = "TotalImpuestosRetenidos")
    protected double totalImpuestosRetenidos;
    @XmlElement(name = "Deduccion")
    protected ArrayOfDeduccion deduccion;

    /**
     * Obtiene el valor de la propiedad totalOtrasDeducciones.
     * 
     */
    public double getTotalOtrasDeducciones() {
        return totalOtrasDeducciones;
    }

    /**
     * Define el valor de la propiedad totalOtrasDeducciones.
     * 
     */
    public void setTotalOtrasDeducciones(double value) {
        this.totalOtrasDeducciones = value;
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
     * Obtiene el valor de la propiedad deduccion.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDeduccion }
     *     
     */
    public ArrayOfDeduccion getDeduccion() {
        return deduccion;
    }

    /**
     * Define el valor de la propiedad deduccion.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDeduccion }
     *     
     */
    public void setDeduccion(ArrayOfDeduccion value) {
        this.deduccion = value;
    }

}
