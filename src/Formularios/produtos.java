/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import DAO.MarcaDAO;
import DAO.ProdutoDAO;
import DAO.TipoDAO;
import Objetos.Marca;
import Objetos.Mensagens;
import Objetos.Produto;
import Objetos.Tipo;
import Objetos.Usuario;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pablo
 */
public class produtos extends javax.swing.JPanel {
private int mode = 0;//0 = Cadastrar, 1 = Editar
private int id_edit = 0;//id para ajudar na edição
Mensagens m = new Mensagens();
    /**
     * Creates new form cadastroProdutos
     */
    public produtos() {
        initComponents();
        gerarTabela();
        gerarComboBox();
    }
    
@Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
    }
        
    public void gerarComboBox(){
        comboBox.removeAllItems();
        comboBoxMarca.removeAllItems();
        TipoDAO td = new TipoDAO();
        List<Tipo> Lista = td.listarTodos();
        for(Tipo p: Lista){     
            comboBox.addItem(p.getNome());
        }
        MarcaDAO md = new MarcaDAO();
        List<Marca> Lista2 = md.listarTodos();
        for(Marca f: Lista2){     
            comboBoxMarca.addItem(f.getNome());
        }
    }
    
    public boolean camposPreenchidos(){
        boolean preenchidos = false;
        int qtd = 0;
        if(edDesc.getText().trim().replaceAll(" ","").equals("")){
            qtd=qtd+1;
        }
        if(edQuant.getText().trim().replaceAll(" ","").equals("")){
            qtd=qtd+1;
        }
        if(edTamanho.getText().trim().replaceAll(" ","").equals("")){
            qtd=qtd+1;
        }
        if(edValor.getText().trim().replaceAll(" ","").equals("")){
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
    
    public void gerarTabela(){
        DefaultTableModel modelo = (DefaultTableModel) tabelaProdutos.getModel();
        modelo.setNumRows(0);
        ProdutoDAO pDAO = new ProdutoDAO();
        List<Produto> Lista = pDAO.listarTodos();
        for(Produto p: Lista){     
            modelo.addRow(new Object[]{p.getId_produto(),p.getDesc(),p.getMarca(),p.getTamanho(),String.valueOf(p.getQuantidade()),p.getValor(),p.getTipo()});
        }
    }
    
    public void gerarTabela_com_Consulta(){
        DefaultTableModel modelo = (DefaultTableModel) tabelaProdutos.getModel();
        modelo.setNumRows(0);
        ProdutoDAO pDAO = new ProdutoDAO();
        List<Produto> Lista = pDAO.listarTodos();
        for(Produto p: Lista){     
            if(((p.getDesc().toLowerCase()).contains(edPesquisa.getText().toLowerCase()))||(((String.valueOf(p.getTipo())).toLowerCase()).contains(edPesquisa.getText().toLowerCase()))){
                modelo.addRow(new Object[]{p.getId_produto(),p.getDesc(),p.getMarca(),p.getTamanho(),String.valueOf(p.getQuantidade()),p.getValor(),p.getTipo()});
            }
        }
    }
    
    public void limparCampos(){
        edValor.setText(null);
        edQuant.setText(null);
        edDesc.setText(null);
        edTamanho.setText(null);
    }
    
    public String alterarFormato(String data){
        data.replaceAll("/", "-");
        return data;
    }
    
    public void trocarModo(Produto p){
        if(mode == 0){
            mode = 1;
            labelTitulo.setText("Editar Produto");
            btCadastrar.setText("Editar");
            btCancelarEdicao.setVisible(true);
            labelId.setText("Id:"+p.getId_produto());
            //Pegando valores dos EDs
            int qtd2 = comboBoxMarca.getItemCount();
            for(int i=1;i<=qtd2;i++){
                if(p.getMarca().equals(comboBoxMarca.getItemAt(i))){
                    comboBox.setSelectedIndex(i);
                }
            }
            edValor.setText(String.valueOf(p.getValor()));
            edQuant.setText(String.valueOf(p.getQuantidade()));
            edDesc.setText(p.getDesc());
            int qtd = comboBox.getItemCount();
            for(int i=1;i<=qtd;i++){
                if(p.getTipo().equals(comboBox.getItemAt(i))){
                    comboBox.setSelectedIndex(i);
                }
            }
            edTamanho.setText(p.getTamanho());
        }else{
            mode = 0;
            labelTitulo.setText("Cadastrar Produto");
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
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        edDesc = new javax.swing.JTextField();
        edQuant = new javax.swing.JTextField();
        edTamanho = new javax.swing.JTextField();
        edValor = new javax.swing.JTextField();
        btCadastrar = new javax.swing.JButton();
        btLimparCampos = new javax.swing.JButton();
        labelId = new javax.swing.JLabel();
        btCancelarEdicao = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaProdutos = new javax.swing.JTable();
        edPesquisa = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btPesquisar = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();
        btAtualizar = new javax.swing.JButton();
        btDeletar = new javax.swing.JButton();
        comboBox = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        comboBoxMarca = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelTitulo.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitulo.setText("Cadastrar Produtos");
        add(labelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-80, 80, 1370, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Descrição:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 230, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Marca:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, 230, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Tamanho:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, 230, 20));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Quantidade:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 390, 220, 20));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Valor:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 460, 230, 20));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Tipo:");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 530, 230, 20));

        edDesc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(edDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 230, 30));

        edQuant.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(edQuant, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 420, 230, 30));

        edTamanho.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(edTamanho, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 350, 230, 30));

        edValor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(edValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 490, 230, 30));

        btCadastrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE CADASTRAR PRODUTOS.png"))); // NOI18N
        btCadastrar.setText("Cadastrar");
        btCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrarActionPerformed(evt);
            }
        });
        add(btCadastrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 610, 200, 40));

        btLimparCampos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btLimparCampos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE LIMPAR CAMPOS.png"))); // NOI18N
        btLimparCampos.setText("Limpar Campos");
        btLimparCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimparCamposActionPerformed(evt);
            }
        });
        add(btLimparCampos, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 610, 220, 40));

        labelId.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        add(labelId, new org.netbeans.lib.awtextra.AbsoluteConstraints(1187, 39, 43, 13));

        btCancelarEdicao.setVisible(false);
        btCancelarEdicao.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCancelarEdicao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE LIMPAR CAMPOS.png"))); // NOI18N
        btCancelarEdicao.setText("Cancelar Edição");
        btCancelarEdicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarEdicaoActionPerformed(evt);
            }
        });
        add(btCancelarEdicao, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 680, 200, 40));

        tabelaProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Descrição", "Marca", "Tamanho", "Quantidade", "Valor", "Tipo"
            }
        ));
        jScrollPane1.setViewportView(tabelaProdutos);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 190, 460, -1));

        edPesquisa.setToolTipText("Pesquisa nas descrições e tipos");
        edPesquisa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(edPesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 160, 250, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Pesquisar:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 160, -1, -1));

        btPesquisar.setToolTipText("Pesquisa nas descrições e tipos");
        btPesquisar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE PESQUISAR.png"))); // NOI18N
        btPesquisar.setText("PESQUISAR");
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });
        add(btPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 153, -1, 30));

        btEditar.setToolTipText("Edita o produto selecionado");
        btEditar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE EDITAR.png"))); // NOI18N
        btEditar.setText("Editar");
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });
        add(btEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 610, 130, -1));

        btAtualizar.setToolTipText("Atualiza a tabela");
        btAtualizar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE ATUALIZAR.png"))); // NOI18N
        btAtualizar.setText("Atualizar");
        btAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizarActionPerformed(evt);
            }
        });
        add(btAtualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 610, 130, -1));

        btDeletar.setToolTipText("Deleta o produto selecionado");
        btDeletar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE DELETAR.png"))); // NOI18N
        btDeletar.setText("Deletar");
        btDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeletarActionPerformed(evt);
            }
        });
        add(btDeletar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 610, 130, 40));

        comboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(comboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 550, 150, -1));

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 550, -1, 30));

        comboBoxMarca.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboBoxMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxMarcaActionPerformed(evt);
            }
        });
        add(comboBoxMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 290, 150, -1));

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 290, -1, 30));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/projeto menu.png"))); // NOI18N
        jLabel11.setText("jLabel11");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1400, 990));
    }// </editor-fold>//GEN-END:initComponents

    private void btCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarActionPerformed
        if(camposPreenchidos()==true){
        Produto p = new Produto();
        ProdutoDAO pDAO = new ProdutoDAO();
        //Pegando valores dos EDs
        p.setDesc(edDesc.getText());
        p.setMarca(String.valueOf(comboBoxMarca.getSelectedItem()));
        p.setQuantidade(Integer.parseInt(edQuant.getText()));
        p.setTipo(String.valueOf(comboBox.getSelectedItem()));
        p.setTamanho(edTamanho.getText());
        p.setValor(Double.parseDouble(edValor.getText()));
        //Enviar para o DAO
        if(mode == 0){
            pDAO.cadastrarProduto(p);
            gerarTabela();
        }else{
            p.setId_produto(id_edit);
            pDAO.editarPorID(p);
            gerarTabela();
            trocarModo(p);
        }
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
        if(tabelaProdutos.getRowSelectionAllowed()==true){
            ProdutoDAO pDAO = new ProdutoDAO();
            DefaultTableModel modelo = (DefaultTableModel) tabelaProdutos.getModel();
            int[] linhas = tabelaProdutos.getSelectedRows();
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
        if(tabelaProdutos.getRowSelectionAllowed()==true){
            DefaultTableModel modelo = (DefaultTableModel) tabelaProdutos.getModel();
            int[] linhas = tabelaProdutos.getSelectedRows();
            if(linhas.length==1){
                int id = Integer.parseInt(modelo.getValueAt(linhas[0], 0).toString());
                ProdutoDAO pDAO = new ProdutoDAO();
                Produto p = new Produto();
                List<Produto> Lista = pDAO.listarTodos();
                for(int i=0;i<Lista.size();i++){
                    p = Lista.get(i);
                    if(p.getId_produto()==id){
                        id_edit = p.getId_produto();
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
        Produto p = new Produto();
        trocarModo(p);
    }//GEN-LAST:event_btCancelarEdicaoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja acicionar um novo tipo?", "ADICIONAR TIPO", JOptionPane.YES_NO_OPTION);
        if(opcao==0){
            JTextField textfield = new JTextField(50);
            JLabel rotulo = new JLabel("Insira o novo tipo:");
            JPanel painel = new JPanel();
            painel.add(rotulo);
            painel.add(textfield);
            JOptionPane.showMessageDialog(null, painel, "ADICIONAR TIPO", JOptionPane.PLAIN_MESSAGE);
            String novoTipo = textfield.getText();
            Tipo t = new Tipo();
            t.setNome(novoTipo);
            TipoDAO td = new TipoDAO();
            td.cadastrarTipo(t);
            gerarComboBox();
        } 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void comboBoxMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxMarcaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       int opcao = JOptionPane.showConfirmDialog(null, "Deseja acicionar uma nova marca?", "ADICIONAR MARCA", JOptionPane.YES_NO_OPTION);
        if(opcao==0){
            JTextField textfield = new JTextField(50);
            JLabel rotulo = new JLabel("Insira a nova marca:");
            JPanel painel = new JPanel();
            painel.add(rotulo);
            painel.add(textfield);
            JOptionPane.showMessageDialog(null, painel, "ADICIONAR MARCA", JOptionPane.PLAIN_MESSAGE);
            String novoMarca = textfield.getText();
            Marca t = new Marca();
            t.setNome(novoMarca);
            MarcaDAO td = new MarcaDAO();
            td.cadastrarMarca(t);
            gerarComboBox();
        } 
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAtualizar;
    private javax.swing.JButton btCadastrar;
    private javax.swing.JButton btCancelarEdicao;
    private javax.swing.JButton btDeletar;
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btLimparCampos;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JComboBox<String> comboBox;
    private javax.swing.JComboBox<String> comboBoxMarca;
    private javax.swing.JTextField edDesc;
    private javax.swing.JTextField edPesquisa;
    private javax.swing.JTextField edQuant;
    private javax.swing.JTextField edTamanho;
    private javax.swing.JTextField edValor;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JTable tabelaProdutos;
    // End of variables declaration//GEN-END:variables
}
