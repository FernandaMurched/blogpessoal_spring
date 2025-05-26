package com.generation.blogpessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.model.UsuarioLogin;
import com.generation.blogpessoal.repository.UsuarioRepository;
import com.generation.blogpessoal.service.UsuarioService;
import com.generation.blogpessoal.util.TestBuilder;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	private static final String USUARIO_ROOT_EMAIL = "root@email.com";
	private static final String USUARIO_ROOT_SENHA = "rootroot";
	private static final String BASE_URL_USUARIOS = "/usuarios";

	@BeforeAll
	void start() {
		usuarioRepository.deleteAll();
		usuarioService.cadastrarUsuario(TestBuilder.criarUsuarioRoot());
	}

	@Test
	@DisplayName("✔️ Deve cadastrar um nome usuário com sucesso!")
	public void deveCadastrarUsuario() {

		// Given
		Usuario usuario = TestBuilder.criarUsuario(null, "Cecilia Maria", "cecilia_maria@email.com", "12345678");

		// When
		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(usuario);
		ResponseEntity<Usuario> resposta = testRestTemplate.exchange(BASE_URL_USUARIOS + "/cadastrar", HttpMethod.POST,
				requisicao, Usuario.class);

		// Then
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
		assertEquals("Cecilia Maria", resposta.getBody().getNome());
		assertEquals("cecilia_maria@email.com", resposta.getBody().getUsuario());
	}

	@Test
	@DisplayName("✔️ Não deve permitir a duplicação do usuário!")
	public void naoDeveDuplicarUsuario() {

		// Given
		Usuario usuario = TestBuilder.criarUsuario(null, "Gaia Abobora", "gaia_abobora@email.com", "12345678");
		usuarioService.cadastrarUsuario(usuario);

		// When
		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(usuario);
		ResponseEntity<Usuario> resposta = testRestTemplate.exchange(BASE_URL_USUARIOS + "/cadastrar", HttpMethod.POST,
				requisicao, Usuario.class);

		// Then
		assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());

	}

	@Test
	@DisplayName("✔️ Deve atualizar os dados de um usuário com sucesso!")
	public void deveAtualizarUmUsuario() {

		// Given
		Usuario usuario = TestBuilder.criarUsuario(null, "Matilda Tilda", "matilda_tilda@email.com", "12345678");
		Optional<Usuario> usuarioCadastrado = usuarioService.cadastrarUsuario(usuario);

		Usuario usuarioUpdate = TestBuilder.criarUsuario(usuarioCadastrado.get().getId(), "Matilda Tilda Tuda",
				"matilda_tuda@email.com", "12345678");

		// When
		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(usuarioUpdate);
		ResponseEntity<Usuario> resposta = testRestTemplate.withBasicAuth(USUARIO_ROOT_EMAIL, USUARIO_ROOT_SENHA)
				.exchange(BASE_URL_USUARIOS + "/atualizar", HttpMethod.PUT, requisicao, Usuario.class);

		// Then
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertEquals("Matilda Tilda Tuda", resposta.getBody().getNome());
		assertEquals("matilda_tuda@email.com", resposta.getBody().getUsuario());

	}

	@Test
	@DisplayName("✔️ Deve listar todos os usuários com sucesso!")
	public void deveListarTodosUsuarios() {

		// Given
		usuarioService.cadastrarUsuario(
				TestBuilder.criarUsuario(null, " Francisca Chica", "fran_chica@email.com", "12345678"));
		usuarioService.cadastrarUsuario(
				TestBuilder.criarUsuario(null, " Philomena Mena", "philo_mena@email.com", "12345678"));

		// When
		ResponseEntity<Usuario[]> resposta = testRestTemplate.withBasicAuth(USUARIO_ROOT_EMAIL, USUARIO_ROOT_SENHA)
				.exchange(BASE_URL_USUARIOS + "/all", HttpMethod.GET, null, Usuario[].class);
		// Then
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertNotNull(resposta.getBody());

	}

	@Test
	@DisplayName("✔️ Deve buscar um usuário pelo ID com sucesso!")
	public void deveBuscarIdUsuario() {

		// Given
		Usuario usuario = TestBuilder.criarUsuario(null, "Matilda Tilda", "matilda_tilda@email.com", "12345678");
		Optional<Usuario> usuarioCadastrado = usuarioService.cadastrarUsuario(usuario);
		Long idBuscar = usuarioCadastrado.get().getId();

		// When
		ResponseEntity<Usuario> resposta = testRestTemplate.withBasicAuth(USUARIO_ROOT_EMAIL, USUARIO_ROOT_SENHA)
				.exchange(BASE_URL_USUARIOS + "/" + idBuscar, HttpMethod.GET, null, Usuario.class);
		// Then
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertNotNull(resposta.getBody());
		assertEquals(idBuscar, resposta.getBody().getId());
	}

	@Test
	@DisplayName("✔️ Deve autenticar o usuário e retornar um token com sucesso!")
	public void deveAutenticarUsuario() {

		// Given
		Usuario usuario = TestBuilder.criarUsuario(null, "Matilda Tilda", "matilda_tilda@email.com", "12345678");
		usuarioService.cadastrarUsuario(usuario);
		UsuarioLogin usuarioLogin = TestBuilder.criarUsuario("matilda_tilda@email.com", "12345678");

		// When
		HttpEntity<UsuarioLogin> requisicao = new HttpEntity<UsuarioLogin>(usuarioLogin);
		ResponseEntity<UsuarioLogin> resposta = testRestTemplate.exchange(BASE_URL_USUARIOS + "/logar", HttpMethod.POST,
				requisicao, UsuarioLogin.class);

		// Then
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertNotNull(resposta.getBody().getToken());

	}

}
