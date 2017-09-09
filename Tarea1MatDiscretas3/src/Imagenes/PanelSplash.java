package Imagenes;

// Imagen de fondo en Java – JFrame
// http://bytelchus.com/imagen-de-fondo-en-java-jframe/
// NOTA:
// Me base en la primera parte de aqui para crear el panel con el fondo
// Pero a la hora de ponerlo en el JFrame de la pantalla Splash, solo
// es necesario añadir dicho objeto a los componentes del JFrame

// Importes necesarios para la clase
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

// Clase cuyo unico proposito es servir como fondo para la ventana de bienvenida del aplicativo
public class PanelSplash extends JPanel
{
    public PanelSplash()
    {
        this.setSize(800, 500);
    }
    
    @Override
    public void paintComponent(Graphics imagen)
    {
        Dimension medida = getSize();
        ImageIcon fondo = new ImageIcon(getClass().getResource("/Imagenes/Splash.jpg"));
        imagen.drawImage(fondo.getImage(), 0, 0, medida.width, medida.height, null);
        setOpaque(false);
        super.paintComponent(imagen);
    }
}
