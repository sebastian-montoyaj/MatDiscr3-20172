package Tarea1;

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
// Dynamic Graphics Object Painting [Este articulo me yudo muchisimo]
// Link: http://stackoverflow.com/questions/10628492/dynamic-graphics-object-painting/10628553#10628553
//
// paintComponent() vs paint() and JPanel vs Canvas in a paintbrush-type GUI [Otro enlace interesante que compara metodos de graficación]
// http://stackoverflow.com/questions/12175174/paintcomponent-vs-paint-and-jpanel-vs-canvas-in-a-paintbrush-type-gui
//
// How to draw a n sided regular polygon in cartesian coordinates? [Esta pagina en especifico me dio la idea de como graficar los n vertices de un poligono regular]
// https://stackoverflow.com/questions/7198144/how-to-draw-a-n-sided-regular-polygon-in-cartesian-coordinates

// Importo todos los paquetes necesarios para el dibujado de figuras
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

// Clase encargada del dibujado de los poligonos, vertices y aristas
public class Graficador
{
    // ----- Variables -----
    private BufferedImage superficie; // Variable que contendra el area de dibujo en la memoria del computador y que nos permitira realizar dibujos off-sreen (o sea primero en memoria y luego expuestos en alguna interfaz)
    private int anchoSuperficie = 760; // Variable que guarda el ancho de la superficie de dibujo
    private int altoSuperficie = 760; // Variable que guarda el alto de la superficie de dibujo
    
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
        Font fuenteEtiqueta = new Font("SansSerif", 1, 16);
        
        AreaDibujo.setFont(fuenteEtiqueta); // Ahora, establezco  esa fuente como el estilo con el que escribire la etiqueta
        AreaDibujo.setColor(colorEtiqueta); // Despues, establezco el color de la etiqueta con el color que entro por los parametros
        AreaDibujo.drawString(etiqueta, coordenadaX, coordenadaY); // Y finalmente dibujo la etiqueta con la fuente y color asignados en la posicion de los parametros
        
