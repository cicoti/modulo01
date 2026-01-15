package br.com.unipds.modulo01.app;

import br.com.unipds.modulo01.domain.*;
import br.com.unipds.modulo01.service.relatorio.ProdutoRelatorioService;

import java.math.BigDecimal;
import java.util.*;

public class MainFase2_Collections {

    public static void main(String[] args) {

        ProdutoRelatorioService relatorio = new ProdutoRelatorioService();

        Produto p1 = new ProdutoFisico(1, "Notebook", new BigDecimal("4500"), 10, new BigDecimal("2.5"));
        Produto p2 = new ProdutoDigital(2, "Curso Java", new BigDecimal("199"), 1000, "link");
        Produto p3 = new ProdutoFisico(1, "Notebook Duplicado", new BigDecimal("4500"), 5, new BigDecimal("2.5"));

        List<Produto> lista = new ArrayList<>();
        lista.add(p1);
        lista.add(p2);
        lista.add(p3);

        System.out.println("LIST");
        lista.forEach(System.out::println);

        System.out.println();
        System.out.println("SET");
        Set<Produto> set = relatorio.produtosUnicos(lista);
        set.forEach(System.out::println);

        System.out.println();
        System.out.println("MAP");

        Map<Integer, Produto> map = relatorio.mapearPorCodigo(lista);
        map.forEach((k, v) -> System.out.println(k + " -> " + v.getDescricao()));
    }
}
