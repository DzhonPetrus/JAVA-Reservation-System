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
import oracle.jdbc.OracleTypes;

/**
 *
 * @author dzhon
 */
public class DAO_TRANSACTIONS {
    ORACLE_DB orcl = new ORACLE_DB();
    public List<CLASS_TRANSACTIONS> getAllTransactions() {
        List<CLASS_TRANSACTIONS> list = new ArrayList<>();

        
        try {
            orcl.createConnection();
            orcl.cst = orcl.con.prepareCall("{CALL SELECTTRANSACTIONS(?)}");
            orcl.cst.registerOutParameter(1, OracleTypes.CURSOR);
            orcl.cst.execute();
            orcl.rs = (ResultSet) orcl.cst.getObject(1);
            // GET THE RESULT OF QUERY THEN ADD IT TO LIST
            while (orcl.rs.next()){
                CLASS_TRANSACTIONS tempTransaction = convertRowToTransaction(orcl.rs);
                list.add(tempTransaction);
            }
            // CLOSE CONECTION
            orcl.rs.close();
            orcl.cst.close();
            
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
            orcl.createConnection();
            orcl.cst = orcl.con.prepareCall("{CALL SELECTTRANSACTIONSBYSTATUS(?,?)}");
            orcl.cst.registerOutParameter(1, OracleTypes.CURSOR);
            orcl.cst.setString(2, status);
            orcl.cst.execute();
            orcl.rs = (ResultSet) orcl.cst.getObject(1);
            // GET THE RESULT OF QUERY THEN ADD IT TO LIST
            while (orcl.rs.next()){
                CLASS_TRANSACTIONS tempTransaction = convertRowToTransaction(orcl.rs);
                list.add(tempTransaction);
            }
            // CLOSE CONECTION
            orcl.rs.close();
            orcl.cst.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAO_TRANSACTIONS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<CLASS_TRANSACTIONS> getAllUserTransactions(int id) {
        List<CLASS_TRANSACTIONS> list = new ArrayList<>();

        
        try {
            orcl.createConnection();
            orcl.cst = orcl.con.prepareCall("{CALL SELECTTRANSACTIONSOFUSER(?,?)}");
            orcl.cst.registerOutParameter(1, OracleTypes.CURSOR);
            orcl.cst.setInt(2, id);
            orcl.cst.execute();
            orcl.rs = (ResultSet) orcl.cst.getObject(1);
            // GET THE RESULT OF QUERY THEN ADD IT TO LIST
            while (orcl.rs.next()){
                CLASS_TRANSACTIONS tempTransaction = convertRowToTransaction(orcl.rs);
                list.add(tempTransaction);
            }
            // CLOSE CONECTION
            orcl.rs.close();
            orcl.cst.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAO_TRANSACTIONS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<CLASS_TRANSACTIONS> searchTransaction(int id) {
        List<CLASS_TRANSACTIONS> list = new ArrayList<>();

        try {
            orcl.createConnection();
            orcl.cst = orcl.con.prepareCall("{CALL SEARCHTRANSACTION(?,?)}");
            orcl.cst.registerOutParameter(1, OracleTypes.CURSOR);
            orcl.cst.setInt(2, id);
            orcl.cst.execute();
            orcl.rs = (ResultSet) orcl.cst.getObject(1);
            // GET THE RESULT OF QUERY THEN ADD IT TO LIST
            while (orcl.rs.next()){
                CLASS_TRANSACTIONS tempTransaction = convertRowToTransaction(orcl.rs);
                list.add(tempTransaction);
            }
            // CLOSE CONECTION
            orcl.rs.close();
            orcl.cst.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAO_TRANSACTIONS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<CLASS_TRANSACTIONS> getUserCurrentTransaction(int id) {
        List<CLASS_TRANSACTIONS> list = new ArrayList<>();

        try {
            orcl.createConnection();
            orcl.cst = orcl.con.prepareCall("{CALL SEARCHTRANSACTIONCURRENTUSER(?,?)}");
            orcl.cst.registerOutParameter(1, OracleTypes.CURSOR);
            orcl.cst.setInt(2, id);
            orcl.cst.execute();
            orcl.rs = (ResultSet) orcl.cst.getObject(1);
            // GET THE RESULT OF QUERY THEN ADD IT TO LIST
            while (orcl.rs.next()){
                CLASS_TRANSACTIONS tempTransaction = convertRowToTransaction(orcl.rs);
                list.add(tempTransaction);
            }
            // CLOSE CONECTION
            orcl.rs.close();
            orcl.cst.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAO_TRANSACTIONS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public void addTransaction(CLASS_TRANSACTIONS theTransaction) throws SQLException {
        orcl.createConnection();
        orcl.cst = orcl.con.prepareCall("{CALL INSERTTRANSACTION(?,?,?,?,?,?,?)}");
        orcl.cst.setInt(1, theTransaction.getUser_id());
        orcl.cst.setString(2, theTransaction.getBorrower_id());
        orcl.cst.setString(3, theTransaction.getBorrower_fname());
        orcl.cst.setString(4, theTransaction.getBorrower_lname());
        orcl.cst.setInt(5, theTransaction.getItem_id());
        orcl.cst.setString(6, theTransaction.getStatus());
        orcl.cst.setString(7, theTransaction.getDate());
        
        orcl.cst.execute();
        orcl.rs.close();
        orcl.cst.close();
    }
    
     public void updateTransaction(CLASS_TRANSACTIONS theTransaction) throws SQLException {
        orcl.createConnection();
        orcl.cst = orcl.con.prepareCall("{CALL UPDATETRANSACTION(?,?,?,?,?,?,?,?)}");
        orcl.cst.setInt(1, theTransaction.getUser_id());
        orcl.cst.setString(2, theTransaction.getBorrower_id());
        orcl.cst.setString(3, theTransaction.getBorrower_fname());
        orcl.cst.setString(4, theTransaction.getBorrower_lname());
        orcl.cst.setInt(5, theTransaction.getItem_id());
        orcl.cst.setString(6, theTransaction.getStatus());
        orcl.cst.setString(7, theTransaction.getDate_updated());
        orcl.cst.setInt(8, theTransaction.getId());
        orcl.cst.execute();
        orcl.rs.close();
        orcl.cst.close();
    }
    
    public void deleteTransaction(CLASS_TRANSACTIONS theTransaction) throws SQLException {
        orcl.createConnection();
        orcl.cst = orcl.con.prepareCall("{CALL DELETETRANSACTION(?)}");
        orcl.cst.setInt(1, theTransaction.getId());
        orcl.cst.execute();
        orcl.rs.close();
        orcl.cst.close();
    }
    
    public void updateStatus(int id, String status) throws SQLException {
        orcl.createConnection();
        orcl.cst = orcl.con.prepareCall("{CALL UPDATESTATUS(?,?)}");
        orcl.cst.setString(1, status);
        orcl.cst.setInt(2, id);
        orcl.cst.execute();
        orcl.rs.close();
        orcl.cst.close();
    }
       
    public String getItemName(int item_id) throws SQLException {
        String name = "";
        orcl.createConnection();
        orcl.cst = orcl.con.prepareCall("{CALL SEARCHITEMNAME(?,?)}");
        orcl.cst.registerOutParameter(1, OracleTypes.CURSOR);
        orcl.cst.setInt(2, item_id);
        orcl.cst.execute();
        orcl.rs = (ResultSet) orcl.cst.getObject(1);
        // GET THE RESULT OF QUERY THEN ADD IT TO LIST
        while (orcl.rs.next()){
            name = orcl.rs.getString("name");
        }
        // CLOSE CONECTION
        orcl.rs.close();
        orcl.st.close();
        return name;
    }
}
