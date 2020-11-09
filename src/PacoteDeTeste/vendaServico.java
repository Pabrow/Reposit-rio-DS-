/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PacoteDeTeste;

import DAO.ClienteDAO;
import DAO.ServicoDAO;
import DAO.VendaDAO;
import DAO.VendaServicoDAO;
import Formularios.FrameCadastroServicos;
import Objetos.Cliente;
import Objetos.Mensagens;
import Objetos.Servico;
import Objetos.Usuario;
import Objetos.Venda;
import Objetos.VendaServico;
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
public class vendaServico extends javax.swing.JPanel {

private int mode = 0;//0 = Cadastrar, 1 = Editar
private int id_edit = 0;//id para ajudar na edição
Mensagens m = new Mensagens();
    /**
     * Creates new form FrameCadastroCliente
     */
    public vendaServico() {
        initComponents();
        gerarTabelaVendas();
        gerarTabelaServicos();
        gerarTabelaClientes();
        gerarLabelFuncionario();
    }
    
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    
    public void gerarLabelFuncionario(){
        Usuario user = Usuario.getInstancia();
        labelFuncionario.setText(user.getCpf());
    }
    
    public void adicionarItem(int id, int qtd){
        DefaultTableModel modelo = (DefaultTableModel) tabelaServicosCarrinho.getModel();
        ServicoDAO pDAO = new ServicoDAO();
        List<Servico> Lista = pDAO.listarTodos();
        for(Servico p: Lista){     
            if(id == p.getId_servico()){
                modelo.addRow(new Object[]{p.getId_servico(),p.getDesc(),p.getTempo(),p.getTempo(),p.getValor()});
            }
        }
    }
    
    public void gerarTabelaVendas(){
        DefaultTableModel modelo = (DefaultTableModel) tabelaVendas.getModel();
        modelo.setNumRows(0);
        VendaDAO pDAO = new VendaDAO();
        List<Venda> Lista = pDAO.listarTodos();
        for(Venda p: Lista){     
            modelo.addRow(new Object[]{p.getId_venda(),p.getValor(),p.getData(),p.getHora(),p.getFormaPag(),p.getId_cliente_fk(),p.getId_funcionario_fk()});
        }
    }
    
    public void gerarTabelaServicos(){
        DefaultTableModel modelo = (DefaultTableModel) tabelaServicos.getModel();
        modelo.setNumRows(0);
        ServicoDAO pDAO = new ServicoDAO();
        List<Servico> Lista = pDAO.listarTodos();
        for(Servico p: Lista){     
            modelo.addRow(new Object[]{p.getId_servico(),p.getDesc(),p.getTempo(),p.getTempo(),p.getValor()});
        }
    }
    
    public void gerarTabelaClientes(){
        DefaultTableModel modelo = (DefaultTableModel) tabelaClientes.getModel();
        modelo.setNumRows(0);
        ClienteDAO pDAO = new ClienteDAO();
        List<Cliente> Lista = pDAO.listarTodos();
        for(Cliente p: Lista){     
            modelo.addRow(new Object[]{p.getId_cliente(),p.getNome(),p.getRg(),p.getCpf()});
        }
    }
    
    public void gerarTabelaVendas_com_Consulta(){
        DefaultTableModel modelo = (DefaultTableModel) tabelaVendas.getModel();
        modelo.setNumRows(0);
        VendaDAO pDAO = new VendaDAO();
        List<Venda> Lista = pDAO.listarTodos();
        for(Venda p: Lista){     
            if(((p.getData().toLowerCase()).contains(edPesquisa.getText().toLowerCase()))){
                modelo.addRow(new Object[]{p.getId_venda(),p.getValor(),p.getData(),p.getHora(),p.getFormaPag(),p.getId_cliente_fk(),p.getId_funcionario_fk()});
            }//        "Id", "Valor", "Data", "Hora", "Forma Pagamento", "Cliente", "Funcionário", "Produto", "Quantidade"
        }
    }
    
