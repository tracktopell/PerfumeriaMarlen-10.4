
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ConceptosTimbradoPlus complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConceptosTimbradoPlus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Conceptos" type="{http://tempuri.org/}ArrayOfConceptoCFDITimbradoPlus" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConceptosTimbradoPlus", propOrder = {
    "conceptos"
})
public class ConceptosTimbradoPlus {

    @XmlElement(name = "Conceptos")
    protected ArrayOfConceptoCFDITimbradoPlus conceptos;

    /**
     * Obtiene el valor de la propiedad conceptos.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfConceptoCFDITimbradoPlus }
     *     
     */
    public ArrayOfConceptoCFDITimbradoPlus getConceptos() {
        return conceptos;
    }

    /**
     * Define el valor de la propiedad conceptos.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfConceptoCFDITimbradoPlus }
     *     
     */
    public void setConceptos(ArrayOfConceptoCFDITimbradoPlus value) {
        this.conceptos = value;
    }

}
