
package com.pmarlen.digifactws2018.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ObjetoX complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ObjetoX">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arregloString" type="{https://cfd.sicofi.com.mx}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="arregloDouble" type="{https://cfd.sicofi.com.mx}ArrayOfDouble" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObjetoX", propOrder = {
    "arregloString",
    "arregloDouble"
})
public class ObjetoX {

    protected ArrayOfString arregloString;
    protected ArrayOfDouble arregloDouble;

    /**
     * Obtiene el valor de la propiedad arregloString.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getArregloString() {
        return arregloString;
    }

    /**
     * Define el valor de la propiedad arregloString.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setArregloString(ArrayOfString value) {
        this.arregloString = value;
    }

    /**
     * Obtiene el valor de la propiedad arregloDouble.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDouble }
     *     
     */
    public ArrayOfDouble getArregloDouble() {
        return arregloDouble;
    }

    /**
     * Define el valor de la propiedad arregloDouble.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDouble }
     *     
     */
    public void setArregloDouble(ArrayOfDouble value) {
        this.arregloDouble = value;
    }

}
