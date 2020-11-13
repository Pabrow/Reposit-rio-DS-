/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;

/**
 *
 * @author pablo
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
        this.setResizable(false);
        gerarPanels();
    }
    
    private void gerarPanels(){
        Home home = new Home();
        this.jPanel1.add(home);
        produtos cadastroProdutos = new produtos();
        this.jPanel2.add(cadastroProdutos);
        clientes clientes = new clientes();
        this.jPanel3.add(clientes);
        funcionarios funcionarios = new funcionarios();
        this.jPanel4.add(funcionarios);
        fornecedores fornecedores = new fornecedores();
        this.jPanel5.add(fornecedores);
        servicos servico = new servicos();
        this.jPanel6.add(servico);
        //despesas despesas = new despesas();
        //this.jPanel7.add(despesas);
        vendaProduto vendaProduto = new vendaProduto();
        this.jPanel8.add(vendaProduto);
        vendaServico vendaServico = new vendaServico();
        this.jPanel9.add(vendaServico);
        compras compra = new compras();
        this.jPanel10.add(compra);
        caixa caixa = new caixa();
        this.jPanel11.add(caixa);
        this.setSize(721, 540);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        Painel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Painel.setLayout(new java.awt.CardLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());
        Painel.add(jPanel1, "pn1");

        jPanel2.setLayout(new java.awt.BorderLayout());
        Painel.add(jPanel2, "produto");

        jPanel3.setLayout(new java.awt.BorderLayout());
        Painel.add(jPanel3, "pn3");

        jPanel4.setLayout(new java.awt.BorderLayout());
        Painel.add(jPanel4, "pn4");

        jPanel5.setPreferredSize(new java.awt.Dimension(650, 669));
        jPanel5.setLayout(new java.awt.BorderLayout());
        Painel.add(jPanel5, "pn5");

        jPanel6.setLayout(new java.awt.BorderLayout());
        Painel.add(jPanel6, "pn6");

        jPanel7.setLayout(new java.awt.BorderLayout());
        Painel.add(jPanel7, "pn7");

        jPanel8.setLayout(new java.awt.BorderLayout());
        Painel.add(jPanel8, "pn8");

        jPanel9.setLayout(new java.awt.BorderLayout());
        Painel.add(jPanel9, "pn9");

        jPanel10.setPreferredSize(new java.awt.Dimension(650, 650));
        jPanel10.setLayout(new java.awt.BorderLayout());
        Painel.add(jPanel10, "pn10");

        jPanel11.setLayout(new java.awt.BorderLayout());
        Painel.add(jPanel11, "pnonze");

        jMenuBar1.setBorder(null);

        jMenu1.setText("Home");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Cadastros");

        jMenuItem2.setText("Produtos");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);
        jMenu2.add(jSeparator1);

        jMenuItem3.setText("Clientes");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);
        jMenu2.add(jSeparator2);

        jMenuItem4.setText("Funcionários");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);
        jMenu2.add(jSeparator3);

        jMenuItem5.setText("Fornecedores");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);
        jMenu2.add(jSeparator4);

        jMenuItem6.setText("Serviços");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);
        jMenu2.add(jSeparator5);

        jMenuItem7.setText("Despesas");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Venda");

        jMenuItem8.setText("Produtos");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);
        jMenu3.add(jSeparator6);

        jMenuItem9.setText("Serviços");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem9);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Compra");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu4ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        jMenu5.setText("Caixa");
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenu5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu5ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Painel, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Painel, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(650, 669));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        jMenu1.setForeground(Color.black);
        jMenu2.setForeground(Color.black);
        jMenu3.setForeground(Color.black);
        jMenu4.setForeground(Color.black);
        jMenu5.setForeground(Color.black); 
        CardLayout cl = (CardLayout) Painel.getLayout();
        cl.show(Painel, "produto");
        this.setSize(650, 669);//[897, 599]
        this.setLocationRelativeTo(null);
        jMenu2.setForeground(Color.blue);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        jMenu1.setForeground(Color.black);
        jMenu2.setForeground(Color.black);
        jMenu3.setForeground(Color.black);
        jMenu4.setForeground(Color.black);
        jMenu5.setForeground(Color.black); 
        CardLayout cl = (CardLayout) Painel.getLayout();
        cl.show(Painel, "pn5");
        this.setSize(650,669);//[897, 599]
        this.setLocationRelativeTo(null);
        jMenu2.setForeground(Color.blue);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        jMenu1.setForeground(Color.black);
        jMenu2.setForeground(Color.black);
        jMenu3.setForeground(Color.black);
        jMenu4.setForeground(Color.black);
        jMenu5.setForeground(Color.black); 
        CardLayout cl = (CardLayout) Painel.getLayout();
        cl.show(Painel, "pn8");
        this.setSize(925, 805);//[897, 599]
        this.setLocationRelativeTo(null);
        jMenu3.setForeground(Color.blue);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed

    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        jMenu1.setForeground(Color.black);
        jMenu2.setForeground(Color.black);
        jMenu3.setForeground(Color.black);
        jMenu4.setForeground(Color.black);
        jMenu5.setForeground(Color.black); 
        CardLayout cl = (CardLayout) Painel.getLayout();
        cl.show(Painel, "pn3");
        this.setSize(650, 669);//[897, 599]
        this.setLocationRelativeTo(null);
        jMenu2.setForeground(Color.blue);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        jMenu1.setForeground(Color.black);
        jMenu2.setForeground(Color.black);
        jMenu3.setForeground(Color.black);
        jMenu4.setForeground(Color.black);
        jMenu5.setForeground(Color.black);
        CardLayout cl = (CardLayout) Painel.getLayout();
        cl.show(Painel, "pn4");
        this.setSize(650, 669);//[897, 599]
        this.setLocationRelativeTo(null);
        jMenu2.setForeground(Color.blue);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        jMenu1.setForeground(Color.black);
        jMenu2.setForeground(Color.black);
        jMenu3.setForeground(Color.black);
        jMenu4.setForeground(Color.black);
        jMenu5.setForeground(Color.black);
        CardLayout cl = (CardLayout) Painel.getLayout();
        cl.show(Painel, "pn6");
        this.setSize(650, 669);//[897, 599]
        this.setLocationRelativeTo(null);
        jMenu2.setForeground(Color.blue);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        jMenu1.setForeground(Color.black);
        jMenu2.setForeground(Color.black);
        jMenu3.setForeground(Color.black);
        jMenu4.setForeground(Color.black);
        jMenu5.setForeground(Color.black); 
        CardLayout cl = (CardLayout) Painel.getLayout();
        cl.show(Painel, "pn7");
        this.setSize(650, 669);//[897, 599]
        this.setLocationRelativeTo(null);
        jMenu2.setForeground(Color.blue);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        jMenu1.setForeground(Color.black);
        jMenu2.setForeground(Color.black);
        jMenu3.setForeground(Color.black);
        jMenu4.setForeground(Color.black);
        jMenu5.setForeground(Color.black); 
        CardLayout cl = (CardLayout) Painel.getLayout();
        cl.show(Painel, "pn9");
        this.setSize(897, 618);//[897, 599]
        this.setLocationRelativeTo(null);
        jMenu3.setForeground(Color.blue);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed
    
    }//GEN-LAST:event_jMenu4ActionPerformed

    private void jMenu5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu5ActionPerformed
    }//GEN-LAST:event_jMenu5ActionPerformed

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
        jMenu1.setForeground(Color.black);
        jMenu2.setForeground(Color.black);
        jMenu3.setForeground(Color.black);
        jMenu4.setForeground(Color.black);
        jMenu5.setForeground(Color.black); 
        CardLayout cl = (CardLayout) Painel.getLayout();
        cl.show(Painel, "pnonze");
        this.setSize(650, 669);//[897, 599]
        this.setLocationRelativeTo(null);
        jMenu5.setForeground(Color.blue);
    }//GEN-LAST:event_jMenu5MouseClicked

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
        jMenu1.setForeground(Color.black);
        jMenu2.setForeground(Color.black);
        jMenu3.setForeground(Color.black);
        jMenu4.setForeground(Color.black);
        jMenu5.setForeground(Color.black); 
        CardLayout cl = (CardLayout) Painel.getLayout();
        cl.show(Painel, "pn10");
        this.setSize(650, 669);//[897, 599]
        this.setLocationRelativeTo(null);
        jMenu4.setForeground(Color.blue);
    }//GEN-LAST:event_jMenu4MouseClicked

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        jMenu1.setForeground(Color.black);
        jMenu2.setForeground(Color.black);
        jMenu3.setForeground(Color.black);
        jMenu4.setForeground(Color.black);
        jMenu5.setForeground(Color.black); 
        CardLayout cl = (CardLayout) Painel.getLayout();
        cl.show(Painel, "pn1");
        this.setSize(721, 540);//[897, 599]
        this.setLocationRelativeTo(null);
        jMenu1.setForeground(Color.blue);
    }//GEN-LAST:event_jMenu1MouseClicked

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Painel;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    // End of variables declaration//GEN-END:variables
}