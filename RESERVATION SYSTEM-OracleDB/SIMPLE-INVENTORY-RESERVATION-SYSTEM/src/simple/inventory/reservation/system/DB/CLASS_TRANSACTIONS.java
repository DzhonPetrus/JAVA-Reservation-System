/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.inventory.reservation.system.DB;

import java.util.Date;

/**
 *
 * @author dzhon
 */
public class CLASS_TRANSACTIONS {
    int id, user_id, item_id;
    String borrower_id, borrower_fname, borrower_lname, status, date, date_updated;

    public CLASS_TRANSACTIONS(int id, int user_id, String borrower_id, String borrower_fname, String borrower_lname, int item_id, String status, String date, String date_updated) {
        this.id = id;
        this.user_id = user_id;
        this.item_id = item_id;
        this.borrower_id = borrower_id;
        this.borrower_fname = borrower_fname;
        this.borrower_lname = borrower_lname;
        this.status = status;
        this.date = date;
        this.date_updated = date_updated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getBorrower_id() {
        return borrower_id;
    }

    public void setBorrower_id(String borrower_id) {
        this.borrower_id = borrower_id;
    }

    public String getBorrower_fname() {
        return borrower_fname;
    }

    public void setBorrower_fname(String borrower_fname) {
        this.borrower_fname = borrower_fname;
    }

    public String getBorrower_lname() {
        return borrower_lname;
    }

    public void setBorrower_lname(String borrower_lname) {
        this.borrower_lname = borrower_lname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(String date_updated) {
        this.date_updated = date_updated;
    }

    @Override
    public String toString() {
        return "CLASSTRANSACTIONS{" + "id=" + id + ", user_id=" + user_id  + ", borrower_id=" + borrower_id + ", borrower_fname=" + borrower_fname + ", borrower_lname=" + borrower_lname + ", item_id=" + item_id + ", status=" + status + ", date=" + date + ", date_updated=" + date_updated + '}';
    }
    
    
}
