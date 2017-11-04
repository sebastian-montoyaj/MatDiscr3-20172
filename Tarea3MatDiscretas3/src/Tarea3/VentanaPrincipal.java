package Tarea3;

// Se importan los paquetes que necesita la clase
import Imagenes.PanelPrincipal;
import java.awt.Image;
import java.util.Random;
import javax.swing.*;

// Ventana principal que interactua con el usuario
public class VentanaPrincipal extends JFrame
{
    Graficador dibujoOp1 = new Graficador();
    Graficador dibujoOp2 = new Graficador();
    
    int[][] matrizOp1;
    int[][] matrizOp2;
    float[] colores;
    
    private void construirGrupo()
    {
        int tamGrupo = (int) Math.pow(2, (int)jsExponente.getValue());
        matrizOp1 = new int[tamGrupo][tamGrupo];
        matrizOp2 = new int[tamGrupo][tamGrupo];
        colores = new float[tamGrupo];
        
        String elementosGrupo = "";
        for (int i=0; i < tamGrupo; i++)
        {
            elementosGrupo = elementosGrupo + Integer.toBinaryString(i);
            
            if (i<tamGrupo-1)
            {
                elementosGrupo = elementosGrupo + "\n";
            }
            
            colores[i] = ((320/tamGrupo)*i)/360.0f;
        }
        
        // ----------- Esto es solo para hacer pruebas
        Random rand = new Random();
        for (int i=0; i < tamGrupo; i++)
        {
            for (int j=0; j < tamGrupo; j++)
            {
                matrizOp1[i][j] = rand.nextInt(tamGrupo);
            }
        }
        // -----------
        
        jtaElementosGrupo.setText(elementosGrupo);
    }
    
