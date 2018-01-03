
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
 *         &lt;element name="CancelaCFDINominaPlus12Result" type="{http://tempuri.org/}TimbradoPlusNominaCancelacionResponse" minOccurs="0"/>
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
    "cancelaCFDINominaPlus12Result"
})
@XmlRootElement(name = "CancelaCFDINominaPlus12Response")
public class CancelaCFDINominaPlus12Response {

    @XmlElement(name = "CancelaCFDINominaPlus12Result")
    protected TimbradoPlusNominaCancelacionResponse cancelaCFDINominaPlus12Result;

    /**
     * Obtiene el valor de la propiedad cancelaCFDINominaPlus12Result.
     * 
     * @return
     *     possible object is
     *     {@link TimbradoPlusNominaCancelacionResponse }
     *     
     */
    public TimbradoPlusNominaCancelacionResponse getCancelaCFDINominaPlus12Result() {
        return cancelaCFDINominaPlus12Result;
    }

    /**
     * Define el valor de la propiedad cancelaCFDINominaPlus12Result.
     * 
     * @param value
     *     allowed object is
     *     {@link TimbradoPlusNominaCancelacionResponse }
     *     
     */
    public void setCancelaCFDINominaPlus12Result(TimbradoPlusNominaCancelacionResponse value) {
        this.cancelaCFDINominaPlus12Result = value;
    }

}
