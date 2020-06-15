package br.com.tokiomarine.seguradora.avaliacao.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.repository.EstudanteRepository;
import br.com.tokiomarine.seguradora.avaliacao.service.exception.EstudanteNotFoundException;

@Service
public class EstudanteServiceImpl implements EstudandeService {

	@Autowired
	EstudanteRepository repository;

	@Override
	public List<Estudante> buscarEstudantes() {
		return (List<Estudante>) repository.findAll();
	}

	@Override
	public Estudante buscarEstudante(long id) {
		final Estudante estudante = repository.getOne(id);
		if (estudante == null)
			throw new EstudanteNotFoundException("Identificador inválido:" + id);
		
		return estudante;
	}

	@Override
	public void cadastrarEstudante(@Valid Estudante estudante) {
		repository.save(estudante);
	}

	@Override
	public Estudante atualizarEstudante(@Valid Estudante estudante) {
		if(this.buscarEstudante(estudante.getId()) != null)
			estudante = repository.saveAndFlush(estudante);
		return estudante;
	}

	@Override
	public void apagarEstudante(@Valid long id) {
		if(this.buscarEstudante(id) != null)
			repository.deleteById(id);
	}
}