
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ComplementoPagos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ComplementoPagos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CFDIPagos" type="{http://tempuri.org/}ArrayOfPagoCP" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplementoPagos", propOrder = {
    "cfdiPagos"
})
public class ComplementoPagos {

    @XmlElement(name = "CFDIPagos")
    protected ArrayOfPagoCP cfdiPagos;

    /**
     * Obtiene el valor de la propiedad cfdiPagos.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPagoCP }
     *     
     */
    public ArrayOfPagoCP getCFDIPagos() {
        return cfdiPagos;
    }

    /**
     * Define el valor de la propiedad cfdiPagos.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPagoCP }
     *     
     */
    public void setCFDIPagos(ArrayOfPagoCP value) {
        this.cfdiPagos = value;
    }

}
