/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import DAO.ClienteDAO;
import DAO.FuncionarioDAO;
import DAO.ServicoDAO;
import DAO.VendaDAO;
import DAO.VendaServicoDAO;
import Objetos.Cliente;
import Objetos.Funcionario;
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
        if(edParcelas.getText().trim().replaceAll(" ","").equals("")){
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
    
    public void adicionarItem(int id, int qtd){
        DefaultTableModel modelo = (DefaultTableModel) tabelaServicosCarrinho.getModel();
        ServicoDAO pDAO = new ServicoDAO();
        System.out.println(qtd);
        List<Servico> Lista = pDAO.listarTodos();
        for(Servico p: Lista){     
            if(id == p.getId_servico()){
                modelo.addRow(new Object[]{p.getId_servico(),p.getDesc(),p.getTempo(),p.getValor(), qtd});
                setValor();
            }
        }
    }
    
    public void gerarTabelaVendas(){
        DefaultTableModel modelo = (DefaultTableModel) tabelaVendas.getModel();
        modelo.setNumRows(0);
        VendaDAO pDAO = new VendaDAO();
        FuncionarioDAO fDAO = new FuncionarioDAO();
        List<Funcionario> ListaF = fDAO.listarTodos();
        List<Venda> Lista = pDAO.listarTodos();
        String str = null;
        for(Venda p: Lista){            
            for(Funcionario f: ListaF){     
                if(f.getId_funcionario()==p.getId_funcionario_fk()){
                    str = f.getNome();
                }
            }
            modelo.addRow(new Object[]{p.getId_venda(),p.getValor(),p.getData(),p.getHora(),p.getFormaPag(),p.getId_cliente_fk(),str});
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
        FuncionarioDAO fDAO = new FuncionarioDAO();
        List<Funcionario> ListaF = fDAO.listarTodos();
        List<Venda> Lista = pDAO.listarTodos();
        String str = null;
        for(Venda p: Lista){              
            for(Funcionario f: ListaF){     
                if(f.getId_funcionario()==p.getId_funcionario_fk()){
                    str = f.getNome();
                }
            }
            if(((p.getData().toLowerCase()).contains(edPesquisa.getText().toLowerCase()))){
                modelo.addRow(new Object[]{p.getId_venda(),p.getValor(),p.getData(),p.getHora(),p.getFormaPag(),p.getId_cliente_fk(),str});
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
    
    public void limparCampos(){
        edValor.setText(null);
        edData.setText(null);
        edPesquisa.setText(null);
        edPesquisaProduto.setText(null);
        edPesquisaCliente.setText(null);
        gerarData();
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
        int linhas = tabelaServicosCarrinho.getRowCount();
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
    
    public void trocarModo(Venda p){
        if(mode == 0){
            mode = 1;
            labelTitulo.setText("Editar Venda");
            btCadastrar.setText("Editar Venda");
            btCancelarEdicao.setVisible(true);
            labelId.setText("Id:"+p.getId_venda());
            //Pegando valores dos EDs
            edValor.setText(String.valueOf(p.getValor()));
            edData.setText(p.getData().replaceAll("-", ""));
            edHora.setText(p.getHora());
            edFuncID.setText(String.valueOf(p.getId_funcionario_fk()));
            edFuncID.setVisible(true);
            edHora.setVisible(true);
            edData.setVisible(true);
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
            edHora.setEditable(false);
            edData.setEditable(false);
            labelTitulo.setText("Realizar Venda");
            btCadastrar.setText("Realizar Venda");
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
        jLabel4 = new javax.swing.JLabel();
        lbValor = new javax.swing.JLabel();
        edValor = new javax.swing.JTextField();
        btCadastrar = new javax.swing.JButton();
        btLimparCampos = new javax.swing.JButton();
        labelId = new javax.swing.JLabel();
        labelFuncionario = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbvalor = new javax.swing.JLabel();
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
        edHora = new javax.swing.JTextField();
        btAdicionarItem = new javax.swing.JButton();
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
        edFuncID = new javax.swing.JLabel();
        btAtualizar1 = new javax.swing.JButton();
        btAtualizar2 = new javax.swing.JButton();
        btCancelarEdicao = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaVendas = new javax.swing.JTable();
        edPesquisa = new javax.swing.JTextField();
        btPesquisar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        btDeletar = new javax.swing.JButton();
        btAtualizar = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelTitulo.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitulo.setText("Realizar Venda");
        add(labelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 1390, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setText("Forma Pagamento:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 560, -1, -1));

        lbValor.setVisible(false);
        lbValor.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbValor.setText("Valor da Venda:");
        lbValor.setEnabled(false);
        add(lbValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 620, -1, -1));

        edValor.setVisible(false);
        edValor.setEditable(false);
        edValor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        edValor.setEnabled(false);
        add(edValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 620, 220, -1));

        btCadastrar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE REALIZAR VENDA.png"))); // NOI18N
        btCadastrar.setText("Realizar Venda");
        btCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrarActionPerformed(evt);
            }
        });
        add(btCadastrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 820, -1, -1));

        btLimparCampos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btLimparCampos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE LIMPAR CAMPOS.png"))); // NOI18N
        btLimparCampos.setText("Limpar Campos");
        btLimparCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimparCamposActionPerformed(evt);
            }
        });
        add(btLimparCampos, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 770, 170, -1));

        labelId.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        add(labelId, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 45, 43, 13));
        add(labelFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 10, 100, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Serviços:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        lbvalor.setText("R$ 0,00");
        add(lbvalor, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 750, 110, 20));

        tabelaServicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Descrição", "Tempo", "Preço"
            }
        ));
        jScrollPane2.setViewportView(tabelaServicos);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 420, 330));

        tabelaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nome", "RG", "CPF"
            }
        ));
        jScrollPane4.setViewportView(tabelaClientes);

        add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, 413, 330));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Cliente:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, -1, -1));

        edPesquisaProduto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add(edPesquisaProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 190, -1));

        edPesquisaCliente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add(edPesquisaCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, 200, -1));

        btPesquisarCliente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btPesquisarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE PESQUISAR.png"))); // NOI18N
        btPesquisarCliente.setText("PESQUISAR");
        btPesquisarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarClienteActionPerformed(evt);
            }
        });
        add(btPesquisarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 100, 130, 23));

        btPesquisarProduto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btPesquisarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE PESQUISAR.png"))); // NOI18N
        btPesquisarProduto.setText("PESQUISAR");
        btPesquisarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarProdutoActionPerformed(evt);
            }
        });
        add(btPesquisarProduto, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 130, 23));

        lbHora.setVisible(false);
        lbHora.setBackground(new java.awt.Color(255, 255, 255));
        lbHora.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbHora.setText("Hora da Venda:");
        lbHora.setEnabled(false);
        add(lbHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 690, -1, -1));

        edData.setVisible(false);
        edData.setEditable(false);
        try {
            edData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####/##/##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        edData.setEnabled(false);
        add(edData, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 650, 220, -1));

        lbId.setVisible(false);
        lbId.setBackground(new java.awt.Color(255, 255, 255));
        lbId.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbId.setText("Id do Funcionário:");
        lbId.setEnabled(false);
        add(lbId, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 720, -1, -1));

        edHora.setVisible(false);
        edHora.setEditable(false);
        edHora.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        edHora.setText("AUTOMÁTICA");
        edHora.setEnabled(false);
        add(edHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 690, 210, -1));

        btAdicionarItem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btAdicionarItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE ADICIONAR ITEM.png"))); // NOI18N
        btAdicionarItem.setText("ADICIONAR ITEM");
        btAdicionarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicionarItemActionPerformed(evt);
            }
        });
        add(btAdicionarItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 480, -1, 40));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Funcionário:");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 10, -1, -1));

        lbData.setVisible(false);
        lbData.setBackground(new java.awt.Color(255, 255, 255));
        lbData.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbData.setText("Data da Venda:");
        lbData.setEnabled(false);
        add(lbData, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 650, -1, -1));

        tabelaServicosCarrinho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Descrição", "Tempo", "Preço Unitário", "Quantidade"
            }
        ));
        jScrollPane5.setViewportView(tabelaServicosCarrinho);

        add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 580, 413, 330));

        comboBoxFormaPag.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A Vista", "A Prazo" }));
        comboBoxFormaPag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxFormaPagActionPerformed(evt);
            }
        });
        add(comboBoxFormaPag, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 560, 180, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Carrinho do cliente:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 550, -1, -1));

        btAlterarQtd.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btAlterarQtd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE ALTERAR QUANTIDADE.png"))); // NOI18N
        btAlterarQtd.setText("Alterar Quantidade");
        btAlterarQtd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterarQtdActionPerformed(evt);
            }
        });
        add(btAlterarQtd, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 920, -1, 30));

        btDeletarItem.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btDeletarItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE DELETAR.png"))); // NOI18N
        btDeletarItem.setText("Deletar Item");
        btDeletarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeletarItemActionPerformed(evt);
            }
        });
        add(btDeletarItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 920, 161, 30));

        edParcelas.setVisible(false);
        edParcelas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add(edParcelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 590, 220, -1));

        lbParcelas.setVisible(false);
        lbParcelas.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbParcelas.setText("Parcelas:");
        add(lbParcelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 590, -1, -1));

        edFuncID.setVisible(false);
        add(edFuncID, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 720, 90, 17));

        btAtualizar.setToolTipText("Atualiza a tabela.");
        btAtualizar1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btAtualizar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE ATUALIZAR.png"))); // NOI18N
        btAtualizar1.setText("Atualizar");
        btAtualizar1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btAtualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizar1ActionPerformed(evt);
            }
        });
        add(btAtualizar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 480, 150, 40));

        btAtualizar.setToolTipText("Atualiza a tabela.");
        btAtualizar2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btAtualizar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE ATUALIZAR.png"))); // NOI18N
        btAtualizar2.setText("Atualizar");
        btAtualizar2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btAtualizar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizar2ActionPerformed(evt);
            }
        });
        add(btAtualizar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 480, 140, 40));

        btCancelarEdicao.setVisible(false);
        btCancelarEdicao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btCancelarEdicao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE DELETAR.png"))); // NOI18N
        btCancelarEdicao.setText("Cancelar Edição");
        btCancelarEdicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarEdicaoActionPerformed(evt);
            }
        });
        add(btCancelarEdicao, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 770, 160, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Valor Atual da Venda: ");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 750, -1, -1));

        tabelaVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Valor", "Data", "Hora", "Forma Pagamento", "Cliente", "Funcionário"
            }
        ));
        jScrollPane1.setViewportView(tabelaVendas);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 140, 470, 330));

        edPesquisa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        edPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edPesquisaActionPerformed(evt);
            }
        });
        add(edPesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 100, 210, -1));

        btPesquisar.setToolTipText("Pesquisa nos nomes e nos cpf's");
        btPesquisar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE PESQUISAR.png"))); // NOI18N
        btPesquisar.setText("PESQUISAR");
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });
        add(btPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 100, 130, 24));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Pesquisar:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 100, -1, -1));

        btDeletar.setToolTipText("Deleta a venda selecionada.");
        btDeletar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE DELETAR.png"))); // NOI18N
        btDeletar.setText("Deletar");
        btDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeletarActionPerformed(evt);
            }
        });
        add(btDeletar, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 480, 120, 41));

        btAtualizar.setToolTipText("Atualiza a tabela.");
        btAtualizar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE ATUALIZAR.png"))); // NOI18N
        btAtualizar.setText("Atualizar");
        btAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizarActionPerformed(evt);
            }
        });
        add(btAtualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 480, -1, 41));

        btAtualizar.setToolTipText("Editar a venda selecionada.");
        btEditar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONE EDITAR.png"))); // NOI18N
        btEditar.setText("Editar");
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });
        add(btEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 480, 122, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/projeto menu.png"))); // NOI18N
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 1400, 980));
    }// </editor-fold>//GEN-END:initComponents

    private void btCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarActionPerformed
        if(camposPreenchidos()==true){
        if((tabelaClientes.getRowCount()>0)&&(tabelaServicos.getRowCount()>0)){
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
                    int linhas = tabelaServicosCarrinho.getRowCount();
                    System.out.println(linhas);
                    if(linhas==1){
                        pv.setId_servico_fk(Integer.parseInt(modelo.getValueAt(0, 0).toString()));
                        pv.setQuant(Integer.parseInt(String.valueOf(modelo.getValueAt(0, 4))));
                        pv.setValorUnit(Double.parseDouble(modelo.getValueAt(0, 3).toString()));
                        pv.setId_venda_fk(pvDAO.retornarUltimoId());
                        pvDAO.cadastrarVendaServico(pv);
                        gerarTabelaVendas();
                    }else{
                        for(int i=0;i<linhas;i++){
                            pv.setId_servico_fk(Integer.parseInt(modelo.getValueAt(i, 0).toString()));
                            pv.setQuant(Integer.parseInt(modelo.getValueAt(i, 4).toString()));
                            pv.setValorUnit(Double.parseDouble(modelo.getValueAt(i, 3).toString()));
                            pv.setId_venda_fk(pvDAO.retornarUltimoId());
                            pvDAO.cadastrarVendaServico(pv);
                            gerarTabelaVendas();
                        }
                    }
                }else{
                    r.setId_venda(id_edit);
                    rDAO.editarPorID(r);
                    gerarTabelaVendas();
                    trocarModo(r);
                    zerarValor();
                }
                limparCampos();
            }else{
                JOptionPane.showMessageDialog(null, "Por favor, selecione um único cliente","Importante",1);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Por favor, selecione o(s) serviço(s) e cliente(s)","Importante",1);
        }
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
                setValor();
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
                setValor();
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

    private void btAtualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizar1ActionPerformed
        gerarTabelaClientes();
    }//GEN-LAST:event_btAtualizar1ActionPerformed

    private void btAtualizar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizar2ActionPerformed
        gerarTabelaServicos();
    }//GEN-LAST:event_btAtualizar2ActionPerformed

    private void btCancelarEdicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarEdicaoActionPerformed
        Venda p = new Venda();
        trocarModo(p);
    }//GEN-LAST:event_btCancelarEdicaoActionPerformed


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
    private javax.swing.JButton btPesquisarCliente;
    private javax.swing.JButton btPesquisarProduto;
    private javax.swing.JComboBox<String> comboBoxFormaPag;
    private javax.swing.JFormattedTextField edData;
    private javax.swing.JLabel edFuncID;
    private javax.swing.JTextField edHora;
    private javax.swing.JTextField edParcelas;
    private javax.swing.JTextField edPesquisa;
    private javax.swing.JTextField edPesquisaCliente;
    private javax.swing.JTextField edPesquisaProduto;
    private javax.swing.JTextField edValor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel labelFuncionario;
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel lbData;
    private javax.swing.JLabel lbHora;
    private javax.swing.JLabel lbId;
    private javax.swing.JLabel lbParcelas;
    private javax.swing.JLabel lbValor;
    private javax.swing.JLabel lbvalor;
    private javax.swing.JTable tabelaClientes;
    private javax.swing.JTable tabelaServicos;
    private javax.swing.JTable tabelaServicosCarrinho;
    private javax.swing.JTable tabelaVendas;
    // End of variables declaration//GEN-END:variables
}
