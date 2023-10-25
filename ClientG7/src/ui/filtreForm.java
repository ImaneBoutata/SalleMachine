/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dao.IDao;
import entities.Machine;
import entities.Salle;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import utils.Config;

/**
 *
 * @author hp
 */
public class filtreForm extends javax.swing.JInternalFrame {

    IDao<Machine> dao;
    IDao<Salle> salle;

    DefaultTableModel model;
    private static int id;
    /**
     * Creates new form filtreForm
     */
    public filtreForm() {
     try {
            initComponents();
            dao = (IDao<Machine>) Naming.lookup("rmi://" + Config.ip + ":" + Config.port + "/" + "dao");
            salle = (IDao<Salle>) Naming.lookup("rmi://" + Config.ip + ":" + Config.port + "/" + "salle");
            model = (DefaultTableModel) machinesList.getModel();
            load();
            loadSalle();
        } catch (NotBoundException ex) {
            Logger.getLogger(MachineForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(MachineForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(MachineForm.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
       public void load (){
        model.setRowCount(0);
        try {
            for(Machine m : dao.findMachinesBySalle((Salle) ComboSalle.getSelectedItem())){
                model.addRow(new Object[]{
                    m.getId(),
                    m.getRef(),
                    m.getMarque(),
                    m.getPrix(),
                    
                });
            }
        } catch (RemoteException ex) {
            Logger.getLogger(filtreForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       public void loadSalle(){
        try {
             List<Salle> salleList = new ArrayList<>(salle.findAll());
            for (Salle s : salleList){
                
                ComboSalle.addItem(s);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(MachineForm.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel4 = new javax.swing.JLabel();
        ComboSalle = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        machinesList = new javax.swing.JTable();

        jLabel4.setText("Salle :");

        jButton1.setText("Filtrer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Machines par Salle :");

        machinesList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Ref", "Marque", "Prix"
            }
        ));
        machinesList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                machinesListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(machinesList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(jLabel4)
                .addGap(45, 45, 45)
                .addComponent(ComboSalle, 0, 292, Short.MAX_VALUE)
                .addGap(88, 88, 88)
                .addComponent(jButton1)
                .addGap(104, 104, 104))
            .addGroup(layout.createSequentialGroup()
                .addGap(309, 309, 309)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(ComboSalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void machinesListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_machinesListMouseClicked
        // TODO add your handling code here:
        id = Integer.parseInt(model.getValueAt(machinesList.getSelectedRow(), 0).toString());
        ComboSalle.getModel().setSelectedItem(model.getValueAt(machinesList.getSelectedRow(),4));

    }//GEN-LAST:event_machinesListMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        load();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboSalle;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable machinesList;
    // End of variables declaration//GEN-END:variables
}