    public void gerarTabelaClientes_com_Consulta(){
        DefaultTableModel modelo = (DefaultTableModel) tabelaClientes.getModel();
        modelo.setNumRows(0);
        ClienteDAO pDAO = new ClienteDAO();
        List<Cliente> Lista = pDAO.listarTodos();
        for(Cliente p: Lista){     
            if(((p.getNome().toLowerCase()).contains(edPesquisaCliente.getText().toLowerCase()))||(((String.valueOf(p.getId_cliente())).toLowerCase()).contains(edPesquisaCliente.getText().toLowerCase()))){
                modelo.addRow(new Object[]{p.getId_cliente(),p.getNome(),p.getRg(),p.getCpf()});
            }
        }
    }
    
    public void gerarTabelaServicos_com_Consulta(){
        DefaultTableModel modelo = (DefaultTableModel) tabelaServicos.getModel();
        modelo.setNumRows(0);
        ServicoDAO pDAO = new ServicoDAO();
        List<Servico> Lista = pDAO.listarTodos();
        for(Servico p: Lista){     
            if((p.getDesc().toLowerCase()).contains(edPesquisaProduto.getText().toLowerCase())||(((String.valueOf(p.getId_servico())).toLowerCase()).contains(edPesquisaCliente.getText().toLowerCase()))){
                modelo.addRow(new Object[]{p.getId_servico(),p.getDesc(),p.getTempo(),p.getTempo(),p.getValor()});
            }
        }
    }
    
    public void gerarTabelaCarrinho_com_Consulta(){
        int count_linha = 0;
        DefaultTableModel modelo = (DefaultTableModel) tabelaServicosCarrinho.getModel();
        ServicoDAO pDAO = new ServicoDAO();
        List<Servico> Lista = pDAO.listarTodos();
        for(Servico p: Lista){     
            if((p.getDesc().toLowerCase()).contains(edPesquisaProduto.getText().toLowerCase())||(((String.valueOf(p.getId_servico())).toLowerCase()).contains(edPesquisaCliente.getText().toLowerCase()))){
                modelo.addRow(new Object[]{p.getId_servico(),p.getDesc(),p.getTempo(),p.getTempo(),p.getValor()});
                count_linha=+1;
            }
        }
        int[] linhas = tabelaServicosCarrinho.getSelectedRows();
        int qtd_linhas = linhas.length - 1;
        for(int i =0;i<=(qtd_linhas-count_linha);i++){
            modelo.removeRow(i);
        }
    }
    
