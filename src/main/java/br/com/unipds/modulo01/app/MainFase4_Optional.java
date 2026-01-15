package br.com.unipds.modulo01.app;

import br.com.unipds.modulo01.domain.*;
import br.com.unipds.modulo01.service.ProdutoService;

import java.math.BigDecimal;

public class MainFase4_Optional {

    public static void main(String[] args) {

        ProdutoService service = new ProdutoService();

        service.cadastrarProduto(new ProdutoFisico(
                1, "Notebook", new BigDecimal("4500"), 10, new BigDecimal("2.5"))
        );

        System.out.println(service.buscarDescricaoOuMensagem(1));
        System.out.println(service.buscarDescricaoOuMensagem(99));

        service.aplicarDescontoSeExistir(1, new BigDecimal("10"));

        System.out.println(service.buscarProduto(1));
    }
}
