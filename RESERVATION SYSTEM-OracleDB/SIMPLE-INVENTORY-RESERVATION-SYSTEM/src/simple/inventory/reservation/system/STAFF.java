/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.inventory.reservation.system;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import simple.inventory.reservation.system.DB.CLASS_ACCOUNTS;
import simple.inventory.reservation.system.DB.CLASS_ITEMS;
import simple.inventory.reservation.system.DB.CLASS_TRANSACTIONS;
import simple.inventory.reservation.system.DB.DAO_ACCOUNTS;
import simple.inventory.reservation.system.DB.DAO_ITEMS;
import simple.inventory.reservation.system.DB.DAO_TRANSACTIONS;

/**
 *
 * @author dzhon
 */
public class STAFF extends javax.swing.JFrame {

    private String currUSER;

    /**
     * Creates new form STAFF
     * @param currID
     * @param currUSER
     */
    public STAFF(int currID, String currUSER) {
        initComponents();
        setDate();
        this.currID=currID;
        this.currUSER=currUSER;
        // COMPONENTS PROP INIT
        btnADD.setOpaque(true);
        btnPROFILE.setOpaque(true);
        btnUPDATE.setOpaque(true);
        btnSIGNOUT.setOpaque(true);
        btnADDTRANS.setOpaque(true);
        btnVIEWTRANS.setOpaque(true);
        btnACTION.setOpaque(true);
        
        //DATE SELECTION NOW -> ONWARD
        Date date = new Date();
        dtRESERVE.setMinSelectableDate(date);
        
        lblUSER.setText("Welcome, " + currUSER);
        
        btnVIEWTRANS.doClick();
        resetState();
    }

    public DAO_ACCOUNTS daoACCOUNTS = new DAO_ACCOUNTS();
    public DAO_TRANSACTIONS daoTRANSACTIONS = new DAO_TRANSACTIONS();
    public DAO_ITEMS daoITEMS = new DAO_ITEMS();
    public CLASS_ACCOUNTS tempAccount = null;
    public CLASS_TRANSACTIONS tempTransaction = null;
    public CLASS_ITEMS tempItem = null;
    public List<CLASS_TRANSACTIONS> transactions = null;
    public List<CLASS_ACCOUNTS> accounts = null;
    public List<CLASS_ITEMS> items =null;
    
    public DefaultListModel itemList = new DefaultListModel();
    
    public String currDate = "";
    public int stateProf = 0; // STATE OF PROFILE
    public int newID = 0;
    public int selectedID = 0 ;
    public int currID = 0;
    public int currROLE = 0;
    public int user_id = 0;
    
    public void resetState(){
        stateProf = 0; // STATE OF PROFILE
        newID = 0;
        selectedID = 0 ;
        user_id = 0;
        
                
        accounts = daoACCOUNTS.searchAccount(currID);
        txtUSERNAME.setText(accounts.get(0).getUsername());
        txtPASSWORD.setText(accounts.get(0).getPassword());
        txtFNAMEPROF.setText(accounts.get(0).getFname());
        txtLNAMEPROF.setText(accounts.get(0).getLname());
        txtPERSONIDPROF.setText(accounts.get(0).getPerson_id());
        currROLE = accounts.get(0).getRole();
        chkAVAILABLE.setSelected(false);
        btnUPDATE.setEnabled(false);
        
        loadALLTRANSACTIONS();
        loadALLITEMS();
    }
    
    public void loadALLTRANSACTIONS(){
        int i =0;
        List<String> status = new ArrayList<String>();
        cbSTATUS.removeAllItems();
        status.clear();
        status.add("ALL");
        transactions = daoTRANSACTIONS.getAllTransactions();
        TableModelTRANSACTIONS model = new TableModelTRANSACTIONS(transactions);
        tblTRANS.setModel(model);
        
        for(i = 0 ; i <= transactions.size()-1 ; i++)
            if(!status.contains(transactions.get(i).getStatus()))
                status.add(transactions.get(i).getStatus());
            
        
        lstITEMS.setModel(itemList);
        cbSTATUS.setModel(new DefaultComboBoxModel(status.toArray()));
        cbSTATUS.setSelectedIndex(0);
        
        // LOOK IF THERE ARE TRANSACTIONS
        newID = transactions.isEmpty() ?  0  : transactions.get(transactions.size()-1).getId() + 1;
        
    }
    
    public void loadTRANSACTIONSBYSTATUS(String status){
        transactions = daoTRANSACTIONS.getAllStatus(status);
        TableModelTRANSACTIONS model = new TableModelTRANSACTIONS(transactions);
        tblTRANS.setModel(model);
    }
    
    public void loadALLITEMS() {
        loadALLAVAILABLEITEMS();
        itemList.clear();
        int i = 0;
        items = daoITEMS.getAllItems();
        
        for(i = 0 ; i <= items.size()-1 ; i++)
            if(!itemList.contains(items.get(i).getName()))
                itemList.addElement(items.get(i).getName() + " ("+ items.get(i).getStock() +")");
        
        lstITEMS.setModel(itemList);
    }
    
    public void loadALLAVAILABLEITEMS() {
        itemList.clear();
        cbITEMS.removeAllItems();
        int i = 0;
        items = daoITEMS.getAvailableItems();
        
        for(i = 0 ; i <= items.size()-1 ; i++)
            if(!itemList.contains(items.get(i).getName())){
                itemList.addElement(items.get(i).getName() + " ("+ items.get(i).getStock() +")");
                cbITEMS.addItem(items.get(i).getName());
            }
        
        lstITEMS.setModel(itemList);
    }
    
