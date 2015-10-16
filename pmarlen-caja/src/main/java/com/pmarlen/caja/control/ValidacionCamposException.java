package com.pmarlen.caja.control;

import javax.swing.JComponent;

/**
 *
 * @author alfredo
 */
class ValidacionCamposException extends Exception{
	private JComponent source;
	
	public ValidacionCamposException(String message,JComponent source) {
		super(message);
		this.source = source;
	}

	/**
	 * @return the source
	 */
	public JComponent getSource() {
		return source;
	}	
}
