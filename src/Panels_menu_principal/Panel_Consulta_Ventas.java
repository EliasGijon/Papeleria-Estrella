package Panels_menu_principal;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

/*
 * @author Gijon Vazquez Elias Dominick
 */
public class Panel_Consulta_Ventas extends JPanel implements ActionListener{
    
    //Talves se necesite parametro Connection
    public Panel_Consulta_Ventas (Connection con){
        this.con = con;
        initComponents();
    }
    
    private void initComponents(){
        final String [] vector_Producto_Venta = {null,null,null,null,null,null,null};
        final String [] vector_Servicio_Venta = {null,null,null,null,null,null,null};
        final byte nTabs = 2;
        
        pTablaP_Y_S = new JPanel();
        pCentro = new JPanel();
        pDatos_Venta = new JPanel();
        pCodigo_V = new JPanel();
        pNumero_Cl = new JPanel();
        pTotal_V = new JPanel();
        pBotones = new JPanel();
        pTabbedPane = new ArrayList<JPanel>();
        
        tProductos_Venta = new JTable();
        tServicios_Venta = new JTable();      
        sptProductos = new JScrollPane();
        sptServicios = new JScrollPane();
        
        tpTablas = new JTabbedPane(JTabbedPane.BOTTOM);
        
        btnConsultar_Cliente = new JButton();
        btnConsultar_General = new JButton();
        btnNueva_fila = new ArrayList<JButton>();
        jdchFecha_V = new com.toedter.calendar.JDateChooser();
        lblCodigo_V = new JLabel();
        lblFecha_V = new JLabel();
        lblNumero_Cl = new JLabel();
        lblTotal_V = new JLabel();
        lblTitulo = new JLabel();
        
        //    private JLabel lblTitulo;
        txtCodigo_V = new JTextField();
        txtNumero_Cl = new JTextField();
        txtTotal_V = new JTextField();
        
        //JLabel's
        lblTitulo.setText("Nueva venta");
        lblCodigo_V. setText("Codigo_V     ");
        lblFecha_V.  setText("Fecha_V       ");
        lblNumero_Cl.setText("Numero_Cl  ");
        lblTotal_V.  setText("Total Venta");
        
        //JTextField's
        txtCodigo_V.setEditable(false);
        txtNumero_Cl.setText("0");
        
        //JDate's
        jdchFecha_V.setDateFormatString("dd/MM/yyyy");
        
        //JTable's
        tProductos_Venta.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    vector_Producto_Venta
                },
                new String [] {
                    "Codigo Venta","Codigo Producto","Nombre Producto", "Precio", "Cantidad", "Total", "Fecha"
                }
            ));
            //En base al nombre del producto se busca el codigo en la base de datos

            sptProductos.setViewportView(tProductos_Venta);
            
        tServicios_Venta.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    vector_Servicio_Venta
                },
                new String [] {
                    "Codigo Venta", "Codigo Servicio", "Nombre Servico", "Precio", "Cantidad", "Total", "Fecha"
                }
            ));
            //En base al nombre del producto se busca el codigo en la base de datos

            sptServicios.setViewportView(tServicios_Venta);  
        
