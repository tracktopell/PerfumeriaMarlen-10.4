
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="cambiorequest" type="{http://tempuri.org/}CambiaEstatus33Request" minOccurs="0"/>
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
    "cambiorequest"
})
@XmlRootElement(name = "CambiaEstatusFactura33")
public class CambiaEstatusFactura33 {

    protected CambiaEstatus33Request cambiorequest;

    /**
     * Obtiene el valor de la propiedad cambiorequest.
     * 
     * @return
     *     possible object is
     *     {@link CambiaEstatus33Request }
     *     
     */
    public CambiaEstatus33Request getCambiorequest() {
        return cambiorequest;
    }

    /**
     * Define el valor de la propiedad cambiorequest.
     * 
     * @param value
     *     allowed object is
     *     {@link CambiaEstatus33Request }
     *     
     */
    public void setCambiorequest(CambiaEstatus33Request value) {
        this.cambiorequest = value;
    }

}
