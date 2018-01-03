
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Percepciones33 complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Percepciones33">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TotalSueldos" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TotalSeparacionIndemnizacion" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TotalJubilacionPensionRetiro" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TotalGravado" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TotalExento" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Percepcion" type="{http://tempuri.org/}ArrayOfPercepcion33" minOccurs="0"/>
 *         &lt;element name="Jubilacion" type="{http://tempuri.org/}JubilacionPensionRetiro33" minOccurs="0"/>
 *         &lt;element name="Separacion" type="{http://tempuri.org/}SeparacionIndemnizacion33" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Percepciones33", propOrder = {
    "totalSueldos",
    "totalSeparacionIndemnizacion",
    "totalJubilacionPensionRetiro",
    "totalGravado",
    "totalExento",
    "percepcion",
    "jubilacion",
    "separacion"
})
public class Percepciones33 {

    @XmlElement(name = "TotalSueldos")
    protected double totalSueldos;
    @XmlElement(name = "TotalSeparacionIndemnizacion")
    protected double totalSeparacionIndemnizacion;
    @XmlElement(name = "TotalJubilacionPensionRetiro")
    protected double totalJubilacionPensionRetiro;
    @XmlElement(name = "TotalGravado")
    protected double totalGravado;
    @XmlElement(name = "TotalExento")
    protected double totalExento;
    @XmlElement(name = "Percepcion")
    protected ArrayOfPercepcion33 percepcion;
    @XmlElement(name = "Jubilacion")
    protected JubilacionPensionRetiro33 jubilacion;
    @XmlElement(name = "Separacion")
    protected SeparacionIndemnizacion33 separacion;

    /**
     * Obtiene el valor de la propiedad totalSueldos.
     * 
     */
    public double getTotalSueldos() {
        return totalSueldos;
    }

    /**
     * Define el valor de la propiedad totalSueldos.
     * 
     */
    public void setTotalSueldos(double value) {
        this.totalSueldos = value;
    }

    /**
     * Obtiene el valor de la propiedad totalSeparacionIndemnizacion.
     * 
     */
    public double getTotalSeparacionIndemnizacion() {
        return totalSeparacionIndemnizacion;
    }

    /**
     * Define el valor de la propiedad totalSeparacionIndemnizacion.
     * 
     */
    public void setTotalSeparacionIndemnizacion(double value) {
        this.totalSeparacionIndemnizacion = value;
    }

    /**
     * Obtiene el valor de la propiedad totalJubilacionPensionRetiro.
     * 
     */
    public double getTotalJubilacionPensionRetiro() {
        return totalJubilacionPensionRetiro;
    }

    /**
     * Define el valor de la propiedad totalJubilacionPensionRetiro.
     * 
     */
    public void setTotalJubilacionPensionRetiro(double value) {
        this.totalJubilacionPensionRetiro = value;
    }

    /**
     * Obtiene el valor de la propiedad totalGravado.
     * 
     */
    public double getTotalGravado() {
        return totalGravado;
    }

    /**
     * Define el valor de la propiedad totalGravado.
     * 
     */
    public void setTotalGravado(double value) {
        this.totalGravado = value;
    }

    /**
     * Obtiene el valor de la propiedad totalExento.
     * 
     */
    public double getTotalExento() {
        return totalExento;
    }

    /**
     * Define el valor de la propiedad totalExento.
     * 
     */
    public void setTotalExento(double value) {
        this.totalExento = value;
    }

    /**
     * Obtiene el valor de la propiedad percepcion.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPercepcion33 }
     *     
     */
    public ArrayOfPercepcion33 getPercepcion() {
        return percepcion;
    }

    /**
     * Define el valor de la propiedad percepcion.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPercepcion33 }
     *     
     */
    public void setPercepcion(ArrayOfPercepcion33 value) {
        this.percepcion = value;
    }

    /**
     * Obtiene el valor de la propiedad jubilacion.
     * 
     * @return
     *     possible object is
     *     {@link JubilacionPensionRetiro33 }
     *     
     */
    public JubilacionPensionRetiro33 getJubilacion() {
        return jubilacion;
    }

    /**
     * Define el valor de la propiedad jubilacion.
     * 
     * @param value
     *     allowed object is
     *     {@link JubilacionPensionRetiro33 }
     *     
     */
    public void setJubilacion(JubilacionPensionRetiro33 value) {
        this.jubilacion = value;
    }

    /**
     * Obtiene el valor de la propiedad separacion.
     * 
     * @return
     *     possible object is
     *     {@link SeparacionIndemnizacion33 }
     *     
     */
    public SeparacionIndemnizacion33 getSeparacion() {
        return separacion;
    }

    /**
     * Define el valor de la propiedad separacion.
     * 
     * @param value
     *     allowed object is
     *     {@link SeparacionIndemnizacion33 }
     *     
     */
    public void setSeparacion(SeparacionIndemnizacion33 value) {
        this.separacion = value;
    }

}
