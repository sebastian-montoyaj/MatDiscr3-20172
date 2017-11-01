package Imagenes;

// Imagen de fondo en Java – JFrame
// http://bytelchus.com/imagen-de-fondo-en-java-jframe/
// NOTA:
// Nos basamos en la primera parte de aqui para crear el panel con el fondo
// Pero a la hora de ponerlo en el JFrame de la pantalla Splash, solo
// es necesario añadir dicho objeto a los componentes del JFrame

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

// Clase cuyo unico proposito es servir como fondo para la ventana de bienvenida
public class PanelSplash extends JPanel
{
    public PanelSplash()
    {
        // Se establece el tamaño del fondo
        this.setSize(850, 430);
    }
    
    @Override
    public void paintComponent(Graphics imagen)
    {
        // Se carga la imagen a utilizar para el fondo y se establece como fondo de este panel
        Dimension medida = getSize();
        ImageIcon fondo = new ImageIcon(getClass().getResource("/Imagenes/Splash.jpg"));
        imagen.drawImage(fondo.getImage(), 0, 0, medida.width, medida.height, null);
        setOpaque(false);
        super.paintComponent(imagen);
    }
    
    
}
