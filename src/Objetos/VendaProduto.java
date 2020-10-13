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
public class VendaProduto {
    private int id_vendaProduto;

    public int getId_vendaProduto() {
        return id_vendaProduto;
    }

    public void setId_vendaProduto(int id_vendaProduto) {
        this.id_vendaProduto = id_vendaProduto;
    }
    private int id_venda_fk;
    private int id_produto_fk;
    private double valorUnit;
    private int quant;

    public int getId_venda_fk() {
        return id_venda_fk;
    }

    public void setId_venda_fk(int id_venda_fk) {
        this.id_venda_fk = id_venda_fk;
    }

    public int getId_produto_fk() {
        return id_produto_fk;
    }

    public void setId_produto_fk(int id_produto_fk) {
        this.id_produto_fk = id_produto_fk;
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
