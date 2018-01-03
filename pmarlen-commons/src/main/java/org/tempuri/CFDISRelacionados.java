
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CFDISRelacionados complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CFDISRelacionados">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TipoRelacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Relacionados" type="{http://tempuri.org/}ArrayOfCFDISRelacionado" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CFDISRelacionados", propOrder = {
    "tipoRelacion",
    "relacionados"
})
public class CFDISRelacionados {

    @XmlElement(name = "TipoRelacion")
    protected String tipoRelacion;
    @XmlElement(name = "Relacionados")
    protected ArrayOfCFDISRelacionado relacionados;

    /**
     * Obtiene el valor de la propiedad tipoRelacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoRelacion() {
        return tipoRelacion;
    }

    /**
     * Define el valor de la propiedad tipoRelacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoRelacion(String value) {
        this.tipoRelacion = value;
    }

    /**
     * Obtiene el valor de la propiedad relacionados.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCFDISRelacionado }
     *     
     */
    public ArrayOfCFDISRelacionado getRelacionados() {
        return relacionados;
    }

    /**
     * Define el valor de la propiedad relacionados.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCFDISRelacionado }
     *     
     */
    public void setRelacionados(ArrayOfCFDISRelacionado value) {
        this.relacionados = value;
    }

}
