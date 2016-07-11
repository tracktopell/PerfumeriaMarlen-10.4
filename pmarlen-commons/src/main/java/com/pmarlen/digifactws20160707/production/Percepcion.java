
package com.pmarlen.digifactws20160707.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Percepcion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Percepcion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Concepto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Clave" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoPercepcion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ImporteGravado" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ImporteExento" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Percepcion", propOrder = {
    "concepto",
    "clave",
    "tipoPercepcion",
    "importeGravado",
    "importeExento"
})
public class Percepcion {

    @XmlElement(name = "Concepto")
    protected String concepto;
    @XmlElement(name = "Clave")
    protected String clave;
    @XmlElement(name = "TipoPercepcion")
    protected int tipoPercepcion;
    @XmlElement(name = "ImporteGravado")
    protected double importeGravado;
    @XmlElement(name = "ImporteExento")
    protected double importeExento;

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
     * Obtiene el valor de la propiedad tipoPercepcion.
     * 
     */
    public int getTipoPercepcion() {
        return tipoPercepcion;
    }

    /**
     * Define el valor de la propiedad tipoPercepcion.
     * 
     */
    public void setTipoPercepcion(int value) {
        this.tipoPercepcion = value;
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

}
