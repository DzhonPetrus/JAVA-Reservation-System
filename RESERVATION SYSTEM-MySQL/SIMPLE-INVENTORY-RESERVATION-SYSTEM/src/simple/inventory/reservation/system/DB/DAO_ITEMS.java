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
public class DAO_ITEMS {
        List<CLASS_ITEMS> items = new ArrayList<>();
        public MYSQL_DB sql = new MYSQL_DB();
    public List<CLASS_ITEMS> getAllItems() {
        List<CLASS_ITEMS> list = new ArrayList<>();

        
        
        try {
            sql.createConnection();
            sql.rs = sql.st.executeQuery("Select * from items");
            // GET THE RESULT OF QUERY THEN ADD IT TO LIST
            while (sql.rs.next()){
                CLASS_ITEMS tempItem = convertRowToItem(sql.rs);
                list.add(tempItem);
            }
            // CLOSE CONECTION
            sql.rs.close();
            sql.st.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAO_ITEMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    private CLASS_ITEMS convertRowToItem(ResultSet myRs) throws SQLException {
		
	int id = myRs.getInt("ID");
	String name = myRs.getString("NAME");
	int stock = myRs.getInt("STOCK");
		
		
	CLASS_ITEMS tempItem = new CLASS_ITEMS(id, name, stock);
	return tempItem;
    }
    
    public List<CLASS_ITEMS> searchItem(String name) {
        List<CLASS_ITEMS> list = new ArrayList<>();

        name='%' + name + '%';
        try {
            sql.createConnection();
            sql.rs = sql.st.executeQuery("Select * from items where name like '"+ name +"'");
            // GET THE RESULT OF QUERY THEN ADD IT TO LIST
            while (sql.rs.next()){
                CLASS_ITEMS tempItem = convertRowToItem(sql.rs);
                list.add(tempItem);
            }
            
            
            // CLOSE CONECTION
            sql.rs.close();
            sql.st.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAO_ITEMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public void updateStock(int id, int stock) throws SQLException{
        sql.createConnection();
        sql.pst = sql.con.prepareStatement("Update items set stock=? where id=?");
        sql.pst.setInt(1, stock);
        sql.pst.setInt(2, id);
        sql.pst.executeUpdate();
        sql.rs.close();
        sql.st.close();
    }
    
    public List<CLASS_ITEMS> getAvailableItems() {
        List<CLASS_ITEMS> list = new ArrayList<>();
        
        
        try {
            sql.createConnection();
            sql.rs = sql.st.executeQuery("Select * from items where stock > 0");
            // GET THE RESULT OF QUERY THEN ADD IT TO LIST
            while (sql.rs.next()){
                CLASS_ITEMS tempItem = convertRowToItem(sql.rs);
                list.add(tempItem);
            }
            
            
            // CLOSE CONECTION
            sql.rs.close();
            sql.st.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAO_ITEMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
