package br.com.unipds.modulo01.app;

import br.com.unipds.modulo01.domain.*;
import br.com.unipds.modulo01.service.ProdutoService;
import br.com.unipds.modulo01.service.relatorio.ProdutoRelatorioService;
import br.com.unipds.modulo01.service.relatorio.ProdutoEstatisticaService;
import br.com.unipds.modulo01.util.ProdutoFileStorage;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainCompleto {

	//teste
	
    public static void main(String[] args) {

        System.out.println("=== SISTEMA COMPLETO - MODULO 01 ===");

        ProdutoService service = new ProdutoService();
        ProdutoRelatorioService relatorio = new ProdutoRelatorioService();
        ProdutoEstatisticaService estatistica = new ProdutoEstatisticaService();
        ProdutoFileStorage storage = new ProdutoFileStorage();

        // =========================
        // FASE 1 e 2 - POO
        // =========================

        ProdutoFisico p1 = new ProdutoFisico(1, "Notebook", new BigDecimal("4500"), 10, new BigDecimal("2.5"));
        ProdutoDigital p2 = new ProdutoDigital(2, "Curso Java", new BigDecimal("199"), 1000, "https://download.curso.com/java");
        ProdutoFisico p3 = new ProdutoFisico(3, "Mouse", new BigDecimal("150"), 3, new BigDecimal("0.2"));

        service.cadastrarProduto(p1);
        service.cadastrarProduto(p2);
        service.cadastrarProduto(p3);

        System.out.println();
        System.out.println("Produtos cadastrados:");
        service.listarProdutos().forEach(System.out::println);

        // =========================
        // FASE 3 - Collections
        // =========================

        System.out.println();
        System.out.println("Collections - List, Set, Map");

        List<Produto> lista = new ArrayList<>(service.listarProdutos());
        lista.add(new ProdutoFisico(1, "Notebook duplicado", new BigDecimal("4500"), 5, new BigDecimal("2.5")));

        System.out.println("List (com duplicados):");
        lista.forEach(System.out::println);

        System.out.println();
        System.out.println("Set (sem duplicados):");
        Set<Produto> set = relatorio.produtosUnicos(lista);
        set.forEach(System.out::println);

        System.out.println();
        System.out.println("Map por código:");
        Map<Integer, Produto> map = relatorio.mapearPorCodigo(lista);
        map.forEach((k, v) -> System.out.println(k + " -> " + v.getDescricao()));

        // =========================
        // FASE 4 - Optional + Streams
        // =========================

        System.out.println();
        System.out.println("Relatórios com Streams:");

        List<Produto> produtos = service.listarProdutos();

        System.out.println("Valor total em estoque:");
        System.out.println(estatistica.calcularValorTotalEstoque(produtos));

        System.out.println("Preço médio:");
        System.out.println(estatistica.calcularPrecoMedio(produtos));

        System.out.println("Produto mais caro:");
        estatistica.produtoMaisCaro(produtos).ifPresent(System.out::println);

        System.out.println("Produto mais barato:");
        estatistica.produtoMaisBarato(produtos).ifPresent(System.out::println);

        System.out.println("Produtos com estoque baixo (<= 5):");
        estatistica.produtosComEstoqueBaixo(produtos, 5).forEach(System.out::println);

        System.out.println("Existe produto acima de 1000?");
        System.out.println(estatistica.existeProdutoCaro(produtos, new BigDecimal("1000")));

        System.out.println();
        System.out.println("Optional avançado:");

        System.out.println(service.buscarDescricaoOuMensagem(2));
        System.out.println(service.buscarDescricaoOuMensagem(999));

        System.out.println("Aplicando desconto de 10% no Notebook");
        service.aplicarDescontoSeExistir(1, new BigDecimal("10"));
        System.out.println(service.buscarProduto(1));

        // =========================
        // FASE 5 - Arquivos (NIO.2)
        // =========================

        System.out.println();
        System.out.println("Persistência em arquivo:");

        Path arquivo = Path.of("produtos_completo.csv");

        storage.exportar(produtos, arquivo);
        System.out.println("Arquivo exportado: " + arquivo.toAbsolutePath());

        System.out.println("Importando do arquivo...");

        List<Produto> importados = storage.importar(arquivo);

        importados.forEach(p -> {
            System.out.println(p);
            System.out.println("Frete: " + p.calcularFrete());
        });

        System.out.println();
        System.out.println("Valor total em estoque (importado):");
        System.out.println(estatistica.calcularValorTotalEstoque(importados));

        System.out.println();
        System.out.println("=== FIM DO SISTEMA ===");
    }
}
