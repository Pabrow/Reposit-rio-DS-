/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.ConexaoSQL;
import Objetos.Marca;
import Objetos.Mensagens;
import Objetos.Tipo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mateu
 */
public class TipoDAO {

    private Connection con = null;
    Mensagens m = new Mensagens();

    public void cadastrarTipo(Tipo p) {
        con = ConexaoSQL.conectar();
        String sql = "INSERT INTO Tipo (nome_tipo) values (?);";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, p.getNome());
            stm.execute();
            stm.close();
            m.mensagemInformacao("CADASTRADO COM SUCESSO");
        } catch (Exception e) {
            m.mensagemErro("ERRO AO CADASTRAR" + e.getMessage());
        }
    }

    public void editarPorID(Tipo p) {
        con = ConexaoSQL.conectar();
        String sql = "update marca set nome_tipo =? where id_tipo=?;";
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
        String sql = "delete from tipo where id_tipo=?";
        int opcao = JOptionPane.showConfirmDialog(null, "EXCLUIR tipo NO ID: " + id, "?", JOptionPane.YES_NO_OPTION);
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

    public List<Tipo> listarTodos() {
        con = ConexaoSQL.conectar();
        List<Tipo> Lista = new ArrayList<>();
        String sql = "Select * from Tipo";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            ResultSet Resultado = stm.executeQuery();
            while (Resultado.next()) {
                Tipo p = new Tipo();
                p.setId_tipo(Resultado.getInt("id_tipo"));
                p.setNome(Resultado.getString("nome_tipo"));
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
