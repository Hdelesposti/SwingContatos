/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contatos.revisao.view;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

/**
 *
 * @author clayton
 */
public class PrincipalView extends JFrame {

    /**
     * Creates new form Principal
     */

    public PrincipalView() {
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mbPrincipal = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miIncluirContato = new javax.swing.JMenuItem();
        miConsultarContato = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestão de Contatos");

        jMenu1.setText("Opções");

        miIncluirContato.setText("Incluir contatos");
        miIncluirContato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miIncluirContatoActionPerformed(evt);
            }
        });
        jMenu1.add(miIncluirContato);

        miConsultarContato.setText("Consultar contatos");
        miConsultarContato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miConsultarContatoActionPerformed(evt);
            }
        });
        jMenu1.add(miConsultarContato);

        mbPrincipal.add(jMenu1);

        setJMenuBar(mbPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 739, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 368, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miConsultarContatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miConsultarContatoActionPerformed

    }//GEN-LAST:event_miConsultarContatoActionPerformed

    private void miIncluirContatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miIncluirContatoActionPerformed

    }//GEN-LAST:event_miIncluirContatoActionPerformed

    
    /**
     * @param args the command line arguments
     */

    public JMenuItem getMiConsultarContato() {
        return miConsultarContato;
    }

    public JMenuItem getMiIncluirContato() {
        return miIncluirContato;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar mbPrincipal;
    private javax.swing.JMenuItem miConsultarContato;
    private javax.swing.JMenuItem miIncluirContato;
    // End of variables declaration//GEN-END:variables
}