    public void limparCampos(){
        edValor.setText(null);
        edData.setText(null);
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
    
    public double calcularValor(){
        float valor = 0;
        int qtd;
        float preco;
        DefaultTableModel modelo = (DefaultTableModel) tabelaServicosCarrinho.getModel();
        int[] linhas = tabelaServicosCarrinho.getSelectedRows();
        if(linhas.length==1){
            preco = Float.valueOf(String.valueOf(modelo.getValueAt(linhas[0], 4)));
            qtd = Integer.parseInt(modelo.getValueAt(linhas[0], 5).toString());
            valor = valor + (preco*qtd);
        }else{
            for(int i=0;i<linhas.length;i++){
                preco = Float.valueOf(String.valueOf(modelo.getValueAt(linhas[i], 4)));
                qtd = Integer.parseInt(modelo.getValueAt(linhas[i], 5).toString());
                valor = valor + (preco*qtd);
            }
        
        }
        return valor;
    }
    
    public void verificador(FrameCadastroServicos fcv){
        boolean end = false;
        if(fcv.isVisible()){
            do{
                if(fcv.isVisible()){
                }else{
                    gerarTabelaServicos();
                    break;
                }
            }while(end != true);
        }
    }
    public void trocarModo(Venda p){
        if(mode == 0){
            mode = 1;
            labelTitulo.setText("Editar Venda");
            btCadastrar.setText("Editar Venda");
            labelId.setText("Id:"+p.getId_venda());
            //Pegando valores dos EDs
            edValor.setText(String.valueOf(p.getValor()));
            edData.setText(p.getData().replaceAll("-", ""));
            edHora.setText(p.getHora());
            edFuncID.setText(String.valueOf(p.getId_funcionario_fk()));
            edFuncID.setVisible(true);
            edHora.setVisible(true);
            edData.setVisible(true);
            edFuncID.setEditable(true);
            edHora.setEditable(true);
            edData.setEditable(true);
            edPesquisaCliente.setText(String.valueOf(p.getId_cliente_fk()));
            if(p.getFormaPag().equals("A Vista")){
                comboBoxFormaPag.setSelectedIndex(0);
            }else{
                comboBoxFormaPag.setSelectedIndex(1);
            }
            gerarTabelaClientes_com_Consulta();
            gerarTabelaServicos_com_Consulta();
        }else{
            mode = 0;
            edFuncID.setVisible(false);
            edHora.setVisible(false);
            edData.setVisible(false);
            edFuncID.setEditable(false);
            edHora.setEditable(false);
            edData.setEditable(false);
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
        lbValor = new javax.swing.JLabel();
        edValor = new javax.swing.JTextField();
        btCadastrar = new javax.swing.JButton();
        btLimparCampos = new javax.swing.JButton();
        labelId = new javax.swing.JLabel();
        labelFuncionario = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaServicos = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelaClientes = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        edPesquisaProduto = new javax.swing.JTextField();
        edPesquisaCliente = new javax.swing.JTextField();
        btPesquisarCliente = new javax.swing.JButton();
        btPesquisarProduto = new javax.swing.JButton();
        lbHora = new javax.swing.JLabel();
        edData = new javax.swing.JFormattedTextField();
        lbId = new javax.swing.JLabel();
        edFuncID = new javax.swing.JTextField();
        edHora = new javax.swing.JTextField();
        btAdicionarItem = new javax.swing.JButton();
        labelFuncionario1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbData = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabelaServicosCarrinho = new javax.swing.JTable();
        comboBoxFormaPag = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        btAlterarQtd = new javax.swing.JButton();
        btDeletarItem = new javax.swing.JButton();
        edParcelas = new javax.swing.JTextField();
        lbParcelas = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaVendas = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        edPesquisa = new javax.swing.JTextField();
        btPesquisar = new javax.swing.JButton();
        btDeletar = new javax.swing.JButton();
        btAtualizar = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        labelTitulo.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        labelTitulo.setText("Realizar Venda");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setText("Forma Pagamento:");

        lbValor.setVisible(false);
        lbValor.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbValor.setText("Valor da Venda:");
        lbValor.setEnabled(false);

        edValor.setVisible(false);
        edValor.setEditable(false);
        edValor.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        edValor.setEnabled(false);

        btCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE REALIZAR VENDA.png"))); // NOI18N
        btCadastrar.setText("Realizar Venda");
        btCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrarActionPerformed(evt);
            }
        });

