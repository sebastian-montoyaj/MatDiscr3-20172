package Tarea3;

// Se importan los paquetes que necesita la clase
import Imagenes.PanelPrincipal;
import java.awt.Image;
import javax.swing.*;

// Ventana principal que interactua con el usuario
public class VentanaPrincipal extends JFrame
{
    Graficador dibujo = new Graficador();
    
    int[][] matrizOp;
    float[] colores;
    
    GF2N galoisField;
    int[] pModulos;
    
    // Constructor de la clase o ventana. Su proposito no va mas de lo estetico o para inicializacion de variables
    public VentanaPrincipal() throws Exception
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
    
    private void construirGrupo() throws Exception
    {
        int tamGrupo = (int) Math.pow(2, (int)jsExponente.getValue());
        matrizOp = new int[tamGrupo][tamGrupo];
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
        
        galoisField = new GF2N(tamGrupo);
        String[] pModulosEtiquetas = new String[tamGrupo];
        pModulos = new int[tamGrupo];
        int conta = 0;
        
        for (int i = tamGrupo; i < tamGrupo*2; i++)
        {
            pModulos[conta] = i;
            pModulosEtiquetas[conta] = Integer.toBinaryString(i);
            conta++;
        }
        
        jcbPoliModulo.setModel(new DefaultComboBoxModel(pModulosEtiquetas));
        jtaElementosGrupo.setText(elementosGrupo);
    }
    
    public void limpiar()
    {
        jcbOperacion.setSelectedIndex(-1);
        dibujo.limpiar();
        jlAreaDibujo.setIcon(dibujo.retornarLienzo());
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
        jpOpciones = new javax.swing.JPanel();
        jl3 = new javax.swing.JLabel();
        jcbOperacion = new javax.swing.JComboBox<>();
        jl4 = new javax.swing.JLabel();
        jcbPoliModulo = new javax.swing.JComboBox<>();
        jl6 = new javax.swing.JLabel();
        jl7 = new javax.swing.JLabel();
        jbDibujar = new javax.swing.JButton();
        jlAreaDibujo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jpGrupo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "<html>Grupo 2<sup>m</sup></html>", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Comic Sans MS", 1, 24))); // NOI18N
        jpGrupo.setOpaque(false);

        jl1.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        jl1.setText("Exponente:");

        jl2.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        jl2.setText("Elementos del grupo:");

        jsExponente.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jsExponente.setModel(new javax.swing.SpinnerNumberModel(1, 1, 7, 1));
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

        jpOpciones.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Opciones", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Comic Sans MS", 1, 24))); // NOI18N
        jpOpciones.setOpaque(false);

        jl3.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        jl3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl3.setText("Operación");

        jcbOperacion.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        jcbOperacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Suma", "Producto" }));
        jcbOperacion.setSelectedIndex(-1);

        jl4.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        jl4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl4.setText("Polinomio Modulo");

        jcbPoliModulo.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N

        javax.swing.GroupLayout jpOpcionesLayout = new javax.swing.GroupLayout(jpOpciones);
        jpOpciones.setLayout(jpOpcionesLayout);
        jpOpcionesLayout.setHorizontalGroup(
            jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jl3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jcbOperacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jl4, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                    .addComponent(jcbPoliModulo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpOpcionesLayout.setVerticalGroup(
            jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jl3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jl4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbPoliModulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jl6.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        jl6.setText("Dibujar");

        jl7.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        jl7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl7.setText("Representación Campo");

        jbDibujar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/dibujar.png"))); // NOI18N
        jbDibujar.setBorderPainted(false);
        jbDibujar.setContentAreaFilled(false);
        jbDibujar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDibujarActionPerformed(evt);
            }
        });

        jlAreaDibujo.setBackground(new java.awt.Color(255, 255, 255));
        jlAreaDibujo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jlAreaDibujo.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jbDibujar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jl6))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jpOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jpGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlAreaDibujo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jl7, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jl7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlAreaDibujo, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpGrupo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbDibujar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jl6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Evento para definir el grupo de trabajo
    private void jsExponenteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jsExponenteStateChanged
        try
        {
            limpiar();
            construirGrupo();
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }//GEN-LAST:event_jsExponenteStateChanged

    // Evento para dibujar 
    private void jbDibujarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDibujarActionPerformed
        int tamGrupo = colores.length;
        
        if (jcbOperacion.getSelectedIndex() == -1)
        {
            JOptionPane.showMessageDialog(this, "¡Por favor, seleccione una operación!", "ERROR",JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try
        {
//            System.out.println("Viejo: " + galoisField.getReducingPolynomial());
//            galoisField.setReducingPolynomial(33L);
//            System.out.println("Nuevo: " + galoisField.getReducingPolynomial());
            
            
//            Long temp = new Long(8);
//            
//            System.out.println("Es Irruducible: " + GF2N.isIrreducible(temp));
//            galoisField.setReducingPolynomial(temp);
//            System.out.println("Tamaño campo: " + galoisField.getFieldSize() + " - P. Irreducible: " + Integer.toBinaryString((int)galoisField.getReducingPolynomial()));
//            System.out.println(temp.byteValue());
            
            int poliElegido = pModulos[jcbPoliModulo.getSelectedIndex()];
            galoisField.setReducingPolynomial(poliElegido);
            
            for (int i=0; i < tamGrupo; i++)
            {
                //System.out.print("Fila " + (i+1) + ": ");
                for (int j=0; j < tamGrupo; j++)
                {
                    if (jcbOperacion.getSelectedIndex() == 0)
                    {
                        matrizOp[i][j] = (int) galoisField.add(i, j);
                    }
                    else
                    {
                       matrizOp[i][j] = (int) galoisField.multiply(i, j); 
                    }
                    //System.out.print(matrizOp[i][j] + "\t");
                }
                //System.out.println();
            }
            
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        
        dibujo.dibujarCampo(matrizOp, colores);
        
        Icon temp = dibujo.retornarLienzo();
        jlAreaDibujo.setIcon(temp);
    }//GEN-LAST:event_jbDibujarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbDibujar;
    private javax.swing.JComboBox<String> jcbOperacion;
    private javax.swing.JComboBox<String> jcbPoliModulo;
    private javax.swing.JLabel jl1;
    private javax.swing.JLabel jl2;
    private javax.swing.JLabel jl3;
    private javax.swing.JLabel jl4;
    private javax.swing.JLabel jl6;
    private javax.swing.JLabel jl7;
    private javax.swing.JLabel jlAreaDibujo;
    private javax.swing.JPanel jpGrupo;
    private javax.swing.JPanel jpOpciones;
    private javax.swing.JSpinner jsExponente;
    private javax.swing.JScrollPane jspElementosGrupo;
    private javax.swing.JTextArea jtaElementosGrupo;
    // End of variables declaration//GEN-END:variables
}