    private STAFF() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void setDate(){
        Timer timer = new Timer(1000, new setDateActionListener());
        timer.start();
    }
    class setDateActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            currDate = new SimpleDateFormat("dd-MM-yyyy  |  HH:mm:ss").format(Calendar.getInstance().getTime());      
            lblTIME.setText(currDate);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTOP = new javax.swing.JPanel();
        lblUSER = new javax.swing.JLabel();
        lblTIME = new javax.swing.JLabel();
        btnADD = new javax.swing.JButton();
        btnUPDATE = new javax.swing.JButton();
        btnSIGNOUT = new javax.swing.JButton();
        btnVIEWTRANS = new javax.swing.JButton();
        btnPROFILE = new javax.swing.JButton();
        lblLOGO1 = new javax.swing.JLabel();
        lblTITLE7 = new javax.swing.JLabel();
        lblLOGO3 = new javax.swing.JLabel();
        lblTITLE6 = new javax.swing.JLabel();
        panelBOTVIEWTRANS = new javax.swing.JPanel();
        lblTITLE5 = new javax.swing.JLabel();
        lblTITLE11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstITEMS = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblTRANS = new javax.swing.JTable();
        chkAVAILABLE = new javax.swing.JCheckBox();
        cbSTATUS = new javax.swing.JComboBox<>();
        lblTITLE12 = new javax.swing.JLabel();
        panelBOTCRUD = new javax.swing.JPanel();
        lblACTION1 = new javax.swing.JLabel();
        txtPERSONIDRES = new javax.swing.JTextField();
        lblACTION3 = new javax.swing.JLabel();
        txtFNAMERES = new javax.swing.JTextField();
        lblACTION4 = new javax.swing.JLabel();
        lblACTION5 = new javax.swing.JLabel();
        txtLNAMERES = new javax.swing.JTextField();
        lblACTION7 = new javax.swing.JLabel();
        btnADDTRANS = new javax.swing.JButton();
        cbITEMS = new javax.swing.JComboBox<>();
        dtRESERVE = new com.toedter.calendar.JDateChooser();
        cbTYPE = new javax.swing.JComboBox<>();
        lblACTION11 = new javax.swing.JLabel();
        panelBOTPROFILE = new javax.swing.JPanel();
        lblACTION6 = new javax.swing.JLabel();
        txtPERSONIDPROF = new javax.swing.JTextField();
        lblACTION8 = new javax.swing.JLabel();
        txtFNAMEPROF = new javax.swing.JTextField();
        lblACTION9 = new javax.swing.JLabel();
        txtLNAMEPROF = new javax.swing.JTextField();
        lblACTION2 = new javax.swing.JLabel();
        lblACTION10 = new javax.swing.JLabel();
        txtPASSWORD = new javax.swing.JPasswordField();
        txtUSERNAME = new javax.swing.JTextField();
        btnACTION = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("STAFF");
        setMinimumSize(new java.awt.Dimension(1540, 970));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        panelTOP.setBackground(new java.awt.Color(0, 128, 50));
        panelTOP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUSER.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblUSER.setForeground(new java.awt.Color(255, 255, 255));
        lblUSER.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUSER.setText("WELCOME, BLABLA");
        lblUSER.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        panelTOP.add(lblUSER, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, -1, -1));

        lblTIME.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblTIME.setForeground(new java.awt.Color(255, 255, 255));
        lblTIME.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTIME.setText("TIME");
        lblTIME.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        panelTOP.add(lblTIME, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, -1, -1));

        btnADD.setBackground(new java.awt.Color(0, 128, 50));
        btnADD.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        btnADD.setForeground(new java.awt.Color(255, 255, 255));
        btnADD.setText("ADD TRANSACTION  ");
        btnADD.setBorder(null);
        btnADD.setBorderPainted(false);
        btnADD.setContentAreaFilled(false);
        btnADD.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnADD.setFocusPainted(false);
        btnADD.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnADD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnADD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnADDMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnADDMouseExited(evt);
            }
        });
        btnADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnADDActionPerformed(evt);
            }
        });
        panelTOP.add(btnADD, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 160, 250, 35));

        btnUPDATE.setBackground(new java.awt.Color(0, 128, 50));
        btnUPDATE.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        btnUPDATE.setForeground(new java.awt.Color(255, 255, 255));
        btnUPDATE.setText("UPDATE TRANSACTION  ");
        btnUPDATE.setBorder(null);
        btnUPDATE.setBorderPainted(false);
        btnUPDATE.setContentAreaFilled(false);
        btnUPDATE.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUPDATE.setFocusPainted(false);
        btnUPDATE.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnUPDATE.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnUPDATE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnUPDATEMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnUPDATEMouseExited(evt);
            }
        });
        btnUPDATE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUPDATEActionPerformed(evt);
            }
        });
        panelTOP.add(btnUPDATE, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 160, 280, 35));

        btnSIGNOUT.setBackground(new java.awt.Color(0, 128, 50));
        btnSIGNOUT.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        btnSIGNOUT.setForeground(new java.awt.Color(255, 255, 255));
        btnSIGNOUT.setText("LOG OUT  ");
        btnSIGNOUT.setActionCommand("");
        btnSIGNOUT.setBorder(null);
        btnSIGNOUT.setBorderPainted(false);
        btnSIGNOUT.setContentAreaFilled(false);
        btnSIGNOUT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSIGNOUT.setFocusPainted(false);
        btnSIGNOUT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnSIGNOUT.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSIGNOUT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSIGNOUTMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSIGNOUTMouseExited(evt);
            }
        });
        btnSIGNOUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSIGNOUTActionPerformed(evt);
            }
        });
        panelTOP.add(btnSIGNOUT, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 120, 35));

        btnVIEWTRANS.setBackground(new java.awt.Color(0, 128, 50));
        btnVIEWTRANS.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        btnVIEWTRANS.setForeground(new java.awt.Color(255, 255, 255));
        btnVIEWTRANS.setText("VIEW TRANSACTIONS  ");
        btnVIEWTRANS.setActionCommand("");
        btnVIEWTRANS.setBorder(null);
        btnVIEWTRANS.setBorderPainted(false);
        btnVIEWTRANS.setContentAreaFilled(false);
        btnVIEWTRANS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVIEWTRANS.setFocusPainted(false);
        btnVIEWTRANS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnVIEWTRANS.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnVIEWTRANS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVIEWTRANSMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVIEWTRANSMouseExited(evt);
            }
        });
        btnVIEWTRANS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVIEWTRANSActionPerformed(evt);
            }
        });
        panelTOP.add(btnVIEWTRANS, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 160, 260, 35));

        btnPROFILE.setBackground(new java.awt.Color(0, 128, 50));
        btnPROFILE.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        btnPROFILE.setForeground(new java.awt.Color(255, 255, 255));
        btnPROFILE.setText("PROFILE   ");
        btnPROFILE.setBorder(null);
        btnPROFILE.setBorderPainted(false);
        btnPROFILE.setContentAreaFilled(false);
        btnPROFILE.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPROFILE.setFocusPainted(false);
        btnPROFILE.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnPROFILE.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnPROFILE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPROFILEMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPROFILEMouseExited(evt);
            }
        });
        btnPROFILE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPROFILEActionPerformed(evt);
            }
        });
        panelTOP.add(btnPROFILE, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 120, 35));

        lblLOGO1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblLOGO1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLOGO1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/educator-ok.png"))); // NOI18N
        lblLOGO1.setName("lblTITLE"); // NOI18N
        panelTOP.add(lblLOGO1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        lblTITLE7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 30)); // NOI18N
        lblTITLE7.setForeground(new java.awt.Color(251, 197, 49));
        lblTITLE7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTITLE7.setText("FEU TECH");
        lblTITLE7.setName(""); // NOI18N
        panelTOP.add(lblTITLE7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 30, -1, -1));

        lblLOGO3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblLOGO3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLOGO3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/seal-ok.png"))); // NOI18N
        lblLOGO3.setName("lblTITLE"); // NOI18N
        panelTOP.add(lblLOGO3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1430, 0, -1, -1));

        lblTITLE6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 30)); // NOI18N
        lblTITLE6.setForeground(new java.awt.Color(251, 197, 49));
        lblTITLE6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTITLE6.setText("RESERVATION SYSTEM");
        lblTITLE6.setName(""); // NOI18N
        panelTOP.add(lblTITLE6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 70, -1, -1));

        getContentPane().add(panelTOP);
        panelTOP.setBounds(0, 0, 1540, 200);

        panelBOTVIEWTRANS.setBackground(new java.awt.Color(251, 197, 49));
        panelBOTVIEWTRANS.setPreferredSize(new java.awt.Dimension(575, 600));
        panelBOTVIEWTRANS.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTITLE5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 30)); // NOI18N
        lblTITLE5.setForeground(new java.awt.Color(0, 128, 50));
        lblTITLE5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTITLE5.setText("TRANSACTIONS");
        lblTITLE5.setName(""); // NOI18N
        panelBOTVIEWTRANS.add(lblTITLE5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        lblTITLE11.setFont(new java.awt.Font("Segoe UI Symbol", 0, 24)); // NOI18N
        lblTITLE11.setForeground(new java.awt.Color(0, 128, 50));
        lblTITLE11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTITLE11.setText("STATUS:");
        lblTITLE11.setName(""); // NOI18N
        panelBOTVIEWTRANS.add(lblTITLE11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 20, -1, -1));

        jScrollPane1.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        lstITEMS.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lstITEMS.setForeground(new java.awt.Color(0, 128, 50));
        jScrollPane1.setViewportView(lstITEMS);

        panelBOTVIEWTRANS.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 40, 270, 70));

        jScrollPane4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N

        tblTRANS.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        tblTRANS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "U_ID", "B_ID", "B_FNAME", "B_LNAME", "ITEM_ID", "STATUS", "DATE_RES", "DATE_UPDATED"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTRANS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblTRANSMouseReleased(evt);
            }
        });
        jScrollPane4.setViewportView(tblTRANS);

        panelBOTVIEWTRANS.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 1540, 660));

        chkAVAILABLE.setBackground(new java.awt.Color(251, 197, 49));
        chkAVAILABLE.setFont(new java.awt.Font("Segoe UI Symbol", 0, 24)); // NOI18N
        chkAVAILABLE.setForeground(new java.awt.Color(0, 128, 50));
        chkAVAILABLE.setText("AVAILABLE");
        chkAVAILABLE.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkAVAILABLE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAVAILABLEActionPerformed(evt);
            }
        });
        panelBOTVIEWTRANS.add(chkAVAILABLE, new org.netbeans.lib.awtextra.AbsoluteConstraints(1370, 10, -1, -1));

        cbSTATUS.setFont(new java.awt.Font("Segoe UI Symbol", 0, 24)); // NOI18N
        cbSTATUS.setForeground(new java.awt.Color(0, 128, 50));
        cbSTATUS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALL", "BORROWED", "RESERVED", "RETURNED" }));
        cbSTATUS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbSTATUS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSTATUSActionPerformed(evt);
            }
        });
        panelBOTVIEWTRANS.add(cbSTATUS, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 60, -1, -1));

        lblTITLE12.setFont(new java.awt.Font("Segoe UI Symbol", 0, 24)); // NOI18N
        lblTITLE12.setForeground(new java.awt.Color(0, 128, 50));
        lblTITLE12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTITLE12.setText("ITEMS:");
        lblTITLE12.setName(""); // NOI18N
        panelBOTVIEWTRANS.add(lblTITLE12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 10, -1, -1));

        getContentPane().add(panelBOTVIEWTRANS);
        panelBOTVIEWTRANS.setBounds(0, 200, 1540, 770);

        panelBOTCRUD.setBackground(new java.awt.Color(251, 197, 49));
        panelBOTCRUD.setLayout(null);

        lblACTION1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblACTION1.setForeground(new java.awt.Color(0, 128, 50));
        lblACTION1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblACTION1.setText("ITEM");
        lblACTION1.setName("lblTITLE"); // NOI18N
        panelBOTCRUD.add(lblACTION1);
        lblACTION1.setBounds(470, 270, 120, 32);

        txtPERSONIDRES.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        txtPERSONIDRES.setForeground(new java.awt.Color(0, 128, 50));
        txtPERSONIDRES.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPERSONIDRES.setName("txtUSERNAME"); // NOI18N
        txtPERSONIDRES.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPERSONIDRESKeyReleased(evt);
            }
        });
        panelBOTCRUD.add(txtPERSONIDRES);
        txtPERSONIDRES.setBounds(740, 120, 330, 40);

        lblACTION3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblACTION3.setForeground(new java.awt.Color(0, 128, 50));
        lblACTION3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblACTION3.setText("FACULTY/STAFF ID");
        lblACTION3.setName("lblTITLE"); // NOI18N
        panelBOTCRUD.add(lblACTION3);
        lblACTION3.setBounds(470, 120, 220, 32);

        txtFNAMERES.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        txtFNAMERES.setForeground(new java.awt.Color(0, 128, 50));
        txtFNAMERES.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtFNAMERES.setName("txtUSERNAME"); // NOI18N
        panelBOTCRUD.add(txtFNAMERES);
        txtFNAMERES.setBounds(740, 170, 330, 40);

        lblACTION4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblACTION4.setForeground(new java.awt.Color(0, 128, 50));
        lblACTION4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblACTION4.setText("FIRST NAME");
        lblACTION4.setName("lblTITLE"); // NOI18N
        panelBOTCRUD.add(lblACTION4);
        lblACTION4.setBounds(470, 170, 180, 32);

        lblACTION5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblACTION5.setForeground(new java.awt.Color(0, 128, 50));
        lblACTION5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblACTION5.setText("LAST NAME");
        lblACTION5.setName("lblTITLE"); // NOI18N
        panelBOTCRUD.add(lblACTION5);
        lblACTION5.setBounds(470, 220, 170, 32);

        txtLNAMERES.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        txtLNAMERES.setForeground(new java.awt.Color(0, 128, 50));
        txtLNAMERES.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtLNAMERES.setName("txtUSERNAME"); // NOI18N
        panelBOTCRUD.add(txtLNAMERES);
        txtLNAMERES.setBounds(740, 220, 330, 40);

        lblACTION7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblACTION7.setForeground(new java.awt.Color(0, 128, 50));
        lblACTION7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblACTION7.setText("DATE");
        lblACTION7.setName("lblTITLE"); // NOI18N
        panelBOTCRUD.add(lblACTION7);
        lblACTION7.setBounds(470, 370, 120, 32);

        btnADDTRANS.setBackground(new java.awt.Color(251, 197, 49));
        btnADDTRANS.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        btnADDTRANS.setForeground(new java.awt.Color(0, 128, 50));
        btnADDTRANS.setText("RESERVE ITEM");
        btnADDTRANS.setActionCommand("ADD TRANSACTION");
        btnADDTRANS.setBorder(null);
        btnADDTRANS.setBorderPainted(false);
        btnADDTRANS.setContentAreaFilled(false);
        btnADDTRANS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnADDTRANS.setFocusPainted(false);
        btnADDTRANS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnADDTRANSMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnADDTRANSMouseExited(evt);
            }
        });
        btnADDTRANS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnADDTRANSActionPerformed(evt);
            }
        });
        panelBOTCRUD.add(btnADDTRANS);
        btnADDTRANS.setBounds(470, 440, 610, 35);

        cbITEMS.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        cbITEMS.setForeground(new java.awt.Color(0, 128, 50));
        cbITEMS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ITEM1", "ITEM2", "ITEM3" }));
        cbITEMS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelBOTCRUD.add(cbITEMS);
        cbITEMS.setBounds(740, 270, 330, 42);

        dtRESERVE.setForeground(new java.awt.Color(0, 128, 50));
        dtRESERVE.setDateFormatString("dd/MM/yyyy");
        dtRESERVE.setEnabled(false);
        dtRESERVE.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        panelBOTCRUD.add(dtRESERVE);
        dtRESERVE.setBounds(740, 370, 330, 30);

        cbTYPE.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        cbTYPE.setForeground(new java.awt.Color(0, 128, 50));
        cbTYPE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BORROW", "RESERVE" }));
        cbTYPE.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbTYPE.setEnabled(false);
        cbTYPE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTYPEActionPerformed(evt);
            }
        });
        panelBOTCRUD.add(cbTYPE);
        cbTYPE.setBounds(740, 320, 330, 42);

        lblACTION11.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblACTION11.setForeground(new java.awt.Color(0, 128, 50));
        lblACTION11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblACTION11.setText("TYPE");
        lblACTION11.setName("lblTITLE"); // NOI18N
        panelBOTCRUD.add(lblACTION11);
        lblACTION11.setBounds(470, 320, 120, 32);

        getContentPane().add(panelBOTCRUD);
        panelBOTCRUD.setBounds(0, 200, 1540, 770);

        panelBOTPROFILE.setBackground(new java.awt.Color(251, 197, 49));
        panelBOTPROFILE.setPreferredSize(new java.awt.Dimension(575, 600));
        panelBOTPROFILE.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblACTION6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblACTION6.setForeground(new java.awt.Color(0, 128, 50));
        lblACTION6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblACTION6.setText("FACULTY/STAFF ID");
        lblACTION6.setName("lblTITLE"); // NOI18N
        panelBOTPROFILE.add(lblACTION6, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 210, -1, -1));

        txtPERSONIDPROF.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        txtPERSONIDPROF.setForeground(new java.awt.Color(0, 128, 50));
        txtPERSONIDPROF.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPERSONIDPROF.setName("txtUSERNAME"); // NOI18N
        panelBOTPROFILE.add(txtPERSONIDPROF, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 210, 245, -1));

        lblACTION8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblACTION8.setForeground(new java.awt.Color(0, 128, 50));
        lblACTION8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblACTION8.setText("FIRST NAME");
        lblACTION8.setName("lblTITLE"); // NOI18N
        panelBOTPROFILE.add(lblACTION8, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 260, -1, -1));

        txtFNAMEPROF.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        txtFNAMEPROF.setForeground(new java.awt.Color(0, 128, 50));
        txtFNAMEPROF.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtFNAMEPROF.setName("txtUSERNAME"); // NOI18N
        panelBOTPROFILE.add(txtFNAMEPROF, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 260, 245, -1));

        lblACTION9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblACTION9.setForeground(new java.awt.Color(0, 128, 50));
        lblACTION9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblACTION9.setText("LAST NAME");
        lblACTION9.setName("lblTITLE"); // NOI18N
        panelBOTPROFILE.add(lblACTION9, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 310, -1, -1));

        txtLNAMEPROF.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        txtLNAMEPROF.setForeground(new java.awt.Color(0, 128, 50));
        txtLNAMEPROF.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtLNAMEPROF.setName("txtUSERNAME"); // NOI18N
        panelBOTPROFILE.add(txtLNAMEPROF, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 310, 245, -1));

        lblACTION2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblACTION2.setForeground(new java.awt.Color(0, 128, 50));
        lblACTION2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblACTION2.setText("USERNAME");
        lblACTION2.setName("lblTITLE"); // NOI18N
        panelBOTPROFILE.add(lblACTION2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, -1, -1));

        lblACTION10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblACTION10.setForeground(new java.awt.Color(0, 128, 50));
        lblACTION10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblACTION10.setText("PASSWORD");
        lblACTION10.setName("lblTITLE"); // NOI18N
        panelBOTPROFILE.add(lblACTION10, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 150, -1, -1));

        txtPASSWORD.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        txtPASSWORD.setForeground(new java.awt.Color(0, 128, 50));
        txtPASSWORD.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        panelBOTPROFILE.add(txtPASSWORD, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 150, 245, 30));

        txtUSERNAME.setEditable(false);
        txtUSERNAME.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        txtUSERNAME.setForeground(new java.awt.Color(0, 128, 50));
        txtUSERNAME.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtUSERNAME.setName("txtUSERNAME"); // NOI18N
        panelBOTPROFILE.add(txtUSERNAME, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 100, 245, -1));

        btnACTION.setBackground(new java.awt.Color(251, 197, 49));
        btnACTION.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        btnACTION.setForeground(new java.awt.Color(0, 128, 50));
        btnACTION.setText("UPDATE ACCOUNT");
        btnACTION.setActionCommand("");
        btnACTION.setBorder(null);
        btnACTION.setBorderPainted(false);
        btnACTION.setContentAreaFilled(false);
        btnACTION.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnACTION.setFocusPainted(false);
        btnACTION.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnACTIONMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnACTIONMouseExited(evt);
            }
        });
        btnACTION.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnACTIONActionPerformed(evt);
            }
        });
        panelBOTPROFILE.add(btnACTION, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 420, 575, 35));

        getContentPane().add(panelBOTPROFILE);
        panelBOTPROFILE.setBounds(0, 200, 1540, 770);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnADDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnADDMouseEntered
        // TODO add your handling code here:
        btnADD.setBackground(new Color(251,197,49));
        btnADD.setForeground(new Color(0,128,50));
    }//GEN-LAST:event_btnADDMouseEntered

    private void btnADDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnADDMouseExited
        // TODO add your handling code here:

        btnADD.setBackground(new Color(0,128,50));
        btnADD.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnADDMouseExited

    private void btnADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnADDActionPerformed

        cbTYPE.setSelectedIndex(0);
        txtPERSONIDRES.setText("");
        txtFNAMERES.setText("");
        txtLNAMERES.setText("");
        dtRESERVE.setEnabled(false);
        panelBOTVIEWTRANS.setVisible(false);
        panelBOTCRUD.setVisible(true);
        panelBOTPROFILE.setVisible(false);

        btnADDTRANS.setText("ADD TRANSACTION");
        btnUPDATE.setEnabled(false);
        
        loadALLTRANSACTIONS();
    }//GEN-LAST:event_btnADDActionPerformed

    private void btnUPDATEMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUPDATEMouseEntered
        // TODO add your handling code here:
        if(btnUPDATE.isEnabled() == true) {
            btnUPDATE.setBackground(new Color(251,197,49));
            btnUPDATE.setForeground(new Color(0,128,50));
        }
    }//GEN-LAST:event_btnUPDATEMouseEntered

    private void btnUPDATEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUPDATEMouseExited
        // TODO add your handling code here:
        btnUPDATE.setBackground(new Color(0,128,50));
        btnUPDATE.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnUPDATEMouseExited

    private void btnSIGNOUTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSIGNOUTMouseEntered
        // TODO add your handling code here:
        btnSIGNOUT.setBackground(new Color(251,197,49));
        btnSIGNOUT.setForeground(new Color(0,128,50));
    }//GEN-LAST:event_btnSIGNOUTMouseEntered

    private void btnSIGNOUTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSIGNOUTMouseExited
        // TODO add your handling code here:
        btnSIGNOUT.setBackground(new Color(0,128,50));
        btnSIGNOUT.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnSIGNOUTMouseExited

    private void btnSIGNOUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSIGNOUTActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        this.hide();
        LOGIN loginForm = new LOGIN();
        loginForm.setVisible(true);
    }//GEN-LAST:event_btnSIGNOUTActionPerformed

    private void btnVIEWTRANSMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVIEWTRANSMouseEntered
        // TODO add your handling code here:
        btnVIEWTRANS.setBackground(new Color(251,197,49));
        btnVIEWTRANS.setForeground(new Color(0,128,50));
    }//GEN-LAST:event_btnVIEWTRANSMouseEntered

    private void btnVIEWTRANSMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVIEWTRANSMouseExited
        // TODO add your handling code here:
        btnVIEWTRANS.setBackground(new Color(0,128,50));
        btnVIEWTRANS.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnVIEWTRANSMouseExited

    private void btnVIEWTRANSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVIEWTRANSActionPerformed
        // TODO add your handling code here:
        panelBOTVIEWTRANS.setVisible(true);
        panelBOTCRUD.setVisible(false);
        panelBOTPROFILE.setVisible(false);
        
        resetState();
    }//GEN-LAST:event_btnVIEWTRANSActionPerformed

    private void btnPROFILEMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPROFILEMouseEntered
        // TODO add your handling code here:
        btnPROFILE.setBackground(new Color(251,197,49));
        btnPROFILE.setForeground(new Color(0,128,50));
    }//GEN-LAST:event_btnPROFILEMouseEntered

    private void btnPROFILEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPROFILEMouseExited
        // TODO add your handling code here:
        btnPROFILE.setBackground(new Color(0,128,50));
        btnPROFILE.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnPROFILEMouseExited

    private void btnPROFILEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPROFILEActionPerformed
        // CHANGE PANEL
        panelBOTVIEWTRANS.setVisible(false);
        panelBOTCRUD.setVisible(false);
        panelBOTPROFILE.setVisible(true);

        //DISABLED STATE
        stateProf = 0;

        //DISABLE FIELDS
        txtUSERNAME.setEnabled(false);
        txtPASSWORD.setEnabled(false);
        txtFNAMEPROF.setEnabled(false);
        txtLNAMEPROF.setEnabled(false);
        txtPERSONIDPROF.setEnabled(false);

        btnACTION.setText("EDIT PROFILE");

    }//GEN-LAST:event_btnPROFILEActionPerformed

    private void btnADDTRANSMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnADDTRANSMouseEntered
        // TODO add your handling code here:
        if(btnADDTRANS.isEnabled()){
            btnADDTRANS.setBackground(new Color(0,128,50));
            btnADDTRANS.setForeground(new Color(251,197,49));
        }
    }//GEN-LAST:event_btnADDTRANSMouseEntered

    private void btnADDTRANSMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnADDTRANSMouseExited
        // TODO add your handling code here:
        btnADDTRANS.setBackground(new Color(251,197,49));
        btnADDTRANS.setForeground(new Color(0,128,50));
    }//GEN-LAST:event_btnADDTRANSMouseExited

    private void btnACTIONMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnACTIONMouseEntered
        // TODO add your handling code here:
        btnACTION.setBackground(new Color(0,128,50));
        btnACTION.setForeground(new Color(251,197,49));
    }//GEN-LAST:event_btnACTIONMouseEntered

    private void btnACTIONMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnACTIONMouseExited
        // TODO add your handling code here:
        btnACTION.setBackground(new Color(251,197,49));
        btnACTION.setForeground(new Color(0,128,50));
    }//GEN-LAST:event_btnACTIONMouseExited

    private void btnACTIONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnACTIONActionPerformed
        // TODO add your handling code here:
        if(stateProf == 0) {
            stateProf = 1;
            txtPASSWORD.setEnabled(true);
            txtFNAMEPROF.setEnabled(true);
            txtLNAMEPROF.setEnabled(true);
            txtPERSONIDPROF.setEnabled(true);
            btnACTION.setText("UPDATE PROFILE");
        }else if(stateProf == 1){
            
            int Confirmation = JOptionPane.showConfirmDialog(rootPane, "ARE YOU SURE YOU WANT TO UPDATE YOUR PROFILE?", "CONFIRMATION", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(Confirmation == 0){  
                // ENTER CODE TO UPDATE PROFILE
                tempAccount = new CLASS_ACCOUNTS(currID, txtUSERNAME.getText(), txtPASSWORD.getText(), txtPERSONIDPROF.getText(), txtFNAMEPROF.getText(), txtLNAMEPROF.getText(), currROLE);
                try {
                    daoACCOUNTS.updateAccount(tempAccount);
                    resetState();
                    btnPROFILE.doClick();
                } catch (SQLException ex) {
                    Logger.getLogger(STAFF.class.getName()).log(Level.SEVERE, null, ex);
                }

                
            }
        }
    }//GEN-LAST:event_btnACTIONActionPerformed

    private void cbTYPEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTYPEActionPerformed
        // TODO add your handling code here:
        if(cbTYPE.getSelectedIndex() == 1){
            dtRESERVE.setEnabled(true);
            btnACTION.setText("ADD RESERVATION");
        }else{
            dtRESERVE.setEnabled(false);
            dtRESERVE.setDate(null);
            btnACTION.setText("ADD TRANSACTION");
        }
    }//GEN-LAST:event_cbTYPEActionPerformed

    private void btnADDTRANSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnADDTRANSActionPerformed
        // TODO add your handling code here:
        //RESET FIELDS
        int type = cbTYPE.getSelectedIndex();
        String b_id = txtPERSONIDRES.getText();
        String b_fname = txtFNAMERES.getText();
        String b_lname = txtLNAMERES.getText();
        String item_name = cbITEMS.getSelectedItem().toString();
        String status = "";
        
        Date date_reservation = dtRESERVE.getDate();
        String date_res = (date_reservation != null) ? ((JTextField)dtRESERVE.getDateEditor().getUiComponent()).getText() : null;
        
        items = daoITEMS.searchItem(item_name);
        int item_id = items.get(0).getId();
        int item_stock = items.get(0).getStock() - 1;
        if(type == 1){
            //ADD RESERVATION CODE
            status = "RESERVED";
        }else{
            //ADD TRANSACTION CODE
            status = "BORROWED";
            
        }
        
        tempTransaction = new CLASS_TRANSACTIONS(newID, user_id, b_id, b_fname, b_lname, item_id, status, date_res, null);
        int Confirmation = JOptionPane.showConfirmDialog(rootPane, "ARE YOU SURE YOU WANT TO ADD NEW TRANSACTION?", "CONFIRMATION", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(Confirmation == 0){
            try {
                daoTRANSACTIONS.addTransaction(tempTransaction);
                daoITEMS.updateStock(item_id, item_stock);
                resetState();
                btnVIEWTRANS.doClick();
            } catch (SQLException ex) {
                Logger.getLogger(STAFF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnADDTRANSActionPerformed

    private void txtPERSONIDRESKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPERSONIDRESKeyReleased
        String personID = txtPERSONIDRES.getText();
        user_id = 0;
        accounts = daoACCOUNTS.searchAccountByPersonID(personID);
        txtFNAMERES.setEnabled(true);
        txtLNAMERES.setEnabled(true);
        cbTYPE.setEnabled(false);
        cbTYPE.setSelectedIndex(0);
        if(!accounts.isEmpty()){
            txtFNAMERES.setText(accounts.get(0).getFname());
            txtLNAMERES.setText(accounts.get(0).getLname());
            txtFNAMERES.setEnabled(false);
            txtLNAMERES.setEnabled(false);
            user_id = accounts.get(0).getId();
            cbTYPE.setEnabled(true);
            transactions = daoTRANSACTIONS.getUserCurrentTransaction(user_id);
            if(!transactions.isEmpty()){
                btnADDTRANS.setEnabled(false);
                JOptionPane.showMessageDialog(rootPane, "BORROWER STILL HAS INCOMPLETE TRANSACTION.", "INFORMATION", JOptionPane.OK_OPTION);
            }else{
                btnADDTRANS.setEnabled(true);
            }
        }
        
    }//GEN-LAST:event_txtPERSONIDRESKeyReleased

    private void tblTRANSMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTRANSMouseReleased
        // TODO add your handling code here:
        // get selected transactions
        int row = tblTRANS.getSelectedRow();
        // make sure a row is selected
        if(row < 0) {
            btnUPDATE.setEnabled(false);
        }else{
            String stat = (String.valueOf(tblTRANS.getValueAt(row, TableModelTRANSACTIONS.STATUS_COL)));
            btnUPDATE.setEnabled(!stat.equals("RETURNED") && !stat.equals("CANCELLED"));
            
            // GET VALUE OF SELECTED ROW
            selectedID = Integer.parseInt(String.valueOf(tblTRANS.getValueAt(row, TableModelTRANSACTIONS.ID_COL))); 
        }
    }//GEN-LAST:event_tblTRANSMouseReleased

    private void btnUPDATEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUPDATEActionPerformed
        // TODO add your handling code here:
        
        //Custom button text
        Object[] reservedOptions = {"BORROWED","RETURNED","CANCEL"};
        Object[] borrowedOptions = {"RETURNED","CANCEL"};
           
        int Confirmation = 19;
        transactions = daoTRANSACTIONS.searchTransaction(selectedID);
        String status = null;
        String newStatus = null;
        String itemName = null;
        String b_id = null;
        int item_id = 19;
        int item_stock = 19;
        try {
            status = transactions.get(0).getStatus().trim().toUpperCase();
            b_id = transactions.get(0).getBorrower_id().trim().toUpperCase();
            item_id = transactions.get(0).getItem_id();
            itemName = daoTRANSACTIONS.getItemName(item_id);
            item_stock = daoITEMS.searchItem(itemName).get(0).getStock();
        } catch (SQLException ex) {
            Logger.getLogger(STAFF.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
        if(status.equals("RESERVED")){
            Confirmation = JOptionPane.showOptionDialog(rootPane, "DO YOU WANT TO UPDATE STATUS OF TRANSACTION \nID = "+ selectedID +"    |    BORROWER-ID = "+ b_id +"     |    ITEM = "+ itemName +"\n FROM RESERVED TO _______?", "CONFIRMATION", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, reservedOptions, reservedOptions[2]);
            newStatus = reservedOptions[Confirmation].toString();
        }else if (status.equals("BORROWED")){
            Confirmation = JOptionPane.showOptionDialog(rootPane, "DO YOU WANT TO UPDATE STATUS OF TRANSACTION \nID = "+ selectedID +"    |    BORROWER-ID = "+ b_id +"     |    ITEM = "+ itemName +"\n FROM BORROWED TO _______?", "CONFIRMATION", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, borrowedOptions, borrowedOptions[1]);
            newStatus = borrowedOptions[Confirmation].toString();
            
        }
        
        if(!newStatus.equals("CANCEL") && !newStatus.equals(null)){
            try {
                if(newStatus.equals("RETURNED"))
                    daoITEMS.updateStock(item_id, item_stock + 1);
                daoTRANSACTIONS.updateStatus(selectedID, newStatus);
                resetState();
                btnVIEWTRANS.doClick();
            } catch (SQLException ex) {
                Logger.getLogger(STAFF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnUPDATEActionPerformed

    private void chkAVAILABLEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAVAILABLEActionPerformed
        // TODO add your handling code here:
        if(chkAVAILABLE.isSelected())
        loadALLAVAILABLEITEMS();
        else
        loadALLITEMS();
    }//GEN-LAST:event_chkAVAILABLEActionPerformed

    private void cbSTATUSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSTATUSActionPerformed
        // TODO add your handling code here:
        if(cbSTATUS.getItemCount() > 0){
            boolean isALL = (cbSTATUS.getSelectedIndex() == 0);
            if(isALL){
                loadALLTRANSACTIONS();
            }else{
                loadTRANSACTIONSBYSTATUS(cbSTATUS.getSelectedItem().toString()) ;
            }
        }
    }//GEN-LAST:event_cbSTATUSActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(STAFF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(STAFF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(STAFF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(STAFF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new STAFF().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnACTION;
    private javax.swing.JButton btnADD;
    private javax.swing.JButton btnADDTRANS;
    private javax.swing.JButton btnPROFILE;
    private javax.swing.JButton btnSIGNOUT;
    private javax.swing.JButton btnUPDATE;
    private javax.swing.JButton btnVIEWTRANS;
    private javax.swing.JComboBox<String> cbITEMS;
    private javax.swing.JComboBox<String> cbSTATUS;
    private javax.swing.JComboBox<String> cbTYPE;
    private javax.swing.JCheckBox chkAVAILABLE;
    private com.toedter.calendar.JDateChooser dtRESERVE;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblACTION1;
    private javax.swing.JLabel lblACTION10;
    private javax.swing.JLabel lblACTION11;
    private javax.swing.JLabel lblACTION2;
    private javax.swing.JLabel lblACTION3;
    private javax.swing.JLabel lblACTION4;
    private javax.swing.JLabel lblACTION5;
    private javax.swing.JLabel lblACTION6;
    private javax.swing.JLabel lblACTION7;
    private javax.swing.JLabel lblACTION8;
    private javax.swing.JLabel lblACTION9;
    private javax.swing.JLabel lblLOGO1;
    private javax.swing.JLabel lblLOGO3;
    private javax.swing.JLabel lblTIME;
    private javax.swing.JLabel lblTITLE11;
    private javax.swing.JLabel lblTITLE12;
    private javax.swing.JLabel lblTITLE5;
    private javax.swing.JLabel lblTITLE6;
    private javax.swing.JLabel lblTITLE7;
    private javax.swing.JLabel lblUSER;
    private javax.swing.JList<String> lstITEMS;
    private javax.swing.JPanel panelBOTCRUD;
    private javax.swing.JPanel panelBOTPROFILE;
    private javax.swing.JPanel panelBOTVIEWTRANS;
    private javax.swing.JPanel panelTOP;
    private javax.swing.JTable tblTRANS;
    private javax.swing.JTextField txtFNAMEPROF;
    private javax.swing.JTextField txtFNAMERES;
    private javax.swing.JTextField txtLNAMEPROF;
    private javax.swing.JTextField txtLNAMERES;
    private javax.swing.JPasswordField txtPASSWORD;
    private javax.swing.JTextField txtPERSONIDPROF;
    private javax.swing.JTextField txtPERSONIDRES;
    private javax.swing.JTextField txtUSERNAME;
    // End of variables declaration//GEN-END:variables
}
