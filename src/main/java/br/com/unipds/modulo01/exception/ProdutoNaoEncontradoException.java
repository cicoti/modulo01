package br.com.unipds.modulo01.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7675144518311301052L;

	public ProdutoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
