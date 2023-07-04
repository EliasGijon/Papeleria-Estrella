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
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import papeleria_estrella.Conectar;

/**
 *
 * @author Eduardo Elias Hernandez Moreno
 */
public class Panel_Actualizar_Datos{
    
    public Panel_Actualizar_Datos (String nombre_Panel, Connection con){   
        this.con = con;
        nFilas = 1;
        if (nombre_Panel.equals("Actualizar_Precio_P")){
            pPrincipal = PActualizar_Producto();
            btnNuevaFila.doClick();
            btnRegistrar.doClick();
        }
        else{
            if (nombre_Panel.equals("Actualizar_Precio_S")){
                pPrincipal = PActualizar_Servicio();
                btnNuevaFila.doClick();
                btnRegistrar.doClick();
            }
            else{
                if (nombre_Panel.equals("Actualizar_Numero_Tel_Cl")){
                    pPrincipal = PActualizar_Cliente();
                    btnNuevaFila.doClick();
                    btnRegistrar.doClick();
                }
                else {
                    if (nombre_Panel.equals("Actualizar_Proveedor")){
                        pPrincipal = PActualizar_Proveedor();
                        btnNuevaFila.doClick();
                        btnRegistrar.doClick();
                    }
                }
            }   
        }
    }
    
    private JPanel PActualizar_Producto (){
        JPanel panel = new JPanel();
        final boolean[] editable = {false, false, true};
        
        lblTitulo = new JLabel();
        pSur = new JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tTabla = new javax.swing.JTable();
        lblEspacio1 = new JLabel();
        lblEspacio2 = new JLabel();
        btnRegistrar = new JButton();
        btnNuevaFila = new JButton();
        
        //labels
        lblTitulo.setText("           Actualizar precio de Producto");
        lblEspacio1.setText("           ");
        lblEspacio2.setText("           ");

        //Consultar numero de ultimo cliente
        
        final String[] vector = {null, null, null};
        //JTable
        tTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                vector,
            },
            new String [] {
                "Codigo_P", "Nombre_P", "Precio_P"
            }
        ){
            @Override
            public boolean isCellEditable(int row, int column){
                return editable[column];
            }
        });
        
        jScrollPane1.setViewportView(tTabla);

        final javax.swing.table.DefaultTableModel dtmTable = (DefaultTableModel) tTabla.getModel();

        //Botones
        btnRegistrar.setText("Consultar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                ResultSet rs=null;
                Statement sentencia=null;
                String sql = "Select Codigo_P, Nombre_P, Precio_P From producto";
                try {
                    sentencia=(Statement) con.createStatement();
                    rs=sentencia.executeQuery(sql);
                } catch (SQLException ex) { 
                    Logger.getLogger(Panel_Actualizar_Datos.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (int i = 0; i < nFilas; i++) {   
                    try {
                        if(rs.next()&&rs!=null){
                            dtmTable.setValueAt(rs.getString("Codigo_P"), i, 0);
                            dtmTable.setValueAt(rs.getString("Nombre_P"), i, 1);
                            dtmTable.setValueAt(rs.getDouble("Precio_P"), i, 2);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Panel_Actualizar_Datos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                  /* Verificar datos de tabla
                    try {
                        btnRegistrarActionPerformed(e, con, sql);
                    } catch (SQLException ex) {
                        Logger.getLogger(Panel_Consulta_Datos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                  /* Verificar datos de tabla
                    try {
                        btnRegistrarActionPerformed(e, con, sql);
                    } catch (SQLException ex) {
                        Logger.getLogger(Panel_Consulta_Datos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  */             
                }
                
            }
        });

        btnNuevaFila.setText("Nueva Fila");
        btnNuevaFila.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet rs=null;
                Statement sentencia=null;
                String sql = "Select count(*) as filas From producto"; 
                try {
                    sentencia=(Statement) con.createStatement();
                    rs=sentencia.executeQuery(sql);
                    rs.next();
                    int Filas = rs.getInt("Filas");
                    rs.close();
                    for(int i=0;i<Filas;i++){
                        btnNuevaFilaActionPerformed(e, dtmTable, vector); 
                    }
                } catch (SQLException ex) { 
                    Logger.getLogger(Panel_Actualizar_Datos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });


        //Paneles
        panel.setLayout(new java.awt.BorderLayout());

        panel.add(lblTitulo, java.awt.BorderLayout.NORTH);
        panel.add(lblEspacio1, java.awt.BorderLayout.EAST);
        panel.add(lblEspacio2, java.awt.BorderLayout.WEST);

        panel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        //panel.add(pSur, java.awt.BorderLayout.SOUTH);

        /*Panel parte inferior*/
        pSur.setLayout(new java.awt.GridLayout(1, 3));
        pSur.add(btnNuevaFila);
        pSur.add(btnRegistrar);

        return panel;
    }
    
    private JPanel PActualizar_Servicio (){
        JPanel panel = new JPanel();
        final boolean[] editable = {false, false, true};
        
        lblTitulo = new JLabel();
        pSur = new JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tTabla = new javax.swing.JTable();
        lblEspacio1 = new JLabel();
        lblEspacio2 = new JLabel();
        btnRegistrar = new JButton();
        btnNuevaFila = new JButton();
        
        //labels
        lblTitulo.setText("           Actualizar precio de servicio");
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
        ){
            @Override
            public boolean isCellEditable(int row, int column){
                return editable[column];
            }
        });
        
        jScrollPane1.setViewportView(tTabla);
        
        final javax.swing.table.DefaultTableModel dtmTable = (DefaultTableModel) tTabla.getModel();
        
        //Botones
        btnRegistrar.setText("Consultar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet rs=null;
                Statement sentencia=null;
                String sql = "Select Codigo_S, Nombre_S, Precio_S From servicio";
                try {
                    sentencia=(Statement) con.createStatement();
                    rs=sentencia.executeQuery(sql);
                } catch (SQLException ex) { 
                    Logger.getLogger(Panel_Actualizar_Datos.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (int i = 0; i < nFilas; i++) {   
                    try {
                        if(rs.next()&&rs!=null){
                            dtmTable.setValueAt(rs.getString("Codigo_S"), i, 0);
                            dtmTable.setValueAt(rs.getString("Nombre_S"), i, 1);
                            dtmTable.setValueAt(rs.getDouble("Precio_S"), i, 2);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Panel_Actualizar_Datos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });


        btnNuevaFila.setText("Nueva Fila");
        btnNuevaFila.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet rs=null;
                Statement sentencia=null;
                String sql = "Select count(*) as filas From servicio"; 
                try {
                    sentencia=(Statement) con.createStatement();
                    rs=sentencia.executeQuery(sql);
                    rs.next();
                    int Filas = rs.getInt("Filas");
                    rs.close();
                    for(int i=0;i<Filas;i++){
                        btnNuevaFilaActionPerformed(e, dtmTable, vector); 
                    }
                } catch (SQLException ex) { 
                    Logger.getLogger(Panel_Actualizar_Datos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //Paneles
        panel.setLayout(new java.awt.BorderLayout());

        panel.add(lblTitulo, java.awt.BorderLayout.NORTH);
        panel.add(lblEspacio1, java.awt.BorderLayout.EAST);
        panel.add(lblEspacio2, java.awt.BorderLayout.WEST);

        panel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        //panel.add(pSur, java.awt.BorderLayout.SOUTH);

        /*Panel parte inferior*/
        pSur.setLayout(new java.awt.GridLayout(1, 3));
        pSur.add(btnNuevaFila);
        pSur.add(btnRegistrar);

        return panel;
    }

    //Panel Nuevo_Cliente
    private JPanel PActualizar_Cliente (){
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
 
        final String[] vector = {null, null, null};
        //JTable
        tTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
            },
            new String [] {
                "Numero_Cl", "Nombre_Cl", "Telefono_Cl"
            }
        ));
        
        jScrollPane1.setViewportView(tTabla);
        
        final javax.swing.table.DefaultTableModel dtmTable = (DefaultTableModel) tTabla.getModel();
        //Botones
        btnRegistrar.setText("Consultar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet rs=null;
                Statement sentencia=null;
                String sql = "Select Numero_Cl, Nombre_Cl, Telefono_Cl From cliente";
                try {
                    sentencia=(Statement) con.createStatement();
                    rs=sentencia.executeQuery(sql);
                } catch (SQLException ex) { 
                    Logger.getLogger(Panel_Actualizar_Datos.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (int i = 0; i < nFilas; i++) {   
                    try {
                        if(rs.next()&&rs!=null){
                            dtmTable.setValueAt(rs.getInt("Numero_Cl"), i, 0);
                            dtmTable.setValueAt(rs.getString("Nombre_Cl"), i, 1);
                            dtmTable.setValueAt(rs.getString("Telefono_Cl"), i, 2);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Panel_Actualizar_Datos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });


        btnNuevaFila.setText("Nueva Fila");
        btnNuevaFila.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet rs=null;
                Statement sentencia=null;
                String sql = "Select count(*) as filas From cliente"; 
                try {
                    sentencia=(Statement) con.createStatement();
                    rs=sentencia.executeQuery(sql);
                    rs.next();
                    int Filas = rs.getInt("Filas");
                    rs.close();
                    for(int i=0;i<Filas;i++){
                        btnNuevaFilaActionPerformed(e, dtmTable, vector); 
                    }
                } catch (SQLException ex) { 
                    Logger.getLogger(Panel_Actualizar_Datos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        //Paneles
        panel.setLayout(new java.awt.BorderLayout());

        panel.add(lblTitulo, java.awt.BorderLayout.NORTH);
        panel.add(lblEspacio1, java.awt.BorderLayout.EAST);
        panel.add(lblEspacio2, java.awt.BorderLayout.WEST);

        panel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

//        panel.add(pSur, java.awt.BorderLayout.SOUTH);

        /*Panel parte inferior*/
        pSur.setLayout(new java.awt.GridLayout(1, 3));
        pSur.add(btnNuevaFila);
        pSur.add(btnRegistrar);
        
        return panel;
    }
    
    private JPanel PActualizar_Proveedor (){
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
        
        jScrollPane1.setViewportView(tTabla);
        
        final javax.swing.table.DefaultTableModel dtmTable = (DefaultTableModel) tTabla.getModel();
        //Botones
        btnRegistrar.setText("Consultar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet rs=null;
                Statement sentencia=null;
                String sql = "Select Codigo_Pro, Nombre_Pro, No_Tel_Pro, Direccion_Pro From proveedor";
                try {
                    sentencia=(Statement) con.createStatement();
                    rs=sentencia.executeQuery(sql);
                } catch (SQLException ex) { 
                    Logger.getLogger(Panel_Actualizar_Datos.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (int i = 0; i < nFilas; i++) {   
                    try {
                        if(rs.next()&&rs!=null){
                            dtmTable.setValueAt(rs.getString("Codigo_Pro"), i, 0);
                            dtmTable.setValueAt(rs.getString("Nombre_Pro"), i, 1);
                            dtmTable.setValueAt(rs.getString("No_Tel_Pro"), i, 2);
                            dtmTable.setValueAt(rs.getString("Direccion_Pro"), i, 3);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Panel_Actualizar_Datos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });


        btnNuevaFila.setText("Nueva Fila");
        btnNuevaFila.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet rs=null;
                Statement sentencia=null;
                String sql = "Select count(*) as filas From proveedor"; 
                try {
                    sentencia=(Statement) con.createStatement();
                    rs=sentencia.executeQuery(sql);
                    rs.next();
                    int Filas = rs.getInt("Filas");
                    rs.close();
                    for(int i=0;i<Filas;i++){
                        btnNuevaFilaActionPerformed(e, dtmTable, vector); 
                    }
                } catch (SQLException ex) { 
                    Logger.getLogger(Panel_Actualizar_Datos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //Paneles
        panel.setLayout(new java.awt.BorderLayout());

        panel.add(lblTitulo, java.awt.BorderLayout.NORTH);
        panel.add(lblEspacio1, java.awt.BorderLayout.EAST);
        panel.add(lblEspacio2, java.awt.BorderLayout.WEST);

        panel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        //panel.add(pSur, java.awt.BorderLayout.SOUTH);

        /*Panel parte inferior*/
        pSur.setLayout(new java.awt.GridLayout(1, 3));
        pSur.add(btnNuevaFila);
        pSur.add(btnRegistrar);
        
        return panel;
    }
     
    private void btnNuevaFilaActionPerformed (java.awt.event.ActionEvent evt, javax.swing.table.DefaultTableModel dtmTable, String[] vector){       
        dtmTable.addRow(vector);
        nFilas ++;
    }
    
    private void btnRegistrarActionPerformed (java.awt.event.ActionEvent evt, Connection con, String sql) throws SQLException{
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
    private int ultimo_Cliente;
    // End of variables declaration   
}
