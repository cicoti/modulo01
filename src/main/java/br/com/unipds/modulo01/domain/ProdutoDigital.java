package br.com.unipds.modulo01.domain;

import java.math.BigDecimal;

public class ProdutoDigital extends Produto {

    private String linkDownload;

    public ProdutoDigital(int codigo, String descricao, BigDecimal preco, int estoque, String linkDownload) {
        super(codigo, descricao, preco, estoque);
        this.linkDownload = linkDownload;
    }

    public String getLinkDownload() {
        return linkDownload;
    }

    public void setLinkDownload(String linkDownload) {
        this.linkDownload = linkDownload;
    }

    @Override
    public BigDecimal calcularFrete() {
        return BigDecimal.ZERO;
    }
}
