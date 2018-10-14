package br.com.curso.config.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.curso.config.dao.UsuarioDAO;
import br.com.curso.config.dao.UsuarioDAOInterface;
import br.com.curso.config.domain.TipoSexo;
import br.com.curso.config.domain.Usuario;

@Controller
@RequestMapping("usuario") // também poderia adicionar sem o valor value
public class UsuarioController {

	@Autowired
	private UsuarioDAOInterface<Usuario> dao;
	
	@ModelAttribute("sexos")
	public TipoSexo[] tipoSexo() {
		return TipoSexo.values();
	}
	
	@GetMapping("/nome")
    public ModelAndView listarPorNome(@RequestParam(value = "nome") String nome) {
 
        if (nome == null) {
            return new ModelAndView("redirect:/usuario/todos");
        }
        return new ModelAndView("/user/list", "usuarios", dao.getByNome(nome));
    }   
     
    @GetMapping("/sexo")
    public ModelAndView listarPorSexo(@RequestParam(value = "tipoSexo") TipoSexo sexo) {
 
        if (sexo == null) {
            return new ModelAndView("redirect:/usuario/todos");
        }
        return new ModelAndView("/user/list", "usuarios", dao.geyBySexo(sexo));
    }

	@RequestMapping(value = "/todos", method = RequestMethod.GET)
	public ModelAndView listaTodos(ModelMap model) {
		
		return new ModelAndView("/user/list", model);

	}

	// Criação do metodo para direcionar para a pagina de cadastro

	@GetMapping("/cadastro") // Ela funciona como um request do tipo get
	public String cadastro(@ModelAttribute("usuario") Usuario usuario, ModelMap model) { // para fazer a ligação do
																							// formulario com o
																							// controler, utilizamos a
																							// notação modelattribute.
																							// Se formos observar, a
																							// pagina jsp tem um
																							// modelattribute também
		model.addAttribute("sexos", TipoSexo.values()); // Aqui é como se estivessemos retornando uma lista com os
														// valores que existem dentro do enum, no caso, tipo sexo, para
														// quando chegar no atributo itens, exibir os tipos que existem
														// no enum
		return "/user/add";
	}

	// Criar um metodo para receber o formulario quando receber da página

	@PostMapping("/save")
	public String save(
			@Valid /* serve para utilizar a validação do bean validation */ @ModelAttribute("usuario") Usuario usuario,
			BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "/user/add";
		}

		dao.salvar(usuario);
		attr.addFlashAttribute("message", "Usuario salvo com sucesso.");
		return "redirect:/usuario/todos"; // o uso do redirect é para quando o usuario for salvo voltar para a página
											// inicial, ou voltar para a página que está sendo direcionada

	}

	@GetMapping("/update/{id}")
	public ModelAndView preUpdate(@PathVariable("id") Long id, ModelMap model) {
		Usuario usuario = dao.getId(id);
		model.addAttribute("usuario", usuario);
		return new ModelAndView("/user/add", model);
	}

	@PostMapping("/update")
	public ModelAndView update(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result,
			RedirectAttributes attr) {
		if (result.hasErrors()) {
			return new ModelAndView("/user/add");
		}
		dao.editar(usuario);
		attr.addFlashAttribute("message", "Usuario modificado com sucesso");
		return new ModelAndView("redirect:/usuario/todos");
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes attr) {
		dao.excluir(id);
		attr.addFlashAttribute("message", "Usuario excluido com sucesso");
		return "redirect:/usuario/todos";
	}
	
	

}
