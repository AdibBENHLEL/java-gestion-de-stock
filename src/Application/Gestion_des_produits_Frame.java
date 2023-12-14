package Application;

import base_de_donneés.Parametre_BDD_Class;
import base_de_donneés.Base_des_donneé_GSA_ABH_Class;
import base_de_donneés.View_Resultes_TAB_BDD_GDSA_ABH_Classe;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;




public final  class Gestion_des_produits_Frame extends javax.swing.JFrame 
{     
        ResultSet rs,rscountligne ;
    
    
   // ResultSet rs;
      Base_des_donneé_GSA_ABH_Class BDD;

    public void init(){
        setTitle("gestion_des_produit ");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//for exit frame
        setVisible(true);
    }
    
    public Gestion_des_produits_Frame() throws SQLException
    {   BDD = new Base_des_donneé_GSA_ABH_Class(new Parametre_BDD_Class().HOST_DB, new Parametre_BDD_Class().USERNAME_DB, new Parametre_BDD_Class().PASSWORD_DB, new Parametre_BDD_Class().IPHOST, new Parametre_BDD_Class().PORT);
        initComponents();
        table();
    }
     public void table() throws SQLException {
       String t[] = {"ID","Code_produit","Nom_Produit","Forniseur","Prix","Quantite"};
        rs = BDD.querySelect( t,"gestion_des_produits");
        if (rs == null) {
             System.out.println("Error retrieving data from database");
        }
        System.out.println(rs);
        try{
        DefaultTableModel model = new DefaultTableModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(t); // Définit les en-têtes de colonne
        while (rs.next()){
        int id=rs.getInt(1);
        int Code_produit=rs.getInt(2);
        String Nom_produit=rs.getString(3);
        String Forniseur=rs.getString(4);
        float Prix=rs.getFloat(5);
        int Quantite=rs.getInt(6);        
        model.addRow(new Object[]{id,Code_produit,Nom_produit,Forniseur,Prix,Quantite});
        JTable table = new JTable(model);
        message.setText("table remplir avec succ dans la Frame");
        Tab.setModel(model);
        }
        int rowCount = Tab.getRowCount();
        int columnCount = Tab.getColumnCount();
        System.out.println(rowCount);
        System.out.println(columnCount);
        }catch(Exception e)
        {    System.out.println("problem");
            System.out.println(e.getMessage());
        }
}
     
