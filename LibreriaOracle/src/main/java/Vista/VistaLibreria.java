package Vista;

import DAOs.ColumnaMetaDatos;
import DAOs.TablaMetaDatos;
import DAOs.TableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author PC1Ry
 */
public class VistaLibreria extends javax.swing.JFrame {

    
    /**
     * Creates new form JFrameVistaLibreria
     */
    public VistaLibreria() {
        initComponents();
    }
    
    //Getters

    public JPanel getPanel1() {
        return panel1;
    }

    public JComboBox<String> getAcciones() {
        return acciones;
    }

    public JButton getCancelar() {
        return cancelar;
    }

    public JButton getEjecutar() {
        return ejecutar;
    }

    public JButton getEjecutarAccionCompleja() {
        return ejecutarAccionCompleja;
    }

    public JPanel getjPanel1() {
        return panel1;
    }

    public JPanel getPanelAcciones() {
        return panelAcciones;
    }

    public JPanel getPanelTablas() {
        return panelTablas;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public JScrollPane getjScrollPane2() {
        return jScrollPane2;
    }

    public JTable getjTable1() {
        return jTable1;
    }

    public JComboBox<String> getTablasDeAccion() {
        return tablasDeAccion;
    }

    public JLabel getTituloAcciones() {
        return tituloAcciones;
    }

    public JLabel getTituloTablas() {
        return tituloTablas;
    }

    public JTable getVisor() {
        return visor;
    }
    
    //-------------------------------------------------------------------------
    //------------------------Getters especiales-------------------------------
    //-------------------------------------------------------------------------
    public String getSelectedAccion(){
        return (String) acciones.getSelectedItem();
    }
    
    public String getSelectedTable(){
        return (String) tablasDeAccion.getSelectedItem();
    }
    
    //_________________________________________________________________________
    //------------------------Metodos de la vista------------------------------
    
    
    public void setVisiblePanelTabla(boolean mostrar){
        tablasDeAccion.setVisible(mostrar);
    }
    
    public void setVisibleBotonAccionComleja(boolean mostrar){
        ejecutarAccionCompleja.setVisible(mostrar);
    }
    
    public void llenarTablasDeAccion(String []tablas){
        
        tablasDeAccion.removeAllItems();
        for(int i=0; i<tablas.length; i++){
            tablasDeAccion.addItem(tablas[i]);
        }
    }
    
    public void llenarAcciones(String[] accionesArray){
        
        acciones.removeAllItems();
        for(int i=0; i<accionesArray.length; i++){
            acciones.addItem(accionesArray[i]);
        }
    }
    
    public Class<?> getClassFromDB(String data_type){
        
        switch(data_type){
            case "NUMBER":
                return Integer.class;
            case "VARCHAR2":
                return String.class;
            default:
                throw new IllegalArgumentException("Tipo no soportado: " + data_type);
        }
    }
    
    public void llenarTabla(List<TableModel> resultados, List<ColumnaMetaDatos> columnas){
        
        DefaultTableModel tableModel = new DefaultTableModel(){
            
            @Override
            public boolean isCellEditable(int row, int column){
                if(column==0){
                    return false;
                }
                return true;
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                
                return getClassFromDB(columnas.get(columnIndex).getData_type());
            }
            
            @Override
            public void setValueAt(Object aValue, int row, int column){
                
                try{
                    if(getColumnClass(column)==Integer.class){
                        aValue=Integer.valueOf((String) aValue);
                    }
                }catch(Exception e){
                    showError("Valor inválido para columna " + column);
                    return;
                }
                super.setValueAt(aValue, row, column);
            }
        };
        
        //Añadir columnas
        for (ColumnaMetaDatos columna : columnas) {
            tableModel.addColumn(columna.getColumn_name());
        }
        
        visor.setModel(tableModel);
        //Añadir filas
        for(int i=0; i<resultados.size(); i++){  
            tableModel.addRow(resultados.get(i).getAtributosArray());
        }
        //Añadir restriccion de tamaño maximo de caracteres
        for(int i=0; i<columnas.size(); i++){
            JTextField tf = new JTextField();
            tf.setDocument(new EditorDocument(columnas.get(i).getColumn_length(),
            Integer.class==getClassFromDB(columnas.get(i).getData_type())
            ));
            visor.getColumnModel().getColumn(i)
                    .setCellEditor(new DefaultCellEditor(tf));
        }
        
    }
    //-------------------------------------------------------------------------
    //----------------------------Mensajes-------------------------------------
    //-------------------------------------------------------------------------
    public void showError(String msg){
        
        JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
    public void showMsg(String msg){
        
        JOptionPane.showMessageDialog(null, msg, "Info", JOptionPane.ERROR_MESSAGE);
    }
    
    //-------------------------------------------------------------------------
    //----------------------------Insert de filas------------------------------
    //-------------------------------------------------------------------------
    public void addEmptyRow(){
        
        DefaultTableModel tableModelVisor = (DefaultTableModel) visor.getModel();
        tableModelVisor.addRow(new Object[0]);
        visor.setModel(tableModelVisor);
    }
    
    public void removeLastRow(){
        
        DefaultTableModel tableModelVisor = (DefaultTableModel) visor.getModel();
        tableModelVisor.removeRow(tableModelVisor.getRowCount()-1);
        visor.setModel(tableModelVisor);
    }
    
    public Object[] getRowToInsert(){
        
        Object[] filaData = new Object[visor.getColumnCount()-1];
        for(int i=0; i<filaData.length; i++){
            filaData[i] = visor.getValueAt(visor.getRowCount()-1, i+1);
            if(filaData[i]!=null){
                System.out.println(filaData[i].getClass());
            }
        }
        return filaData;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        panel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        visor = new javax.swing.JTable();
        ejecutar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        panelAcciones = new javax.swing.JPanel();
        tituloAcciones = new javax.swing.JLabel();
        acciones = new javax.swing.JComboBox<>();
        panelTablas = new javax.swing.JPanel();
        tablasDeAccion = new javax.swing.JComboBox<>();
        tituloTablas = new javax.swing.JLabel();
        ejecutarAccionCompleja = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel1.setBackground(new java.awt.Color(51, 153, 255));

        visor.setBackground(new java.awt.Color(0, 102, 204));
        visor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        visor.setFont(new java.awt.Font("Yu Gothic", 0, 14)); // NOI18N
        visor.setForeground(new java.awt.Color(255, 255, 255));
        visor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        visor.setRowHeight(30);
        visor.setSelectionBackground(new java.awt.Color(204, 204, 204));
        visor.setShowGrid(false);
        visor.setShowHorizontalLines(true);
        jScrollPane2.setViewportView(visor);

        ejecutar.setBackground(new java.awt.Color(153, 255, 153));
        ejecutar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ejecutar.setText("Ejecutar");
        ejecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejecutarActionPerformed(evt);
            }
        });

