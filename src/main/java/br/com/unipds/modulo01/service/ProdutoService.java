package br.com.unipds.modulo01.service;

import br.com.unipds.modulo01.domain.Produto;
import br.com.unipds.modulo01.exception.ProdutoNaoEncontradoException;
import br.com.unipds.modulo01.repository.ProdutoRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ProdutoService {

    private final ProdutoRepository repository = new ProdutoRepository();

    public void cadastrarProduto(Produto produto) {
        repository.save(produto);
    }

    public Produto buscarProduto(int codigo) {
        return repository.findById(codigo)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado: " + codigo));
    }

    public Optional<Produto> buscarProdutoOptional(int codigo) {
        return repository.findById(codigo);
    }

    public void aplicarDescontoSeExistir(int codigo, BigDecimal percentual) {
        repository.findById(codigo)
                .ifPresent(produto -> {
                    BigDecimal desconto = produto.getPreco()
                            .multiply(percentual)
                            .divide(BigDecimal.valueOf(100));

                    produto.setPreco(produto.getPreco().subtract(desconto));
                });
    }

    public String buscarDescricaoOuMensagem(int codigo) {
        return repository.findById(codigo)
                .map(Produto::getDescricao)
                .orElse("Produto inexistente");
    }

    public List<Produto> listarProdutos() {
        return repository.findAll();
    }

    public void removerProduto(int codigo) {
        if (!repository.exists(codigo)) {
            throw new ProdutoNaoEncontradoException("Produto não encontrado: " + codigo);
        }
        repository.delete(codigo);
    }
}
