
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
 *         &lt;element name="GeneraPDFNomina12Result" type="{http://tempuri.org/}PDFNominaResponse" minOccurs="0"/>
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
    "generaPDFNomina12Result"
})
@XmlRootElement(name = "GeneraPDFNomina12Response")
public class GeneraPDFNomina12Response {

    @XmlElement(name = "GeneraPDFNomina12Result")
    protected PDFNominaResponse generaPDFNomina12Result;

    /**
     * Obtiene el valor de la propiedad generaPDFNomina12Result.
     * 
     * @return
     *     possible object is
     *     {@link PDFNominaResponse }
     *     
     */
    public PDFNominaResponse getGeneraPDFNomina12Result() {
        return generaPDFNomina12Result;
    }

    /**
     * Define el valor de la propiedad generaPDFNomina12Result.
     * 
     * @param value
     *     allowed object is
     *     {@link PDFNominaResponse }
     *     
     */
    public void setGeneraPDFNomina12Result(PDFNominaResponse value) {
        this.generaPDFNomina12Result = value;
    }

}
