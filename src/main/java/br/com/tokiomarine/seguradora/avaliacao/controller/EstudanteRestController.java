package br.com.tokiomarine.seguradora.avaliacao.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.service.EstudandeService;

@RestController
public class EstudanteRestController {

	@Autowired
	EstudandeService service;

	@GetMapping("/estudantes")
	public List<Estudante> listarEstudantes() {
		return service.buscarEstudantes();
	}

	@GetMapping("/estudantes/{id}")
	public Estudante exibirEdicaoEstudante(@PathVariable("id") Long id) {
		Estudante estudante = service.buscarEstudante(id);
		return estudante;
	}

	@PostMapping("/estudantes")
	public String adicionarEstudante(@RequestBody @Valid Estudante estudante, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute(estudante);
		}

		service.cadastrarEstudante(estudante);
		return "Cadastro efetuado com sucesso!";
	}

	@PutMapping("/estudantes/{id}")
	public Estudante atualizarEstudante(@RequestBody @Valid Estudante estudante, @PathVariable Long id, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute(estudante);
			return estudante;
		}

		estudante.setId(id);
		estudante = service.atualizarEstudante(estudante);
		return estudante;
	}

	@DeleteMapping("/estudantes/{id}")
	public String apagarEstudante(@PathVariable("id") Long id) {
		service.apagarEstudante(id);
		return "Estudante removido!";
	}
}