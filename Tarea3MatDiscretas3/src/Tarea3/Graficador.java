package Tarea3;

// Las siguientes paginas me ayudaron con la creacion de esta clase:
// 
// Clase Graphics y sus métodos
// Link: http://www.javaya.com.ar/detalleconcepto.php?codigo=130&inicio=40
// 
// 2D Graphics, Java2D and Images
// Link: https://www3.ntu.edu.sg/home/ehchua/programming/java/J8b_Game_2DGraphics.html
// 
// Java para programadores. (5.4): Los métodos Graphics y Paint [Esta pagina fue una de las que me guio para hacer las imagenes off-screen (en la memoriia) para luego cargarlas a la interfaz de usuario]
// http://recursosformacion.com/wordpress/2013/05/java-para-programadores-5-3los-metodos-graphics-y-paint/
// 
// El mejor articulo porque aunque esta en ingles me dio la idea fundamental de como usar canvas o crear imagenes en memoria para luego retornarlas como iconos:
// Dynamic Graphics Object Painting [Este articulo me ayudo muchisimo]
// Link: http://stackoverflow.com/questions/10628492/dynamic-graphics-object-painting/10628553#10628553
//
// paintComponent() vs paint() and JPanel vs Canvas in a paintbrush-type GUI [Otro enlace interesante que compara metodos de graficación]
// http://stackoverflow.com/questions/12175174/paintcomponent-vs-paint-and-jpanel-vs-canvas-in-a-paintbrush-type-gui
//
// How to draw a n sided regular polygon in cartesian coordinates? [Esta pagina en especifico me dio la idea de como graficar los n vertices de un poligono regular]
// https://stackoverflow.com/questions/7198144/how-to-draw-a-n-sided-regular-polygon-in-cartesian-coordinates

// Importo todos los paquetes necesarios para el dibujado de figuras
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;

// Clase encargada del dibujado de las representaciones o coloraciones de los campos finitos
public class Graficador
{
    // ----- Variables -----
    private BufferedImage superficie; // Variable que contendra el area de dibujo en la memoria del computador y que nos permitira realizar dibujos off-sreen (o sea primero en memoria y luego expuestos en alguna interfaz)
    private int anchoSuperficie = 788; // Variable que guarda el ancho de la superficie de dibujo
    private int altoSuperficie = 788; // Variable que guarda el alto de la superficie de dibujo
    
    // Constructor que inicializa el area de dibujo(lienzo) sobre sobre el cual se dibujara
    public Graficador()
    {
        // Para empezar se define el tamaño del area de dibujo y se establece los colores que tendra cada pixel.
        // En nuestro caso seran los clasicos colores RGB y uno extra (Alpha) que define la opacidad o transparencia de cada pixel
        superficie = new BufferedImage(anchoSuperficie, altoSuperficie, BufferedImage.TYPE_INT_ARGB); 
        
        // Luego tomo control del area de dibujo con una variable graphics
        Graphics2D AreaDibujo = (Graphics2D) superficie.getGraphics();
        
        AreaDibujo.setColor(Color.white); // Establezco el color blanco para esta variable controladora
        AreaDibujo.fillRect(0,0,anchoSuperficie, altoSuperficie); // Y relleno de blanco toda el area para dejar la superficie limpia
        
        AreaDibujo.dispose(); // Para terminar cerramos el canal de comunicacion para no dejar servicios activos de manera innecesaria
    }
    
    // El metodo limpiar tiene como tarea borrar el area de dibujo para preparar la superficie para algun nuevo dibujo
    public void limpiar()
    {
        Graphics2D AreaDibujo = (Graphics2D) superficie.getGraphics(); // Tomamos control del area de dibujo
        AreaDibujo.fillRect(0,0,anchoSuperficie, altoSuperficie); // Limpiamos el area de dibujo desde el primer pixel hasta el ultimo
        AreaDibujo.dispose(); // Y se libera el control del espacio de trabajo
    }
    
