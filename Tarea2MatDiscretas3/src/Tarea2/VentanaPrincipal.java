package Tarea2;

// Se importan los paquetes que necesita la clase
import Imagenes.PanelPrincipal;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

// Ventana principal que interactua con el usuario
public class VentanaPrincipal extends JFrame
{
    // Variable para realizar el dibujado del grafo y los circuitos posibles
    Graficador dibujo = new Graficador();
    
    // Variables para almacenar las coordenadas de los vertices del grafo
    ArrayList XVertices = new ArrayList();
    ArrayList YVertices = new ArrayList();
    
    // Variable para realizar el analisis de los circuitos hamiltonianos
    AnalizadorHamiltoniano analizadorCiclos;
    
    // Variable para almacenar los circuitos encontrados
    List<int[]> ciclosHamiltonianos = new ArrayList();
    
    // Variable para guardar una etiqueta o representacion simbolica de los circuitos encontrados y que se mostraran en un Jlist
    DefaultListModel<String> listaCiclosHImpresa = new  DefaultListModel();
    
    // Constructor de la clase o ventana. Su proposito no va mas de lo estetico o para inicializacion de variables
    public VentanaPrincipal()
    {
        initComponents();
        
        // Se establece el icono de la ventana
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
        
        // Se le indica tambien al mostrador de circuitos que haga ajuste linea de forma automatica
        jtaCircuitoElegido.setLineWrap(true);
        
        // Y se procede a dibujar de forma inicial el grafo minimo con el que puede trabajar este aplicativo
        construirGrafo();
    }
    
