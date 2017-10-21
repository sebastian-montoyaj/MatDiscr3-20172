package Tarea2;

// Se importan los paquetes que necesita la clase
import Imagenes.PanelPrincipal;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.*;

// Ventana principal que interactua con el usuario
public class VentanaPrincipal extends JFrame
{
    // Variable para realizar el dibujado del grafo y los circuitos posibles
    Graficador dibujo = new Graficador();
    
    // Variables para almacenar las coordenadas de los vertices del grafo
    ArrayList XVertices = new ArrayList();
    ArrayList YVertices = new ArrayList();
    
    
    
    // Constructor de la clase o ventana. Su proposito no va mas de lo estetico o para inicializacion de variables
    public VentanaPrincipal()
    {
        initComponents();
        
        // Se establece el icomo de la ventana
        Image icono = new ImageIcon(getClass().getResource("/Imagenes/icono.png")).getImage();
        setIconImage(icono);
        
        // Se define el titulo de la ventana
        setTitle("Tarea 2 - Matemáticas Discretas III");
        
        // Se pone la ventana en el centro de la pantalla cuando esta se visualice
        setLocationRelativeTo(null);
        
        // Se agrega un fondo a esta ventana
        PanelPrincipal panelConFondo = new PanelPrincipal();
        add(panelConFondo);
        
        // Se establece el comportamiento de los campos controladores del numero de vertices
        jsZ1.setModel(new SpinnerNumberModel(2, 2, 10, 1));
        jsZ2.setModel(new SpinnerNumberModel(2, 2, 10, 1));
        // How to limit JSpinner
        // https://stackoverflow.com/questions/15880844/how-to-limit-jspinner
        
        // Y se procede a dibujar de forma inicial el grafo minimo con el que puede trabajar este aplicativo
        construirGrafo();
    }
    
