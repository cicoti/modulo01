package br.com.unipds.modulo01.service.relatorio;

import br.com.unipds.modulo01.domain.Produto;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class ProdutoEstatisticaService {

    public BigDecimal calcularValorTotalEstoque(List<Produto> produtos) {
        return produtos.stream()
                .map(p -> p.getPreco().multiply(BigDecimal.valueOf(p.getEstoque())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calcularPrecoMedio(List<Produto> produtos) {
        return produtos.stream()
                .map(Produto::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(produtos.size()), BigDecimal.ROUND_HALF_UP);
    }

    public Optional<Produto> produtoMaisCaro(List<Produto> produtos) {
        return produtos.stream()
                .max(Comparator.comparing(Produto::getPreco));
    }

    public Optional<Produto> produtoMaisBarato(List<Produto> produtos) {
        return produtos.stream()
                .min(Comparator.comparing(Produto::getPreco));
    }

    public Map<Class<?>, List<Produto>> agruparPorTipo(List<Produto> produtos) {
        return produtos.stream()
                .collect(Collectors.groupingBy(Produto::getClass));
    }

    public List<Produto> produtosComEstoqueBaixo(List<Produto> produtos, int limite) {
        return produtos.stream()
                .filter(p -> p.getEstoque() <= limite)
                .collect(Collectors.toList());
    }

    public long quantidadeTotal(List<Produto> produtos) {
        return produtos.stream().count();
    }

    public boolean existeProdutoCaro(List<Produto> produtos, BigDecimal valor) {
        return produtos.stream()
                .anyMatch(p -> p.getPreco().compareTo(valor) > 0);
    }
}