    // Metodo para borrar o reiniciar los estados de los componentes de la ventana
    public void limpiarEstadoAnalisis()
    {
        ciclosHamiltonianos.clear();
        listaCiclosHImpresa.clear();
        jlListaCircuitos.setModel(listaCiclosHImpresa);
        jtfTotalCircuitos.setText("");
        jtaCircuitoElegido.setText("");
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
        int distanciaEjeHorizontal = ancho/(zn + 1);
        int distanciaEjeVertical = alto/(zm + 1);
        
        // Antes de computar las coordenadas exactas de cada vertice se reinician los vectores de coordenadas para que no se acumulen datos viejos con nuevos
        XVertices.clear();
        YVertices.clear();
        
        // A continuacion, se prosigue a calcular y almacenar cada una de las coordenadas de los vertices
        int coordenadaX, coordenadaY;
        for (int i = 0; i < zm; i++)
        {
            coordenadaY = distanciaEjeVertical*(i+1);
            for (int j = 0; j < zn; j++)
            {
                coordenadaX = distanciaEjeHorizontal*(j+1);
                XVertices.add(coordenadaX);
                YVertices.add(coordenadaY);
            }
        }
        
        // Para terminar simplemente se hace lo siguiente:
        
        // Se invoca el metodo de dibujado de grafos y se establece dicha imagen en el area de dibujo
        Icon imagenGrafo = dibujo.dibujarGrafo(XVertices, YVertices, true);
        jlAreaDibujo.setIcon(imagenGrafo);
        
        // Se crea la representacion matricial (matriz de adyacencia) del grafo
        // NOTA1: Recuerde!!! Esta matriz es cuadrada porque la idea es describir como es la conexion de todos los vertices contra todos los vertices
        // NOTA2: Un 0 representa: NO ARISTA y 1 representa: ARISTA
        int[][] grafo = new int[zm*zn][zm*zn];
        for (int i=0; i < grafo.length; i++)
        {
            for (int j=0; j < grafo[0].length; j++)
            {
                if (j!=i)
                {
                    grafo[i][j] = 1;
                }
                else
                {
                    grafo[i][j] = 0;
                }
            }
        }
        
        // Y se crea un objeto analizador de grafos
        analizadorCiclos = new AnalizadorHamiltoniano(grafo);
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
        jtfTotalCircuitos = new javax.swing.JTextField();
        jtbDibujarCircuito = new javax.swing.JToggleButton();
        jspListaCircuitos = new javax.swing.JScrollPane();
        jlListaCircuitos = new javax.swing.JList<>();
        jspCircuitoElegido = new javax.swing.JScrollPane();
        jtaCircuitoElegido = new javax.swing.JTextArea();
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
        jtbAnalizar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/analizar02.png"))); // NOI18N
        jtbAnalizar.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/analizar02.png"))); // NOI18N
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
                .addGap(15, 15, 15)
                .addComponent(jtbAnalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jl3)
                .addContainerGap())
        );
        jpOpcionesLayout.setVerticalGroup(
            jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpOpcionesLayout.createSequentialGroup()
                .addGroup(jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpOpcionesLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl1)
                            .addComponent(jsZ1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl2)
                            .addComponent(jsZ2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpOpcionesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jtbAnalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jl3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpResultado.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Resultado", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Comic Sans MS", 1, 24))); // NOI18N
        jpResultado.setOpaque(false);

        jl4.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        jl4.setText("Posibles Circuitos Hamiltonianos:");

        jl5.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        jl5.setText("Dibujar");

        jtfTotalCircuitos.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jtfTotalCircuitos.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jtfTotalCircuitos.setEnabled(false);

        jtbDibujarCircuito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/dibujar01.png"))); // NOI18N
        jtbDibujarCircuito.setBorderPainted(false);
        jtbDibujarCircuito.setContentAreaFilled(false);
        jtbDibujarCircuito.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/dibujar02.png"))); // NOI18N
        jtbDibujarCircuito.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/dibujar02.png"))); // NOI18N
        jtbDibujarCircuito.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jtbDibujarCircuitoItemStateChanged(evt);
            }
        });

        jlListaCircuitos.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        jlListaCircuitos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jspListaCircuitos.setViewportView(jlListaCircuitos);

        jtaCircuitoElegido.setColumns(20);
        jtaCircuitoElegido.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        jtaCircuitoElegido.setRows(5);
        jspCircuitoElegido.setViewportView(jtaCircuitoElegido);

        javax.swing.GroupLayout jpResultadoLayout = new javax.swing.GroupLayout(jpResultado);
        jpResultado.setLayout(jpResultadoLayout);
        jpResultadoLayout.setHorizontalGroup(
            jpResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpResultadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspCircuitoElegido)
                    .addComponent(jtfTotalCircuitos)
                    .addComponent(jl4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpResultadoLayout.createSequentialGroup()
                        .addComponent(jspListaCircuitos, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jtbDibujarCircuito, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jl5))))
                .addContainerGap())
        );
        jpResultadoLayout.setVerticalGroup(
            jpResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpResultadoLayout.createSequentialGroup()
                .addComponent(jl4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfTotalCircuitos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jpResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpResultadoLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jl5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtbDibujarCircuito, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpResultadoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jspListaCircuitos, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspCircuitoElegido)
                .addContainerGap())
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlAreaDibujo, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlAreaDibujo, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Si cambia el valor de Zm entonces se redibuja el grafo
    private void jsZ1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jsZ1StateChanged
        construirGrafo();
        limpiarEstadoAnalisis();
    }//GEN-LAST:event_jsZ1StateChanged

    // De igual modo, si cambia el valor de Zn vuelve y se redibuja el grafo
    private void jsZ2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jsZ2StateChanged
        construirGrafo();
        limpiarEstadoAnalisis();
    }//GEN-LAST:event_jsZ2StateChanged

    // Evento para realizar la busqueda de los circuitos
    private void jtbAnalizarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jtbAnalizarItemStateChanged
        analizadorCiclos.comenzarAnalisis(); // En primer lugar se procede a invocar el metodo que analiza el grafo en busca de los circuitos
        
        // Una vez termina el analisis se muestran en total cuantos fueron los ciclos encontrados
        jtfTotalCircuitos.setText(Integer.toString(analizadorCiclos.obtenerNumeroCiclosHEncontrados()));
        
        // Luego se procede a rellenar la lista visual de circuitos con etiquetas simples que representen cada uno de las soluciones
        listaCiclosHImpresa.clear();
        for(int i = 0; i < analizadorCiclos.obtenerNumeroCiclosHEncontrados(); i++)
        {
            listaCiclosHImpresa.addElement("Circuito " + (i+1));
        }
        jlListaCircuitos.setModel(listaCiclosHImpresa);
        
        // Y se recuperan las listas de soluciones para manejarlas dentro del programa
        ciclosHamiltonianos = analizadorCiclos.obtenerListaCiclosHEncontrados();
    }//GEN-LAST:event_jtbAnalizarItemStateChanged

    // Evento para dibujar en pantalla el circuito especifico que quiere ver el usuario
    private void jtbDibujarCircuitoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jtbDibujarCircuitoItemStateChanged
        // Primero se recupera el indice o circuito seleccionado por el usuario en la lista de etiquetas
        int circuitoElegido = jlListaCircuitos.getSelectedIndex();
        
        // Si hay soluciones encontradas Y el usuario eleigio alguna de ellas entonces
        if ((ciclosHamiltonianos.size() > 0) && (circuitoElegido > -1))
        {
            // Se dibuja nuevamente la malla de vertices
            dibujo.dibujarGrafo(XVertices, YVertices, true);
            
            // Ahora, se crea un hilo en el cual se desarrollara la logica para dibujar tramo por tramo el circuito elegido
            // Esto es necesario porque si no aparto el siguiente segmento de codigo del evento de este boton, 
            // cuando ejecute la instruccion Thread.sleep(2000); se bloqueara la interfaz del aplicativo y no dejara
            // ver el dibujado tramo por tramo. Por tanto:
            Thread hiloDibujo = new Thread()
            {
                @Override
                public void run()
                {
                    // Recupero el recorrido especifico del circuito
                    int[] tempCiclo = ciclosHamiltonianos.get(circuitoElegido);
                    
                    // Imprimo un encabezado de que circuito se mostrara en el campo de ruta
                    jtaCircuitoElegido.setText("Circuito " + circuitoElegido + " :\n");
                    
                    // Luego, creo algunas variables para pasar por todos los vertices del circuito y recuperar sus coordenadas
                    int aux, cIniX, cIniY, cFinX, cFinY;
                    
                    // Tambien, creo una variable para refrescar el lienzo de dibujo
                    Icon instantaneaGrafo;
                    
                    // A continuacion se empezara a imprimir y dibujar el circuito por lo que:
                    
                    // 1ero - Se imprime cual es el vertice origen
                    jtaCircuitoElegido.setText(jtaCircuitoElegido.getText() + Integer.toString(tempCiclo[0]) + "->");
                    
                    // 2do - Mientras haya n-1 vertices por recorrer HAGA:
                    for (int i=0; i < tempCiclo.length-1; i++)
                    {
                        // Recupero las coordenadas del vertice i
                        aux = tempCiclo[i];
                        cIniX = (int) XVertices.get(aux);
                        cIniY = (int) YVertices.get(aux);
                        
                        // Recupero las coordenadas del vertice i+1
                        aux = tempCiclo[i+1];
                        cFinX = (int) XVertices.get(aux);
                        cFinY = (int) YVertices.get(aux);
                        
                        // Imprimo, el vertice i+1 o que es punta del trazado que se esta haciendo
                        jtaCircuitoElegido.setText(jtaCircuitoElegido.getText() + Integer.toString(aux) + "->");
                        
                        // De igual modo, dibujo una flecha entre los vertices i e i+1 y refresco al area de dibujo
                        dibujo.dibujarFlecha(cIniX, cIniY, cFinX, cFinY, Color.blue, false);
                        instantaneaGrafo = dibujo.retornarLienzo();
                        jlAreaDibujo.setIcon(instantaneaGrafo);
                        
                        // Y antes de pasar a la siguiente iteracion espero 2 segundos
                        try {Thread.sleep(2000);} catch (InterruptedException ex) {}
                    }
                    
                    // 3ero - Una vez sale del ciclo anterior, recupero las coordenadas del ultimo y primer vertice
                    aux = tempCiclo[tempCiclo.length-1];
                    cIniX = (int) XVertices.get(aux);
                    cIniY = (int) YVertices.get(aux);
                    aux = tempCiclo[0];
                    cFinX = (int) XVertices.get(aux);
                    cFinY = (int) YVertices.get(aux);
                    
                    // 4to - Imprimo y dibujo el ultimo tramo del circuito
                    jtaCircuitoElegido.setText(jtaCircuitoElegido.getText() + Integer.toString(aux));
                    dibujo.dibujarFlecha(cIniX, cIniY, cFinX, cFinY, Color.blue, false);
                    instantaneaGrafo = dibujo.retornarLienzo();
                    jlAreaDibujo.setIcon(instantaneaGrafo);
                }
            };
            
            // Finalmente, ponemos en marcha le proceso de dibujado del circuito
            hiloDibujo.start();
        }
    }//GEN-LAST:event_jtbDibujarCircuitoItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jl1;
    private javax.swing.JLabel jl2;
    private javax.swing.JLabel jl3;
    private javax.swing.JLabel jl4;
    private javax.swing.JLabel jl5;
    private javax.swing.JLabel jlAreaDibujo;
    private javax.swing.JList<String> jlListaCircuitos;
    private javax.swing.JPanel jpOpciones;
    private javax.swing.JPanel jpResultado;
    private javax.swing.JSpinner jsZ1;
    private javax.swing.JSpinner jsZ2;
    private javax.swing.JScrollPane jspCircuitoElegido;
    private javax.swing.JScrollPane jspListaCircuitos;
    private javax.swing.JTextArea jtaCircuitoElegido;
    private javax.swing.JToggleButton jtbAnalizar;
    private javax.swing.JToggleButton jtbDibujarCircuito;
    private javax.swing.JTextField jtfTotalCircuitos;
    // End of variables declaration//GEN-END:variables
}
