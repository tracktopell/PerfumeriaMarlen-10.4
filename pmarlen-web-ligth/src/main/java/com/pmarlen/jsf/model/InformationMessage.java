package com.pmarlen.jsf.model;

import java.io.Serializable;

/**
 *
 * @author Alfredo Estrada
 */
public class InformationMessage implements Serializable{
    
    private long   id;
    private String message;
    private long   expirationDate;

    public InformationMessage() {
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the expirationDate
     */
    public long getExpirationDate() {
        return expirationDate;
    }

    /**
     * @param expirationDate the expirationDate to set
     */
    public void setExpirationDate(long expirationDate) {
        this.expirationDate = expirationDate;
    }
    
}
