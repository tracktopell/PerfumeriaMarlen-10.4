
package com.pmarlen.digifactws2018.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DetallesAxuFolios complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DetallesAxuFolios">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NumerodeIdentificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Fecha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nacionales" type="{https://cfd.sicofi.com.mx}ArrayOfComprobanteNacionalAuxiliar" minOccurs="0"/>
 *         &lt;element name="Relacionados" type="{https://cfd.sicofi.com.mx}ArrayOfComprobanteNacionalRelacionadoAuxiliar" minOccurs="0"/>
 *         &lt;element name="Extranjeros" type="{https://cfd.sicofi.com.mx}ArrayOfComprobanteExtranjeroAuxiliar" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DetallesAxuFolios", propOrder = {
    "numerodeIdentificacion",
    "fecha",
    "nacionales",
    "relacionados",
    "extranjeros"
})
public class DetallesAxuFolios {

    @XmlElement(name = "NumerodeIdentificacion")
    protected String numerodeIdentificacion;
    @XmlElement(name = "Fecha")
    protected String fecha;
    @XmlElement(name = "Nacionales")
    protected ArrayOfComprobanteNacionalAuxiliar nacionales;
    @XmlElement(name = "Relacionados")
    protected ArrayOfComprobanteNacionalRelacionadoAuxiliar relacionados;
    @XmlElement(name = "Extranjeros")
    protected ArrayOfComprobanteExtranjeroAuxiliar extranjeros;

    /**
     * Obtiene el valor de la propiedad numerodeIdentificacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumerodeIdentificacion() {
        return numerodeIdentificacion;
    }

    /**
     * Define el valor de la propiedad numerodeIdentificacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumerodeIdentificacion(String value) {
        this.numerodeIdentificacion = value;
    }

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecha(String value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad nacionales.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfComprobanteNacionalAuxiliar }
     *     
     */
    public ArrayOfComprobanteNacionalAuxiliar getNacionales() {
        return nacionales;
    }

    /**
     * Define el valor de la propiedad nacionales.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfComprobanteNacionalAuxiliar }
     *     
     */
    public void setNacionales(ArrayOfComprobanteNacionalAuxiliar value) {
        this.nacionales = value;
    }

    /**
     * Obtiene el valor de la propiedad relacionados.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfComprobanteNacionalRelacionadoAuxiliar }
     *     
     */
    public ArrayOfComprobanteNacionalRelacionadoAuxiliar getRelacionados() {
        return relacionados;
    }

    /**
     * Define el valor de la propiedad relacionados.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfComprobanteNacionalRelacionadoAuxiliar }
     *     
     */
    public void setRelacionados(ArrayOfComprobanteNacionalRelacionadoAuxiliar value) {
        this.relacionados = value;
    }

    /**
     * Obtiene el valor de la propiedad extranjeros.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfComprobanteExtranjeroAuxiliar }
     *     
     */
    public ArrayOfComprobanteExtranjeroAuxiliar getExtranjeros() {
        return extranjeros;
    }

    /**
     * Define el valor de la propiedad extranjeros.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfComprobanteExtranjeroAuxiliar }
     *     
     */
    public void setExtranjeros(ArrayOfComprobanteExtranjeroAuxiliar value) {
        this.extranjeros = value;
    }

}
