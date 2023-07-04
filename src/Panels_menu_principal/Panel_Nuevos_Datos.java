/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Panels_menu_principal;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eduardo Elias Hernandez Moreno
 */
public class Panel_Nuevos_Datos{
    JMenuItem menuItem;
    
    public Panel_Nuevos_Datos (String nombre_Panel, Connection con, JMenuItem menuItem) throws SQLException{   
        this.menuItem = menuItem;
        this.con = con;
        nFilas = 1;
        if (nombre_Panel.equals("Nuevo_Producto")){
            pPrincipal = PNuevo_Producto();
        }
        else{
            if (nombre_Panel.equals("Nuevo_Servicio")){
                pPrincipal = PNuevo_Servicio();
            }
            else{
                if (nombre_Panel.equals("Nuevo_Cliente")){
                    pPrincipal = PNuevo_Cliente();
                }
                else {
                    if (nombre_Panel.equals("Nuevo_Proveedor")){
                        pPrincipal = PNuevo_Proveedor();
                    }
                }
            }   
        }
    }
    
    private JPanel PNuevo_Producto (){
        JPanel panel = new JPanel();
        
        lblTitulo = new JLabel();
        pSur = new JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tTabla = new javax.swing.JTable();
        lblEspacio1 = new JLabel();
        lblEspacio2 = new JLabel();
        btnRegistrar = new JButton();
        btnNuevaFila = new JButton();
        
        //labels
        lblTitulo.setText("           Nuevo Producto");
        lblEspacio1.setText("           ");
        lblEspacio2.setText("           ");

        //Consultar numero de ultimo cliente
        
        final String[] vector = {null, null, null};
        final DefaultTableModel newModelo = new DefaultTableModel(
            new Object [][] {
                vector
            },
            new String [] {
                "Codigo_P", "Nombre_P", "Precio_P"
            }
        );
         
        
        final DefaultTableModel dtmTable = newModelo;
        
        //JTable
        tTabla.setModel(dtmTable);
        tTabla.setFocusable(false); 
        jScrollPane1.setViewportView(tTabla);
 

        //Botones
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < nFilas; i++) {
                    String sql = "INSERT INTO producto VALUES('"+ dtmTable.getValueAt(i, 0)+"','"+ dtmTable.getValueAt(i, 1)+"',"+ 0 +","+dtmTable.getValueAt(i, 2)+")";
                    //Verificar datos de tabla
                    
                    try {
                        btnRegistrarActionPerformed(con, sql);
                    } catch (SQLException ex) {
                        Logger.getLogger(Panel_Nuevos_Datos.class.getName()).log(Level.SEVERE, null, ex);
                    }               
                }
                JOptionPane.showMessageDialog(null, "Registro de productos exitoso");
                menuItem.doClick();
            }
        });


        btnNuevaFila.setText("Nueva Fila");
        btnNuevaFila.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnNuevaFilaActionPerformed(e, dtmTable, vector);
            }
        });
        

        //Paneles
        panel.setLayout(new java.awt.BorderLayout());

        panel.add(lblTitulo, java.awt.BorderLayout.NORTH);
        panel.add(lblEspacio1, java.awt.BorderLayout.EAST);
        panel.add(lblEspacio2, java.awt.BorderLayout.WEST);

        panel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        panel.add(pSur, java.awt.BorderLayout.SOUTH);

        /*Panel parte inferior*/
        pSur.setLayout(new java.awt.GridLayout(1, 3));
        pSur.add(btnNuevaFila);
        pSur.add(btnRegistrar);

        return panel;
    }
    
    private JPanel PNuevo_Servicio (){
        JPanel panel = new JPanel();
        
        lblTitulo = new JLabel();
        pSur = new JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tTabla = new javax.swing.JTable();
        lblEspacio1 = new JLabel();
        lblEspacio2 = new JLabel();
        btnRegistrar = new JButton();
        btnNuevaFila = new JButton();

        //labels
        lblTitulo.setText("           Nuevo Servicio");
        lblEspacio1.setText("           ");
        lblEspacio2.setText("           ");

        //Consultar numero de ultimo cliente
        
        final String[] vector = {null, null, null};
        //JTable
        tTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                vector
            },
            new String [] {
                "Codigo_S", "Nombre_S", "Precio_S"
            }
        ));
        tTabla.setFocusable(false);

        jScrollPane1.setViewportView(tTabla);
        
        final javax.swing.table.DefaultTableModel dtmTable = (DefaultTableModel) tTabla.getModel();
        //Botones
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < nFilas; i++) {
                    String sql = "INSERT INTO servicio VALUES('"+ dtmTable.getValueAt(i, 0)+"','"+ dtmTable.getValueAt(i, 1)+"',"+dtmTable.getValueAt(i, 2)+")";
                    //Verificar datos de tabla

                    try {
                        btnRegistrarActionPerformed(con, sql);
                    } catch (SQLException ex) {
                        Logger.getLogger(Panel_Nuevos_Datos.class.getName()).log(Level.SEVERE, null, ex);
                    }               
                }
                JOptionPane.showMessageDialog(null, "Registro de servicios exitoso");
                menuItem.doClick();
            }
        });


        btnNuevaFila.setText("Nueva Fila");
        btnNuevaFila.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnNuevaFilaActionPerformed(e, dtmTable, vector);
            }
        });

        //Paneles
        panel.setLayout(new java.awt.BorderLayout());

        panel.add(lblTitulo, java.awt.BorderLayout.NORTH);
        panel.add(lblEspacio1, java.awt.BorderLayout.EAST);
        panel.add(lblEspacio2, java.awt.BorderLayout.WEST);

        panel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        panel.add(pSur, java.awt.BorderLayout.SOUTH);

        /*Panel parte inferior*/
        pSur.setLayout(new java.awt.GridLayout(1, 3));
        pSur.add(btnNuevaFila);
        pSur.add(btnRegistrar);

        return panel;
    }

    //Panel Nuevo_Cliente
    private JPanel PNuevo_Cliente () throws SQLException{
        JPanel panel = new JPanel();    
        
        lblTitulo = new JLabel();
        pSur = new JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tTabla = new javax.swing.JTable();
        lblEspacio1 = new JLabel();
        lblEspacio2 = new JLabel();
        btnRegistrar = new JButton();
        btnNuevaFila = new JButton();

        //labels
        lblTitulo.setText("           Nuevo Cliente");
        lblEspacio1.setText("           ");
        lblEspacio2.setText("           ");
        
       
        //Consultar numero de ultimo cliente
        final boolean[] editable = {false, true, true};
        final String[] vector = {null, null, null};
        
        //JTable
        tTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
            },
            new String [] {
                "Numero_Cl", "Nombre_Cl", "Telefono_Cl"
            }
        ){
            @Override
            public boolean isCellEditable(int row, int column){
                return editable[column];
            }
        });
        tTabla.setFocusable(false);
        
        jScrollPane1.setViewportView(tTabla);
        
        final javax.swing.table.DefaultTableModel dtmTabla = (DefaultTableModel) tTabla.getModel();
        final int Numero_Cl = consultarU_Numero_Cl(con);
        dtmTabla.setValueAt(Numero_Cl, nFilas-1, 0);
        
        
        
        //Botones
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            //Falta validar datos
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < nFilas; i++){
                    
                    try {
                        String sql = "INSERT INTO cliente VALUES("+dtmTabla.getValueAt(i, 0)+",'"+ dtmTabla.getValueAt(i, 1)+"','"+dtmTabla.getValueAt(i, 2)+"')";
                        //Verificar datos de tabla
                        btnRegistrarActionPerformed(con, sql);
                    } catch (SQLException ex) {
                        Logger.getLogger(Panel_Nuevos_Datos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                JOptionPane.showMessageDialog(null, "Registro de clientes exitoso");
                menuItem.doClick();
            }
        });


        btnNuevaFila.setText("Nueva Fila");
        btnNuevaFila.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnNuevaFilaActionPerformed(e, dtmTabla, vector);
                final int newNumero_Cl = Numero_Cl + nFilas-1;
                dtmTabla.setValueAt(newNumero_Cl, nFilas-1, 0);
            }
        });
        
        //Paneles
        panel.setLayout(new java.awt.BorderLayout());

        panel.add(lblTitulo, java.awt.BorderLayout.NORTH);
        panel.add(lblEspacio1, java.awt.BorderLayout.EAST);
        panel.add(lblEspacio2, java.awt.BorderLayout.WEST);

        panel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        panel.add(pSur, java.awt.BorderLayout.SOUTH);

        /*Panel parte inferior*/
        pSur.setLayout(new java.awt.GridLayout(1, 3));
        pSur.add(btnNuevaFila);
        pSur.add(btnRegistrar);
        
        return panel;
    }
    
    private JPanel PNuevo_Proveedor (){
        JPanel panel = new JPanel();
        
        pPrincipal = new JPanel();
        lblTitulo = new JLabel();
        pSur = new JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tTabla = new javax.swing.JTable();
        lblEspacio1 = new JLabel();
        lblEspacio2 = new JLabel();
        btnRegistrar = new JButton();
        btnNuevaFila = new JButton();

        //labels
        lblTitulo.setText("           Nuevo Proveedor");
        lblEspacio1.setText("           ");
        lblEspacio2.setText("           ");

        //Consultar numero de ultimo cliente

        final String[] vector = {null, null, null, null};
        //JTable
        tTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                vector
            },
            new String [] {
                "Codigo_Pro", "Nombre_Pro", "No_Telefono_Pro", "Direccion_Pro"
            }
        ));
        tTabla.setFocusable(false);

        jScrollPane1.setViewportView(tTabla);
        
        final javax.swing.table.DefaultTableModel dtmTable = (DefaultTableModel) tTabla.getModel();
        //Botones
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < nFilas; i++) {
                    try {
                        String sql = "INSERT INTO proveedor VALUES('"+ dtmTable.getValueAt(i, 0)+"','"+ dtmTable.getValueAt(i, 1)+"','"+dtmTable.getValueAt(i, 2)+"','"+dtmTable.getValueAt(i, 3)+"')";
                        //Verificar datos de tabla
                        btnRegistrarActionPerformed(con, sql);
                    } catch (SQLException ex) {
                        Logger.getLogger(Panel_Nuevos_Datos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                JOptionPane.showMessageDialog(null, "Registro de proveedores exitoso");
                menuItem.doClick();
            }
        });

        btnNuevaFila.setText("Nueva Fila");
        btnNuevaFila.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnNuevaFilaActionPerformed(e, dtmTable, vector);
            }
        });

        //Paneles
        panel.setLayout(new java.awt.BorderLayout());

        panel.add(lblTitulo, java.awt.BorderLayout.NORTH);
        panel.add(lblEspacio1, java.awt.BorderLayout.EAST);
        panel.add(lblEspacio2, java.awt.BorderLayout.WEST);

        panel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        panel.add(pSur, java.awt.BorderLayout.SOUTH);

        /*Panel parte inferior*/
        pSur.setLayout(new java.awt.GridLayout(1, 3));
        pSur.add(btnNuevaFila);
        pSur.add(btnRegistrar);
        
        return panel;
    }
     
    private int consultarU_Numero_Cl (Connection con) throws SQLException{
        String sql = "SELECT * FROM ultimas_claves_secuenciales"; 
        int Numero_Cl = 0;
        
        try (Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                Numero_Cl = rs.getInt("Numero_Cl");
            }
            rs.close();
            st.close();
        }
        
        return Numero_Cl+1;
    }
    
    private void btnNuevaFilaActionPerformed (java.awt.event.ActionEvent evt, javax.swing.table.DefaultTableModel dtmTabla, String[] vector){       
        dtmTabla.addRow(vector);
        nFilas ++;
    }
    
    //Validar datos
    private void btnRegistrarActionPerformed (Connection con, String sql) throws SQLException{
        try (Statement statement = con.createStatement()) {
            statement.executeUpdate(sql);   
        }
        
        
    }   
    
    // Variables declaration - do not modify
    private Connection con;
    private int nFilas;
   
    private JLabel lblEspacio1;
    private JLabel lblEspacio2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tTabla;
    private JLabel lblTitulo;
    public JPanel pPrincipal;
    private JPanel pSur;
    private JButton btnNuevaFila;
    private JButton btnRegistrar;
    // End of variables declaration   
}
