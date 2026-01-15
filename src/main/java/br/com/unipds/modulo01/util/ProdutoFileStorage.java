package br.com.unipds.modulo01.util;

import br.com.unipds.modulo01.domain.*;
import br.com.unipds.modulo01.exception.ArquivoProdutoFormatoInvalidoException;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ProdutoFileStorage {

    private static final String SEPARADOR = ";";

    public void exportar(List<Produto> produtos, Path arquivo) {
        List<String> linhas = new ArrayList<>();

        for (Produto p : produtos) {
            linhas.add(converterProdutoParaLinha(p));
        }

        try {
            Files.write(arquivo, linhas);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao gravar arquivo: " + arquivo, e);
        }
    }

    public List<Produto> importar(Path arquivo) {
        List<String> linhas;

        try {
            linhas = Files.readAllLines(arquivo);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler arquivo: " + arquivo, e);
        }

        List<Produto> produtos = new ArrayList<>();

        for (String linha : linhas) {
            if (linha == null || linha.trim().isEmpty()) {
                continue;
            }

            produtos.add(converterLinhaParaProduto(linha));
        }

        return produtos;
    }

    private String converterProdutoParaLinha(Produto produto) {

        StringBuilder sb = new StringBuilder();

        sb.append(produto.getCodigo()).append(SEPARADOR);

        if (produto instanceof ProdutoFisico) {
            ProdutoFisico pf = (ProdutoFisico) produto;
            sb.append("FISICO").append(SEPARADOR)
              .append(produto.getDescricao()).append(SEPARADOR)
              .append(produto.getPreco()).append(SEPARADOR)
              .append(produto.getEstoque()).append(SEPARADOR)
              .append(pf.getPeso());
        } else if (produto instanceof ProdutoDigital) {
            ProdutoDigital pd = (ProdutoDigital) produto;
            sb.append("DIGITAL").append(SEPARADOR)
              .append(produto.getDescricao()).append(SEPARADOR)
              .append(produto.getPreco()).append(SEPARADOR)
              .append(produto.getEstoque()).append(SEPARADOR)
              .append(pd.getLinkDownload());
        } else {
            throw new ArquivoProdutoFormatoInvalidoException("Tipo de produto desconhecido");
        }

        return sb.toString();
    }

    private Produto converterLinhaParaProduto(String linha) {

        String[] partes = linha.split(SEPARADOR);

        if (partes.length != 6) {
            throw new ArquivoProdutoFormatoInvalidoException("Linha inválida: " + linha);
        }

        try {
            int codigo = Integer.parseInt(partes[0]);
            String tipo = partes[1];
            String descricao = partes[2];
            BigDecimal preco = new BigDecimal(partes[3]);
            int estoque = Integer.parseInt(partes[4]);
            String extra = partes[5];

            if ("FISICO".equalsIgnoreCase(tipo)) {
                BigDecimal peso = new BigDecimal(extra);
                return new ProdutoFisico(codigo, descricao, preco, estoque, peso);
            }

            if ("DIGITAL".equalsIgnoreCase(tipo)) {
                return new ProdutoDigital(codigo, descricao, preco, estoque, extra);
            }

            throw new ArquivoProdutoFormatoInvalidoException("Tipo inválido: " + tipo);

        } catch (Exception e) {
            throw new ArquivoProdutoFormatoInvalidoException("Erro ao processar linha: " + linha, e);
        }
    }
}
