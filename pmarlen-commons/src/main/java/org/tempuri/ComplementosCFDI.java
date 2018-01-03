
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import mx.gob.sat.aerolineas.Aerolineas;
import mx.gob.sat.ine.INE;
import mx.gob.sat.notariospublicos.NotariosPublicos;


/**
 * <p>Clase Java para ComplementosCFDI complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ComplementosCFDI">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LeyendasFiscales" type="{http://tempuri.org/}ArrayOfLeyenda" minOccurs="0"/>
 *         &lt;element name="INE" type="{http://www.sat.gob.mx/ine}INE" minOccurs="0"/>
 *         &lt;element name="Aerolineas" type="{http://www.sat.gob.mx/aerolineas}Aerolineas" minOccurs="0"/>
 *         &lt;element name="ImpuestosLocales" type="{http://tempuri.org/}OtrosImpuestos" minOccurs="0"/>
 *         &lt;element name="NotarioPublicos" type="{http://www.sat.gob.mx/notariospublicos}NotariosPublicos" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplementosCFDI", propOrder = {
    "leyendasFiscales",
    "ine",
    "aerolineas",
    "impuestosLocales",
    "notarioPublicos"
})
public class ComplementosCFDI {

    @XmlElement(name = "LeyendasFiscales")
    protected ArrayOfLeyenda leyendasFiscales;
    @XmlElement(name = "INE")
    protected INE ine;
    @XmlElement(name = "Aerolineas")
    protected Aerolineas aerolineas;
    @XmlElement(name = "ImpuestosLocales")
    protected OtrosImpuestos impuestosLocales;
    @XmlElement(name = "NotarioPublicos")
    protected NotariosPublicos notarioPublicos;

    /**
     * Obtiene el valor de la propiedad leyendasFiscales.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfLeyenda }
     *     
     */
    public ArrayOfLeyenda getLeyendasFiscales() {
        return leyendasFiscales;
    }

    /**
     * Define el valor de la propiedad leyendasFiscales.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfLeyenda }
     *     
     */
    public void setLeyendasFiscales(ArrayOfLeyenda value) {
        this.leyendasFiscales = value;
    }

    /**
     * Obtiene el valor de la propiedad ine.
     * 
     * @return
     *     possible object is
     *     {@link INE }
     *     
     */
    public INE getINE() {
        return ine;
    }

    /**
     * Define el valor de la propiedad ine.
     * 
     * @param value
     *     allowed object is
     *     {@link INE }
     *     
     */
    public void setINE(INE value) {
        this.ine = value;
    }

    /**
     * Obtiene el valor de la propiedad aerolineas.
     * 
     * @return
     *     possible object is
     *     {@link Aerolineas }
     *     
     */
    public Aerolineas getAerolineas() {
        return aerolineas;
    }

    /**
     * Define el valor de la propiedad aerolineas.
     * 
     * @param value
     *     allowed object is
     *     {@link Aerolineas }
     *     
     */
    public void setAerolineas(Aerolineas value) {
        this.aerolineas = value;
    }

    /**
     * Obtiene el valor de la propiedad impuestosLocales.
     * 
     * @return
     *     possible object is
     *     {@link OtrosImpuestos }
     *     
     */
    public OtrosImpuestos getImpuestosLocales() {
        return impuestosLocales;
    }

    /**
     * Define el valor de la propiedad impuestosLocales.
     * 
     * @param value
     *     allowed object is
     *     {@link OtrosImpuestos }
     *     
     */
    public void setImpuestosLocales(OtrosImpuestos value) {
        this.impuestosLocales = value;
    }

    /**
     * Obtiene el valor de la propiedad notarioPublicos.
     * 
     * @return
     *     possible object is
     *     {@link NotariosPublicos }
     *     
     */
    public NotariosPublicos getNotarioPublicos() {
        return notarioPublicos;
    }

    /**
     * Define el valor de la propiedad notarioPublicos.
     * 
     * @param value
     *     allowed object is
     *     {@link NotariosPublicos }
     *     
     */
    public void setNotarioPublicos(NotariosPublicos value) {
        this.notarioPublicos = value;
    }

}
