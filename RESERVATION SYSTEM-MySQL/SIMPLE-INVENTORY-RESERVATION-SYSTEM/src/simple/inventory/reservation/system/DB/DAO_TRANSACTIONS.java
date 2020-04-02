/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.inventory.reservation.system.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dzhon
 */
public class DAO_TRANSACTIONS {
    MYSQL_DB sql = new MYSQL_DB();
    public List<CLASS_TRANSACTIONS> getAllTransactions() {
        List<CLASS_TRANSACTIONS> list = new ArrayList<>();

        
        try {
            sql.createConnection();
            sql.rs = sql.st.executeQuery("Select * from transactions");
            // GET THE RESULT OF QUERY THEN ADD IT TO LIST
            while (sql.rs.next()){
                CLASS_TRANSACTIONS tempTransaction = convertRowToTransaction(sql.rs);
                list.add(tempTransaction);
            }
            // CLOSE CONECTION
            sql.rs.close();
            sql.st.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAO_TRANSACTIONS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    private CLASS_TRANSACTIONS convertRowToTransaction(ResultSet myRs) throws SQLException {
		
	int ID = myRs.getInt("ID");
	int userID = myRs.getInt("USER_ID");
	String borrowerID = myRs.getString("BORROWER_ID");
	String borrowerFNAME = myRs.getString("BORROWER_FNAME");
	String borrowerLNAME = myRs.getString("BORROWER_LNAME");
	int itemID = myRs.getInt("ITEM_ID");
	String status = myRs.getString("STATUS");
        String dateRes = myRs.getString("DATE_RES");
        String dateUpdated = myRs.getString("DATE_UPDATED");
		
		
	CLASS_TRANSACTIONS tempTransaction = new CLASS_TRANSACTIONS(ID, userID, borrowerID, borrowerFNAME, borrowerLNAME, itemID, status, dateRes, dateUpdated);
	return tempTransaction;
    }
    
    public List<CLASS_TRANSACTIONS> getAllStatus(String status) {
        List<CLASS_TRANSACTIONS> list = new ArrayList<>();

        status='%' + status + '%';
        try {
            sql.createConnection();
            sql.rs = sql.st.executeQuery("Select * from transactions where status like '"+ status +"'");
            // GET THE RESULT OF QUERY THEN ADD IT TO LIST
            while (sql.rs.next()){
                CLASS_TRANSACTIONS tempTransaction = convertRowToTransaction(sql.rs);
                list.add(tempTransaction);
            }
            // CLOSE CONECTION
            sql.rs.close();
            sql.st.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAO_TRANSACTIONS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<CLASS_TRANSACTIONS> getAllUserTransactions(int id) {
        List<CLASS_TRANSACTIONS> list = new ArrayList<>();

        
        try {
            sql.createConnection();
            sql.rs = sql.st.executeQuery("Select * from transactions where user_id="+ id +"");
            // GET THE RESULT OF QUERY THEN ADD IT TO LIST
            while (sql.rs.next()){
                CLASS_TRANSACTIONS tempTransaction = convertRowToTransaction(sql.rs);
                list.add(tempTransaction);
            }
            // CLOSE CONECTION
            sql.rs.close();
            sql.st.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAO_TRANSACTIONS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<CLASS_TRANSACTIONS> searchTransaction(int id) {
        List<CLASS_TRANSACTIONS> list = new ArrayList<>();

        try {
            sql.createConnection();
            sql.rs = sql.st.executeQuery("Select * from transactions where id="+ id +"");
            // GET THE RESULT OF QUERY THEN ADD IT TO LIST
            while (sql.rs.next()){
                CLASS_TRANSACTIONS tempTransaction = convertRowToTransaction(sql.rs);
                list.add(tempTransaction);
            }
            // CLOSE CONECTION
            sql.rs.close();
            sql.st.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAO_TRANSACTIONS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<CLASS_TRANSACTIONS> getUserCurrentTransaction(int id) {
        List<CLASS_TRANSACTIONS> list = new ArrayList<>();

        try {
            sql.createConnection();
            sql.rs = sql.st.executeQuery("Select * from transactions where user_id="+ id +" and status='RESERVED' or status='BORROWED'");
            // GET THE RESULT OF QUERY THEN ADD IT TO LIST
            while (sql.rs.next()){
                CLASS_TRANSACTIONS tempTransaction = convertRowToTransaction(sql.rs);
                list.add(tempTransaction);
            }
            // CLOSE CONECTION
            sql.rs.close();
            sql.st.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAO_TRANSACTIONS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public void addTransaction(CLASS_TRANSACTIONS theTransaction) throws SQLException {
        sql.createConnection();
        sql.pst = sql.con.prepareStatement("Insert into transactions(USER_ID,BORROWER_ID,BORROWER_FNAME,BORROWER_LNAME,ITEM_ID,STATUS,DATE_RES,DATE_UPDATED) values(?, ?, ?, ?, ?, ?, ?, ?)");
        sql.pst.setInt(1, theTransaction.getUser_id());
        sql.pst.setString(2, theTransaction.getBorrower_id());
        sql.pst.setString(3, theTransaction.getBorrower_fname());
        sql.pst.setString(4, theTransaction.getBorrower_lname());
        sql.pst.setInt(5, theTransaction.getItem_id());
        sql.pst.setString(6, theTransaction.getStatus());
        sql.pst.setString(7, theTransaction.getDate());
        sql.pst.setString(8, theTransaction.getDate_updated());
        
        sql.pst.executeUpdate();
        sql.rs.close();
        sql.st.close();
    }
    
     public void updateTransaction(CLASS_TRANSACTIONS theTransaction) throws SQLException {
        sql.createConnection();
        sql.pst = sql.con.prepareStatement("Update transactions set USER_ID=?, BORROWER_ID=?, BORROWER_FNAME=?, BORROWER_LNAME=?, ITEM_ID=?, STATUS=?, DATE_UPDATED=? where id=?");
        sql.pst.setInt(1, theTransaction.getUser_id());
        sql.pst.setString(2, theTransaction.getBorrower_id());
        sql.pst.setString(3, theTransaction.getBorrower_fname());
        sql.pst.setString(4, theTransaction.getBorrower_lname());
        sql.pst.setInt(5, theTransaction.getItem_id());
        sql.pst.setString(6, theTransaction.getStatus());
        sql.pst.setString(7, theTransaction.getDate_updated());
        sql.pst.setInt(8, theTransaction.getId());
        sql.pst.executeUpdate();
        sql.rs.close();
        sql.st.close();
    }
    
    public void deleteTransaction(CLASS_TRANSACTIONS theTransaction) throws SQLException {
        sql.createConnection();
        sql.pst = sql.con.prepareStatement("Delete from transactions where id=?");
        sql.pst.setInt(1, theTransaction.getId());
        sql.pst.executeUpdate();
        sql.rs.close();
        sql.st.close();
    }
    
    public void updateStatus(int id, String status) throws SQLException {
        sql.createConnection();
        sql.pst = sql.con.prepareStatement("Update transactions set status=? where id=?");
        sql.pst.setString(1, status);
        sql.pst.setInt(2, id);
        sql.pst.executeUpdate();
        sql.rs.close();
        sql.st.close();
    }
       
    public String getItemName(int item_id) throws SQLException {
        String name = "";
        sql.createConnection();
        sql.rs = sql.st.executeQuery("SELECT i.name FROM transactions as t JOIN items as i ON t.item_id = i.id where t.item_id="+ item_id +"");
        // GET THE RESULT OF QUERY THEN ADD IT TO LIST
        while (sql.rs.next()){
            name = sql.rs.getString("name");
        }
        // CLOSE CONECTION
        sql.rs.close();
        sql.st.close();
        return name;
    }
}
