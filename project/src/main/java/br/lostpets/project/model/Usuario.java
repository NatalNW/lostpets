package br.lostpets.project.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIO")
public class Usuario{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, name = "ID_PESSOA")
	private int idPessoa;

	@Column(nullable = false, name = "NOME") private String nome;
	@Column(nullable = true, name = "TELEFONE_FIXO") private String telefoneFixo;
	@Column(nullable = true, name = "TELEFONE_CELULAR") private String telefoneCelular;
	@Column(nullable = false, name = "EMAIL") private String email;
	@Column(nullable = true, name = "SENHA") private String senha;
	@Column(nullable = true, name = "PATH_IMG") private String idImagem;
	@Column(nullable = true, name = "CEP") private String cep;
	@Column(nullable = true, name = "RUA") private String rua;
	@Column(nullable = true, name = "BAIRRO") private String bairro;
	@Column(nullable = true, name = "CIDADE") private String cidade;
	@Column(nullable = true, name = "UF") private String uf;
	@Column(nullable = true, name = "LATITUDE") private String latitude;
	@Column(nullable = true, name = "LONGITUDE") private String longitude;
	@Column(nullable = true, name = "ADD_CADASTRO") private String addCadastro;
	@Column(nullable = true, name = "ULTIMO_ACESSO") private String ultimoAcesso;

	public Usuario() {}
	
	public Usuario(String nome, String telefoneFixo, String telefoneCelular, String email, String senha,
			String idImagem, String cep, String rua, String bairro, String cidade, String uf, String latitude,
			String longitude) {
		this.nome = nome;
		this.telefoneFixo = telefoneFixo;
		this.telefoneCelular = telefoneCelular;
		this.email = email;
		this.senha = senha;
		this.idImagem = idImagem;
		this.cep = cep;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
		this.latitude = latitude;
		this.longitude = longitude;
		this.addCadastro = getDate();
	}

	public Usuario(String nome, String email)
	{
		this.nome = nome;
		this.email = email;	
		this.addCadastro = getDate();
	}
	
	private static String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date hora = Calendar.getInstance().getTime();
		String dataFormatada = sdf.format(hora);
		
		return dataFormatada;
	}
	
	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefoneFixo() {
		return telefoneFixo;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getidImagem() {
		return idImagem;
	}

	public void setidImagem(String idImagem) {
		this.idImagem = idImagem;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getAddCadastro() {
		return addCadastro;
	}

	public void setAddCadastro(String addCadastro) {
		this.addCadastro = addCadastro;
	}

	public String getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void setUltimoAcesso(String ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}
	
}