//JButton's
        botonesNueva_Fila(vector_Producto_Venta, vector_Servicio_Venta, nTabs);
        
    
        btnConsultar_Cliente.setText("Consultar cliente");
        btnConsultar_Cliente.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultar_ClienteActionPerformed(con);
            }

            private void btnConsultar_ClienteActionPerformed(Connection con) {          
            }
        });
       
        btnConsultar_General.setText("Consulta General");
        btnConsultar_General.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt){
                //btnRegistrarActionPerformed(evt);
            }
        });    
           
        
        //JTabbedPane
        tpTablas.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tpTablas.setFocusable(false);
	tpTablas.setRequestFocusEnabled(false);    
        
        
                      
        //JPanel's
        panelesTabbedPane(nTabs);
        
        /*Contenido Panel_Nueva_Venta*/
        setLayout(new BorderLayout());
        
        add(pCentro, BorderLayout.CENTER);
        add(pBotones, BorderLayout.SOUTH);
        add(lblTitulo, BorderLayout.NORTH);      
        
        /*Contenido pCodigo_V*/
        pCodigo_V.setLayout(new BorderLayout());
        pCodigo_V.add(lblCodigo_V, BorderLayout.WEST);
        pCodigo_V.add(txtCodigo_V, BorderLayout.CENTER);
        
        /*Contenido pNumero_Cl*/
        pNumero_Cl.setLayout(new BorderLayout());
        pNumero_Cl.add(lblNumero_Cl, BorderLayout.WEST);
        pNumero_Cl.add(txtNumero_Cl, BorderLayout.CENTER);
        
        /*Contenido pDatos_Venta*/
        pDatos_Venta.setLayout(new BorderLayout());
        
        pDatos_Venta.add(pCodigo_V, BorderLayout.NORTH);
        
        pDatos_Venta.add(lblFecha_V, BorderLayout.WEST);
        pDatos_Venta.add(jdchFecha_V, BorderLayout.CENTER);
        
        pDatos_Venta.add(pNumero_Cl, BorderLayout.SOUTH);
        
        /*Contenido pTotal_V*/
        pTotal_V.setLayout(new BorderLayout());
        pTotal_V.add(lblTotal_V, BorderLayout.WEST);
        pTotal_V.add(txtTotal_V, BorderLayout.CENTER);
        
        /*Contenido pTablaP_Y_S*/
        pTablaP_Y_S.setLayout(new BorderLayout());
        pTablaP_Y_S.add(pDatos_Venta, BorderLayout.NORTH);
        //pTablaP_Y_S.add(pTotal_V, BorderLayout.SOUTH);
        pTablaP_Y_S.add(tpTablas, BorderLayout.CENTER);
        
        
        /*Contenido pCentro*/
        pCentro.setLayout(new BorderLayout());
        pCentro.add(pDatos_Venta, BorderLayout.NORTH);
        pCentro.add(pTablaP_Y_S, BorderLayout.CENTER);
        pCentro.add(pBotones, BorderLayout.SOUTH);
        
        /*Contenido pBotones*/
        pBotones.setLayout(new GridLayout(1, 1));
        pBotones.add(btnConsultar_Cliente);
        //pBotones.add(btnRegistrar);
        
    }
    
    
    private Date fechaActual(){
        Date fecha=new Date();
        SimpleDateFormat formatoFecha=new SimpleDateFormat("dd/MM/YYYY");
        
        //el return de este metodo se asigna a txtFecha_V
        /*
            se puede añadir un listener para que no se pueda editar fecha
            jdchFecha_V.setDate(fechaActual);
        */
        return fecha;
    }
    
    private void botonesNueva_Fila (final String[] vector_Producto_Venta, final String[] vector_Servicio_Venta, byte nTabs){    
        for (int i = 0; i < nTabs; i++) {
            
            JButton btn = new JButton();
            
            btnNueva_fila.add(btn);
            btnNueva_fila.get(i).setText("+");
            final int indice = i;
            btnNueva_fila.get(i).addActionListener(new java.awt.event.ActionListener() {
                @Override           
                public void actionPerformed(ActionEvent evt) {
                    
                    try{
                        javax.swing.table.DefaultTableModel tmTabla = new javax.swing.table.DefaultTableModel();
                        String[] vector = null;
                        
                        if (indice == 0){//Producto_Venta
                            tmTabla = (javax.swing.table.DefaultTableModel)tProductos_Venta.getModel();
                            vector = vector_Producto_Venta;
                        }
                        else{//Servicio_Venta
                            if (indice == 1){
                                tmTabla = (javax.swing.table.DefaultTableModel)tServicios_Venta.getModel();
                                vector = vector_Servicio_Venta;
                            }
                        }
                        
                        btnNueva_FilaActionPerformed(evt, tmTabla, vector, indice);
                        final javax.swing.table.DefaultTableModel dtmTableServ = (DefaultTableModel) tServicios_Venta.getModel();
                        final javax.swing.table.DefaultTableModel dtmTablePro = (DefaultTableModel) tProductos_Venta.getModel();
        
                        Datos(indice,dtmTableServ,dtmTablePro);
                    }
                    catch(Exception e){
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                }
            });
            
            btnNueva_fila.get(i).doClick();
            
        }     
    }
    
    private void Datos (final int indice, final DefaultTableModel dtmTableServ, final DefaultTableModel dtmTablePro){
        int nFilas = 0;
        JButton btnConsulta = new JButton();
        btnConsulta.setText("Consultar");
        btnConsulta.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = null;
                int nFilas = 0;
                if (indice == 0){//Producto_Venta
                    sql = "Select count(*) as filas From producto_venta";

                }
                else{//Servicio_Venta
                    if (indice == 1){
                        sql = "Select count(*) as filas From servicio_venta"; 
                    }
                }
                ResultSet rs=null;
                Statement sentencia=null;
                try {
                    sentencia=(Statement) con.createStatement();
                    rs=sentencia.executeQuery(sql);
                    rs.next();
                    nFilas = rs.getInt("Filas");
                    rs.close();
                   /* for(int i=0;i<Filas;i++){
                       // btnNuevaFilaActionPerformed(e, dtmTable, vector); 
                       
                    }*/
                }catch(SQLException ex){
                }
                
                if (indice == 0){//Producto_Venta
                    sql = "select pv.Codigo_V as Ventas, pv.Codigo_P as ID, p.NOMBRE_P as Producto, p.Precio_P as Precio, pv.Cantidad_P as Pedido, p.Precio_P*pv.Cantidad_P as SubTotal, v.Fecha_V as Fecha from producto_venta as pv inner join producto as p on p.Codigo_P = pv.Codigo_P inner join venta as v on v.Codigo_V = pv.Codigo_V having  p.Nombre_P is not null order by v.Fecha_V asc";
                    try {
                        sentencia=(Statement) con.createStatement();
                        rs=sentencia.executeQuery(sql);
                    } catch (SQLException ex) { 
                        Logger.getLogger(Panel_Consulta_Datos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    for (int i = 0; i < nFilas; i++) {   
                        try {
                            if(rs.next()&&rs!=null){
                                dtmTablePro.setValueAt(rs.getString("Ventas"), i, 0);
                                dtmTablePro.setValueAt(rs.getString("ID"), i, 1);
                                dtmTablePro.setValueAt(rs.getString("Producto"), i, 2);
                                dtmTablePro.setValueAt(rs.getString("Precio"), i, 3);
                                dtmTablePro.setValueAt(rs.getString("Pedido"), i, 4);
                                dtmTablePro.setValueAt(rs.getString("SubTotal"), i, 5);
                                dtmTablePro.setValueAt(rs.getString("Fecha"), i, 6);
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Panel_Consulta_Datos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                else{//Servicio_Venta
                    if (indice == 1){
                        sql = "select sv.Codigo_V as Venta, sv.Codigo_S as ID, s.Nombre_S as Servicio, s.Precio_S as Precio, sv.Cantidad_S as Encargo, s.Precio_S*sv.Cantidad_S as SubTotal, v.Fecha_V as Fecha from servicio_venta as sv inner join servicio as s on s.Codigo_S = sv.Codigo_S inner join venta as v on v.Codigo_V = sv.Codigo_V having Servicio is not null order by v.Fecha_V asc";
                        try {
                            sentencia=(Statement) con.createStatement();
                            rs=sentencia.executeQuery(sql);
                        } catch (SQLException ex) { 
                            Logger.getLogger(Panel_Consulta_Datos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        for (int i = 0; i < nFilas; i++) {   
                            try {
                                if(rs.next()&&rs!=null){
                                    dtmTableServ.setValueAt(rs.getString("Venta"), i, 0);
                                    dtmTableServ.setValueAt(rs.getString("ID"), i, 1);
                                    dtmTableServ.setValueAt(rs.getString("Servicio"), i, 2);
                                    dtmTableServ.setValueAt(rs.getString("Precio"), i, 3);
                                    dtmTableServ.setValueAt(rs.getString("Encargo"), i, 4);
                                    dtmTableServ.setValueAt(rs.getString("SubTotal"), i, 5);
                                    dtmTableServ.setValueAt(rs.getString("Fecha"), i, 6);
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(Panel_Consulta_Datos.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } 
                    }
                }
            }
        });
        btnConsulta.doClick();
    }
    
    private void panelesTabbedPane (byte nTabs){
        for (int i = 0; i < nTabs; i++) {
            JPanel panel = new JPanel();
            pTabbedPane.add(panel);
            pTabbedPane.get(i).setLayout(new BorderLayout());
            
            if (i == 0){
                pTabbedPane.get(i).add(sptProductos, BorderLayout.CENTER);
                //pTabbedPane.get(i).add(btnNueva_fila.get(i), BorderLayout.SOUTH);
                tpTablas.addTab("Productos vendidos", pTabbedPane.get(i));
              //  Productos=(JTabbedPane) tpTablas.getTabComponentAt(i);
            }
            else{
                if (i == 1){
                    pTabbedPane.get(i).add(sptServicios, BorderLayout.CENTER);
                    //pTabbedPane.get(i).add(btnNueva_fila.get(i), BorderLayout.SOUTH);
                    tpTablas.addTab("Servicios vendidos", pTabbedPane.get(i));
                 //   Servicio=(JTabbedPane) tpTablas.getSelectedComponent();
                }
            }
            
        }
    
    }
    
    //Listeners
    private void btnNueva_FilaActionPerformed(java.awt.event.ActionEvent evt, javax.swing.table.DefaultTableModel dtmTabla, String[] vector, int indice) {                                                  
        // TODO add your handling code here:
        //Se abre ventana para agregar productos o servicios para venta
        String sql = null;
        if (indice == 0){//Producto_Venta
            sql = "Select count(*) as filas From producto_venta";
            
        }
        else{//Servicio_Venta
            if (indice == 1){
                sql = "Select count(*) as filas From servicio_venta"; 
            }
        }
        ResultSet rs=null;
        Statement sentencia=null;
        try {
            sentencia=(Statement) con.createStatement();
            rs=sentencia.executeQuery(sql);
            rs.next();
            int Filas = rs.getInt("Filas");
            rs.close();
            for(int i=0;i<Filas;i++){
               // btnNuevaFilaActionPerformed(e, dtmTable, vector); 
               dtmTabla.addRow(vector);
            }
        }catch(SQLException ex){
            
        }
        
        
    }                                                          
    
    // Variables declaration - do not modify  
    private Connection con;
    
    private JPanel pCentro;
    private JPanel pBotones;
    
    private JPanel pDatos_Venta;
    private JPanel pTablaP_Y_S;
    private JPanel pCodigo_V;
    private JPanel pNumero_Cl;
    private JPanel pTotal_V;
    private ArrayList<JPanel> pTabbedPane;
                       
    private JTable tProductos_Venta;
    private JTable tServicios_Venta;
    private JScrollPane sptProductos;
    private JScrollPane sptServicios;
    
    private JTabbedPane tpTablas;
    //public JTabbedPane Productos;
    //public JTabbedPane Servicio;
    
    private JButton btnConsultar_Cliente;
    private JButton btnConsultar_General;
    private ArrayList<JButton> btnNueva_fila;
    
    private com.toedter.calendar.JDateChooser jdchFecha_V;
    private JLabel lblCodigo_V;
    private JLabel lblFecha_V;
    private JLabel lblNumero_Cl;
    private JLabel lblTotal_V;
    private JLabel lblTitulo;
//    private JLabel lblTitulo;
    private JTextField txtCodigo_V;
    private JTextField txtNumero_Cl;
    private JTextField txtTotal_V;
    // End of variables declaration    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==tpTablas.getTabComponentAt(0)){
            
        }
       /* if(e.getSource()==Servicio){
            JFrame x=new JFrame();
            x.setVisible(true);
        }*/
    }
    
}
