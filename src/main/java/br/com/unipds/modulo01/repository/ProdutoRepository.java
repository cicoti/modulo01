package br.com.unipds.modulo01.repository;

import br.com.unipds.modulo01.domain.Produto;

import java.util.*;

public class ProdutoRepository {

    private final Map<Integer, Produto> banco = new HashMap<>();

    public void save(Produto produto) {
        banco.put(produto.getCodigo(), produto);
    }

    public Optional<Produto> findById(int codigo) {
        return Optional.ofNullable(banco.get(codigo));
    }

    public List<Produto> findAll() {
        return new ArrayList<>(banco.values());
    }

    public void delete(int codigo) {
        banco.remove(codigo);
    }

    public boolean exists(int codigo) {
        return banco.containsKey(codigo);
    }
}
