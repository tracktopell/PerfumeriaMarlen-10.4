
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Incapacidad33 complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Incapacidad33">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DiasIncapacidad" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TipoIncapacidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteMonetario" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Incapacidad33", propOrder = {
    "diasIncapacidad",
    "tipoIncapacidad",
    "importeMonetario"
})
public class Incapacidad33 {

    @XmlElement(name = "DiasIncapacidad")
    protected int diasIncapacidad;
    @XmlElement(name = "TipoIncapacidad")
    protected String tipoIncapacidad;
    @XmlElement(name = "ImporteMonetario")
    protected double importeMonetario;

    /**
     * Obtiene el valor de la propiedad diasIncapacidad.
     * 
     */
    public int getDiasIncapacidad() {
        return diasIncapacidad;
    }

    /**
     * Define el valor de la propiedad diasIncapacidad.
     * 
     */
    public void setDiasIncapacidad(int value) {
        this.diasIncapacidad = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoIncapacidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoIncapacidad() {
        return tipoIncapacidad;
    }

    /**
     * Define el valor de la propiedad tipoIncapacidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoIncapacidad(String value) {
        this.tipoIncapacidad = value;
    }

    /**
     * Obtiene el valor de la propiedad importeMonetario.
     * 
     */
    public double getImporteMonetario() {
        return importeMonetario;
    }

    /**
     * Define el valor de la propiedad importeMonetario.
     * 
     */
    public void setImporteMonetario(double value) {
        this.importeMonetario = value;
    }

}
