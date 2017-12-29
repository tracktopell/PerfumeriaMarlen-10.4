
package com.pmarlen.digifactws2018.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para CFDIBPResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CFDIBPResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Correct" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ErrorDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UUID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StampDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="SATCertificateNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CFDISignature" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SATSignature" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DocNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RefType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FiscalYear" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CmpnyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CFDIBPResponse", propOrder = {
    "correct",
    "errorDescription",
    "version",
    "uuid",
    "stampDate",
    "satCertificateNumber",
    "cfdiSignature",
    "satSignature",
    "docNum",
    "refType",
    "fiscalYear",
    "cmpnyCode"
})
public class CFDIBPResponse {

    @XmlElement(name = "Correct")
    protected boolean correct;
    @XmlElement(name = "ErrorDescription")
    protected String errorDescription;
    @XmlElement(name = "Version")
    protected String version;
    @XmlElement(name = "UUID")
    protected String uuid;
    @XmlElement(name = "StampDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar stampDate;
    @XmlElement(name = "SATCertificateNumber")
    protected String satCertificateNumber;
    @XmlElement(name = "CFDISignature")
    protected String cfdiSignature;
    @XmlElement(name = "SATSignature")
    protected String satSignature;
    @XmlElement(name = "DocNum")
    protected String docNum;
    @XmlElement(name = "RefType")
    protected String refType;
    @XmlElement(name = "FiscalYear")
    protected String fiscalYear;
    @XmlElement(name = "CmpnyCode")
    protected String cmpnyCode;

    /**
     * Obtiene el valor de la propiedad correct.
     * 
     */
    public boolean isCorrect() {
        return correct;
    }

    /**
     * Define el valor de la propiedad correct.
     * 
     */
    public void setCorrect(boolean value) {
        this.correct = value;
    }

    /**
     * Obtiene el valor de la propiedad errorDescription.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorDescription() {
        return errorDescription;
    }

    /**
     * Define el valor de la propiedad errorDescription.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorDescription(String value) {
        this.errorDescription = value;
    }

    /**
     * Obtiene el valor de la propiedad version.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Define el valor de la propiedad version.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Obtiene el valor de la propiedad uuid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUUID() {
        return uuid;
    }

    /**
     * Define el valor de la propiedad uuid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUUID(String value) {
        this.uuid = value;
    }

    /**
     * Obtiene el valor de la propiedad stampDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStampDate() {
        return stampDate;
    }

    /**
     * Define el valor de la propiedad stampDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStampDate(XMLGregorianCalendar value) {
        this.stampDate = value;
    }

    /**
     * Obtiene el valor de la propiedad satCertificateNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSATCertificateNumber() {
        return satCertificateNumber;
    }

    /**
     * Define el valor de la propiedad satCertificateNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSATCertificateNumber(String value) {
        this.satCertificateNumber = value;
    }

    /**
     * Obtiene el valor de la propiedad cfdiSignature.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCFDISignature() {
        return cfdiSignature;
    }

    /**
     * Define el valor de la propiedad cfdiSignature.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCFDISignature(String value) {
        this.cfdiSignature = value;
    }

    /**
     * Obtiene el valor de la propiedad satSignature.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSATSignature() {
        return satSignature;
    }

    /**
     * Define el valor de la propiedad satSignature.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSATSignature(String value) {
        this.satSignature = value;
    }

    /**
     * Obtiene el valor de la propiedad docNum.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocNum() {
        return docNum;
    }

    /**
     * Define el valor de la propiedad docNum.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocNum(String value) {
        this.docNum = value;
    }

    /**
     * Obtiene el valor de la propiedad refType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefType() {
        return refType;
    }

    /**
     * Define el valor de la propiedad refType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefType(String value) {
        this.refType = value;
    }

    /**
     * Obtiene el valor de la propiedad fiscalYear.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFiscalYear() {
        return fiscalYear;
    }

    /**
     * Define el valor de la propiedad fiscalYear.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFiscalYear(String value) {
        this.fiscalYear = value;
    }

    /**
     * Obtiene el valor de la propiedad cmpnyCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCmpnyCode() {
        return cmpnyCode;
    }

    /**
     * Define el valor de la propiedad cmpnyCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCmpnyCode(String value) {
        this.cmpnyCode = value;
    }

}
