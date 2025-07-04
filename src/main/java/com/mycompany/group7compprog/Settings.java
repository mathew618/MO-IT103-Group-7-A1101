/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.group7compprog;

import java.io.File;

/**
 *
 * @author mathe
 */
public class Settings extends javax.swing.JPanel {

    /**
     * Creates new form Settings
     */
    public Settings() {
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

        f6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        f6.setBackground(new java.awt.Color(228, 255, 255));

        jLabel5.setFont(new java.awt.Font("Bahnschrift", 1, 24)); // NOI18N
        jLabel5.setText("• SETTINGS");

        jButton1.setBackground(new java.awt.Color(255, 204, 204));
        jButton1.setText("Reset CSV");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout f6Layout = new javax.swing.GroupLayout(f6);
        f6.setLayout(f6Layout);
        f6Layout.setHorizontalGroup(
            f6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(f6Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(f6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(468, Short.MAX_VALUE))
        );
        f6Layout.setVerticalGroup(
            f6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(f6Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel5)
                .addGap(56, 56, 56)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(275, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(f6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(f6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        File file = new File("temp_emp.csv");
        file.delete();
        
        Temp.duplicateCSV("Employee.csv", "temp_emp.csv");
        Emp.reloadEmp();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel f6;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
