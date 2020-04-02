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
public class DAO_ITEMS {
        List<CLASS_ITEMS> items = new ArrayList<>();
        public ORACLE_DB orcl = new ORACLE_DB();
    public List<CLASS_ITEMS> getAllItems() {
        List<CLASS_ITEMS> list = new ArrayList<>();

        
        try {
            orcl.createConnection();
            orcl.cst = orcl.con.prepareCall("{CALL SELECTITEMS(?)}");
            orcl.cst.registerOutParameter(1, OracleTypes.CURSOR);
            orcl.cst.execute();
            orcl.rs = (ResultSet) orcl.cst.getObject(1);
            // GET THE RESULT OF QUERY THEN ADD IT TO LIST
            while (orcl.rs.next()){
                CLASS_ITEMS tempItem = convertRowToItem(orcl.rs);
                list.add(tempItem);
            }
            // CLOSE CONECTION
            orcl.rs.close();
            orcl.cst.close();
            
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
            
            orcl.createConnection();
            orcl.cst = orcl.con.prepareCall("{CALL SEARCHITEM(?,?)}");
            orcl.cst.registerOutParameter(1, OracleTypes.CURSOR);
            orcl.cst.setString(2, name);
            orcl.cst.execute();
            orcl.rs = (ResultSet) orcl.cst.getObject(1);
            // GET THE RESULT OF QUERY THEN ADD IT TO LIST
            while (orcl.rs.next()){
                CLASS_ITEMS tempItem = convertRowToItem(orcl.rs);
                list.add(tempItem);
            }
            // CLOSE CONECTION
            orcl.rs.close();
            orcl.cst.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DAO_ITEMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public void updateStock(int id, int stock) throws SQLException{
        orcl.createConnection();
        orcl.cst = orcl.con.prepareCall("{CALL UPDATESTOCK(?,?)}");
        orcl.cst.setInt(1, stock);
        orcl.cst.setInt(2, id);
        orcl.cst.executeUpdate();
        orcl.rs.close();
        orcl.cst.close();
    }
    
    public List<CLASS_ITEMS> getAvailableItems() {
        List<CLASS_ITEMS> list = new ArrayList<>();
        
        
        try {
            orcl.createConnection();
            orcl.cst = orcl.con.prepareCall("{CALL SELECTITEMSAVAILABLE(?)}");
            orcl.cst.registerOutParameter(1, OracleTypes.CURSOR);
            orcl.cst.execute();
            orcl.rs = (ResultSet) orcl.cst.getObject(1);
            // GET THE RESULT OF QUERY THEN ADD IT TO LIST
            while (orcl.rs.next()){
                CLASS_ITEMS tempItem = convertRowToItem(orcl.rs);
                list.add(tempItem);
            }
            
            
            // CLOSE CONECTION
            orcl.rs.close();
            orcl.cst.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAO_ITEMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
