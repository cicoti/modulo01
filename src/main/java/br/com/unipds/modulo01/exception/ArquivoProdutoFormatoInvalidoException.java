package br.com.unipds.modulo01.exception;

public class ArquivoProdutoFormatoInvalidoException extends RuntimeException {

    public ArquivoProdutoFormatoInvalidoException(String mensagem) {
        super(mensagem);
    }

    public ArquivoProdutoFormatoInvalidoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
