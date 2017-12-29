
package com.pmarlen.digifactws2018.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para AuxiliarFolios complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="AuxiliarFolios">
 *   &lt;complexContent>
 *     &lt;extension base="{https://cfd.sicofi.com.mx}GeneralesAuxiliares">
 *       &lt;sequence>
 *         &lt;element name="Detalles" type="{https://cfd.sicofi.com.mx}ArrayOfDetallesAxuFolios" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuxiliarFolios", propOrder = {
    "detalles"
})
public class AuxiliarFolios
    extends GeneralesAuxiliares
{

    @XmlElement(name = "Detalles")
    protected ArrayOfDetallesAxuFolios detalles;

    /**
     * Obtiene el valor de la propiedad detalles.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDetallesAxuFolios }
     *     
     */
    public ArrayOfDetallesAxuFolios getDetalles() {
        return detalles;
    }

    /**
     * Define el valor de la propiedad detalles.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDetallesAxuFolios }
     *     
     */
    public void setDetalles(ArrayOfDetallesAxuFolios value) {
        this.detalles = value;
    }

}
