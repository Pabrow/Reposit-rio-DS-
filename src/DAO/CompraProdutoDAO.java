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
public class CompraProdutoDAO {
    private Connection con = null;
    Mensagens m = new Mensagens();
    
    
    public void cadastrarCompraProduto(Compra p){
        con = ConexaoSQL.conectar();
        String sql = "INSERT INTO compra_Prod (id_compra_fk, id_produto_fk, quant_compProd, valorUnit_compProd) values (?, ?, ?, ?);";
        try( PreparedStatement stm =con.prepareStatement(sql)){   
          stm.setInt(1, p.getId_compra());
          stm.setInt(2, p.getId_produto_fk());
          stm.setInt(3, p.getQuantItens());
          stm.setDouble(4, p.getValorUnit());
          stm.execute();
          stm.close();
        } catch (Exception e) {
            m.mensagemErro("ERRO:"+e.getMessage());
        }   
    }
    /*
    public void editarPorID(Compra p){
        con = ConexaoSQL.conectar();
        String sql = "update Venda_Prod set id_venda_fk =?, valorUnit_produto =?, id_produto_fk =?, quant_vendaprod = ? where id_vendaProd=?;";
         try( PreparedStatement stm =con.prepareStatement(sql)){     
            stm.setInt(2, p.getId_venda_fk());
            stm.setDouble(1, p.getValorUnit());
            stm.setInt(3, p.getId_produto_fk());
            stm.setInt(4, p.getQuant());
            stm.setInt(5, p.getId_vendaProduto());
            stm.execute();
            stm.close();
            m.mensagemInformacao("EDITADO COM SUCESSO");
        } catch (Exception e) {
            m.mensagemErro("ERRO AO EDITAR"+e.getMessage());
        }
    }
    
    public void deletar(int id){
        con = ConexaoSQL.conectar();
        String sql = "delete from Venda_Prod where id_vendaProd=?";
        int opcao = JOptionPane.showConfirmDialog(null, "EXCLUIR A VENDA DO PRODUTO NO ID: "+id, "?", JOptionPane.YES_NO_OPTION);
        if(opcao==0){
            try( PreparedStatement stm =con.prepareStatement(sql)){
                stm.setInt(1,id);
                stm.execute(); 
                stm.close();
                m.mensagemInformacao("DELETADO COM SUCESSO");
            }catch (Exception e){
                throw new RuntimeException(e.getMessage());
            }   
        }else{
            m.mensagemWarning("Operação cancelada");
        }
    }
    
    public List<VendaProduto> listarTodos() {
        con = ConexaoSQL.conectar();
        List<VendaProduto> Lista = new ArrayList<>();
        String sql = "Select * from Venda_Prod";
        try (PreparedStatement stm =con.prepareStatement(sql)){
            ResultSet Resultado  = stm.executeQuery();
            while(Resultado.next()){
                VendaProduto p = new VendaProduto();
                p.setId_produto_fk(Resultado.getInt("id_produto_fk"));
                p.setId_vendaProduto(Resultado.getInt("id_vendaProd"));
                p.setId_venda_fk(Resultado.getInt("id_venda_fk"));
                p.setQuant(Resultado.getInt("quant_vendaprod"));
                p.setValorUnit(Resultado.getDouble("valorUnit_produto"));
                Lista.add(p);
            }
            stm.close();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Lista;
    }
    */
    public int retornarUltimoId() {
        con = ConexaoSQL.conectar();
        int id = 0;
        String sql = "Select MAX(id_compra) from compra";
        try (PreparedStatement stm =con.prepareStatement(sql)){
            ResultSet Resultado  = stm.executeQuery();
            while(Resultado.next()){
                id = (Resultado.getInt("MAX(id_compra)"));
            }
            stm.close();
            con.close();
        } catch (Exception e) {
        }
        return id;
    }
    
}