    void Verif_existe(String valeur,String colone_cher)throws SQLException{
        String st;
            rs = BDD.querySelectAll("gestion_des_produits", colone_cher+" LIKE '%" + valeur+ "%' ");
            if (colone_cher.equals("Nom_Produit") || (colone_cher.equals("Forniseur"))){
                st="SELECT COUNT(*) FROM gestion_des_produits WHERE " +colone_cher+" LIKE '%" + valeur+"%' ";
            }
            else{
                st="SELECT COUNT(*) FROM gestion_des_produits WHERE " +colone_cher+"=" + valeur;
            }
            rscountligne=BDD.exécutionQuery(st);
            try {
                if (rscountligne.next()) {
                    int count = rscountligne.getInt(1);
                    if (count == 0) {
                        message.setText("Le recherche n'existe pas\n dans la base de données.");
                        DefaultTableModel model = new DefaultTableModel();
                        model.setRowCount(0);
                        model.addRow(new Object[]{"","","","",""});
                        JTable table = new JTable(model);
                        Tab.setModel(model);

                        
                    }
                    else{
                    try {
                        TabRecherche_show(rs);
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(Gestion_des_produits_Frame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                     } }
            }catch (SQLException ex) {
                Logger.getLogger(Gestion_des_produits_Frame.class.getName()).log(Level.SEVERE, null, ex);
            }}
     void TabRecherche_show(ResultSet rs)throws SQLException{
        String t[] = {"ID","Code_produit","Nom_Produit","Forniseur","Prix","Quantite"};
        if (rs == null) {
             System.out.println("Error retrieving data from database  ");           
        }
        System.out.println(rs);
        try{
        DefaultTableModel model = new DefaultTableModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(t); // Définit les en-têtes de colonne
        while (rs.next()){
        int id=rs.getInt(1);
        int Code_produit=rs.getInt(2);
        String Nom_produit=rs.getString(3);
        String Forniseur=rs.getString(4);
        float Prix=rs.getFloat(5);
        int Quantite=rs.getInt(6);        
        model.addRow(new Object[]{id,Code_produit,Nom_produit,Forniseur,Prix,Quantite});
        JTable table = new JTable(model);
        message.setText("table remplir avec succ dans la Frame");
        Tab.setModel(model);
        }
        int rowCount = Tab.getRowCount();
        int columnCount = Tab.getColumnCount();
        System.out.println(rowCount);
        System.out.println(columnCount);
        }catch(Exception e)
        {    System.out.println("problem");
            System.out.println(e.getMessage());
        }}
     
     
     public boolean verifcode(String noncolon,String valeur)
     {
       String st="SELECT COUNT(*) FROM gestion_des_produits WHERE " +noncolon+"=" + valeur;
            rscountligne=BDD.exécutionQuery(st);
            try {
                if (rscountligne.next()) {
                    int count = rscountligne.getInt(1);
                    if (count == 0) {
                        message.setText("verification validee. produit n'existe pas dans la base de données ");
                        return true;}
                        else {  message.setText(" Attention produit existe deja  dans la base de données");
                                return false;
                             }
                }
            }catch(Exception e)
        {    System.out.println("problem");
            System.out.println(e.getMessage());
            
        }
        return false;
     }
     
     
     void actualiser() {
        ch_code.setText("");
        ch_Nom.setText("");
        ch_forniseur.setText("");
        ch_prix.setText("");
        ch_quantite.setText("");
        }
    
        
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Actualiser_Button = new javax.swing.JButton();
        Supprimer_Button = new javax.swing.JButton();
        Modifier_Button = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ch_Nom = new javax.swing.JTextField();
        ch_forniseur = new javax.swing.JTextField();
        ch_code = new javax.swing.JTextField();
        ch_prix = new javax.swing.JTextField();
        ch_quantite = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Recherche_ComboBox = new javax.swing.JComboBox<>();
        Retour_Button = new javax.swing.JButton();
        clear_Button = new javax.swing.JButton();
        message = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Rechercher_Button = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tab = new javax.swing.JTable();
        recherche = new javax.swing.JTextField();
        Ajouter_Button1 = new javax.swing.JButton();
        Exit = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setFont(new java.awt.Font("Agency FB", 1, 10)); // NOI18N
        setForeground(new java.awt.Color(102, 0, 204));
        setMinimumSize(new java.awt.Dimension(867, 480));
        setUndecorated(true);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Recherche par catégorie");

        Actualiser_Button.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Actualiser_Button.setText("Actualisé");
        Actualiser_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Actualiser_ButtonActionPerformed(evt);
            }
        });

        Supprimer_Button.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Supprimer_Button.setText("Supprimer");
        Supprimer_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Supprimer_ButtonActionPerformed(evt);
            }
        });

        Modifier_Button.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Modifier_Button.setText("Modifier");
        Modifier_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Modifier_ButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Forniseur");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Prix");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Quantité");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Nom_Produit");

        ch_Nom.setBackground(new java.awt.Color(0, 153, 255));
        ch_Nom.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ch_Nom.setDisabledTextColor(new java.awt.Color(102, 102, 255));
        ch_Nom.setSelectedTextColor(new java.awt.Color(102, 102, 255));
        ch_Nom.setSelectionColor(new java.awt.Color(255, 153, 0));
        ch_Nom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ch_NomActionPerformed(evt);
            }
        });

        ch_forniseur.setBackground(new java.awt.Color(0, 153, 255));
        ch_forniseur.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ch_forniseur.setDisabledTextColor(new java.awt.Color(102, 102, 255));
        ch_forniseur.setSelectedTextColor(new java.awt.Color(102, 102, 255));
        ch_forniseur.setSelectionColor(new java.awt.Color(255, 153, 0));
        ch_forniseur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ch_forniseurActionPerformed(evt);
            }
        });

        ch_code.setBackground(new java.awt.Color(0, 153, 255));
        ch_code.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ch_code.setDisabledTextColor(new java.awt.Color(102, 102, 255));
        ch_code.setSelectedTextColor(new java.awt.Color(102, 102, 255));
        ch_code.setSelectionColor(new java.awt.Color(255, 153, 0));
        ch_code.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ch_codeActionPerformed(evt);
            }
        });

        ch_prix.setBackground(new java.awt.Color(0, 153, 255));
        ch_prix.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ch_prix.setDisabledTextColor(new java.awt.Color(102, 102, 255));
        ch_prix.setSelectedTextColor(new java.awt.Color(102, 102, 255));
        ch_prix.setSelectionColor(new java.awt.Color(255, 153, 0));
        ch_prix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ch_prixActionPerformed(evt);
            }
        });

        ch_quantite.setBackground(new java.awt.Color(0, 153, 255));
        ch_quantite.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ch_quantite.setDisabledTextColor(new java.awt.Color(102, 102, 255));
        ch_quantite.setSelectedTextColor(new java.awt.Color(102, 102, 255));
        ch_quantite.setSelectionColor(new java.awt.Color(255, 153, 0));
        ch_quantite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ch_quantiteActionPerformed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 0, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Gestion des Produits");

        Recherche_ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Code_produit", "Nom_Produit", "Forniseur", "Prix", "Quantite", " " }));
        Recherche_ComboBox.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                Recherche_ComboBoxAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        Recherche_ComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Recherche_ComboBoxActionPerformed(evt);
            }
        });

        Retour_Button.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Retour_Button.setText("Retour");
        Retour_Button.setSelected(true);
        Retour_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Retour_ButtonActionPerformed(evt);
            }
        });

        clear_Button.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        clear_Button.setText("clear");
        clear_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_ButtonActionPerformed(evt);
            }
        });

        message.setBackground(new java.awt.Color(102, 204, 255));
        message.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        message.setForeground(new java.awt.Color(255, 51, 51));
        message.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("All Rights reserverd -- APP created BY BEN HLEL ADIB");

        Rechercher_Button.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Rechercher_Button.setText("Rechercher");
        Rechercher_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rechercher_ButtonActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Code_produit");

        Tab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Code_produit", "Nom_Produit", "Forniseur", "Prix", "Quantité"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        Tab.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                TabMouseDragged(evt);
            }
        });
        Tab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Tab);

        recherche.setBackground(new java.awt.Color(51, 153, 255));
        recherche.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        recherche.setDisabledTextColor(new java.awt.Color(102, 102, 255));
        recherche.setSelectedTextColor(new java.awt.Color(102, 102, 255));
        recherche.setSelectionColor(new java.awt.Color(255, 153, 0));
        recherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rechercheActionPerformed(evt);
            }
        });

        Ajouter_Button1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Ajouter_Button1.setText("Ajouter");
        Ajouter_Button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ajouter_Button1ActionPerformed(evt);
            }
        });

        Exit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Exit.setText("EXIT");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Application/O57tTgmpqO78CwSDhvS2.png"))); // NOI18N
        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ch_code, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ch_Nom, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ch_forniseur, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ch_quantite, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ch_prix, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(75, 75, 75)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Recherche_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(recherche, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(Rechercher_Button, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(Ajouter_Button1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(Modifier_Button)
                                        .addGap(18, 18, 18)
                                        .addComponent(Supprimer_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(Actualiser_Button)
                                        .addGap(18, 18, 18)
                                        .addComponent(clear_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(Retour_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Actualiser_Button, Modifier_Button, Supprimer_Button, clear_Button});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Modifier_Button)
                            .addComponent(Supprimer_Button)
                            .addComponent(Actualiser_Button)
                            .addComponent(clear_Button)
                            .addComponent(Ajouter_Button1)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ch_code, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ch_Nom, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ch_forniseur, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ch_prix, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ch_quantite, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(13, 13, 13)
                            .addComponent(Recherche_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(recherche, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(Rechercher_Button))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Retour_Button)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Exit))
                .addGap(7, 7, 7))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Actualiser_Button, Modifier_Button, Supprimer_Button, clear_Button});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel3, jLabel4, jLabel5, jLabel6});

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
       
                                        

    private void Actualiser_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Actualiser_ButtonActionPerformed
        actualiser(); 
            try {
                table();
                JOptionPane.showMessageDialog(this, "Run Acctualisation");
                // TODO add your handling code here:
            } catch (SQLException ex) {
                Logger.getLogger(Gestion_des_produits_Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_Actualiser_ButtonActionPerformed

    private void Supprimer_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Supprimer_ButtonActionPerformed
        String id = String.valueOf(Tab.getValueAt(Tab.getSelectedRow(), 0));//faire selectionner tout une ligne
        if (JOptionPane.showConfirmDialog(this, "est ce que tu es sure que tu veux suuprimer", "attention!!!", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            BDD.queryDelete("gestion_des_produits", "id=" + id);
        } else {
            return;
        }
            try {
                table();
                JOptionPane.showMessageDialog(this, "Supprime valideé");
                // TODO add your handling code here:
            } catch (SQLException ex) {
                Logger.getLogger(Gestion_des_produits_Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_Supprimer_ButtonActionPerformed

    private void Modifier_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Modifier_ButtonActionPerformed
        if (ch_code.getText().equals("") || ch_Nom.getText().equals("") || ch_forniseur.getText().equals("")
                || ch_prix.getText().equals("") || ch_quantite.getText().equals("")
                ) {
                    JOptionPane.showMessageDialog(this, "SVP entrer les informations complete");
                  }
        else {
            
            String colon[] = {"Code_produit","Nom_Produit","Forniseur","Prix","Quantite"};
            String[] DATA_SAISIE = {ch_code.getText(), ch_Nom.getText(), ch_forniseur.getText(),ch_prix.getText(),ch_quantite.getText()};
             String id = String.valueOf(Tab.getValueAt(Tab.getSelectedRow(), 0));
            System.out.println(BDD.queryUpdate("gestion_des_produits", colon, DATA_SAISIE, "id='" + id + "'"));
            try {
                table();
                JOptionPane.showMessageDialog(this, "Modification valideé");
            } catch (SQLException ex) {
                Logger.getLogger(Gestion_des_produits_Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
            actualiser();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_Modifier_ButtonActionPerformed

    private void ch_NomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ch_NomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ch_NomActionPerformed

    private void ch_forniseurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ch_forniseurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ch_forniseurActionPerformed

    private void ch_codeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ch_codeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ch_codeActionPerformed

    private void ch_prixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ch_prixActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ch_prixActionPerformed

    private void ch_quantiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ch_quantiteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ch_quantiteActionPerformed

    private void Recherche_ComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Recherche_ComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Recherche_ComboBoxActionPerformed

    private void Recherche_ComboBoxAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_Recherche_ComboBoxAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_Recherche_ComboBoxAncestorAdded

    private void Retour_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Retour_ButtonActionPerformed
        Main_Gestion_de_stock MGS=new Main_Gestion_de_stock();
        MGS.init();
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_Retour_ButtonActionPerformed

    private void clear_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_ButtonActionPerformed
        ch_code.setText("");
        ch_Nom.setText("");
        ch_forniseur.setText("");
        ch_prix.setText("");
        ch_quantite.setText("");
        
        // TODO add your handling code here:
    }//GEN-LAST:event_clear_ButtonActionPerformed

    private void Rechercher_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rechercher_ButtonActionPerformed

        if (recherche.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "SVP entrer quelque chose");
        } else {
            if (Recherche_ComboBox.getSelectedItem().equals("Code_produit")) {
                try {
                    Verif_existe(recherche.getText(),"Code_produit");
                } catch (SQLException ex) {
                    Logger.getLogger(Gestion_des_produits_Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (Recherche_ComboBox.getSelectedItem().equals("ID")) {
                try {
                    Verif_existe(recherche.getText(),"ID");
                } catch (SQLException ex) {
                    Logger.getLogger(Gestion_des_produits_Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (Recherche_ComboBox.getSelectedItem().equals("Nom_Produit")) {
                try {
                    Verif_existe(recherche.getText(),"Nom_Produit");
                } catch (SQLException ex) {
                    Logger.getLogger(Gestion_des_produits_Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (Recherche_ComboBox.getSelectedItem().equals("Forniseur")) {
                try {
                    Verif_existe(recherche.getText(),"Forniseur");
                } catch (SQLException ex) {
                    Logger.getLogger(Gestion_des_produits_Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (Recherche_ComboBox.getSelectedItem().equals("Prix")) {
                try {
                    Verif_existe(recherche.getText(),"Prix");
                } catch (SQLException ex) {
                    Logger.getLogger(Gestion_des_produits_Frame.class.getName()).log(Level.SEVERE, null, ex);
                }            } else if (Recherche_ComboBox.getSelectedItem().equals("Quantite")) {
                try {
                    Verif_existe(recherche.getText(),"Quantite");
                } catch (SQLException ex) {
                    Logger.getLogger(Gestion_des_produits_Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
    }}}
    
    
    
    {   // TODO add your handling code here:
    }//GEN-LAST:event_Rechercher_ButtonActionPerformed

    private void rechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rechercheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rechercheActionPerformed

    private void Ajouter_Button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ajouter_Button1ActionPerformed
    if (ch_code.getText().equals("") || ch_Nom.getText().equals("") || ch_forniseur.getText().equals("")
                || ch_prix.getText().equals("") || ch_quantite.getText().equals("")
                ) {
                    JOptionPane.showMessageDialog(this, "SVP entrer les informations complete");
                  }
        else { 
        
            if (verifcode("Code_produit",ch_code.getText())){
            String colon[] = {"Code_produit","Nom_Produit","Forniseur","Prix","Quantite"};
            String[] DATA_SAISIE = {ch_code.getText(), ch_Nom.getText(), ch_forniseur.getText(),ch_prix.getText(),ch_quantite.getText()};
            System.out.println(BDD.queryInsert("gestion_des_produits", colon, DATA_SAISIE));
            try{
            table();
            actualiser();
            JOptionPane.showMessageDialog(this, "Ajout avec succueé dans la BDD ");
            }
            catch(SQLException e){
            System.out.println("problem");
            System.out.println(e.getMessage());
            }}
       }   
    }//GEN-LAST:event_Ajouter_Button1ActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
this.dispose();    }//GEN-LAST:event_ExitActionPerformed

    private void TabMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabMouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_TabMouseDragged

    private void TabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabMouseClicked
        ch_code.setText(String.valueOf(Tab.getValueAt(Tab.getSelectedRow(), 1)));
        ch_Nom.setText(String.valueOf(Tab.getValueAt(Tab.getSelectedRow(), 2)));
        ch_forniseur.setText(String.valueOf(Tab.getValueAt(Tab.getSelectedRow(), 3)));
        ch_prix.setText(String.valueOf(Tab.getValueAt(Tab.getSelectedRow(), 4)));
        ch_quantite.setText(String.valueOf(Tab.getValueAt(Tab.getSelectedRow(), 5)));
        // TODO add your handling code here:
    }//GEN-LAST:event_TabMouseClicked

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
            java.util.logging.Logger.getLogger(Gestion_des_produits_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gestion_des_produits_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gestion_des_produits_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gestion_des_produits_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Gestion_des_produits_Frame().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Gestion_des_produits_Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Actualiser_Button;
    private javax.swing.JButton Ajouter_Button1;
    private javax.swing.JButton Exit;
    private javax.swing.JButton Modifier_Button;
    private javax.swing.JComboBox<String> Recherche_ComboBox;
    private javax.swing.JButton Rechercher_Button;
    private javax.swing.JButton Retour_Button;
    private javax.swing.JButton Supprimer_Button;
    private javax.swing.JTable Tab;
    private javax.swing.JTextField ch_Nom;
    private javax.swing.JTextField ch_code;
    private javax.swing.JTextField ch_forniseur;
    private javax.swing.JTextField ch_prix;
    private javax.swing.JTextField ch_quantite;
    private javax.swing.JButton clear_Button;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel message;
    private javax.swing.JTextField recherche;
    // End of variables declaration//GEN-END:variables
}