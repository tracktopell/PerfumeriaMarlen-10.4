/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.caja.view;

import javax.swing.JFrame;

/**
 *
 * @author Softtek
 */
public class DialogConfiguracionPassword extends javax.swing.JDialog {

	/**
	 * Creates new form DialogConfiguracionBTImpresora
	 */
	public DialogConfiguracionPassword(JFrame parent) {
		super(parent,true);
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        aceptar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        actual = new javax.swing.JPasswordField();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nuevo = new javax.swing.JPasswordField();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        repetir = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Impresora Bluetooth");
        getContentPane().setLayout(new java.awt.BorderLayout(10, 10));

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        aceptar.setText("Guardar");
        jPanel1.add(aceptar);

        cancelar.setText("Cancelar");
        jPanel1.add(cancelar);

        jPanel3.add(jPanel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        jPanel5.setLayout(new java.awt.GridLayout(3, 0));

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Contraseña actual :");
        jLabel1.setPreferredSize(new java.awt.Dimension(200, 19));
        jPanel2.add(jLabel1);

        actual.setColumns(10);
        jPanel2.add(actual);

        jPanel5.add(jPanel2);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Nueva Contraseña :");
        jLabel2.setPreferredSize(new java.awt.Dimension(200, 19));
        jPanel4.add(jLabel2);

        nuevo.setColumns(10);
        jPanel4.add(nuevo);

        jPanel5.add(jPanel4);

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Repetir Contraseña :");
        jLabel3.setPreferredSize(new java.awt.Dimension(200, 19));
        jPanel6.add(jLabel3);

        repetir.setColumns(10);
        jPanel6.add(repetir);

        jPanel5.add(jPanel6);

        getContentPane().add(jPanel5, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(392, 198));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptar;
    private javax.swing.JPasswordField actual;
    private javax.swing.JButton cancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPasswordField nuevo;
    private javax.swing.JPasswordField repetir;
    // End of variables declaration//GEN-END:variables

	/**
	 * @return the aceptar
	 */
	public javax.swing.JButton getAceptar() {
		return aceptar;
	}

	/**
	 * @return the cancelar
	 */
	public javax.swing.JButton getCancelar() {
		return cancelar;
	}

	/**
	 * @return the actual
	 */
	public javax.swing.JPasswordField getActual() {
		return actual;
	}

	/**
	 * @return the nuevo
	 */
	public javax.swing.JPasswordField getNuevo() {
		return nuevo;
	}

	/**
	 * @return the repetir
	 */
	public javax.swing.JPasswordField getRepetir() {
		return repetir;
	}

	
}
