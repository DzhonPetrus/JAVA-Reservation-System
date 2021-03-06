/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.inventory.reservation.system;

import simple.inventory.reservation.system.DB.CLASS_ACCOUNTS;
import simple.inventory.reservation.system.DB.DAO_ACCOUNTS;
import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author dzhon
 */
public class LOGIN extends javax.swing.JFrame {

    
    // LOGIN FLAG ERROR
    public void flagError() {
        lblFLAG.setOpaque(true);
        lblFLAG.setBackground(Color.red);
        txtPASSWORD.setText("");
        lblPASS.setVisible(true);
    }
            
    /**
     * Creates new form LOGIN
     */
    public LOGIN() {
        initComponents();
    }

    
    public List<CLASS_ACCOUNTS> accounts = null;
    public DAO_ACCOUNTS daoACCOUNTS = new DAO_ACCOUNTS();
    
    public void verifyAccountRole(int role, int currID, String currUSER){
        switch(role) {
            case 0: // ADMIN ROLE
                LOGIN.this.hide();
                ADMIN adminForm = new ADMIN(currID, currUSER);
                adminForm.setVisible(true);
                break;
            case 1: // STAFF ROLE
                LOGIN.this.hide();
                STAFF staffForm = new STAFF(currID, currUSER);
                staffForm.setVisible(true);
                break;
            case 2: //FACULTY ROLE
                LOGIN.this.hide();
                FACULTY facultyForm = new FACULTY(currID, currUSER);
                facultyForm.setVisible(true);
                break;
        }
    }
    
