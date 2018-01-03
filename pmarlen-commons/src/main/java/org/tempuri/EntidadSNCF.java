
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para EntidadSNCF complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="EntidadSNCF">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrigenRecurso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MontoRecursoPropio" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EntidadSNCF", propOrder = {
    "origenRecurso",
    "montoRecursoPropio"
})
public class EntidadSNCF {

    @XmlElement(name = "OrigenRecurso")
    protected String origenRecurso;
    @XmlElement(name = "MontoRecursoPropio")
    protected double montoRecursoPropio;

    /**
     * Obtiene el valor de la propiedad origenRecurso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigenRecurso() {
        return origenRecurso;
    }

    /**
     * Define el valor de la propiedad origenRecurso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigenRecurso(String value) {
        this.origenRecurso = value;
    }

    /**
     * Obtiene el valor de la propiedad montoRecursoPropio.
     * 
     */
    public double getMontoRecursoPropio() {
        return montoRecursoPropio;
    }

    /**
     * Define el valor de la propiedad montoRecursoPropio.
     * 
     */
    public void setMontoRecursoPropio(double value) {
        this.montoRecursoPropio = value;
    }

}
