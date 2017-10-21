package Tarea2;

// Importes necesarios para la clase
import Imagenes.PanelSplash;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

/* Nota:
 * Mucha de las instrucciones que usamos para dar forma a esta ventana se encuentran en:
 *
 * Ventanas java personalizadas al máximo (facil)
 * http://www.taringa.net/post/hazlo-tu-mismo/16282931/Ventanas-java-personalizadas-al-maximo-facil.html
 * 
 * y para el efecto de aparicion y desaparicion use:
 * La manera mas fácil de crear un Splash en Java
 * https://richieblog.wordpress.com/2008/10/14/la-manera-mas-facil-de-crear-un-splash-en-java/
 * 
 * Make a JFrame not visible in the the TaskBar [Para que esta ventana no aparezca en la barra de tareas]
 * http://www.rgagnon.com/javadetails/java-make-a-frame-not-visible-in-the-taskbar.html
 * 
 * Por otra parte, el codigo relevante de esta pantalla se encuentra en el CONSTRUCTOR, en run()
 * y en la clase PanelSplash, lo demas que se encuentra aqui es codigo generado por la IDE de netbeans
 */

public class VentanaBienvenida extends JFrame implements Runnable
{
    Thread hilo;
    
    public VentanaBienvenida()
    {
        setUndecorated(true); // Se quita la barra de titulo, la cual no es apropiada de una pantalla Splash
        // Para quitar la barra de titulo es completamente necesario poner esta instruccion aqui porque
        // si lo hace despues de iniciar (dibujar) los componentes de la ventana, me GENERA un error.
        
        setType(Type.UTILITY); // Con esta unica instruccion se quita la ventana de la barra de tareas
        // De igual modo, la instruccion anterior no puede ir despues de initComponents() porque tambien genera error
        
        initComponents();
        setLocationRelativeTo(null); // Esto hara que nuestra ventana aparezca en el centro de la pantalla cuando se abra
        setResizable(false); // Esto hara que la ventana siempre tenga un tamaño fijo
        
        // Ajustar imagen al tamaño de un JLabel - Java 
        // Vease: http://fagonerx.blogspot.com/2011/04/ajustar-imagen-al-tamano-de-un-jlabel.html
        ImageIcon logoUdea = new ImageIcon(getClass().getResource("/Imagenes/Logo_UDEA.png"));
        Icon logoUdeaEscala = new ImageIcon(logoUdea.getImage().getScaledInstance(jlUDEA.getWidth(), jlUDEA.getHeight(), Image.SCALE_DEFAULT));
        jlUDEA.setIcon(logoUdeaEscala);
        this.repaint();
        
        PanelSplash panelConFondo = new PanelSplash(); // Instancia el panel que usaremos para esta pantalla
        add(panelConFondo); // Aqui simplemente se agrega el Jpanel el cual contiene la imagen de fondo y que se ubicara detras de los otros controles
        
        // Las siguientes instrucciones son para darle una forma más amena y no tan cuadriculada.
        // Tenga presente que estas instrucciones SIEMPRE deben ir luego de initComponents().
        
        Shape formaVentana = new RoundRectangle2D.Double(0, 0, this.getBounds().width, this.getBounds().height, 50, 50);
        /* Los dos primeros parametros son la posición en la ventana que será dada a esta forma
         * Los otros dos intermedios seran el limite de la forma, en este caso le estamos dando los mismos límites que el jFrame
         * Los últimos dos parametros representan la curvatura del borde redondeado
         */
        this.setShape(formaVentana); // Finalmente, con esto establecemos la forma de la ventana
    }
    
    @Override
    public void run()
    {
        try // Se encierra el siguiente codigo en un try-catch porque es un requerimiento a la hora de usar hilos
        {
            setVisible(true); // Se hace visible la ventana de bienvenida
            hilo.sleep(5000); // Con esto se indica que se visualice la ventana splash por 5000 milisegundos (5 segundos)
            
            // Ahora para diferenciar este proyecto haremos que la misma se desvanezca despues de 5 segundos de haberse aparecido la misma.
            // Para hacerlo nos basamos de: https://youtu.be/1vCX7vtrORQ
            
            float aux = 1; // Por lo que se crea una variable aux que comienza en 1 (osea no hay tranparencia en la ventana)
            for (int i =1; i<100; i++) // Luego, se hace un ciclo que comienza en 1 e itera 100 veces
            {
                aux = aux - 0.01F; // Ahora, aqui se ira restando de 0.01 a la opacidad de la ventana (esto incrementara la transparencia de la pantalla)
                // Tenga cuidado que en ningun punto del ciclo la opacidad llegue a ser menor de 0 PORQUE GENERA UN ERROR
                // para ello, verifique que lo que le resta a la opacidad por el numero de ciclos no pase esa condición.
                // Por ejemplo: 0.01*100 = 1 y por tanto en la iteracion 100, la opacidad llega a 0.
                // Si quiere aumente el 0.01 a 0.02 para que vea como se presenta un error.
                this.setOpacity(aux); // Una vez calculada el nivel de opacidad la establezco en la propiedad de la pantalla
                hilo.sleep(20); // Se pone un hilo a correr 20 milisegundos para que los ciclos no se ejecuten de una. Osea que haya una pausita.
            }
            
            this.dispose(); // Y se cierra la ventana transcurrido el tiempo
            
            VentanaPrincipal ventanaPrimaria = new VentanaPrincipal(); // Se crea una instancia de la ventana principal
            ventanaPrimaria.setVisible(true); // Y por ultimo se muestra ya la ventana que interctua con el usuario
        }
        catch (InterruptedException error)
        {
            System.out.println("Problema: " + error); // En caso de algun error se imprime tal error por consola
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlUDEA = new javax.swing.JLabel();
        jlTitulo = new javax.swing.JLabel();
        jlDerechos = new javax.swing.JLabel();
        jlIntegrantes = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jlUDEA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jlTitulo.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jlTitulo.setText("<html>Tarea 2<br>Matemáticas Discretas III</html>");

        jlDerechos.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jlDerechos.setText("Todos los derechos reservados. 2017 ©");
        jlDerechos.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jlIntegrantes.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        jlIntegrantes.setText("<html>Integrantes:<br>\nAndrés Mauricio Álvarez Ortiz<br>\nFrancisco Javier Silva Cadavid<br>\nSebastián Montoya Jiménez\n</html>");
        jlIntegrantes.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jlUDEA, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlIntegrantes, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlDerechos, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jlIntegrantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(jlDerechos, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jlUDEA, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jlDerechos;
    private javax.swing.JLabel jlIntegrantes;
    private javax.swing.JLabel jlTitulo;
    private javax.swing.JLabel jlUDEA;
    // End of variables declaration//GEN-END:variables
}
