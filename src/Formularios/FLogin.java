/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Formularios.*;
import DAO.FuncionarioDAO;
import Objetos.Funcionario;
import Objetos.Mensagens;
import Objetos.Usuario;
import static java.lang.System.exit;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author pablo
 */
public class FLogin extends javax.swing.JFrame {
private int mode = 0;
    /**
     * Creates new form FLogin
     */
    public FLogin() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        gerarPrimeiroAcesso();
    }
    
    private void gerarPrimeiroAcesso(){
        FuncionarioDAO f = new FuncionarioDAO();
        if(f.primeiroAcesso()>0){
            btPrimeiroAcesso.setVisible(false);
            btEsqSenha.setVisible(true);
        }else{
            btPrimeiroAcesso.setVisible(true);
            btEsqSenha.setVisible(false);
        }
    }
    
    public void jaCadastrado(){
        btPrimeiroAcesso.setVisible(false);
        btEsqSenha.setVisible(true);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btEsqSenha = new javax.swing.JButton();
        btPrimeiroAcesso = new javax.swing.JButton();
        btLogin = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        edPassSenha = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        edCpf = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(244, 244, 244));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jPanel1.setLayout(null);

        btEsqSenha.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btEsqSenha.setForeground(new java.awt.Color(0, 0, 204));
        btEsqSenha.setText("Esqueci a senha");
        btEsqSenha.setBorder(null);
        btEsqSenha.setBorderPainted(false);
        btEsqSenha.setContentAreaFilled(false);
        btEsqSenha.setFocusPainted(false);
        btEsqSenha.setFocusable(false);
        btEsqSenha.setRequestFocusEnabled(false);
        btEsqSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btEsqSenhaMouseClicked(evt);
            }
        });
        btEsqSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEsqSenhaActionPerformed(evt);
            }
        });
        jPanel1.add(btEsqSenha);
        btEsqSenha.setBounds(310, 350, 100, 30);

        btPrimeiroAcesso.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btPrimeiroAcesso.setForeground(new java.awt.Color(0, 0, 204));
        btPrimeiroAcesso.setText("Primeiro Acesso");
        btPrimeiroAcesso.setBorder(null);
        btPrimeiroAcesso.setBorderPainted(false);
        btPrimeiroAcesso.setContentAreaFilled(false);
        btPrimeiroAcesso.setFocusPainted(false);
        btPrimeiroAcesso.setFocusable(false);
        btPrimeiroAcesso.setRequestFocusEnabled(false);
        btPrimeiroAcesso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPrimeiroAcessoActionPerformed(evt);
            }
        });
        jPanel1.add(btPrimeiroAcesso);
        btPrimeiroAcesso.setBounds(160, 350, 100, 30);

        btLogin.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btLogin.setText("Entrar");
        btLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLoginActionPerformed(evt);
            }
        });
        jPanel1.add(btLogin);
        btLogin.setBounds(230, 400, 132, 31);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Icone Olho 4.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel6);
        jLabel6.setBounds(370, 320, 30, 30);

        edPassSenha.setEchoChar('\u2022');
        jPanel1.add(edPassSenha);
        edPassSenha.setBounds(160, 320, 250, 30);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Insira sua Senha:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(220, 300, 133, 22);

        try {
            edCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        edCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edCpfActionPerformed(evt);
            }
        });
        jPanel1.add(edCpf);
        edCpf.setBounds(160, 260, 250, 30);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Insira seu CPF:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(230, 240, 119, 22);

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Acessar Login");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(jLabel1)
                .addContainerGap(220, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(3, 3, 544, 52);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/sherek motos3.png"))); // NOI18N
        jPanel1.add(jLabel5);
        jLabel5.setBounds(160, 60, 250, 163);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 550, 450);

        setSize(new java.awt.Dimension(556, 479));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void edCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edCpfActionPerformed
       
    }//GEN-LAST:event_edCpfActionPerformed

    private void btEsqSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEsqSenhaActionPerformed
         FuncionarioDAO fd = new FuncionarioDAO();
        Funcionario d = new Funcionario();
        Mensagens m = new Mensagens();
        if(JOptionPane.showConfirmDialog(null, "Deseja Continuar?", "Esqueceu a Senha", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0){
            JFormattedTextField cpf = null;
            try {
                cpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
            } catch (ParseException ex) {
                Logger.getLogger(FLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
            JLabel rotuloC = new JLabel("Insira o CPF:");
            JPanel painelC = new JPanel();
            painelC.add(rotuloC);
            painelC.add(cpf);
            JOptionPane.showMessageDialog(null, painelC, "Esqueceu a Senha", JOptionPane.PLAIN_MESSAGE);
            String Scpf = cpf.getText();
            int id = fd.esqueciSenha(Scpf, 0);
            System.out.println(id);
            if(id!=0){
                JLabel rotuloR = new JLabel("Insira o RG:");
                JPanel painelR = new JPanel();
                JTextField rg = new JTextField(10);
                painelR.add(rotuloR);
                painelR.add(rg);
                JOptionPane.showMessageDialog(null, painelR, "Esqueceu a Senha", JOptionPane.PLAIN_MESSAGE);
                String Srg = rg.getText();
                int id2 = fd.esqueciSenha(Srg, id);
                System.out.println(id2);
                if(id2!=0){
                    JPasswordField password = new JPasswordField(10);
                    password.setEchoChar('*');
                    password.setText(null);
                    JLabel rotulo2 = new JLabel("Insira a nova senha:");
                    JPanel painel2 = new JPanel();
                    painel2.add(rotulo2);
                    painel2.add(password);
                    JOptionPane.showMessageDialog(null, painel2, "ALTERAR SENHA", JOptionPane.PLAIN_MESSAGE);
                    String novaSenha = password.getText();
                    d.setId_funcionario(id);
                    if((!novaSenha.equals(""))&&(novaSenha.length()>2)){
                        fd.editarSenha(d, novaSenha);
                    }else{
                        m.mensagemErro("Campo vazio!"); 
                    }
                }else{
                    m.mensagemErro("RG Incorreto"); 
                }
            }else{
                m.mensagemErro("CPF Incorreto"); 
            }
        }
    }//GEN-LAST:event_btEsqSenhaActionPerformed

    private void btLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLoginActionPerformed
        if((!edCpf.getText().equals(""))&&(!edPassSenha.getText().equals(""))){
            FuncionarioDAO pDAO = new FuncionarioDAO();
            Funcionario p = pDAO.entrarFuncionario( edPassSenha.getText(),edCpf.getText());
            if(p.getId_funcionario() > 0 ){
                Usuario user = Usuario.getInstancia();
                user.setCpf(p.getCpf());
                user.setNome(p.getNome());
                user.setSenha(p.getSenha());
                user.setId(p.getId_funcionario());
                TelaPrincipal Menu = new TelaPrincipal();
                Menu.setVisible(true);
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(null,"Usuário ou senha incorretos.", "Acessar Login", ERROR_MESSAGE);
            }
        }else{
                JOptionPane.showMessageDialog(null,"Digite o Usuário e a Senha.", "Acessar Login", ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btLoginActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        if(mode==0){
            jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Icone Olho Fechado 3.png")));
            edPassSenha.setEchoChar('\u0000');
            mode = 1;
        }else{
            jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Icone Olho 4.png")));
            mode = 0;
            edPassSenha.setEchoChar('\u2022');
        }
    }//GEN-LAST:event_jLabel6MouseClicked

    private void btPrimeiroAcessoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPrimeiroAcessoActionPerformed
        funcionarioPrimeiroAcesso fcf = new funcionarioPrimeiroAcesso();
        fcf.setVisible(rootPaneCheckingEnabled);
    }//GEN-LAST:event_btPrimeiroAcessoActionPerformed

    private void btEsqSenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btEsqSenhaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btEsqSenhaMouseClicked

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
            java.util.logging.Logger.getLogger(FLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEsqSenha;
    private javax.swing.JButton btLogin;
    private javax.swing.JButton btPrimeiroAcesso;
    private javax.swing.JFormattedTextField edCpf;
    private javax.swing.JPasswordField edPassSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
