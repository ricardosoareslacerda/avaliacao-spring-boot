package br.com.tokiomarine.seguradora.avaliacao.service;

import java.util.List;

import javax.validation.Valid;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;

public interface EstudandeService {

	List<Estudante> buscarEstudantes();

	Estudante buscarEstudante(long id);

	void cadastrarEstudante(@Valid Estudante estudante);

	Estudante atualizarEstudante(@Valid Estudante estudante);

	void apagarEstudante(@Valid long estudanteId);
}