package br.lostpets.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.lostpets.project.components.CadastroPessoaAnimalComponent;
import br.lostpets.project.components.PessoaAnimalComponent;
import br.lostpets.project.model.PetPerdido;
import br.lostpets.project.model.Usuario;
import br.lostpets.project.service.UsuarioService;

@Controller
public class CadastroAnimalController {

	@Autowired
	private UsuarioService usuarioService;
	private PessoaAnimalComponent PessoaAnimal;
	private ModelAndView modelAndView = new ModelAndView();
	private Usuario usuario;
	private PetPerdido petPerdido;
	private CadastroPessoaAnimalComponent cadastroPessoaAnimal = new CadastroPessoaAnimalComponent();
	
	@GetMapping("/LostPets/Cadastro_Animal_Perdido")
	public ModelAndView PaginaCadastroAnimalPerdido() {
		modelAndView.addObject("pet", cadastroPessoaAnimal);
		modelAndView.setViewName("cadastroAnimalPerdido");
		return modelAndView;
	}
	
	@PostMapping("/LostPets/Cadastro_Animal_Perdido")
	public ModelAndView cadastroAnimalPerdido(CadastroPessoaAnimalComponent cadastroPessoaAnimal) {
		
		String email = cadastroPessoaAnimal.getUsuario().getEmail();
		boolean existe = usuarioService.verificarEmail(email);
		
		if(existe) {
			System.err.println("CHEGOU AQUI1");
		}else {
			usuario = cadastroPessoaAnimal.getUsuario();
			petPerdido = cadastroPessoaAnimal.getPetPerdido();
			
			//petPerdido = new PetPerdido(usuario, nomeAnimal, dataPerdido, descricao, tipoAnimal, pathImg, cep, latitude, longitude)
			//usuarioService.salvarUsuario();
			//petPerdidoService.salvarPet();
			
			System.err.print("USUARIO: ");
			System.out.println(usuario.toString());
			System.err.print("PETPERDIDO: ");
			System.out.println(petPerdido.toString());
		}
		
			modelAndView.setViewName("principalPage");
			
		return modelAndView;
	}
	
	
}
