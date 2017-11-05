package Imagenes;

// Usamos la mismas instrucciones de PanelSplash para crear el fondo de pantalla de la ventana principal
// Revise dicha clase para encontrar un enlace con mas informacion al respecto

// Importes necesarios para la clase
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

// Clase cuyo unico proposito es servir como fondo para la ventana principal
public class PanelPrincipal extends JPanel
{
    public PanelPrincipal()
    {
        // Se establece el tamaño del fondo
        this.setSize(1204, 857);
    }
    
    @Override
    public void paintComponent(Graphics imagen)
    {
        // Se carga la imagen a utilizar para el fondo y se establece como fondo de este panel
        Dimension medida = getSize();
        ImageIcon fondo = new ImageIcon(getClass().getResource("/Imagenes/Fondo.jpg"));
        imagen.drawImage(fondo.getImage(), 0, 0, medida.width, medida.height, null);
        setOpaque(false);
        super.paintComponent(imagen);
    }
}
