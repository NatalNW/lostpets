package br.lostpets.project.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.lostpets.project.components.CadastroPessoaAnimalComponent;
import br.lostpets.project.model.Endereco;
import br.lostpets.project.model.PetPerdido;
import br.lostpets.project.model.Usuario;
import br.lostpets.project.service.PetPerdidoService;
import br.lostpets.project.service.UsuarioService;
import br.lostpets.project.service.ViaCep;
import br.lostpets.project.utils.GoogleDriveConfig;

@Controller
public class CadastroAnimalController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PetPerdidoService petPerdidoService;
	private ModelAndView modelAndView;
	private Usuario usuario;
	private PetPerdido petPerdido;
	private CadastroPessoaAnimalComponent cadastroPessoaAnimal = new CadastroPessoaAnimalComponent();

	private ViaCep viaCep = new ViaCep();
	private Endereco endereco = new Endereco();

	@GetMapping("/LostPets/Cadastro-Animal-Perdido")
	public ModelAndView PaginaCadastroAnimalPerdido() {
		modelAndView = new ModelAndView();
		modelAndView.addObject("pet", cadastroPessoaAnimal);
		modelAndView.setViewName("cadastroAnimalPerdido");
		return modelAndView;
	}

	@PostMapping("/LostPets/Cadastro-Animal-Perdido")
	public ModelAndView cadastroAnimalPerdido(@RequestParam(value = "files") MultipartFile[] files,
			CadastroPessoaAnimalComponent cadastroPessoaAnimal) throws IOException, GeneralSecurityException {

		usuario = cadastroPessoaAnimal.getUsuario();
		petPerdido = cadastroPessoaAnimal.getPetPerdido();

		usuario = usuarioService.verificarEmailUsuario(usuario.getEmail());
		
		endereco = viaCep.getLatitudeLongitude(petPerdido.getCep());

		petPerdido.setStatus("P");
		petPerdido.setUsuario(usuario);
		petPerdido.setLatitude(endereco.getLatitude());
		petPerdido.setLongitude(endereco.getLongitude());

		if (usuario != null) {
			petPerdidoService.salvarPet(petPerdido);
			// chama o pdf aqui

			for (MultipartFile file : files) {
				petPerdido.setPathImg(GoogleDriveConfig.uploadFile(file));
			}

		} else {
			usuarioService.salvarUsuario(usuario);
			petPerdidoService.salvarPet(petPerdido);

			for (MultipartFile file : files) {
				petPerdido.setPathImg(GoogleDriveConfig.uploadFile(file));
			}

		}

		return new ModelAndView("redirect:/LostPets/Cadastro-Animal-Perdido");
	}

}
