
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Percepcion33 complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Percepcion33">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TipoPercepcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Clave" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Concepto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ImporteGravado" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ImporteExento" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="DatosAdicionales" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Acciones" type="{http://tempuri.org/}AccionesOTitulos33" minOccurs="0"/>
 *         &lt;element name="HorasExtra" type="{http://tempuri.org/}ArrayOfHorasExtras33" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Percepcion33", propOrder = {
    "tipoPercepcion",
    "clave",
    "concepto",
    "importeGravado",
    "importeExento",
    "datosAdicionales",
    "acciones",
    "horasExtra"
})
public class Percepcion33 {

    @XmlElement(name = "TipoPercepcion")
    protected String tipoPercepcion;
    @XmlElement(name = "Clave")
    protected String clave;
    @XmlElement(name = "Concepto")
    protected String concepto;
    @XmlElement(name = "ImporteGravado")
    protected double importeGravado;
    @XmlElement(name = "ImporteExento")
    protected double importeExento;
    @XmlElement(name = "DatosAdicionales")
    protected String datosAdicionales;
    @XmlElement(name = "Acciones")
    protected AccionesOTitulos33 acciones;
    @XmlElement(name = "HorasExtra")
    protected ArrayOfHorasExtras33 horasExtra;

    /**
     * Obtiene el valor de la propiedad tipoPercepcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoPercepcion() {
        return tipoPercepcion;
    }

    /**
     * Define el valor de la propiedad tipoPercepcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoPercepcion(String value) {
        this.tipoPercepcion = value;
    }

    /**
     * Obtiene el valor de la propiedad clave.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClave() {
        return clave;
    }

    /**
     * Define el valor de la propiedad clave.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClave(String value) {
        this.clave = value;
    }

    /**
     * Obtiene el valor de la propiedad concepto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConcepto() {
        return concepto;
    }

    /**
     * Define el valor de la propiedad concepto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConcepto(String value) {
        this.concepto = value;
    }

    /**
     * Obtiene el valor de la propiedad importeGravado.
     * 
     */
    public double getImporteGravado() {
        return importeGravado;
    }

    /**
     * Define el valor de la propiedad importeGravado.
     * 
     */
    public void setImporteGravado(double value) {
        this.importeGravado = value;
    }

    /**
     * Obtiene el valor de la propiedad importeExento.
     * 
     */
    public double getImporteExento() {
        return importeExento;
    }

    /**
     * Define el valor de la propiedad importeExento.
     * 
     */
    public void setImporteExento(double value) {
        this.importeExento = value;
    }

    /**
     * Obtiene el valor de la propiedad datosAdicionales.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatosAdicionales() {
        return datosAdicionales;
    }

    /**
     * Define el valor de la propiedad datosAdicionales.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatosAdicionales(String value) {
        this.datosAdicionales = value;
    }

    /**
     * Obtiene el valor de la propiedad acciones.
     * 
     * @return
     *     possible object is
     *     {@link AccionesOTitulos33 }
     *     
     */
    public AccionesOTitulos33 getAcciones() {
        return acciones;
    }

    /**
     * Define el valor de la propiedad acciones.
     * 
     * @param value
     *     allowed object is
     *     {@link AccionesOTitulos33 }
     *     
     */
    public void setAcciones(AccionesOTitulos33 value) {
        this.acciones = value;
    }

    /**
     * Obtiene el valor de la propiedad horasExtra.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfHorasExtras33 }
     *     
     */
    public ArrayOfHorasExtras33 getHorasExtra() {
        return horasExtra;
    }

    /**
     * Define el valor de la propiedad horasExtra.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfHorasExtras33 }
     *     
     */
    public void setHorasExtra(ArrayOfHorasExtras33 value) {
        this.horasExtra = value;
    }

}
