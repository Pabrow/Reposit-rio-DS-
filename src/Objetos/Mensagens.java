/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.*;

/**
 *
 * @author pablo
 */
public class Mensagens {
    
    public void mensagemErro(String Mensagem){
        JOptionPane.showMessageDialog(null, Mensagem, "Erro", ERROR_MESSAGE);
    }
    
    public void mensagemWarning(String Mensagem){
        JOptionPane.showMessageDialog(null, Mensagem, "Importante", WARNING_MESSAGE);
    }
    
    public void mensagemInformacao(String Mensagem){
        JOptionPane.showMessageDialog(null, Mensagem, "Mensagem", INFORMATION_MESSAGE);
    }
    
    public void mensagemPadrão1(){
        JOptionPane.showMessageDialog(null, "Por favor, selecione uma única linha", "Mensagem", INFORMATION_MESSAGE);
    }
    
    public void mensagemPadrão2(){
        JOptionPane.showMessageDialog(null, "Por favor, selecione uma linha se deseja deletar", "Mensagem", INFORMATION_MESSAGE);
    }
}
