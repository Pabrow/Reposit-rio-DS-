/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import DAO.FuncionarioDAO;
import Objetos.Funcionario;
import Objetos.Mensagens;
import Objetos.Usuario;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pablo
 */
public class funcionarios extends javax.swing.JPanel {

private int mode = 0;//0 = Cadastrar, 1 = Editar
private int id_edit = 0;//id para auxilar na edição
Mensagens m = new Mensagens();
    /**
     * Creates new form FrameCadastroCliente
     */
    public funcionarios() {
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
        if(((edCpf.getText().trim().replaceAll(" ","").equals("..-")))&&((edTelefone.getText().trim().replaceAll(" ","").length()!=14))){
            qtd=qtd+1;
        }
        if(edEmail.getText().trim().replaceAll(" ","").equals("")){
            qtd=qtd+1;
        }
        if(edSalario.getText().trim().replaceAll(" ","").equals("")){
            qtd=qtd+1;
        }
        if(edPassSenha.getText().trim().replaceAll(" ","").equals("")){
            qtd=qtd+1;
        }
        if(edRg.getText().trim().replaceAll(" ","").equals("")){
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
        DefaultTableModel modelo = (DefaultTableModel) tabelaFuncionarios.getModel();
        modelo.setNumRows(0);
        FuncionarioDAO pDAO = new FuncionarioDAO();
        List<Funcionario> Lista = pDAO.listarTodos();
        for(Funcionario p: Lista){  
            modelo.addRow(new Object[]{p.getId_funcionario(),p.getNome(),p.getCpf(),p.getRg(),p.getSexo(),p.getTelefone(),p.getEmail(),p.getSalario(),p.getFuncao()});
        }//"Id", "Nome", "CPF", "RG", "Sexo", "Telefone", "Email", "Salário", "Data de Nascimento", "Função"
    }
    
    public void gerarTabela_com_Consulta(){
        DefaultTableModel modelo = (DefaultTableModel) tabelaFuncionarios.getModel();
        modelo.setNumRows(0);
        FuncionarioDAO pDAO = new FuncionarioDAO();
        List<Funcionario> Lista = pDAO.listarTodos();
        for(Funcionario p: Lista){ 
            if(((p.getNome().toLowerCase()).contains(edPesquisa.getText().toLowerCase()))||((p.getCpf().toLowerCase()).contains(edPesquisa.getText().toLowerCase()))){
                modelo.addRow(new Object[]{p.getId_funcionario(),p.getNome(),p.getCpf(),p.getRg(),p.getSexo(),p.getTelefone(),p.getEmail(),p.getFuncao()});
            }
        }
    }
    
    public void limparCampos(){
        edCpf.setText(null);
        edFuncao.setText(null);
        edPassSenha.setText(null);
        edEmail.setText(null);
        edSalario.setText(null);
        edNome.setText(null);
        edRg.setText(null);
        edTelefone.setText(null);
    }
    
    public void trocarModo(Funcionario p){
        if(mode == 0){
            mode = 1;
            jLabel1.setVisible(false);
            btAlterSenha.setVisible(true);
            edPassSenha.setVisible(false);
            labelTitulo.setText("Editar Funcionario");
            btCadastrar.setText("Editar");
            btCancelarEdicao.setVisible(true);
            labelId.setText("ID:"+p.getId_funcionario());
            //Pegando valores dos EDs
            edCpf.setText(p.getCpf());
            edFuncao.setText(p.getFuncao());
            edEmail.setText(p.getEmail());
            edSalario.setText(String.valueOf(p.getSalario()));
            edNome.setText(p.getNome());
            edRg.setText(p.getRg());
            edTelefone.setText(p.getTelefone());
            btAlterSenha.setVisible(true);
            if(p.getSexo().equals("Masculino")){
                comBoxSexo.setSelectedIndex(0);
            }else{
                comBoxSexo.setSelectedIndex(1);
            }
        }else{
            mode = 0;
            labelTitulo.setText("Cadastrar Funcionario");
            btCadastrar.setText("Cadastrar");
            btCancelarEdicao.setVisible(false);
            btAlterSenha.setVisible(false);
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
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        edNome = new javax.swing.JTextField();
        edSalario = new javax.swing.JTextField();
        edRg = new javax.swing.JTextField();
        edEmail = new javax.swing.JTextField();
        comBoxSexo = new javax.swing.JComboBox<>();
        btCadastrar = new javax.swing.JButton();
        btLimparCampos = new javax.swing.JButton();
        labelId = new javax.swing.JLabel();
        edTelefone = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        edPassSenha = new javax.swing.JPasswordField();
        edFuncao = new javax.swing.JTextField();
        labelFuncionario = new javax.swing.JLabel();
        labelFuncionario1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        edCpf = new javax.swing.JFormattedTextField();
        btAlterSenha = new javax.swing.JLabel();
        btCancelarEdicao = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaFuncionarios = new javax.swing.JTable();
        edPesquisa = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btPesquisar = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();
        btAtualizar = new javax.swing.JButton();
        btDeletar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        setLayout(null);

        labelTitulo.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitulo.setText("Cadastrar Funcionario");
        add(labelTitulo);
        labelTitulo.setBounds(0, 100, 1400, 44);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Nome:");
        add(jLabel2);
        jLabel2.setBounds(220, 230, 200, 20);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("CPF:");
        add(jLabel3);
        jLabel3.setBounds(220, 300, 200, 20);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("RG:");
        add(jLabel4);
        jLabel4.setBounds(220, 370, 200, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Salário:");
        add(jLabel5);
        jLabel5.setBounds(460, 370, 200, 20);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Email:");
        add(jLabel6);
        jLabel6.setBounds(230, 440, 190, 20);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Função:");
        add(jLabel7);
        jLabel7.setBounds(220, 510, 200, 20);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Telefone:");
        add(jLabel8);
        jLabel8.setBounds(460, 230, 200, 20);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Sexo:");
        add(jLabel9);
        jLabel9.setBounds(490, 300, 150, 20);

        edNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(edNome);
        edNome.setBounds(220, 260, 200, 30);

        edSalario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(edSalario);
        edSalario.setBounds(460, 400, 210, 30);

        edRg.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(edRg);
        edRg.setBounds(220, 400, 200, 30);

        edEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(edEmail);
        edEmail.setBounds(220, 470, 200, 30);

        comBoxSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Feminino"}));
        add(comBoxSexo);
        comBoxSexo.setBounds(490, 330, 150, 30);

        btCadastrar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE ADICIONAR PESSOA.png"))); // NOI18N
        btCadastrar.setText("Cadastrar");
        btCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrarActionPerformed(evt);
            }
        });
        add(btCadastrar);
        btCadastrar.setBounds(280, 620, 124, 36);

        btLimparCampos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btLimparCampos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE LIMPAR CAMPOS.png"))); // NOI18N
        btLimparCampos.setText("Limpar Campos");
        btLimparCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimparCamposActionPerformed(evt);
            }
        });
        add(btLimparCampos);
        btLimparCampos.setBounds(460, 620, 170, 36);

        labelId.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        add(labelId);
        labelId.setBounds(380, 190, 43, 37);

        try {
            edTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        add(edTelefone);
        edTelefone.setBounds(460, 260, 210, 30);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Senha:");
        add(jLabel1);
        jLabel1.setBounds(470, 440, 200, 20);

        edPassSenha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(edPassSenha);
        edPassSenha.setBounds(470, 470, 200, 30);

        edFuncao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        edFuncao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edFuncaoActionPerformed(evt);
            }
        });
        add(edFuncao);
        edFuncao.setBounds(220, 540, 200, 30);
        add(labelFuncionario);
        labelFuncionario.setBounds(1062, 103, 47, 23);

        labelFuncionario1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        labelFuncionario1.setText("[FUNCIONARIO]");
        add(labelFuncionario1);
        labelFuncionario1.setBounds(1260, 20, 132, 20);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel12.setText("Funcionário:");
        add(jLabel12);
        jLabel12.setBounds(1150, 20, 110, 16);

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
        add(edCpf);
        edCpf.setBounds(220, 330, 200, 30);

        btAlterSenha.setVisible(false);
        btAlterSenha.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btAlterSenha.setText("ALTERAR SENHA");
        btAlterSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btAlterSenhaMouseClicked(evt);
            }
        });
        add(btAlterSenha);
        btAlterSenha.setBounds(470, 520, 200, 30);

        btCancelarEdicao.setVisible(false);
        btCancelarEdicao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btCancelarEdicao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE LIMPAR CAMPOS.png"))); // NOI18N
        btCancelarEdicao.setText("Cancelar Edição");
        btCancelarEdicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarEdicaoActionPerformed(evt);
            }
        });
        add(btCancelarEdicao);
        btCancelarEdicao.setBounds(340, 690, 200, 40);

        tabelaFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome", "CPF", "RG", "Sexo", "Telefone", "Email", "Salário", "Função"
            }
        ));
        jScrollPane1.setViewportView(tabelaFuncionarios);

        add(jScrollPane1);
        jScrollPane1.setBounds(710, 220, 566, 496);

        edPesquisa.setToolTipText("Pesquisa nos nomes e nos cpf's");
        add(edPesquisa);
        edPesquisa.setBounds(808, 190, 310, 20);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Pesquisar:");
        add(jLabel10);
        jLabel10.setBounds(710, 190, 95, 22);

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
        btPesquisar.setBounds(1120, 190, 150, 22);

        btEditar.setToolTipText("Edita o funcionario selecionado");
        btEditar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE EDITAR.png"))); // NOI18N
        btEditar.setText("Editar");
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });
        add(btEditar);
        btEditar.setBounds(1110, 730, 120, 50);

        btAtualizar.setToolTipText("Atualiza a tabela");
        btAtualizar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE ATUALIZAR.png"))); // NOI18N
        btAtualizar.setText("Atualizar");
        btAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizarActionPerformed(evt);
            }
        });
        add(btAtualizar);
        btAtualizar.setBounds(930, 730, 140, 50);

        btDeletar.setToolTipText("Deleta o funcionario selecionado");
        btDeletar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE DELETAR.png"))); // NOI18N
        btDeletar.setText("Deletar");
        btDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeletarActionPerformed(evt);
            }
        });
        add(btDeletar);
        btDeletar.setBounds(770, 730, 120, 50);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/projeto menu.png"))); // NOI18N
        jLabel11.setText("jLabel11");
        add(jLabel11);
        jLabel11.setBounds(0, 0, 1400, 1000);
    }// </editor-fold>//GEN-END:initComponents

    private void btCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarActionPerformed
        if(camposPreenchidos()==true){
        if(mode == 0){
            Funcionario p = new Funcionario();
            FuncionarioDAO pDAO = new FuncionarioDAO();
            //Pegando valores dos EDs
            p.setCpf(edCpf.getText());
            p.setEmail(edEmail.getText());
            p.setNome(edNome.getText());
            p.setRg(edRg.getText());
            p.setSexo(String.valueOf(comBoxSexo.getSelectedItem()));
            p.setTelefone(edTelefone.getText());
            p.setFuncao(edFuncao.getText());
            p.setSenha(edPassSenha.getText());
            p.setSalario(Double.parseDouble(edSalario.getText()));
            //Enviar para o DAO
            if((!p.getSenha().equals(""))&&(p.getSenha().length()>2)){
                pDAO.cadastrarFuncionario(p);
                gerarTabela();
                limparCampos();
            }else{
                JOptionPane.showMessageDialog(null, "A senha deve possuir 3 ou mais caracteres e não deve ser nula", "Importante", 1);
            }
        }else{
            Funcionario p = new Funcionario();
            FuncionarioDAO pDAO = new FuncionarioDAO();
            //Pegando valores dos EDs
            p.setId_funcionario(id_edit);
            p.setCpf(edCpf.getText());
            p.setEmail(edEmail.getText());
            p.setNome(edNome.getText());
            p.setRg(edRg.getText());
            p.setSexo(String.valueOf(comBoxSexo.getSelectedItem()));
            p.setTelefone(edTelefone.getText());
            p.setSenha(edPassSenha.getText());
            p.setSalario(Double.parseDouble(edSalario.getText()));
            p.setFuncao(edFuncao.getText());
            //Enviar para o DAO
            if((!p.getSenha().equals(""))&&(p.getSenha().length()>2)){
                pDAO.editarPorID(p);
                gerarTabela();
                limparCampos();
                trocarModo(p);
            }else{
                JOptionPane.showMessageDialog(null, "A senha deve possuir 3 ou mais caracteres e não deve ser nula", "Importante", 1);
            }
        }
        }
    }//GEN-LAST:event_btCadastrarActionPerformed

    private void btLimparCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparCamposActionPerformed
        limparCampos();
    }//GEN-LAST:event_btLimparCamposActionPerformed

    private void edFuncaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edFuncaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edFuncaoActionPerformed

    private void edCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edCpfActionPerformed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        gerarTabela_com_Consulta();
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void btDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeletarActionPerformed
        if(tabelaFuncionarios.getRowSelectionAllowed()==true){
            FuncionarioDAO pDAO = new FuncionarioDAO();
            DefaultTableModel modelo = (DefaultTableModel) tabelaFuncionarios.getModel();
            int[] linhas = tabelaFuncionarios.getSelectedRows();
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
        if(tabelaFuncionarios.getRowSelectionAllowed()==true){
            DefaultTableModel modelo = (DefaultTableModel) tabelaFuncionarios.getModel();
            int[] linhas = tabelaFuncionarios.getSelectedRows();
            if(linhas.length==1){
                int id = Integer.parseInt(modelo.getValueAt(linhas[0], 0).toString());
                FuncionarioDAO pDAO = new FuncionarioDAO();
                List<Funcionario> Lista = pDAO.listarTodos();
                for(int i=0;i<Lista.size();i++){
                    Funcionario p = Lista.get(i);
                    if(p.getId_funcionario()==id){
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

    private void btAlterSenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAlterSenhaMouseClicked
        FuncionarioDAO pDAO = new FuncionarioDAO();
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja alterar sua senha? ", "ALTERAR SENHA", JOptionPane.YES_NO_OPTION);
        if(opcao==0){
            JPasswordField password = new JPasswordField(10);
            password.setEchoChar('*');
            JLabel rotulo = new JLabel("Insira a senha antiga:");
            JPanel painel = new JPanel();
            painel.add(rotulo);
            painel.add(password);
            JOptionPane.showMessageDialog(null, painel, "ALTERAR SENHA", JOptionPane.PLAIN_MESSAGE);
            String senhaAntiga = password.getText();
            Funcionario p = pDAO.entrarFuncionario( senhaAntiga,edCpf.getText());
            if(p.getId_funcionario() > 0 ){
                password.setText(null);
                JLabel rotulo2 = new JLabel("Insira a nova senha:");
                JPanel painel2 = new JPanel();
                painel2.add(rotulo2);
                painel2.add(password);
                JOptionPane.showMessageDialog(null, painel2, "ALTERAR SENHA", JOptionPane.PLAIN_MESSAGE);
                String novaSenha = password.getText();
                if((!novaSenha.equals(""))&&(novaSenha.length()>2)){
                    pDAO.editarSenha(p, novaSenha);
                }else{
                   m.mensagemErro("Campo vazio!"); 
                }
            }else{
                m.mensagemErro("Senha ou CPF incorretos");
            }
        }
        // Cria campo onde o usuario entra com a senha

        // Cria um rótulo para o campo

        // Coloca o rótulo e a caixa de entrada numa JPanel:

        // Mostra o rótulo e a caixa de entrada de password para o usuario fornecer a senha:
    }//GEN-LAST:event_btAlterSenhaMouseClicked

    private void btCancelarEdicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarEdicaoActionPerformed
        Funcionario p = new Funcionario();
        trocarModo(p);
    }//GEN-LAST:event_btCancelarEdicaoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btAlterSenha;
    private javax.swing.JButton btAtualizar;
    private javax.swing.JButton btCadastrar;
    private javax.swing.JButton btCancelarEdicao;
    private javax.swing.JButton btDeletar;
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btLimparCampos;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JComboBox<String> comBoxSexo;
    private javax.swing.JFormattedTextField edCpf;
    private javax.swing.JTextField edEmail;
    private javax.swing.JTextField edFuncao;
    private javax.swing.JTextField edNome;
    private javax.swing.JPasswordField edPassSenha;
    private javax.swing.JTextField edPesquisa;
    private javax.swing.JTextField edRg;
    private javax.swing.JTextField edSalario;
    private javax.swing.JFormattedTextField edTelefone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelFuncionario;
    private javax.swing.JLabel labelFuncionario1;
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JTable tabelaFuncionarios;
    // End of variables declaration//GEN-END:variables
}
