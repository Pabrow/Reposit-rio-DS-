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
    
    public void gerarLabel(){
        Usuario user = Usuario.getInstancia();
        labelFuncionario.setText(user.getCpf());
        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        jLabel7.setVisible(false);
        jLabel8.setVisible(false);
    }
    
    public void gerarTabela(){
        DefaultTableModel modelo = (DefaultTableModel) tabelaCaixas.getModel();
        modelo.setNumRows(0);
        CaixaDAO pDAO = new CaixaDAO();
        List<Caixa> Lista = pDAO.listarTodos();
        for(Caixa p: Lista){     
            modelo.addRow(new Object[]{p.getId_caixa(),alterarData2(p.getDataIn_caixa()),alterarData2(p.getDataFin_caixa()),p.getSaldoIn_caixa(),p.getTotalFin_caixa(),p.getTotalPag_caixa(),p.getTotalRec_caixa()});
        }//        "Id", "Data Inicial", "Data Final", "Saldo Inicial", "Saldo final", "Total pagamento", "Total recebimento"
    }
    
    public void gerarTabela_com_Consulta(){
        DefaultTableModel modelo = (DefaultTableModel) tabelaCaixas.getModel();
        modelo.setNumRows(0);
        CaixaDAO pDAO = new CaixaDAO();
        List<Caixa> Lista = pDAO.listarTodos();
        for(Caixa p: Lista){ 
            if(((p.getDataIn_caixa().toLowerCase()).contains(edPesquisa.getText().toLowerCase()))||((p.getDataFin_caixa().toLowerCase()).contains(edPesquisa.getText().toLowerCase()))){
                modelo.addRow(new Object[]{p.getId_caixa(),alterarData2(p.getDataIn_caixa()),alterarData2(p.getDataFin_caixa()),p.getSaldoIn_caixa(),p.getTotalFin_caixa(),p.getTotalPag_caixa(),p.getTotalRec_caixa()});
            }
        }
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
            labelId.setText("Id:"+p.getId_caixa());
            //
            jLabel2.setVisible(true);
            jLabel3.setVisible(true);
            jLabel4.setVisible(true);
            jLabel5.setVisible(true);
            jLabel6.setVisible(true);
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
        paneCadastrar = new javax.swing.JTabbedPane();
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
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        edPesquisa = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCaixas = new javax.swing.JTable();
        btDeletar = new javax.swing.JButton();
        btAtualizar = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();
        btPesquisar = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        paneCadastrar.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);

        btCadastrar.setBackground(new java.awt.Color(153, 204, 255));
        btCadastrar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE GERAR CAIXA.png"))); // NOI18N
        btCadastrar.setText("Gerar Caixa");
        btCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrarActionPerformed(evt);
            }
        });
        jPanel2.add(btCadastrar);
        btCadastrar.setBounds(200, 150, 180, 36);

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
        jPanel2.add(btLimparCampos);
        btLimparCampos.setBounds(190, 350, 200, 36);

        labelTitulo.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        labelTitulo.setText("Gerar Caixa");
        jPanel2.add(labelTitulo);
        labelTitulo.setBounds(170, 0, 270, 90);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Funcionário:");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(360, 0, 80, 17);

        labelFuncionario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelFuncionario.setText("[FUNCIONARIO]");
        jPanel2.add(labelFuncionario);
        labelFuncionario.setBounds(440, 0, 140, 17);
        jPanel2.add(labelId);
        labelId.setBounds(390, 160, 30, 20);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Importante:");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(250, 90, 100, 17);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Ao clicar no botão Gerar Caixa, o caixa");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(180, 110, 340, 17);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText(" Anterior é fechado e um novo Caixa é gerado.");
        jPanel2.add(jLabel11);
        jLabel11.setBounds(140, 130, 370, 17);

        edDataIn.setVisible(false);
        edDataIn.setEditable(false);
        edDataIn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel2.add(edDataIn);
        edDataIn.setBounds(250, 200, 130, 21);

        edDataFin.setVisible(false);
        edDataFin.setEditable(false);
        edDataFin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel2.add(edDataFin);
        edDataFin.setBounds(250, 230, 130, 21);

        edSaldoIn.setVisible(false);
        edSaldoIn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel2.add(edSaldoIn);
        edSaldoIn.setBounds(300, 280, 130, 21);

        edSaldoFin.setVisible(false);
        edSaldoFin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel2.add(edSaldoFin);
        edSaldoFin.setBounds(160, 320, 130, 21);

        edTotalPag.setVisible(false);
        edTotalPag.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel2.add(edTotalPag);
        edTotalPag.setBounds(160, 280, 130, 21);

        edTotalRec.setVisible(false);
        edTotalRec.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel2.add(edTotalRec);
        edTotalRec.setBounds(300, 320, 130, 21);

        jLabel2.setVisible(false);
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Data Final:");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(180, 230, 100, 15);

        jLabel3.setVisible(false);
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Data Inicial:");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(180, 200, 100, 15);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Saldo Inicial:");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(180, 260, 100, 15);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Total Pagamento:");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(160, 300, 130, 15);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Saldo Final:");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(320, 260, 120, 15);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Total Recebimento:");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(300, 300, 150, 15);

        paneCadastrar.addTab("Gerar", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel10.setText("Pesquisar:");

        edPesquisa.setToolTipText("Procure pela data Inicial ou data Final");
        edPesquisa.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        tabelaCaixas.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        tabelaCaixas.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tabelaCaixas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Data Inicial", "Data Final", "Saldo Inicial", "Saldo final", "Total pagamento", "Total recebimento"
            }
        ));
        tabelaCaixas.setSelectionBackground(new java.awt.Color(128, 128, 128));
        jScrollPane1.setViewportView(tabelaCaixas);

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

        btAtualizar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE ATUALIZAR.png"))); // NOI18N
        btAtualizar.setText("Atualizar");
        btAtualizar.setToolTipText("Atualiza a tabela");
        btAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizarActionPerformed(evt);
            }
        });

        btEditar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE EDITAR.png"))); // NOI18N
        btEditar.setText("Editar");
        btEditar.setToolTipText("Edita o caixa selecionado");
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btPesquisar))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(btDeletar)
                .addGap(77, 77, 77)
                .addComponent(btAtualizar)
                .addGap(70, 70, 70)
                .addComponent(btEditar))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btPesquisar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(edPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)))
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        paneCadastrar.addTab("Visualizar, Editar e Deletar", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paneCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paneCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarActionPerformed
        Caixa p = new Caixa();
        CaixaDAO pDAO = new CaixaDAO();
        Usuario user = Usuario.getInstancia();
        //Pegando valores dos EDs
        p.setDataFin_caixa(alterarData(edDataFin.getText()));
        p.setDataIn_caixa(alterarData(edDataIn.getText()));
        p.setSaldoIn_caixa(Double.parseDouble(edSaldoIn.getText()));
        p.setTotalFin_caixa(Double.parseDouble(edSaldoFin.getText()));
        p.setTotalPag_caixa(Double.parseDouble(edTotalPag.getText()));
        p.setTotalRec_caixa(Double.parseDouble(edTotalRec.getText()));
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

    private void btDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeletarActionPerformed
        if(tabelaCaixas.getRowSelectionAllowed()==true){
            CaixaDAO pDAO = new CaixaDAO();
            DefaultTableModel modelo = (DefaultTableModel) tabelaCaixas.getModel();
            int[] linhas = tabelaCaixas.getSelectedRows();
            if(linhas.length==1){
                int id = Integer.parseInt(modelo.getValueAt(linhas[0], 0).toString());
                pDAO.deletar(id);
                gerarTabela();
            }else{
                m.mensagemPadrão1();
            }
        }else{
            m.mensagemPadrão2();
        }
    }//GEN-LAST:event_btDeletarActionPerformed

    private void btAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizarActionPerformed
        gerarTabela();
    }//GEN-LAST:event_btAtualizarActionPerformed

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        if(tabelaCaixas.getRowSelectionAllowed()==true){
            DefaultTableModel modelo = (DefaultTableModel) tabelaCaixas.getModel();
            int[] linhas = tabelaCaixas.getSelectedRows();
            if(linhas.length==1){
                btLimparCampos.setVisible(true);
                int id = Integer.parseInt(modelo.getValueAt(linhas[0], 0).toString());
                CaixaDAO pDAO = new CaixaDAO();
                Caixa p = new Caixa();
                List<Caixa> Lista = pDAO.listarTodos();
                for(int i=0;i<Lista.size();i++){
                    p = Lista.get(i);
                    if(p.getId_caixa()==id){
                        id_edit = id;
                        trocarModo(p);
                    }
                }
                paneCadastrar.setSelectedIndex(0);
            }else{
                m.mensagemPadrão1();
            }
        }else{
            m.mensagemPadrão2();
        }
    }//GEN-LAST:event_btEditarActionPerformed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        gerarTabela_com_Consulta();
    }//GEN-LAST:event_btPesquisarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAtualizar;
    private javax.swing.JButton btCadastrar;
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel labelFuncionario;
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JTabbedPane paneCadastrar;
    private javax.swing.JTable tabelaCaixas;
    // End of variables declaration//GEN-END:variables
}
