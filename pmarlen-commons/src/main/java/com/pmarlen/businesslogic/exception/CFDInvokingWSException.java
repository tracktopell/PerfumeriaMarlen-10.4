package com.pmarlen.businesslogic.exception;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.ws.WebFault;

/**
 *
 * @author alfred
 */
@WebFault(name="CFDInvokingWSException")
@XmlAccessorType(XmlAccessType.FIELD)
public class CFDInvokingWSException extends Exception {
    
    //private int errorType;
    
    /**
     * Creates a new instance of <code>AuthenticationException</code> without detail message.
     */
    public CFDInvokingWSException() {
    }

    /**
     * Constructs an instance of <code>AuthenticationException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public CFDInvokingWSException(String msg) {
        super(msg);
    }
}
