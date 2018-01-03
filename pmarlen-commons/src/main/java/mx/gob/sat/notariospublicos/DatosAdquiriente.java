
package mx.gob.sat.notariospublicos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DatosAdquiriente complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DatosAdquiriente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DatosUnAdquiriente" type="{http://www.sat.gob.mx/notariospublicos}DatosUnAdquiriente" minOccurs="0"/>
 *         &lt;element name="DatosAdquirientesCopSC" type="{http://www.sat.gob.mx/notariospublicos}DatosAdquirientesCopSC" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="CoproSocConyugalE" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatosAdquiriente", propOrder = {
    "datosUnAdquiriente",
    "datosAdquirientesCopSC"
})
public class DatosAdquiriente {

    @XmlElement(name = "DatosUnAdquiriente")
    protected DatosUnAdquiriente datosUnAdquiriente;
    @XmlElement(name = "DatosAdquirientesCopSC")
    protected DatosAdquirientesCopSC datosAdquirientesCopSC;
    @XmlAttribute(name = "CoproSocConyugalE")
    protected String coproSocConyugalE;

    /**
     * Obtiene el valor de la propiedad datosUnAdquiriente.
     * 
     * @return
     *     possible object is
     *     {@link DatosUnAdquiriente }
     *     
     */
    public DatosUnAdquiriente getDatosUnAdquiriente() {
        return datosUnAdquiriente;
    }

    /**
     * Define el valor de la propiedad datosUnAdquiriente.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosUnAdquiriente }
     *     
     */
    public void setDatosUnAdquiriente(DatosUnAdquiriente value) {
        this.datosUnAdquiriente = value;
    }

    /**
     * Obtiene el valor de la propiedad datosAdquirientesCopSC.
     * 
     * @return
     *     possible object is
     *     {@link DatosAdquirientesCopSC }
     *     
     */
    public DatosAdquirientesCopSC getDatosAdquirientesCopSC() {
        return datosAdquirientesCopSC;
    }

    /**
     * Define el valor de la propiedad datosAdquirientesCopSC.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosAdquirientesCopSC }
     *     
     */
    public void setDatosAdquirientesCopSC(DatosAdquirientesCopSC value) {
        this.datosAdquirientesCopSC = value;
    }

    /**
     * Obtiene el valor de la propiedad coproSocConyugalE.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoproSocConyugalE() {
        return coproSocConyugalE;
    }

    /**
     * Define el valor de la propiedad coproSocConyugalE.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoproSocConyugalE(String value) {
        this.coproSocConyugalE = value;
    }

}
