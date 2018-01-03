
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ValidaCFDI33Response complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ValidaCFDI33Response">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Estructura" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Sello" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Timbre" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ValidacionCorrecta" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="CadenaValidacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CodigoError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MensajeError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidaCFDI33Response", propOrder = {
    "estructura",
    "sello",
    "timbre",
    "validacionCorrecta",
    "cadenaValidacion",
    "codigoError",
    "mensajeError"
})
public class ValidaCFDI33Response {

    @XmlElement(name = "Estructura")
    protected boolean estructura;
    @XmlElement(name = "Sello")
    protected boolean sello;
    @XmlElement(name = "Timbre")
    protected boolean timbre;
    @XmlElement(name = "ValidacionCorrecta")
    protected boolean validacionCorrecta;
    @XmlElement(name = "CadenaValidacion")
    protected String cadenaValidacion;
    @XmlElement(name = "CodigoError")
    protected String codigoError;
    @XmlElement(name = "MensajeError")
    protected String mensajeError;

    /**
     * Obtiene el valor de la propiedad estructura.
     * 
     */
    public boolean isEstructura() {
        return estructura;
    }

    /**
     * Define el valor de la propiedad estructura.
     * 
     */
    public void setEstructura(boolean value) {
        this.estructura = value;
    }

    /**
     * Obtiene el valor de la propiedad sello.
     * 
     */
    public boolean isSello() {
        return sello;
    }

    /**
     * Define el valor de la propiedad sello.
     * 
     */
    public void setSello(boolean value) {
        this.sello = value;
    }

    /**
     * Obtiene el valor de la propiedad timbre.
     * 
     */
    public boolean isTimbre() {
        return timbre;
    }

    /**
     * Define el valor de la propiedad timbre.
     * 
     */
    public void setTimbre(boolean value) {
        this.timbre = value;
    }

    /**
     * Obtiene el valor de la propiedad validacionCorrecta.
     * 
     */
    public boolean isValidacionCorrecta() {
        return validacionCorrecta;
    }

    /**
     * Define el valor de la propiedad validacionCorrecta.
     * 
     */
    public void setValidacionCorrecta(boolean value) {
        this.validacionCorrecta = value;
    }

    /**
     * Obtiene el valor de la propiedad cadenaValidacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCadenaValidacion() {
        return cadenaValidacion;
    }

    /**
     * Define el valor de la propiedad cadenaValidacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCadenaValidacion(String value) {
        this.cadenaValidacion = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoError.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoError() {
        return codigoError;
    }

    /**
     * Define el valor de la propiedad codigoError.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoError(String value) {
        this.codigoError = value;
    }

    /**
     * Obtiene el valor de la propiedad mensajeError.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensajeError() {
        return mensajeError;
    }

    /**
     * Define el valor de la propiedad mensajeError.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensajeError(String value) {
        this.mensajeError = value;
    }

}
