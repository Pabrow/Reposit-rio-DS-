/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import DAO.DespesaDAO;
import Objetos.Despesa;
import Objetos.Mensagens;
import Objetos.Usuario;
import java.awt.Graphics;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pablo
 */
public class despesas extends javax.swing.JPanel {

private int mode = 0;//0 = Cadastrar, 1 = Editar
private int id_edit = 0;//id para ajudar na edição
Mensagens m = new Mensagens();
    /**
     * Creates new form FrameCadastroCliente
     */
    public despesas(){
        initComponents();
        gerarData();
        gerarLabel();
        gerarTabela();
    }
    
    public void gerarData(){
        String data = String.valueOf(getData());
        edData.setText(alterarData2(data));
        
    }
    
    public Date getData(){
        Date data = new Date(System.currentTimeMillis());  
        return data;
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
    
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    
    public void gerarLabel(){
        Usuario user = Usuario.getInstancia();
        labelFuncionario.setText(user.getCpf());
    }
    
    public void gerarTabela(){
        DefaultTableModel modelo = (DefaultTableModel) tabelaDespesa.getModel();
        modelo.setNumRows(0);
        DespesaDAO pDAO = new DespesaDAO();
        List<Despesa> Lista = pDAO.listarTodos();
        for(Despesa p: Lista){     
            modelo.addRow(new Object[]{p.getId_despesa(),p.getData(),p.getDesc(),p.getFormaPag(),p.getValor()});
        }
    }
    
    public void gerarTabela_com_Consulta(){
        DefaultTableModel modelo = (DefaultTableModel) tabelaDespesa.getModel();
        modelo.setNumRows(0);
        DespesaDAO pDAO = new DespesaDAO();
        List<Despesa> Lista = pDAO.listarTodos();
        for(Despesa p: Lista){     
           if(((p.getDesc().toLowerCase()).contains(edPesquisa.getText().toLowerCase()))||(((String.valueOf(p.getId_despesa()))).toLowerCase()).contains(edPesquisa.getText().toLowerCase())){
                modelo.addRow(new Object[]{p.getId_despesa(),p.getData(),p.getValor(),String.valueOf(p.getFormaPag()),p.getDesc()});
            }
        }
    }
    
    public void limparCampos(){
        edValor.setText(null);
        edParcelas.setText(null);
        edDescricao.setText(null);
    }
    
    public String alterarFormato(String data){
        data.replaceAll("/", "-");
        return data;
    }
    
    public void trocarModo(Despesa p){
        if(mode == 0){
            mode = 1;
            labelTitulo.setText("Editar Despesa");
            btCadastrar.setText("Editar");
            btCancelarEdicao.setVisible(true);
            labelId.setText("ID:"+p.getId_despesa());
            //Pegando valores dos EDs
            edData.setText(alterarData2(p.getData()));
            edValor.setText(String.valueOf(p.getValor()));
            if(p.getFormaPag().equals("A Vista")){
                comboBoxFormaPag.setSelectedIndex(0);
            }else{
                comboBoxFormaPag.setSelectedIndex(1);
            }
            edDescricao.setText(p.getDesc());
        }else{
            mode = 0;
            labelTitulo.setText("Cadastrar Despesa");
            btCadastrar.setText("Cadastrar");
            btCancelarEdicao.setVisible(false);
            labelId.setText(null);
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

        paneCadastrar = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btCadastrar = new javax.swing.JButton();
        btLimparCampos = new javax.swing.JButton();
        labelId = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        edValor = new javax.swing.JTextField();
        edDescricao = new javax.swing.JTextField();
        labelFuncionario = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbParcelas = new javax.swing.JLabel();
        edParcelas = new javax.swing.JTextField();
        comboBoxFormaPag = new javax.swing.JComboBox<>();
        edData = new javax.swing.JTextField();
        btCancelarEdicao = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaDespesa = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        edPesquisa = new javax.swing.JTextField();
        btPesquisar = new javax.swing.JButton();
        btDeletar = new javax.swing.JButton();
        btAtualizar = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();

        paneCadastrar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        paneCadastrar.setName(""); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);

        labelTitulo.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitulo.setText("Cadastrar Despesa");
        jPanel2.add(labelTitulo);
        labelTitulo.setBounds(10, 70, 1300, 44);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Data:");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(560, 270, 60, 30);

        btCadastrar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE CADASTRAR DESPESA.png"))); // NOI18N
        btCadastrar.setText("Cadastrar Despesa");
        btCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrarActionPerformed(evt);
            }
        });
        jPanel2.add(btCadastrar);
        btCadastrar.setBounds(450, 610, 200, 50);

        btLimparCampos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btLimparCampos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE LIMPAR CAMPOS.png"))); // NOI18N
        btLimparCampos.setText("Limpar Campos");
        btLimparCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimparCamposActionPerformed(evt);
            }
        });
        jPanel2.add(btLimparCampos);
        btLimparCampos.setBounds(690, 610, 180, 50);

        labelId.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jPanel2.add(labelId);
        labelId.setBounds(290, 120, 43, 12);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Valor:");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(560, 310, 70, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Decrição:");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(600, 470, 100, 22);

        edValor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        edValor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(edValor);
        edValor.setBounds(620, 310, 120, 30);

        edDescricao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(edDescricao);
        edDescricao.setBounds(470, 500, 368, 70);

        labelFuncionario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelFuncionario.setText("[FUNCIONARIO]");
        jPanel2.add(labelFuncionario);
        labelFuncionario.setBounds(1210, 30, 88, 15);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Funcionário:");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(1140, 30, 65, 15);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Forma Pagamento:");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(500, 352, 180, 30);

        lbParcelas.setVisible(false);
        lbParcelas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbParcelas.setText("Parcelas:");
        jPanel2.add(lbParcelas);
        lbParcelas.setBounds(550, 410, 100, 30);

        edParcelas.setVisible(false);
        edParcelas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(edParcelas);
        edParcelas.setBounds(650, 410, 120, 30);

        comboBoxFormaPag.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboBoxFormaPag.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A Vista", "A Prazo" }));
        comboBoxFormaPag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxFormaPagActionPerformed(evt);
            }
        });
        jPanel2.add(comboBoxFormaPag);
        comboBoxFormaPag.setBounds(680, 353, 120, 30);

        edData.setEditable(false);
        edData.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(edData);
        edData.setBounds(620, 270, 120, 30);

        btCancelarEdicao.setVisible(false);
        btCancelarEdicao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btCancelarEdicao.setText("Cancelar Edição");
        btCancelarEdicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarEdicaoActionPerformed(evt);
            }
        });
        jPanel2.add(btCancelarEdicao);
        btCancelarEdicao.setBounds(610, 740, 129, 25);

        paneCadastrar.addTab("Cadastrar", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tabelaDespesa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Data", "Descrição", "Forma de Pagamento", "Valor"}
        ));
        jScrollPane1.setViewportView(tabelaDespesa);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Pesquisar:");

        btPesquisar.setToolTipText("Pesquisa nos nomes e nos cpf's");
        btPesquisar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE PESQUISAR.png"))); // NOI18N
        btPesquisar.setText("PESQUISAR");
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });

        btDeletar.setToolTipText("Deleta a despesa selecionada");
        btDeletar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE DELETAR.png"))); // NOI18N
        btDeletar.setText("Deletar");
        btDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeletarActionPerformed(evt);
            }
        });

        btAtualizar.setToolTipText("Atualiza a tabela");
        btAtualizar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE ATUALIZAR.png"))); // NOI18N
        btAtualizar.setText("Atualizar");
        btAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizarActionPerformed(evt);
            }
        });

        btAtualizar.setToolTipText("Edita a despesa selecionada");
        btEditar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE EDITAR.png"))); // NOI18N
        btEditar.setText("Editar");
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btPesquisar)
                .addGap(360, 360, 360))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(277, 277, 277)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(430, 430, 430)
                        .addComponent(btDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(btAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(btEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(284, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(edPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btAtualizar)
                    .addComponent(btEditar))
                .addContainerGap(123, Short.MAX_VALUE))
        );

        paneCadastrar.addTab("Visualizar, Editar e Deletar", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paneCadastrar))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paneCadastrar)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarActionPerformed
        Despesa p = new Despesa();
        DespesaDAO pDAO = new DespesaDAO();
        //Pegando valores dos EDs
        p.setData(alterarData(edData.getText()));
        p.setValor(Double.parseDouble(edValor.getText()));
        p.setFormaPag(String.valueOf(comboBoxFormaPag.getSelectedItem()));
        p.setDesc(edDescricao.getText());
        //Enviar para o DAO
        if(mode == 0){
            pDAO.cadastrarDespesa(p);
        }else{
            p.setId_despesa(id_edit);
            pDAO.editarPorID(p);
            trocarModo(p);
        }
        gerarTabela();
        limparCampos();
    }//GEN-LAST:event_btCadastrarActionPerformed

    private void btLimparCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparCamposActionPerformed
        limparCampos();
    }//GEN-LAST:event_btLimparCamposActionPerformed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        gerarTabela_com_Consulta();
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void btDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeletarActionPerformed
        if(tabelaDespesa.getRowSelectionAllowed()==true){
            DespesaDAO pDAO = new  DespesaDAO();
            DefaultTableModel modelo = (DefaultTableModel) tabelaDespesa.getModel();
            int[] linhas = tabelaDespesa.getSelectedRows();
            for(int i=0;i<linhas.length;i++){
                int id = Integer.parseInt(modelo.getValueAt(linhas[i], 0).toString());
                pDAO.deletar(id);
                gerarTabela();
            }
        }else{
            m.mensagemPadrão1();
        }
    }//GEN-LAST:event_btDeletarActionPerformed

    private void btAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizarActionPerformed
        gerarTabela();
    }//GEN-LAST:event_btAtualizarActionPerformed

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        if(tabelaDespesa.getRowSelectionAllowed()==true){
            DefaultTableModel modelo = (DefaultTableModel) tabelaDespesa.getModel();
            int[] linhas = tabelaDespesa.getSelectedRows();
            if(linhas.length==1){
                int id = Integer.parseInt(modelo.getValueAt(linhas[0], 0).toString());
                DespesaDAO pDAO = new DespesaDAO();
                Despesa p = new Despesa();
                List<Despesa> Lista = pDAO.listarTodos();
                for(int i=0;i<Lista.size();i++){
                    p = Lista.get(i);
                    if(p.getId_despesa()==id){
                        id_edit = id;
                        trocarModo(p);
                    }
                }
                paneCadastrar.setSelectedIndex(0);
            }else{
                JOptionPane.showMessageDialog(null, "POR FAVOR, SÓ SELECIONE UM");
            }
        }else{
            JOptionPane.showMessageDialog(null, "POR FAVOR, Selecione uma linha se deseja editar");
        }
    }//GEN-LAST:event_btEditarActionPerformed

    private void comboBoxFormaPagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxFormaPagActionPerformed
        if(comboBoxFormaPag.getSelectedIndex()!=0){
            edParcelas.setVisible(true);
            lbParcelas.setVisible(true);
        }else{
            edParcelas.setVisible(false);
            lbParcelas.setVisible(false);
        }
    }//GEN-LAST:event_comboBoxFormaPagActionPerformed

    private void btCancelarEdicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarEdicaoActionPerformed
        Despesa p = new Despesa();
        trocarModo(p);
    }//GEN-LAST:event_btCancelarEdicaoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAtualizar;
    private javax.swing.JButton btCadastrar;
    private javax.swing.JButton btCancelarEdicao;
    private javax.swing.JButton btDeletar;
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btLimparCampos;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JComboBox<String> comboBoxFormaPag;
    private javax.swing.JTextField edData;
    private javax.swing.JTextField edDescricao;
    private javax.swing.JTextField edParcelas;
    private javax.swing.JTextField edPesquisa;
    private javax.swing.JTextField edValor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelFuncionario;
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel lbParcelas;
    private javax.swing.JTabbedPane paneCadastrar;
    private javax.swing.JTable tabelaDespesa;
    // End of variables declaration//GEN-END:variables
}
