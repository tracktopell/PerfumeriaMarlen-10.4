
package mx.gob.sat.notariospublicos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DescInmueble complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DescInmueble">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="TipoInmueble" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Calle" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="NoExterior" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="NoInterior" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Colonia" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Localidad" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Referencia" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Municipio" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Estado" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Pais" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CodigoPostal" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DescInmueble")
public class DescInmueble {

    @XmlAttribute(name = "TipoInmueble")
    protected String tipoInmueble;
    @XmlAttribute(name = "Calle")
    protected String calle;
    @XmlAttribute(name = "NoExterior")
    protected String noExterior;
    @XmlAttribute(name = "NoInterior")
    protected String noInterior;
    @XmlAttribute(name = "Colonia")
    protected String colonia;
    @XmlAttribute(name = "Localidad")
    protected String localidad;
    @XmlAttribute(name = "Referencia")
    protected String referencia;
    @XmlAttribute(name = "Municipio")
    protected String municipio;
    @XmlAttribute(name = "Estado")
    protected String estado;
    @XmlAttribute(name = "Pais")
    protected String pais;
    @XmlAttribute(name = "CodigoPostal")
    protected String codigoPostal;

    /**
     * Obtiene el valor de la propiedad tipoInmueble.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoInmueble() {
        return tipoInmueble;
    }

    /**
     * Define el valor de la propiedad tipoInmueble.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoInmueble(String value) {
        this.tipoInmueble = value;
    }

    /**
     * Obtiene el valor de la propiedad calle.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Define el valor de la propiedad calle.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalle(String value) {
        this.calle = value;
    }

    /**
     * Obtiene el valor de la propiedad noExterior.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoExterior() {
        return noExterior;
    }

    /**
     * Define el valor de la propiedad noExterior.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoExterior(String value) {
        this.noExterior = value;
    }

    /**
     * Obtiene el valor de la propiedad noInterior.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoInterior() {
        return noInterior;
    }

    /**
     * Define el valor de la propiedad noInterior.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoInterior(String value) {
        this.noInterior = value;
    }

    /**
     * Obtiene el valor de la propiedad colonia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * Define el valor de la propiedad colonia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColonia(String value) {
        this.colonia = value;
    }

    /**
     * Obtiene el valor de la propiedad localidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * Define el valor de la propiedad localidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalidad(String value) {
        this.localidad = value;
    }

    /**
     * Obtiene el valor de la propiedad referencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * Define el valor de la propiedad referencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferencia(String value) {
        this.referencia = value;
    }

    /**
     * Obtiene el valor de la propiedad municipio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * Define el valor de la propiedad municipio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMunicipio(String value) {
        this.municipio = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad pais.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPais() {
        return pais;
    }

    /**
     * Define el valor de la propiedad pais.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPais(String value) {
        this.pais = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoPostal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * Define el valor de la propiedad codigoPostal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoPostal(String value) {
        this.codigoPostal = value;
    }

}
