/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Objetos.Mensagens;
import java.sql.Connection;

/**
 *
 * @author mateu
 */
public class MarcaDAO {

    private Connection con = null;
    Mensagens m = new Mensagens();

    public void cadastrarMarca(marca p) {
        con = ConexaoSQL.conectar();
        String sql = "INSERT INTO Marca (nome_marca) values (?);";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, p.getNome());
            stm.execute();
            stm.close();
            m.mensagemInformacao("CADASTRADO COM SUCESSO");
        } catch (Exception e) {
            m.mensagemErro("ERRO AO CADASTRAR" + e.getMessage());
        }
    }

    public void editarPorID(Marca p) {
        con = ConexaoSQL.conectar();
        String sql = "update marca set nome_marca =? where id_marca=?;";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, p.getNome());
            stm.execute();
            m.mensagemInformacao("EDITADO COM SUCESSO");
            stm.close();
        } catch (Exception e) {
            m.mensagemErro("ERRO AO EDITAR" + e.getMessage());
        }
    }

    public void deletar(int id) {
        con = ConexaoSQL.conectar();
        String sql = "delete from marca where id_marca=?";
        int opcao = JOptionPane.showConfirmDialog(null, "EXCLUIR MARCA NO ID: " + id, "?", JOptionPane.YES_NO_OPTION);
        if (opcao == 0) {
            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setInt(1, id);
                stm.execute();
                stm.close();
                m.mensagemInformacao("DELETADO COM SUCESSO");
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        } else {
            m.mensagemWarning("Operação cancelada");
        }
    }

    public List<Marca> listarTodos() {
        con = ConexaoSQL.conectar();
        List<Marca> Lista = new ArrayList<>();
        String sql = "Select * from Marca";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            ResultSet Resultado = stm.executeQuery();
            while (Resultado.next()) {
                Marca p = new Marca();
                p.setId_funcionario(Resultado.getInt("id_marca"));
                p.setNome(Resultado.getString("nome_marca"));
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
