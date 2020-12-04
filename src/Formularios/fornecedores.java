/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import DAO.FornecedorDAO;
import Objetos.Fornecedor;
import Objetos.Mensagens;
import Objetos.Usuario;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pablo
 */
public class fornecedores extends javax.swing.JPanel {

private int mode = 0;//0 = Cadastrar, 1 = Editar
private int id_edit = 0;// Id para auxiliar na edição
Mensagens m = new Mensagens();
    /**
     * Creates new form FrameCadastroCliente
     */
    public fornecedores() {
        initComponents();
        gerarLabel();
        gerarTabela();
    }
    
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    public boolean camposPreenchidos(){
        boolean preenchidos = false;
        int qtd = 0;
        if(((edCnpj.getText().trim().replaceAll(" ","").equals("../-")))&&((edCnpj.getText().trim().replaceAll(" ","").length()!=18))){
            qtd=qtd+1;
        }
        if(edEndereco.getText().trim().replaceAll(" ","").equals("")){
            qtd=qtd+1;
        }
        if(edEndereco.getText().trim().replaceAll(" ","").equals("")){
            qtd=qtd+1;
        }
        if(edNome.getText().trim().replaceAll(" ","").equals("")){
            qtd=qtd+1;
        }
        if((edTelefone.getText().trim().replaceAll(" ","").equals("()-"))&&((edTelefone.getText().trim().replaceAll(" ","").length()!=14))){
            qtd=qtd+1;
        }
        if(qtd!=0){
            preenchidos = false;
            if(qtd==1){
                JOptionPane.showMessageDialog(null, qtd+" campo ficou vazio!", "Importante", 1);
            }else{
                JOptionPane.showMessageDialog(null, qtd+" campos ficaram vazios!", "Importante", 1);
            }
        }else{
            preenchidos = true;
        }
        return preenchidos;
    }
    public void gerarLabel(){
        Usuario user = Usuario.getInstancia();
        labelFuncionario.setText(user.getCpf());
    }
    
    public void gerarTabela(){
        DefaultTableModel modelo = (DefaultTableModel) tabelaFornecedores.getModel();
        modelo.setNumRows(0);
        FornecedorDAO pDAO = new FornecedorDAO();
        List<Fornecedor> Lista = pDAO.listarTodos();
        for(Fornecedor p: Lista){     
            modelo.addRow(new Object[]{p.getId_fornecedor(),p.getNome(),p.getCnpj(),p.getEnd(),p.getTel()});
        }
    }
    
    public void gerarTabela_com_Consulta(){
        DefaultTableModel modelo = (DefaultTableModel) tabelaFornecedores.getModel();
        modelo.setNumRows(0);
        FornecedorDAO pDAO = new FornecedorDAO();
        List<Fornecedor> Lista = pDAO.listarTodos();
        for(Fornecedor p: Lista){ 
            if(((p.getNome().toLowerCase()).contains(edPesquisa.getText().toLowerCase()))||((p.getCnpj().toLowerCase()).contains(edPesquisa.getText().toLowerCase()))){
                modelo.addRow(new Object[]{p.getId_fornecedor(),p.getNome(),p.getCnpj(),p.getEnd(),p.getTel()});                
            }
        }
    }
    
    public void limparCampos(){
        edCnpj.setText(null);
        edTelefone.setText(null);
        edNome.setText(null);
        edEndereco.setText(null);
    }
    
