/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import DAO.ClienteDAO;
import DAO.CompraDAO;
import DAO.FornecedorDAO;
import DAO.ProdutoDAO;
import DAO.VendaDAO;
import Objetos.Cliente;
import Objetos.Compra;
import Objetos.Fornecedor;
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
        gerarTabelaCompras();
        gerarTabelaProdutos();
    }
    
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    
    public void gerarLabelFuncionario(){
        Usuario user = Usuario.getInstancia();
        labelFuncionario.setText(user.getCpf());
    }
    
    public void gerarTabelaCompras(){
        DefaultTableModel modelo = (DefaultTableModel) tabelaCompras.getModel();
        modelo.setNumRows(0);
        CompraDAO pDAO = new CompraDAO();
        List<Compra> Lista = pDAO.listarTodos();
        for(Compra p: Lista){     
            modelo.addRow(new Object[]{p.getId_compra(),p.getData(),p.getFormaPag(),p.getQuantItens(),p.getValor(),p.getId_fornecedor_fk(),p.getId_produto_fk()});
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
        List<Venda> Lista = pDAO.listarTodos();
        for(Venda p: Lista){     
            if(((p.getData().toLowerCase()).contains(edPesquisa.getText().toLowerCase()))){
                modelo.addRow(new Object[]{p.getId_venda(),p.getValor(),p.getData(),p.getHora(),p.getFormaPag(),p.getId_cliente_fk(),p.getId_funcionario_fk()});
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
        edParcelas.setText(null);
        edPesquisa.setText(null);
        edPesquisaProduto.setText(null);
        edPesquisaCliente.setText(null);
    }
    
    public Date getData(){
        Date data = new Date(System.currentTimeMillis());  
        return data;
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
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
        jLabel12 = new javax.swing.JLabel();
        labelFuncionario1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btAdicionarItem = new javax.swing.JButton();
        lbParcelas = new javax.swing.JLabel();
        edParcelas = new javax.swing.JTextField();
        comboBoxFormaPag = new javax.swing.JComboBox<>();
        edFuncID = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCompras = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        edPesquisa = new javax.swing.JTextField();
        btPesquisar = new javax.swing.JButton();
        btDeletar = new javax.swing.JButton();
        btAtualizar = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(650, 650));
        setLayout(null);

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(650, 650));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        labelTitulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelTitulo.setText("Realizar Compra");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Forma Pagamento:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Valor da Compra:");

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Data da Compra:");
        jLabel7.setOpaque(true);

        edValor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btCadastrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE REALIZAR VENDA.png"))); // NOI18N
        btCadastrar.setText("Realizar Compra");
        btCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrarActionPerformed(evt);
            }
        });

        btLimparCampos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btLimparCampos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE LIMPAR CAMPOS.png"))); // NOI18N
        btLimparCampos.setText("Limpar Campos");
        btLimparCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimparCamposActionPerformed(evt);
            }
        });

        labelId.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Produto:");

        tabelaProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Descrição", "Marca","Tipo","Valor"
            }
        ));
        jScrollPane2.setViewportView(tabelaProdutos);

        tabelaFornecedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nome", "Endereço", "CNPJ", "Telefone"
            }
        ));
        jScrollPane4.setViewportView(tabelaFornecedor);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Fornecedor:");

        edPesquisaProduto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        edPesquisaCliente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btPesquisarFornecedor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btPesquisarFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE PESQUISAR.png"))); // NOI18N
        btPesquisarFornecedor.setText("PESQUISAR");
        btPesquisarFornecedor.setBorder(null);
        btPesquisarFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarFornecedorActionPerformed(evt);
            }
        });

        btPesquisarProduto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btPesquisarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE PESQUISAR.png"))); // NOI18N
        btPesquisarProduto.setText("PESQUISAR");
        btPesquisarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarProdutoActionPerformed(evt);
            }
        });

        edData.setFont(new java.awt.Font("Tahoma", 1, 14));
        edData.setEditable(false);
        try {
            edData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####/##/##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Id do Funcionário:");
        jLabel12.setOpaque(true);

        labelFuncionario1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelFuncionario1.setText("[FUNCIONARIO]");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Funcionário:");

        btAdicionarItem.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btAdicionarItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE ADICIONAR ITEM.png"))); // NOI18N
        btAdicionarItem.setText("ADICIONAR ITEM");
        btAdicionarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicionarItemActionPerformed(evt);
            }
        });

        lbParcelas.setVisible(false);
        lbParcelas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbParcelas.setText("Parcelas:");

        edParcelas.setVisible(false);

        comboBoxFormaPag.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        comboBoxFormaPag.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A Vista", "A Prazo" }));
        comboBoxFormaPag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxFormaPagActionPerformed(evt);
            }
        });

        edFuncID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(266, 266, 266)
                        .addComponent(jLabel13)
                        .addGap(6, 6, 6)
                        .addComponent(labelFuncionario1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(labelId, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(63, 63, 63)
                                        .addComponent(edPesquisaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(2, 2, 2)
                                .addComponent(btPesquisarProduto)))
                        .addGap(9, 9, 9))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(edParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(19, 19, 19)
                        .addComponent(edValor, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(53, 53, 53)
                        .addComponent(edData, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(53, 53, 53)
                        .addComponent(edFuncID, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(edPesquisaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(8, 8, 8)
                        .addComponent(btPesquisarFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btCadastrar)
                        .addGap(41, 41, 41)
                        .addComponent(btLimparCampos))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btAdicionarItem)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(8, 8, 8)
                            .addComponent(comboBoxFormaPag, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(126, 126, 126)
                .addComponent(labelFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btCadastrar, btLimparCampos});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {edData, edParcelas});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {comboBoxFormaPag, edValor});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane2, jScrollPane4});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(labelFuncionario1))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(labelFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(labelId, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelTitulo))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(edPesquisaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btPesquisarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(btAdicionarItem, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(comboBoxFormaPag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbParcelas)
                    .addComponent(edParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(edValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(edData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(edFuncID, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btPesquisarFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(edPesquisaCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))))
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btCadastrar)
                    .addComponent(btLimparCampos)))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btCadastrar, btLimparCampos});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {edData, edParcelas});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {comboBoxFormaPag, edValor});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jScrollPane2, jScrollPane4});

        jTabbedPane1.addTab("Cadastrar", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(null);

        tabelaCompras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Data", "Forma Pagamento", "Quantidade Itens", "Valor", "Id Fornecedor", "Id Produto"
            }//p.getId_compra(),p.getData(),p.getFormaPag(),p.getQuantItens(),p.getValor(),p.getId_fornecedor_fk(),p.getId_produto_fk()
        )
    );
    jScrollPane1.setViewportView(tabelaCompras);

    jPanel3.add(jScrollPane1);
    jScrollPane1.setBounds(37, 55, 565, 493);

    jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jLabel10.setText("Pesquisar:");
    jPanel3.add(jLabel10);
    jLabel10.setBounds(37, 21, 54, 15);

    edPesquisa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jPanel3.add(edPesquisa);
    edPesquisa.setBounds(95, 16, 344, 23);

    btPesquisar.setToolTipText("Pesquisa nos nomes e nos cpf's");
    btPesquisar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE PESQUISAR.png"))); // NOI18N
    btPesquisar.setText("PESQUISAR");
    btPesquisar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btPesquisarActionPerformed(evt);
        }
    });
    jPanel3.add(btPesquisar);
    btPesquisar.setBounds(457, 11, 145, 33);

    btDeletar.setToolTipText("Deleta o cliente selecionado.");
    btDeletar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    btDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE DELETAR.png"))); // NOI18N
    btDeletar.setText("Deletar");
    btDeletar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btDeletarActionPerformed(evt);
        }
    });
    jPanel3.add(btDeletar);
    btDeletar.setBounds(140, 560, 109, 41);

    btAtualizar.setToolTipText("Atualiza a tabela.");
    btAtualizar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    btAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE ATUALIZAR.png"))); // NOI18N
    btAtualizar.setText("Atualizar");
    btAtualizar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btAtualizarActionPerformed(evt);
        }
    });
    jPanel3.add(btAtualizar);
    btAtualizar.setBounds(270, 560, 127, 39);

    btAtualizar.setToolTipText("Editar o cliente selecionado.");
    btEditar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE EDITAR.png"))); // NOI18N
    btEditar.setText("Editar");
    btEditar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btEditarActionPerformed(evt);
        }
    });
    jPanel3.add(btEditar);
    btEditar.setBounds(410, 560, 109, 41);

    jTabbedPane1.addTab("Visualizar, Editar e Deletar", jPanel3);

    add(jTabbedPane1);
    jTabbedPane1.setBounds(0, 0, 724, 690);
    }// </editor-fold>//GEN-END:initComponents

    private void btCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarActionPerformed
        if((tabelaFornecedor.getRowSelectionAllowed()==true)&&(tabelaProdutos.getRowSelectionAllowed()==true)){
            int[] linhasClientes = tabelaFornecedor.getSelectedRows();
            if(linhasClientes.length==1){
                int[] id_produto = null;
                int[] quantidade = null;
                DefaultTableModel modeloProdutos = (DefaultTableModel) tabelaProdutos.getModel();
                DefaultTableModel modeloCliente = (DefaultTableModel) tabelaFornecedor.getModel();
                int[] linhasProdutos = tabelaProdutos.getSelectedRows();;
                ProdutoDAO pDAO = new ProdutoDAO();
                List<Produto> listaProdutos = pDAO.listarTodos();
                System.out.println("1");
                for(int j=0;j==linhasProdutos.length;j++){
                    System.out.println("2");
                    if(id_produto[j]<1){
                        id_produto[j] = 0;
                    }
                    quantidade[j] = 0;
                }

                int i = 0;
                for(Produto p: listaProdutos){
                    System.out.println("3");
                    if(p.getId_produto()==(Integer.parseInt(modeloProdutos.getValueAt(linhasProdutos[i], 0).toString()))){
                        quantidade[i] = Integer.parseInt(JOptionPane.showInputDialog("Qual a quantidade para o produto: "+ p.getMarca()+"?"));
                        id_produto[i] = p.getId_produto();
                        i++;
                        System.out.println("4");
                    }else{}
                }

                System.out.println("5");
                int id_cliente = Integer.parseInt(modeloCliente.getValueAt(linhasClientes[0], 0).toString());
                System.out.println(id_cliente);
                //
                Compra r = new Compra();
                CompraDAO rDAO = new CompraDAO();
                Usuario user = Usuario.getInstancia();
                //Pegando valores dos EDs
                r.setData(getData().toString());
                r.setFormaPag(String.valueOf(comboBoxFormaPag.getSelectedItem()));
                System.out.println(user.getId());
                r.setValor(Double.parseDouble(edValor.getText()));
                //Enviar para o DAO
                if(mode == 0){
                    rDAO.cadastrarCompra(r);
                }else{
                    r.setId_compra(id_edit);
                    rDAO.editarPorID(r);
                    trocarModo(r);
                }
                limparCampos();
            }else{
                JOptionPane.showMessageDialog(null, "POR FAVOR, \n Selecione um único cliente");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "POR FAVOR, \n Selecione o(s) produto(s) e cliente(s)");
        }
    }//GEN-LAST:event_btCadastrarActionPerformed

    private void btLimparCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparCamposActionPerformed
        limparCampos();
    }//GEN-LAST:event_btLimparCamposActionPerformed

    private void btPesquisarFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarFornecedorActionPerformed
        gerarTabelaCompras_com_Consulta();
    }//GEN-LAST:event_btPesquisarFornecedorActionPerformed

    private void btPesquisarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarProdutoActionPerformed
        gerarTabelaProdutos_com_Consulta();
    }//GEN-LAST:event_btPesquisarProdutoActionPerformed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        gerarTabelaCompras_com_Consulta();
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void btDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeletarActionPerformed
        if(tabelaCompras.getRowSelectionAllowed()==true){
            CompraDAO pDAO = new CompraDAO();
            DefaultTableModel modelo = (DefaultTableModel) tabelaCompras.getModel();
            int[] linhas = tabelaCompras.getSelectedRows();
            for(int i=0;i<linhas.length;i++){
                int id = Integer.parseInt(modelo.getValueAt(linhas[i], 0).toString());
                pDAO.deletar(id);
                gerarTabelaCompras();
            }
        }else{
            JOptionPane.showMessageDialog(null, "POR FAVOR, Selecione uma linha se deseja editar");
        }
    }//GEN-LAST:event_btDeletarActionPerformed

    private void btAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizarActionPerformed
        gerarTabelaCompras();
    }//GEN-LAST:event_btAtualizarActionPerformed

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        if(tabelaCompras.getRowSelectionAllowed()==true){
            DefaultTableModel modelo = (DefaultTableModel) tabelaCompras.getModel();
            int[] linhas = tabelaCompras.getSelectedRows();
            if(linhas.length==1){
                int id = Integer.parseInt(modelo.getValueAt(linhas[0], 0).toString());
                CompraDAO pDAO = new CompraDAO();
                Compra p = new Compra();
                List<Compra> Lista = pDAO.listarTodos();
                for(int i=0;i<Lista.size();i++){
                    p = Lista.get(i);
                    if(p.getId_compra()==id){
                        id_edit = i;
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

    private void btAdicionarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionarItemActionPerformed
        if(tabelaProdutos.getRowSelectionAllowed()==true){
            DefaultTableModel modelo = (DefaultTableModel) tabelaProdutos.getModel();
            int[] linhas = tabelaProdutos.getSelectedRows();
            if(linhas.length==1){
                int id = Integer.parseInt(modelo.getValueAt(linhas[0], 0).toString());
                listaIdsProdutos.add(id);
            }else{
                JOptionPane.showMessageDialog(null, "POR FAVOR, SÓ SELECIONE UM");
            }
        }else{
            JOptionPane.showMessageDialog(null, "POR FAVOR, Selecione um produto para adicionar");
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdicionarItem;
    private javax.swing.JButton btAtualizar;
    private javax.swing.JButton btCadastrar;
    private javax.swing.JButton btDeletar;
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelFuncionario;
    private javax.swing.JLabel labelFuncionario1;
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel lbParcelas;
    private javax.swing.JTable tabelaCompras;
    private javax.swing.JTable tabelaFornecedor;
    private javax.swing.JTable tabelaProdutos;
    // End of variables declaration//GEN-END:variables
}
