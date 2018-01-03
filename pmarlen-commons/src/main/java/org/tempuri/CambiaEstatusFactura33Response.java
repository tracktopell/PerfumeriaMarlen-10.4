
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
 *         &lt;element name="CambiaEstatusFactura33Result" type="{http://tempuri.org/}CambiaEstatus33Response" minOccurs="0"/>
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
    "cambiaEstatusFactura33Result"
})
@XmlRootElement(name = "CambiaEstatusFactura33Response")
public class CambiaEstatusFactura33Response {

    @XmlElement(name = "CambiaEstatusFactura33Result")
    protected CambiaEstatus33Response cambiaEstatusFactura33Result;

    /**
     * Obtiene el valor de la propiedad cambiaEstatusFactura33Result.
     * 
     * @return
     *     possible object is
     *     {@link CambiaEstatus33Response }
     *     
     */
    public CambiaEstatus33Response getCambiaEstatusFactura33Result() {
        return cambiaEstatusFactura33Result;
    }

    /**
     * Define el valor de la propiedad cambiaEstatusFactura33Result.
     * 
     * @param value
     *     allowed object is
     *     {@link CambiaEstatus33Response }
     *     
     */
    public void setCambiaEstatusFactura33Result(CambiaEstatus33Response value) {
        this.cambiaEstatusFactura33Result = value;
    }

}
