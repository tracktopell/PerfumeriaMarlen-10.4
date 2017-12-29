
package com.pmarlen.digifactws2018.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para BalanzaComprobacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="BalanzaComprobacion">
 *   &lt;complexContent>
 *     &lt;extension base="{https://cfd.sicofi.com.mx}GeneralesConta">
 *       &lt;sequence>
 *         &lt;element name="TipoEnvio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaModificacionBalanza" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cuentas" type="{https://cfd.sicofi.com.mx}ArrayOfCuentaBalanza" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BalanzaComprobacion", propOrder = {
    "tipoEnvio",
    "fechaModificacionBalanza",
    "cuentas"
})
public class BalanzaComprobacion
    extends GeneralesConta
{

    @XmlElement(name = "TipoEnvio")
    protected String tipoEnvio;
    @XmlElement(name = "FechaModificacionBalanza")
    protected String fechaModificacionBalanza;
    @XmlElement(name = "Cuentas")
    protected ArrayOfCuentaBalanza cuentas;

    /**
     * Obtiene el valor de la propiedad tipoEnvio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoEnvio() {
        return tipoEnvio;
    }

    /**
     * Define el valor de la propiedad tipoEnvio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoEnvio(String value) {
        this.tipoEnvio = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaModificacionBalanza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaModificacionBalanza() {
        return fechaModificacionBalanza;
    }

    /**
     * Define el valor de la propiedad fechaModificacionBalanza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaModificacionBalanza(String value) {
        this.fechaModificacionBalanza = value;
    }

    /**
     * Obtiene el valor de la propiedad cuentas.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCuentaBalanza }
     *     
     */
    public ArrayOfCuentaBalanza getCuentas() {
        return cuentas;
    }

    /**
     * Define el valor de la propiedad cuentas.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCuentaBalanza }
     *     
     */
    public void setCuentas(ArrayOfCuentaBalanza value) {
        this.cuentas = value;
    }

}
