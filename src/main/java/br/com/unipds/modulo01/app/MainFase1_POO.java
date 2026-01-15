package br.com.unipds.modulo01.app;

import br.com.unipds.modulo01.domain.*;

import java.math.BigDecimal;

public class MainFase1_POO {

    public static void main(String[] args) {

        ProdutoFisico notebook = new ProdutoFisico(
                1, "Notebook", new BigDecimal("4500"), 10, new BigDecimal("2.5")
        );

        ProdutoDigital curso = new ProdutoDigital(
                2, "Curso Java", new BigDecimal("199"), 1000, "https://download.curso.com/java"
        );

        Produto[] produtos = { notebook, curso };

        for (Produto p : produtos) {
            System.out.println(p);
            System.out.println("Frete: " + p.calcularFrete());
            System.out.println();
        }
    }
}
