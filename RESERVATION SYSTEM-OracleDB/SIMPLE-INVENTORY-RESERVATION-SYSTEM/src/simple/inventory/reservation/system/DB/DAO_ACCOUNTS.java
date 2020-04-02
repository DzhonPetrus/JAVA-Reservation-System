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
public class DAO_ACCOUNTS {
    ORACLE_DB orcl = new ORACLE_DB();
    public List<CLASS_ACCOUNTS> getAllAccounts() {
        List<CLASS_ACCOUNTS> list = new ArrayList<>();
        
        try {
            orcl.createConnection();
            orcl.cst = orcl.con.prepareCall("{CALL SELECTACCOUNTS(?)}");
            orcl.cst.registerOutParameter(1, OracleTypes.CURSOR);
            orcl.cst.execute();
            orcl.rs = (ResultSet) orcl.cst.getObject(1);
            // GET THE RESULT OF QUERY THEN ADD IT TO LIST
            while (orcl.rs.next()){
                CLASS_ACCOUNTS tempAccount = convertRowToEmployee(orcl.rs);
                list.add(tempAccount);
            }
            // CLOSE CONECTION
            orcl.rs.close();
            orcl.cst.close();
            orcl.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_ACCOUNTS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    private CLASS_ACCOUNTS convertRowToEmployee(ResultSet myRs) throws SQLException {
		
	int id = myRs.getInt("ID");
	String username = myRs.getString("USERNAME");
	String password = myRs.getString("PASSWORD");
	String perid = myRs.getString("PERSON_ID");
	String fname = myRs.getString("fname");
	String lname = myRs.getString("lname");
	int role = myRs.getInt("ROLE");
		
		
	CLASS_ACCOUNTS tempAccount = new CLASS_ACCOUNTS(id, username, password, perid, fname, lname, role);
	return tempAccount;
    }
    
    public List<CLASS_ACCOUNTS> searchAccount(int ID) {
        List<CLASS_ACCOUNTS> list = new ArrayList<>();
        
        try {
          orcl.createConnection();
            orcl.cst = orcl.con.prepareCall("{CALL SEARCHACCOUNT(?,?)}");
            orcl.cst.registerOutParameter(1, OracleTypes.CURSOR);
            orcl.cst.setInt(2, ID);
            orcl.cst.execute();
            orcl.rs = (ResultSet) orcl.cst.getObject(1);
            // GET THE RESULT OF QUERY THEN ADD IT TO LIST
            while (orcl.rs.next()){
                CLASS_ACCOUNTS tempAccount = convertRowToEmployee(orcl.rs);
                list.add(tempAccount);
            }
            // CLOSE CONECTION
            orcl.rs.close();
            orcl.cst.close();
            orcl.con.close();
      
        } catch (SQLException ex) {
            Logger.getLogger(DAO_ACCOUNTS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<CLASS_ACCOUNTS> searchAccountByPersonID(String personID) {
        List<CLASS_ACCOUNTS> list = new ArrayList<>();
        
        try {
           orcl.createConnection();
            orcl.cst = orcl.con.prepareCall("{CALL SEARCHACCOUNTBYPERSON(?,?)}");
            orcl.cst.registerOutParameter(1, OracleTypes.CURSOR);
            orcl.cst.setString(2, personID);
            orcl.cst.execute();
            orcl.rs = (ResultSet) orcl.cst.getObject(1);
            // GET THE RESULT OF QUERY THEN ADD IT TO LIST
            while (orcl.rs.next()){
                CLASS_ACCOUNTS tempAccount = convertRowToEmployee(orcl.rs);
                list.add(tempAccount);
            }
            // CLOSE CONECTION
            orcl.rs.close();
            orcl.cst.close();
            orcl.con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAO_ACCOUNTS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
     public List<CLASS_ACCOUNTS> verifyAccount(String username, String password) {
        List<CLASS_ACCOUNTS> list = new ArrayList<>();
        
        try {
            orcl.createConnection();
            orcl.cst = orcl.con.prepareCall("{CALL VERIFYACCOUNT(?,?,?)}");
            orcl.cst.registerOutParameter(1, OracleTypes.CURSOR);
            orcl.cst.setString(2, username);
            orcl.cst.setString(3, password);
            orcl.cst.execute();
            orcl.rs = (ResultSet) orcl.cst.getObject(1);
            // GET THE RESULT OF QUERY THEN ADD IT TO LIST
            while (orcl.rs.next()){
                CLASS_ACCOUNTS tempAccount = convertRowToEmployee(orcl.rs);
                list.add(tempAccount);
            }
            // CLOSE CONECTION
            orcl.rs.close();
            orcl.cst.close();
            orcl.con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAO_ACCOUNTS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     
    public void addAccount(CLASS_ACCOUNTS theAccount) throws SQLException {
        orcl.createConnection();
        orcl.cst = orcl.con.prepareCall("{CALL INSERTACCOUNT(?,?,?,?,?,?)}");
        orcl.cst.setString(1, theAccount.getUsername());
        orcl.cst.setString(2, theAccount.getPassword());
        orcl.cst.setString(3, theAccount.getPerson_id());
        orcl.cst.setString(4, theAccount.getFname());
        orcl.cst.setString(5, theAccount.getLname());
        orcl.cst.setInt(6, theAccount.getRole());
        orcl.cst.execute();
        orcl.rs.close();
        orcl.cst.close();
    }
    
    public void updateAccount(CLASS_ACCOUNTS theAccount) throws SQLException {
        orcl.createConnection();
        orcl.cst = orcl.con.prepareCall("{CALL UPDATEACCOUNT(?,?,?,?,?,?,?)}");
        orcl.cst.setString(1, theAccount.getUsername());
        orcl.cst.setString(2, theAccount.getPassword());
        orcl.cst.setString(3, theAccount.getPerson_id());
        orcl.cst.setString(4, theAccount.getFname());
        orcl.cst.setString(5, theAccount.getLname());
        orcl.cst.setInt(6, theAccount.getRole());
        orcl.cst.setInt(7, theAccount.getId());
        orcl.cst.execute();
        orcl.cst.close();
        orcl.rs.close();
    }
    
    public void deleteAccount(CLASS_ACCOUNTS theAccount) throws SQLException {
        orcl.createConnection();
        orcl.cst = orcl.con.prepareCall("{CALL DELETEACCOUNT(?)}");
        orcl.cst.setInt(1, theAccount.getId());
        orcl.cst.execute();
        orcl.rs.close();
        orcl.cst.close();
    }
}
