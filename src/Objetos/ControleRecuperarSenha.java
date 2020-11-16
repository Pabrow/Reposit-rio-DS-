/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Conexao.ConexaoSQL;
import java.sql.SQLException;
import javax.swing.JOptionPane;
    
/**
 *
 * @author mateu
 */
public class ControleRecuperarSenha {
    ConexaoSQL conexao = new ConexaoSQL();
    ModeloRecuperarSenha mod = new ModeloRecuperarSenha();
    
    public String retornaPergunta(ModeloRecuperarSenha cadastrousuario){
        conexao.conectar();
        String pergunta;
        
        conexao.ExexutaSql("Select * from funcionario where cpf_func = '"+cadastrousuario.getUsuario()+"'");
        try{
            conexao.resultSet.last(); 
            pergunta = conexao.resultset.getString("pergunta");
            return pergunta;
        }catch (SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao buscar o usuário!\nErro: "+ex);
        }
        return null;
    }
    
    public String validarResposta(ModeloRecuperarSenha modSenha){
        String senha;
        conexao.conectar();
        
        conexao.ExexutaSql("Select * from funcionario where cpf_func = '"+modSenha.getUsuario()+"'");
        try{
            conexao.resultSet.last(); 
            if(conexao.resultset.getString("resposta").equals(modSenha.getResposta()))
            senha = conexao.resultset.getString("senha");
            return senha;
        }catch (SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Resposta incorreta!\nErro: "+ex);
        }
        return "Resposta Incorreta";
    }
}
