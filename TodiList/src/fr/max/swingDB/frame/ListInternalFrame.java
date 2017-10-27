/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.max.swingDB.frame;

import fr.max.swingDB.dao.ListDAO;
import fr.max.swingDB.entity.ListDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author formation
 */
public class ListInternalFrame extends javax.swing.JInternalFrame {

    private final ListDTO listdto;
    private final ListDAO listdao;
    private Connection dbConnection;
    private PreparedStatement pStatement;
    private ResultSet resultSet;

    private DefaultTableModel model;
    private DefaultComboBoxModel Combo;
    private Map<String, Integer> mapIdCategorie;
    private Map<Integer, Integer> mapRowId;

    /**
     * Creates new form AuthorInternalFrame
     *
     * @throws java.sql.SQLException
     */
    public ListInternalFrame() throws SQLException {
        initComponents();
        setVisible(true);

        dbConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/list_taches", "root", "");
        listdto = new ListDTO();
        listdao = new ListDAO((com.mysql.jdbc.Connection) dbConnection, pStatement, resultSet);
        mapRowId = new HashMap<>();

        Map<String, Integer> mapIdCategorie = new HashMap<>();
//      updateButton.setEnabled(false);
//      createButton.setEnabled(false);
//      deleteButton.setEnabled(false); 

        initTable();
    }

    private void initTable() throws SQLException {
        //Récupération du modèle de la jTable
        model = (DefaultTableModel) listTable.getModel();
        Combo = (DefaultComboBoxModel) categorieComboBox.getModel();
        
        //Suppression des lignes existante sur la table
        model.setRowCount(0);
        Combo.removeAllElements();
        
        ResultSet resultSet =listdao.findAll().getResul();
        resultSet.beforeFirst();
       
        //Tableau d'objet qui représente une ligne de la table
        Object[] tableRow = new Object[4];

        int tableRowNumber = 0;
        mapRowId.clear();
        //Boucle sur le ResultSet
        while (resultSet.next()) {
            mapRowId.put(tableRowNumber,resultSet.getInt(1));
            //Constitution du tableau des données
            tableRow[0] = resultSet.getObject("id_tache");
            tableRow[1] = resultSet.getObject("nomDeTache");
            tableRow[2] = resultSet.getObject("nomDeCategorie");
            tableRow[3] = resultSet.getObject("tacheTerminee");

            //Ajout du tableau dans une nouvelle ligne du modèle
            model.addRow(tableRow);
            tableRowNumber++;
        }
        
        mapIdCategorie = listdao.getFkMap();
        Set<String> lCat =  mapIdCategorie.keySet();
        
        SortedSet<String> listCategorie = new TreeSet<>();
        for (String entry : lCat) {
            listCategorie.add(entry);
        }
        //ajout des éléments classés par ordre alphabetique dans la combo box via son model
        for (String o : listCategorie) {   
            Combo.addElement(o);
        }
        //Fermeture des objets de connexion
//        resultSet.close();
//        pStatement.close();
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
        listTable = new javax.swing.JTable();
        tacheLabel = new javax.swing.JLabel();
        tacheField = new javax.swing.JTextField();
        categorieLabel = new javax.swing.JLabel();
        categorieComboBox = new javax.swing.JComboBox<>();
        termineeCheckBox = new javax.swing.JCheckBox();
        updateButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        createButton = new javax.swing.JButton();
        razButton = new javax.swing.JButton();
        idLabel = new javax.swing.JLabel();
        idTextField = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Gestion des auteurs");

        listTable.setAutoCreateRowSorter(true);
        listTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "N°", "Tâche", "Catégorie", "Terminée"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        listTable.setColumnSelectionAllowed(true);
        listTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listTable);
        listTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (listTable.getColumnModel().getColumnCount() > 0) {
            listTable.getColumnModel().getColumn(0).setMinWidth(20);
            listTable.getColumnModel().getColumn(0).setPreferredWidth(30);
            listTable.getColumnModel().getColumn(0).setMaxWidth(40);
            listTable.getColumnModel().getColumn(1).setResizable(false);
            listTable.getColumnModel().getColumn(2).setResizable(false);
            listTable.getColumnModel().getColumn(3).setMinWidth(60);
            listTable.getColumnModel().getColumn(3).setPreferredWidth(70);
            listTable.getColumnModel().getColumn(3).setMaxWidth(80);
        }

        tacheLabel.setText("Tâche");

        categorieLabel.setText("Catégorie");

        categorieComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Perso", "Pro", "Famille" }));

