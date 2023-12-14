package base_de_donneés;



import com.mysql.cj.xdevapi.Statement;
import java.sql.ResultSet;
// a ResultSet is a collection of rows and columns that represents the results of a query. It is an object that provides a way to access the data returned by a query. 
//The ResultSet interface is part of the JDBC (Java Database Connectivity) API and is used to retrieve data from relational databases.

import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;


public class View_Resultes_TAB_BDD_GDSA_ABH_Classe extends AbstractTableModel {
    ResultSet rs ;
    //private final  ResultSet rs;//ligne de tab ou résultat d'une requete sql 
    
    //constructeur de class
    public View_Resultes_TAB_BDD_GDSA_ABH_Classe(ResultSet rs){
        this.rs = rs;
        
        fireTableDataChanged();
       
        }
   
    
    @Override
    public int getColumnCount() {
        try {
            if (rs == null) {
                return 0;
            } else {
                return  rs.getMetaData().getColumnCount();//fonction récursive 
            }
        } catch (SQLException e) {
            System.out.println("getColumncount  resultset generating error while getting column count");
            System.out.println(e.getMessage());
            return 0;
        }
        
      
    }
    
    @Override
    /*This function is used to retrieve the number of rows in a ResultSet object.
    The function takes no parameters and returns an integer value representing the number of rows.*/
   public int getRowCount() {
        
        try {
            if (rs == null) {
                return 0;
            } else {
                int rowCount=0 ;
                while (rs.next()) {
                     System.out.println("i count row");
                     rowCount++;
                }return  rowCount ;
            }
        } catch (SQLException e) {
            System.out.println("getrowcount resultset generating error while getting rows count");
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
    /**
     *
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    //*/
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < 0 || rowIndex > getRowCount()//negatife ou supérieur ou nbr de ligne totale 
                || columnIndex < 0 || columnIndex > getColumnCount()) {
            return null;
        }
        try {
            if (rs == null) {
                return null;
            } else {
                rs.absolute(rowIndex + 1);//the function calls the absolute() method on the ResultSet object to move the cursor to the specified row
                System.out.println(rs.getObject(columnIndex + 1));
                return rs.getObject(columnIndex + 1);
                /*is necessary because JDBC -Java Database Connectivity- uses column and row indices that start from 1, while the ResultSet object uses indices that start from 0.
                To correctly reference the desired column and row,
                you need to add 1 to the specified index when using the absolute() and getObject() methods.*/
            }
        } catch (SQLException e) {
            System.out.println("getvalueat resultset generating error while fetching rows");
            System.out.println(e.getMessage());
            return null;
        }
    }
         @Override
    public String getColumnName(int columnIndex) {
        try {
            return rs.getMetaData().getColumnName(columnIndex + 1);//getMetaData() lire le contenu 
        } catch (SQLException e) {
            System.out.println("getColumnname  resultset generating error while fetching column name");
            System.out.println(e.getMessage());
        }
        return super.getColumnName(columnIndex);//refers to the parent class of the current class
    }

        
}
  

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   