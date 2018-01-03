
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para PDFCFDIResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PDFCFDIResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PDF" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="CodigoError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ErrorPDF" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PDFCorrecto" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PDFCFDIResponse", propOrder = {
    "pdf",
    "codigoError",
    "errorPDF",
    "pdfCorrecto"
})
public class PDFCFDIResponse {

    @XmlElement(name = "PDF")
    protected byte[] pdf;
    @XmlElement(name = "CodigoError")
    protected String codigoError;
    @XmlElement(name = "ErrorPDF")
    protected String errorPDF;
    @XmlElement(name = "PDFCorrecto")
    protected boolean pdfCorrecto;

    /**
     * Obtiene el valor de la propiedad pdf.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getPDF() {
        return pdf;
    }

    /**
     * Define el valor de la propiedad pdf.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setPDF(byte[] value) {
        this.pdf = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoError.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoError() {
        return codigoError;
    }

    /**
     * Define el valor de la propiedad codigoError.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoError(String value) {
        this.codigoError = value;
    }

    /**
     * Obtiene el valor de la propiedad errorPDF.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorPDF() {
        return errorPDF;
    }

    /**
     * Define el valor de la propiedad errorPDF.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorPDF(String value) {
        this.errorPDF = value;
    }

    /**
     * Obtiene el valor de la propiedad pdfCorrecto.
     * 
     */
    public boolean isPDFCorrecto() {
        return pdfCorrecto;
    }

    /**
     * Define el valor de la propiedad pdfCorrecto.
     * 
     */
    public void setPDFCorrecto(boolean value) {
        this.pdfCorrecto = value;
    }

}
