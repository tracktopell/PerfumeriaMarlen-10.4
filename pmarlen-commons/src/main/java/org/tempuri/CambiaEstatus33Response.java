
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CambiaEstatus33Response complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CambiaEstatus33Response">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodigoError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ErrorCambioEstatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CambioCorrecto" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CambiaEstatus33Response", propOrder = {
    "codigoError",
    "errorCambioEstatus",
    "cambioCorrecto"
})
public class CambiaEstatus33Response {

    @XmlElement(name = "CodigoError")
    protected String codigoError;
    @XmlElement(name = "ErrorCambioEstatus")
    protected String errorCambioEstatus;
    @XmlElement(name = "CambioCorrecto")
    protected boolean cambioCorrecto;

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
     * Obtiene el valor de la propiedad errorCambioEstatus.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorCambioEstatus() {
        return errorCambioEstatus;
    }

    /**
     * Define el valor de la propiedad errorCambioEstatus.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorCambioEstatus(String value) {
        this.errorCambioEstatus = value;
    }

    /**
     * Obtiene el valor de la propiedad cambioCorrecto.
     * 
     */
    public boolean isCambioCorrecto() {
        return cambioCorrecto;
    }

    /**
     * Define el valor de la propiedad cambioCorrecto.
     * 
     */
    public void setCambioCorrecto(boolean value) {
        this.cambioCorrecto = value;
    }

}
