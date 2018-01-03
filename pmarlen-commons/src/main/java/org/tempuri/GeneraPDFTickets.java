
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
 *         &lt;element name="pdfrequest" type="{http://tempuri.org/}PDFTicketRquest" minOccurs="0"/>
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
    "pdfrequest"
})
@XmlRootElement(name = "GeneraPDFTickets")
public class GeneraPDFTickets {

    protected PDFTicketRquest pdfrequest;

    /**
     * Obtiene el valor de la propiedad pdfrequest.
     * 
     * @return
     *     possible object is
     *     {@link PDFTicketRquest }
     *     
     */
    public PDFTicketRquest getPdfrequest() {
        return pdfrequest;
    }

    /**
     * Define el valor de la propiedad pdfrequest.
     * 
     * @param value
     *     allowed object is
     *     {@link PDFTicketRquest }
     *     
     */
    public void setPdfrequest(PDFTicketRquest value) {
        this.pdfrequest = value;
    }

}
