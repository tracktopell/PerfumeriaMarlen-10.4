
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
 *         &lt;element name="GeneraPDFTicketsResult" type="{http://tempuri.org/}PDFTicketResponse" minOccurs="0"/>
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
    "generaPDFTicketsResult"
})
@XmlRootElement(name = "GeneraPDFTicketsResponse")
public class GeneraPDFTicketsResponse {

    @XmlElement(name = "GeneraPDFTicketsResult")
    protected PDFTicketResponse generaPDFTicketsResult;

    /**
     * Obtiene el valor de la propiedad generaPDFTicketsResult.
     * 
     * @return
     *     possible object is
     *     {@link PDFTicketResponse }
     *     
     */
    public PDFTicketResponse getGeneraPDFTicketsResult() {
        return generaPDFTicketsResult;
    }

    /**
     * Define el valor de la propiedad generaPDFTicketsResult.
     * 
     * @param value
     *     allowed object is
     *     {@link PDFTicketResponse }
     *     
     */
    public void setGeneraPDFTicketsResult(PDFTicketResponse value) {
        this.generaPDFTicketsResult = value;
    }

}
