package papeleria_estrella;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author felix
 */
public class Conectar {
    Connection con;
    //Datos de conexion a base de datos
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/papeleria_estrella";
    
    
    public Connection getConection(String user, String pass){
        //Reset a null conex a bd
        con=null;
        
        try{
            Class.forName(driver);
            //conexion a bd
            con = (Connection) DriverManager.getConnection(url, user, pass);
            //Si la conex exitosa, mostramos un mensaje conexion exitosa
        }
        //Si conexion no exitosa
        catch( Exception e ){
            JOptionPane.showMessageDialog(null, "Error de conexión");
        }
        
        return con;
    }
}
