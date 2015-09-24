/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.caja.control;

import javax.swing.JComponent;

/**
 *
 * @author tracktopell
 */
class ValidatioException extends Exception {
	JComponent source;

	public ValidatioException(String message,JComponent source) {
		super(message);
		this.source = source;
	}
	
	public JComponent getSource() {
		return source;
	}

}
