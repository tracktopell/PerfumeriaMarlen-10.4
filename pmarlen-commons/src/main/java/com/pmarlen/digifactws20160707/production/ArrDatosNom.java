
package com.pmarlen.digifactws20160707.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrDatosNom complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrDatosNom">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Percepcion" type="{https://cfd.sicofi.com.mx}ArrayOfAnyType" minOccurs="0"/>
 *         &lt;element name="Deduccion" type="{https://cfd.sicofi.com.mx}ArrayOfAnyType" minOccurs="0"/>
 *         &lt;element name="Incapacidad" type="{https://cfd.sicofi.com.mx}ArrayOfAnyType" minOccurs="0"/>
 *         &lt;element name="HoraExtra" type="{https://cfd.sicofi.com.mx}ArrayOfAnyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrDatosNom", propOrder = {
    "percepcion",
    "deduccion",
    "incapacidad",
    "horaExtra"
})
public class ArrDatosNom {

    @XmlElement(name = "Percepcion")
    protected ArrayOfAnyType percepcion;
    @XmlElement(name = "Deduccion")
    protected ArrayOfAnyType deduccion;
    @XmlElement(name = "Incapacidad")
    protected ArrayOfAnyType incapacidad;
    @XmlElement(name = "HoraExtra")
    protected ArrayOfAnyType horaExtra;

    /**
     * Obtiene el valor de la propiedad percepcion.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAnyType }
     *     
     */
    public ArrayOfAnyType getPercepcion() {
        return percepcion;
    }

    /**
     * Define el valor de la propiedad percepcion.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAnyType }
     *     
     */
    public void setPercepcion(ArrayOfAnyType value) {
        this.percepcion = value;
    }

    /**
     * Obtiene el valor de la propiedad deduccion.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAnyType }
     *     
     */
    public ArrayOfAnyType getDeduccion() {
        return deduccion;
    }

    /**
     * Define el valor de la propiedad deduccion.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAnyType }
     *     
     */
    public void setDeduccion(ArrayOfAnyType value) {
        this.deduccion = value;
    }

    /**
     * Obtiene el valor de la propiedad incapacidad.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAnyType }
     *     
     */
    public ArrayOfAnyType getIncapacidad() {
        return incapacidad;
    }

    /**
     * Define el valor de la propiedad incapacidad.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAnyType }
     *     
     */
    public void setIncapacidad(ArrayOfAnyType value) {
        this.incapacidad = value;
    }

    /**
     * Obtiene el valor de la propiedad horaExtra.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAnyType }
     *     
     */
    public ArrayOfAnyType getHoraExtra() {
        return horaExtra;
    }

    /**
     * Define el valor de la propiedad horaExtra.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAnyType }
     *     
     */
    public void setHoraExtra(ArrayOfAnyType value) {
        this.horaExtra = value;
    }

}
