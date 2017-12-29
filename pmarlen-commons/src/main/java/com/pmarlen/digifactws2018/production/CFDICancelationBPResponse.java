
package com.pmarlen.digifactws2018.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para CFDICancelationBPResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CFDICancelationBPResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Correct" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ErrorDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RFC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CancelationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="CancelationUUID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CancelationResponse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "CFDICancelationBPResponse", propOrder = {
    "correct",
    "errorDescription",
    "rfc",
    "cancelationDate",
    "cancelationUUID",
    "cancelationResponse",
    "docNum",
    "refType",
    "fiscalYear",
    "cmpnyCode"
})
public class CFDICancelationBPResponse {

    @XmlElement(name = "Correct")
    protected boolean correct;
    @XmlElement(name = "ErrorDescription")
    protected String errorDescription;
    @XmlElement(name = "RFC")
    protected String rfc;
    @XmlElement(name = "CancelationDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar cancelationDate;
    @XmlElement(name = "CancelationUUID")
    protected String cancelationUUID;
    @XmlElement(name = "CancelationResponse")
    protected String cancelationResponse;
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
     * Obtiene el valor de la propiedad cancelationDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCancelationDate() {
        return cancelationDate;
    }

    /**
     * Define el valor de la propiedad cancelationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCancelationDate(XMLGregorianCalendar value) {
        this.cancelationDate = value;
    }

    /**
     * Obtiene el valor de la propiedad cancelationUUID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCancelationUUID() {
        return cancelationUUID;
    }

    /**
     * Define el valor de la propiedad cancelationUUID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCancelationUUID(String value) {
        this.cancelationUUID = value;
    }

    /**
     * Obtiene el valor de la propiedad cancelationResponse.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCancelationResponse() {
        return cancelationResponse;
    }

    /**
     * Define el valor de la propiedad cancelationResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCancelationResponse(String value) {
        this.cancelationResponse = value;
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
