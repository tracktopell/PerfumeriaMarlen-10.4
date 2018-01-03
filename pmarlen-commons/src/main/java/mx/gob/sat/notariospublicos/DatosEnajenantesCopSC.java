
package mx.gob.sat.notariospublicos;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DatosEnajenantesCopSC complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DatosEnajenantesCopSC">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DatosEnajenanteCopSC" type="{http://www.sat.gob.mx/notariospublicos}DatosEnajenanteCopSC" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatosEnajenantesCopSC", propOrder = {
    "datosEnajenanteCopSC"
})
public class DatosEnajenantesCopSC {

    @XmlElement(name = "DatosEnajenanteCopSC")
    protected List<DatosEnajenanteCopSC> datosEnajenanteCopSC;

    /**
     * Gets the value of the datosEnajenanteCopSC property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the datosEnajenanteCopSC property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDatosEnajenanteCopSC().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DatosEnajenanteCopSC }
     * 
     * 
     */
    public List<DatosEnajenanteCopSC> getDatosEnajenanteCopSC() {
        if (datosEnajenanteCopSC == null) {
            datosEnajenanteCopSC = new ArrayList<DatosEnajenanteCopSC>();
        }
        return this.datosEnajenanteCopSC;
    }

}
