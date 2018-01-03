
package org.tempuri;

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
 *         &lt;element name="ValidaCFDIV33Result" type="{http://tempuri.org/}ValidaCFDI33Response" minOccurs="0"/>
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
    "validaCFDIV33Result"
})
@XmlRootElement(name = "ValidaCFDIV33Response")
public class ValidaCFDIV33Response {

    @XmlElement(name = "ValidaCFDIV33Result")
    protected ValidaCFDI33Response validaCFDIV33Result;

    /**
     * Obtiene el valor de la propiedad validaCFDIV33Result.
     * 
     * @return
     *     possible object is
     *     {@link ValidaCFDI33Response }
     *     
     */
    public ValidaCFDI33Response getValidaCFDIV33Result() {
        return validaCFDIV33Result;
    }

    /**
     * Define el valor de la propiedad validaCFDIV33Result.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidaCFDI33Response }
     *     
     */
    public void setValidaCFDIV33Result(ValidaCFDI33Response value) {
        this.validaCFDIV33Result = value;
    }

}
