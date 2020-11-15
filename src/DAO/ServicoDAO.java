package DAO;
import Conexao.ConexaoSQL;
import Objetos.Mensagens;
import Objetos.Servico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class ServicoDAO {
    private Connection con = null;
    Mensagens m = new Mensagens();
    
    
    public void cadastrarServico(Servico p){
        con = ConexaoSQL.conectar();
        String sql = "INSERT INTO Servico (desc_serv, valor_serv, tempo_serv, id_funcionario_fk) values (?, ?, ?, ?);";
        try( PreparedStatement stm =con.prepareStatement(sql)){  
          stm.setString(1, p.getDesc());
          stm.setDouble(2, p.getValor()); 
          stm.setString(3, p.getTempo());
          stm.setInt(4, p.getId_funcionario_fk());
          stm.execute();
          stm.close();
          m.mensagemInformacao("CADASTRADO COM SUCESSO");
        } catch (Exception e) {
            m.mensagemErro("ERRO AO CADASTRAR"+e.getMessage());
        }   
    }
    
    public void editarPorID(Servico p){
        con = ConexaoSQL.conectar();
        String sql = "update servico set desc_serv =?, valor_serv =?, tempo_serv =?, id_funcionario_fk =? where id_serv=?;";
         try( PreparedStatement stm =con.prepareStatement(sql)){     
            stm.setString(1, p.getDesc());
            stm.setDouble(2, p.getValor());
            stm.setString(3, p.getTempo());
            stm.setInt(4, p.getId_funcionario_fk());
            stm.setInt(5, p.getId_servico());
            stm.execute();
            stm.close();
            m.mensagemInformacao("EDITADO COM SUCESSO");
        } catch (Exception e) {
            m.mensagemErro("ERRO AO EDITAR"+e.getMessage());
        }
    }
    
    public void deletar(int id){
        con = ConexaoSQL.conectar();
        String sql = "delete from servico where id_serv=?";
        int opcao = JOptionPane.showConfirmDialog(null, "EXCLUIR O SERVIÇO NO ID: "+id, "?", JOptionPane.YES_NO_OPTION);
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
    
    public List<Servico> listarTodos() {
        con = ConexaoSQL.conectar();
        List<Servico> Lista = new ArrayList<>();
        String sql = "Select * from Servico";
        try (PreparedStatement stm =con.prepareStatement(sql)){
            ResultSet Resultado  = stm.executeQuery();
            while(Resultado.next()){
                Servico p = new Servico();
                p.setId_servico(Resultado.getInt("id_serv"));
                p.setValor(Resultado.getDouble("valor_serv"));
                p.setDesc(Resultado.getString("desc_serv"));
                p.setTempo(Resultado.getString("tempo_serv"));
                p.setId_funcionario_fk(Resultado.getInt("id_funcionario_fk"));
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
