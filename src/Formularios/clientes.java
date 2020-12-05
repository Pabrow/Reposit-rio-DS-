/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import DAO.ClienteDAO;
import Objetos.Cliente;
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
public class clientes extends javax.swing.JPanel {
private int mode = 0;//0 = Cadastrar, 1 = Editar
private int id_edit = 0;// Id para auxiliar na edição
Mensagens m = new Mensagens();
    /**
     * Creates new form FrameCadastroCliente
     */
    public clientes() {
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
        System.out.println(edDataNasc.getText().trim().replaceAll(" ","").length());
        System.out.println(edCpf.getText().trim().replaceAll(" ","").length());
        if(((edCpf.getText().trim().replaceAll(" ","").equals("..-")))&&((edCpf.getText().trim().replaceAll(" ","").length()!=14))){
            qtd=qtd+1;
        }
        if(((edDataNasc.getText().trim().replaceAll(" ","").equals("//")))&&((edDataNasc.getText().trim().replaceAll(" ","").length()!=10))){
            qtd=qtd+1;
        }
        if(edEmail.getText().trim().replaceAll(" ","").equals("")){
            qtd=qtd+1;
        }
        if(edEndereco.getText().trim().replaceAll(" ","").equals("")){
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
        DefaultTableModel modelo = (DefaultTableModel) tabelaClientes.getModel();
        modelo.setNumRows(0);
        ClienteDAO pDAO = new ClienteDAO();
        List<Cliente> Lista = pDAO.listarTodos();
        for(Cliente p: Lista){     
            modelo.addRow(new Object[]{p.getId_cliente(),p.getNome(),p.getCpf(),p.getRg(),p.getSexo(),p.getTelefone(),p.getEmail(),p.getEndereco(),alterarData2(p.getDataNasc())});
        }
    }
    
    public void gerarTabela_com_Consulta(){
        DefaultTableModel modelo = (DefaultTableModel) tabelaClientes.getModel();
        modelo.setNumRows(0);
        ClienteDAO pDAO = new ClienteDAO();
        List<Cliente> Lista = pDAO.listarTodos();
        for(Cliente p: Lista){ 
            if(((p.getNome().toLowerCase()).contains(edPesquisa.getText().toLowerCase()))||((p.getCpf().toLowerCase()).contains(edPesquisa.getText().toLowerCase()))){
                modelo.addRow(new Object[]{p.getId_cliente(),p.getNome(),p.getCpf(),p.getRg(),p.getSexo(),p.getTelefone(),p.getEmail(),p.getEndereco(),alterarData2(p.getDataNasc())});
            }
        }
    }
    
    public void limparCampos(){
        edCpf.setText(null);
        edDataNasc.setText(null);
        edEmail.setText(null);
        edEndereco.setText(null);
        edNome.setText(null);
        edRg.setText(null);
        edTelefone.setText(null);
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
    
    public void trocarModo(Cliente p){
        if(mode == 0){
            mode = 1;
            labelTitulo.setText("Editar Cliente");
            btCadastrar.setText("Editar");
            btCancelarEdicao.setVisible(true);
            labelId.setText("ID:"+p.getId_cliente());
            //Pegando valores dos EDs
            edCpf.setText(p.getCpf());
            edDataNasc.setText(alterarData2(p.getDataNasc()));
            edEmail.setText(p.getEmail());
            edEndereco.setText(p.getEndereco());
            edNome.setText(p.getNome());
            edRg.setText(p.getRg());
            edTelefone.setText(p.getTelefone());
            if(p.getSexo().equals("Masculino")){
                comBoxSexo.setSelectedIndex(0);
            }else{
                comBoxSexo.setSelectedIndex(1);
            }
        }else{
            mode = 0;
            labelTitulo.setText("Cadastrar Cliente");
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

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        edDataNasc = new javax.swing.JFormattedTextField();
        edEmail = new javax.swing.JTextField();
        edEndereco = new javax.swing.JTextField();
        edRg = new javax.swing.JTextField();
        edCpf = new javax.swing.JFormattedTextField();
        edNome = new javax.swing.JTextField();
        edTelefone = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        comBoxSexo = new javax.swing.JComboBox<>();
        btLimparCampos = new javax.swing.JButton();
        btCadastrar = new javax.swing.JButton();
        labelFuncionario = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        labelTitulo = new javax.swing.JLabel();
        labelId = new javax.swing.JLabel();
        btCancelarEdicao = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        edPesquisa = new javax.swing.JTextField();
        btPesquisar = new javax.swing.JButton();
        btDeletar = new javax.swing.JButton();
        btAtualizar = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaClientes = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();

        setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Nome:");
        add(jLabel2);
        jLabel2.setBounds(200, 190, 60, 20);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("CPF:");
        add(jLabel3);
        jLabel3.setBounds(210, 260, 41, 22);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("RG:");
        add(jLabel4);
        jLabel4.setBounds(210, 310, 33, 22);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Endereço:");
        add(jLabel5);
        jLabel5.setBounds(180, 350, 92, 22);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Email:");
        add(jLabel6);
        jLabel6.setBounds(500, 250, 56, 22);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Data de Nascimento:");
        add(jLabel7);
        jLabel7.setBounds(450, 300, 200, 22);

        try {
            edDataNasc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        add(edDataNasc);
        edDataNasc.setBounds(490, 330, 90, 22);

        edEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(edEmail);
        edEmail.setBounds(440, 280, 200, 23);

        edEndereco.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(edEndereco);
        edEndereco.setBounds(120, 380, 222, 23);

        edRg.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(edRg);
        edRg.setBounds(120, 330, 231, 23);

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
        edCpf.setBounds(120, 280, 231, 23);

        edNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(edNome);
        edNome.setBounds(120, 210, 231, 23);

        try {
            edTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        edTelefone.setText("");
        add(edTelefone);
        edTelefone.setBounds(440, 220, 200, 24);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Telefone:");
        add(jLabel8);
        jLabel8.setBounds(490, 190, 86, 22);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Sexo:");
        add(jLabel9);
        jLabel9.setBounds(500, 360, 51, 30);

        comBoxSexo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comBoxSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Feminino"}));
        add(comBoxSexo);
        comBoxSexo.setBounds(470, 390, 128, 23);

        btLimparCampos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btLimparCampos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE LIMPAR CAMPOS.png"))); // NOI18N
        btLimparCampos.setText("Limpar Campos");
        btLimparCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimparCamposActionPerformed(evt);
            }
        });
        add(btLimparCampos);
        btLimparCampos.setBounds(440, 520, 197, 63);

        btCadastrar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE ADICIONAR PESSOA.png"))); // NOI18N
        btCadastrar.setText("Cadastrar");
        btCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrarActionPerformed(evt);
            }
        });
        add(btCadastrar);
        btCadastrar.setBounds(150, 520, 202, 63);

