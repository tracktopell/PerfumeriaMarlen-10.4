
package com.pmarlen.digifactws20160707.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Empleado complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Empleado">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RFC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NombreEmpleado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Calle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumExt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumInt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Colonia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Municipio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Referencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ciudad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Pais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Email1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Email2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Telefono1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Telefono2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumEmpleado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CURP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoRegimen" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="NumSeguroSocial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Departamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CLABE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Banco" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="FechaInicioRelLaboral" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Puesto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoContrato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoJornada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PeriodicidadPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SalarioBaseCorApor" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="RiesgoPuesto" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SalarioDiarioIntegrado" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Empleado", propOrder = {
    "rfc",
    "nombreEmpleado",
    "calle",
    "numExt",
    "numInt",
    "colonia",
    "municipio",
    "referencia",
    "ciudad",
    "cp",
    "estado",
    "pais",
    "email1",
    "email2",
    "telefono1",
    "telefono2",
    "numEmpleado",
    "curp",
    "tipoRegimen",
    "numSeguroSocial",
    "departamento",
    "clabe",
    "banco",
    "fechaInicioRelLaboral",
    "puesto",
    "tipoContrato",
    "tipoJornada",
    "periodicidadPago",
    "salarioBaseCorApor",
    "riesgoPuesto",
    "salarioDiarioIntegrado"
})
public class Empleado {

    @XmlElement(name = "RFC")
    protected String rfc;
    @XmlElement(name = "NombreEmpleado")
    protected String nombreEmpleado;
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
    @XmlElement(name = "Pais")
    protected String pais;
    @XmlElement(name = "Email1")
    protected String email1;
    @XmlElement(name = "Email2")
    protected String email2;
    @XmlElement(name = "Telefono1")
    protected String telefono1;
    @XmlElement(name = "Telefono2")
    protected String telefono2;
    @XmlElement(name = "NumEmpleado")
    protected String numEmpleado;
    @XmlElement(name = "CURP")
    protected String curp;
    @XmlElement(name = "TipoRegimen")
    protected int tipoRegimen;
    @XmlElement(name = "NumSeguroSocial")
    protected String numSeguroSocial;
    @XmlElement(name = "Departamento")
    protected String departamento;
    @XmlElement(name = "CLABE")
    protected String clabe;
    @XmlElement(name = "Banco")
    protected int banco;
    @XmlElement(name = "FechaInicioRelLaboral")
    protected String fechaInicioRelLaboral;
    @XmlElement(name = "Puesto")
    protected String puesto;
    @XmlElement(name = "TipoContrato")
    protected String tipoContrato;
    @XmlElement(name = "TipoJornada")
    protected String tipoJornada;
    @XmlElement(name = "PeriodicidadPago")
    protected String periodicidadPago;
    @XmlElement(name = "SalarioBaseCorApor")
    protected double salarioBaseCorApor;
    @XmlElement(name = "RiesgoPuesto")
    protected int riesgoPuesto;
    @XmlElement(name = "SalarioDiarioIntegrado")
    protected double salarioDiarioIntegrado;

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
     * Obtiene el valor de la propiedad nombreEmpleado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    /**
     * Define el valor de la propiedad nombreEmpleado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreEmpleado(String value) {
        this.nombreEmpleado = value;
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
     * Obtiene el valor de la propiedad email1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail1() {
        return email1;
    }

    /**
     * Define el valor de la propiedad email1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail1(String value) {
        this.email1 = value;
    }

    /**
     * Obtiene el valor de la propiedad email2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail2() {
        return email2;
    }

    /**
     * Define el valor de la propiedad email2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail2(String value) {
        this.email2 = value;
    }

    /**
     * Obtiene el valor de la propiedad telefono1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefono1() {
        return telefono1;
    }

    /**
     * Define el valor de la propiedad telefono1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefono1(String value) {
        this.telefono1 = value;
    }

    /**
     * Obtiene el valor de la propiedad telefono2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefono2() {
        return telefono2;
    }

    /**
     * Define el valor de la propiedad telefono2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefono2(String value) {
        this.telefono2 = value;
    }

    /**
     * Obtiene el valor de la propiedad numEmpleado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumEmpleado() {
        return numEmpleado;
    }

    /**
     * Define el valor de la propiedad numEmpleado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumEmpleado(String value) {
        this.numEmpleado = value;
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

    /**
     * Obtiene el valor de la propiedad tipoRegimen.
     * 
     */
    public int getTipoRegimen() {
        return tipoRegimen;
    }

