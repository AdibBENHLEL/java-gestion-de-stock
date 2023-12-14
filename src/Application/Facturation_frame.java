package Application;


import Application.Main_Gestion_de_stock;
import javax.swing.WindowConstants;
import base_de_donneés.Parametre_BDD_Class;
import base_de_donneés.View_Resultes_TAB_BDD_GDSA_ABH_Classe;
import base_de_donneés.Base_des_donneé_GSA_ABH_Class;
import com.mysql.cj.xdevapi.Table;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.PrintJob;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.table.DefaultTableModel;

public class Facturation_frame extends javax.swing.JFrame {
    public void init(){
        setTitle("gestion de stock By BEN HLEL ADIB  ");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//for exit frame
        setVisible(true);
    }
    ResultSet rs,rscountligne,rF;
    Base_des_donneé_GSA_ABH_Class BDD;
    public Facturation_frame() throws SQLException {
        BDD = new Base_des_donneé_GSA_ABH_Class(new Parametre_BDD_Class().HOST_DB, new Parametre_BDD_Class().USERNAME_DB, new Parametre_BDD_Class().PASSWORD_DB, new Parametre_BDD_Class().IPHOST, new Parametre_BDD_Class().PORT);
        initComponents();
        jam();
        table("gestion_des_produits");
        table("gestion_facturation");
        actualiser();
        
    }
    void Verif_existe(String Tabname,String valeur,String colone_cher)throws SQLException{
            rs = BDD.querySelectAll(Tabname, colone_cher+" LIKE '%" + valeur+ "%' ");
            String st="SELECT COUNT(*) FROM "+Tabname+" WHERE " +colone_cher+"=" + valeur;
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
                        TabP.setModel(model);
                        
                        

                        
                    }
                    else{
                    try {
                        TabRecherche_show(Tabname,rs);
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(Gestion_des_produits_Frame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                     } }
            }catch (SQLException ex) {
                Logger.getLogger(Gestion_des_produits_Frame.class.getName()).log(Level.SEVERE, null, ex);
            }}
     void TabRecherche_show(String tabname,ResultSet rs)throws SQLException{
        String t[] = {"ID","Code_produit","Nom_Produit","Forniseur","Prix","Quantite"};
        String t2[] = {"ID_operation","ID_Facture","Code_Produit","Nom_Produit","Prix_vente","Quantite","Total_unitaire"};
        if (rs == null) {
             System.out.println("Error retrieving data from database  ");           
        }
        System.out.println(rs);
        try{
        DefaultTableModel model = new DefaultTableModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(t); // Définit les en-têtes de colonne
        if (tabname.equals("gestion_des_produits")){
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
                TabP.setModel(model);
                }
                int rowCount = TabP.getRowCount();
                int columnCount = TabP.getColumnCount();
                System.out.println(rowCount);
                System.out.println(columnCount);}
        else if(tabname.equals("gestion_facturation")){
                model.setColumnIdentifiers(t2); // Définit les en-têtes de colonne
                while (rs.next()){
                int id_operation=rs.getInt(1);
                int id_Facture=rs.getInt(2);
                int Code_produit=rs.getInt(3);
                String Nom_produit=rs.getString(4);
                float Prix=rs.getFloat(5);
                int Quantite=rs.getInt(6);
                float Totalunitaire=rs.getFloat(7);
                model.addRow(new Object[]{id_operation,id_Facture,Code_produit,Nom_produit,Prix,Quantite,Totalunitaire});
                JTable table = new JTable(model);
                FacTable.setModel(model);
                }
                int rowCount = FacTable.getRowCount();
                int columnCount = FacTable.getColumnCount();
                System.out.println(rowCount);
                System.out.println(columnCount);}
        }catch(Exception e)
        {    System.out.println("problem");
            System.out.println(e.getMessage());
        }}


    void actualiser() {
        ch_CodeProduit.setText("");
        NomProd.setText("");
        ch_Prix.setText("");
        ch_Quantite.setText("");
        ch_NumFact.setText("");
        }
    
 public void table(String NONTAB) throws SQLException {
       String t[] = {"ID","Code_produit","Nom_Produit","Forniseur","Prix","Quantite"};
       String t2[] = {"ID_operation","ID_Facture","Code_Produit","Nom_Produit","Prix_vente","Quantite","Total_unitaire"};
       DefaultTableModel model = new DefaultTableModel();
       model.setRowCount(0);
        if (NONTAB.equals("gestion_des_produits"))
        {   rs = BDD.querySelect( t,NONTAB);
            if (rs == null) {
                System.out.println("Error retrieving data from database");
            }
            try{
                model.setColumnIdentifiers(t);
                while (rs.next()){
                int id=rs.getInt(1);
                int Code_produit=rs.getInt(2);
                String Nom_produit=rs.getString(3);
                String Forniseur=rs.getString(4);
                float Prix=rs.getFloat(5);
                int Quantite=rs.getInt(6);        
                model.addRow(new Object[]{id,Code_produit,Nom_produit,Forniseur,Prix,Quantite});
                JTable table = new JTable(model);
                TabP.setModel(model);
                }}catch(Exception e)
                    {System.out.println("problem");
                    System.out.println(e.getMessage());
                    }           
        }else if (NONTAB.equals("gestion_facturation")) {
            rs = BDD.querySelect( t2,NONTAB);
            if (rs == null) {
                System.out.println("Error retrieving data from database");
            }
            try{
                model.setColumnIdentifiers(t2);// Définit les en-têtes de colonne
                while (rs.next()){
                int id_operation=rs.getInt(1);
                int id_facture=rs.getInt(2);
                int Code_produit=rs.getInt(3);
                String Nom_produit=rs.getString(4);
                float Prix=rs.getFloat(5);
                int Quantite=rs.getInt(6);      
                float Total=rs.getFloat(7);
                model.addRow(new Object[]{id_operation,id_facture,Code_produit,Nom_produit,Prix,Quantite,Total});
                JTable table = new JTable(model);
                FacTable.setModel(model);}}
                catch(Exception e)
                    {System.out.println("problem");
                    System.out.println(e.getMessage());
                    }   
                
        }}


     public void jam() {
        Date s = new Date();
        SimpleDateFormat tgl = new SimpleDateFormat("EEEE-dd-MMM-yyyy");
        SimpleDateFormat jam = new SimpleDateFormat("HH:mm");
        DATE.setText(jam.format(s));
        TIME.setText(tgl.format(s));}
     
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        FacTable = new javax.swing.JTable();
        Recherche_ComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        Rechercher_Button = new javax.swing.JButton();
        NomProd = new javax.swing.JTextField();
        ch_CodeProduit = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TabP = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        Total_Label = new javax.swing.JLabel();
        Retour_Button1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        Imprimer = new javax.swing.JButton();
        ch_Quantite = new javax.swing.JTextField();
        ch_Prix = new javax.swing.JTextField();
        ch_NumFact = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        TIME = new javax.swing.JLabel();
        DATE = new javax.swing.JLabel();
        Exit = new javax.swing.JButton();
        Actualiser_tabprod = new javax.swing.JButton();
        Actualiser_tabFacture = new javax.swing.JButton();
        clean = new javax.swing.JButton();
        ch_recherche = new javax.swing.JTextField();
        message = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel7.setBackground(new java.awt.Color(153, 51, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 0, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Facturation");

        FacTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_operation", "ID_Facture", "Code_Produit", "Nom_Produit", "Prix_vente", "Quantite", "Total_unitaire"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        FacTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FacTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(FacTable);

        Recherche_ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Code_produit", "Nom", "Forniseur", "Prix", "Quantité", " " }));
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

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Recherche par catégorie");

        Rechercher_Button.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Rechercher_Button.setText("Rechercher");
        Rechercher_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rechercher_ButtonActionPerformed(evt);
            }
        });

        NomProd.setBackground(new java.awt.Color(0, 153, 255));
        NomProd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NomProd.setDisabledTextColor(new java.awt.Color(102, 102, 255));
        NomProd.setSelectedTextColor(new java.awt.Color(102, 102, 255));
        NomProd.setSelectionColor(new java.awt.Color(255, 153, 0));
        NomProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NomProdActionPerformed(evt);
            }
        });

        ch_CodeProduit.setBackground(new java.awt.Color(0, 153, 255));
        ch_CodeProduit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ch_CodeProduit.setDisabledTextColor(new java.awt.Color(102, 102, 255));
        ch_CodeProduit.setSelectedTextColor(new java.awt.Color(102, 102, 255));
        ch_CodeProduit.setSelectionColor(new java.awt.Color(255, 153, 0));
        ch_CodeProduit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ch_CodeProduitActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Code_produit");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Quantité");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Ajouter produit au facture");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        TabP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Code_produit", "Nom_Produit", "Forniseur", "Prix", "Quantite"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TabP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabPMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TabP);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("numero de facture");

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Supprimer produit d'une facture");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("Rechercher produit dans une facture");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("TOTAL FACTURE :");

        Total_Label.setBackground(new java.awt.Color(0, 51, 204));
        Total_Label.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        Total_Label.setForeground(new java.awt.Color(255, 0, 0));
        Total_Label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        Retour_Button1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Retour_Button1.setText("Retour");
        Retour_Button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Retour_Button1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("All Rights reserverd -2023- APP created BY BEN HLEL ADIB");

        Imprimer.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Imprimer.setText("Imprimer ");
        Imprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImprimerActionPerformed(evt);
            }
        });

        ch_Quantite.setBackground(new java.awt.Color(0, 153, 255));
        ch_Quantite.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ch_Quantite.setDisabledTextColor(new java.awt.Color(102, 102, 255));
        ch_Quantite.setSelectedTextColor(new java.awt.Color(102, 102, 255));
        ch_Quantite.setSelectionColor(new java.awt.Color(255, 153, 0));
        ch_Quantite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ch_QuantiteActionPerformed(evt);
            }
        });

        ch_Prix.setBackground(new java.awt.Color(0, 153, 255));
        ch_Prix.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ch_Prix.setDisabledTextColor(new java.awt.Color(102, 102, 255));
        ch_Prix.setSelectedTextColor(new java.awt.Color(102, 102, 255));
        ch_Prix.setSelectionColor(new java.awt.Color(255, 153, 0));
        ch_Prix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ch_PrixActionPerformed(evt);
            }
        });

        ch_NumFact.setBackground(new java.awt.Color(0, 153, 255));
        ch_NumFact.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ch_NumFact.setDisabledTextColor(new java.awt.Color(102, 102, 255));
        ch_NumFact.setSelectedTextColor(new java.awt.Color(102, 102, 255));
        ch_NumFact.setSelectionColor(new java.awt.Color(255, 153, 0));
        ch_NumFact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ch_NumFactActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Prix");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Nom_Produit");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Application/O57tTgmpqO78CwSDhvS2.png"))); // NOI18N
        jLabel11.setText("jLabel11");

        TIME.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TIME.setForeground(new java.awt.Color(0, 102, 255));
        TIME.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        DATE.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DATE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        Exit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Exit.setText("EXIT");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });

        Actualiser_tabprod.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Actualiser_tabprod.setText("Actualiser");
        Actualiser_tabprod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Actualiser_tabprodActionPerformed(evt);
            }
        });

        Actualiser_tabFacture.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Actualiser_tabFacture.setText("Actualiser");
        Actualiser_tabFacture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Actualiser_tabFactureActionPerformed(evt);
            }
        });

        clean.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        clean.setText("Clean");
        clean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanActionPerformed(evt);
            }
        });

        ch_recherche.setBackground(new java.awt.Color(0, 153, 255));
        ch_recherche.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ch_recherche.setDisabledTextColor(new java.awt.Color(102, 102, 255));
        ch_recherche.setSelectedTextColor(new java.awt.Color(102, 102, 255));
        ch_recherche.setSelectionColor(new java.awt.Color(255, 153, 0));
        ch_recherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ch_rechercheActionPerformed(evt);
            }
        });

        message.setBackground(new java.awt.Color(242, 242, 242));
        message.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        message.setForeground(new java.awt.Color(255, 51, 51));
        message.setDisabledTextColor(new java.awt.Color(102, 102, 255));
        message.setSelectedTextColor(new java.awt.Color(102, 102, 255));
        message.setSelectionColor(new java.awt.Color(255, 153, 0));
        message.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                messageActionPerformed(evt);
            }
        });

        jLabel10.setForeground(new java.awt.Color(255, 0, 51));
        jLabel10.setText("RQ: la quantité doit etre inféreur aux max de stock");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Retour_Button1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Total_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Imprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(TIME, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                            .addComponent(DATE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(48, 48, 48)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addContainerGap(54, Short.MAX_VALUE)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(29, 29, 29)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(128, 128, 128)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(5, 5, 5)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel2)
                                                .addComponent(jLabel9))
                                            .addGap(49, 49, 49)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(NomProd, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(ch_CodeProduit, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addComponent(jLabel4)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(65, 65, 65)))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(ch_Quantite, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(ch_NumFact, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(ch_Prix, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(35, 35, 35)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(clean)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(Actualiser_tabprod))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jButton3)
                                                    .addGap(203, 203, 203)
                                                    .addComponent(Actualiser_tabFacture))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jButton1)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jButton2))))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(48, 48, 48)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(18, 18, 18)
                                    .addComponent(Recherche_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(ch_recherche, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(Rechercher_Button)
                                    .addGap(18, 18, 18)
                                    .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 37, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 741, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(DATE, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(TIME, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Recherche_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Rechercher_Button)
                    .addComponent(ch_recherche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ch_CodeProduit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NomProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(ch_Prix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ch_Quantite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(clean)
                            .addComponent(Actualiser_tabprod))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ch_NumFact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3)
                        .addComponent(Actualiser_tabFacture)))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(Imprimer)
                        .addComponent(Exit)
                        .addComponent(Retour_Button1))
                    .addComponent(Total_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jLabel6)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Rechercher_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rechercher_ButtonActionPerformed