        labelFuncionario.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        labelFuncionario.setText("[FUNCIONARIO]");
        add(labelFuncionario);
        labelFuncionario.setBounds(1250, 10, 132, 20);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("Funcionário:");
        add(jLabel1);
        jLabel1.setBounds(1120, 10, 110, 20);

        labelTitulo.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitulo.setText("Cadastrar Cliente");
        add(labelTitulo);
        labelTitulo.setBounds(90, 110, 1242, 44);
        add(labelId);
        labelId.setBounds(700, 550, 21, 21);

        btCancelarEdicao.setVisible(false);
        btCancelarEdicao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btCancelarEdicao.setText("Cancelar Edição");
        btCancelarEdicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarEdicaoActionPerformed(evt);
            }
        });
        add(btCancelarEdicao);
        btCancelarEdicao.setBounds(280, 620, 202, 63);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Pesquisar:");
        add(jLabel10);
        jLabel10.setBounds(770, 180, 95, 22);

        edPesquisa.setToolTipText("Pesquisa nos nomes e nos cpf's");
        edPesquisa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(edPesquisa);
        edPesquisa.setBounds(880, 180, 320, 30);

        btPesquisar.setToolTipText("Pesquisa nos nomes e nos cpf's");
        btPesquisar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE PESQUISAR.png"))); // NOI18N
        btPesquisar.setText("PESQUISAR");
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });
        add(btPesquisar);
        btPesquisar.setBounds(1210, 180, 145, 30);

        btDeletar.setToolTipText("Deleta o cliente selecionado");
        btDeletar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE LIMPAR CAMPOS.png"))); // NOI18N
        btDeletar.setText("Deletar");
        btDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeletarActionPerformed(evt);
            }
        });
        add(btDeletar);
        btDeletar.setBounds(1170, 630, 109, 40);

        btAtualizar.setToolTipText("Atualiza a tabela.");
        btAtualizar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE ATUALIZAR.png"))); // NOI18N
        btAtualizar.setText("Atualizar");
        btAtualizar.setToolTipText("Atualiza a tabela");
        btAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizarActionPerformed(evt);
            }
        });
        add(btAtualizar);
        btAtualizar.setBounds(1000, 630, 150, 39);

        btAtualizar.setToolTipText("Editar o cliente selecionado.");
        btEditar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE EDITAR.png"))); // NOI18N
        btEditar.setText("Editar");
        btEditar.setToolTipText("Edita o cliente selecionado");
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });
        add(btEditar);
        btEditar.setBounds(870, 630, 109, 40);

        tabelaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome", "CPF", "RG", "Sexo", "Telefone", "Email", "Endereço", "Data de Nascimento"
            }
        ));
        jScrollPane1.setViewportView(tabelaClientes);

        add(jScrollPane1);
        jScrollPane1.setBounds(760, 220, 600, 402);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/projeto menu.png"))); // NOI18N
        add(jLabel11);
        jLabel11.setBounds(0, -90, 1390, 1000);
    }// </editor-fold>//GEN-END:initComponents

    private void edCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edCpfActionPerformed

    private void btLimparCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparCamposActionPerformed
        limparCampos();
    }//GEN-LAST:event_btLimparCamposActionPerformed

    private void btCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarActionPerformed
        if(camposPreenchidos()==true){
        if(mode == 0){
            Cliente p = new Cliente();
            ClienteDAO pDAO = new ClienteDAO();
            //Pegando valores dos EDs
            p.setCpf(edCpf.getText());
            p.setDataNasc(alterarData(edDataNasc.getText()));
            p.setEmail(edEmail.getText());
            p.setEndereco(edEndereco.getText());
            p.setNome(edNome.getText());
            p.setRg(edRg.getText());
            p.setSexo(String.valueOf(comBoxSexo.getSelectedItem()));
            p.setTelefone(edTelefone.getText());
            //Enviar para o DAO
            pDAO.cadastrarCliente(p);
            gerarTabela();
            limparCampos();
        }else{
            Cliente p = new Cliente();
            ClienteDAO pDAO = new ClienteDAO();
            //Pegando valores dos EDs
            p.setId_cliente(id_edit);
            p.setCpf(edCpf.getText());
            p.setDataNasc(alterarData(edDataNasc.getText()));
            p.setEmail(edEmail.getText());
            p.setEndereco(edEndereco.getText());
            p.setNome(edNome.getText());
            p.setRg(edRg.getText());
            p.setSexo(String.valueOf(comBoxSexo.getSelectedItem()));
            p.setTelefone(edTelefone.getText());
            //Enviar para o DAO
            pDAO.editarPorID(p);
            gerarTabela();
            limparCampos();
            trocarModo(p);
        }}
    }//GEN-LAST:event_btCadastrarActionPerformed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        gerarTabela_com_Consulta();
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void btDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeletarActionPerformed
        if(tabelaClientes.getRowSelectionAllowed()==true){
            ClienteDAO pDAO = new ClienteDAO();
            DefaultTableModel modelo = (DefaultTableModel) tabelaClientes.getModel();
            int[] linhas = tabelaClientes.getSelectedRows();
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
        if(tabelaClientes.getRowSelectionAllowed()==true){
            DefaultTableModel modelo = (DefaultTableModel) tabelaClientes.getModel();
            int[] linhas = tabelaClientes.getSelectedRows();
            if(linhas.length==1){
                int id = Integer.parseInt(modelo.getValueAt(linhas[0], 0).toString());
                ClienteDAO pDAO = new ClienteDAO();
                List<Cliente> Lista = pDAO.listarTodos();
                for(int i=0;i<Lista.size();i++){
                    Cliente p = Lista.get(i);
                    if(p.getId_cliente()==id){
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
        Cliente p = new Cliente();
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
    private javax.swing.JComboBox<String> comBoxSexo;
    private javax.swing.JFormattedTextField edCpf;
    private javax.swing.JFormattedTextField edDataNasc;
    private javax.swing.JTextField edEmail;
    private javax.swing.JTextField edEndereco;
    private javax.swing.JTextField edNome;
    private javax.swing.JTextField edPesquisa;
    private javax.swing.JTextField edRg;
    private javax.swing.JFormattedTextField edTelefone;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelFuncionario;
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JTable tabelaClientes;
    // End of variables declaration//GEN-END:variables
}