        // Una vez termino de hacer lo que necesitaba con este metodo, cierro la comunicacion
        AreaDibujo.dispose();
    }
    
    // Este metodo tiene como objetivo dibujar un punto o pequeño circulo el cual representara un vertice
    public void dibujarVertice(int x, int y)
    {
        // Primero, creo una variable grafica para tener control del area de dibujo
        Graphics2D AreaDibujo = (Graphics2D) superficie.getGraphics();
        
        // Despues, creo una variable para definir el tamaño o radio del vertice
        int radioVertice = 10;
        
        // Antes de dibujar el vertice como tal se deben recalcular las coordenadas de dibujo,
        // esto se hace porque el circulo a dibujar no se grafica precisamente tomando dichas
        // coordenadas como si fueran el centro del circulo, ya que lo que en verdad hace Java
        // es dibujar "imaginariamente" un cuadrado y dentro de tal cuadrado dibujar el circulo.
        // Entonces, como la esquina superior izquierda de dicho cuadrado si coincide con las 
        // coordenadas dadas entonces se procede a restarle a estas la mitad del radio del
        // circulo lo cual es suficiente para arreglar o dibujar precisamente el vertice donde
        // se pide.
        x = x -(radioVertice/2);
        y = y -(radioVertice/2);
        
        // Ya con las coordenadas bien definidas solo basta elegir un color para el vertice y dibujarlo
        AreaDibujo.setColor(Color.black);
        AreaDibujo.fillOval(x, y, radioVertice, radioVertice);
        
        // Una vez termino de hacer lo que necesitaba con este metodo, cierro la comunicacion
        AreaDibujo.dispose();
    }
    
    // Este metodo es el encargado de dibujar una arista entre dos vertices dados
    public void dibujarArista(int xInicio, int yInicio, int xFin, int yFin, Color colorArista)
    {
        // NOTA: Desde un punto de vista general, este metodo mas que dibujar aristas
        // lo que en verdad hace es dibujar lineas rectas entre dos coordenadas.
        
        // Primero, creo una variable grafica para tener control del area de dibujo
        Graphics2D AreaDibujo = (Graphics2D) superficie.getGraphics();
        
        // Luego establezco un pincel con un grosor adecuado para dibujar la arista
        AreaDibujo.setStroke(new BasicStroke(2.5f));
        
        // Tambien se establece el color indicado para la linea o arista
        AreaDibujo.setColor(colorArista);
        
        // Y finalmente se dibuja la linea
        AreaDibujo.drawLine(xInicio, yInicio, xFin, yFin);
        
        // Una vez termino de hacer lo que necesitaba con este metodo, cierro la comunicacion
        AreaDibujo.dispose();
    }
    
    // Metodo para dibujar un poligono de cualquier forma dadas las coordenadas de sus vertices
    public Icon dibujarPoligono(ArrayList coordenadasX, ArrayList coordenadasY, int xCentro, int yCentro, boolean conAristas)
    {
        // Primero, se limpia y se crea una variable grafica para tener control del area de dibujo
        limpiar();
        Graphics2D AreaDibujo = (Graphics2D) superficie.getGraphics();
        
        // Luego, creo algunas variables para la iteracion y dibujado de los vertices
        int numElementos = coordenadasX.size();
        int tempX1, tempY1, tempX2, tempY2;
        
        // Ahora, por cada par de coordenadas se hace:
        for (int i = 0; i < numElementos; i++)
        {
            tempX1 = (int) coordenadasX.get(i%numElementos); // Se obtiene la coordenada X del vertice i
            tempY1 = (int) coordenadasY.get(i%numElementos); // Se obtiene la coordenada Y del vertice i
            
            dibujarVertice(tempX1, tempY1); // Y se dibuja el vertice en las coordenadas dadas
            
            // En caso que el poligono se quiera dibujar con sus aristas entonces
            if (conAristas)
            {
                tempX2 = (int) coordenadasX.get((i+1)%numElementos); // Se obtiene la coordenada X del vertice i + 1 
                tempY2 = (int) coordenadasY.get((i+1)%numElementos); // Se obtiene la coordenada Y del vertice i + 1
                
                dibujarArista(tempX1, tempY1, tempX2, tempY2, Color.blue); // Y se dibuja la arista que va desde el vertice i hasta el vertice i + 1
                
                // Nota: La razon por la que se usan modulos es que si se llega al ultimo vertice entonces el siguiente vertice (o el i+1) debe ser el primero
            }
            
            // Una vez se ha dibujado el vertice, se procede a etiquetarlo y para ello se calculan nuevas coordenadas
            // que no deben ser las mismas del vertice pero que tampoco se situen por dentro del poligono. Esto se 
            // consigue sumando y restando de manera arbitraria algunos pixeles con respecto al centro del poligono.
            if (tempX1 > xCentro)
            {
                tempX1 = tempX1 + 10;
            }
            else
            {
                tempX1 = tempX1 - 20;
            }
            
            if (tempY1 < yCentro)
            {
                tempY1 = tempY1 - 10;
            }
            else
            {
                tempY1 = tempY1 + 20;
            }
            
            // Ya con las coordenadas de la etiqueta se procede a dibujarla con algun color que resalte
            dibujarEtiqueta(Integer.toString(i), tempX1, tempY1, Color.red);
        }
        
        // Una vez termino de hacer lo que necesitaba con este metodo, cierro la comunicacion
        AreaDibujo.dispose();
        
        // Y retorno la imagen del poligono como si fuera un icono
        Icon graficaPoligono = new ImageIcon(superficie);
        return graficaPoligono; 
    }
    
    // Metodo para revisar si dos segmentos de linea se intersectan
    public boolean revisarInterseccion(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4)
    {
        return Line2D.linesIntersect(x1,y1,x2,y2,x3,y3,x4,y4);
    }
    
    public Icon retornarLienzo() // METODO TEMPORAL para hacer pruebas [Creo que a la final no lo incluire]
    {
        Icon temp = new ImageIcon(superficie);
        return temp;
    }
}
