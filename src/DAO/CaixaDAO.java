package DAO;
import Conexao.ConexaoSQL;
import Objetos.Caixa;
import Objetos.Mensagens;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class CaixaDAO {
    private Connection con = null;
    Mensagens m = new Mensagens();
    
    public void cadastrarCaixa(Caixa p){
        con = ConexaoSQL.conectar();
        String sql = "INSERT INTO caixa (dataIn_caixa, saldoIn_caixa, totalRec_caixa, totalPag_caixa, dataFin_caixa, saldoFin_caixa, id_funcionario_fk) values (?, ?, ?, ?, ?, ?, ?);";
        try( PreparedStatement stm =con.prepareStatement(sql)){   
          stm.setString(1, p.getDataIn_caixa());
          stm.setDouble(2, 0);
          stm.setDouble(3, 0);
          stm.setDouble(4, 0);
          stm.setString(5, p.getDataFin_caixa());
          stm.setDouble(6, 0);
          stm.setInt(7, p.getId_funcionario_fk());
          stm.execute();
          stm.close();
          m.mensagemInformacao("CADASTRADO COM SUCESSO");
        } catch (Exception e) {
            m.mensagemErro("ERRO AO CADASTRAR"+e.getMessage());
        }   
    }
    
    public void editarPorID(Caixa p){
        con = ConexaoSQL.conectar();
        String sql = "update caixa set dataIn_caixa =?, dataFin_caixa =?, saldoIn_caixa =?, totalRec_caixa =?, totalPag_caixa =?, saldoFi_caixa =?, id_funcionario_fk =? where id_caixa=?;";
         try( PreparedStatement stm =con.prepareStatement(sql)){   
            stm.setString(1, p.getDataIn_caixa());
            stm.setString(2, p.getDataFin_caixa());
            stm.setDouble(3, p.getSaldoIn_caixa());
            stm.setDouble(4, p.getTotalRec_caixa());
            stm.setDouble(5, p.getTotalPag_caixa());
            stm.setDouble(6, p.getTotalFin_caixa());
            stm.setInt(7, p.getId_funcionario_fk());
            stm.setInt(8, p.getId_caixa());
            stm.execute();
            m.mensagemInformacao("EDITADO COM SUCESSO");
            stm.close();
        } catch (Exception e) {
            m.mensagemErro("ERRO AO EDITAR"+e.getMessage());
        }
    }
    
    public void deletar(int id){
        con = ConexaoSQL.conectar();
        String sql = "delete from caixa where id_caixa=?";
        int opcao = JOptionPane.showConfirmDialog(null, "EXCLUIR O CAIXA NO ID: "+id, "EXCLUIR", JOptionPane.YES_NO_OPTION);
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
    
    public List<Caixa> listarTodos() {
        con = ConexaoSQL.conectar();
        List<Caixa> Lista = new ArrayList<>();
        String sql = "Select * from Caixa";
        try (PreparedStatement stm =con.prepareStatement(sql)){
            ResultSet Resultado  = stm.executeQuery();
            while(Resultado.next()){
                Caixa p = new Caixa();
                p.setId_caixa(Resultado.getInt("id_caixa"));
                p.setDataIn_caixa(Resultado.getString("dataIn_caixa"));
                p.setDataFin_caixa(Resultado.getString("dataFin_caixa"));
                p.setId_funcionario_fk(Resultado.getInt("id_funcionario_fk"));
                p.setSaldoIn_caixa(Resultado.getDouble("saldoIn_caixa"));
                p.setTotalFin_caixa(Resultado.getDouble("saldoFin_caixa"));
                p.setTotalPag_caixa(Resultado.getDouble("totalPag_caixa"));
                p.setTotalRec_caixa(Resultado.getDouble("totalRec_caixa"));
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
