
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para EmisorComercioExteriorTimbrado complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="EmisorComercioExteriorTimbrado">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RFC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RazonSocial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RegimenFiscal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Calle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumExt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumInt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Colonia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Municipio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Referencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ciudad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Localidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Pais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="s_Calle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="s_NumExt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="s_NumInt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="s_Colonia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="s_Ciudad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="s_Delegacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="s_Estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="s_Pais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="s_CP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CURP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmisorComercioExteriorTimbrado", propOrder = {
    "rfc",
    "razonSocial",
    "regimenFiscal",
    "calle",
    "numExt",
    "numInt",
    "colonia",
    "municipio",
    "referencia",
    "ciudad",
    "cp",
    "estado",
    "localidad",
    "pais",
    "sCalle",
    "sNumExt",
    "sNumInt",
    "sColonia",
    "sCiudad",
    "sDelegacion",
    "sEstado",
    "sPais",
    "scp",
    "curp"
})
public class EmisorComercioExteriorTimbrado {

    @XmlElement(name = "RFC")
    protected String rfc;
    @XmlElement(name = "RazonSocial")
    protected String razonSocial;
    @XmlElement(name = "RegimenFiscal")
    protected String regimenFiscal;
    @XmlElement(name = "Calle")
    protected String calle;
    @XmlElement(name = "NumExt")
    protected String numExt;
    @XmlElement(name = "NumInt")
    protected String numInt;
    @XmlElement(name = "Colonia")
    protected String colonia;
    @XmlElement(name = "Municipio")
    protected String municipio;
    @XmlElement(name = "Referencia")
    protected String referencia;
    @XmlElement(name = "Ciudad")
    protected String ciudad;
    @XmlElement(name = "CP")
    protected String cp;
    @XmlElement(name = "Estado")
    protected String estado;
    @XmlElement(name = "Localidad")
    protected String localidad;
    @XmlElement(name = "Pais")
    protected String pais;
    @XmlElement(name = "s_Calle")
    protected String sCalle;
    @XmlElement(name = "s_NumExt")
    protected String sNumExt;
    @XmlElement(name = "s_NumInt")
    protected String sNumInt;
    @XmlElement(name = "s_Colonia")
    protected String sColonia;
    @XmlElement(name = "s_Ciudad")
    protected String sCiudad;
    @XmlElement(name = "s_Delegacion")
    protected String sDelegacion;
    @XmlElement(name = "s_Estado")
    protected String sEstado;
    @XmlElement(name = "s_Pais")
    protected String sPais;
    @XmlElement(name = "s_CP")
    protected String scp;
    @XmlElement(name = "CURP")
    protected String curp;

    /**
     * Obtiene el valor de la propiedad rfc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRFC() {
        return rfc;
    }

    /**
     * Define el valor de la propiedad rfc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRFC(String value) {
        this.rfc = value;
    }

    /**
     * Obtiene el valor de la propiedad razonSocial.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * Define el valor de la propiedad razonSocial.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRazonSocial(String value) {
        this.razonSocial = value;
    }

    /**
     * Obtiene el valor de la propiedad regimenFiscal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegimenFiscal() {
        return regimenFiscal;
    }

    /**
     * Define el valor de la propiedad regimenFiscal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegimenFiscal(String value) {
        this.regimenFiscal = value;
    }

    /**
     * Obtiene el valor de la propiedad calle.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Define el valor de la propiedad calle.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalle(String value) {
        this.calle = value;
    }

    /**
     * Obtiene el valor de la propiedad numExt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumExt() {
        return numExt;
    }

    /**
     * Define el valor de la propiedad numExt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumExt(String value) {
        this.numExt = value;
    }

    /**
     * Obtiene el valor de la propiedad numInt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumInt() {
        return numInt;
    }

    /**
     * Define el valor de la propiedad numInt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumInt(String value) {
        this.numInt = value;
    }

    /**
     * Obtiene el valor de la propiedad colonia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * Define el valor de la propiedad colonia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColonia(String value) {
        this.colonia = value;
    }

    /**
     * Obtiene el valor de la propiedad municipio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * Define el valor de la propiedad municipio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMunicipio(String value) {
        this.municipio = value;
    }

    /**
     * Obtiene el valor de la propiedad referencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * Define el valor de la propiedad referencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferencia(String value) {
        this.referencia = value;
    }

    /**
     * Obtiene el valor de la propiedad ciudad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Define el valor de la propiedad ciudad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCiudad(String value) {
        this.ciudad = value;
    }

    /**
     * Obtiene el valor de la propiedad cp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCP() {
        return cp;
    }

    /**
     * Define el valor de la propiedad cp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCP(String value) {
        this.cp = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad localidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * Define el valor de la propiedad localidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalidad(String value) {
        this.localidad = value;
    }

    /**
     * Obtiene el valor de la propiedad pais.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPais() {
        return pais;
    }

    /**
     * Define el valor de la propiedad pais.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPais(String value) {
        this.pais = value;
    }

    /**
     * Obtiene el valor de la propiedad sCalle.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSCalle() {
        return sCalle;
    }

    /**
     * Define el valor de la propiedad sCalle.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSCalle(String value) {
        this.sCalle = value;
    }

    /**
     * Obtiene el valor de la propiedad sNumExt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSNumExt() {
        return sNumExt;
    }

    /**
     * Define el valor de la propiedad sNumExt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSNumExt(String value) {
        this.sNumExt = value;
    }

    /**
     * Obtiene el valor de la propiedad sNumInt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSNumInt() {
        return sNumInt;
    }

    /**
     * Define el valor de la propiedad sNumInt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSNumInt(String value) {
        this.sNumInt = value;
    }

    /**
     * Obtiene el valor de la propiedad sColonia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSColonia() {
        return sColonia;
    }

    /**
     * Define el valor de la propiedad sColonia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSColonia(String value) {
        this.sColonia = value;
    }

    /**
     * Obtiene el valor de la propiedad sCiudad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSCiudad() {
        return sCiudad;
    }

    /**
     * Define el valor de la propiedad sCiudad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSCiudad(String value) {
        this.sCiudad = value;
    }

    /**
     * Obtiene el valor de la propiedad sDelegacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSDelegacion() {
        return sDelegacion;
    }

    /**
     * Define el valor de la propiedad sDelegacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSDelegacion(String value) {
        this.sDelegacion = value;
    }

    /**
     * Obtiene el valor de la propiedad sEstado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSEstado() {
        return sEstado;
    }

    /**
     * Define el valor de la propiedad sEstado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSEstado(String value) {
        this.sEstado = value;
    }

    /**
     * Obtiene el valor de la propiedad sPais.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSPais() {
        return sPais;
    }

    /**
     * Define el valor de la propiedad sPais.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSPais(String value) {
        this.sPais = value;
    }

    /**
     * Obtiene el valor de la propiedad scp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSCP() {
        return scp;
    }

    /**
     * Define el valor de la propiedad scp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSCP(String value) {
        this.scp = value;
    }

    /**
     * Obtiene el valor de la propiedad curp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCURP() {
        return curp;
    }

    /**
     * Define el valor de la propiedad curp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCURP(String value) {
        this.curp = value;
    }

}
