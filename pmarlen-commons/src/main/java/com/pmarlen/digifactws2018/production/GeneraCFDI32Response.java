
package com.pmarlen.digifactws2018.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GeneraCFDI32Result" type="{https://cfd.sicofi.com.mx}RespuestaCFDI" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "generaCFDI32Result"
})
@XmlRootElement(name = "GeneraCFDI32Response")
public class GeneraCFDI32Response {

    @XmlElement(name = "GeneraCFDI32Result")
    protected RespuestaCFDI generaCFDI32Result;

    /**
     * Obtiene el valor de la propiedad generaCFDI32Result.
     * 
     * @return
     *     possible object is
     *     {@link RespuestaCFDI }
     *     
     */
    public RespuestaCFDI getGeneraCFDI32Result() {
        return generaCFDI32Result;
    }

    /**
     * Define el valor de la propiedad generaCFDI32Result.
     * 
     * @param value
     *     allowed object is
     *     {@link RespuestaCFDI }
     *     
     */
    public void setGeneraCFDI32Result(RespuestaCFDI value) {
        this.generaCFDI32Result = value;
    }

}
