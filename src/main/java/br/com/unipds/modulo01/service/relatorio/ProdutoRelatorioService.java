package br.com.unipds.modulo01.service.relatorio;

import br.com.unipds.modulo01.domain.Produto;
import br.com.unipds.modulo01.domain.ProdutoDigital;
import br.com.unipds.modulo01.domain.ProdutoFisico;

import java.util.*;
import java.util.stream.Collectors;

public class ProdutoRelatorioService {

    public List<Produto> ordenarPorPreco(List<Produto> produtos) {
        return produtos.stream()
                .sorted(Comparator.comparing(Produto::getPreco))
                .collect(Collectors.toList());
    }

    public List<Produto> ordenarPorDescricao(List<Produto> produtos) {
        return produtos.stream()
                .sorted(Comparator.comparing(Produto::getDescricao))
                .collect(Collectors.toList());
    }

    public Set<Produto> produtosUnicos(List<Produto> produtos) {
        return new HashSet<>(produtos);
    }

    public List<ProdutoFisico> produtosFisicos(List<Produto> produtos) {
        return produtos.stream()
                .filter(p -> p instanceof ProdutoFisico)
                .map(p -> (ProdutoFisico) p)
                .collect(Collectors.toList());
    }

    public List<ProdutoDigital> produtosDigitais(List<Produto> produtos) {
        return produtos.stream()
                .filter(p -> p instanceof ProdutoDigital)
                .map(p -> (ProdutoDigital) p)
                .collect(Collectors.toList());
    }

    public Map<Integer, Produto> mapearPorCodigo(List<Produto> produtos) {
        return produtos.stream()
                .collect(Collectors.toMap(
                        Produto::getCodigo,
                        p -> p,
                        (p1, p2) -> p1
                ));
    }
}
