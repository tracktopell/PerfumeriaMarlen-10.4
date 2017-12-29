
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
 *         &lt;element name="DescargaPDFMResult" type="{https://cfd.sicofi.com.mx}DescargaPDFResponse" minOccurs="0"/>
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
    "descargaPDFMResult"
})
@XmlRootElement(name = "DescargaPDFMResponse")
public class DescargaPDFMResponse {

    @XmlElement(name = "DescargaPDFMResult")
    protected DescargaPDFResponse descargaPDFMResult;

    /**
     * Obtiene el valor de la propiedad descargaPDFMResult.
     * 
     * @return
     *     possible object is
     *     {@link DescargaPDFResponse }
     *     
     */
    public DescargaPDFResponse getDescargaPDFMResult() {
        return descargaPDFMResult;
    }

    /**
     * Define el valor de la propiedad descargaPDFMResult.
     * 
     * @param value
     *     allowed object is
     *     {@link DescargaPDFResponse }
     *     
     */
    public void setDescargaPDFMResult(DescargaPDFResponse value) {
        this.descargaPDFMResult = value;
    }

}
