package br.com.unipds.modulo01.app;

import br.com.unipds.modulo01.domain.*;
import br.com.unipds.modulo01.service.relatorio.ProdutoEstatisticaService;

import java.math.BigDecimal;
import java.util.List;

public class MainFase3_Streams {

    public static void main(String[] args) {

        ProdutoEstatisticaService estatistica = new ProdutoEstatisticaService();

        List<Produto> produtos = List.of(
                new ProdutoFisico(1, "Notebook", new BigDecimal("4500"), 10, new BigDecimal("2.5")),
                new ProdutoDigital(2, "Curso Java", new BigDecimal("199"), 1000, "link"),
                new ProdutoFisico(3, "Mouse", new BigDecimal("150"), 3, new BigDecimal("0.2"))
        );

        System.out.println("Valor total estoque:");
        System.out.println(estatistica.calcularValorTotalEstoque(produtos));

        System.out.println("Produto mais caro:");
        estatistica.produtoMaisCaro(produtos).ifPresent(System.out::println);

        System.out.println("Estoque baixo:");
        estatistica.produtosComEstoqueBaixo(produtos, 5).forEach(System.out::println);
    }
}