    public void trocarModo(Fornecedor p){
        if(mode == 0){
            mode = 1;
            labelTitulo.setText("Editar Fornecedor");
            btCadastrar.setText("Editar");
            btCancelarEdicao.setVisible(true);
            labelId.setText("Id:"+p.getId_fornecedor());
            //Pegando valores dos EDs
            edCnpj.setText(p.getCnpj());
            edTelefone.setText(p.getEnd());
            edNome.setText(p.getNome());
            edEndereco.setText(p.getEnd());
            edTelefone.setText(p.getTel());
        }else{
            mode = 0;
            labelTitulo.setText("Cadastrar Fornecedor");
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

        labelTitulo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        edNome = new javax.swing.JTextField();
        edEndereco = new javax.swing.JTextField();
        btCadastrar = new javax.swing.JButton();
        btLimparCampos = new javax.swing.JButton();
        labelId = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        labelFuncionario = new javax.swing.JLabel();
        edTelefone = new javax.swing.JFormattedTextField();
        edCnpj = new javax.swing.JFormattedTextField();
        btCancelarEdicao = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaFornecedores = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        edPesquisa = new javax.swing.JTextField();
        btPesquisar = new javax.swing.JButton();
        btDeletar = new javax.swing.JButton();
        btAtualizar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setLayout(null);

        labelTitulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitulo.setText("Cadastrar Fornecedor");
        add(labelTitulo);
        labelTitulo.setBounds(430, 60, 449, 29);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Nome:");
        add(jLabel2);
        jLabel2.setBounds(410, 190, 41, 17);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("CNPJ:");
        add(jLabel3);
        jLabel3.setBounds(420, 240, 37, 17);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Endereço:");
        add(jLabel4);
        jLabel4.setBounds(400, 290, 63, 17);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Telefone:");
        add(jLabel5);
        jLabel5.setBounds(410, 340, 56, 17);

        edNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(edNome);
        edNome.setBounds(360, 210, 165, 23);

        edEndereco.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(edEndereco);
        edEndereco.setBounds(360, 310, 165, 23);

        btCadastrar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE CADASTRAR FORNECEDOR.png"))); // NOI18N
        btCadastrar.setText("Cadastrar");
        btCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrarActionPerformed(evt);
            }
        });
        add(btCadastrar);
        btCadastrar.setBounds(280, 420, 125, 31);

        btLimparCampos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btLimparCampos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE LIMPAR CAMPOS.png"))); // NOI18N
        btLimparCampos.setText("Limpar Campos");
        btLimparCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimparCamposActionPerformed(evt);
            }
        });
        add(btLimparCampos);
        btLimparCampos.setBounds(430, 420, 155, 31);

        labelId.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        add(labelId);
        labelId.setBounds(410, 160, 43, 30);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Funcionário:");
        add(jLabel1);
        jLabel1.setBounds(350, 10, 65, 15);

        labelFuncionario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelFuncionario.setText("[FUNCIONARIO]");
        add(labelFuncionario);
        labelFuncionario.setBounds(420, 10, 88, 15);

        try {
            edTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        add(edTelefone);
        edTelefone.setBounds(360, 370, 165, 23);

        try {
            edCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        add(edCnpj);
        edCnpj.setBounds(360, 260, 165, 20);

        btCancelarEdicao.setVisible(false);
        btCancelarEdicao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btCancelarEdicao.setText("Cancelar Edição");
        btCancelarEdicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarEdicaoActionPerformed(evt);
            }
        });
        add(btCancelarEdicao);
        btCancelarEdicao.setBounds(360, 520, 129, 25);

        btEditar.setToolTipText("Edita o fornecedor selecionado");
        btEditar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE EDITAR.png"))); // NOI18N
        btEditar.setText("Editar");
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });
        add(btEditar);
        btEditar.setBounds(740, 630, 109, 41);

        tabelaFornecedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome", "CNPJ", "Endereço", "Telefone"
            }
        ));
        jScrollPane1.setViewportView(tabelaFornecedores);

        add(jScrollPane1);
        jScrollPane1.setBounds(690, 220, 452, 402);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Pesquisar:");
        add(jLabel10);
        jLabel10.setBounds(700, 180, 62, 17);

        edPesquisa.setToolTipText("Pesquisa nos nomes e nos cnpj's");
        add(edPesquisa);
        edPesquisa.setBounds(780, 180, 220, 20);

        btPesquisar.setToolTipText("Pesquisa nos nomes e nos cnpj's");
        btPesquisar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE PESQUISAR.png"))); // NOI18N
        btPesquisar.setText("PESQUISAR");
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });
        add(btPesquisar);
        btPesquisar.setBounds(1030, 180, 139, 33);

        btDeletar.setToolTipText("Deleta o fornecedor selecionado");
        btDeletar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE DELETAR.png"))); // NOI18N
        btDeletar.setText("Deletar");
        btDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeletarActionPerformed(evt);
            }
        });
        add(btDeletar);
        btDeletar.setBounds(1020, 630, 109, 33);

        btAtualizar.setToolTipText("Atualiza a tabela");
        btAtualizar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE ATUALIZAR.png"))); // NOI18N
        btAtualizar.setText("Atualizar");
        btAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizarActionPerformed(evt);
            }
        });
        add(btAtualizar);
        btAtualizar.setBounds(870, 630, 127, 39);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/projeto menu.png"))); // NOI18N
        add(jLabel6);
        jLabel6.setBounds(0, 0, 1380, 970);
    }// </editor-fold>//GEN-END:initComponents

    private void btCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarActionPerformed
        if(camposPreenchidos()==true){
        if(mode == 0){
            Fornecedor p = new Fornecedor();
            FornecedorDAO pDAO = new FornecedorDAO();
            //Pegando valores dos EDs
            p.setCnpj(edCnpj.getText());
            p.setEnd(edEndereco.getText());
            p.setNome(edNome.getText());
            p.setTel(edTelefone.getText());
            //Enviar para o DAO
            pDAO.cadastrarFornecedor(p);
            gerarTabela();
            limparCampos();
        }else{
            Fornecedor p = new Fornecedor();
            FornecedorDAO pDAO = new FornecedorDAO();
            //Pegando valores dos EDs
            p.setId_fornecedor(id_edit);
            p.setCnpj(edCnpj.getText());
            p.setEnd(edEndereco.getText());
            p.setNome(edNome.getText());
            p.setTel(edTelefone.getText());
            //Enviar para o DAO
            pDAO.editarPorID(p);
            gerarTabela();
            limparCampos();
            trocarModo(p);
        }
        }
    }//GEN-LAST:event_btCadastrarActionPerformed

    private void btLimparCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparCamposActionPerformed
        limparCampos();
    }//GEN-LAST:event_btLimparCamposActionPerformed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        gerarTabela_com_Consulta();
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void btDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeletarActionPerformed
        if(tabelaFornecedores.getRowSelectionAllowed()==true){
            FornecedorDAO pDAO = new FornecedorDAO();
            DefaultTableModel modelo = (DefaultTableModel) tabelaFornecedores.getModel();
            int[] linhas = tabelaFornecedores.getSelectedRows();
            if(linhas.length==1){
                String idS = modelo.getValueAt(linhas[0], 0).toString();
                int id = Integer.parseInt(idS);
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
        if(tabelaFornecedores.getRowSelectionAllowed()==true){
            DefaultTableModel modelo = (DefaultTableModel) tabelaFornecedores.getModel();
            int[] linhas = tabelaFornecedores.getSelectedRows();
            if(linhas.length==1){
                int id = Integer.parseInt(modelo.getValueAt(linhas[0], 0).toString());
                FornecedorDAO pDAO = new FornecedorDAO();
                List<Fornecedor> Lista = pDAO.listarTodos();
                for(int i=0;i<Lista.size();i++){
                    Fornecedor p = Lista.get(i);
                    if(p.getId_fornecedor()==id){
                        id_edit = id;
                        trocarModo(p);
                    }
                }
            }else{
                m.mensagemPadrão1();
            }
        }else{
            m.mensagemPadrão2();
        }
    }//GEN-LAST:event_btEditarActionPerformed

    private void btCancelarEdicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarEdicaoActionPerformed
        Fornecedor p = new Fornecedor();
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
    private javax.swing.JFormattedTextField edCnpj;
    private javax.swing.JTextField edEndereco;
    private javax.swing.JTextField edNome;
    private javax.swing.JTextField edPesquisa;
    private javax.swing.JFormattedTextField edTelefone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelFuncionario;
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JTable tabelaFornecedores;
    // End of variables declaration//GEN-END:variables
}
