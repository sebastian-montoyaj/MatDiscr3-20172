package Tarea2;

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
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

// Clase encargada del dibujado de los poligonos, vertices y aristas
public class Graficador
{
    // ----- Variables -----
    private BufferedImage superficie; // Variable que contendra el area de dibujo en la memoria del computador y que nos permitira realizar dibujos off-sreen (o sea primero en memoria y luego expuestos en alguna interfaz)
    private int anchoSuperficie = 600; // Variable que guarda el ancho de la superficie de dibujo
    private int altoSuperficie = 600; // Variable que guarda el alto de la superficie de dibujo
    
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
    
    // Este metodo es una derivacion del anterior pues la idea de este metodo es dibujar una flecha donde
    // la cola de la misma sera en la primeras coordenadas indicadas en los paramnetros y la cabeza en los
    // siguientes dos. Además se le puede indicar de que color se quiere la flecha y si la punta de la flecha
    // sea rellena (---►) o no (--->).
    public void dibujarFlecha(int xInicio, int yInicio, int xFin, int yFin, Color colorFlecha, boolean rellena)
    {
        Graphics2D AreaDibujo = (Graphics2D) superficie.getGraphics(); // Primero tomo control del area de dibujo con una variable graphics
        AreaDibujo.setStroke(new BasicStroke(2.5f)); // Luego establezco un pincel con un grosor adecuado para dibujar la flecha
        // Y creo varias varibles que necesitare para calcular angulos y coordenadas de la punta de flecha.
        double ang,angSep,deltax,deltay;
        int distancia, p1x,p1y,p2x,p2y;
        
        // Entonces, la idea de dibujar una flecha, no es más que dibujar una linea
        // y en la terminacion de la linea dibujar otras dos lineas a lado y lado.
        // Sinceramente, para la creacion de este metodo me base de:
        // http://mexiconotas2.blogspot.com/2010/08/tutorial-como-dibujar-una-flecha.html
        
        // Como se puede ver las lineas finales (punta) se hacen calculando coordenas adecuadas que me
        // permitan dibujar dos lineas que comienzan en el final de la flecha hasta dos parejas (x,y)
        // pertinentes, por lo que:
        
        AreaDibujo.setColor(colorFlecha); // De inmediato asigno el color con el que se dibujara la flecha
        
        // Luego, las siguientes dos instrucciones son para determinar el diferencial de distancia de la linea 
        // en el eje x y el diferencial de distancia de la misma linea en y
        deltax = xInicio - xFin;
        deltay = -(yInicio - yFin);
        
        ang = Math.atan(deltay/deltax); // Una vez tenemos los diferenciales de la linea en ambos ejes, calculamos el angulo que hace la linea con respecto a la horizontal
            
        if (deltax < 0) // Si el diferecial de la linea en x es negativo entonces
        {
            ang = ang + Math.PI; // Le añadimos al angulo anterior 180 grados
            // Esto no es más que para hacer ajustes en la posicion de la flecha
            // y que no nos quede invertida la punta para cuando la flecha va de 
            // izquierda a derecha
        }
            
        angSep = 30.0; // Luego asignamos la separacion o anchura de la punta
        distancia = 15; // como tambien el largo de la punta
        
        // Las siguientes 4 instrucciones son para conocer esas parejas (x,y) que darian la forma
        // de la punta, en si no voy a entrar en mucho detalle pero en pocas palabras no es más que
        // descomponer vectorialmente tringulos para obtener dichas coordenadas.
        p1x = (int) (xFin + (distancia*Math.cos(ang - Math.toRadians(angSep))) );
        p1y = (int) (yFin - (distancia*Math.sin(ang - Math.toRadians(angSep))) );
        p2x = (int) (xFin + (distancia*Math.cos(ang + Math.toRadians(angSep))) );
        p2y = (int) (yFin - (distancia*Math.sin(ang + Math.toRadians(angSep))) );
        
        dibujarArista(xInicio, yInicio, xFin, yFin, colorFlecha); // Una vez conocidas las coordenadas de control para la punta de flecha, dibujamos como tal la linea propia de la flecha
        
        if (rellena) // Por ultimo, si se indico en los parametros que la punta fuera rellena entonces
        {
            int[] puntosXFlecha = {xFin,p1x,p2x}; // Creo una tripleta de posiciones en x 
            int[] puntosYFlecha = {yFin,p1y,p2y}; // Creo una tripleta de posiciones en y
            AreaDibujo.fillPolygon(puntosXFlecha, puntosYFlecha, 3);  // Y creo un tringulo con las coordenadas especificadas y que seguramente formara la punta de flecha donde le corresponde.
            // El 3 es para indicar que se dibujara un poligono (figura) de 3 lados, si le pongo 5 o 2
            // me saca un error porque el metodo tratara de dibujar una figura de esos lados con tan
            // solo las tres parejas de coordenadas que le estoy indicando.
        }
        else // Sino, simplmente
        {
            AreaDibujo.drawLine(p1x, p1y, xFin, yFin); // Se dibuja una primera linea de punta
            AreaDibujo.drawLine(p2x, p2y, xFin, yFin); // y luego se dibuja la otra parte de linea de punta
        }
        
        AreaDibujo.dispose(); // De igual modo que los otros metodos, liberamos nuestro control del espacio de trabajo
    }
    
