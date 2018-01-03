
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ConceptosComercioCFDI33 complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConceptosComercioCFDI33">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="conceptos" type="{http://tempuri.org/}ArrayOfConceptoComercioCFDI33" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConceptosComercioCFDI33", propOrder = {
    "conceptos"
})
public class ConceptosComercioCFDI33 {

    protected ArrayOfConceptoComercioCFDI33 conceptos;

    /**
     * Obtiene el valor de la propiedad conceptos.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfConceptoComercioCFDI33 }
     *     
     */
    public ArrayOfConceptoComercioCFDI33 getConceptos() {
        return conceptos;
    }

    /**
     * Define el valor de la propiedad conceptos.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfConceptoComercioCFDI33 }
     *     
     */
    public void setConceptos(ArrayOfConceptoComercioCFDI33 value) {
        this.conceptos = value;
    }

}
