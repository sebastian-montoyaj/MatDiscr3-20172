package Tarea1;

// Importo los paquetes que considero necesarios
import Imagenes.PanelPrincipal;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

// Ventana principal de la tarea No.1 para la materia de Matematicas Discretas III
public class VentanaPrincipal extends JFrame
{
    // Variable para realizar el dibujado del poligono y las soluciones validas del ejercicio 4
    Graficador dibujo = new Graficador();
    
    // Variables para alamcenar las coordenadas de los vertices del poligono de estudio
    ArrayList XVertices = new ArrayList();
    ArrayList YVertices = new ArrayList();
    
    ArrayList<List> listaFormas = new ArrayList();
    
    DefaultListModel<String> listaFormasImpresa = new  DefaultListModel();
    
    // Variable para saber que forma se quiere dibujar
    int indiceSeleccionado;
    
    public VentanaPrincipal() // Constructor de la clase o ventana
    {
        initComponents();
        
        Image icono = new ImageIcon(getClass().getResource("/Imagenes/icono.png")).getImage(); // Guardo el icono en una variable
        setIconImage(icono); // Establezco el icono de la ventana principal
        
        setTitle("Tarea 1 - Ejercicio 4 ~ Matematicas Discretas III"); // También pongo el titulo del programa
        
        setLocationRelativeTo(null); // Pongo la ventana en el centro de la pantalla cuando se visualice
        
        PanelPrincipal panelConFondo = new PanelPrincipal(); // Instancio el panel que usare para esta pantalla
        add(panelConFondo); // Agrego el Jpanel con la imagen de fondo y que se ubicara detras de los otros componentes
    }
    
    // Metodo para borrar o reiniciar los estados de los componentes de la ventana
    public void limpiarPantalla()
    {
        // Limpio la lista y el indice seleccionado de la lista
        listaFormas.clear();
        listaFormasImpresa.clear();
        jlFormas.setModel(listaFormasImpresa);
        jlFormas.clearSelection();
        indiceSeleccionado = -1;
        
        jtNumVectores.setText(""); // Limpio el campo de numero de vectores
        
        // Reinicio las variables con los vectores de coordenadas para los vertices
        XVertices.clear();
        YVertices.clear();
        
        // Y limpio el area de dibujo
        jlAreaDibujo.setIcon(null);
        dibujo.limpiar();
    }
    
    /**
     * Realiza el Proceso de Combinatoria
     *
     * @param a lista de objetos a combinar
     * @param m cantidad de posiciones
     */
    public void combinar(List<String> a, int m) {
 
        IteradorCombinacion it = new IteradorCombinacion(a, m);
        Iterator secuenciador = it.iterator();
        
        listaFormas.clear();
        while (secuenciador.hasNext())
        {
            listaFormas.add((List) (secuenciador.next()));
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlAreaDibujo = new javax.swing.JLabel();
        jpOpciones = new javax.swing.JPanel();
        jl1 = new javax.swing.JLabel();
        jtNumVectores = new javax.swing.JTextField();
        jbDibujar = new javax.swing.JButton();
        jl2 = new javax.swing.JLabel();
        jbAnalizar = new javax.swing.JButton();
        jl3 = new javax.swing.JLabel();
        jbLimpiar = new javax.swing.JButton();
        jl4 = new javax.swing.JLabel();
        jpFormas = new javax.swing.JPanel();
        jspFormas = new javax.swing.JScrollPane();
        jlFormas = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1408, 792));
        setResizable(false);

        jlAreaDibujo.setBackground(new java.awt.Color(255, 255, 255));
        jlAreaDibujo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jlAreaDibujo.setOpaque(true);

        jpOpciones.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Opciones", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("SansSerif", 1, 28))); // NOI18N
        jpOpciones.setOpaque(false);

        jl1.setFont(new java.awt.Font("SansSerif", 1, 22)); // NOI18N
        jl1.setText("Número de Vértices:");

