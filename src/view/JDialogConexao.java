/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Toolkit;
import static java.lang.Thread.sleep;
import javax.swing.JOptionPane;

/**
 *
 * @author PEGAZUS
 */
public class JDialogConexao extends javax.swing.JDialog {

    /**
     * Creates new form JDialogConexao
     */
    public JDialogConexao(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(500,200);
        
        //Setando icone da barra de tarefa e barra de titulo
        setIconImage(Toolkit.getDefaultToolkit().getImage(JFrameSobre.class.getResource("/img/PointsLogoBarraTarefa.png")));
        //Fim Setando icone da barra de tarefa e barra de titulo
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelLogo = new javax.swing.JLabel();
        jProgressBarConexao = new javax.swing.JProgressBar();
        jLabelInformacaoConexao = new javax.swing.JLabel();
        jButtonSair = new javax.swing.JButton();
        jButtonTestarConexao = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("TESTE CONEXÃO | Points - Sistema de Pontuação e Qualidade");

        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/PointsLogoNome.png"))); // NOI18N

        jButtonSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/sair16x16.png"))); // NOI18N
        jButtonSair.setText("SAIR");
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });

        jButtonTestarConexao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/conexao.png"))); // NOI18N
        jButtonTestarConexao.setText("TESTAR");
        jButtonTestarConexao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTestarConexaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBarConexao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelLogo)
                            .addComponent(jLabelInformacaoConexao, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 88, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonTestarConexao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSair)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelInformacaoConexao, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBarConexao, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSair)
                    .addComponent(jButtonTestarConexao))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonTestarConexaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTestarConexaoActionPerformed
        new Thread() {
            public void run() {
                for (int i = 0; i < 101; i++) {
                    try {
                        sleep(100);
                        jProgressBarConexao.setValue(i);

                        if (jProgressBarConexao.getValue() <= 25) {
                            jLabelInformacaoConexao.setText("Coletando informações...");
                        } else if (jProgressBarConexao.getValue() <= 50) {
                            jLabelInformacaoConexao.setText("Testando Conexão c/ Internet...");
                        } else if (jProgressBarConexao.getValue() <= 75) {
                            jLabelInformacaoConexao.setText("Testando Conexão c/ Banco de Dados...");
                        } else {
                            jLabelInformacaoConexao.setText("Finalizando Testes....");
                        }
                    } catch (InterruptedException ex) {
                    }
                }

                TesteConexao();
            }
        }.start();       
        
    }//GEN-LAST:event_jButtonTestarConexaoActionPerformed

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonSairActionPerformed

    public void TesteConexao() {

        try {
            java.net.URL mandarMail = new java.net.URL("http://www.guj.com.br");
            java.net.URLConnection conn = mandarMail.openConnection();
            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) conn;
            httpConn.connect();
            int x = httpConn.getResponseCode();
            if (x == 200) {
                JOptionPane.showMessageDialog(null,"Conexão Efetuada c/ Sucesso",
               "Teste Conexão",JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,"Sem Conexão",
               "Teste Conexão",JOptionPane.WARNING_MESSAGE);
            }

        } catch (java.net.MalformedURLException urlmal) {
        } catch (java.io.IOException ioexcp) {
        }

    }
    
    
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
            java.util.logging.Logger.getLogger(JDialogConexao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDialogConexao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDialogConexao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDialogConexao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDialogConexao dialog = new JDialogConexao(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSair;
    private javax.swing.JButton jButtonTestarConexao;
    private javax.swing.JLabel jLabelInformacaoConexao;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JProgressBar jProgressBarConexao;
    // End of variables declaration//GEN-END:variables
}