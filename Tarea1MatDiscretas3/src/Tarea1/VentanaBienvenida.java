package Tarea1;

/* OJO!!! MUY IMPORTANTE=>>>>
 * Un caso o error muy particular que me paso con este JFrame es que no me aparecia la opción: Design
 * con la cual se puede hacer el diseño de la ventana de manera grafica y no escribiendo codigo.
 * 
 * Esto se debe a que por alguna razon NetBeans solo me creaba el archivo VentanaBienvenida.java,
 * el cual yo pensaba que era lo UNICO que necesitaba para hacer un JFRAME de manera visual, pero
 * buscando en internet enocontre que NetBeans debia crear dos archivos, en mi caso debia generar
 * VentanaBienvenida.java y VentanaBienvenida.form para reconocer que se queria trabajar con el
 * GUI Builder.
 * 
 * Este ultimo no aparece en el navegador del proyecto pero esta adjunto en la carpeta donde se crea 
 * VentanaBienvenida.java y que es absolutamente necesario para que aparezca la opcion design
 * y asi se pueda desarrollar graficamente el JFRAME.
 * 
 * En conclusion, no se debe borrar tal archivo de la carpeta SRC porque aunque no parezca relevante,
 * si lo es.
 */

// Importo algunos paquetes necesarios para la ventana
import Imagenes.PanelSplash;
import com.sun.awt.AWTUtilities;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

// Clase que tiene como objetivo presentar el aplicativo y sus autores
public class VentanaBienvenida extends JFrame implements Runnable
{
    Thread hilo;
    
    public VentanaBienvenida()
    {
        setUndecorated(true); // Quito la barra de titulo, la cual no es apropiada de una pantalla Splash
        // Para quitar la barra de titulo es completamente necesario poner esta instruccion aqui porque
        // si lo hace despues de iniciar (dibujar) los componentes de la ventana, me GENERA un error.
        setType(Type.UTILITY); // Con esta unica instruccion quito la ventana de la barra de tareas
        // De igual modo, la instruccion anterior no puede ir despues de initComponents() porque tambien me genera error
        
        initComponents();
        
        setLocationRelativeTo(null); //Esto hara que nuestra ventana aparezca en el centro de la pantalla cuando se abra
        setResizable(false); // Esto hara que la ventana siempre tenga un tamaño fijo
        
        // Ajustar imagen al tamaño de un JLabel - Java 
        // Vease: http://fagonerx.blogspot.com/2011/04/ajustar-imagen-al-tamano-de-un-jlabel.html
        ImageIcon logoUdea = new ImageIcon(getClass().getResource("/Imagenes/Logo_UDEA.png"));
        Icon logoUdeaEscala = new ImageIcon(logoUdea.getImage().getScaledInstance(jlUDEA.getWidth(), jlUDEA.getHeight(), Image.SCALE_DEFAULT));
        jlUDEA.setIcon(logoUdeaEscala);
        this.repaint();
        
        PanelSplash panelConFondo = new PanelSplash(); // Instancia el panel que usare para esta pantalla
        add(panelConFondo); // Aqui simplemente agrego el Jpanel el cual contiene la imagen de fondo y que se ubicara detras de los otros controles
        
        // Las siguientes instrucciones son para darle una forma más amena y no tan cuadriculada.
        // Tenga presente que estas instrucciones SIEMPRE deben ir luego de initComponents().
        
        Shape formaVentana = new RoundRectangle2D.Double(0, 0, this.getBounds().width, this.getBounds().height, 200, 200);
        /* Los dos primeros parametros son la posición de la ventana que será dada a esta forma
         * Los otros dos intermedios seran el limite de la forma, en este caso le estamos dando los mismos límites que el jFrame
         * Los últimos dos parametros representan la curvatura del borde redondeado
         */
        AWTUtilities.setWindowShape(this, formaVentana); // Finalmente, con esto establecemos la forma de la ventana
    }
    