    // Este metodo dibujarEtiqueta tiene como tarea escribir un pequeño texto en las coordenadas y con el color que se le indique en los parametros
    public void dibujarEtiqueta(String etiqueta, int coordenadaX, int coordenadaY, Color colorEtiqueta)
    {
        // Primero, creo una variable grafica para tener control del area de dibujo
        Graphics2D AreaDibujo = (Graphics2D) superficie.getGraphics();
        
        // Luego, creo un variable de tipo fuente la cual se debe inicializar con: 
        // 1ero - Un tipo de letra especifico. Aqui particularmente usare SansSerif, aunque se puede usar Arial u otra
        // 2do - Un estilo de escritura. Donde 0 = Sencilla, 1 = Negrilla ó 2 = Italica
        // y 3ero - Un tamaño de letra en pixeles
        Font fuenteEtiqueta = new Font("SansSerif", 1, 10);
        
        AreaDibujo.setFont(fuenteEtiqueta); // Ahora, establezco esa fuente como el estilo con el que escribire la etiqueta
        AreaDibujo.setColor(colorEtiqueta); // Despues, establezco el color de la etiqueta con el color que entro por los parametros
        AreaDibujo.drawString(etiqueta, coordenadaX, coordenadaY); // Y finalmente dibujo la etiqueta con la fuente y color asignados en la posicion de los parametros
        
        // Una vez termino de hacer lo que necesitaba con este metodo, cierro la comunicacion
        AreaDibujo.dispose();
    }
    
    // Metodo para dibujar un cuadrado(baldosa) en la posicion, tamaño y color especificados. El ultimo parametro define si se debe rellenar la baldosa o no
    public void dibujarBaldosa(int coorX, int coorY, int anchoBaldosa, int altoBaldosa, Color colorBaldosa, boolean rellena)
    {
        // Primero, creo una variable grafica para tener control del area de dibujo
        Graphics2D AreaDibujo = (Graphics2D) superficie.getGraphics();
        
        // Segundo, se establece el color con el que se procedera a dibujar
        AreaDibujo.setColor(colorBaldosa);
        
        // Si la baldosa se debe rellenar entonces
        if (rellena)
        {
            // Se dibuja el cuadrado(baldosa) rellena con el color dado
            AreaDibujo.fillRect(coorX, coorY, anchoBaldosa, altoBaldosa);
            
            // Y se cambia de nuevo el color a negro, para que en la proxima instruccion despues de este condicional se dibuje el borde de la baldosa
            AreaDibujo.setColor(Color.black);
        }
        
        // Si no entro al condicional anterior entonces se dibuja solo el contorno de la baldosa con el color orginal dado,
        // pero si entro al condicional anterior entonces se procede a dibujar sobre el cuadro relleno un borde negro
        AreaDibujo.drawRect(coorX, coorY, anchoBaldosa, altoBaldosa);
        
        // Una vez termino de hacer lo que necesitaba con este metodo, cierro la comunicacion
        AreaDibujo.dispose();
    }
    
    // Metodo para dibujar la representacion de un campo con los colores que simbolizan cada elemento dentro del grupo que lo conforman
    public Icon dibujarCampo(int[][] matrizCampo, float[] colores)
    {
        // En primer lugar, se procede a limpar el area de dibujo
        limpiar();
        
        // Luego, se crean algunas variables para el dibujado con el fin de:
        int margen = 10; // Dejar un espacio con respecto al borde del area de dibujo para que el trazo no comience desde el borde cero
        int numElementos = matrizCampo.length; // Variable para saber cuantos son los elementos que maneja el campo
        int tamBaldosa = (anchoSuperficie - margen*2)/numElementos; // Variable para saber en todo momento las dimensiones de culaquier baldosa
        int cX, cY = margen; // Variables para saber cual es la siguiente posicion en la que puedo dibujar
        
        // Ahora, mientras haya filas para la matriz que representa el campo HAGA:
        for (int i=0; i < numElementos; i++)
        {
            // Se inicializa que la fila a dibujar empezara desde el margen establecido (columna 0)
            cX = margen;
            
            // Despues, mientras haya columnas para la matriz que representa el campo HAGA:
            for (int j=0; j < numElementos; j++)
            {
                // Dibuje la baldosa, en las coordenadas calculadas con un ancho y alto iguales y definidos por el tamaño calculado previamente
                // Y con el color definido en la celda i|j que especifica el color (elemento) a colorear
                dibujarBaldosa(cX, cY, tamBaldosa, tamBaldosa, Color.getHSBColor(colores[matrizCampo[i][j]], 1, 1), true);
                
                // Ya con la baldosa dibujada, la siguiente debera dibujarse en una columna despues
                cX = cX + tamBaldosa;
            }
            // Despues de dibujarse una fila entera, la siguiente debera dibujarse una fila más abajo
            cY = cY + tamBaldosa;
        }
        
        // Una vez que termino de dibujar el campo retorno la imagen de la misma como si fuera un icono
        Icon graficaPoligono = new ImageIcon(superficie);
        return graficaPoligono; 
    }
    
    // Metodo para obtener una instantanea (foro) del area de dibujo y la cual se retorna como un icono.
    public Icon retornarLienzo()
    {
        Icon temp = new ImageIcon(superficie);
        return temp;
    }
}
