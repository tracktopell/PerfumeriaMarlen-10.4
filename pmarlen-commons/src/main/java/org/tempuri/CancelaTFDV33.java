
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
 *         &lt;element name="cancelacionrequest" type="{http://tempuri.org/}Timbradov33CancelacionRequest" minOccurs="0"/>
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
    "cancelacionrequest"
})
@XmlRootElement(name = "CancelaTFDV33")
public class CancelaTFDV33 {

    protected Timbradov33CancelacionRequest cancelacionrequest;

    /**
     * Obtiene el valor de la propiedad cancelacionrequest.
     * 
     * @return
     *     possible object is
     *     {@link Timbradov33CancelacionRequest }
     *     
     */
    public Timbradov33CancelacionRequest getCancelacionrequest() {
        return cancelacionrequest;
    }

    /**
     * Define el valor de la propiedad cancelacionrequest.
     * 
     * @param value
     *     allowed object is
     *     {@link Timbradov33CancelacionRequest }
     *     
     */
    public void setCancelacionrequest(Timbradov33CancelacionRequest value) {
        this.cancelacionrequest = value;
    }

}
