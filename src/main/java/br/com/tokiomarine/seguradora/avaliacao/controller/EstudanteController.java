package br.com.tokiomarine.seguradora.avaliacao.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.service.EstudandeService;

@Controller
@RequestMapping("/estudantes/")
public class EstudanteController {

	private static final String REDIRECT_ESTUDANTES_LISTAR = "redirect:/estudantes/listar";
	private static final String PAGE_CADASTRAR_ESTUDANTE = "cadastrar-estudante";
	private static final String PAGE_ATUALIZAR_ESTUDANTE = "atualizar-estudante";
	
	@Autowired
	EstudandeService service;

	@RequestMapping(value = "listar")
	public ModelAndView listarEstudantes() {
		ModelAndView model = new ModelAndView(IndexEstudanteController.PAGE_INDEX);
		model.addObject("estudantes", service.buscarEstudantes());
		return model;
	}

	@RequestMapping("criar")
	public ModelAndView iniciarCastrado() {
		ModelAndView model = new ModelAndView(PAGE_CADASTRAR_ESTUDANTE);
		model.addObject("estudante", new Estudante());
		return model;
	}

	@PostMapping("add")
	public ModelAndView adicionarEstudante(@Valid Estudante estudante, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute(estudante);
			return new ModelAndView(PAGE_CADASTRAR_ESTUDANTE);
		}

		service.cadastrarEstudante(estudante);
		return new ModelAndView(REDIRECT_ESTUDANTES_LISTAR);
	}

	@GetMapping("editar/{id}")
	public ModelAndView exibirEdicaoEstudante(@PathVariable("id") long id) {
		Estudante estudante = service.buscarEstudante(id);
		ModelAndView model = new ModelAndView(PAGE_ATUALIZAR_ESTUDANTE);
		model.addObject("estudante", estudante);
		
		return model;
	}

	@PostMapping("atualizar")
	public ModelAndView atualizarEstudante(@Valid Estudante estudante, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute(estudante);
			return new ModelAndView(PAGE_ATUALIZAR_ESTUDANTE);
		}

		estudante = service.atualizarEstudante(estudante);
		return new ModelAndView(REDIRECT_ESTUDANTES_LISTAR);
	}

	@GetMapping("apagar/{id}")
	public ModelAndView apagarEstudante(@PathVariable("id") Long id) {
		service.apagarEstudante(id);
		return new ModelAndView(REDIRECT_ESTUDANTES_LISTAR);
	}
}