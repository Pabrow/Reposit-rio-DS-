/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import DAO.CaixaDAO;
import Objetos.Caixa;
import Objetos.Mensagens;
import Objetos.Usuario;
import java.awt.Color;
import java.awt.Graphics;
import java.sql.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pablo
 */
public class caixa extends javax.swing.JPanel {

private int mode = 0;//0 = Cadastrar, 1 = Editar
private int id_edit = 0;//id para ajudar na edição
Mensagens m = new Mensagens();

    /**
     * Creates new form FrameCadastroCliente
     */
    public caixa() {
        initComponents();
        gerarLabel();
        gerarTabela();
    }
    
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
    }
        
    
    
    public Date getData(){
        Date data = new Date(System.currentTimeMillis());  
        return data;
    }
    
    public void gerarLabel(){
        Usuario user = Usuario.getInstancia();
        labelFuncionario.setText(user.getCpf());
        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        jLabel7.setVisible(false);
        jLabel8.setVisible(false);
    }
    
    public void gerarTabela(){
        DefaultTableModel modelo = (DefaultTableModel) tabelaCaixas.getModel();
        modelo.setNumRows(0);
        CaixaDAO pDAO = new CaixaDAO();
        List<Caixa> Lista = pDAO.listarTodos();
        for(Caixa p: Lista){     
            modelo.addRow(new Object[]{p.getId_caixa(),alterarData2(p.getDataIn_caixa()),retornoDataFinal(p.getDataFin_caixa()),p.getSaldoIn_caixa(),p.getTotalFin_caixa(),p.getTotalPag_caixa(),p.getTotalRec_caixa()});
        }//        "Id", "Data Inicial", "Data Final", "Saldo Inicial", "Saldo final", "Total pagamento", "Total recebimento"
    }
    
    public void gerarTabela_com_Consulta(){
        DefaultTableModel modelo = (DefaultTableModel) tabelaCaixas.getModel();
        modelo.setNumRows(0);
        CaixaDAO pDAO = new CaixaDAO();
        List<Caixa> Lista = pDAO.listarTodos();
        for(Caixa p: Lista){ 
            if(((p.getDataIn_caixa().toLowerCase()).contains(edPesquisa.getText().toLowerCase()))||((p.getDataFin_caixa().toLowerCase()).contains(edPesquisa.getText().toLowerCase()))){
                modelo.addRow(new Object[]{p.getId_caixa(),alterarData2(p.getDataIn_caixa()),retornoDataFinal(p.getDataFin_caixa()),p.getSaldoIn_caixa(),p.getTotalFin_caixa(),p.getTotalPag_caixa(),p.getTotalRec_caixa()});
            }
        }
    }
    
    public String retornoDataFinal(String data){
        String stdata = data;
        if(data.equals("1111-11-11")){
            stdata = "Caixa Atual";
        }
        return stdata;
    }
    
    public void limparCampos(){
        edSaldoIn.setText("0");
        edSaldoFin.setText("0");
        edTotalRec.setText("0");
        edTotalPag.setText("0");
    }
    
    public String alterarData(String data){
        String dia = data.substring(0, 2);
        String mes = data.substring(3, 5);
        String ano = data.substring(6, 10);
        String dataSQL = ano+"-"+mes+"-"+dia;
        return dataSQL;
    }
    
    public String alterarData2(String data){
        String dia = data.substring(8, 10);
        String mes = data.substring(5, 7);
        String ano = data.substring(0, 4);
        String dataTOP = dia+"/"+mes+"/"+ano;
        return dataTOP;
    }
    
    public void trocarModo(Caixa p){
        if(mode == 0){
            mode = 1;
            labelTitulo.setText("Editar Caixa");
            btCadastrar.setText("Editar");
            btCancelarEdicao.setVisible(true);
            labelId.setText("ID:"+p.getId_caixa());
            //
            jLabel6.setVisible(false);
            jLabel9.setVisible(false);
            jLabel11.setVisible(false);
            jLabel2.setVisible(true);
            jLabel3.setVisible(true);
            jLabel4.setVisible(true);
            jLabel5.setVisible(true);
            jLabel7.setVisible(true);
            jLabel8.setVisible(true);
            edDataFin.setVisible(true);
            edDataIn.setVisible(true);
            edSaldoIn.setVisible(true);
            edSaldoFin.setVisible(true);
            edTotalRec.setVisible(true);
            edTotalPag.setVisible(true);
            //Pegando valores dos EDs
            edDataIn.setText(alterarData2(p.getDataIn_caixa()));
            edDataFin.setText(alterarData2(p.getDataFin_caixa()));
            edSaldoIn.setText(String.valueOf(p.getSaldoIn_caixa()));
            edSaldoFin.setText(String.valueOf(p.getTotalFin_caixa()));
            edTotalRec.setText(String.valueOf(p.getTotalRec_caixa()));
            edTotalPag.setText(String.valueOf(p.getTotalPag_caixa()));
        }else{
            mode = 0;
            labelTitulo.setText("Gerar Caixa");
            btCadastrar.setText("Gerar");
            btCancelarEdicao.setVisible(false);
            jLabel6.setVisible(false);
            jLabel9.setVisible(false);
            jLabel11.setVisible(false);
            jLabel2.setVisible(false);
            jLabel3.setVisible(false);
            jLabel4.setVisible(false);
            jLabel5.setVisible(false);
            jLabel6.setVisible(false);
            jLabel7.setVisible(false);
            jLabel8.setVisible(false);
            edDataFin.setVisible(false);
            edDataIn.setVisible(false);
            edSaldoIn.setVisible(false);
            edSaldoFin.setVisible(false);
            edTotalRec.setVisible(false);
            edTotalPag.setVisible(false);
            labelId.setText(null);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btCadastrar = new javax.swing.JButton();
        btLimparCampos = new javax.swing.JButton();
        labelTitulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        labelFuncionario = new javax.swing.JLabel();
        labelId = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        edDataIn = new javax.swing.JTextField();
        edDataFin = new javax.swing.JTextField();
        edSaldoIn = new javax.swing.JTextField();
        edSaldoFin = new javax.swing.JTextField();
        edTotalPag = new javax.swing.JTextField();
        edTotalRec = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btCancelarEdicao = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaCaixas = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        edPesquisa = new javax.swing.JTextField();
        btPesquisar = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();
        btAtualizar = new javax.swing.JButton();
        btDeletar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btCadastrar.setBackground(new java.awt.Color(153, 204, 255));
        btCadastrar.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        btCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE GERAR CAIXA.png"))); // NOI18N
        btCadastrar.setText("Gerar Caixa");
        btCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrarActionPerformed(evt);
            }
        });
        jPanel2.add(btCadastrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 310, 240, 80));

        btLimparCampos.setVisible(false);
        btLimparCampos.setBackground(new java.awt.Color(153, 204, 255));
        btLimparCampos.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btLimparCampos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE LIMPAR CAMPOS.png"))); // NOI18N
        btLimparCampos.setText("Limpar Campos");
        btLimparCampos.setToolTipText("");
        btLimparCampos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btLimparCampos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btLimparCampos.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btLimparCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimparCamposActionPerformed(evt);
            }
        });
        jPanel2.add(btLimparCampos, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 690, 210, 50));

        labelTitulo.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitulo.setText("Gerar Caixa");
        jPanel2.add(labelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-60, 80, 1400, 90));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Funcionário:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 10, 80, -1));

        labelFuncionario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelFuncionario.setText("[FUNCIONARIO]");
        jPanel2.add(labelFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 10, 120, -1));

        labelId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(labelId, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 190, 30, 20));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Importante:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 230, 130, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Ao clicar no botão Gerar Caixa, o caixa");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 370, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText(" Anterior é fechado e um novo Caixa é gerado.");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, 420, -1));

        edDataIn.setVisible(false);
        edDataIn.setEditable(false);
        edDataIn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel2.add(edDataIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, 130, -1));

        edDataFin.setVisible(false);
        edDataFin.setEditable(false);
        edDataFin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel2.add(edDataFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 420, 130, -1));

        edSaldoIn.setVisible(false);
        edSaldoIn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel2.add(edSaldoIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 560, 130, 30));

        edSaldoFin.setVisible(false);
        edSaldoFin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel2.add(edSaldoFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 640, 140, 30));

        edTotalPag.setVisible(false);
        edTotalPag.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel2.add(edTotalPag, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 560, 140, 30));

        edTotalRec.setVisible(false);
        edTotalRec.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel2.add(edTotalRec, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 640, 130, 30));

        jLabel2.setVisible(false);
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Data Final:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 420, 100, -1));

        jLabel3.setVisible(false);
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Data Inicial:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, 120, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Saldo Inicial:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 530, 120, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Total Pagamento:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 610, 170, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Saldo Final:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 530, 120, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Total Recebimento:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 610, 190, 20));

        btCancelarEdicao.setVisible(false);
        btCancelarEdicao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btCancelarEdicao.setText("Cancelar Edição");
        btCancelarEdicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarEdicaoActionPerformed(evt);
            }
        });
        jPanel2.add(btCancelarEdicao, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 780, -1, -1));

        tabelaCaixas.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tabelaCaixas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Data Inicial", "Data Final", "Saldo Inicial", "Saldo final", "Total pagamento", "Total recebimento"
            }
        ));
        tabelaCaixas.setSelectionBackground(new java.awt.Color(128, 128, 128));
        jScrollPane2.setViewportView(tabelaCaixas);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 290, 590, 390));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("Pesquisar:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 250, 100, -1));

        edPesquisa.setToolTipText("Procure pela data Inicial ou data Final");
        edPesquisa.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jPanel2.add(edPesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 250, 334, 30));

        btPesquisar.setToolTipText("Procure pela data Inicial ou data Final");
        btPesquisar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE PESQUISAR.png"))); // NOI18N
        btPesquisar.setText("PESQUISAR");
        btPesquisar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });
        jPanel2.add(btPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 250, -1, 30));

        btEditar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE EDITAR.png"))); // NOI18N
        btEditar.setText("Editar");
        btEditar.setToolTipText("Edita o caixa selecionado");
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });
        jPanel2.add(btEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 710, 130, 60));

        btAtualizar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE ATUALIZAR.png"))); // NOI18N
        btAtualizar.setText("Atualizar");
        btAtualizar.setToolTipText("Atualiza a tabela");
        btAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizarActionPerformed(evt);
            }
        });
        jPanel2.add(btAtualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 710, 150, 60));

        btDeletar.setToolTipText("Deleta o caixa selecionado.");
        btDeletar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE LIMPAR CAMPOS.png"))); // NOI18N
        btDeletar.setText("Deletar");
        btDeletar.setToolTipText("Deleta o caixa selecionado");
        btDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeletarActionPerformed(evt);
            }
        });
        jPanel2.add(btDeletar, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 710, 140, 60));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/projeto menu.png"))); // NOI18N
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1400, 970));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 2, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 966, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarActionPerformed
        Caixa p = new Caixa();
        CaixaDAO pDAO = new CaixaDAO();
        Usuario user = Usuario.getInstancia();
        //Pegando valores dos EDs
        p.setDataIn_caixa(String.valueOf(getData()));
        p.setId_funcionario_fk(user.getId());
        //Enviar para o DAO
        if(mode == 0){
            pDAO.cadastrarCaixa(p);
            gerarTabela();
        }else{
            p.setId_caixa(id_edit);
            pDAO.editarPorID(p);
            gerarTabela();
            trocarModo(p);
        }
        limparCampos();
    }//GEN-LAST:event_btCadastrarActionPerformed

    private void btLimparCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparCamposActionPerformed
        limparCampos();
    }//GEN-LAST:event_btLimparCamposActionPerformed

    private void btCancelarEdicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarEdicaoActionPerformed
        Caixa p = new Caixa();
        trocarModo(p);
    }//GEN-LAST:event_btCancelarEdicaoActionPerformed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btEditarActionPerformed

    private void btAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btAtualizarActionPerformed

    private void btDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeletarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btDeletarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAtualizar;
    private javax.swing.JButton btCadastrar;
    private javax.swing.JButton btCancelarEdicao;
    private javax.swing.JButton btDeletar;
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btLimparCampos;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JTextField edDataFin;
    private javax.swing.JTextField edDataIn;
    private javax.swing.JTextField edPesquisa;
    private javax.swing.JTextField edSaldoFin;
    private javax.swing.JTextField edSaldoIn;
    private javax.swing.JTextField edTotalPag;
    private javax.swing.JTextField edTotalRec;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel labelFuncionario;
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JTable tabelaCaixas;
    // End of variables declaration//GEN-END:variables
}
