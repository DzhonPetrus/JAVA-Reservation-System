/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.inventory.reservation.system.DB;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author dzhon
 */
public class CLASS_ACCOUNTS {
    String username, password, fname, lname, person_id;
    int id, role;

    public CLASS_ACCOUNTS(int id, String username, String password, String person_id, String fname, String lname, int role) {
        
        this.id = id;
        this.username = username;
        this.password = password;
        this.person_id = person_id;
        this.fname = fname;
        this.lname = lname;
        this.role = role;
    }

    CLASS_ACCOUNTS(int i, JTextField txtUSERNAME, JPasswordField txtPASSWORD, int i0, String string, String string0, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "CLASSACCOUNT{id=" + id + ", " + "username=" + username + ", password=" + password + ", person_id=" + person_id + ",  fname=" + fname + ", lname=" + lname + ", role=" + role + '}';
    } 
    
}
