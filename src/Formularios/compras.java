/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import DAO.ClienteDAO;
import DAO.CompraDAO;
import DAO.CompraProdutoDAO;
import DAO.FornecedorDAO;
import DAO.FuncionarioDAO;
import DAO.ProdutoDAO;
import DAO.VendaDAO;
import Objetos.Cliente;
import Objetos.Compra;
import Objetos.Fornecedor;
import Objetos.Funcionario;
import Objetos.Produto;
import Objetos.Usuario;
import Objetos.Venda;
import java.awt.Graphics;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pablo
 */
public class compras extends javax.swing.JPanel {


private int mode = 0;//0 = Cadastrar, 1 = Editar
private int id_edit = 0;//id para ajudar na edição
private List<Integer> listaIdsProdutos;
    /**
     * Creates new form FrameCadastroCliente
     */
    public compras() {
        initComponents();
        gerarLabel();
        gerarData();
        gerarTabelaCompras();
        gerarTabelaProdutos();
        gerarTabelaFornecedores();
    }
    
    public void gerarData(){
        String data = String.valueOf(getData());
        edData.setText(alterarData2(data));
        
    }
    
    public boolean camposPreenchidos(){
        boolean preenchidos = false;
        int qtd = 0;
        if(((edData.getText().trim().replaceAll(" ","").equals("//")))&&((edData.getText().trim().replaceAll(" ","").length()!=10))){
            qtd=qtd+1;
        }
        if(edValor.getText().trim().replaceAll(" ","").equals("")){
            qtd=qtd+1;
        }
        if(edParcelas.getText().trim().replaceAll(" ","").equals("")){
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
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    
    public void adicionarItem(int id, int qtd){
        DefaultTableModel modelo = (DefaultTableModel) tabelaProdutosCarrinho.getModel();
        ProdutoDAO pDAO = new ProdutoDAO();
        List<Produto> Lista = pDAO.listarTodos();
        for(Produto p: Lista){     
            if(id == p.getId_produto()){
                modelo.addRow(new Object[]{p.getId_produto(),p.getDesc(),p.getMarca(),p.getTipo(),p.getValor(),qtd});
            }
        }
    }
    
    public void setValor(){
        double valor = calcularValor();
        if(valor>0){
            lbvalor.setText("R$ "+String.valueOf(valor));
        }else{
            lbvalor.setText("R$ 0,00");
        }
    }
    
    public void zerarValor(){
        lbvalor.setText("R$ 0,00");
    }
    
    public float calcularValor(){
        float valor = 0;
        int qtd;
        float preco;
        DefaultTableModel modelo = (DefaultTableModel) tabelaProdutosCarrinho.getModel();
        int linhas = tabelaProdutosCarrinho.getRowCount();
        if(linhas==1){
            preco = Float.valueOf(String.valueOf(modelo.getValueAt(0, 4)));
            qtd = Integer.parseInt(modelo.getValueAt(0, 5).toString());
            valor = valor + (preco*qtd);
        }else{
            for(int i=0;i<linhas;i++){
                preco = Float.valueOf(String.valueOf(modelo.getValueAt(i, 4)));
                qtd = Integer.parseInt(modelo.getValueAt(i, 5).toString());
                valor = valor + (preco*qtd);
            }
        
        }
        return valor;
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
    
    public void gerarLabelFuncionario(){
        Usuario user = Usuario.getInstancia();
        labelFuncionario.setText(user.getCpf());
    }
    
    public void gerarTabelaCompras(){
        DefaultTableModel modelo = (DefaultTableModel) tabelaCompras.getModel();
        modelo.setNumRows(0);
        String str = null;
        CompraDAO pDAO = new CompraDAO();
        FornecedorDAO fDAO = new FornecedorDAO();
        List<Compra> Lista = pDAO.listarTodos();
        List<Fornecedor> ListaF = fDAO.listarTodos();
        for(Compra p: Lista){     
            for(Fornecedor f: ListaF){     
                if(f.getId_fornecedor()==p.getId_fornecedor_fk()){
                    str = f.getNome();
                }
            }
            modelo.addRow(new Object[]{p.getId_compra(),p.getData(),p.getFormaPag(),p.getValor(),str});
        }
    }
    
    public void gerarTabelaProdutos(){
        DefaultTableModel modelo = (DefaultTableModel) tabelaProdutos.getModel();
        modelo.setNumRows(0);
        ProdutoDAO pDAO = new ProdutoDAO();
        List<Produto> Lista = pDAO.listarTodos();
        for(Produto p: Lista){     
            modelo.addRow(new Object[]{p.getId_produto(),p.getDesc(),p.getMarca(),p.getTipo(),p.getValor()});
        }
    }
    
    public void gerarTabelaFornecedores(){
        DefaultTableModel modelo = (DefaultTableModel) tabelaFornecedor.getModel();
        modelo.setNumRows(0);
        FornecedorDAO pDAO = new FornecedorDAO();
        List<Fornecedor> Lista = pDAO.listarTodos();
        for(Fornecedor p: Lista){     
            modelo.addRow(new Object[]{p.getId_fornecedor(),p.getNome(),p.getEnd(),p.getCnpj(),p.getTel()});
        }
    }
    
    public void gerarTabelaCompras_com_Consulta(){
        DefaultTableModel modelo = (DefaultTableModel) tabelaCompras.getModel();
        modelo.setNumRows(0);
        VendaDAO pDAO = new VendaDAO();
        FuncionarioDAO fDAO = new FuncionarioDAO();
        List<Venda> Lista = pDAO.listarTodos();
        List<Funcionario> ListaF = fDAO.listarTodos();
        String str = null;
        for(Venda p: Lista){     
            if(((p.getData().toLowerCase()).contains(edPesquisa.getText().toLowerCase()))){
                for(Funcionario f: ListaF){     
                if(p.getId_funcionario_fk()==f.getId_funcionario()){
                     str = f.getNome();
                }
                }
                modelo.addRow(new Object[]{p.getId_venda(),p.getValor(),p.getData(),p.getHora(),p.getFormaPag(),p.getId_cliente_fk(),str});
            }//        "Id", "Valor", "Data", "Hora", "Forma Pagamento", "Cliente", "Funcionário", "Produto", "Quantidade"
        }
    }
    
    public void gerarTabelaFornecedores_com_Consulta(){
        DefaultTableModel modelo = (DefaultTableModel) tabelaFornecedor.getModel();
        modelo.setNumRows(0);
        ClienteDAO pDAO = new ClienteDAO();
        List<Cliente> Lista = pDAO.listarTodos();
        for(Cliente p: Lista){     
            if(((p.getNome().toLowerCase()).contains(edPesquisaCliente.getText().toLowerCase()))||(((String.valueOf(p.getId_cliente())).toLowerCase()).contains(edPesquisaCliente.getText().toLowerCase()))){
                modelo.addRow(new Object[]{p.getId_cliente(),p.getNome(),p.getRg(),p.getCpf()});
            }
        }
    }
    
    public void gerarTabelaProdutos_com_Consulta(){
        DefaultTableModel modelo = (DefaultTableModel) tabelaProdutos.getModel();
        modelo.setNumRows(0);
        ProdutoDAO pDAO = new ProdutoDAO();
        List<Produto> Lista = pDAO.listarTodos();
        for(Produto p: Lista){     
            if((p.getDesc().toLowerCase()).contains(edPesquisaProduto.getText().toLowerCase())||(((String.valueOf(p.getId_produto())).toLowerCase()).contains(edPesquisaCliente.getText().toLowerCase()))){
                modelo.addRow(new Object[]{p.getId_produto(),p.getDesc(),p.getMarca(),p.getTipo(),p.getValor()});
            }
        }
    }
    
    public void limparCampos(){
        edValor.setText(null);
        edData.setText(null);
        edParcelas.setText("1");
        edPesquisa.setText(null);
        edPesquisaProduto.setText(null);
        edPesquisaCliente.setText(null);
        gerarData();
    }
    
    public String getHora(){
        Calendar data = Calendar.getInstance();
        String hora = String.valueOf(data.get(Calendar.HOUR_OF_DAY)); 
        String min = String.valueOf(data.get(Calendar.MINUTE));
        String seg = String.valueOf(data.get(Calendar.SECOND));
        String horaFinal = hora+":"+min+":"+seg+"";
        return horaFinal;
    }
    
    public void trocarModo(Compra p){
        if(mode == 0){
            mode = 1;
            labelTitulo.setText("Editar Venda");
            btCadastrar.setText("Editar Venda");
            btCancelarEdicao.setVisible(true);
            edValor.setVisible(true);
            edData.setVisible(true);
            edFuncID.setVisible(true);
            lb1.setVisible(true);
            lb2.setVisible(true);
            lb3.setVisible(true);
            labelId.setText("Id:"+p.getId_compra());
            //Pegando valores dos EDs
            edValor.setText(String.valueOf(p.getValor()));
            edData.setText(p.getData().replaceAll("-", ""));
            if(p.getFormaPag().equals("A Vista")){
                comboBoxFormaPag.setSelectedIndex(0);
            }else{
                comboBoxFormaPag.setSelectedIndex(1);
            }
            edData.setEditable(true);
            edPesquisaCliente.setText(String.valueOf(p.getId_fornecedor_fk()));
            gerarTabelaFornecedores_com_Consulta();
            gerarTabelaProdutos_com_Consulta();
        }else{
            mode = 0;
            labelTitulo.setText("Realizar Venda");
            btCadastrar.setText("Realizar Venda");
            btCancelarEdicao.setVisible(false);
            labelId.setText(null);
            edValor.setVisible(false);
            edData.setVisible(false);
            edFuncID.setVisible(false);
            lb1.setVisible(false);
            lb2.setVisible(false);
            lb3.setVisible(false);
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
        jLabel4 = new javax.swing.JLabel();
        lb1 = new javax.swing.JLabel();
        lb2 = new javax.swing.JLabel();
        edValor = new javax.swing.JTextField();
        btCadastrar = new javax.swing.JButton();
        btLimparCampos = new javax.swing.JButton();
        labelId = new javax.swing.JLabel();
        labelFuncionario = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaProdutos = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelaFornecedor = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        edPesquisaProduto = new javax.swing.JTextField();
        edPesquisaCliente = new javax.swing.JTextField();
        btPesquisarFornecedor = new javax.swing.JButton();
        btPesquisarProduto = new javax.swing.JButton();
        edData = new javax.swing.JFormattedTextField();
        lb3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btAdicionarItem = new javax.swing.JButton();
        lbParcelas = new javax.swing.JLabel();
        edParcelas = new javax.swing.JTextField();
        comboBoxFormaPag = new javax.swing.JComboBox<>();
        edFuncID = new javax.swing.JLabel();
        btAtualizar1 = new javax.swing.JButton();
        btAtualizar2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btDeletarItem = new javax.swing.JButton();
        btAlterarQtd = new javax.swing.JButton();
        btCancelarEdicao = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabelaProdutosCarrinho = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        lbvalor = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        edPesquisa = new javax.swing.JTextField();
        btPesquisar = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();
        btAtualizar = new javax.swing.JButton();
        btDeletar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelaCompras = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(650, 650));
        setLayout(null);

        labelTitulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelTitulo.setText("Realizar Compra");
        add(labelTitulo);
        labelTitulo.setBounds(330, 30, 230, 29);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Forma Pagamento:");
        add(jLabel4);
        jLabel4.setBounds(100, 310, 103, 20);

        lb1.setVisible(false);
        lb1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lb1.setText("Valor da Compra:");
        add(lb1);
        lb1.setBounds(100, 370, 93, 20);

        lb2.setVisible(false);
        lb2.setBackground(new java.awt.Color(255, 255, 255));
        lb2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lb2.setText("Data da Compra:");
        lb2.setOpaque(true);
        add(lb2);
        lb2.setBounds(100, 400, 91, 20);

        edValor.setVisible(false);
        edValor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add(edValor);
        edValor.setBounds(210, 370, 170, 20);

        btCadastrar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE REALIZAR VENDA.png"))); // NOI18N
        btCadastrar.setText("Realizar Compra");
        btCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrarActionPerformed(evt);
            }
        });
        add(btCadastrar);
        btCadastrar.setBounds(300, 540, 167, 33);

        btLimparCampos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btLimparCampos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE LIMPAR CAMPOS.png"))); // NOI18N
        btLimparCampos.setText("Limpar Campos");
        btLimparCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimparCamposActionPerformed(evt);
            }
        });
        add(btLimparCampos);
        btLimparCampos.setBounds(500, 540, 167, 33);

        labelId.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        add(labelId);
        labelId.setBounds(95, 46, 43, 13);
        add(labelFuncionario);
        labelFuncionario.setBounds(700, 0, 90, 28);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Produto:");
        add(jLabel8);
        jLabel8.setBounds(30, 70, 80, 15);

        tabelaProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Descrição", "Marca","Tipo","Valor"
            }
        ));
        jScrollPane2.setViewportView(tabelaProdutos);

        add(jScrollPane2);
        jScrollPane2.setBounds(30, 100, 390, 133);

        tabelaFornecedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nome", "Endereço", "CNPJ", "Telefone"
            }
        ));
        jScrollPane4.setViewportView(tabelaFornecedor);

        add(jScrollPane4);
        jScrollPane4.setBounds(460, 100, 390, 133);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Fornecedor:");
        add(jLabel9);
        jLabel9.setBounds(460, 60, 90, 40);

        edPesquisaProduto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add(edPesquisaProduto);
        edPesquisaProduto.setBounds(80, 70, 210, 21);

        edPesquisaCliente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add(edPesquisaCliente);
        edPesquisaCliente.setBounds(530, 70, 210, 23);

        btPesquisarFornecedor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btPesquisarFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE PESQUISAR.png"))); // NOI18N
        btPesquisarFornecedor.setText("PESQUISAR");
        btPesquisarFornecedor.setBorder(null);
        btPesquisarFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarFornecedorActionPerformed(evt);
            }
        });
        add(btPesquisarFornecedor);
        btPesquisarFornecedor.setBounds(740, 70, 110, 23);

        btPesquisarProduto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btPesquisarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE PESQUISAR.png"))); // NOI18N
        btPesquisarProduto.setText("PESQUISAR");
        btPesquisarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarProdutoActionPerformed(evt);
            }
        });
        add(btPesquisarProduto);
        btPesquisarProduto.setBounds(290, 70, 130, 22);

        edData.setVisible(false);
        edData.setFont(new java.awt.Font("Tahoma", 0, 14));
        try {
            edData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        add(edData);
        edData.setBounds(240, 400, 90, 20);

        lb3.setVisible(false);
        lb3.setBackground(new java.awt.Color(255, 255, 255));
        lb3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lb3.setText("Id do Funcionário:");
        lb3.setOpaque(true);
        add(lb3);
        lb3.setBounds(100, 430, 98, 20);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Funcionário:");
        add(jLabel13);
        jLabel13.setBounds(630, 0, 65, 15);

        btAdicionarItem.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btAdicionarItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE ADICIONAR ITEM.png"))); // NOI18N
        btAdicionarItem.setText("ADICIONAR ITEM");
        btAdicionarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicionarItemActionPerformed(evt);
            }
        });
        add(btAdicionarItem);
        btAdicionarItem.setBounds(150, 240, 163, 28);

        lbParcelas.setVisible(false);
        lbParcelas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbParcelas.setText("Parcelas:");
        add(lbParcelas);
        lbParcelas.setBounds(100, 340, 100, 20);

        edParcelas.setVisible(false);
        edParcelas.setText("1");
        add(edParcelas);
        edParcelas.setBounds(270, 340, 90, 20);

        comboBoxFormaPag.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        comboBoxFormaPag.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A Vista", "A Prazo" }));
        comboBoxFormaPag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxFormaPagActionPerformed(evt);
            }
        });
        add(comboBoxFormaPag);
        comboBoxFormaPag.setBounds(210, 310, 170, 20);

        edFuncID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(edFuncID);
        edFuncID.setBounds(210, 430, 50, 20);

        btAtualizar.setToolTipText("Atualiza a tabela.");
        btAtualizar1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btAtualizar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE ATUALIZAR.png"))); // NOI18N
        btAtualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizar1ActionPerformed(evt);
            }
        });
        add(btAtualizar1);
        btAtualizar1.setBounds(800, 240, 50, 28);

        btAtualizar.setToolTipText("Atualiza a tabela.");
        btAtualizar2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btAtualizar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE ATUALIZAR.png"))); // NOI18N
        btAtualizar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizar2ActionPerformed(evt);
            }
        });
        add(btAtualizar2);
        btAtualizar2.setBounds(350, 240, 63, 28);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Carrinho:");
        add(jLabel5);
        jLabel5.setBounds(630, 280, 57, 17);

        btDeletarItem.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btDeletarItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE DELETAR.png"))); // NOI18N
        btDeletarItem.setText("Deletar Item");
        btDeletarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeletarItemActionPerformed(evt);
            }
        });
        add(btDeletarItem);
        btDeletarItem.setBounds(530, 460, 129, 33);

        btAlterarQtd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btAlterarQtd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE ALTERAR QUANTIDADE.png"))); // NOI18N
        btAlterarQtd.setText("Alterar Quantidade");
        btAlterarQtd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterarQtdActionPerformed(evt);
            }
        });
        add(btAlterarQtd);
        btAlterarQtd.setBounds(670, 460, 171, 33);

        btCancelarEdicao.setVisible(false);
        btCancelarEdicao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btCancelarEdicao.setText("Cancelar Edição");
        btCancelarEdicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarEdicaoActionPerformed(evt);
            }
        });
        add(btCancelarEdicao);
        btCancelarEdicao.setBounds(390, 610, 129, 25);

        tabelaProdutosCarrinho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Descrição", "Marca","Tipo","Valor", "Quantidade"
            }
        ));
        jScrollPane5.setViewportView(tabelaProdutosCarrinho);

        add(jScrollPane5);
        jScrollPane5.setBounds(470, 310, 390, 143);

        jLabel1.setText("Valor Atual da Venda: ");
        add(jLabel1);
        jLabel1.setBounds(200, 560, 107, 14);

        lbvalor.setText("R$ 0,00");
        add(lbvalor);
        lbvalor.setBounds(210, 580, 50, 20);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Pesquisar:");
        add(jLabel11);
        jLabel11.setBounds(930, 130, 54, 15);

        edPesquisa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(edPesquisa);
        edPesquisa.setBounds(990, 130, 240, 23);

        btPesquisar.setToolTipText("Pesquisa nos nomes e nos cpf's");
        btPesquisar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE PESQUISAR.png"))); // NOI18N
        btPesquisar.setText("PESQUISAR");
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });
        add(btPesquisar);
        btPesquisar.setBounds(1240, 120, 133, 33);

        btAtualizar.setToolTipText("Editar o cliente selecionado.");
        btEditar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE EDITAR.png"))); // NOI18N
        btEditar.setText("Editar");
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });
        add(btEditar);
        btEditar.setBounds(1000, 590, 103, 41);

        btAtualizar.setToolTipText("Atualiza a tabela.");
        btAtualizar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE ATUALIZAR.png"))); // NOI18N
        btAtualizar.setText("Atualizar");
        btAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizarActionPerformed(evt);
            }
        });
        add(btAtualizar);
        btAtualizar.setBounds(1130, 590, 117, 39);

        btDeletar.setToolTipText("Deleta o cliente selecionado.");
        btDeletar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE DELETAR.png"))); // NOI18N
        btDeletar.setText("Deletar");
        btDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeletarActionPerformed(evt);
            }
        });
        add(btDeletar);
        btDeletar.setBounds(1270, 590, 103, 33);

        tabelaCompras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Data", "Forma Pagamento", "Valor", "Fornecedor"
            }//p.getId_compra(),p.getData(),p.getFormaPag(),p.getQuantItens(),p.getValor(),p.getId_fornecedor_fk(),p.getId_produto_fk()
        )
    );
    jScrollPane3.setViewportView(tabelaCompras);

    add(jScrollPane3);
    jScrollPane3.setBounds(902, 160, 470, 402);

    jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/projeto menu.png"))); // NOI18N
    add(jLabel2);
    jLabel2.setBounds(0, 0, 1410, 960);
    }// </editor-fold>//GEN-END:initComponents

    private void btCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarActionPerformed
        if(camposPreenchidos()==true){
        if((tabelaFornecedor.getRowCount()>0)&&(tabelaProdutos.getRowCount()>0)){
            int[] linhasClientes = tabelaFornecedor.getSelectedRows();
            if(linhasClientes.length==1){
                DefaultTableModel modeloCliente = (DefaultTableModel) tabelaFornecedor.getModel();
                int id_cliente = Integer.parseInt(modeloCliente.getValueAt(linhasClientes[0], 0).toString());
                //PRIMEIRO A GENTE REALIZA A VENDA E DPS CONTA OS PRODUTOS
                Compra r = new Compra();
                CompraDAO rDAO = new CompraDAO();
                Usuario user = Usuario.getInstancia();
                //Pegando valores dos EDs
                r.setId_funcionario_fk(user.getId());
                r.setData(alterarData(edData.getText()).toString());
                r.setFormaPag(String.valueOf(comboBoxFormaPag.getSelectedItem()));
                r.setId_fornecedor_fk(id_cliente);
                r.setParcelas(Integer.parseInt(edParcelas.getText()));
                r.setValor(calcularValor());
                //Enviar para o DAO
                rDAO.cadastrarCompra(r);
                if(mode == 0){
                    CompraProdutoDAO pvDAO = new CompraProdutoDAO();
                    DefaultTableModel modelo = (DefaultTableModel) tabelaProdutosCarrinho.getModel();
                    int linhas = tabelaProdutosCarrinho.getRowCount();
                    System.out.println(linhas);
                    if(linhas==1){
                        r.setId_produto_fk(Integer.parseInt(modelo.getValueAt(0, 0).toString()));
                        r.setQuantItens(Integer.parseInt(String.valueOf(modelo.getValueAt(0, 5))));
                        r.setValorUnit(Double.parseDouble(modelo.getValueAt(0, 4).toString()));
                        r.setId_compra(pvDAO.retornarUltimoId());
                        pvDAO.cadastrarCompraProduto(r);
                    }else{
                        for(int i=0;i<linhas;i++){
                            r.setId_produto_fk(Integer.parseInt(modelo.getValueAt(i, 0).toString()));
                            r.setQuantItens(Integer.parseInt(modelo.getValueAt(i, 5).toString()));
                            r.setValorUnit(Double.parseDouble(modelo.getValueAt(i, 4).toString()));
                            r.setId_compra(pvDAO.retornarUltimoId());
                            pvDAO.cadastrarCompraProduto(r);
                        }
                    }
                }else{
                    r.setId_compra(id_edit);
                    rDAO.editarPorID(r);
                    gerarTabelaCompras();
                    trocarModo(r);
                    zerarValor();
                }
                gerarTabelaCompras();
                limparCampos();
            }else{
                JOptionPane.showMessageDialog(null, "Por favor, selecione um único cliente", "Importante", 1);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Por favor, selecione o(s) produto(s) e cliente(s)", "Importante", 1);
        }
        }
    }//GEN-LAST:event_btCadastrarActionPerformed

    private void btLimparCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparCamposActionPerformed
        limparCampos();
    }//GEN-LAST:event_btLimparCamposActionPerformed

    private void btPesquisarFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarFornecedorActionPerformed
        gerarTabelaFornecedores_com_Consulta();
    }//GEN-LAST:event_btPesquisarFornecedorActionPerformed

    private void btPesquisarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarProdutoActionPerformed
        gerarTabelaProdutos_com_Consulta();
    }//GEN-LAST:event_btPesquisarProdutoActionPerformed

    private void btAdicionarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionarItemActionPerformed
        if(tabelaProdutos.getRowSelectionAllowed()==true){
            DefaultTableModel modelo = (DefaultTableModel) tabelaProdutos.getModel();
            int[] linhas = tabelaProdutos.getSelectedRows();
            if(linhas.length==1){
                int id = Integer.parseInt(modelo.getValueAt(linhas[0], 0).toString());
                int qtd = Integer.parseInt(JOptionPane.showInputDialog("Quantidade do produto adicionado: ","Digite a Quantidade"));
                adicionarItem(id,qtd);
                setValor();
            }else{
                JOptionPane.showMessageDialog(null, "Por favor, selecione um único produto");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Por favor, Selecione um produto para adicionar");
        }
    }//GEN-LAST:event_btAdicionarItemActionPerformed

    private void comboBoxFormaPagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxFormaPagActionPerformed
        if(comboBoxFormaPag.getSelectedIndex()!=0){
            edParcelas.setVisible(true);
            lbParcelas.setVisible(true);
        }else{
            edParcelas.setVisible(false);
            lbParcelas.setVisible(false);
        }
    }//GEN-LAST:event_comboBoxFormaPagActionPerformed

    private void btAtualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizar1ActionPerformed
        gerarTabelaFornecedores();
    }//GEN-LAST:event_btAtualizar1ActionPerformed

    private void btAtualizar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizar2ActionPerformed
        gerarTabelaProdutos();
    }//GEN-LAST:event_btAtualizar2ActionPerformed

    private void btDeletarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeletarItemActionPerformed
        if(tabelaProdutosCarrinho.getRowSelectionAllowed()==true){
            DefaultTableModel modelo = (DefaultTableModel) tabelaProdutosCarrinho.getModel();
            int[] linhas = tabelaProdutosCarrinho.getSelectedRows();
            if(linhas.length==1){
                modelo.removeRow(linhas[0]);
                setValor();
            }else{
                JOptionPane.showMessageDialog(null, "Por favor, selecione um único produto");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Por favor, Selecione um produto para adicionar");
        }
    }//GEN-LAST:event_btDeletarItemActionPerformed

    private void btAlterarQtdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarQtdActionPerformed
        if(tabelaProdutosCarrinho.getRowSelectionAllowed()==true){
            DefaultTableModel modelo = (DefaultTableModel) tabelaProdutosCarrinho.getModel();
            int[] linhas = tabelaProdutosCarrinho.getSelectedRows();
            if(linhas.length==1){
                int qtd = Integer.parseInt(JOptionPane.showInputDialog("Quantidade do produto adicionado: ","Digite a Quantidade"));
                modelo.setValueAt(qtd, linhas[0], 5);
                setValor();
            }else{
                JOptionPane.showMessageDialog(null, "Por favor, selecione um único produto");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Por favor, Selecione um produto para adicionar");
        }
    }//GEN-LAST:event_btAlterarQtdActionPerformed

    private void btCancelarEdicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarEdicaoActionPerformed
        Compra p = new Compra();
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
    private javax.swing.JButton btAdicionarItem;
    private javax.swing.JButton btAlterarQtd;
    private javax.swing.JButton btAtualizar;
    private javax.swing.JButton btAtualizar1;
    private javax.swing.JButton btAtualizar2;
    private javax.swing.JButton btCadastrar;
    private javax.swing.JButton btCancelarEdicao;
    private javax.swing.JButton btDeletar;
    private javax.swing.JButton btDeletarItem;
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btLimparCampos;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JButton btPesquisarFornecedor;
    private javax.swing.JButton btPesquisarProduto;
    private javax.swing.JComboBox<String> comboBoxFormaPag;
    private javax.swing.JFormattedTextField edData;
    private javax.swing.JLabel edFuncID;
    private javax.swing.JTextField edParcelas;
    private javax.swing.JTextField edPesquisa;
    private javax.swing.JTextField edPesquisaCliente;
    private javax.swing.JTextField edPesquisaProduto;
    private javax.swing.JTextField edValor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel labelFuncionario;
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lb3;
    private javax.swing.JLabel lbParcelas;
    private javax.swing.JLabel lbvalor;
    private javax.swing.JTable tabelaCompras;
    private javax.swing.JTable tabelaFornecedor;
    private javax.swing.JTable tabelaProdutos;
    private javax.swing.JTable tabelaProdutosCarrinho;
    // End of variables declaration//GEN-END:variables
}
