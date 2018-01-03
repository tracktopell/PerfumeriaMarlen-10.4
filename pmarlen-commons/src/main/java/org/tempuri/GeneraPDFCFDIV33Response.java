
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
 *         &lt;element name="GeneraPDFCFDIV33Result" type="{http://tempuri.org/}PDFCFDIResponse" minOccurs="0"/>
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
    "generaPDFCFDIV33Result"
})
@XmlRootElement(name = "GeneraPDFCFDIV33Response")
public class GeneraPDFCFDIV33Response {

    @XmlElement(name = "GeneraPDFCFDIV33Result")
    protected PDFCFDIResponse generaPDFCFDIV33Result;

    /**
     * Obtiene el valor de la propiedad generaPDFCFDIV33Result.
     * 
     * @return
     *     possible object is
     *     {@link PDFCFDIResponse }
     *     
     */
    public PDFCFDIResponse getGeneraPDFCFDIV33Result() {
        return generaPDFCFDIV33Result;
    }

    /**
     * Define el valor de la propiedad generaPDFCFDIV33Result.
     * 
     * @param value
     *     allowed object is
     *     {@link PDFCFDIResponse }
     *     
     */
    public void setGeneraPDFCFDIV33Result(PDFCFDIResponse value) {
        this.generaPDFCFDIV33Result = value;
    }

}
