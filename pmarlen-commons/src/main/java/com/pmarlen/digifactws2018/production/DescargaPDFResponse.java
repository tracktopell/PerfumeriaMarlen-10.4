
package com.pmarlen.digifactws2018.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DescargaPDFResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DescargaPDFResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="correcto" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="incorrecto" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="cuenta" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Error" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ids" type="{https://cfd.sicofi.com.mx}ArrayOfString" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DescargaPDFResponse", propOrder = {
    "correcto",
    "incorrecto",
    "cuenta",
    "error",
    "ids"
})
public class DescargaPDFResponse {

    protected boolean correcto;
    protected boolean incorrecto;
    protected int cuenta;
    @XmlElement(name = "Error")
    protected String error;
    protected ArrayOfString ids;

    /**
     * Obtiene el valor de la propiedad correcto.
     * 
     */
    public boolean isCorrecto() {
        return correcto;
    }

    /**
     * Define el valor de la propiedad correcto.
     * 
     */
    public void setCorrecto(boolean value) {
        this.correcto = value;
    }

    /**
     * Obtiene el valor de la propiedad incorrecto.
     * 
     */
    public boolean isIncorrecto() {
        return incorrecto;
    }

    /**
     * Define el valor de la propiedad incorrecto.
     * 
     */
    public void setIncorrecto(boolean value) {
        this.incorrecto = value;
    }

    /**
     * Obtiene el valor de la propiedad cuenta.
     * 
     */
    public int getCuenta() {
        return cuenta;
    }

    /**
     * Define el valor de la propiedad cuenta.
     * 
     */
    public void setCuenta(int value) {
        this.cuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad error.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getError() {
        return error;
    }

    /**
     * Define el valor de la propiedad error.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setError(String value) {
        this.error = value;
    }

    /**
     * Obtiene el valor de la propiedad ids.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getIds() {
        return ids;
    }

    /**
     * Define el valor de la propiedad ids.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setIds(ArrayOfString value) {
        this.ids = value;
    }

}
