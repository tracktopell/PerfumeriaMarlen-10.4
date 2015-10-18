/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.caja.view;

import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author alfredo
 */
public class AperturaCajaJFrame extends javax.swing.JDialog {

	/**
	 * Creates new form CierreCajaJFrame
	 */
	public AperturaCajaJFrame(final Frame parent) {
		super(parent, true);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        saldoInicial = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        observaciones = new javax.swing.JTextArea();
        jPanel10 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        aceptar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ABRIR SESIÓN DE CAJA");

        jPanel1.setLayout(new java.awt.GridLayout(4, 1));
        jPanel1.add(jPanel11);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("SALDO INICIAL :");
        jLabel2.setPreferredSize(new java.awt.Dimension(300, 25));
        jPanel2.add(jLabel2);

        saldoInicial.setColumns(6);
        saldoInicial.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel2.add(saldoInicial);

        jPanel1.add(jPanel2);

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("OBSERVACIONES AL ABRIR :");
        jLabel6.setPreferredSize(new java.awt.Dimension(300, 25));
        jPanel6.add(jLabel6);

        observaciones.setColumns(30);
        observaciones.setRows(3);
        jScrollPane1.setViewportView(observaciones);

        jPanel6.add(jScrollPane1);

        jPanel1.add(jPanel6);
        jPanel1.add(jPanel10);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 100, 20));

        aceptar.setText("ACEPTAR");
        jPanel9.add(aceptar);

        cancelar.setText("CANCELAR");
        jPanel9.add(cancelar);

        getContentPane().add(jPanel9, java.awt.BorderLayout.SOUTH);

        setSize(new java.awt.Dimension(767, 364));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptar;
    private javax.swing.JButton cancelar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea observaciones;
    private javax.swing.JTextField saldoInicial;
    // End of variables declaration//GEN-END:variables

	public JButton getAceptar() {
		return aceptar;
	}

	public JButton getCancelar() {
		return cancelar;
	}

	public JTextField getSaldoInicial() {
		return saldoInicial;
	}

	public JTextArea getObservaciones() {
		return observaciones;
	}

}
