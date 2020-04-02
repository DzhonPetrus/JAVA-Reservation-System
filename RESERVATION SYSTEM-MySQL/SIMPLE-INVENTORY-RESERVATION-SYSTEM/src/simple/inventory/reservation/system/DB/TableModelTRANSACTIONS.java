/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.inventory.reservation.system;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import simple.inventory.reservation.system.DB.CLASS_ACCOUNTS;
import simple.inventory.reservation.system.DB.CLASS_TRANSACTIONS;
import simple.inventory.reservation.system.DB.CLASS_TRANSACTIONS;

/**
 *
 * @author dzhon
 */
class TableModelTRANSACTIONS extends  AbstractTableModel{    
    public static final int ID_COL = 0;
    public static final int USERID_COL = 1;
    public static final int BORROWER_ID_COL = 2;
    public static final int BORROWER_FNAME_COL = 3;
    public static final int BORROWER_LNAME_COL = 4;
    public static final int ITEM_ID_COL = 5;
    public static final int STATUS_COL = 6;
    public static final int DATE_RES_COL = 7;
    public static final int DATE_UPDATED_COL = 8;
    
    private String[] columnNames = {"ID", "USERID", "BORROWER_ID", "BORROWER_FNAME", "BORROWER_LNAME", 
        "ITEM_ID", "STATUS", "DATE_RES", "DATE_UPDATED"};
    private List<CLASS_TRANSACTIONS> transactions;

    public TableModelTRANSACTIONS(List<CLASS_TRANSACTIONS> theTransactions) {
        transactions = theTransactions;
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public int getRowCount() {
        return transactions.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        CLASS_TRANSACTIONS tempTransaction = transactions.get(row);
        
        switch(col) {
            case ID_COL:
                return tempTransaction.getId();
            case USERID_COL:
                return tempTransaction.getUser_id();
            case BORROWER_ID_COL:
                return tempTransaction.getBorrower_id();
            case BORROWER_FNAME_COL:
                return tempTransaction.getBorrower_fname();
            case BORROWER_LNAME_COL:
                return tempTransaction.getBorrower_lname();
            case ITEM_ID_COL:
                return tempTransaction.getItem_id();
            case STATUS_COL:
                return tempTransaction.getStatus();
            case DATE_RES_COL:
                return tempTransaction.getDate();
            case DATE_UPDATED_COL:
                return tempTransaction.getDate_updated();  
            default:
                return tempTransaction.getId();
        }
    }
    
}