        termineeCheckBox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        termineeCheckBox.setText("Terminer");
        termineeCheckBox.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        termineeCheckBox.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        termineeCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                termineeCheckBoxActionPerformed(evt);
            }
        });

        updateButton.setText("Mise à jour");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("suppression");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        createButton.setText("création");
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });

        razButton.setText("RAZ");
        razButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                razButtonActionPerformed(evt);
            }
        });

        idLabel.setText("N°");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tacheLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(categorieLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(termineeCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                            .addComponent(tacheField)
                            .addComponent(categorieComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(idLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(createButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(razButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {createButton, deleteButton, updateButton});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {categorieLabel, idLabel, tacheLabel});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idLabel)
                    .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createButton))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tacheField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tacheLabel)
                    .addComponent(updateButton))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categorieLabel)
                    .addComponent(deleteButton)
                    .addComponent(categorieComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(termineeCheckBox)
                    .addComponent(razButton))
                .addGap(20, 20, 20))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {categorieComboBox, idTextField, tacheField, termineeCheckBox});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {categorieLabel, idLabel, tacheLabel});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listTableMouseClicked
        //La ligne sélectionnée
        Integer selectedIndex = listTable.getSelectedRow();
        //Redéfinition de l'index pour obtenir la bonne sélection 
        //en cas de tri sur la table
        selectedIndex = listTable.convertRowIndexToModel(selectedIndex);

        //Récupération du modèle
        TableModel modelTable = listTable.getModel();

        //Récupération des données de la ligne sélectionnée
        String id = modelTable.getValueAt(selectedIndex, 0).toString();
        String tache = modelTable.getValueAt(selectedIndex, 1).toString();
        String categorie = modelTable.getValueAt(selectedIndex, 2).toString();
        boolean terminee = (boolean) modelTable.getValueAt(selectedIndex, 3);

        //Si firstName est null
        if (tache == null) {
            tache = "";
        }

        //Insertion des données dans les TextField
        idTextField.setText(String.valueOf(id));
        tacheField.setText(tache);
        categorieComboBox.setSelectedItem(categorie);
        termineeCheckBox.setSelected(terminee);
    }//GEN-LAST:event_listTableMouseClicked

    private void razButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_razButtonActionPerformed
        idTextField.setText("");
        tacheField.setText("");
        categorieComboBox.setSelectedItem("");
        termineeCheckBox.setSelected(!isSelected);
    }//GEN-LAST:event_razButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        try {
            listdao.deleteOneById(listdto);

        } catch (SQLException ex) {
            Logger.getLogger(ListInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        setEvent(listdto);
        try {
            listdao.insert(listdto);
            initTable();
            resetAll();

        } catch (SQLException ex) {
            Logger.getLogger(ListInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_createButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        setEvent(listdto);
        try {
            listdao.update(listdto);
           //pStatement.executeUpdate();
            initTable();
            resetAll();
        } catch (SQLException ex) {
            Logger.getLogger(ListInternalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_updateButtonActionPerformed

    private void termineeCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_termineeCheckBoxActionPerformed
        listdto.setTerminee(termineeCheckBox.isSelected());
    }//GEN-LAST:event_termineeCheckBoxActionPerformed

     public void resetAll(){
        listTable.clearSelection();
        idTextField.setText("id = ");
        tacheField.setText("");
        categorieComboBox.setSelectedIndex(0);
        termineeCheckBox.setSelected(false);
        
        updateButton.setEnabled(false);
        createButton.setEnabled(false);
        deleteButton.setEnabled(false);
    }
    
    private void setEvent(ListDTO ev){
        
        //affectation des variables de l'objet event, variable d'instance de notre classe jframe
        listdto.setTache(tacheField.getText());
        listdto.setCategorie((String) categorieComboBox.getSelectedItem());
        listdto.setTerminee(termineeCheckBox.isSelected()); 
        
        //si les données proviennent d'une ligne existante dans notre tableau, on peut alors renseigner l'id
        //getSelectedRow nous renvoie -1 si aucune ligne n'est sélectionnée
        if (listTable.getSelectedRow() != -1){
            listdto.setTdId(mapRowId.get(listTable.getSelectedRow()));   
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> categorieComboBox;
    private javax.swing.JLabel categorieLabel;
    private javax.swing.JButton createButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel idLabel;
    private javax.swing.JTextField idTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable listTable;
    private javax.swing.JButton razButton;
    private javax.swing.JTextField tacheField;
    private javax.swing.JLabel tacheLabel;
    private javax.swing.JCheckBox termineeCheckBox;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables

}
