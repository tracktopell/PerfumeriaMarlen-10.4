
package mx.gob.sat.terceros;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Impuestos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Impuestos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Retenciones" type="{http://www.sat.gob.mx/terceros}Retenciones" minOccurs="0"/>
 *         &lt;element name="Traslados" type="{http://www.sat.gob.mx/terceros}Traslados" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Impuestos", propOrder = {
    "retenciones",
    "traslados"
})
public class Impuestos {

    @XmlElement(name = "Retenciones")
    protected Retenciones retenciones;
    @XmlElement(name = "Traslados")
    protected Traslados traslados;

    /**
     * Obtiene el valor de la propiedad retenciones.
     * 
     * @return
     *     possible object is
     *     {@link Retenciones }
     *     
     */
    public Retenciones getRetenciones() {
        return retenciones;
    }

    /**
     * Define el valor de la propiedad retenciones.
     * 
     * @param value
     *     allowed object is
     *     {@link Retenciones }
     *     
     */
    public void setRetenciones(Retenciones value) {
        this.retenciones = value;
    }

    /**
     * Obtiene el valor de la propiedad traslados.
     * 
     * @return
     *     possible object is
     *     {@link Traslados }
     *     
     */
    public Traslados getTraslados() {
        return traslados;
    }

    /**
     * Define el valor de la propiedad traslados.
     * 
     * @param value
     *     allowed object is
     *     {@link Traslados }
     *     
     */
    public void setTraslados(Traslados value) {
        this.traslados = value;
    }

}
