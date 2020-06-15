package br.com.tokiomarine.seguradora.avaliacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.tokiomarine.seguradora.avaliacao.service.EstudandeService;

@Controller()
@RequestMapping("/")
public class IndexEstudanteController {
	
	public static final String PAGE_INDEX = "index";
	
	@Autowired
	EstudandeService service;

	@RequestMapping
	public ModelAndView listarEstudantes() {
		ModelAndView model = new ModelAndView(PAGE_INDEX);
		model.addObject("estudantes", service.buscarEstudantes());
		return model;
	}
}