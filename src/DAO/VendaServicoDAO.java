package DAO;
import Conexao.ConexaoSQL;
import Objetos.VendaServico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class VendaServicoDAO {
    private Connection con = null;
    private ResultSet rsDados;  
    
    
    public void cadastrarVendaServico(VendaServico p){
        con = ConexaoSQL.conectar();
        String sql = "INSERT INTO Venda_Serv (id_venda_fk, id_servico_fk, quant_vendaServ, valorUnit_servico) values (?, ?, ?);";
        try( PreparedStatement stm =con.prepareStatement(sql)){   
          stm.setInt(1, p.getId_venda_fk());
          stm.setInt(2, p.getId_servico_fk());
          stm.setInt(3, p.getQuant());
          stm.setDouble(4, p.getValorUnit());
          stm.execute();
          stm.close();
          System.out.println("CADASTRADO COM SUCESSO");
        } catch (Exception e) {
            System.out.println("ERRO AO CADASTRAR"+e.getMessage());
        }   
    }
    
    public void editarPorID(VendaServico p){
        con = ConexaoSQL.conectar();
        String sql = "update Venda_Serv set id_venda_fk =?, valorUnit_servico =?, id_servico_fk =?, quant_vendaServ = ? where id_vendaServ=?;";
         try( PreparedStatement stm =con.prepareStatement(sql)){     
            stm.setInt(1, p.getId_venda_fk());
            stm.setDouble(2, p.getValorUnit());
            stm.setInt(3, p.getId_servico_fk());
            stm.setInt(4, p.getQuant());
            stm.setInt(5, p.getId_vendaServico());
            stm.execute();
            stm.close();
            System.out.println("EDITADO COM SUCESSO");
        } catch (Exception e) {
            System.out.println("ERRO AO EDITAR"+e.getMessage());
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
                System.out.println("DELETADO COM SUCESSO");
            }catch (Exception e){
                throw new RuntimeException(e.getMessage());
            }   
        }else{
            System.out.println("Operação cancelada");
        }
    }
    
    public List<VendaServico> listarTodos() {
        con = ConexaoSQL.conectar();
        List<VendaServico> Lista = new ArrayList<>();
        String sql = "Select * from Venda_Prod";
        try (PreparedStatement stm =con.prepareStatement(sql)){
            ResultSet Resultado  = stm.executeQuery();
            while(Resultado.next()){
                VendaServico p = new VendaServico();
                p.setId_servico_fk(Resultado.getInt("id_servico_fk"));
                p.setId_vendaServico(Resultado.getInt("id_vendaServ"));
                p.setId_venda_fk(Resultado.getInt("id_venda_fk"));
                p.setQuant(Resultado.getInt("quant_vendaServ"));
                p.setValorUnit(Resultado.getDouble("valorUnit_servico"));
                Lista.add(p);
            }
            stm.close();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Lista;
    }
    
    public int retornarUltimoId() {
        con = ConexaoSQL.conectar();
        int id = 0;
        String sql = "Select MAX(id_venda) from Venda";
        try (PreparedStatement stm =con.prepareStatement(sql)){
            ResultSet Resultado  = stm.executeQuery();
            while(Resultado.next()){
                id = (Resultado.getInt("id_venda"));
            }
            stm.close();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return id;
    }
}