if (ch_recherche.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "SVP entrer quelque chose");
        } else {
            if (Recherche_ComboBox.getSelectedItem().equals("Code_produit")) {
                try {
                    Verif_existe("gestion_des_produits",ch_recherche.getText(),"Code_produit");
                } catch (SQLException ex) {
                    Logger.getLogger(Gestion_des_produits_Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (Recherche_ComboBox.getSelectedItem().equals("ID")) {
                try {
                    Verif_existe("gestion_des_produits",ch_recherche.getText(),"ID");
                } catch (SQLException ex) {
                    Logger.getLogger(Gestion_des_produits_Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (Recherche_ComboBox.getSelectedItem().equals("Nom_Produit")) {
                try {
                    Verif_existe("gestion_des_produits",ch_recherche.getText(),"Nom_Produit");
                } catch (SQLException ex) {
                    Logger.getLogger(Gestion_des_produits_Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (Recherche_ComboBox.getSelectedItem().equals("Forniseur")) {
                try {
                    Verif_existe("gestion_des_produits",ch_recherche.getText(),"Forniseur");
                } catch (SQLException ex) {
                    Logger.getLogger(Gestion_des_produits_Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (Recherche_ComboBox.getSelectedItem().equals("Prix")) {
                try {
                    Verif_existe("gestion_des_produits",ch_recherche.getText(),"Prix");
                } catch (SQLException ex) {
                    Logger.getLogger(Gestion_des_produits_Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (Recherche_ComboBox.getSelectedItem().equals("Quantite")) {
                try {
                    Verif_existe("gestion_des_produits",ch_recherche.getText(),"Quantite");
                } catch (SQLException ex) {
                    Logger.getLogger(Gestion_des_produits_Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
    }}}





    {       // TODO add your handling code here:
    }//GEN-LAST:event_Rechercher_ButtonActionPerformed

    private void Recherche_ComboBoxAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_Recherche_ComboBoxAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_Recherche_ComboBoxAncestorAdded

    private void Recherche_ComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Recherche_ComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Recherche_ComboBoxActionPerformed

    private void NomProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NomProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NomProdActionPerformed

    private void ch_CodeProduitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ch_CodeProduitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ch_CodeProduitActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String id = String.valueOf(FacTable.getValueAt(FacTable.getSelectedRow(), 0));//faire selectionner tout une ligne
        if (JOptionPane.showConfirmDialog(this, "est ce que tu es sure que tu veux suuprimer", "attention!!!", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            System.out.println(id);
            BDD.queryDelete("gestion_facturation","ID_operation=" +id);
            try {
                table("gestion_facturation");
                JOptionPane.showMessageDialog(this, "Supprime valideé");
                // TODO add your handling code here:
            } catch (SQLException ex) {
                Logger.getLogger(Gestion_des_produits_Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return;
        }
        


    }
    {       // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        float S;
        if (ch_NumFact.getText().equals("")){
        JOptionPane.showMessageDialog(this, "SVP Remarque: il faux entrer l'ID de Facture ");}
        else {
                try {
                    Verif_existe("gestion_facturation",ch_NumFact.getText(),"ID_Facture");
                    S= BDD.somme("SELECT SUM(Total_unitaire) FROM gestion_facturation WHERE ID_Facture="+ch_NumFact.getText());
                    Total_Label.setText(Float.toString(S));
                } catch (SQLException ex) {
                    Logger.getLogger(Gestion_des_produits_Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
        
        
        
        
        }
        }
    {       // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void Retour_Button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Retour_Button1ActionPerformed
        Main_Gestion_de_stock MGS=new Main_Gestion_de_stock();
        MGS.init();
        this.dispose();
}


{
// TODO add your handling code here:
    }//GEN-LAST:event_Retour_Button1ActionPerformed

    private void ImprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImprimerActionPerformed
        MessageFormat m=new MessageFormat("Facture"+"   Num Facture:"+ch_NumFact.getText()+"      Total:"+Total_Label.getText());
        MessageFormat d=new MessageFormat("Date:"+DATE.getText()+"Time"+TIME.getText());
        if (JOptionPane.showConfirmDialog(this, "il faut indiquer l id de facture et cliquer sur chrecher pour faire la somme", "attention!!!", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
        try{
                FacTable.print(PrintMode.NORMAL, m, d);
                /*
                Toolkit toolkitpardefaut=Toolkit.getDefaultToolkit();//intermidaire entre soft et hard
                PrintJob pj=toolkitpardefaut.getPrintJob(this,"impresion_Facture", null);
                Graphics g=pj.getGraphics();
                jLabel11.paintAll(g);
                pj.end();*/
                
        }
        catch(java.awt.print.PrinterException e){
            System.err.format(e.getMessage());
            JOptionPane.showMessageDialog(this, "Problem d imprision");}
        }
    }

          
        
        
{      
    }//GEN-LAST:event_ImprimerActionPerformed

    private void ch_QuantiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ch_QuantiteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ch_QuantiteActionPerformed

    private void ch_PrixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ch_PrixActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ch_PrixActionPerformed

    private void ch_NumFactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ch_NumFactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ch_NumFactActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
this.dispose();     }//GEN-LAST:event_ExitActionPerformed

    private void Actualiser_tabprodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Actualiser_tabprodActionPerformed
        try {
                table("gestion_des_produits");
                JOptionPane.showMessageDialog(this, "Run Acctualisation table produits");
                // TODO add your handling code here:
            } catch (SQLException ex) {
                Logger.getLogger(Gestion_des_produits_Frame.class.getName()).log(Level.SEVERE, null, ex);
            }



        // TODO add your handling code here:
    }//GEN-LAST:event_Actualiser_tabprodActionPerformed

    private void Actualiser_tabFactureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Actualiser_tabFactureActionPerformed
        try {
                table("gestion_facturation");
                JOptionPane.showMessageDialog(this, "Run Acctualisation table Facture");
                // TODO add your handling code here:
            } catch (SQLException ex) {
                Logger.getLogger(Gestion_des_produits_Frame.class.getName()).log(Level.SEVERE, null, ex);
            }// TODO add your handling code here:
    }//GEN-LAST:event_Actualiser_tabFactureActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
if (NomProd.getText().equals("") || ch_CodeProduit.getText().equals("")
                || ch_Prix.getText().equals("") || ch_Quantite.getText().equals("")|| ch_NumFact.getText().equals("")
                ) {
                    JOptionPane.showMessageDialog(this, "SVP entrer les informations complete");
                  }
        else {
            String t[] = {"ID","Code_produit","Nom_Produit","Forniseur","Prix","Quantite"};
            String t1[] = {"Quantite"};
            String Quantite = String.valueOf(TabP.getValueAt(TabP.getSelectedRow(), 5));//faire 
            String colon[] = {"ID_Facture","Code_Produit","Nom_Produit","Prix_vente","Quantite","Total_unitaire"};
            String[] DATA_SAISIE = {ch_NumFact.getText(),ch_CodeProduit.getText(),NomProd.getText(),ch_Prix.getText(),ch_Quantite.getText(),String.valueOf(Double.parseDouble(ch_Prix.getText()) * Double.parseDouble(ch_Quantite.getText()))};
            if (Integer.parseInt(ch_Quantite.getText())<=Integer.parseInt(Quantite)){          
                System.out.println(BDD.queryInsert("gestion_facturation", colon, DATA_SAISIE));
                int d=Integer.parseInt(Quantite)-Integer.parseInt(ch_Quantite.getText());
                String[] miseajour = {Integer.toString(d)};
                System.out.println(BDD.queryUpdate("gestion_des_produits", t1, miseajour,"Quantite="+Quantite));
                try{
                table("gestion_des_produits");   
                table("gestion_facturation");
                actualiser();
                JOptionPane.showMessageDialog(this, "Ajout avec succueé dans la BDD ");
                }
                catch(SQLException e){
                System.out.println("problem");
                System.out.println(e.getMessage());
                }}
            else {
                JOptionPane.showMessageDialog(this, "Attention la quantite de produits est insuffisante");
            }}
       }
{
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanActionPerformed

        actualiser();
        // TODO add your handling code here:
    }//GEN-LAST:event_cleanActionPerformed

    private void ch_rechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ch_rechercheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ch_rechercheActionPerformed

    private void messageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_messageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_messageActionPerformed

    private void TabPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabPMouseClicked
        ch_CodeProduit.setText(String.valueOf(TabP.getValueAt(TabP.getSelectedRow(), 1)));
        NomProd.setText(String.valueOf(TabP.getValueAt(TabP.getSelectedRow(), 2)));
        ch_Prix.setText(String.valueOf(TabP.getValueAt(TabP.getSelectedRow(), 4)));
        // TODO add your handling code here:
    }//GEN-LAST:event_TabPMouseClicked

    private void FacTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FacTableMouseClicked
        ch_NumFact.setText(String.valueOf(FacTable.getValueAt(FacTable.getSelectedRow(), 1)));
        // TODO add your handling code here:
    }//GEN-LAST:event_FacTableMouseClicked

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
            java.util.logging.Logger.getLogger(Facturation_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Facturation_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Facturation_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Facturation_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Facturation_frame().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Facturation_frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Actualiser_tabFacture;
    private javax.swing.JButton Actualiser_tabprod;
    private javax.swing.JLabel DATE;
    private javax.swing.JButton Exit;
    private javax.swing.JTable FacTable;
    private javax.swing.JButton Imprimer;
    private javax.swing.JTextField NomProd;
    private javax.swing.JComboBox<String> Recherche_ComboBox;
    private javax.swing.JButton Rechercher_Button;
    private javax.swing.JButton Retour_Button1;
    private javax.swing.JLabel TIME;
    private javax.swing.JTable TabP;
    private javax.swing.JLabel Total_Label;
    private javax.swing.JTextField ch_CodeProduit;
    private javax.swing.JTextField ch_NumFact;
    private javax.swing.JTextField ch_Prix;
    private javax.swing.JTextField ch_Quantite;
    private javax.swing.JTextField ch_recherche;
    private javax.swing.JButton clean;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField message;
    // End of variables declaration//GEN-END:variables

    private String toString(int d) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