    // Este metodo tiene la tarea de dibujar una arista no recta entre dos vertices dados (O sea dibujar como una especie de "[")
    public void dibujarAristaDoblada(int xInicio, int yInicio, int xFin, int yFin, int desfase, Color colorArista)
    {
        // Primero, creo una variable grafica para tener control del area de dibujo
        Graphics2D AreaDibujo = (Graphics2D) superficie.getGraphics();
        
        // Luego establezco un pincel con un grosor adecuado para dibujar la arista
        AreaDibujo.setStroke(new BasicStroke(2.5f));
        
        // Tambien se establece el color indicado para la linea o arista
        AreaDibujo.setColor(colorArista);
        
        // Ahora y dependiendo de como es la ubicacion de los dos vertices se procede a dibujar el codo:
        // 1. Si el vertice final esta verticalmente arriba entonces
        if ((xInicio == xFin) && (yInicio > yFin))
        {
            AreaDibujo.drawLine(xInicio - desfase, yInicio, xFin - desfase, yFin);
            AreaDibujo.drawLine(xInicio - desfase, yInicio, xInicio, yInicio);
            AreaDibujo.drawLine(xFin - desfase, yFin, xFin, yFin);
        }
        
        // 2. Si el vertice final esta a la derecha y arriba entonces
        if ((xInicio < xFin) && (yInicio > yFin))
        {
            AreaDibujo.drawLine(xInicio - desfase, yInicio - desfase, xFin - desfase, yFin - desfase);
            AreaDibujo.drawLine(xInicio - desfase, yInicio - desfase, xInicio, yInicio);
            AreaDibujo.drawLine(xFin - desfase, yFin - desfase, xFin, yFin);
        }
        
        // 3. Si el vertice final esta a la derecha entonces
        if ((xInicio < xFin) && (yInicio == yFin))
        {
            AreaDibujo.drawLine(xInicio, yInicio - desfase, xFin, yFin - desfase);
            AreaDibujo.drawLine(xInicio, yInicio - desfase, xInicio, yInicio);
            AreaDibujo.drawLine(xFin, yFin - desfase, xFin, yFin);
        }
        
        // 4. Si el vertice final esta a la derecha y abajo entonces
        if ((xInicio < xFin) && (yInicio < yFin))
        {
            AreaDibujo.drawLine(xInicio + desfase, yInicio - desfase, xFin + desfase, yFin - desfase);
            AreaDibujo.drawLine(xInicio + desfase, yInicio - desfase, xInicio, yInicio);
            AreaDibujo.drawLine(xFin + desfase, yFin - desfase, xFin, yFin);
        }
        
        // 5. Si el vertice final esta verticalmente abajo entonces
        if ((xInicio == xFin) && (yInicio < yFin))
        {
            AreaDibujo.drawLine(xInicio + desfase, yInicio, xFin + desfase, yFin);
            AreaDibujo.drawLine(xInicio + desfase, yInicio, xInicio, yInicio);
            AreaDibujo.drawLine(xFin + desfase, yFin, xFin, yFin);
        }
        
        // 6. Si el vertice final esta a la izquierda y abajo entonces
        if ((xInicio > xFin) && (yInicio < yFin))
        {
            AreaDibujo.drawLine(xInicio + desfase, yInicio + desfase, xFin + desfase, yFin + desfase);
            AreaDibujo.drawLine(xInicio + desfase, yInicio + desfase, xInicio, yInicio);
            AreaDibujo.drawLine(xFin + desfase, yFin + desfase, xFin, yFin);
        }
        
        // 7. Si el vertice final esta a la izquierda entonces
        if ((xInicio > xFin) && (yInicio == yFin))
        {
            AreaDibujo.drawLine(xInicio, yInicio + desfase, xFin, yFin + desfase);
            AreaDibujo.drawLine(xInicio, yInicio + desfase, xInicio, yInicio);
            AreaDibujo.drawLine(xFin, yFin + desfase, xFin, yFin);
        }
        
        // 8. Si el vertice final esta a la izquierda y arriba entonces
        if ((xInicio > xFin) && (yInicio > yFin))
        {
            AreaDibujo.drawLine(xInicio - desfase, yInicio + desfase, xFin - desfase, yFin + desfase);
            AreaDibujo.drawLine(xInicio - desfase, yInicio + desfase, xInicio, yInicio);
            AreaDibujo.drawLine(xFin - desfase, yFin + desfase, xFin, yFin);
        }
        
        // Una vez termino de hacer lo que necesitaba con este metodo, cierro la comunicacion
        AreaDibujo.dispose();
    }
    
    // Metodo para dibujar un grafo dados las coordenadas de cada uno de sus vertices
    public Icon dibujarGrafo(ArrayList coordenadasX, ArrayList coordenadasY, boolean etiquetarVertices)
    {
        // Primero, se limpia y se crea una variable grafica para tener control del area de dibujo
        limpiar();
        Graphics2D AreaDibujo = (Graphics2D) superficie.getGraphics();
        
        // Luego, creo algunas variables para la iteracion y dibujado de los vertices
        int numElementos = coordenadasX.size();
        int tempX, tempY;
        
        // Ahora, por cada par de coordenadas se hace:
        for (int i = 0; i < numElementos; i++)
        {
            tempX = (int) coordenadasX.get(i); // Se obtiene la coordenada X del vertice i
            tempY = (int) coordenadasY.get(i); // Se obtiene la coordenada Y del vertice i
            
            // Y se dibuja el vertice en las coordenadas dadas
            dibujarVertice(tempX,tempY);
            
            // De manera opcional, si el parametro etiquetarVertices es verdadero entonces
            if (etiquetarVertices)
            {
                dibujarEtiqueta(Integer.toString(i), tempX+5, tempY+15, Color.gray);
            }
        }
        
        // Una vez termino de hacer lo que necesitaba con este metodo, cierro la comunicacion con el area de dibujo en memoria
        AreaDibujo.dispose();
        
        // Y retorno la imagen del grafo como si fuera un icono
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