    @Override
    public void run()
    {
        try // Encierro en un try-catch porque es un requerimiento a la hora de usar hilos
        {
            setVisible(true); // Hago visible la ventana
            hilo.sleep(5000); // Esto me permite que se visualice la ventana splash por 5000 milisegundos (5 segundos)
            
            // Ahora para diferenciar este proyecto quiero que se desvanezca la pantalla de presentacion
            // despues de 5 segundos de haberse aparecido la misma.
            // Para hacerlo me base en: https://youtu.be/1vCX7vtrORQ pero no hice exactemente lo mismo,
            // a mi forma de ver es más simple lo que yo hice. Entonces para dar ese efecto hice lo siguiente:
            
            float aux = 1; // Creo una variable aux que comienza en 1 (osea no hay tranparencia en la ventana)
            for (int i =1; i<100; i++) //Luego, hago un ciclo que comienza en 1 e itera 100 veces
            {
                aux = aux - 0.01F; // Ahora, aqui ire restando de 0.01 a la opacidad de la ventana (esto incrementara la transparencia de la pantalla)
                // Tenga cuidado que en ningun punto del ciclo la opacidad llegue a ser menor de 0 PORQUE GENERA UN ERROR
                // para ello, verifique que lo que le resta a la opacidad por el numero de ciclos no pase esa condición.
                // Por ejemplo: 0.01*100 = 1 y por tanto en la iteracion 100, la opacidad llega a 0.
                // Si quiere aumente el 0.01 a 0.02 para que vea como se presenta un error.
                this.setOpacity(aux); //Una vez calculada el nivel de opacidad la establezco en la propiedad de la pantalla
                hilo.sleep(20); // Pongo un hilo a correr 20 milisegundos para que los ciclos no se ejecuten de una. Osea que haya una pausita.
            }
            
            this.dispose(); // Cierro la ventana una vez esta se ha vuelto transparente y muestro la que sigue.
            
            VentanaPrincipal ventanaPrimaria = new VentanaPrincipal(); // Ahora, creo una instancia de la ventana principal
            ventanaPrimaria.setVisible(true); // Y muestro ya la ventana principal o de trabajo
        }
        catch (InterruptedException error)
        {
            System.out.println("Problema: " + error); // En caso de algun error que lo imprima por consola
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlUDEA = new javax.swing.JLabel();
        jlTitulo = new javax.swing.JLabel();
        jlVersion = new javax.swing.JLabel();
        jlIntegrantes = new javax.swing.JLabel();
        jlI1 = new javax.swing.JLabel();
        jlI2 = new javax.swing.JLabel();
        jlI3 = new javax.swing.JLabel();
        jlI4 = new javax.swing.JLabel();
        jlDerechos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 500));
        setPreferredSize(new java.awt.Dimension(800, 500));
        setResizable(false);

        jlUDEA.setText("jLabel1");

        jlTitulo.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jlTitulo.setText("Tarea 1 - Matemáticas Discretas III");

        jlVersion.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jlVersion.setText("Versión 1.0");

        jlIntegrantes.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jlIntegrantes.setText("Integrantes:");

        jlI1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jlI1.setText("Daniel Peláez Pineda");

        jlI2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jlI2.setText("Esteban Restrepo Santamaría");

        jlI3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jlI3.setText("Juan Sebastián Peláez Villa");

        jlI4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jlI4.setText("Sebastián Montoya Jiménez");

        jlDerechos.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jlDerechos.setText("Todos los derechos reservados. 2017 ©");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(92, Short.MAX_VALUE)
                .addComponent(jlUDEA, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlDerechos)
                    .addComponent(jlTitulo)
                    .addComponent(jlVersion)
                    .addComponent(jlIntegrantes)
                    .addComponent(jlI1)
                    .addComponent(jlI2)
                    .addComponent(jlI3)
                    .addComponent(jlI4))
                .addGap(91, 91, 91))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlUDEA, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlVersion, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jlIntegrantes, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlI1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jlI2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlI3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlI4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlDerechos, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(100, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jlDerechos;
    private javax.swing.JLabel jlI1;
    private javax.swing.JLabel jlI2;
    private javax.swing.JLabel jlI3;
    private javax.swing.JLabel jlI4;
    private javax.swing.JLabel jlIntegrantes;
    private javax.swing.JLabel jlTitulo;
    private javax.swing.JLabel jlUDEA;
    private javax.swing.JLabel jlVersion;
    // End of variables declaration//GEN-END:variables
}
