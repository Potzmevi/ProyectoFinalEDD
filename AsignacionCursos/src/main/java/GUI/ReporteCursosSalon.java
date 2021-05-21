/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Estructuras.ListaCircular;
import Estructuras.ListaSimple;
import Main.Main;
import static Main.Main.estudiantes;
import static Main.Main.horarios;
import static Main.Main.listaCursos;
import static Main.Main.listaEdificios;
import Objetos.Asignacion;
import Objetos.Curso;
import Objetos.Edificio;
import Objetos.Estudiante;
import Objetos.Horario;
import Objetos.Salon;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author meza4
 */
public class ReporteCursosSalon extends javax.swing.JFrame {

    /**
     * Creates new form MenuPrincipal
     */
    public ReporteCursosSalon() {
        initComponents();
        setVisible(true);
        pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        user = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tipo = new javax.swing.JLabel();
        edificios = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        salon = new javax.swing.JFormattedTextField();
        buscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Usac_logo.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 18, -1, -1));

        jLabel2.setFont(new java.awt.Font("Consolas", 1, 17)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Usuario:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, -1, -1));

        jLabel3.setFont(new java.awt.Font("Consolas", 1, 27)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Ver cursos impartidos en un salon");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(187, 39, -1, -1));

        user.setFont(new java.awt.Font("Consolas", 1, 17)); // NOI18N
        user.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 251, 21));

        tabla.setBackground(new java.awt.Color(255, 255, 204));
        tabla.setForeground(new java.awt.Color(0, 0, 0));
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Semestre", "Creditos"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 810, 330));

        jLabel4.setFont(new java.awt.Font("Consolas", 1, 17)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Id:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, -1, -1));

        id.setFont(new java.awt.Font("Consolas", 1, 17)); // NOI18N
        id.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 251, 21));

        jLabel5.setFont(new java.awt.Font("Consolas", 1, 17)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tipo:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, -1, -1));

        tipo.setFont(new java.awt.Font("Consolas", 1, 17)); // NOI18N
        tipo.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.add(tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, 251, 21));

        edificios.setBackground(new java.awt.Color(255, 255, 204));
        edificios.setForeground(new java.awt.Color(0, 0, 0));
        edificios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edificiosActionPerformed(evt);
            }
        });
        jPanel1.add(edificios, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, 150, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tipo.png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, -1, -1));

        jLabel8.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Edificio");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 150, -1, -1));

        jLabel10.setFont(new java.awt.Font("Consolas", 0, 17)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Salon:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 180, -1, -1));

        salon.setBackground(new java.awt.Color(255, 255, 204));
        salon.setForeground(new java.awt.Color(0, 0, 0));
        salon.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jPanel1.add(salon, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, 150, -1));

        buscar.setBackground(new java.awt.Color(255, 255, 204));
        buscar.setFont(new java.awt.Font("Consolas", 0, 15)); // NOI18N
        buscar.setForeground(new java.awt.Color(0, 0, 0));
        buscar.setText("Buscar");
        buscar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buscar.setFocusPainted(false);
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });
        jPanel1.add(buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 210, 70, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        user.setText(Main.usuarioActual.getNombre());
        tipo.setText(Main.usuarioActual.getTipo());
        id.setText(String.valueOf(Main.usuarioActual.getId()));
        ListaCircular.Nodo nodo = listaEdificios.getRoot();
        if (nodo == null) {
            JOptionPane.showMessageDialog(null, "Cree un edificio primero antes de Modificar un salon");
            this.dispose();
        } else {
            do {
                Edificio edif = (Edificio) nodo.getData();
                edificios.addItem(edif.getNombre());
                nodo = nodo.getNext();
            } while (nodo != listaEdificios.getRoot());
        }
    }//GEN-LAST:event_formComponentShown

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        try {
            if (!salon.getText().equals("")) {
                ListaCircular.Nodo nodo = listaEdificios.buscarNombre(edificios.getSelectedItem().toString());
                ArrayList<Curso> cursos = new ArrayList();
                Edificio edif = (Edificio) nodo.getData();
                ListaSimple salones = edif.getSalones();
                ListaSimple.Nodo nodoListaSimple = salones.getNode(salon.getText());
                if (nodoListaSimple == null) {
                    JOptionPane.showMessageDialog(null, "Salon con ese id no existe");
                } else {
                    ArrayList<Horario> horario = horarios.getmRaiz().toArray();
                    DefaultTableModel model = (DefaultTableModel) tabla.getModel();
                    model.setRowCount(0);
                    for (int i = 0; i < horario.size(); i++) {
                        Horario horarioObj = horario.get(i);
                        Salon salonObj = horarioObj.getSalon();
                        Edificio edificioObj = horarioObj.getEdificio();
                        Curso curso = horarioObj.getCurso();
                        
                        if (((Salon) nodoListaSimple.getData()).getId() == salonObj.getId() && edif.getNombre().equals(edificioObj.getNombre())) {
                            if(cursos.size()==0){
                                model.addRow(new Object[]{curso.getNombre(), curso.getSemestre(), curso.getCreditos()});
                                    cursos.add(curso);
                            }
                            for (int j = 0; j < cursos.size(); j++) {
                                if (!curso.equals(cursos.get(i))) {
                                    
                                    model.addRow(new Object[]{curso.getNombre(), curso.getSemestre(), curso.getCreditos()});
                                    cursos.add(curso);
                                }
                            }

                        }

                    }
                    
                }

            } else {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un id");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_buscarActionPerformed

    private void edificiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edificiosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edificiosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReporteCursosSalon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReporteCursosSalon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReporteCursosSalon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReporteCursosSalon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReporteCursosSalon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscar;
    private javax.swing.JComboBox<String> edificios;
    private javax.swing.JLabel id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JFormattedTextField salon;
    private javax.swing.JTable tabla;
    private javax.swing.JLabel tipo;
    private javax.swing.JLabel user;
    // End of variables declaration//GEN-END:variables
}
