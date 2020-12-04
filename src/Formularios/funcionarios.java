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

        paneCadastrar = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
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
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaFuncionarios = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        edPesquisa = new javax.swing.JTextField();
        btPesquisar = new javax.swing.JButton();
        btDeletar = new javax.swing.JButton();
        btAtualizar = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        labelTitulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelTitulo.setText("Cadastrar Funcionario");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Nome:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("CPF:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("RG:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Salário:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Email:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Função:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Telefone:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Sexo:");

        edNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        edSalario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        edRg.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        edEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        comBoxSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Feminino"}));

        btCadastrar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE ADICIONAR PESSOA.png"))); // NOI18N
        btCadastrar.setText("Cadastrar");
        btCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrarActionPerformed(evt);
            }
        });

        btLimparCampos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btLimparCampos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE LIMPAR CAMPOS.png"))); // NOI18N
        btLimparCampos.setText("Limpar Campos");
        btLimparCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimparCamposActionPerformed(evt);
            }
        });

        labelId.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        try {
            edTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Senha:");

        edPassSenha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        edFuncao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        edFuncao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edFuncaoActionPerformed(evt);
            }
        });

        labelFuncionario1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        labelFuncionario1.setText("[FUNCIONARIO]");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel12.setText("Funcionário:");

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

        btAlterSenha.setVisible(false);
        btAlterSenha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btAlterSenha.setText("ALTERAR SENHA");
        btAlterSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btAlterSenhaMouseClicked(evt);
            }
        });

        btCancelarEdicao.setVisible(false);
        btCancelarEdicao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btCancelarEdicao.setText("Cancelar Edição");
        btCancelarEdicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarEdicaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(291, 291, 291)
                .addComponent(labelId, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(edEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(edCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(edRg, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(edNome, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(71, 71, 71)
                                            .addComponent(jLabel4))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(67, 67, 67)
                                            .addComponent(jLabel3))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(56, 56, 56)
                                            .addComponent(jLabel2)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(65, 65, 65)
                                        .addComponent(jLabel6)))
                                .addComponent(edFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(57, 57, 57)
                                    .addComponent(jLabel7)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(52, 52, 52)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(edSalario, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(44, 44, 44)
                                            .addComponent(jLabel8))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(53, 53, 53)
                                            .addComponent(jLabel9))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(51, 51, 51)
                                            .addComponent(jLabel5))
                                        .addComponent(edPassSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(59, 59, 59)
                                            .addComponent(jLabel1))
                                        .addComponent(edTelefone)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(80, 80, 80)
                                    .addComponent(comBoxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(77, 77, 77)
                                    .addComponent(btAlterSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGap(56, 56, 56)
                            .addComponent(labelTitulo))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGap(63, 63, 63)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btCancelarEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(btCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btLimparCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelFuncionario1)
                .addContainerGap(555, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(labelFuncionario1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(labelTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelId, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(edNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(edCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addGap(3, 3, 3)
                                .addComponent(edRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(edTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comBoxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(edSalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(edEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(edFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(edPassSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btAlterSenha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btLimparCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(42, 42, 42)
                .addComponent(btCancelarEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(199, Short.MAX_VALUE))
        );

        paneCadastrar.addTab("Cadastrar", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tabelaFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome", "CPF", "RG", "Sexo", "Telefone", "Email", "Salário", "Função"
            }
        ));
        jScrollPane1.setViewportView(tabelaFuncionarios);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Pesquisar:");

        edPesquisa.setToolTipText("Pesquisa nos nomes e nos cpf's");

        btPesquisar.setToolTipText("Pesquisa nos nomes e nos cpf's");
        btPesquisar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE PESQUISAR.png"))); // NOI18N
        btPesquisar.setText("PESQUISAR");
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });

        btDeletar.setToolTipText("Deleta o funcionario selecionado");
        btDeletar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE DELETAR.png"))); // NOI18N
        btDeletar.setText("Deletar");
        btDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeletarActionPerformed(evt);
            }
        });

        btAtualizar.setToolTipText("Atualiza a tabela");
        btAtualizar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE ATUALIZAR.png"))); // NOI18N
        btAtualizar.setText("Atualizar");
        btAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizarActionPerformed(evt);
            }
        });

        btEditar.setToolTipText("Edita o funcionario selecionado");
        btEditar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
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
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btPesquisar))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(btDeletar)
                        .addGap(53, 53, 53)
                        .addComponent(btAtualizar)
                        .addGap(39, 39, 39)
                        .addComponent(btEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(579, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(edPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btEditar))
                .addGap(182, 182, 182))
        );

        paneCadastrar.addTab("Visualizar, Editar e Deletar", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(paneCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 1186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(paneCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 799, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarActionPerformed
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
                paneCadastrar.setSelectedIndex(0);
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
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLabel labelFuncionario;
    private javax.swing.JLabel labelFuncionario1;
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JTabbedPane paneCadastrar;
    private javax.swing.JTable tabelaFuncionarios;
    // End of variables declaration//GEN-END:variables
}
