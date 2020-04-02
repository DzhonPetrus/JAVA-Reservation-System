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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class FACULTY extends javax.swing.JFrame {

    private final int currID;

    /**
     * Creates new form FACULTY
     * @param currID
     * @param currUSER
     */
    public FACULTY(int currID, String currUSER) {
        initComponents();
        setDate();
        this.currUSER = currUSER;
        // COMPONENTS PROP INIT
        btnADD.setOpaque(true);
        btnPROFILE.setOpaque(true);
        btnCANCEL.setOpaque(true);
        btnSIGNOUT.setOpaque(true);
        btnRES.setOpaque(true);
        btnVIEWTRANS.setOpaque(true);
        btnACTION.setOpaque(true);
        
        
        //DATE SELECTION NOW -> ONWARD
        Date date = new Date();
        dtRESERVE.setDate(date);
        dtRESERVE.setMinSelectableDate(date);
        this.currID = currID;
        lblUSER.setText("Welcome, " + currUSER);
        
        btnVIEWTRANS.doClick();
    }
    
    public DAO_ACCOUNTS daoACCOUNTS = new DAO_ACCOUNTS();
    public DAO_TRANSACTIONS daoTRANSACTIONS = new DAO_TRANSACTIONS();
    public CLASS_ACCOUNTS tempAccount = null;
    public CLASS_TRANSACTIONS tempTransaction = null;
    public List<CLASS_TRANSACTIONS> transactions = null;
    public List<CLASS_ACCOUNTS> accounts = null;
    public DAO_ITEMS daoITEMS = new DAO_ITEMS();
    public List<CLASS_ITEMS> items =null;
    public DefaultListModel itemList = new DefaultListModel();
    
    public int stateProf = 0; // STATE OF PROFILE
    public int newID = 0;
    public int selectedID = 0 ;
    public String currUSER = "";
    public int currROLE = 0;
    
    public void resetState() throws SQLException{
        stateProf = 0; // STATE OF PROFILE
        newID = 0;
        selectedID = 0 ;
        
        btnCANCEL.setEnabled(false);
        btnADD.setEnabled(false);
        accounts = daoACCOUNTS.searchAccount(currID);
        txtUSERNAME.setText(accounts.get(0).getUsername());
        txtPASSWORD.setText(accounts.get(0).getPassword());
        txtFNAMEPROF.setText(accounts.get(0).getFname());
        txtFNAMERES.setText(accounts.get(0).getFname());
        txtLNAMEPROF.setText(accounts.get(0).getLname());
        txtLNAMERES.setText(accounts.get(0).getLname());
        txtPERSONIDPROF.setText(accounts.get(0).getPerson_id());
        txtPERSONIDRES.setText(accounts.get(0).getPerson_id());
        currROLE = accounts.get(0).getRole();
        loadALLUSERTRANSACTIONS();
        getUserCurrentTransaction();
        loadALLAVAILABLEITEMS();
    }
    
     public void getUserCurrentTransaction() throws SQLException{
        transactions = daoTRANSACTIONS.getUserCurrentTransaction(currID);
        if(transactions.isEmpty()){
            lblITEMTITLE.setText("CURRENT TRANSACTION:");
            lblRESITEM.setText("NONE");
            btnADD.setEnabled(true);
            btnCANCEL.setEnabled(false);
        }else{
            lblITEMTITLE.setText((transactions.get(0).getStatus().equals("RESERVED")) ? "CURRENT RESERVED ITEM:" : "CURRENT BORROWED ITEM:");
            lblRESITEM.setText(daoTRANSACTIONS.getItemName(transactions.get(0).getItem_id()));
            btnADD.setEnabled(false);
            btnCANCEL.setEnabled(transactions.get(0).getStatus().equals("RESERVED"));
        }
    }
    
    public void loadALLUSERTRANSACTIONS(){
        transactions = daoTRANSACTIONS.getAllTransactions();
        // LOOK IF THERE ARE TRANSACTIONS
        newID = transactions.isEmpty() ?  0  : transactions.get(transactions.size()-1).getId() + 1;
        
        transactions = daoTRANSACTIONS.getAllUserTransactions(currID);
        TableModelTRANSACTIONS model = new TableModelTRANSACTIONS(transactions);
        tblTRANS.setModel(model);
    }
    
        public void loadALLAVAILABLEITEMS() {
        itemList.clear();
        cbITEMS.removeAllItems();
        int i = 0;
        items = daoITEMS.getAvailableItems();
        
        for(i = 0 ; i <= items.size()-1 ; i++)
            if(!itemList.contains(items.get(i).getName())){
                itemList.addElement(items.get(i).getName());
                cbITEMS.addItem(items.get(i).getName());
            }
        
    }
    
    private FACULTY() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void setDate(){
        Timer timer = new Timer(1000, new setDateActionListener());
        timer.start();
    }
    class setDateActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String currDate = new SimpleDateFormat("dd-MM-yyyy  |  HH:mm:ss").format(Calendar.getInstance().getTime());      
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
        btnCANCEL = new javax.swing.JButton();
        btnSIGNOUT = new javax.swing.JButton();
        btnVIEWTRANS = new javax.swing.JButton();
        btnPROFILE = new javax.swing.JButton();
        lblLOGO3 = new javax.swing.JLabel();
        lblTITLE7 = new javax.swing.JLabel();
        lblTITLE6 = new javax.swing.JLabel();
        lblLOGO1 = new javax.swing.JLabel();
        panelBOTVIEWTRANS = new javax.swing.JPanel();
        lblTITLE5 = new javax.swing.JLabel();
        lblRESITEM = new javax.swing.JLabel();
        lblITEMTITLE = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblTRANS = new javax.swing.JTable();
        panelBOTCRUD = new javax.swing.JPanel();
        lblACTION1 = new javax.swing.JLabel();
        txtPERSONIDRES = new javax.swing.JTextField();
        lblACTION3 = new javax.swing.JLabel();
        txtFNAMERES = new javax.swing.JTextField();
        lblACTION4 = new javax.swing.JLabel();
        lblACTION5 = new javax.swing.JLabel();
        txtLNAMERES = new javax.swing.JTextField();
        lblACTION7 = new javax.swing.JLabel();
        btnRES = new javax.swing.JButton();
        cbITEMS = new javax.swing.JComboBox<>();
        dtRESERVE = new com.toedter.calendar.JDateChooser();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FACULTY");
        setAlwaysOnTop(true);
        setMinimumSize(new java.awt.Dimension(1540, 870));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1540, 970));
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
        btnADD.setText("ADD RESERVATION  ");
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
        panelTOP.add(btnADD, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 160, 240, 35));

        btnCANCEL.setBackground(new java.awt.Color(0, 128, 50));
        btnCANCEL.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        btnCANCEL.setForeground(new java.awt.Color(255, 255, 255));
        btnCANCEL.setText("CANCEL RESERVATION  ");
        btnCANCEL.setBorder(null);
        btnCANCEL.setBorderPainted(false);
        btnCANCEL.setContentAreaFilled(false);
        btnCANCEL.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCANCEL.setFocusPainted(false);
        btnCANCEL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnCANCEL.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnCANCEL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCANCELMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCANCELMouseExited(evt);
            }
        });
        btnCANCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCANCELActionPerformed(evt);
            }
        });
        panelTOP.add(btnCANCEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 160, 280, 35));

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
        panelTOP.add(btnVIEWTRANS, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 160, 270, 35));

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

        lblLOGO3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblLOGO3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLOGO3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/seal-ok.png"))); // NOI18N
        lblLOGO3.setName("lblTITLE"); // NOI18N
        panelTOP.add(lblLOGO3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1430, 0, -1, -1));

        lblTITLE7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 30)); // NOI18N
        lblTITLE7.setForeground(new java.awt.Color(251, 197, 49));
        lblTITLE7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTITLE7.setText("FEU TECH");
        lblTITLE7.setName(""); // NOI18N
        panelTOP.add(lblTITLE7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 30, -1, -1));

        lblTITLE6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 30)); // NOI18N
        lblTITLE6.setForeground(new java.awt.Color(251, 197, 49));
        lblTITLE6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTITLE6.setText("RESERVATION SYSTEM");
        lblTITLE6.setName(""); // NOI18N
        panelTOP.add(lblTITLE6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 70, -1, -1));

        lblLOGO1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblLOGO1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLOGO1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/educator-ok.png"))); // NOI18N
        lblLOGO1.setName("lblTITLE"); // NOI18N
        panelTOP.add(lblLOGO1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

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
        panelBOTVIEWTRANS.add(lblTITLE5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lblRESITEM.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblRESITEM.setForeground(new java.awt.Color(0, 128, 50));
        lblRESITEM.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRESITEM.setText("ITEM");
        lblRESITEM.setToolTipText("");
        lblRESITEM.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblRESITEM.setName(""); // NOI18N
        panelBOTVIEWTRANS.add(lblRESITEM, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 20, -1, -1));

        lblITEMTITLE.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblITEMTITLE.setForeground(new java.awt.Color(0, 128, 50));
        lblITEMTITLE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblITEMTITLE.setText("CURRENT RESERVED ITEM:");
        lblITEMTITLE.setName(""); // NOI18N
        panelBOTVIEWTRANS.add(lblITEMTITLE, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 20, -1, -1));

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
        jScrollPane4.setViewportView(tblTRANS);

        panelBOTVIEWTRANS.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1540, 710));

        getContentPane().add(panelBOTVIEWTRANS);
        panelBOTVIEWTRANS.setBounds(0, 200, 1540, 770);

        panelBOTCRUD.setBackground(new java.awt.Color(251, 197, 49));
        panelBOTCRUD.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblACTION1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblACTION1.setForeground(new java.awt.Color(0, 128, 50));
        lblACTION1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblACTION1.setText("ITEM");
        lblACTION1.setName("lblTITLE"); // NOI18N
        panelBOTCRUD.add(lblACTION1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 150, -1, -1));

        txtPERSONIDRES.setEditable(false);
        txtPERSONIDRES.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        txtPERSONIDRES.setForeground(new java.awt.Color(0, 128, 50));
        txtPERSONIDRES.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPERSONIDRES.setName("txtUSERNAME"); // NOI18N
        panelBOTCRUD.add(txtPERSONIDRES, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 250, 300, -1));

        lblACTION3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblACTION3.setForeground(new java.awt.Color(0, 128, 50));
        lblACTION3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblACTION3.setText("FACULTY/STAFF ID");
        lblACTION3.setName("lblTITLE"); // NOI18N
        panelBOTCRUD.add(lblACTION3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 250, -1, -1));

        txtFNAMERES.setEditable(false);
        txtFNAMERES.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        txtFNAMERES.setForeground(new java.awt.Color(0, 128, 50));
        txtFNAMERES.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtFNAMERES.setName("txtUSERNAME"); // NOI18N
        panelBOTCRUD.add(txtFNAMERES, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 300, 300, -1));

        lblACTION4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblACTION4.setForeground(new java.awt.Color(0, 128, 50));
        lblACTION4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblACTION4.setText("FIRST NAME");
        lblACTION4.setName("lblTITLE"); // NOI18N
        panelBOTCRUD.add(lblACTION4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 300, -1, -1));

        lblACTION5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblACTION5.setForeground(new java.awt.Color(0, 128, 50));
        lblACTION5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblACTION5.setText("LAST NAME");
        lblACTION5.setName("lblTITLE"); // NOI18N
        panelBOTCRUD.add(lblACTION5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 350, -1, -1));

        txtLNAMERES.setEditable(false);
        txtLNAMERES.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        txtLNAMERES.setForeground(new java.awt.Color(0, 128, 50));
        txtLNAMERES.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtLNAMERES.setName("txtUSERNAME"); // NOI18N
        panelBOTCRUD.add(txtLNAMERES, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 350, 300, -1));

        lblACTION7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblACTION7.setForeground(new java.awt.Color(0, 128, 50));
        lblACTION7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblACTION7.setText("DATE");
        lblACTION7.setName("lblTITLE"); // NOI18N
        panelBOTCRUD.add(lblACTION7, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 200, -1, -1));

        btnRES.setBackground(new java.awt.Color(251, 197, 49));
        btnRES.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        btnRES.setForeground(new java.awt.Color(0, 128, 50));
        btnRES.setText("RESERVE ITEM");
        btnRES.setBorder(null);
        btnRES.setBorderPainted(false);
        btnRES.setContentAreaFilled(false);
        btnRES.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRES.setFocusPainted(false);
        btnRES.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRESMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRESMouseExited(evt);
            }
        });
        btnRES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRESActionPerformed(evt);
            }
        });
        panelBOTCRUD.add(btnRES, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 420, 575, 35));

        cbITEMS.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        cbITEMS.setForeground(new java.awt.Color(0, 128, 50));
        cbITEMS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ITEM1", "ITEM2", "ITEM3" }));
        panelBOTCRUD.add(cbITEMS, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 150, 300, -1));

        dtRESERVE.setForeground(new java.awt.Color(0, 128, 50));
        dtRESERVE.setDateFormatString("dd/MM/yyyy");
        dtRESERVE.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        panelBOTCRUD.add(dtRESERVE, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 200, 300, 30));

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
        panelBOTPROFILE.add(lblACTION6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, -1, -1));

        txtPERSONIDPROF.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        txtPERSONIDPROF.setForeground(new java.awt.Color(0, 128, 50));
        txtPERSONIDPROF.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPERSONIDPROF.setName("txtUSERNAME"); // NOI18N
        panelBOTPROFILE.add(txtPERSONIDPROF, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 200, 245, -1));

        lblACTION8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblACTION8.setForeground(new java.awt.Color(0, 128, 50));
        lblACTION8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblACTION8.setText("FIRST NAME");
        lblACTION8.setName("lblTITLE"); // NOI18N
        panelBOTPROFILE.add(lblACTION8, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 260, -1, -1));

        txtFNAMEPROF.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        txtFNAMEPROF.setForeground(new java.awt.Color(0, 128, 50));
        txtFNAMEPROF.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtFNAMEPROF.setName("txtUSERNAME"); // NOI18N
        panelBOTPROFILE.add(txtFNAMEPROF, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 250, 245, -1));

        lblACTION9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblACTION9.setForeground(new java.awt.Color(0, 128, 50));
        lblACTION9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblACTION9.setText("LAST NAME");
        lblACTION9.setName("lblTITLE"); // NOI18N
        panelBOTPROFILE.add(lblACTION9, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 310, -1, -1));

        txtLNAMEPROF.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        txtLNAMEPROF.setForeground(new java.awt.Color(0, 128, 50));
        txtLNAMEPROF.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtLNAMEPROF.setName("txtUSERNAME"); // NOI18N
        panelBOTPROFILE.add(txtLNAMEPROF, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 300, 245, -1));

        lblACTION2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblACTION2.setForeground(new java.awt.Color(0, 128, 50));
        lblACTION2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblACTION2.setText("USERNAME");
        lblACTION2.setName("lblTITLE"); // NOI18N
        panelBOTPROFILE.add(lblACTION2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, -1, -1));

        lblACTION10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblACTION10.setForeground(new java.awt.Color(0, 128, 50));
        lblACTION10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblACTION10.setText("PASSWORD");
        lblACTION10.setName("lblTITLE"); // NOI18N
        panelBOTPROFILE.add(lblACTION10, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 150, -1, -1));

        txtPASSWORD.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        txtPASSWORD.setForeground(new java.awt.Color(0, 128, 50));
        txtPASSWORD.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        panelBOTPROFILE.add(txtPASSWORD, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 140, 245, 30));

        txtUSERNAME.setEditable(false);
        txtUSERNAME.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        txtUSERNAME.setForeground(new java.awt.Color(0, 128, 50));
        txtUSERNAME.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtUSERNAME.setName("txtUSERNAME"); // NOI18N
        panelBOTPROFILE.add(txtUSERNAME, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 90, 245, -1));

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
        panelBOTPROFILE.add(btnACTION, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 410, 575, 35));

        getContentPane().add(panelBOTPROFILE);
        panelBOTPROFILE.setBounds(0, 200, 1540, 770);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnADDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnADDMouseEntered
        // TODO add your handling code here:
        if(btnADD.isEnabled() == true) {
            btnADD.setBackground(new Color(251,197,49));
            btnADD.setForeground(new Color(0,128,50));
        }
    }//GEN-LAST:event_btnADDMouseEntered

    private void btnADDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnADDMouseExited
        // TODO add your handling code here:

        btnADD.setBackground(new Color(0,128,50));
        btnADD.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnADDMouseExited

    private void btnADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnADDActionPerformed

        panelBOTVIEWTRANS.setVisible(false);
        panelBOTCRUD.setVisible(true);
        panelBOTPROFILE.setVisible(false);

        btnRES.setText("ADD RESERVATION");
    }//GEN-LAST:event_btnADDActionPerformed

    private void btnCANCELMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCANCELMouseEntered
        // TODO add your handling code here:
        if(btnCANCEL.isEnabled() == true) {
            btnCANCEL.setBackground(new Color(251,197,49));
            btnCANCEL.setForeground(new Color(0,128,50));
        }
    }//GEN-LAST:event_btnCANCELMouseEntered

    private void btnCANCELMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCANCELMouseExited
        // TODO add your handling code here:
        btnCANCEL.setBackground(new Color(0,128,50));
        btnCANCEL.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnCANCELMouseExited

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

    private void btnVIEWTRANSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVIEWTRANSActionPerformed
        // TODO add your handling code here:
        panelBOTVIEWTRANS.setVisible(true);
        panelBOTCRUD.setVisible(false);
        panelBOTPROFILE.setVisible(false);
        
        try {
            resetState();
        } catch (SQLException ex) {
            Logger.getLogger(FACULTY.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnVIEWTRANSActionPerformed

    private void btnVIEWTRANSMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVIEWTRANSMouseExited
        // TODO add your handling code here:
        btnVIEWTRANS.setBackground(new Color(0,128,50));
        btnVIEWTRANS.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnVIEWTRANSMouseExited

    private void btnVIEWTRANSMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVIEWTRANSMouseEntered
        // TODO add your handling code here:
        btnVIEWTRANS.setBackground(new Color(251,197,49));
        btnVIEWTRANS.setForeground(new Color(0,128,50));
    }//GEN-LAST:event_btnVIEWTRANSMouseEntered

    private void btnRESMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRESMouseEntered
        // TODO add your handling code here:
        btnRES.setBackground(new Color(0,128,50));
        btnRES.setForeground(new Color(251,197,49));
    }//GEN-LAST:event_btnRESMouseEntered

    private void btnRESMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRESMouseExited
        // TODO add your handling code here:
        btnRES.setBackground(new Color(251,197,49));
        btnRES.setForeground(new Color(0,128,50));
    }//GEN-LAST:event_btnRESMouseExited

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
        
        txtUSERNAME.setEnabled(false);
        txtPASSWORD.setEnabled(false);
        txtFNAMEPROF.setEnabled(false);
        txtLNAMEPROF.setEnabled(false);
        txtPERSONIDPROF.setEnabled(false);
        
        btnACTION.setText("EDIT PROFILE");
        
        
    }//GEN-LAST:event_btnPROFILEActionPerformed

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
            if(Confirmation == 0)  
                // ENTER CODE TO UPDATE PROFILE
                tempAccount = new CLASS_ACCOUNTS(currID, txtUSERNAME.getText(), txtPASSWORD.getText(), txtPERSONIDPROF.getText(), txtFNAMEPROF.getText(), txtLNAMEPROF.getText(), currROLE);
                try {
                    daoACCOUNTS.updateAccount(tempAccount);
                } catch (SQLException ex) {
                    Logger.getLogger(STAFF.class.getName()).log(Level.SEVERE, null, ex);
                }

            try {
                resetState();
            } catch (SQLException ex) {
                Logger.getLogger(FACULTY.class.getName()).log(Level.SEVERE, null, ex);
            }
                btnPROFILE.doClick();
        }
    }//GEN-LAST:event_btnACTIONActionPerformed

    private void btnRESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRESActionPerformed
        // TODO add your handling code here:
        String b_id = txtPERSONIDRES.getText();
        String b_fname = txtFNAMERES.getText();
        String b_lname = txtLNAMERES.getText();
        String item_name = cbITEMS.getSelectedItem().toString();
        String status = "RESERVED";
        
        Date date_reservation = dtRESERVE.getDate();
        String date_res = (date_reservation != null) ? ((JTextField)dtRESERVE.getDateEditor().getUiComponent()).getText() : null;
        
        items = daoITEMS.searchItem(item_name);
        int item_id = items.get(0).getId();
        
        tempTransaction = new CLASS_TRANSACTIONS(newID, currID, b_id, b_fname, b_lname, item_id, status, date_res, null);
        int Confirmation = JOptionPane.showConfirmDialog(rootPane, "ARE YOU SURE YOU WANT TO RESERVE \nITEM = "+ item_name +"   |   DATE = "+ date_res +"?", "CONFIRMATION", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(Confirmation == 0){
            try {
                daoTRANSACTIONS.addTransaction(tempTransaction);
                btnVIEWTRANS.doClick();
            } catch (SQLException ex) {
                Logger.getLogger(STAFF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnRESActionPerformed

    private void btnCANCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCANCELActionPerformed
        // TODO add your handling code here:
        transactions = daoTRANSACTIONS.getUserCurrentTransaction(currID);
        
        int Confirmation;
        try {
            Confirmation = JOptionPane.showConfirmDialog(rootPane, "ARE YOU SURE YOU WANT TO CANCEL YOUR RESERVATION \nITEM = "+ daoTRANSACTIONS.getItemName(transactions.get(0).getItem_id()) +"   |   DATE = "+ transactions.get(0).getDate() +"?", "CONFIRMATION", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE); 
            if(Confirmation == 0){ 
                items = daoITEMS.searchItem(daoTRANSACTIONS.getItemName(transactions.get(0).getItem_id()));
                daoITEMS.updateStock(items.get(0).getId(), items.get(0).getStock()+1);
                daoTRANSACTIONS.updateStatus(transactions.get(0).getId(), "CANCELLED");
                btnVIEWTRANS.doClick();
            } 
        } catch (SQLException ex) {
            Logger.getLogger(FACULTY.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnCANCELActionPerformed

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
            java.util.logging.Logger.getLogger(FACULTY.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FACULTY.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FACULTY.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FACULTY.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FACULTY().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnACTION;
    private javax.swing.JButton btnADD;
    private javax.swing.JButton btnCANCEL;
    private javax.swing.JButton btnPROFILE;
    private javax.swing.JButton btnRES;
    private javax.swing.JButton btnSIGNOUT;
    private javax.swing.JButton btnVIEWTRANS;
    private javax.swing.JComboBox<String> cbITEMS;
    private com.toedter.calendar.JDateChooser dtRESERVE;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblACTION1;
    private javax.swing.JLabel lblACTION10;
    private javax.swing.JLabel lblACTION2;
    private javax.swing.JLabel lblACTION3;
    private javax.swing.JLabel lblACTION4;
    private javax.swing.JLabel lblACTION5;
    private javax.swing.JLabel lblACTION6;
    private javax.swing.JLabel lblACTION7;
    private javax.swing.JLabel lblACTION8;
    private javax.swing.JLabel lblACTION9;
    private javax.swing.JLabel lblITEMTITLE;
    private javax.swing.JLabel lblLOGO1;
    private javax.swing.JLabel lblLOGO3;
    private javax.swing.JLabel lblRESITEM;
    private javax.swing.JLabel lblTIME;
    private javax.swing.JLabel lblTITLE5;
    private javax.swing.JLabel lblTITLE6;
    private javax.swing.JLabel lblTITLE7;
    private javax.swing.JLabel lblUSER;
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
