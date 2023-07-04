package Ventana;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JApplet;
import javax.swing.*;
import java.awt.BorderLayout;


//Paneles Ocupados
import Panels_Login.JP_Login;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.sql.Connection;
import papeleria_estrella.Conectar;

public class Login extends JApplet implements ActionListener {
    JFrame JF_Estadisticas;
    
    JPanel JP_Fondo;
    
    JLabel lbl_Imagen;
    JLabel lbl_Usuario;
    JLabel lbl_Contrasena;
    
    JTextField txt_Usuario;
    
    JPasswordField txt_Contrasena;
    
    JButton btn_Acceso; 
    JButton btn_Cancelar;
    
    public Login() {
        //1.Creamos el Formulario
        JF_Estadisticas = new JFrame();
        JF_Estadisticas.setTitle("Iniciar Sesi\u00F3n");
        JF_Estadisticas.setSize(600, 600);
        JF_Estadisticas.setLayout(new BorderLayout());
        JF_Estadisticas.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/Imagenes/Login-icon.png")));
	JF_Estadisticas.setUndecorated(true);
	JF_Estadisticas.setType(Type.POPUP);
        
        //2.Agregar los paneles para distribucion
        JP_Fondo = new JP_Login();
        JP_Fondo.setLayout(null);
        
        //3.Hacemos las Instrucciones
        lbl_Usuario = new JLabel("Usuario:");
        lbl_Usuario.setForeground(Color.WHITE);
        lbl_Usuario.setFont(new Font("Tahoma", Font.BOLD, 22));
        lbl_Usuario.setBounds(44, 357, 130, 29);
        JP_Fondo.add(lbl_Usuario);

        lbl_Contrasena = new JLabel("Contrase\u00F1a:");
        lbl_Contrasena.setForeground(Color.WHITE);
        lbl_Contrasena.setFont(new Font("Tahoma", Font.BOLD, 22));
        lbl_Contrasena.setBounds(44, 414, 157, 29);
        JP_Fondo.add(lbl_Contrasena);
        
        lbl_Imagen = new JLabel();
        lbl_Imagen.setIcon(new ImageIcon(Login.class.getResource("/Imagenes/user-icon.png")));
        lbl_Imagen.setBounds(44, 44, 500, 260);
        JP_Fondo.add(lbl_Imagen);
        
        //4.Recoleccion de Datos
        txt_Usuario = new JTextField();
        txt_Usuario.setBounds(289, 357, 255, 29);
        JP_Fondo.add(txt_Usuario);
        
        txt_Contrasena = new JPasswordField();
        txt_Contrasena.setBounds(289, 414, 255, 29);
        JP_Fondo.add(txt_Contrasena);
        
        //5.Botones de Acccion
        btn_Acceso = new JButton("Acceso");
        btn_Acceso.setFont(new Font("Tahoma", Font.BOLD, 16));
        btn_Acceso.setBounds(44, 512, 130, 40);
        JP_Fondo.add(btn_Acceso);
        
        btn_Cancelar = new JButton("Cancelar");
        btn_Cancelar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btn_Cancelar.setBounds(414, 513, 130, 39);
        JP_Fondo.add(btn_Cancelar);
        
        //6.Elementos con Acciones
        btn_Acceso.addActionListener(this);
        btn_Cancelar.addActionListener(this);
        
        //7.Mostrar Interfaz
        JF_Estadisticas.add(JP_Fondo,BorderLayout.CENTER);
        JF_Estadisticas.setLocationRelativeTo(null);
        JF_Estadisticas.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn_Acceso){
            String usuario,password;
            Conectar conectar = new Conectar();
            
            boolean TB=ValidacionUsuario();
            if(TB==true){
                usuario = txt_Usuario.getText();
                password = new String(txt_Contrasena.getPassword());

                Connection con;

                con = conectar.getConection(usuario, password);

                if (con!=null){
                    JOptionPane.showMessageDialog(null, "Bienvenido! "+ usuario); 
                    JF_Estadisticas.dispose();
                    Papeleria ventana = new Papeleria(con);
                }
            }
        }
        if(e.getSource()==btn_Cancelar){
            System.exit(0);
        }
    }
    
    public boolean ValidacionUsuario(){
        boolean TB=true;
        
        String Campo1 = txt_Usuario.getText();
        if(Campo1.equals("")){
            JOptionPane.showMessageDialog(null,"Se debe Ingresar un Nombre de Usuario","Error en Usuario",JOptionPane.WARNING_MESSAGE);
            TB=false;
        }
        
        String password= String.valueOf(txt_Contrasena.getPassword());
        if(password.equals("")){
            JOptionPane.showMessageDialog(null,"Se debe Ingresar una Contraseña","Error en Contraseña",JOptionPane.WARNING_MESSAGE);
            TB=false;
        }
         
        return TB;
    }

}
