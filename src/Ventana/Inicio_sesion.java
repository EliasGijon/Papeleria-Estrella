/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventana;

import java.sql.Connection;
import javax.swing.JOptionPane;
import papeleria_estrella.Conectar;

/**
 *
 * @author felix
 */
public class Inicio_sesion extends javax.swing.JFrame {

    /**
     * Creates new form Inicio_sesion
     */
    public Inicio_sesion() {
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

        pPrincipal = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnIniciar_sesion = new javax.swing.JButton();
        pDatos = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pPrincipal.setLayout(new java.awt.BorderLayout());

        lblTitulo.setText("                                                       Inicio de sesión");
        pPrincipal.add(lblTitulo, java.awt.BorderLayout.PAGE_START);

        btnIniciar_sesion.setText("Iniciar sesion");
        btnIniciar_sesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciar_sesionActionPerformed(evt);
            }
        });
        pPrincipal.add(btnIniciar_sesion, java.awt.BorderLayout.PAGE_END);

        lblUsuario.setText("Usuario");

        lblPassword.setText("Password");

        javax.swing.GroupLayout pDatosLayout = new javax.swing.GroupLayout(pDatos);
        pDatos.setLayout(pDatosLayout);
        pDatosLayout.setHorizontalGroup(
            pDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDatosLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(pDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pDatosLayout.createSequentialGroup()
                        .addComponent(lblUsuario)
                        .addGap(18, 18, 18)
                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pDatosLayout.createSequentialGroup()
                        .addComponent(lblPassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        pDatosLayout.setVerticalGroup(
            pDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDatosLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(pDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsuario))
                .addGap(18, 18, 18)
                .addGroup(pDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pPrincipal.add(pDatos, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciar_sesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciar_sesionActionPerformed
        // TODO add your handling code here:
        Conectar conectar = new Conectar();
        
        usuario = txtUsuario.getText();
        password = txtPassword.getText();
        
        Connection con;
        
        con = conectar.getConection(usuario, password);
        
        
        if (con!=null){
            JOptionPane.showMessageDialog(null, "Conexion exitosa"); 
            dispose();
            Papeleria ventana = new Papeleria(con);
        }
        
        
    }//GEN-LAST:event_btnIniciar_sesionActionPerformed

    /**
     * @param args the command line arguments
     */

    private String usuario = "";
    private String password = "";
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciar_sesion;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel pDatos;
    private javax.swing.JPanel pPrincipal;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
