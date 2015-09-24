/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.caja.view;

import javax.swing.JFrame;

/**
 *
 * @author tracktopell
 */
public class DialogConfiguracionBTImpresora extends javax.swing.JDialog {

	
	/**
	 * Creates new form DialogConfiguracionBTImpresora
	 */
	public DialogConfiguracionBTImpresora(JFrame parent) {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        listaDispositivos = new javax.swing.JList();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btaddressActual = new javax.swing.JTextField();
        probarImpresoraBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        explorar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        aceptar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Impresora Bluetooth");
        getContentPane().setLayout(new java.awt.BorderLayout(10, 10));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dispositivos Bluetooth (IMPRESORAS) al alcance :"));
        jScrollPane1.setViewportView(listaDispositivos);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setText("Dispositivo actual :");
        jPanel2.add(jLabel1);

        btaddressActual.setEditable(false);
        btaddressActual.setColumns(15);
        jPanel2.add(btaddressActual);

        probarImpresoraBtn.setText("-> enviar prueba de impresión");
        jPanel2.add(probarImpresoraBtn);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        explorar.setText("Explorar");
        jPanel4.add(explorar);

        jPanel3.add(jPanel4, java.awt.BorderLayout.WEST);

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 20, 5));

        aceptar.setText("Asignar seleccionado");
        jPanel1.add(aceptar);

        cancelar.setText("Cancelar");
        jPanel1.add(cancelar);

        jPanel3.add(jPanel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        setSize(new java.awt.Dimension(509, 244));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptar;
    private javax.swing.JTextField btaddressActual;
    private javax.swing.JButton cancelar;
    private javax.swing.JButton explorar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList listaDispositivos;
    private javax.swing.JButton probarImpresoraBtn;
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
	 * @return the listaDispositivos
	 */
	public javax.swing.JList getListaDispositivos() {
		return listaDispositivos;
	}

	/**
	 * @return the probarImpresoraBtn
	 */
	public javax.swing.JButton getProbarImpresoraBtn() {
		return probarImpresoraBtn;
	}

	/**
	 * @return the btaddressActual
	 */
	public javax.swing.JTextField getBtaddressActual() {
		return btaddressActual;
	}

	/**
	 * @return the explorar
	 */
	public javax.swing.JButton getExplorar() {
		return explorar;
	}
}
