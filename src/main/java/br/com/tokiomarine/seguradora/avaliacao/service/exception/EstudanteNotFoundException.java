package br.com.tokiomarine.seguradora.avaliacao.service.exception;

public class EstudanteNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EstudanteNotFoundException(Long id) {
		super("Não pode encontrar o estudante " + id);
	}

	public EstudanteNotFoundException(String string) {
		super(string);
	}
}