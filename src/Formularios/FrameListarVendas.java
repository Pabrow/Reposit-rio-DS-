/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import DAO.VendaDAO;
import Objetos.Venda;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mateu
 */
public class FrameListarVendas extends javax.swing.JFrame {

    /**
     * Creates new form FrameListarVendas
     */
    public FrameListarVendas() {
        initComponents();
        preencherTabela();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btPesquisarPorData = new javax.swing.JButton();
        Imprimir = new javax.swing.JButton();
        btListarTodos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        li_vendas = new javax.swing.JTable();
        btEditar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        edDATAINCICIAL = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        edDataFinal = new javax.swing.JFormattedTextField();
        btCadastrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btPesquisarPorData.setForeground(new java.awt.Color(0, 51, 204));
        btPesquisarPorData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE PESQUISAR.png"))); // NOI18N
        btPesquisarPorData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarPorDataActionPerformed(evt);
            }
        });

        Imprimir.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        Imprimir.setText("Imprimir");

        btListarTodos.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        btListarTodos.setText("Listar todos");
        btListarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btListarTodosActionPerformed(evt);
            }
        });

        li_vendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Funcionário", "Cliente", "Data", "Hora", "Forma de Pagamento", "Valor"
            }
        ));
        jScrollPane1.setViewportView(li_vendas);

        btEditar.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        btEditar.setText("Editar");
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });

        btExcluir.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        btExcluir.setText("Excluir");
        btExcluir.setToolTipText("");
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel2.setText("A partir de:");

        edDATAINCICIAL.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("##/##/####"))));
        edDATAINCICIAL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edDATAINCICIALActionPerformed(evt);
            }
        });

        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Listar Vendas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel3.setText("Até:");

        edDataFinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat(""))));
        edDataFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edDataFinalActionPerformed(evt);
            }
        });

        btCadastrar.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        btCadastrar.setText("Cadastrar");
        btCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(edDATAINCICIAL, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btPesquisarPorData, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btListarTodos)
                .addGap(36, 36, 36))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addComponent(btCadastrar)
                            .addGap(36, 36, 36)
                            .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(34, 34, 34)
                            .addComponent(btEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(29, 29, 29)
                            .addComponent(Imprimir))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(btPesquisarPorData, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edDATAINCICIAL, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btListarTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btExcluir)
                        .addComponent(btEditar)
                        .addComponent(Imprimir))
                    .addComponent(btCadastrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btPesquisarPorDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarPorDataActionPerformed
        preencherTabela(edDataFinal.getText());
    }//GEN-LAST:event_btPesquisarPorDataActionPerformed

    private void btListarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btListarTodosActionPerformed
        preencherTabela();
    }//GEN-LAST:event_btListarTodosActionPerformed

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        int opcao = li_vendas.getSelectedRow();
        if (opcao >= 0) {
            Venda c = new Venda();
            c.setId_venda(Integer.parseInt(li_vendas.getValueAt(opcao, 0).toString()));
            c.setId_funcionario_fk(Integer.parseInt(li_vendas.getValueAt(opcao, 1).toString()));
            c.setId_cliente_fk(Integer.parseInt(li_vendas.getValueAt(opcao, 2).toString()));
            c.setData(li_vendas.getValueAt(opcao, 3).toString());
            c.setHora(li_vendas.getValueAt(opcao, 4).toString());
            c.setFormaPag(li_vendas.getValueAt(opcao, 5).toString());
            c.setValor(Integer.parseInt(li_vendas.getValueAt(opcao, 6).toString()));
            VendaDAO cdao = new VendaDAO();
            cdao.editarPorID(c);
            preencherTabela();
    }//GEN-LAST:event_btEditarActionPerformed
    }
    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        int opcao = li_vendas.getSelectedRow();
        if (opcao >= 0) {
            Venda c = new Venda();
            c.setId_venda(Integer.parseInt(li_vendas.getValueAt(opcao, 0).toString()));
            VendaDAO cdao = new VendaDAO();
            cdao.deletar(c);
            preencherTabela();
        }
    }//GEN-LAST:event_btExcluirActionPerformed

    public void gerarTabelaVendas(){
        DefaultTableModel modelo = (DefaultTableModel) li_vendas.getModel();
        modelo.setNumRows(0);
        VendaDAO pDAO = new VendaDAO();
        List<Venda> Lista = pDAO.listarTodos();
        for(Venda p: Lista){     
            modelo.addRow(new Object[]{p.getId_venda(),p.getValor(),(p.getData()),p.getHora(),p.getFormaPag(),p.getId_cliente_fk(),p.getId_funcionario_fk()});
        }
    }

    public void gerarTabelaVendas_com_Consulta(){
        DefaultTableModel modelo = (DefaultTableModel) li_vendas.getModel();
        modelo.setNumRows(0);
        VendaDAO pDAO = new VendaDAO();
        List<Venda> Lista = pDAO.listarTodos();
        for(Venda p: Lista){     
            if(((p.getData().toLowerCase()).contains(edDATAINCICIAL.getText().toLowerCase()))){
                modelo.addRow(new Object[]{p.getId_venda(), p.getValor(), p.getData(), p.getHora(),p.getFormaPag(),p.getId_cliente_fk(),p.getId_funcionario_fk()});
            }//        "Id", "Valor", "Data", "Hora", "Forma Pagamento", "Cliente", "Funcionário", "Produto", "Quantidade"
        }   
    }

    public void preencherTabela() {
        VendaDAO cdao = new VendaDAO();
        List<Venda> lista = cdao.listarTodos();
        DefaultTableModel tabela = (DefaultTableModel) li_vendas.getModel();
        tabela.setRowCount(0);
        for (Venda c : lista) {
            tabela.addRow(new Object[]{c.getId_venda(), c.getId_funcionario_fk(), c.getId_cliente_fk(), c.getData(), c.getHora(), c.getFormaPag(), c.getValor()});
        }
    }
    
    public void preencherTabela(String text) {
        VendaDAO cdao = new VendaDAO();
        List<Venda> lista = cdao.listarTodos();
        DefaultTableModel tabela = (DefaultTableModel) li_vendas.getModel();
        tabela.setRowCount(0);
        for (Venda c : lista) {
            tabela.addRow(new Object[]{c.getId_venda(), c.getId_funcionario_fk(), c.getId_cliente_fk(), c.getData(), c.getHora(), c.getFormaPag(), c.getValor()});
        }
    }
    
    private void edDATAINCICIALActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edDATAINCICIALActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edDATAINCICIALActionPerformed

    private void edDataFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edDataFinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edDataFinalActionPerformed

    private void btCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarActionPerformed
        vendaProduto cpr = new vendaProduto();
        cpr.setVisible(true);
        preencherTabela();
    }//GEN-LAST:event_btCadastrarActionPerformed

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
            java.util.logging.Logger.getLogger(FrameListarVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameListarVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameListarVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameListarVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameListarVendas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Imprimir;
    private javax.swing.JButton btCadastrar;
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btListarTodos;
    private javax.swing.JButton btPesquisarPorData;
    private javax.swing.JFormattedTextField edDATAINCICIAL;
    private javax.swing.JFormattedTextField edDataFinal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable li_vendas;
    // End of variables declaration//GEN-END:variables
}