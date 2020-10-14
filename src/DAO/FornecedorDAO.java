package DAO;
import Conexao.ConexaoSQL;
import Objetos.Fornecedor;
import Objetos.Mensagens;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class FornecedorDAO {
    private Connection con = null;
    Mensagens m = new Mensagens();
    
    
    public void cadastrarFornecedor(Fornecedor p){
        con = ConexaoSQL.conectar();
        String sql = "INSERT INTO Fornecedor (nome_forn, cnpj_forn, end_forn, tel_forn) values (?, ?, ?, ?);";
        try( PreparedStatement stm =con.prepareStatement(sql)){   
          stm.setString(1, p.getNome());
          stm.setString(2, p.getCnpj());
          stm.setString(3, p.getEnd());
          stm.setString(4, p.getTel());
          stm.execute();
          stm.close();
          m.mensagemInformacao("CADASTRADO COM SUCESSO");
        } catch (Exception e) {
            m.mensagemErro("ERRO AO CADASTRAR"+e.getMessage());
        }   
    }
    
    public void editarPorID(Fornecedor p){
        con = ConexaoSQL.conectar();
        String sql = "update Fornecedor set nome_forn =?, cnpj_forn =?, end_forn =?, tel_forn =? where id_forn=?;";
         try( PreparedStatement stm =con.prepareStatement(sql)){   
            stm.setString(1, p.getNome());
            stm.setString(2, p.getCnpj());
            stm.setString(3, p.getEnd());
            stm.setString(4, p.getTel());
            stm.setInt(5, p.getId_fornecedor());
            stm.execute();
            stm.close();
            m.mensagemInformacao("EDITADO COM SUCESSO");
        } catch (Exception e) {
           m.mensagemErro("ERRO AO EDITAR"+e.getMessage());
        }
    }
    
    public void deletar(int id){
        con = ConexaoSQL.conectar();
        String sql = "delete from fornecedor where id_forn=?";
        int opcao = JOptionPane.showConfirmDialog(null, "EXCLUIR O FORNECEDOR NO ID: "+id, "?", JOptionPane.YES_NO_OPTION);
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
            m.mensagemErro("Operação cancelada");
        }
    }
    
    public List<Fornecedor> listarTodos() {
        con = ConexaoSQL.conectar();
        List<Fornecedor> Lista = new ArrayList<>();
        String sql = "Select * from Fornecedor";
        try (PreparedStatement stm =con.prepareStatement(sql)){
            ResultSet Resultado  = stm.executeQuery();
            while(Resultado.next()){
                Fornecedor p = new Fornecedor();
                p.setId_fornecedor(Resultado.getInt("id_forn"));
                p.setNome(Resultado.getString("nome_forn"));
                p.setCnpj(Resultado.getString("cnpj_forn"));
                p.setEnd(Resultado.getString("end_forn"));
                p.setTel(Resultado.getString("tel_forn"));
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
