import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.Future;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nikhil4patilSuckz
 * :(    -nikhil
 * >:)   -wesley
 */
public class ScoringBotWindow extends javax.swing.JFrame implements Window {
    Future<?> scoringBot;
    /**
     * Creates new form scoringBotWindow
     */
    
    public ScoringBotWindow() {
        initComponents();
        vulnerabilityNumberDisplay_jLabel.setText(vulnerabilityNumberDisplay_jLabel.getText().split("/")[0] + " / Unspecified Number");
        pointsNumberDisplay_jLabel.setText(pointsNumberDisplay_jLabel.getText().split("/")[0] + " / Unspecified Number");
    }
    
    public ScoringBotWindow(int totalVulns, int totalPoints) {
        initComponents();
        vulnerabilityNumberDisplay_jLabel.setText(vulnerabilityNumberDisplay_jLabel.getText().split("/")[0] + " / " + totalVulns + ((totalVulns == 1) ? " Vulnerability" : " Vulnerabilities"));
        pointsNumberDisplay_jLabel.setText(pointsNumberDisplay_jLabel.getText().split("/")[0] + " / " + totalPoints + ((totalPoints == 1) ? " Point" : " Points"));
    }
    
    protected void setFuture(Future<?> f) {
        scoringBot = f;
    }
    
    public void vulnSolved(Vulnerability vuln) {
        modifyTable(vuln, false, vuln.isPenalty());
    }
    
    public void vulnUnSolved(Vulnerability vuln) {
        modifyTable(vuln, true, vuln.isPenalty());
    }
    
    protected synchronized void modifyTable(Vulnerability mod, boolean rm, boolean isPenalty) {
        JTable table = ((isPenalty) ? penalties_jTable : points_jTable);
        if (rm) {
            for (int j = 0; j < table.getRowCount(); j++) {
                if (table.getValueAt(j, 0).equals(mod.getName())) {
                    ((DefaultTableModel)table.getModel()).removeRow(j);
                    ((isPenalty) ? penalties_jLabel : points_jLabel).setText(((isPenalty) ? "-" : "+") + (Math.abs(Integer.parseInt(((isPenalty) ? penalties_jLabel : points_jLabel).getText())) - mod.pointsWorth()));
                    pointsNumberDisplay_jLabel.setText((Integer.parseInt(points_jLabel.getText()) + Integer.parseInt(penalties_jLabel.getText())) + " /" + pointsNumberDisplay_jLabel.getText().split("/")[1]);
                    vulnerabilityNumberDisplay_jLabel.setText(points_jTable.getRowCount() + " /" + vulnerabilityNumberDisplay_jLabel.getText().split("/")[1]);
                }
            }
        }
        else {
            ((DefaultTableModel)table.getModel()).addRow(new Object[]{mod.getName(), mod.pointsWorth(), LocalDateTime.now().toString().split("T")[1]});
            ((isPenalty) ? penalties_jLabel : points_jLabel).setText(((isPenalty) ? "-" : "+") + (Math.abs(Integer.parseInt(((isPenalty) ? penalties_jLabel : points_jLabel).getText())) + mod.pointsWorth()));
            pointsNumberDisplay_jLabel.setText((Integer.parseInt(points_jLabel.getText()) + Integer.parseInt(penalties_jLabel.getText())) + " /" + pointsNumberDisplay_jLabel.getText().split("/")[1]);
            vulnerabilityNumberDisplay_jLabel.setText(points_jTable.getRowCount() + " /" + vulnerabilityNumberDisplay_jLabel.getText().split("/")[1]);
        }
        vulnerabilityNumberDisplay_jLabel.setForeground((penalties_jTable.getRowCount() > 0) ? penalties_jLabel.getForeground() : points_jLabel.getForeground());
        pointsNumberDisplay_jLabel.setForeground((penalties_jTable.getRowCount() > 0) ? penalties_jLabel.getForeground() : points_jLabel.getForeground());
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        vulnerabilities_jScrollPane = new javax.swing.JScrollPane();
        points_jTable = new javax.swing.JTable();
        pointsNumberDisplay_jLabel = new javax.swing.JLabel();
        vulnerabilities_jScrollPane1 = new javax.swing.JScrollPane();
        penalties_jTable = new javax.swing.JTable();
        penaltiesTableLabel = new javax.swing.JLabel();
        pointsTableLabel = new javax.swing.JLabel();
        points_jLabel = new javax.swing.JLabel();
        penalties_jLabel = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        vulnerabilityNumberDisplay_jLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(526, 408));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0};
        layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        getContentPane().setLayout(layout);

        points_jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vulnerability", "Points", "Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        points_jTable.setColumnSelectionAllowed(true);
        vulnerabilities_jScrollPane.setViewportView(points_jTable);
        points_jTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(vulnerabilities_jScrollPane, gridBagConstraints);

        pointsNumberDisplay_jLabel.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        pointsNumberDisplay_jLabel.setForeground(new java.awt.Color(0, 204, 0));
        pointsNumberDisplay_jLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pointsNumberDisplay_jLabel.setText("0 / 0 Points");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        getContentPane().add(pointsNumberDisplay_jLabel, gridBagConstraints);

        penalties_jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vulnerability", "Points", "Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        penalties_jTable.setColumnSelectionAllowed(true);
        vulnerabilities_jScrollPane1.setViewportView(penalties_jTable);
        penalties_jTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(vulnerabilities_jScrollPane1, gridBagConstraints);

        penaltiesTableLabel.setText("Penalties");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(penaltiesTableLabel, gridBagConstraints);

        pointsTableLabel.setText("Points");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(pointsTableLabel, gridBagConstraints);

        points_jLabel.setForeground(new java.awt.Color(0, 204, 0));
        points_jLabel.setText("+0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(points_jLabel, gridBagConstraints);

        penalties_jLabel.setForeground(new java.awt.Color(204, 0, 0));
        penalties_jLabel.setText("-0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(penalties_jLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(filler1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 19;
        gridBagConstraints.ipadx = 10;
        getContentPane().add(filler3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 19;
        gridBagConstraints.ipadx = 10;
        getContentPane().add(filler2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(filler4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(filler5, gridBagConstraints);

        vulnerabilityNumberDisplay_jLabel.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        vulnerabilityNumberDisplay_jLabel.setForeground(new java.awt.Color(0, 204, 0));
        vulnerabilityNumberDisplay_jLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vulnerabilityNumberDisplay_jLabel.setText("0 / 0 Vulnerabilities");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(vulnerabilityNumberDisplay_jLabel, gridBagConstraints);

        jMenu1.setText("Help");

        jMenuItem2.setText("TODO");
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setVisible(false);
        this.stopScoringBot(scoringBot);
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        //This is a test line
        //sdkjkjdfsjkldsfjlkfsdjklsdfkjljfdjljkldslfkj
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
            java.util.logging.Logger.getLogger(ScoringBotWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ScoringBotWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ScoringBotWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ScoringBotWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ScoringBotWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JLabel penaltiesTableLabel;
    private javax.swing.JLabel penalties_jLabel;
    private javax.swing.JTable penalties_jTable;
    private javax.swing.JLabel pointsNumberDisplay_jLabel;
    private javax.swing.JLabel pointsTableLabel;
    private javax.swing.JLabel points_jLabel;
    private javax.swing.JTable points_jTable;
    private javax.swing.JScrollPane vulnerabilities_jScrollPane;
    private javax.swing.JScrollPane vulnerabilities_jScrollPane1;
    private javax.swing.JLabel vulnerabilityNumberDisplay_jLabel;
    // End of variables declaration//GEN-END:variables
}
