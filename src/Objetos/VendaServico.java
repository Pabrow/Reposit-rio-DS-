/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author pablo
 */
public class VendaServico {
    private int id_vendaServico;
    private int id_venda_fk;
    private int id_servico_fk;
    private double valorUnit;
    private int quant;

    public int getId_vendaServico() {
        return id_vendaServico;
    }

    public void setId_vendaServico(int id_vendaServico) {
        this.id_vendaServico = id_vendaServico;
    }

    public int getId_servico_fk() {
        return id_servico_fk;
    }

    public void setId_servico_fk(int id_servico_fk) {
        this.id_servico_fk = id_servico_fk;
    }

    public int getId_venda_fk() {
        return id_venda_fk;
    }

    public void setId_venda_fk(int id_venda_fk) {
        this.id_venda_fk = id_venda_fk;
    }

    public double getValorUnit() {
        return valorUnit;
    }

    public void setValorUnit(double valorUnit) {
        this.valorUnit = valorUnit;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }
    
}
