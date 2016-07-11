
package com.pmarlen.digifactws20160707.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para SectorFinanciero complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="SectorFinanciero">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IncluirComplemento" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="NumeroFideiComiso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NombreFideiComiso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DescripcionFideiComiso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SectorFinanciero", propOrder = {
    "incluirComplemento",
    "numeroFideiComiso",
    "nombreFideiComiso",
    "descripcionFideiComiso"
})
public class SectorFinanciero {

    @XmlElement(name = "IncluirComplemento")
    protected boolean incluirComplemento;
    @XmlElement(name = "NumeroFideiComiso")
    protected String numeroFideiComiso;
    @XmlElement(name = "NombreFideiComiso")
    protected String nombreFideiComiso;
    @XmlElement(name = "DescripcionFideiComiso")
    protected String descripcionFideiComiso;

    /**
     * Obtiene el valor de la propiedad incluirComplemento.
     * 
     */
    public boolean isIncluirComplemento() {
        return incluirComplemento;
    }

    /**
     * Define el valor de la propiedad incluirComplemento.
     * 
     */
    public void setIncluirComplemento(boolean value) {
        this.incluirComplemento = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroFideiComiso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroFideiComiso() {
        return numeroFideiComiso;
    }

    /**
     * Define el valor de la propiedad numeroFideiComiso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroFideiComiso(String value) {
        this.numeroFideiComiso = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreFideiComiso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreFideiComiso() {
        return nombreFideiComiso;
    }

    /**
     * Define el valor de la propiedad nombreFideiComiso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreFideiComiso(String value) {
        this.nombreFideiComiso = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcionFideiComiso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionFideiComiso() {
        return descripcionFideiComiso;
    }

    /**
     * Define el valor de la propiedad descripcionFideiComiso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionFideiComiso(String value) {
        this.descripcionFideiComiso = value;
    }

}
