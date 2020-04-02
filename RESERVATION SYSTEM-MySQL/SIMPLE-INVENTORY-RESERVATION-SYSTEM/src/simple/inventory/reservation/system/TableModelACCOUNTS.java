/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.inventory.reservation.system;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import simple.inventory.reservation.system.DB.CLASS_ACCOUNTS;

/**
 *
 * @author dzhon
 */
class TableModelACCOUNTS extends  AbstractTableModel{
    public static final int ID_COL = 0;
    public static final int USERNAME_COL = 1;
    public static final int PASSWORD_COL = 2;
    public static final int PERSON_ID_COL = 3;
    public static final int FNAME_COL = 4;
    public static final int LNAME_COL = 5;
    public static final int ROLE_COL = 6;
    
    private String[] columnNames = {"ID", "USERNAME", "PASSWORD", "PERSON_ID", "FNAME", "LNAME", "ROLE"};
    private List<CLASS_ACCOUNTS> accounts;

    public TableModelACCOUNTS(List<CLASS_ACCOUNTS> theAccounts) {
        accounts = theAccounts;
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public int getRowCount() {
        return accounts.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        CLASS_ACCOUNTS tempAccount = accounts.get(row);
        
        switch(col) {
            case ID_COL:
                return tempAccount.getId();
            case USERNAME_COL:
                return tempAccount.getUsername();
            case PASSWORD_COL:
                return tempAccount.getPassword();
            case PERSON_ID_COL:
                return tempAccount.getPerson_id();
            case FNAME_COL:
                return tempAccount.getFname();
            case LNAME_COL:
                return tempAccount.getLname();
            case ROLE_COL:
                return tempAccount.getRole();
            default:
                return tempAccount.getId();
        }
    }
    
}
