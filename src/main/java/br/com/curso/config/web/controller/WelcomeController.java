package br.com.curso.config.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller //Anotação que mostrara que é uma classe de console
public class WelcomeController {
	
	
	@RequestMapping(value="/", method=RequestMethod.GET) //Essa notação vai mostrar como será a chamada od metodo
	public String welcome() {
		//return "welcome";
		return "redirect:/usuario/todos"; //estou direcionando meu acesso
	}
	
	@RequestMapping(value="/teste",method=RequestMethod.GET)
	public ModelAndView teste() { //O tipo modelandview é um tipo do proprio spring que serve para enviar informações para a pagina, desde uma string, até objetos completos
		ModelAndView view = new ModelAndView("welcome");
		view.addObject("teste", "Olá, eu sou o spring MVC");
		return view;
	}

}
