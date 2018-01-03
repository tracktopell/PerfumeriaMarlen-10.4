
package mx.gob.sat.notariospublicos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DatosEnajenante complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DatosEnajenante">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DatosUnEnajenante" type="{http://www.sat.gob.mx/notariospublicos}DatosUnEnajenante" minOccurs="0"/>
 *         &lt;element name="DatosEnajenantesCopSC" type="{http://www.sat.gob.mx/notariospublicos}DatosEnajenantesCopSC" minOccurs="0"/>
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
@XmlType(name = "DatosEnajenante", propOrder = {
    "datosUnEnajenante",
    "datosEnajenantesCopSC"
})
public class DatosEnajenante {

    @XmlElement(name = "DatosUnEnajenante")
    protected DatosUnEnajenante datosUnEnajenante;
    @XmlElement(name = "DatosEnajenantesCopSC")
    protected DatosEnajenantesCopSC datosEnajenantesCopSC;
    @XmlAttribute(name = "CoproSocConyugalE")
    protected String coproSocConyugalE;

    /**
     * Obtiene el valor de la propiedad datosUnEnajenante.
     * 
     * @return
     *     possible object is
     *     {@link DatosUnEnajenante }
     *     
     */
    public DatosUnEnajenante getDatosUnEnajenante() {
        return datosUnEnajenante;
    }

    /**
     * Define el valor de la propiedad datosUnEnajenante.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosUnEnajenante }
     *     
     */
    public void setDatosUnEnajenante(DatosUnEnajenante value) {
        this.datosUnEnajenante = value;
    }

    /**
     * Obtiene el valor de la propiedad datosEnajenantesCopSC.
     * 
     * @return
     *     possible object is
     *     {@link DatosEnajenantesCopSC }
     *     
     */
    public DatosEnajenantesCopSC getDatosEnajenantesCopSC() {
        return datosEnajenantesCopSC;
    }

    /**
     * Define el valor de la propiedad datosEnajenantesCopSC.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosEnajenantesCopSC }
     *     
     */
    public void setDatosEnajenantesCopSC(DatosEnajenantesCopSC value) {
        this.datosEnajenantesCopSC = value;
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