    /**
     * Define el valor de la propiedad tipoRegimen.
     * 
     */
    public void setTipoRegimen(int value) {
        this.tipoRegimen = value;
    }

    /**
     * Obtiene el valor de la propiedad numSeguroSocial.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumSeguroSocial() {
        return numSeguroSocial;
    }

    /**
     * Define el valor de la propiedad numSeguroSocial.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumSeguroSocial(String value) {
        this.numSeguroSocial = value;
    }

    /**
     * Obtiene el valor de la propiedad departamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Define el valor de la propiedad departamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartamento(String value) {
        this.departamento = value;
    }

    /**
     * Obtiene el valor de la propiedad clabe.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCLABE() {
        return clabe;
    }

    /**
     * Define el valor de la propiedad clabe.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCLABE(String value) {
        this.clabe = value;
    }

    /**
     * Obtiene el valor de la propiedad banco.
     * 
     */
    public int getBanco() {
        return banco;
    }

    /**
     * Define el valor de la propiedad banco.
     * 
     */
    public void setBanco(int value) {
        this.banco = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaInicioRelLaboral.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaInicioRelLaboral() {
        return fechaInicioRelLaboral;
    }

    /**
     * Define el valor de la propiedad fechaInicioRelLaboral.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaInicioRelLaboral(String value) {
        this.fechaInicioRelLaboral = value;
    }

    /**
     * Obtiene el valor de la propiedad puesto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPuesto() {
        return puesto;
    }

    /**
     * Define el valor de la propiedad puesto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPuesto(String value) {
        this.puesto = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoContrato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoContrato() {
        return tipoContrato;
    }

    /**
     * Define el valor de la propiedad tipoContrato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoContrato(String value) {
        this.tipoContrato = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoJornada.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoJornada() {
        return tipoJornada;
    }

    /**
     * Define el valor de la propiedad tipoJornada.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoJornada(String value) {
        this.tipoJornada = value;
    }

    /**
     * Obtiene el valor de la propiedad periodicidadPago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriodicidadPago() {
        return periodicidadPago;
    }

    /**
     * Define el valor de la propiedad periodicidadPago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriodicidadPago(String value) {
        this.periodicidadPago = value;
    }

    /**
     * Obtiene el valor de la propiedad salarioBaseCorApor.
     * 
     */
    public double getSalarioBaseCorApor() {
        return salarioBaseCorApor;
    }

    /**
     * Define el valor de la propiedad salarioBaseCorApor.
     * 
     */
    public void setSalarioBaseCorApor(double value) {
        this.salarioBaseCorApor = value;
    }

    /**
     * Obtiene el valor de la propiedad riesgoPuesto.
     * 
     */
    public int getRiesgoPuesto() {
        return riesgoPuesto;
    }

    /**
     * Define el valor de la propiedad riesgoPuesto.
     * 
     */
    public void setRiesgoPuesto(int value) {
        this.riesgoPuesto = value;
    }

    /**
     * Obtiene el valor de la propiedad salarioDiarioIntegrado.
     * 
     */
    public double getSalarioDiarioIntegrado() {
        return salarioDiarioIntegrado;
    }

    /**
     * Define el valor de la propiedad salarioDiarioIntegrado.
     * 
     */
    public void setSalarioDiarioIntegrado(double value) {
        this.salarioDiarioIntegrado = value;
    }

}