    public Boolean found = false;
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblLOGO3 = new javax.swing.JLabel();
        lblUSER = new javax.swing.JLabel();
        lblPASS = new javax.swing.JLabel();
        lblLOGO1 = new javax.swing.JLabel();
        txtUSERNAME = new javax.swing.JTextField();
        lblFLAG = new javax.swing.JLabel();
        lblLOGO4 = new javax.swing.JLabel();
        txtPASSWORD = new javax.swing.JPasswordField();
        lblX = new javax.swing.JLabel();
        lblLOGO2 = new javax.swing.JLabel();
        btnLOGIN = new javax.swing.JButton();
        lblTITLE4 = new javax.swing.JLabel();
        lblUSER1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOG-IN");
        setBackground(new java.awt.Color(0, 128, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        setMinimumSize(new java.awt.Dimension(550, 450));
        setName("LOGIN"); // NOI18N
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(0, 128, 50));
        jPanel1.setPreferredSize(new java.awt.Dimension(555, 450));
        jPanel1.setLayout(null);

        lblLOGO3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblLOGO3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLOGO3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/key.png"))); // NOI18N
        lblLOGO3.setName("lblTITLE"); // NOI18N
        jPanel1.add(lblLOGO3);
        lblLOGO3.setBounds(190, 350, 60, 40);

        lblUSER.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        lblUSER.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUSER.setText("USERNAME");
        lblUSER.setName(""); // NOI18N
        jPanel1.add(lblUSER);
        lblUSER.setBounds(140, 240, 100, 41);
        lblUSER.getAccessibleContext().setAccessibleName("X");

        lblPASS.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        lblPASS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPASS.setText("********");
        lblPASS.setName(""); // NOI18N
        jPanel1.add(lblPASS);
        lblPASS.setBounds(140, 300, 80, 41);

        lblLOGO1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblLOGO1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLOGO1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/educator.png"))); // NOI18N
        lblLOGO1.setName("lblTITLE"); // NOI18N
        jPanel1.add(lblLOGO1);
        lblLOGO1.setBounds(400, 240, 40, 40);

        txtUSERNAME.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        txtUSERNAME.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtUSERNAME.setName("txtUSERNAME"); // NOI18N
        txtUSERNAME.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUSERNAMEKeyPressed(evt);
            }
        });
        jPanel1.add(txtUSERNAME);
        txtUSERNAME.setBounds(140, 240, 300, 40);
        txtUSERNAME.getAccessibleContext().setAccessibleName("");

        lblFLAG.setBackground(new java.awt.Color(51, 255, 51));
        lblFLAG.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        lblFLAG.setForeground(new java.awt.Color(255, 255, 255));
        lblFLAG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFLAG.setText("<FLAG>");
        lblFLAG.setAlignmentX(0.5F);
        lblFLAG.setName("lblFLAG"); // NOI18N
        jPanel1.add(lblFLAG);
        lblFLAG.setBounds(0, 409, 555, 40);

        lblLOGO4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblLOGO4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLOGO4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/padlock.png"))); // NOI18N
        lblLOGO4.setName("lblTITLE"); // NOI18N
        jPanel1.add(lblLOGO4);
        lblLOGO4.setBounds(400, 300, 40, 40);

        txtPASSWORD.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        txtPASSWORD.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPASSWORD.setToolTipText("");
        txtPASSWORD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPASSWORDKeyPressed(evt);
            }
        });
        jPanel1.add(txtPASSWORD);
        txtPASSWORD.setBounds(140, 300, 300, 40);

        lblX.setFont(new java.awt.Font("Segoe UI Semibold", 0, 36)); // NOI18N
        lblX.setForeground(new java.awt.Color(255, 255, 255));
        lblX.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblX.setText("X");
        lblX.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblX.setName("lblTITLE"); // NOI18N
        lblX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblXMouseReleased(evt);
            }
        });
        jPanel1.add(lblX);
        lblX.setBounds(505, 0, 50, 48);

        lblLOGO2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        lblLOGO2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLOGO2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/seal-ok.png"))); // NOI18N
        lblLOGO2.setName("lblTITLE"); // NOI18N
        jPanel1.add(lblLOGO2);
        lblLOGO2.setBounds(212, 26, 150, 120);

        btnLOGIN.setBackground(java.awt.SystemColor.activeCaptionBorder);
        btnLOGIN.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btnLOGIN.setText("LOGIN");
        btnLOGIN.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLOGIN.setName("btnLOGIN"); // NOI18N
        btnLOGIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLOGINActionPerformed(evt);
            }
        });
        jPanel1.add(btnLOGIN);
        btnLOGIN.setBounds(154, 351, 250, 40);

        lblTITLE4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 30)); // NOI18N
        lblTITLE4.setForeground(new java.awt.Color(255, 255, 255));
        lblTITLE4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTITLE4.setText("FEU TECH RESERVATION SYSTEM");
        lblTITLE4.setName(""); // NOI18N
        jPanel1.add(lblTITLE4);
        lblTITLE4.setBounds(42, 152, 457, 41);

        lblUSER1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        lblUSER1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUSER1.setText("USERNAME");
        lblUSER1.setName(""); // NOI18N
        jPanel1.add(lblUSER1);
        lblUSER1.setBounds(140, 240, 100, 41);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 550, 450);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        lblFLAG.setText("");
        lblFLAG.setOpaque(false);
        found = false;
        
    }//GEN-LAST:event_formWindowOpened

    private void lblXMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXMouseReleased
        // TODO add your handling code here:
        int Confirmation = JOptionPane.showConfirmDialog(rootPane, "DO YOU WANT TO EXIT?", "CONFIRMATION", JOptionPane.YES_NO_OPTION);
        if(Confirmation == 0)   
            System.exit(0);
    }//GEN-LAST:event_lblXMouseReleased

    private void btnLOGINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLOGINActionPerformed
        // VERIFY FORM FIELDS
        if (!txtUSERNAME.getText().isEmpty() && !txtPASSWORD.getText().isEmpty()) {
            
            accounts = daoACCOUNTS.verifyAccount(txtUSERNAME.getText().trim(), txtPASSWORD.getText());
            
            if (!accounts.isEmpty()){
                int role = accounts.get(0).getRole();
                int currID = accounts.get(0).getId();
                String currUSER = accounts.get(0).getUsername();
                verifyAccountRole(role, currID, currUSER);
            }else{
                flagError();
                lblFLAG.setText("USERNAME OR PASSWORD IS INVALID.");
                txtPASSWORD.grabFocus();
            }
        }else{
                flagError();
                lblFLAG.setText("ENTER USERNAME AND PASSWORD.");
                txtUSERNAME.grabFocus();
        }
    }//GEN-LAST:event_btnLOGINActionPerformed

    private void txtUSERNAMEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUSERNAMEKeyPressed
        // TODO add your handling code here:
        lblUSER.setVisible(false);
    }//GEN-LAST:event_txtUSERNAMEKeyPressed

    private void txtPASSWORDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPASSWORDKeyPressed
        // TODO add your handling code here:
        lblPASS.setVisible(false);
    }//GEN-LAST:event_txtPASSWORDKeyPressed

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
            java.util.logging.Logger.getLogger(LOGIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LOGIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LOGIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LOGIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LOGIN().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLOGIN;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblFLAG;
    private javax.swing.JLabel lblLOGO1;
    private javax.swing.JLabel lblLOGO2;
    private javax.swing.JLabel lblLOGO3;
    private javax.swing.JLabel lblLOGO4;
    private javax.swing.JLabel lblPASS;
    private javax.swing.JLabel lblTITLE4;
    private javax.swing.JLabel lblUSER;
    private javax.swing.JLabel lblUSER1;
    private javax.swing.JLabel lblX;
    private javax.swing.JPasswordField txtPASSWORD;
    private javax.swing.JTextField txtUSERNAME;
    // End of variables declaration//GEN-END:variables
}
