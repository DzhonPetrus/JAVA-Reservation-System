/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.inventory.reservation.system.DB;

import java.sql.*;
import java.util.logging.Level;


/**
 *
 * @author dzhon
 */
public class ORACLE_DB {
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    CallableStatement cst = null;
    
    void createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##petrus", "tiger");
            
            this.st = con.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            java.util.logging.Logger.getLogger(ORACLE_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
