
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para OtrosPagos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="OtrosPagos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="otrospagos" type="{http://tempuri.org/}ArrayOfOtroPago" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OtrosPagos", propOrder = {
    "otrospagos"
})
public class OtrosPagos {

    protected ArrayOfOtroPago otrospagos;

    /**
     * Obtiene el valor de la propiedad otrospagos.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfOtroPago }
     *     
     */
    public ArrayOfOtroPago getOtrospagos() {
        return otrospagos;
    }

    /**
     * Define el valor de la propiedad otrospagos.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfOtroPago }
     *     
     */
    public void setOtrospagos(ArrayOfOtroPago value) {
        this.otrospagos = value;
    }

}
