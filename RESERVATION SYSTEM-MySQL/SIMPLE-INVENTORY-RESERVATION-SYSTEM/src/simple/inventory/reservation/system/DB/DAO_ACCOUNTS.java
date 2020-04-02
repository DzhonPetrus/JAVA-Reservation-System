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
public class DAO_ACCOUNTS {
    MYSQL_DB sql = new MYSQL_DB();
    public List<CLASS_ACCOUNTS> getAllAccounts() {
        List<CLASS_ACCOUNTS> list = new ArrayList<>();
        
        try {
            sql.createConnection();
            sql.rs = sql.st.executeQuery("Select * from accounts");
            // GET THE RESULT OF QUERY THEN ADD IT TO LIST
            while (sql.rs.next()){
                CLASS_ACCOUNTS tempAccount = convertRowToEmployee(sql.rs);
                list.add(tempAccount);
            }
            // CLOSE CONECTION
            sql.rs.close();
            sql.st.close();
            sql.con.close();
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
            sql.createConnection();
            sql.rs = sql.st.executeQuery("Select * from accounts where ID ="+ ID +"");
            // GET THE RESULT OF QUERY THEN ADD IT TO LIST
            while (sql.rs.next()){
                CLASS_ACCOUNTS tempAccount = convertRowToEmployee(sql.rs);
                list.add(tempAccount);
            }
            
            
            // CLOSE CONECTION
            sql.rs.close();
            sql.st.close();
            sql.con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAO_ACCOUNTS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<CLASS_ACCOUNTS> searchAccountByPersonID(String personID) {
        List<CLASS_ACCOUNTS> list = new ArrayList<>();
        
        try {
            sql.createConnection();
            sql.rs = sql.st.executeQuery("Select * from accounts where PERSON_ID ='"+ personID +"'");
            // GET THE RESULT OF QUERY THEN ADD IT TO LIST
            while (sql.rs.next()){
                CLASS_ACCOUNTS tempAccount = convertRowToEmployee(sql.rs);
                list.add(tempAccount);
            }
            
            
            // CLOSE CONECTION
            sql.rs.close();
            sql.st.close();
            sql.con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAO_ACCOUNTS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
     public List<CLASS_ACCOUNTS> verifyAccount(String username, String password) {
        List<CLASS_ACCOUNTS> list = new ArrayList<>();
        
        try {
            sql.createConnection();
            sql.rs = sql.st.executeQuery("Select * from accounts where username = '"+ username +"' and password = '"+ password +"'");
            // GET THE RESULT OF QUERY THEN ADD IT TO LIST
            while (sql.rs.next()){
                CLASS_ACCOUNTS tempAccount = convertRowToEmployee(sql.rs);
                list.add(tempAccount);
            }
            
            
            // CLOSE CONECTION
            sql.rs.close();
            sql.st.close();
            sql.con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAO_ACCOUNTS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     
    public void addAccount(CLASS_ACCOUNTS theAccount) throws SQLException {
        sql.createConnection();
        sql.pst = sql.con.prepareStatement("Insert into accounts(username, password, person_id, fname, lname, role) values(?, ?, ?, ?, ?, ?)");
        sql.pst.setString(1, theAccount.getUsername());
        sql.pst.setString(2, theAccount.getPassword());
        sql.pst.setString(3, theAccount.getPerson_id());
        sql.pst.setString(4, theAccount.getFname());
        sql.pst.setString(5, theAccount.getLname());
        sql.pst.setInt(6, theAccount.getRole());
        sql.pst.executeUpdate();
        sql.rs.close();
        sql.st.close();
    }
    
    public void updateAccount(CLASS_ACCOUNTS theAccount) throws SQLException {
        sql.createConnection();
        sql.pst = sql.con.prepareStatement("Update accounts set username=?, password=?, person_id=?, fname=?, lname=?, role=? where id=?");
        sql.pst.setString(1, theAccount.getUsername());
        sql.pst.setString(2, theAccount.getPassword());
        sql.pst.setString(3, theAccount.getPerson_id());
        sql.pst.setString(4, theAccount.getFname());
        sql.pst.setString(5, theAccount.getLname());
        sql.pst.setInt(6, theAccount.getRole());
        sql.pst.setInt(7, theAccount.getId());
        sql.pst.executeUpdate();
        sql.rs.close();
        sql.st.close();
    }
    
    public void deleteAccount(CLASS_ACCOUNTS theAccount) throws SQLException {
        sql.createConnection();
        sql.pst = sql.con.prepareStatement("Delete from accounts where id=?");
        sql.pst.setInt(1, theAccount.getId());
        sql.pst.executeUpdate();
        sql.rs.close();
        sql.st.close();
    }
}
