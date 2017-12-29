
package com.pmarlen.digifactws2018.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="parametro1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="percepcion" type="{https://cfd.sicofi.com.mx}Percepcion" minOccurs="0"/>
 *         &lt;element name="deduccion" type="{https://cfd.sicofi.com.mx}Deduccion" minOccurs="0"/>
 *         &lt;element name="incapacidad" type="{https://cfd.sicofi.com.mx}Incapacidad" minOccurs="0"/>
 *         &lt;element name="horaextra" type="{https://cfd.sicofi.com.mx}HoraExtra" minOccurs="0"/>
 *         &lt;element name="datosnomina" type="{https://cfd.sicofi.com.mx}ArrDatosNom" minOccurs="0"/>
 *         &lt;element name="mercancia" type="{https://cfd.sicofi.com.mx}Mercancia" minOccurs="0"/>
 *         &lt;element name="Descripcion" type="{https://cfd.sicofi.com.mx}DescripcionEspecifica" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "parametro1",
    "percepcion",
    "deduccion",
    "incapacidad",
    "horaextra",
    "datosnomina",
    "mercancia",
    "descripcion"
})
@XmlRootElement(name = "TestConexion")
public class TestConexion {

    protected String parametro1;
    protected Percepcion percepcion;
    protected Deduccion deduccion;
    protected Incapacidad incapacidad;
    protected HoraExtra horaextra;
    protected ArrDatosNom datosnomina;
    protected Mercancia mercancia;
    @XmlElement(name = "Descripcion")
    protected DescripcionEspecifica descripcion;

    /**
     * Obtiene el valor de la propiedad parametro1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParametro1() {
        return parametro1;
    }

    /**
     * Define el valor de la propiedad parametro1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParametro1(String value) {
        this.parametro1 = value;
    }

    /**
     * Obtiene el valor de la propiedad percepcion.
     * 
     * @return
     *     possible object is
     *     {@link Percepcion }
     *     
     */
    public Percepcion getPercepcion() {
        return percepcion;
    }

    /**
     * Define el valor de la propiedad percepcion.
     * 
     * @param value
     *     allowed object is
     *     {@link Percepcion }
     *     
     */
    public void setPercepcion(Percepcion value) {
        this.percepcion = value;
    }

    /**
     * Obtiene el valor de la propiedad deduccion.
     * 
     * @return
     *     possible object is
     *     {@link Deduccion }
     *     
     */
    public Deduccion getDeduccion() {
        return deduccion;
    }

    /**
     * Define el valor de la propiedad deduccion.
     * 
     * @param value
     *     allowed object is
     *     {@link Deduccion }
     *     
     */
    public void setDeduccion(Deduccion value) {
        this.deduccion = value;
    }

    /**
     * Obtiene el valor de la propiedad incapacidad.
     * 
     * @return
     *     possible object is
     *     {@link Incapacidad }
     *     
     */
    public Incapacidad getIncapacidad() {
        return incapacidad;
    }

    /**
     * Define el valor de la propiedad incapacidad.
     * 
     * @param value
     *     allowed object is
     *     {@link Incapacidad }
     *     
     */
    public void setIncapacidad(Incapacidad value) {
        this.incapacidad = value;
    }

    /**
     * Obtiene el valor de la propiedad horaextra.
     * 
     * @return
     *     possible object is
     *     {@link HoraExtra }
     *     
     */
    public HoraExtra getHoraextra() {
        return horaextra;
    }

    /**
     * Define el valor de la propiedad horaextra.
     * 
     * @param value
     *     allowed object is
     *     {@link HoraExtra }
     *     
     */
    public void setHoraextra(HoraExtra value) {
        this.horaextra = value;
    }

    /**
     * Obtiene el valor de la propiedad datosnomina.
     * 
     * @return
     *     possible object is
     *     {@link ArrDatosNom }
     *     
     */
    public ArrDatosNom getDatosnomina() {
        return datosnomina;
    }

    /**
     * Define el valor de la propiedad datosnomina.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrDatosNom }
     *     
     */
    public void setDatosnomina(ArrDatosNom value) {
        this.datosnomina = value;
    }

    /**
     * Obtiene el valor de la propiedad mercancia.
     * 
     * @return
     *     possible object is
     *     {@link Mercancia }
     *     
     */
    public Mercancia getMercancia() {
        return mercancia;
    }

    /**
     * Define el valor de la propiedad mercancia.
     * 
     * @param value
     *     allowed object is
     *     {@link Mercancia }
     *     
     */
    public void setMercancia(Mercancia value) {
        this.mercancia = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link DescripcionEspecifica }
     *     
     */
    public DescripcionEspecifica getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link DescripcionEspecifica }
     *     
     */
    public void setDescripcion(DescripcionEspecifica value) {
        this.descripcion = value;
    }

}