        jtNumVectores.setFont(new java.awt.Font("SansSerif", 0, 22)); // NOI18N
        jtNumVectores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtNumVectoresKeyTyped(evt);
            }
        });

        jbDibujar.setFont(new java.awt.Font("SansSerif", 1, 22)); // NOI18N
        jbDibujar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Poligono.png"))); // NOI18N
        jbDibujar.setBorderPainted(false);
        jbDibujar.setContentAreaFilled(false);
        jbDibujar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDibujarActionPerformed(evt);
            }
        });

        jl2.setFont(new java.awt.Font("SansSerif", 0, 22)); // NOI18N
        jl2.setText("<html>Dibujar<br>Polígono</html>");

        jbAnalizar.setFont(new java.awt.Font("SansSerif", 1, 22)); // NOI18N
        jbAnalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Analisis.png"))); // NOI18N
        jbAnalizar.setBorderPainted(false);
        jbAnalizar.setContentAreaFilled(false);
        jbAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAnalizarActionPerformed(evt);
            }
        });

        jl3.setFont(new java.awt.Font("SansSerif", 0, 22)); // NOI18N
        jl3.setText("<html>Realizar<br>Análisis</html>");

        jbLimpiar.setFont(new java.awt.Font("SansSerif", 1, 22)); // NOI18N
        jbLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Limpiar.png"))); // NOI18N
        jbLimpiar.setBorderPainted(false);
        jbLimpiar.setContentAreaFilled(false);
        jbLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLimpiarActionPerformed(evt);
            }
        });

        jl4.setFont(new java.awt.Font("SansSerif", 0, 22)); // NOI18N
        jl4.setText("Limpiar");

        javax.swing.GroupLayout jpOpcionesLayout = new javax.swing.GroupLayout(jpOpciones);
        jpOpciones.setLayout(jpOpcionesLayout);
        jpOpcionesLayout.setHorizontalGroup(
            jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpOpcionesLayout.createSequentialGroup()
                        .addComponent(jbDibujar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jl2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jbAnalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jl3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jl4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpOpcionesLayout.createSequentialGroup()
                        .addComponent(jl1)
                        .addGap(18, 18, 18)
                        .addComponent(jtNumVectores)))
                .addContainerGap())
        );
        jpOpcionesLayout.setVerticalGroup(
            jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtNumVectores, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jbAnalizar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jbDibujar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jl2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jl3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jl4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpFormas.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Alternativas Posibles", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("SansSerif", 1, 28))); // NOI18N
        jpFormas.setOpaque(false);

        jlFormas.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        jlFormas.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jlFormas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlFormas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlFormasMouseClicked(evt);
            }
        });
        jspFormas.setViewportView(jlFormas);

        javax.swing.GroupLayout jpFormasLayout = new javax.swing.GroupLayout(jpFormas);
        jpFormas.setLayout(jpFormasLayout);
        jpFormasLayout.setHorizontalGroup(
            jpFormasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFormasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jspFormas, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpFormasLayout.setVerticalGroup(
            jpFormasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFormasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jspFormas)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpFormas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlAreaDibujo, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlAreaDibujo, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jpFormas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Evento para dibujar el poligono de n lados
    private void jbDibujarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDibujarActionPerformed
        // En primer lugar, se revisa que el usuario si haya escrito cuantos vertices tendra el poligono sino entonces se termina la rutina
        if (jtNumVectores.getText().isEmpty())
        {
            return;
        }
        
        // Si pasa aqui entonces se recupera el numero de vertices que tendra el poligono
        float numVertices = Float.parseFloat(jtNumVectores.getText());
        
        // Se calcula el radio que tendra el poligono en base al ancho del area de dibujo [Donde el ancho y el alto de tal area deben ser iguales]
        int radioPoligono = jlAreaDibujo.getWidth()/2 - 30;
        
        // Despues se determina cual es la coordenada que se situa en el centro de tal area de dibujo
        int xCentro = jlAreaDibujo.getWidth()/2;
        int yCentro = jlAreaDibujo.getHeight()/2;
        
        // Tambien, se crean una variables auxilires para iterar y calcular las coordenadas de cada uno de los vertices
        int i;
        double tempX, tempY;
        
        // Y se reinician los vectores de coordenadas para los vertices
        XVertices.clear();
        YVertices.clear();
        
        // Ahora, se calcula la coordenada X y Y de cada vertice y se almacena la misma en la variable respectiva de coordendas de vertices
        for (i = 0; i < numVertices; i++)
        {
            tempX = radioPoligono* Math.cos(2*Math.PI*(i/numVertices)) + xCentro;
            tempY = radioPoligono* Math.sin(2*Math.PI*(i/numVertices)) + yCentro;
            XVertices.add(Math.toIntExact(Math.round(tempX)));
            YVertices.add(Math.toIntExact(Math.round(tempY)));
        }
        
        // Ya con todas las posiciones estimadas de los vertices se procede a dibujar dichos vertices
        dibujo.dibujarPoligono(XVertices, YVertices, xCentro, yCentro, false);
        
        // Finalmente, se muestra en pantalla el dibujo del poligono 
        Icon imagenPoligono = dibujo.retornarLienzo();
        jlAreaDibujo.setIcon(imagenPoligono);
    }//GEN-LAST:event_jbDibujarActionPerformed

    private void jbAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAnalizarActionPerformed
        // Si no hay vertices almacenados entonces se retorna de inmediato
        if (XVertices.isEmpty())
        {
            return;
        }
        
        // Si los hay entonces se crea una lista vacia de todos lo vertices que vamos a analizar y se guarda cuantos son
        List<String> segmentosPosibles = new ArrayList();
        int numVertices = XVertices.size();
        
        // Se rellena tal lista de vertices
        for (int i = 0; i < numVertices; i++)
        {
            segmentosPosibles.add(Integer.toString(i));
        }
        
        // Y se procede a hacer la combinacion sin repeticion
        combinar(segmentosPosibles, 2);
        
        
        
        
        // Luego se procede a
        
        
        
        listaFormasImpresa.clear();
        List aux;
        
        for (int i = 0; i < listaFormas.size(); i++)
        {
            aux = listaFormas.get(i);
            listaFormasImpresa.addElement("Forma " + (i+1) + ": " + aux.get(0).toString() + " con " + aux.get(1).toString());
        }
        
        jlFormas.setModel(listaFormasImpresa);
    }//GEN-LAST:event_jbAnalizarActionPerformed

    // Evento encargado de reiniciar el estado de la ventana
    private void jbLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLimpiarActionPerformed
        limpiarPantalla();
    }//GEN-LAST:event_jbLimpiarActionPerformed

    // Evento para controlar que el usuario si escriba un numero de vertices valido
    private void jtNumVectoresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtNumVectoresKeyTyped
        char caracter = evt.getKeyChar(); // En primer lugar, se obtiene el caracter que escribio el usuario
        
        // Si ese caracter no es un digito entonces
        if (Character.isDigit(caracter) != true)
        {
            evt.consume(); // Se consume el evento para que no se ponga tal caracter en la caja de texto
        }
        
        // Si no entro al condicional anterior es porque el caracter efectivamente si es un numero,
        // pero se debe revisar tambien que si el usuario va a escribir como primer digito el CERO entonces
        if (jtNumVectores.getText().isEmpty() && Character.getNumericValue(caracter) == 0)
        {
            evt.consume(); // Se consume el evento porque se necesita un numero de vertices mayor a cero
        }
    }//GEN-LAST:event_jtNumVectoresKeyTyped
    
    // Los siguientes dos eventos son para determinar cual solucion se quiere dibujar y en que momento hacer el dibujado
    
    private void jlFormasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlFormasMouseClicked
        // Si no hay forma almacenados entonces se retorna de inmediato
        if (listaFormas.isEmpty())
        {
            return;
        }
        
        indiceSeleccionado = jlFormas.getSelectedIndex(); // Obtengo la forma seleccionada
        
        if (indiceSeleccionado != -1) // Si la lista tiene por lo menos una forma y se ha elegido una de ellas entonces
        {
            List aux = listaFormas.get(indiceSeleccionado);
            
            int x1 = (int) XVertices.get(Integer.parseInt(aux.get(0).toString()));
            int y1 = (int) YVertices.get(Integer.parseInt(aux.get(0).toString()));
            
            int x2 = (int) XVertices.get(Integer.parseInt(aux.get(1).toString()));
            int y2 = (int) YVertices.get(Integer.parseInt(aux.get(1).toString()));
            
            dibujo.dibujarArista(x1, y1, x2, y2, Color.darkGray);
            
            // Finalmente, se muestra en pantalla el dibujo del poligono 
            Icon imagenFormaElegida = dibujo.retornarLienzo();
            jlAreaDibujo.setIcon(imagenFormaElegida);
        }
    }//GEN-LAST:event_jlFormasMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbAnalizar;
    private javax.swing.JButton jbDibujar;
    private javax.swing.JButton jbLimpiar;
    private javax.swing.JLabel jl1;
    private javax.swing.JLabel jl2;
    private javax.swing.JLabel jl3;
    private javax.swing.JLabel jl4;
    private javax.swing.JLabel jlAreaDibujo;
    private javax.swing.JList<String> jlFormas;
    private javax.swing.JPanel jpFormas;
    private javax.swing.JPanel jpOpciones;
    private javax.swing.JScrollPane jspFormas;
    private javax.swing.JTextField jtNumVectores;
    // End of variables declaration//GEN-END:variables
}
