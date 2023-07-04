/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Panels_menu_principal;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Eduardo Elias Hernandez Moreno
 */
public class Panel_Nueva_Compra extends JPanel{
    
    public Panel_Nueva_Compra (Connection con) throws SQLException{
        this.con = con;
        
        i = 0;
        String[] vector = {null,null,null,null,null,null};
        initComponents(vector);
        fProveedor = fNombre_proveedor(vector);
    }
    
    private void initComponents(final String[] vector) throws SQLException{
        cbProdutos = new JComboBox();
        
        codigos_Pro = new ArrayList<String>();
        fProveedor = new JFrame();
        pBotones = new JPanel();
        lblTitulo = new JLabel();
        sptNueva_Compra = new ArrayList<JScrollPane>();
        btnNueva_compra = new JButton();
        btnRegistrar = new JButton();
        tpCompras = new JTabbedPane(JTabbedPane.BOTTOM);
        
        //JLabel's
        lblTitulo.setText("Nueva Compra");
              
        //JTabbedPane
        btnNueva_compra.doClick();
        
        //JButton's
        btnNueva_compra.setText("Nueva compra");
        btnNueva_compra.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnNueva_compraActionPerformed(e, vector);
            }
        });
        
        btnRegistrar.setText("Registrar compra");
        
        //JPanel's
        /*Contenido de panel principal*/
        setLayout(new BorderLayout());
        add(lblTitulo, BorderLayout.NORTH);
        add(tpCompras, BorderLayout.CENTER);
        add(pBotones, BorderLayout.SOUTH);
        /*{*/
            /*Contenido pBotones*/
            pBotones.setLayout(new GridLayout(1,2));
            pBotones.add(btnNueva_compra);
            pBotones.add(btnRegistrar);
        /*}*/
        
        cbProductos1();
        
    }
    
    private void nuevaCompra (String[] vector){
        //JTable's
        tNueva_compra = new JTable();
        DefaultTableModel modelo = new DefaultTableModel(
        new Object[][]{
                vector
            },
            new String[]{
                "Codigo del producto", "Nombre", "Fecha_C", "Cantidad", "Precio", "Total"
            }  
        );
        
        
        tNueva_compra.setModel(modelo);
        //JScrollPane's
        JScrollPane spnewScrollP = new JScrollPane();
        spnewScrollP.setViewportView(tNueva_compra);
        sptNueva_Compra.add(spnewScrollP);
    }
    
    private JFrame fNombre_proveedor (final String[] vector) throws SQLException{
        fNombre_proveedor = new JFrame();
        final JComboBox cbProveedores;
        
        JPanel pPrincipal = new JPanel();
        JPanel pCENTER = new JPanel();
        JLabel lblTitulo1 = new JLabel();        
        final JTable tProveedor = new JTable();
        JButton btnListo = new JButton();
        JScrollPane spProveedor = new JScrollPane();
        
        final DefaultTableModel dtmProveedor = new DefaultTableModel(
          new Object[][]{
                {null, null}
            },
            new String[]{
                "Codigo_Pro", "Nombre_Pro"
            }    
        );
        
        //JTable
        tProveedor.setModel(dtmProveedor);
        tProveedor.setEnabled(false);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText("Codigo de proveedor");
        tProveedor.getColumnModel().getColumn(0).setCellRenderer(renderer);
        
        String sql = "SELECT Codigo_Pro, Nombre_Pro FROM proveedor";
        
        ResultSet rs = consultar(sql);
        cbProveedores = new JComboBox();
        while (rs.next() == true){
            cbProveedores.addItem(rs.getString("Codigo_Pro")+"--"+rs.getString("Nombre_Pro"));
        }
        
        rs.close();
        cbProveedores.addItemListener(new java.awt.event.ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                cbItemPerformed(e, cbProveedores, tProveedor);
            }
        });
        
        //JLabel
        lblTitulo1.setText("Nombre de proveedor");

        //JScrollPane's
        spProveedor.setViewportView(tProveedor);
        
        //JButton's
        btnListo.setText("Listo");
        
        btnListo.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnListoActionPerformed(dtmProveedor, vector);
            }
        });

        //JPanel's
        pCENTER.setLayout(new BorderLayout());
        pCENTER.add(cbProveedores, BorderLayout.NORTH);
        pCENTER.add(spProveedor, BorderLayout.CENTER);
        
        pPrincipal.setLayout(new BorderLayout());
        pPrincipal.add(lblTitulo1, BorderLayout.NORTH);
        pPrincipal.add(pCENTER, BorderLayout.CENTER);
        pPrincipal.add(btnListo, BorderLayout.SOUTH);
        
        fNombre_proveedor.add(pPrincipal);
        fNombre_proveedor.pack();
        
        return fNombre_proveedor;
    }
    
    private ResultSet consultar (String sql) throws SQLException{
        Statement st;
        ResultSet rs = null;
        

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Panel_Nueva_Venta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }
    
    private void cbItemPerformed (ItemEvent e, JComboBox cb, JTable tabla){
        if (e.getStateChange() == ItemEvent.SELECTED){
            if (!cb.getSelectedItem().toString().equals("")){
                String item = cb.getSelectedItem()+"";
                String codigo = "";
                String nombre = "";
                int indice = 0;
                
                for (int i = 0; i<item.length(); i++){
                    if (item.charAt(i) != '-' && item.charAt(i+1) != '-'){
                        codigo += item.charAt(i);
                    }
                    else{
                        codigo += item.charAt(i);
                        indice = i;
                        break;
                    }
                }
                
                for (int i = indice+3; i<item.length(); i++){
                     nombre += item.charAt(i);
                }
                tabla.getModel().setValueAt(codigo, 0, 0);
                tabla.getModel().setValueAt(nombre, 0, 1);
            }
        }
    }
    
    private void cbProductos1 () throws SQLException{
        String sql = "SELECT Codigo_P, NOMBRE_P FROM producto";
        ResultSet rs = consultar(sql);
        
        while (rs.next() == true){
            cbProdutos.addItem(rs.getString("Codigo_P")+"--"+rs.getString("NOMBRE_P"));
        }
    }
    
    //Listeners de botones
    private void btnListoActionPerformed(DefaultTableModel dtmProveedor, final String[] vector){
        datos_Proveedor = new String[2];
        datos_Proveedor[0] = dtmProveedor.getValueAt(0, 0)+"";
        datos_Proveedor[1] = dtmProveedor.getValueAt(0, 1)+"";
        JPanel panel = new JPanel();
        JPanel pNORTH = new JPanel();
        btnNueva_fila = new JButton();
        
        btnNueva_fila.setText("+");
        
        final DefaultTableModel modelo = (DefaultTableModel) tNueva_compra.getModel();
        
        btnNueva_fila.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnNueva_FilaActionPerformed(modelo, vector, cbProdutos, tNueva_compra);
            }
        });
        
        //JPaneles
        pNORTH.setLayout(new BorderLayout());
        pNORTH.add(btnNueva_fila, BorderLayout.CENTER);
        pNORTH.add(cbProdutos, BorderLayout.WEST);
        
        panel.setLayout(new BorderLayout());
        panel.add(sptNueva_Compra.get(i),BorderLayout.CENTER);
        panel.add(pNORTH, BorderLayout.NORTH);
        
        tpCompras.addTab(datos_Proveedor[1], panel);
        codigos_Pro.add(datos_Proveedor[0]);
        
        i++;
        fProveedor.dispose();
        //Se guarda para registrar en base de datos
        
    }
    
    private void btnNueva_FilaActionPerformed(DefaultTableModel modelo, String[] vector, JComboBox cb, JTable tabla){
        String item = cb.getSelectedItem() + "";
        String codigo = "";
        String nombre = "";
        int indice = 0;

        for (int i = 0; i<item.length(); i++){
            if (item.charAt(i) != '-' && item.charAt(i+1) != '-'){
                codigo += item.charAt(i);
            }
            else{
                codigo += item.charAt(i);
                indice = i;
                break;
            }
        }

        for (int i = indice+3; i<item.length(); i++){
             nombre += item.charAt(i);
        }
        tabla.getModel().setValueAt(codigo, 0, 0);
        tabla.getModel().setValueAt(nombre, 0, 1);
        modelo.addRow(vector);
    }
    
    private void btnNueva_compraActionPerformed(java.awt.event.ActionEvent evt, String[] vector){
        nuevaCompra(vector);
        fProveedor.setVisible(true);
        fProveedor.setLocationRelativeTo(null);
        fProveedor.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); 
        //Limpiar ventana
    }
    
    private Connection con;
    private ArrayList<String> codigos_Pro;
    private JComboBox cbProdutos;
      
    private int i;
    private String[] datos_Proveedor;
    private JFrame fProveedor;
    private JPanel pBotones;
    
    private JLabel lblTitulo;
    
    private JTable tNueva_compra;
    private ArrayList<JScrollPane> sptNueva_Compra;
    private JButton btnNueva_fila;
    private JButton btnNueva_compra;
    private JButton btnRegistrar;
    private JTabbedPane tpCompras;
    
    private JFrame fNombre_proveedor;
    
}