        btLimparCampos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE LIMPAR CAMPOS.png"))); // NOI18N
        btLimparCampos.setText("Limpar Campos");
        btLimparCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimparCamposActionPerformed(evt);
            }
        });

        labelId.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel8.setText("Serviços:");

        tabelaServicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Descrição", "Tempo", "Preço"
            }
        ));
        jScrollPane2.setViewportView(tabelaServicos);

        tabelaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nome", "RG", "CPF"
            }
        ));
        jScrollPane4.setViewportView(tabelaClientes);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel9.setText("Cliente:");

        btPesquisarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE PESQUISAR.png"))); // NOI18N
        btPesquisarCliente.setText("PESQUISAR");
        btPesquisarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarClienteActionPerformed(evt);
            }
        });

        btPesquisarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE PESQUISAR.png"))); // NOI18N
        btPesquisarProduto.setText("PESQUISAR");
        btPesquisarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarProdutoActionPerformed(evt);
            }
        });

        lbHora.setVisible(false);
        lbHora.setBackground(new java.awt.Color(255, 255, 255));
        lbHora.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbHora.setText("Hora da Venda:");
        lbHora.setEnabled(false);
        lbHora.setOpaque(true);

        edData.setVisible(false);
        edData.setEditable(false);
        try {
            edData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####/##/##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        edData.setEnabled(false);

        lbId.setVisible(false);
        lbId.setBackground(new java.awt.Color(255, 255, 255));
        lbId.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbId.setText("Id do Funcionário:");
        lbId.setEnabled(false);
        lbId.setOpaque(true);

        edFuncID.setVisible(false);
        edFuncID.setEditable(false);
        edFuncID.setText("AUTOMÁTICO");
        edFuncID.setEnabled(false);

        edHora.setVisible(false);
        edHora.setEditable(false);
        edHora.setText("AUTOMÁTICA");
        edHora.setEnabled(false);

        btAdicionarItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE ADICIONAR ITEM.png"))); // NOI18N
        btAdicionarItem.setText("ADICIONAR ITEM");
        btAdicionarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicionarItemActionPerformed(evt);
            }
        });

        labelFuncionario1.setText("[FUNCIONARIO]");

        jLabel13.setText("Funcionário:");

        lbData.setVisible(false);
        lbData.setBackground(new java.awt.Color(255, 255, 255));
        lbData.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbData.setText("Data da Venda:");
        lbData.setEnabled(false);
        lbData.setOpaque(true);

        tabelaServicosCarrinho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Descrição", "Tempo", "Preço", "Preço Unitário", "Quantidade"
            }
        ));
        jScrollPane5.setViewportView(tabelaServicosCarrinho);

        comboBoxFormaPag.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A Vista", "A Prazo" }));
        comboBoxFormaPag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxFormaPagActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setText("Carrinho do cliente:");

        btAlterarQtd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE ALTERAR QUANTIDADE.png"))); // NOI18N
        btAlterarQtd.setText("Alterar Quantidade");
        btAlterarQtd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterarQtdActionPerformed(evt);
            }
        });

        btDeletarItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE DELETAR.png"))); // NOI18N
        btDeletarItem.setText("Deletar Item");
        btDeletarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeletarItemActionPerformed(evt);
            }
        });

        edParcelas.setVisible(false);

        lbParcelas.setVisible(false);
        lbParcelas.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbParcelas.setText("Parcelas:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(484, 484, 484)
                        .addComponent(labelFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(labelId, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(232, 232, 232)
                        .addComponent(labelTitulo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbHora)
                                    .addComponent(lbId))
                                .addComponent(lbParcelas, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbValor, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(lbData))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(edParcelas)
                            .addComponent(edData)
                            .addComponent(edHora)
                            .addComponent(edFuncID)
                            .addComponent(edValor, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(comboBoxFormaPag, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edPesquisaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btPesquisarProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btAdicionarItem)
                                .addGap(151, 151, 151))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btCadastrar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btLimparCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(116, 116, 116)
                                .addComponent(btDeletarItem, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btAlterarQtd)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(edPesquisaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btPesquisarCliente))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(labelFuncionario1))
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addContainerGap(28, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelId, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(labelFuncionario1))
                        .addGap(13, 13, 13)
                        .addComponent(labelTitulo)))
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(edPesquisaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btPesquisarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btPesquisarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(edPesquisaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btAdicionarItem, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(comboBoxFormaPag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbParcelas)
                            .addComponent(edParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbValor)
                            .addComponent(edValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbData))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbHora)
                            .addComponent(edHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edFuncID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbId)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btAlterarQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btCadastrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btLimparCampos)
                            .addComponent(btDeletarItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(22, 22, 22))
        );

        jTabbedPane1.addTab("Cadastrar", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tabelaVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Valor", "Data", "Hora", "Forma Pagamento", "Cliente", "Funcionário"
            }
        ));
        jScrollPane1.setViewportView(tabelaVendas);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Pesquisar:");

        edPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edPesquisaActionPerformed(evt);
            }
        });

        btPesquisar.setToolTipText("Pesquisa nos nomes e nos cpf's");
        btPesquisar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE PESQUISAR.png"))); // NOI18N
        btPesquisar.setText("PESQUISAR");
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });

        btDeletar.setToolTipText("Deleta a venda selecionada.");
        btDeletar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE DELETAR.png"))); // NOI18N
        btDeletar.setText("Deletar");
        btDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeletarActionPerformed(evt);
            }
        });

        btAtualizar.setToolTipText("Atualiza a tabela.");
        btAtualizar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE ATUALIZAR.png"))); // NOI18N
        btAtualizar.setText("Atualizar");
        btAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizarActionPerformed(evt);
            }
        });

        btAtualizar.setToolTipText("Editar a venda selecionada.");
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
                .addContainerGap(111, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btDeletar)
                        .addGap(18, 18, 18)
                        .addComponent(btAtualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(271, 271, 271))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(edPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btPesquisar)))
                        .addGap(139, 139, 139))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(edPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        jTabbedPane1.addTab("Visualizar, Editar e Deletar", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarActionPerformed
        if((tabelaClientes.getRowSelectionAllowed()==true)&&(tabelaServicos.getRowSelectionAllowed()==true)){
            int[] linhasClientes = tabelaClientes.getSelectedRows();
            if(linhasClientes.length==1){
                DefaultTableModel modeloCliente = (DefaultTableModel) tabelaClientes.getModel();
                int id_cliente = Integer.parseInt(modeloCliente.getValueAt(linhasClientes[0], 0).toString());
                //PRIMEIRO A GENTE REALIZA A VENDA E DPS CONTA OS PRODUTOS
                Venda r = new Venda();
                VendaDAO rDAO = new VendaDAO();
                Usuario user = Usuario.getInstancia();
                //Pegando valores dos EDs
                r.setData(getData().toString());
                r.setFormaPag(String.valueOf(comboBoxFormaPag.getSelectedItem()));
                r.setHora(getHora());
                r.setId_cliente_fk(id_cliente);
                r.setId_funcionario_fk(user.getId());
                r.setValor(calcularValor());
                //Enviar para o DAO
                if(mode == 0){
                    rDAO.cadastrarVenda(r);
                    VendaServico pv = new VendaServico();
                    VendaServicoDAO pvDAO = new VendaServicoDAO();
                    DefaultTableModel modelo = (DefaultTableModel) tabelaServicosCarrinho.getModel();
                    int[] linhas = tabelaServicosCarrinho.getSelectedRows();
                    for(int i=0;i<linhas.length;i++){
                        pv.setId_servico_fk(Integer.parseInt(modelo.getValueAt(linhas[i], 0).toString()));
                        pv.setQuant(Integer.parseInt(modelo.getValueAt(linhas[i], 5).toString()));
                        pv.setValorUnit(Integer.parseInt(modelo.getValueAt(linhas[i], 4).toString()));
                        pv.setId_venda_fk(pvDAO.retornarUltimoId());
                        pvDAO.cadastrarVendaServico(pv);
                        gerarTabelaVendas();
                    }
                }else{
                    r.setId_venda(id_edit);
                    rDAO.editarPorID(r);
                    gerarTabelaVendas();
                    trocarModo(r);
                }
                limparCampos();
            }else{
                JOptionPane.showMessageDialog(null, "Por favor, selecione um único cliente","Importante",1);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Por favor, selecione o(s) serviço(s) e cliente(s)","Importante",1);
        }
    }//GEN-LAST:event_btCadastrarActionPerformed

    private void btLimparCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparCamposActionPerformed
        limparCampos();
    }//GEN-LAST:event_btLimparCamposActionPerformed

    private void btPesquisarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarClienteActionPerformed
        gerarTabelaClientes_com_Consulta();
    }//GEN-LAST:event_btPesquisarClienteActionPerformed

    private void btPesquisarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarProdutoActionPerformed
        gerarTabelaServicos_com_Consulta();
    }//GEN-LAST:event_btPesquisarProdutoActionPerformed

    private void btAdicionarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionarItemActionPerformed
        if(tabelaServicos.getRowSelectionAllowed()==true){
            DefaultTableModel modelo = (DefaultTableModel) tabelaServicos.getModel();
            int[] linhas = tabelaServicos.getSelectedRows();
            if(linhas.length==1){
                int id = Integer.parseInt(modelo.getValueAt(linhas[0], 0).toString());
                int qtd = Integer.parseInt(JOptionPane.showInputDialog("Quantidade do produto adicionado: ","Digite a quantidade"));
                adicionarItem(id,qtd);
            }else{
                JOptionPane.showMessageDialog(null, "Por favor, só selecione um serviço por vez","Importante",1);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Por favor, só selecione um serviço por vez","Importante",1);
        }
    }//GEN-LAST:event_btAdicionarItemActionPerformed

    private void comboBoxFormaPagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxFormaPagActionPerformed
        if(comboBoxFormaPag.getSelectedIndex()==1){
            edParcelas.setVisible(true);
            lbParcelas.setVisible(true);
        }else{
            edParcelas.setVisible(false);
            lbParcelas.setVisible(false);
        }
    }//GEN-LAST:event_comboBoxFormaPagActionPerformed

    private void btAlterarQtdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarQtdActionPerformed
        if(tabelaServicosCarrinho.getRowSelectionAllowed()==true){
            DefaultTableModel modelo = (DefaultTableModel) tabelaServicosCarrinho.getModel();
            int[] linhas = tabelaServicosCarrinho.getSelectedRows();
            if(linhas.length==1){
                int qtd = Integer.parseInt(JOptionPane.showInputDialog("Quantidade do produto adicionado: ","Digite a quantidade"));
                modelo.setValueAt(qtd, linhas[0], 5);
            }else{
                JOptionPane.showMessageDialog(null, "Por favor, só selecione um serviço por vez","Importante",1);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Por favor, só selecione um serviço por vez","Importante",1);
        }
    }//GEN-LAST:event_btAlterarQtdActionPerformed

    private void btDeletarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeletarItemActionPerformed
        if(tabelaServicosCarrinho.getRowSelectionAllowed()==true){
            DefaultTableModel modelo = (DefaultTableModel) tabelaServicosCarrinho.getModel();
            int[] linhas = tabelaServicosCarrinho.getSelectedRows();
            if(linhas.length==1){
                modelo.removeRow(linhas[0]);
            }else{
                JOptionPane.showMessageDialog(null, "Por favor, Selecione um único serviço","Importante",1);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Por favor, selecione um produto para deletar","Importante",1);
        }
    }//GEN-LAST:event_btDeletarItemActionPerformed

    private void edPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edPesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edPesquisaActionPerformed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        gerarTabelaVendas_com_Consulta();
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void btDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeletarActionPerformed
        if(tabelaVendas.getRowSelectionAllowed()==true){
            VendaDAO pDAO = new VendaDAO();
            DefaultTableModel modelo = (DefaultTableModel) tabelaVendas.getModel();
            int[] linhas = tabelaVendas.getSelectedRows();
            for(int i=0;i<linhas.length;i++){
                int id = Integer.parseInt(modelo.getValueAt(linhas[i], 0).toString());
                pDAO.deletar(id);
                gerarTabelaVendas();
            }
        }else{
            m.mensagemPadrão2();
        }
    }//GEN-LAST:event_btDeletarActionPerformed

    private void btAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizarActionPerformed
        gerarTabelaVendas();
    }//GEN-LAST:event_btAtualizarActionPerformed

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        if(tabelaVendas.getRowSelectionAllowed()==true){
            DefaultTableModel modelo = (DefaultTableModel) tabelaVendas.getModel();
            int[] linhas = tabelaVendas.getSelectedRows();
            if(linhas.length==1){
                int id = Integer.parseInt(modelo.getValueAt(linhas[0], 0).toString());
                VendaDAO pDAO = new VendaDAO();
                Venda p = new Venda();
                List<Venda> Lista = pDAO.listarTodos();
                for(int i=0;i<Lista.size();i++){
                    p = Lista.get(i);
                    if(p.getId_venda()==id){
                        id_edit = i;
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdicionarItem;
    private javax.swing.JButton btAlterarQtd;
    private javax.swing.JButton btAtualizar;
    private javax.swing.JButton btCadastrar;
    private javax.swing.JButton btDeletar;
    private javax.swing.JButton btDeletarItem;
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btLimparCampos;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JButton btPesquisarCliente;
    private javax.swing.JButton btPesquisarProduto;
    private javax.swing.JComboBox<String> comboBoxFormaPag;
    private javax.swing.JFormattedTextField edData;
    private javax.swing.JTextField edFuncID;
    private javax.swing.JTextField edHora;
    private javax.swing.JTextField edParcelas;
    private javax.swing.JTextField edPesquisa;
    private javax.swing.JTextField edPesquisaCliente;
    private javax.swing.JTextField edPesquisaProduto;
    private javax.swing.JTextField edValor;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelFuncionario;
    private javax.swing.JLabel labelFuncionario1;
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel lbData;
    private javax.swing.JLabel lbHora;
    private javax.swing.JLabel lbId;
    private javax.swing.JLabel lbParcelas;
    private javax.swing.JLabel lbValor;
    private javax.swing.JTable tabelaClientes;
    private javax.swing.JTable tabelaServicos;
    private javax.swing.JTable tabelaServicosCarrinho;
    private javax.swing.JTable tabelaVendas;
    // End of variables declaration//GEN-END:variables
}
