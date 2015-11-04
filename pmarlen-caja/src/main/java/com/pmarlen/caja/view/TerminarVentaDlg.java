/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pmarlen.caja.view;

import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author alfredo
 */
public class TerminarVentaDlg extends javax.swing.JDialog {

	/**
	 * Creates new form TerminarVentaDlg
	 */
	public TerminarVentaDlg(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
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
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cliente = new javax.swing.JComboBox();
        jPanel10 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        metodoDePago = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        subtotal = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        descuento = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        recibido = new javax.swing.JTextField();
        cambioLbl = new javax.swing.JLabel();
        cambio = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        cargo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        autorizacion = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        aceptar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("TERMINAR VENTA");

        jPanel1.setLayout(new java.awt.GridLayout(1, 1));

        jPanel6.setLayout(new java.awt.GridLayout(7, 1));

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("CLIENTE :");
        jLabel4.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel8.add(jLabel4);
        jPanel8.add(cliente);

        jPanel6.add(jPanel8);

        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("MÉTODO DE PAGO :");
        jLabel6.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel10.add(jLabel6);
        jPanel10.add(metodoDePago);

        jPanel6.add(jPanel10);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("SUBTOTAL :");
        jLabel1.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel3.add(jLabel1);

        subtotal.setEditable(false);
        subtotal.setColumns(5);
        jPanel3.add(subtotal);

        jPanel6.add(jPanel3);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("DESCUENTO :");
        jLabel2.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel4.add(jLabel2);

        descuento.setEditable(false);
        descuento.setColumns(5);
        jPanel4.add(descuento);

        jPanel6.add(jPanel4);

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("TOTAL :");
        jLabel3.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel5.add(jLabel3);

        total.setEditable(false);
        total.setColumns(5);
        jPanel5.add(total);

        jPanel6.add(jPanel5);

        jPanel14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("RECIBIDO :");
        jLabel8.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel14.add(jLabel8);

        recibido.setColumns(5);
        jPanel14.add(recibido);

        cambioLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cambioLbl.setText("CAMBIO :");
        cambioLbl.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel14.add(cambioLbl);

        cambio.setEditable(false);
        cambio.setColumns(5);
        jPanel14.add(cambio);

        jPanel6.add(jPanel14);

        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("CARGO A TARJETA :");
        jLabel7.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel12.add(jLabel7);

        cargo.setColumns(5);
        jPanel12.add(cargo);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("NO. AUTORIZACIÓN :");
        jLabel9.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel12.add(jLabel9);

        autorizacion.setColumns(8);
        jPanel12.add(autorizacion);

        jPanel6.add(jPanel12);

        jPanel1.add(jPanel6);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 50, 5));

        aceptar.setText("ACEPTAR");
        jPanel2.add(aceptar);

        cancelar.setText("CANCELAR");
        jPanel2.add(cancelar);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        setSize(new java.awt.Dimension(909, 362));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(TerminarVentaDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(TerminarVentaDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(TerminarVentaDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(TerminarVentaDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
        //</editor-fold>

		/* Create and display the dialog */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				TerminarVentaDlg dialog = new TerminarVentaDlg(new javax.swing.JFrame(), true);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptar;
    private javax.swing.JTextField autorizacion;
    private javax.swing.JTextField cambio;
    private javax.swing.JLabel cambioLbl;
    private javax.swing.JButton cancelar;
    private javax.swing.JTextField cargo;
    private javax.swing.JComboBox cliente;
    private javax.swing.JTextField descuento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JComboBox metodoDePago;
    private javax.swing.JTextField recibido;
    private javax.swing.JTextField subtotal;
    private javax.swing.JTextField total;
    // End of variables declaration//GEN-END:variables

	/**
	 * @return the cambio
	 */
	public javax.swing.JTextField getCambio() {
		return cambio;
	}

	/**
	 * @return the cliente
	 */
	public javax.swing.JComboBox getCliente() {
		return cliente;
	}

	/**
	 * @return the descuento
	 */
	public javax.swing.JTextField getDescuento() {
		return descuento;
	}


	/**
	 * @return the metodoDePago
	 */
	public javax.swing.JComboBox getMetodoDePago() {
		return metodoDePago;
	}

	/**
	 * @return the recibido
	 */
	public javax.swing.JTextField getRecibido() {
		return recibido;
	}

	/**
	 * @return the subtotal
	 */
	public javax.swing.JTextField getSubtotal() {
		return subtotal;
	}

	/**
	 * @return the total
	 */
	public javax.swing.JTextField getTotal() {
		return total;
	}

	public JButton getAceptar() {
		return aceptar;
	}

	public JButton getCancelar() {
		return cancelar;
	}

	public JTextField getCargo() {
		return cargo;
	}

	public JTextField getAutorizacion() {
		return autorizacion;
	}
	
}
