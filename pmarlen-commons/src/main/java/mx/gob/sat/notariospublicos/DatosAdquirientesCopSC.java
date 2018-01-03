
package mx.gob.sat.notariospublicos;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DatosAdquirientesCopSC complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DatosAdquirientesCopSC">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DatosAdquirienteCopSC" type="{http://www.sat.gob.mx/notariospublicos}DatosAdquirienteCopSC" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatosAdquirientesCopSC", propOrder = {
    "datosAdquirienteCopSC"
})
public class DatosAdquirientesCopSC {

    @XmlElement(name = "DatosAdquirienteCopSC")
    protected List<DatosAdquirienteCopSC> datosAdquirienteCopSC;

    /**
     * Gets the value of the datosAdquirienteCopSC property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the datosAdquirienteCopSC property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDatosAdquirienteCopSC().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DatosAdquirienteCopSC }
     * 
     * 
     */
    public List<DatosAdquirienteCopSC> getDatosAdquirienteCopSC() {
        if (datosAdquirienteCopSC == null) {
            datosAdquirienteCopSC = new ArrayList<DatosAdquirienteCopSC>();
        }
        return this.datosAdquirienteCopSC;
    }

}