        cancelar.setBackground(new java.awt.Color(255, 102, 102));
        cancelar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        panelAcciones.setBackground(new java.awt.Color(0, 102, 153));

        tituloAcciones.setBackground(new java.awt.Color(204, 204, 255));
        tituloAcciones.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        tituloAcciones.setForeground(new java.awt.Color(255, 255, 255));
        tituloAcciones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloAcciones.setText("Accion que se quiere realizar");

        acciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        acciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAccionesLayout = new javax.swing.GroupLayout(panelAcciones);
        panelAcciones.setLayout(panelAccionesLayout);
        panelAccionesLayout.setHorizontalGroup(
            panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAccionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(acciones, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tituloAcciones, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelAccionesLayout.setVerticalGroup(
            panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAccionesLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(tituloAcciones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(acciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        panelTablas.setBackground(new java.awt.Color(0, 102, 153));

        tablasDeAccion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tituloTablas.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        tituloTablas.setForeground(new java.awt.Color(255, 255, 255));
        tituloTablas.setText("Tabla en la que se hara la acción");

        javax.swing.GroupLayout panelTablasLayout = new javax.swing.GroupLayout(panelTablas);
        panelTablas.setLayout(panelTablasLayout);
        panelTablasLayout.setHorizontalGroup(
            panelTablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTablasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tablasDeAccion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tituloTablas, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelTablasLayout.setVerticalGroup(
            panelTablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTablasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tituloTablas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tablasDeAccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        ejecutarAccionCompleja.setBackground(new java.awt.Color(153, 255, 153));
        ejecutarAccionCompleja.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ejecutarAccionCompleja.setText("Avanzar para ejecutar la acción");
        ejecutarAccionCompleja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejecutarAccionComplejaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(panelAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(panelTablas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addComponent(cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ejecutar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ejecutarAccionCompleja, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addComponent(ejecutarAccionCompleja)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ejecutar)
                            .addComponent(cancelar)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(panelAcciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelTablas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ejecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejecutarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ejecutarActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelarActionPerformed

    private void accionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accionesActionPerformed

        
    }//GEN-LAST:event_accionesActionPerformed

    private void ejecutarAccionComplejaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejecutarAccionComplejaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ejecutarAccionComplejaActionPerformed

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
            java.util.logging.Logger.getLogger(VistaLibreria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaLibreria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaLibreria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaLibreria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaLibreria().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> acciones;
    private javax.swing.JButton cancelar;
    private javax.swing.JButton ejecutar;
    private javax.swing.JButton ejecutarAccionCompleja;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JPanel panelTablas;
    private javax.swing.JComboBox<String> tablasDeAccion;
    private javax.swing.JLabel tituloAcciones;
    private javax.swing.JLabel tituloTablas;
    public javax.swing.JTable visor;
    // End of variables declaration//GEN-END:variables
}