    // Constructor de la clase o ventana. Su proposito no va mas de lo estetico o para inicializacion de variables
    public VentanaPrincipal()
    {
        initComponents();
        
        // Se establece el icono de la ventana
        Image icono = new ImageIcon(getClass().getResource("/Imagenes/icono.png")).getImage();
        setIconImage(icono);
        
        // Se define el titulo de la ventana
        setTitle("Tarea 3 - Matemáticas Discretas III");
        
        // Se pone la ventana en el centro de la pantalla cuando esta se visualice
        setLocationRelativeTo(null);
        
        // Se agrega un fondo a esta ventana
        PanelPrincipal panelConFondo = new PanelPrincipal();
        add(panelConFondo);
        
        // ----------------------------------------------------------------------------
        construirGrupo();
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpGrupo = new javax.swing.JPanel();
        jl1 = new javax.swing.JLabel();
        jl2 = new javax.swing.JLabel();
        jsExponente = new javax.swing.JSpinner();
        jspElementosGrupo = new javax.swing.JScrollPane();
        jtaElementosGrupo = new javax.swing.JTextArea();
        jpOperaciones = new javax.swing.JPanel();
        jl3 = new javax.swing.JLabel();
        jcbOperacion1 = new javax.swing.JComboBox<>();
        jl4 = new javax.swing.JLabel();
        jcbOperacion2 = new javax.swing.JComboBox<>();
        jl6 = new javax.swing.JLabel();
        jl7 = new javax.swing.JLabel();
        jl8 = new javax.swing.JLabel();
        jbDibujar = new javax.swing.JButton();
        jlAreaDibujoOp1 = new javax.swing.JLabel();
        jlAreaDibujoOp2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jpGrupo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "<html>Grupo 2<sup>m</sup></html>", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Comic Sans MS", 1, 24))); // NOI18N
        jpGrupo.setOpaque(false);

        jl1.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        jl1.setText("Exponente:");

        jl2.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        jl2.setText("Elementos del grupo:");

        jsExponente.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jsExponente.setModel(new javax.swing.SpinnerNumberModel(1, 1, 5, 1));
        jsExponente.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jsExponenteStateChanged(evt);
            }
        });

        jspElementosGrupo.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N

        jtaElementosGrupo.setColumns(20);
        jtaElementosGrupo.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        jtaElementosGrupo.setRows(5);
        jtaElementosGrupo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jtaElementosGrupo.setEnabled(false);
        jspElementosGrupo.setViewportView(jtaElementosGrupo);

        javax.swing.GroupLayout jpGrupoLayout = new javax.swing.GroupLayout(jpGrupo);
        jpGrupo.setLayout(jpGrupoLayout);
        jpGrupoLayout.setHorizontalGroup(
            jpGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGrupoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspElementosGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpGrupoLayout.createSequentialGroup()
                        .addComponent(jl1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jsExponente, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jl2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpGrupoLayout.setVerticalGroup(
            jpGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpGrupoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl1)
                    .addComponent(jsExponente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jl2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspElementosGrupo)
                .addContainerGap())
        );

        jpOperaciones.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Operaciones", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Comic Sans MS", 1, 24))); // NOI18N
        jpOperaciones.setOpaque(false);

        jl3.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        jl3.setText("Operación +:");

        jcbOperacion1.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        jcbOperacion1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jl4.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        jl4.setText("Operación x:");

        jcbOperacion2.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        jcbOperacion2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jpOperacionesLayout = new javax.swing.GroupLayout(jpOperaciones);
        jpOperaciones.setLayout(jpOperacionesLayout);
        jpOperacionesLayout.setHorizontalGroup(
            jpOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpOperacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpOperacionesLayout.createSequentialGroup()
                        .addComponent(jl3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcbOperacion1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpOperacionesLayout.createSequentialGroup()
                        .addComponent(jl4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcbOperacion2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpOperacionesLayout.setVerticalGroup(
            jpOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpOperacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl3)
                    .addComponent(jcbOperacion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl4)
                    .addComponent(jcbOperacion2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jl6.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        jl6.setText("Dibujar");

        jl7.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        jl7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl7.setText("Representación +");

        jl8.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        jl8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl8.setText("Representación x");

        jbDibujar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/dibujar.png"))); // NOI18N
        jbDibujar.setBorderPainted(false);
        jbDibujar.setContentAreaFilled(false);
        jbDibujar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDibujarActionPerformed(evt);
            }
        });

        jlAreaDibujoOp1.setBackground(new java.awt.Color(255, 255, 255));
        jlAreaDibujoOp1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jlAreaDibujoOp1.setOpaque(true);

        jlAreaDibujoOp2.setBackground(new java.awt.Color(255, 255, 255));
        jlAreaDibujoOp2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jlAreaDibujoOp2.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jpOperaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jbDibujar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jl6))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jpGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlAreaDibujoOp1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jl7, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlAreaDibujoOp2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jl8, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl7)
                            .addComponent(jl8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlAreaDibujoOp1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlAreaDibujoOp2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jpGrupo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpOperaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbDibujar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jl6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Evento para definir el grupo de trabajo
    private void jsExponenteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jsExponenteStateChanged
        construirGrupo();
    }//GEN-LAST:event_jsExponenteStateChanged

    // Evento para dibujar 
    private void jbDibujarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDibujarActionPerformed
        dibujoOp1.dibujarCampo(matrizOp1, colores);
        
        Icon temp = dibujoOp1.retornarLienzo();
        
        jlAreaDibujoOp1.setIcon(temp);
        
    }//GEN-LAST:event_jbDibujarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbDibujar;
    private javax.swing.JComboBox<String> jcbOperacion1;
    private javax.swing.JComboBox<String> jcbOperacion2;
    private javax.swing.JLabel jl1;
    private javax.swing.JLabel jl2;
    private javax.swing.JLabel jl3;
    private javax.swing.JLabel jl4;
    private javax.swing.JLabel jl6;
    private javax.swing.JLabel jl7;
    private javax.swing.JLabel jl8;
    private javax.swing.JLabel jlAreaDibujoOp1;
    private javax.swing.JLabel jlAreaDibujoOp2;
    private javax.swing.JPanel jpGrupo;
    private javax.swing.JPanel jpOperaciones;
    private javax.swing.JSpinner jsExponente;
    private javax.swing.JScrollPane jspElementosGrupo;
    private javax.swing.JTextArea jtaElementosGrupo;
    // End of variables declaration//GEN-END:variables
}
