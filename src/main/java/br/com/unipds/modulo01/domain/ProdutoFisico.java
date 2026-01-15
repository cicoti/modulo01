package br.com.unipds.modulo01.domain;

import java.math.BigDecimal;

public class ProdutoFisico extends Produto {

    private BigDecimal peso;

    public ProdutoFisico(int codigo, String descricao, BigDecimal preco, int estoque, BigDecimal peso) {
        super(codigo, descricao, preco, estoque);
        this.peso = peso;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    @Override
    public BigDecimal calcularFrete() {
        return peso.multiply(new BigDecimal("10"));
    }
}
