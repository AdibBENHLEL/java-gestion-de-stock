package base_de_donneés;

import java.net.Socket;
import java.sql.*;



public class Base_des_donneé_GSA_ABH_Class {
    Connection connection;
    Statement statement;
    String SQL;
   
    
    String url;
    String username;
    String password;
    Socket client;
    int Port;
    String Host;

    //constructeur de la class Base_des_donneé_GSA_ABH_Class
    public Base_des_donneé_GSA_ABH_Class(String url, String username, String password, String Host, int Port) {
          this.url = url;
          this.username = username;
          this.password = password;
          this.Host = Host;
          this.Port = Port;
      }
    
    
    //methode pour faire connecter la base des donneés
    public Connection connexionDatabase() {
        try {
                //on utulise la bibliotheque mysql jdbc driver 
                //Driver class qui hérite de la class exception 
              Class.forName("com.mysql.cj.jdbc.Driver");
              connection =(Connection) DriverManager.getConnection(url, username, password);
        }catch (Exception e){
            System.err.println(e.getMessage());//e.getMessage() c'est pour determiner ou est le problem 
            }
        return connection;
        }
    
    
    //methode pour  déconnecter a la base des donneés
    public Connection closeconnexion() {

        try {
            connection.close();
        } catch (Exception e) {System.err.println(e);//
        }
        return connection;
    }
    
    //methode pour excution de requete 
    public ResultSet exécutionQuery(String sql) {
        connexionDatabase();//appele a la methode connexionDatabase() pour se connecter a la BDD
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();//creation de requete sql
            resultSet = statement.executeQuery(sql);//faire exucuter
            System.out.println(sql);//affichage
        } catch (SQLException ex) {System.err.println(ex);//lancer un exeption vis a vis aux requete 
        }
        return resultSet;
    }
    
    //methode pour faire l'apdate d'une  requete
    public String exécutionUpdate(String sql) {
        connexionDatabase();
        String result = "";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            result = sql;

        } catch (SQLException ex) {
            result = ex.toString();
        }
        return result;
    }
    //pour faire la selection pour tout le tab
    
    public ResultSet querySelectAll(String nomTable) {

        connexionDatabase();//connecter a la bdd
        SQL = "SELECT * FROM " + nomTable;//str pour la requete sql 
        System.out.println(SQL);//simple affichage
        return this.exécutionQuery(SQL);//appel au methode exécutionQuery() pour exucuter la requete sql

    }
        //pour faire la selection pour tout le tab avec condition wherre etat
    public ResultSet querySelectAll(String nomTable, String état) {
        
        connexionDatabase();
        SQL = "SELECT * FROM " + nomTable + " WHERE " + état;
        return this.exécutionQuery(SQL);
        
    }
    
    //pour faire tout les ligne qui correspant a une colonne entre en paramétre avec le non de tab 
    public ResultSet querySelect(String[] nomColonne, String nomTable) {

        connexionDatabase();
        int i;
        SQL = "SELECT ";

        for (i = 0; i <= nomColonne.length - 1; i++) {
            SQL += nomColonne[i];
            if (i < nomColonne.length - 1) {
                SQL += ",";
            }
        }

        SQL += " FROM " + nomTable;
        return this.exécutionQuery(SQL);

    }
    
public float somme(String sql) {
    float sum = 0.0f;
    try {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            sum += resultSet.getFloat(1);
        }

        resultSet.close();
        statement.close();
    } catch (SQLException ex) {
        System.err.println(ex);
    }

    return sum;
}
    

    //pour faire tout les ligne qui correspant a une colonne entre en paramétre avec le non de tab 
    public ResultSet fcSelectCommand(String[] nomColonne, String nomTable, String état) {

        connexionDatabase();//connecter a la BDD
        int i;
        SQL = "SELECT ";

        for (i = 0; i <= nomColonne.length - 1; i++) {
            SQL += nomColonne[i];
            if (i < nomColonne.length - 1) {
                SQL += ",";// select nomColonne[0],nomColonne[1],...nomColonne[nomColonne.length - 1]
            }
        }

        SQL += " FROM " + nomTable + " WHERE " + état;// select nomColonne[0],nomColonne[1],...nomColonne[nomColonne.length - 1] from nomTable where condition  
        return this.exécutionQuery(SQL);//exucuter la requetete 

    }
    //pour insere un ligne contenuTableau  pour un tableux nomTable  
    public String queryInsert(String nomTable, String[] contenuTableau) {// String[] contenuTableau car donner entre d'une interface Frame

        connexionDatabase();
        int i;
        SQL = "INSERT INTO " + nomTable + " VALUES(";

        for (i = 0; i <= contenuTableau.length - 1; i++) {
            SQL += "'" + contenuTableau[i] + "'";
            if (i < contenuTableau.length - 1) {
                SQL += ",";
            }
        }

        SQL += ")";//INSERT INTO "nomTable" VALUES("'contenuTableau[0]',contenuTableau[1]...contenuTableau[contenuTableau.length - 1]")
        return this.exécutionUpdate(SQL);// faire l'apdate de tab

    }

    //pour insere des valeur a une seul colonne passer en parametre  dans  un tableux nomTable 
    public String queryInsert(String nomTable, String[] nomColonne, String[] contenuTableau) {

        connexionDatabase();
        int i;
        SQL = "INSERT INTO " + nomTable + "(";
        for (i = 0; i <= nomColonne.length - 1; i++) {
            SQL += nomColonne[i];
            if (i < nomColonne.length - 1) {
                SQL += ",";
            }
        }
        //INSERT INTO nomTable( ID[0],Id[1]
        SQL += ") VALUES(";
        for (i = 0; i <= contenuTableau.length - 1; i++) {
            SQL += "'" + contenuTableau[i] + "'";
            if (i < contenuTableau.length - 1) {
                SQL += ",";
            }
        }
        //INSERT INTO nomTable( ID[0],Id[1]) values (5,9
        SQL += ")";//INSERT INTO nomTable( ID[0],Id[1]) values (5,9)
        return this.exécutionUpdate(SQL);// faire l'apdate de tab

    }

      //faire l'update des lignes d'un seul chans avec condition 
    public String queryUpdate(String nomTable, String[] nomColonne, String[] contenuTableau, String état) {

        connexionDatabase();//connecter a la BDD
        int i;
        SQL = "UPDATE " + nomTable + " SET ";
        
        for (i = 0; i <= nomColonne.length - 1; i++) {
            SQL += nomColonne[i] + "='" + contenuTableau[i] + "'";
            if (i < nomColonne.length - 1) {
                SQL += ",";
            }
        }
        // "UPDATE nomTable SET nomColonne[0]='contenuTableau[0]',nomColonne[1]='contenuTableau[1]'
        SQL += " WHERE " + état;//"UPDATE nomTable SET nomColonne[0]='contenuTableau[0]',nomColonne[1]='contenuTableau[1]' wherre condition
        return this.exécutionUpdate(SQL);

    }

    // delete un ligne d'un tableux 
    public String queryDelete(String nomtable) {

        connexionDatabase();
        SQL = "DELETE FROM " + nomtable;
        return this.exécutionUpdate(SQL);

    }

    // delete avec condition 
    public String queryDelete(String nomTable, String état) {

        connexionDatabase();
        SQL = "DELETE FROM " + nomTable + " WHERE " + état;
        return this.exécutionUpdate(SQL);

    }
}
