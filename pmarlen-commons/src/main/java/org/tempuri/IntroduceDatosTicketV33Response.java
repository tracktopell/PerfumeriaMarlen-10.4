
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
 *         &lt;element name="IntroduceDatosTicketV33Result" type="{http://tempuri.org/}TicketResponse" minOccurs="0"/>
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
    "introduceDatosTicketV33Result"
})
@XmlRootElement(name = "IntroduceDatosTicketV33Response")
public class IntroduceDatosTicketV33Response {

    @XmlElement(name = "IntroduceDatosTicketV33Result")
    protected TicketResponse introduceDatosTicketV33Result;

    /**
     * Obtiene el valor de la propiedad introduceDatosTicketV33Result.
     * 
     * @return
     *     possible object is
     *     {@link TicketResponse }
     *     
     */
    public TicketResponse getIntroduceDatosTicketV33Result() {
        return introduceDatosTicketV33Result;
    }

    /**
     * Define el valor de la propiedad introduceDatosTicketV33Result.
     * 
     * @param value
     *     allowed object is
     *     {@link TicketResponse }
     *     
     */
    public void setIntroduceDatosTicketV33Result(TicketResponse value) {
        this.introduceDatosTicketV33Result = value;
    }

}
