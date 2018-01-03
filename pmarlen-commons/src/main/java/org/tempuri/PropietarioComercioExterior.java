
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para PropietarioComercioExterior complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PropietarioComercioExterior">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="propietarios" type="{http://tempuri.org/}ArrayOfPropietario" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PropietarioComercioExterior", propOrder = {
    "propietarios"
})
public class PropietarioComercioExterior {

    protected ArrayOfPropietario propietarios;

    /**
     * Obtiene el valor de la propiedad propietarios.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPropietario }
     *     
     */
    public ArrayOfPropietario getPropietarios() {
        return propietarios;
    }

    /**
     * Define el valor de la propiedad propietarios.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPropietario }
     *     
     */
    public void setPropietarios(ArrayOfPropietario value) {
        this.propietarios = value;
    }

}
