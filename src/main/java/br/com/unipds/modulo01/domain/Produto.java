package br.com.unipds.modulo01.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Produto {

    private int codigo;
    private String descricao;
    private BigDecimal preco;
    private int estoque;
    private LocalDate dataCadastro;

    public Produto(int codigo, String descricao, BigDecimal preco, int estoque) {
        this.codigo = codigo;
        this.descricao = descricao;
        setPreco(preco);
        setEstoque(estoque);
        this.dataCadastro = LocalDate.now();
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(BigDecimal preco) {
        if (preco == null || preco.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Preço inválido");
        }
        this.preco = preco;
    }

    public void setEstoque(int estoque) {
        if (estoque < 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo");
        }
        this.estoque = estoque;
    }

    public BigDecimal calcularFrete() {
        return BigDecimal.ZERO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return codigo == produto.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "codigo=" + codigo +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", estoque=" + estoque +
                ", dataCadastro=" + dataCadastro +
                '}';
    }
}
