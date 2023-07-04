/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Panels_Login;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JP_Login extends JPanel {
	private static final long serialVersionUID = 1L;
	// Crear el JPanel para el Formulario Lider, Solo a las Personas Autorizadas
	public void paintComponent(Graphics g){
            //Creamos Variable para el Tamaño del JFrame
            Dimension tamaño=getSize();
            //Creamos Variable de la Imagen a Poner dentro del Area del JPanel
            ImageIcon imagen=new ImageIcon(new  ImageIcon(getClass().getResource("/Imagenes/Fondo_3.jpg")).getImage());
            //Pintamos la Imagen deacuerdo al tamaño del JFrame
            g.drawImage(imagen.getImage(),0,0,tamaño.width,tamaño.height,null);
	}
}
