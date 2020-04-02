/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.inventory.reservation.system.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;


/**
 *
 * @author dzhon
 */
public class MYSQL_DB {
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    void createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reservation_system", "root","");
            System.out.println("Success");
            
            this.st = con.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            java.util.logging.Logger.getLogger(MYSQL_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void test(){
        try {
            // connection
            this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reservation_system", "root","");
            // statement
            this.st = con.createStatement();
            // execute query
            this.rs = st.executeQuery("Select * from items");
            // process result set
            while (rs.next()) {
                System.out.println(rs.getString("id") + ", " + rs.getString("name") + ", " + rs.getString("stock"));
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(MYSQL_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
