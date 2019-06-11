package br.lostpets.project.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.lostpets.project.model.Endereco;
import br.lostpets.project.model.Usuario;
import br.lostpets.project.service.UsuarioService;
import br.lostpets.project.service.ViaCep;
import br.lostpets.project.utils.GoogleDriveConfig;

@Controller
public class CadastroPessoaController {

	@Autowired
	private UsuarioService usuarioService;
	private ModelAndView modelAndView = new ModelAndView();

	private Endereco endereco = new Endereco();
	private ViaCep viaCep = new ViaCep();
	private Usuario usuario;
	
	@GetMapping("/LostPets/Cadastro")
	public ModelAndView cadastroPage() {
		usuario = new Usuario();
		modelAndView.addObject("usuario", usuario);
		modelAndView.setViewName("cadastroPessoa");
		return modelAndView;
	}

	@PostMapping("/LostPets/Cadastro")
	public ModelAndView cadastrar(/*@RequestParam(value = "files") MultipartFile[] files,*/ @Valid Usuario usuario,
			BindingResult bindingResult) throws IOException, GeneralSecurityException {
			
		boolean existe = usuarioService.verificarEmail(usuario.getEmail());

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("cadastroPessoa");
			
		} else if (existe) {
			modelAndView.addObject("mensagemSucesso", "E-mail já cadastrado!");
			modelAndView.setViewName("cadastroPessoa");
		} else {
			String[] cepV = usuario.getCep().split("-");
			String cep = cepV[0].concat(cepV[1]);
			endereco = viaCep.buscarCep(cep);
			usuario.setEndereco(endereco);
			/*
			for (MultipartFile file : files) {
				usuario.setIdImagem(GoogleDriveConfig.uploadFile(GoogleDriveConfig.convert(file), GoogleDriveConfig.getService()));
			}
			*/
			usuarioService.salvarUsuario(usuario);
      
			modelAndView = new ModelAndView("redirect:/LostPets");
			modelAndView.addObject("mensagem", "Usuário cadastrado com sucesso!");
		}
		return modelAndView;
	}
}
