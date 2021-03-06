package DAO;
import Conexao.ConexaoSQL;
import Objetos.Compra;
import Objetos.Mensagens;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class CompraDAO {
    private Connection con = null;
    Mensagens m = new Mensagens();
    
    
    public void cadastrarCompra(Compra p){
        con = ConexaoSQL.conectar();
        String sql = "INSERT INTO Compra (data_compra, valor_compra, formaPag_compra, parcelas_compra, id_fornecedor_fk, id_funcionario_fk) values ( ?, ?, ?, ?, ?, ?);";
        try( PreparedStatement stm =con.prepareStatement(sql)){   
          stm.setString(1, p.getData());
          stm.setDouble(2, p.getValor());
          stm.setString(3, p.getFormaPag());
          stm.setInt(4, p.getParcelas());
          stm.setInt(5, p.getId_fornecedor_fk());
          stm.setInt(6, p.getId_funcionario_fk());
          stm.execute();
          stm.close();
          m.mensagemInformacao("CADASTRADO COM SUCESSO");
          cadastrarCompraProduto(p);
        } catch (Exception e) {
            m.mensagemErro("ERRO AO CADASTRAR"+e.getMessage());
        }   
    }
    
    public void cadastrarCompraProduto(Compra p){
        con = ConexaoSQL.conectar();
        String sql = "INSERT INTO Compra_prod values(null, ?, ?, ?, ?);";
        try( PreparedStatement stm =con.prepareStatement(sql)){   
          stm.setInt(1, p.getId_compra());
          stm.setInt(2, p.getId_produto_fk());
          stm.setInt(3, p.getQuantItens());
          stm.setDouble(4, p.getValorUnit());
          stm.execute();
          stm.close();
        } catch (Exception e) {
        }   
    }
    
    public void editarPorID(Compra p){
        con = ConexaoSQL.conectar();
        String sql = "update compra set data_compra =?, valor_compra =?, quantItens_compra =?, formaPag_compra =?, id_fornecedor_fk =? where id_compra=?;";
         try( PreparedStatement stm =con.prepareStatement(sql)){   
            stm.setString(1, p.getData());
            stm.setDouble(2, p.getValor());
            stm.setInt(3, p.getQuantItens());
            stm.setString(4, p.getFormaPag());
            stm.setInt(5, p.getId_fornecedor_fk());
            stm.setInt(6, p.getId_compra());
            stm.execute();
            m.mensagemInformacao("EDITADO COM SUCESSO");
            stm.close();
        } catch (Exception e) {
            m.mensagemErro("ERRO AO EDITAR"+e.getMessage());
        }
    }
    
    public void deletar(int id){
        con = ConexaoSQL.conectar();
        String sql = "delete from compra where id_compra=?";
        int opcao = JOptionPane.showConfirmDialog(null, "EXCLUIR A COMPRA NO ID: "+id, "?", JOptionPane.YES_NO_OPTION);
        if(opcao==0){
            try( PreparedStatement stm =con.prepareStatement(sql)){
                stm.setInt(1,id);
                stm.execute(); 
                stm.close();
                m.mensagemInformacao("DELETADO COM SUCESSO");
                deletarCompraProduto(id);
            }catch (Exception e){
                throw new RuntimeException(e.getMessage());
            }   
        }else{
            m.mensagemWarning("Operação cancelada");
        }
    }
    
    public void deletarCompraProduto(int id){
        con = ConexaoSQL.conectar();
        String sql = "delete from compra_prod where id_compra_fk=?";
            try( PreparedStatement stm =con.prepareStatement(sql)){
                stm.setInt(1,id);
                stm.execute(); 
                stm.close();
            }catch (Exception e){
                throw new RuntimeException(e.getMessage());
            } 
    }
    
    public List<Compra> listarTodos() {
        con = ConexaoSQL.conectar();
        List<Compra> Lista = new ArrayList<>();
        String sql = "Select * from Compra INNER JOIN Compra_prod ON compra.id_compra = id_compra_fk";
        try (PreparedStatement stm =con.prepareStatement(sql)){
            ResultSet Resultado  = stm.executeQuery();
            while(Resultado.next()){
                Compra p = new Compra();
                p.setId_compra(Resultado.getInt("compra.id_compra"));
                p.setData(Resultado.getString("compra.data_compra"));
                p.setValor(Resultado.getDouble("compra.valor_compra"));
                p.setFormaPag(Resultado.getString("compra.formaPag_compra"));
                p.setId_fornecedor_fk(Resultado.getInt("compra.id_fornecedor_fk"));
                Lista.add(p);
            }
            stm.close();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Lista;
    }
}
