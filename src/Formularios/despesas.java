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
    
    public boolean camposPreenchidos(){
        boolean preenchidos = false;
        int qtd = 0;
        if(edValor.getText().trim().replaceAll(" ","").equals("")){
            qtd=qtd+1;
        }
        if(edParcelas.getText().trim().replaceAll(" ","").equals("")){
            qtd=qtd+1;
        }
        if(edDescricao.getText().trim().replaceAll(" ","").equals("")){
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
        jLabel10 = new javax.swing.JLabel();
        edPesquisa = new javax.swing.JTextField();
        btPesquisar = new javax.swing.JButton();
        btDeletar = new javax.swing.JButton();
        btAtualizar = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaDespesa = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();

        setLayout(null);

        labelTitulo.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitulo.setText("Cadastrar Despesa");
        add(labelTitulo);
        labelTitulo.setBounds(-10, 130, 1300, 44);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Data:");
        add(jLabel2);
        jLabel2.setBounds(220, 240, 60, 30);

        btCadastrar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE CADASTRAR DESPESA.png"))); // NOI18N
        btCadastrar.setText("Cadastrar Despesa");
        btCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrarActionPerformed(evt);
            }
        });
        add(btCadastrar);
        btCadastrar.setBounds(110, 580, 200, 50);

        btLimparCampos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btLimparCampos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE LIMPAR CAMPOS.png"))); // NOI18N
        btLimparCampos.setText("Limpar Campos");
        btLimparCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimparCamposActionPerformed(evt);
            }
        });
        add(btLimparCampos);
        btLimparCampos.setBounds(350, 580, 180, 50);

        labelId.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        add(labelId);
        labelId.setBounds(290, 120, 43, 12);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Valor:");
        add(jLabel3);
        jLabel3.setBounds(220, 280, 70, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Decrição:");
        add(jLabel4);
        jLabel4.setBounds(260, 440, 100, 22);

        edValor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        edValor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        add(edValor);
        edValor.setBounds(280, 280, 120, 30);

        edDescricao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(edDescricao);
        edDescricao.setBounds(130, 470, 368, 70);

        labelFuncionario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelFuncionario.setText("[FUNCIONARIO]");
        add(labelFuncionario);
        labelFuncionario.setBounds(1210, 30, 88, 15);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Funcionário:");
        add(jLabel1);
        jLabel1.setBounds(1140, 30, 65, 15);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Forma Pagamento:");
        add(jLabel6);
        jLabel6.setBounds(160, 330, 180, 30);

        lbParcelas.setVisible(false);
        lbParcelas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbParcelas.setText("Parcelas:");
        add(lbParcelas);
        lbParcelas.setBounds(210, 380, 100, 30);

        edParcelas.setVisible(false);
        edParcelas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(edParcelas);
        edParcelas.setBounds(310, 380, 120, 30);

        comboBoxFormaPag.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboBoxFormaPag.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A Vista", "A Prazo" }));
        comboBoxFormaPag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxFormaPagActionPerformed(evt);
            }
        });
        add(comboBoxFormaPag);
        comboBoxFormaPag.setBounds(340, 330, 120, 30);

        edData.setEditable(false);
        edData.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(edData);
        edData.setBounds(280, 240, 120, 30);

        btCancelarEdicao.setVisible(false);
        btCancelarEdicao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btCancelarEdicao.setText("Cancelar Edição");
        btCancelarEdicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarEdicaoActionPerformed(evt);
            }
        });
        add(btCancelarEdicao);
        btCancelarEdicao.setBounds(270, 710, 129, 25);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Pesquisar:");
        add(jLabel10);
        jLabel10.setBounds(730, 260, 95, 22);
        add(edPesquisa);
        edPesquisa.setBounds(840, 260, 180, 20);

        btPesquisar.setToolTipText("Pesquisa nos nomes e nos cpf's");
        btPesquisar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE PESQUISAR.png"))); // NOI18N
        btPesquisar.setText("PESQUISAR");
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });
        add(btPesquisar);
        btPesquisar.setBounds(1040, 250, 139, 33);

        btDeletar.setToolTipText("Deleta a despesa selecionada");
        btDeletar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE DELETAR.png"))); // NOI18N
        btDeletar.setText("Deletar");
        btDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeletarActionPerformed(evt);
            }
        });
        add(btDeletar);
        btDeletar.setBounds(1040, 730, 109, 33);

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
        btAtualizar.setBounds(900, 740, 127, 39);

        btAtualizar.setToolTipText("Edita a despesa selecionada");
        btEditar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE EDITAR.png"))); // NOI18N
        btEditar.setText("Editar");
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });
        add(btEditar);
        btEditar.setBounds(750, 750, 109, 41);

        tabelaDespesa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Data", "Descrição", "Forma de Pagamento", "Valor"}
        ));
        jScrollPane1.setViewportView(tabelaDespesa);

        add(jScrollPane1);
        jScrollPane1.setBounds(730, 300, 452, 402);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/projeto menu.png"))); // NOI18N
        add(jLabel5);
        jLabel5.setBounds(0, 0, 1400, 970);
    }// </editor-fold>//GEN-END:initComponents

    private void btCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarActionPerformed
        if(camposPreenchidos()==true){
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
        }
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelFuncionario;
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel lbParcelas;
    private javax.swing.JTable tabelaDespesa;
    // End of variables declaration//GEN-END:variables
}