    // Metodo para calcular y dibujar la malla de vertices formada por la operacion entre grupos Zm🕀Zn
    public void construirGrafo()
    {       
        // En primer lugar, obtengo las dimensiones del area de dibujo para saber como puedo distribuir los vertices
        int ancho = jlAreaDibujo.getWidth();
        int alto = jlAreaDibujo.getHeight();
        
        // Ahora, recupero zm y zn (o sea los grupos) para saber como armar la rejilla de vertices o grafo
        int zm = (int) jsZ1.getValue();
        int zn = (int) jsZ2.getValue();
        
        // Luego, realizo un calculo de cuanto espacio debo dejar a lo horizontal y a lo vertical entre cada vertice
        int distanciaEjeHorizontal = ancho/(zm + 1);
        int distanciaEjeVertical = alto/(zn + 1);
        
        // Antes de computar las coordenadas exactas de cada vertice se reinician los vectores de coordenadas para que no se acumulen datos viejos con nuevos
        XVertices.clear();
        YVertices.clear();
        
        // A continuacion, se prosigue a calcular y almacenar cada una de las coordenadas de los vertices
        int coordenadaX, coordenadaY;
        for (int i = 0; i < zm; i++)
        {
            coordenadaX = distanciaEjeHorizontal*(i+1);
            for (int j = 0; j < zn; j++)
            {
                coordenadaY = distanciaEjeVertical*(j+1);
                XVertices.add(coordenadaX);
                YVertices.add(coordenadaY);
            }
        }
        
        // Al terminar simplemente se invoca el metodo de dibujado de grafos y se establece dicha imagen en el area de dibujo
        Icon imagenGrafo = dibujo.dibujarGrafo(XVertices, YVertices);
        jlAreaDibujo.setIcon(imagenGrafo);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpOpciones = new javax.swing.JPanel();
        jl1 = new javax.swing.JLabel();
        jl2 = new javax.swing.JLabel();
        jl3 = new javax.swing.JLabel();
        jsZ1 = new javax.swing.JSpinner();
        jsZ2 = new javax.swing.JSpinner();
        jtbAnalizar = new javax.swing.JToggleButton();
        jpResultado = new javax.swing.JPanel();
        jl4 = new javax.swing.JLabel();
        jl5 = new javax.swing.JLabel();
        jl6 = new javax.swing.JLabel();
        jtfTotalCircuitos = new javax.swing.JTextField();
        jtfCircuitoADibujar = new javax.swing.JTextField();
        jtbDibujarCircuito = new javax.swing.JToggleButton();
        jlAreaDibujo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jpOpciones.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Opciones", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Comic Sans MS", 1, 24))); // NOI18N
        jpOpciones.setOpaque(false);

        jl1.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        jl1.setText("Z:");

        jl2.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        jl2.setText("Z:");

        jl3.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        jl3.setText("Analizar");

        jsZ1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jsZ1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jsZ1StateChanged(evt);
            }
        });

        jsZ2.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jsZ2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jsZ2StateChanged(evt);
            }
        });

        jtbAnalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/analizar01.png"))); // NOI18N
        jtbAnalizar.setBorderPainted(false);
        jtbAnalizar.setContentAreaFilled(false);
        jtbAnalizar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/analizar02.png"))); // NOI18N
        jtbAnalizar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jtbAnalizarItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jpOpcionesLayout = new javax.swing.GroupLayout(jpOpciones);
        jpOpciones.setLayout(jpOpcionesLayout);
        jpOpcionesLayout.setHorizontalGroup(
            jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpOpcionesLayout.createSequentialGroup()
                        .addComponent(jl1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jsZ1))
                    .addGroup(jpOpcionesLayout.createSequentialGroup()
                        .addComponent(jl2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jsZ2)))
                .addContainerGap())
            .addGroup(jpOpcionesLayout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(jtbAnalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jl3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpOpcionesLayout.setVerticalGroup(
            jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpOpcionesLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl1)
                    .addComponent(jsZ1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl2)
                    .addComponent(jsZ2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jtbAnalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jl3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jpResultado.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Resultado", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Comic Sans MS", 1, 24))); // NOI18N
        jpResultado.setOpaque(false);

        jl4.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        jl4.setText("Posibles Circuitos Hamiltonianos:");

        jl5.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        jl5.setText("Ver Circuito:");

        jl6.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        jl6.setText("Dibujar");

        jtfTotalCircuitos.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jtfTotalCircuitos.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jtfTotalCircuitos.setEnabled(false);

        jtfCircuitoADibujar.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jtfCircuitoADibujar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfCircuitoADibujarKeyTyped(evt);
            }
        });

        jtbDibujarCircuito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/dibujar01.png"))); // NOI18N
        jtbDibujarCircuito.setBorderPainted(false);
        jtbDibujarCircuito.setContentAreaFilled(false);
        jtbDibujarCircuito.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/dibujar02.png"))); // NOI18N
        jtbDibujarCircuito.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jtbDibujarCircuitoItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jpResultadoLayout = new javax.swing.GroupLayout(jpResultado);
        jpResultado.setLayout(jpResultadoLayout);
        jpResultadoLayout.setHorizontalGroup(
            jpResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpResultadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfTotalCircuitos)
                    .addGroup(jpResultadoLayout.createSequentialGroup()
                        .addComponent(jl4)
                        .addGap(0, 72, Short.MAX_VALUE))
                    .addGroup(jpResultadoLayout.createSequentialGroup()
                        .addComponent(jl5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtfCircuitoADibujar)))
                .addContainerGap())
            .addGroup(jpResultadoLayout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(jtbDibujarCircuito, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jl6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpResultadoLayout.setVerticalGroup(
            jpResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpResultadoLayout.createSequentialGroup()
                .addComponent(jl4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfTotalCircuitos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jpResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl5)
                    .addComponent(jtfCircuitoADibujar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtbDibujarCircuito, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jl6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 14, Short.MAX_VALUE))
        );

        jlAreaDibujo.setBackground(new java.awt.Color(255, 255, 255));
        jlAreaDibujo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jlAreaDibujo.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jlAreaDibujo, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlAreaDibujo, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Si cambia el valor de Zm entonces se redibuja el grafo
    private void jsZ1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jsZ1StateChanged
        construirGrafo();
    }//GEN-LAST:event_jsZ1StateChanged

    // De igual modo, si cambia el valor de Zn vuelve y se redibuja el grafo
    private void jsZ2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jsZ2StateChanged
        construirGrafo();
    }//GEN-LAST:event_jsZ2StateChanged

    // 
    private void jtbAnalizarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jtbAnalizarItemStateChanged
        
    }//GEN-LAST:event_jtbAnalizarItemStateChanged

    // 
    private void jtfCircuitoADibujarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfCircuitoADibujarKeyTyped
        
    }//GEN-LAST:event_jtfCircuitoADibujarKeyTyped

    // 
    private void jtbDibujarCircuitoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jtbDibujarCircuitoItemStateChanged
        
    }//GEN-LAST:event_jtbDibujarCircuitoItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jl1;
    private javax.swing.JLabel jl2;
    private javax.swing.JLabel jl3;
    private javax.swing.JLabel jl4;
    private javax.swing.JLabel jl5;
    private javax.swing.JLabel jl6;
    private javax.swing.JLabel jlAreaDibujo;
    private javax.swing.JPanel jpOpciones;
    private javax.swing.JPanel jpResultado;
    private javax.swing.JSpinner jsZ1;
    private javax.swing.JSpinner jsZ2;
    private javax.swing.JToggleButton jtbAnalizar;
    private javax.swing.JToggleButton jtbDibujarCircuito;
    private javax.swing.JTextField jtfCircuitoADibujar;
    private javax.swing.JTextField jtfTotalCircuitos;
    // End of variables declaration//GEN-END:variables
}
