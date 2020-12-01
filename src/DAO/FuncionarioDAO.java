package DAO;
import Conexao.ConexaoSQL;
import Objetos.Funcionario;
import Objetos.Mensagens;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class FuncionarioDAO {
    private Connection con = null;
    Mensagens m = new Mensagens();
    
    
    public void cadastrarFuncionario(Funcionario p){
        con = ConexaoSQL.conectar();
        String sql = "INSERT INTO Funcionario (nome_func, cpf_func, senha_func, rg_func, tel_func, email_func, sexo_func, salario_func, funcao_func) values (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try( PreparedStatement stm =con.prepareStatement(sql)){   
          stm.setString(1, p.getNome());
          stm.setString(2, p.getCpf());
          stm.setString(3, p.getSenha());
          stm.setString(4, p.getRg());
          stm.setString(5, p.getTelefone());
          stm.setString(6, p.getEmail());
          stm.setString(7, p.getSexo());
          stm.setDouble(8, p.getSalario());
          stm.setString(9, p.getFuncao());
          stm.execute();
          stm.close();
          m.mensagemInformacao("CADASTRADO COM SUCESSO");
        } catch (Exception e) {
            m.mensagemErro("ERRO AO CADASTRAR"+e.getMessage());
        }   
    }
    
    public void editarPorID(Funcionario p){
        con = ConexaoSQL.conectar();
        String sql = "update funcionario set nome_func =?, cpf_func =?, senha_func =?,  rg_func =?, tel_func =?, email_func =?, sexo_func = ?, salario_func = ?, funcao_func =? where id_func=?;";
         try( PreparedStatement stm =con.prepareStatement(sql)){   
            stm.setString(1, p.getNome());
            stm.setString(2, p.getCpf());
            stm.setString(3, p.getSenha());
            stm.setString(4, p.getRg());
            stm.setString(5, p.getTelefone());
            stm.setString(6, p.getEmail());
            stm.setString(7, p.getSexo());
            stm.setDouble(8, p.getSalario());
            stm.setString(9, p.getFuncao());
            stm.setInt(10, p.getId_funcionario());
            stm.execute();
            m.mensagemInformacao("EDITADO COM SUCESSO");
            stm.close();
        } catch (Exception e) {
            m.mensagemErro("ERRO AO EDITAR"+e.getMessage());
        }
    }
    
    public void deletar(int id){
        con = ConexaoSQL.conectar();
        String sql = "delete from funcionario where id_func=?";
        int opcao = JOptionPane.showConfirmDialog(null, "EXCLUIR O CLIENTE NO ID: "+id, "?", JOptionPane.YES_NO_OPTION);
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
    public List<Funcionario> listarTodos() {
        con = ConexaoSQL.conectar();
        List<Funcionario> Lista = new ArrayList<>();
        String sql = "Select * from Funcionario";
        try (PreparedStatement stm =con.prepareStatement(sql)){
            ResultSet Resultado  = stm.executeQuery();
            while(Resultado.next()){
                Funcionario p = new Funcionario();
                p.setId_funcionario(Resultado.getInt("id_func"));
                p.setNome(Resultado.getString("nome_func"));
                p.setSenha(Resultado.getString("senha_func"));
                p.setCpf(Resultado.getString("cpf_func"));
                p.setSexo(Resultado.getString("sexo_func"));
                p.setRg(Resultado.getString("rg_func"));
                p.setTelefone(Resultado.getString("tel_func"));
                p.setEmail(Resultado.getString("email_func"));
                p.setSalario(Resultado.getDouble("salario_func"));
                p.setFuncao(Resultado.getString("funcao_func"));
                Lista.add(p);
            }
            stm.close();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Lista;
    }
    
    public Funcionario entrarFuncionario(String senha, String cpf){
        con = ConexaoSQL.conectar();
        Funcionario p = new Funcionario();
        String sql = "Select * from funcionario where cpf_func = ? and senha_func = ?";
        try (PreparedStatement stm =con.prepareStatement(sql)){  
            stm.setString(1, cpf);
            stm.setString(2, senha);
            ResultSet Resultado  = stm.executeQuery();
            if(Resultado.next()){
                p.setId_funcionario(Resultado.getInt("id_func"));
                p.setNome(Resultado.getString("nome_func"));
                p.setSenha(Resultado.getString("senha_func"));
                p.setCpf(Resultado.getString("cpf_func"));
                p.setSexo(Resultado.getString("sexo_func"));
                p.setRg(Resultado.getString("rg_func"));
                p.setEmail(Resultado.getString("email_func"));
                p.setSalario(Resultado.getDouble("salario_func"));
                p.setFuncao(Resultado.getString("funcao_func"));
            }
            stm.close();
            con.close();
        } catch (Exception e) {
            m.mensagemErro("Erro:"+e.getMessage());
        }
        return p;
    }
    
    public void editarSenha(Funcionario p, String novaSenha){
        con = ConexaoSQL.conectar();
        String sql = "update funcionario set senha_func=? where id_func=?;";
        try (PreparedStatement stm =con.prepareStatement(sql)){  
            stm.setString(1, novaSenha);
            stm.setInt(2, p.getId_funcionario());
            stm.execute();
            m.mensagemInformacao("SENHA EDITADA COM SUCESSO");
            stm.close();
        } catch (Exception e) {
            m.mensagemErro("ERRO AO EDITAR"+e.getMessage());
        }
    }
    
    public int primeiroAcesso(){
        int retorno = 0;
        con = ConexaoSQL.conectar();
        String sql = "Select COUNT(id_func) from funcionario";
        try (PreparedStatement stm =con.prepareStatement(sql)){  
            ResultSet Resultado  = stm.executeQuery();
            if(Resultado != null && Resultado.next()){
                retorno = Resultado.getInt("COUNT(id_func)");
            }
            stm.close();
            con.close();
        } catch (Exception e) {
            m.mensagemErro("ERRO"+e.getMessage());
        }
        return retorno;
    }
    
    public int esqueciSenha(String Str, int id){
        if(id!=0){
            String rg;
            con = ConexaoSQL.conectar();
            String sql = "Select rg_func from funcionario where id_func = ?";
            try (PreparedStatement stm =con.prepareStatement(sql)){  
                stm.setInt(1, id);
                stm.execute();  
                ResultSet Resultado  = stm.executeQuery();
                rg = Resultado.getString("rg_func");
                if(rg.equals(Str)){
                }else{
                    id = 0;
                }
                stm.close();
                con.close();
            } catch (Exception e) {
                m.mensagemErro("ERRO"+e.getMessage());
            }
        }else{
            con = ConexaoSQL.conectar();
            String sql = "Select id_func from funcionario where cpf_func = ?";
            try (PreparedStatement stm =con.prepareStatement(sql)){  
                stm.setString(1, Str);
                stm.execute();  
                ResultSet Resultado  = stm.executeQuery();
                while(Resultado.next()){
                    if(Resultado!=null){
                        id = Resultado.getInt("id_func");
                    }else{             }
                }
                if(Resultado==null){
                        id = 0;
                }else{             }
                stm.close();
                con.close();
            } catch (Exception e) {
                m.mensagemErro("ERRO"+e.getMessage());
            }
        }
        return id;
    }
}
