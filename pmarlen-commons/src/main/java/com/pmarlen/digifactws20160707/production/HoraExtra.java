
package com.pmarlen.digifactws20160707.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para HoraExtra complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="HoraExtra">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TipoHoras" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dias" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="HorasExtra" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ImportePagado" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HoraExtra", propOrder = {
    "tipoHoras",
    "dias",
    "horasExtra",
    "importePagado"
})
public class HoraExtra {

    @XmlElement(name = "TipoHoras")
    protected String tipoHoras;
    @XmlElement(name = "Dias")
    protected double dias;
    @XmlElement(name = "HorasExtra")
    protected double horasExtra;
    @XmlElement(name = "ImportePagado")
    protected double importePagado;

    /**
     * Obtiene el valor de la propiedad tipoHoras.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoHoras() {
        return tipoHoras;
    }

    /**
     * Define el valor de la propiedad tipoHoras.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoHoras(String value) {
        this.tipoHoras = value;
    }

    /**
     * Obtiene el valor de la propiedad dias.
     * 
     */
    public double getDias() {
        return dias;
    }

    /**
     * Define el valor de la propiedad dias.
     * 
     */
    public void setDias(double value) {
        this.dias = value;
    }

    /**
     * Obtiene el valor de la propiedad horasExtra.
     * 
     */
    public double getHorasExtra() {
        return horasExtra;
    }

    /**
     * Define el valor de la propiedad horasExtra.
     * 
     */
    public void setHorasExtra(double value) {
        this.horasExtra = value;
    }

    /**
     * Obtiene el valor de la propiedad importePagado.
     * 
     */
    public double getImportePagado() {
        return importePagado;
    }

    /**
     * Define el valor de la propiedad importePagado.
     * 
     */
    public void setImportePagado(double value) {
        this.importePagado = value;
    }

}
