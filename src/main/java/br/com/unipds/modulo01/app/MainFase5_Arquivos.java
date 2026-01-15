package br.com.unipds.modulo01.app;

import br.com.unipds.modulo01.domain.*;
import br.com.unipds.modulo01.util.ProdutoFileStorage;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;

public class MainFase5_Arquivos {

    public static void main(String[] args) {

        ProdutoFileStorage storage = new ProdutoFileStorage();

        List<Produto> produtos = List.of(
                new ProdutoFisico(1, "Notebook", new BigDecimal("4500"), 10, new BigDecimal("2.5")),
                new ProdutoDigital(2, "Curso Java", new BigDecimal("199"), 1000, "link")
        );

        Path arquivo = Path.of("produtos_fase5.csv");

        storage.exportar(produtos, arquivo);

        List<Produto> importados = storage.importar(arquivo);

        importados.forEach(p -> {
            System.out.println(p);
            System.out.println("Frete: " + p.calcularFrete());
        });
    }
}
