package org.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.generation.blogpessoal.model.Usuario;

//Indica que a Class UsuarioRepositoryTest é uma classe de teste
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)// Roda em porta aleatóri(diferente da 8080)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)// Indica que o teste a ser feito será um teste unitário (por classe)
public class UsuarioRepositoryTest {
    
	@Autowired // traz o UsuárioRepository
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll // inserindo usuários no meu DB H2, para testar funções
	void start(){

		usuarioRepository.deleteAll();

		usuarioRepository.save(new Usuario(0L, "João da Silva", "joao@email.com.br", "13465278", "https://i.imgur.com/FETvs2O.jpg"));
		
		usuarioRepository.save(new Usuario(0L, "Manuela da Silva", "manuela@email.com.br", "13465278", "https://i.imgur.com/NtyGneo.jpg"));
		
		usuarioRepository.save(new Usuario(0L, "Adriana da Silva", "adriana@email.com.br", "13465278", "https://i.imgur.com/mB3VM2N.jpg"));

        usuarioRepository.save(new Usuario(0L, "Paulo Antunes", "paulo@email.com.br", "13465278", "https://i.imgur.com/JR7kUFU.jpg"));

	}

	@Test //Indica o início do test
	@DisplayName("Retorna 1 usuario") 
	public void deveRetornarUmUsuario() {

		// o que eu espero receber da API, buscando usuario por email
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("joao@email.com.br");

		assertTrue(usuario.get().getUsuario().equals("joao@email.com.br"));
	} // comparando se o que eu esperava receber, foi o que recebi

	@Test
	@DisplayName("Retorna 3 usuarios") //Indica o nome do teste
	public void deveRetornarTresUsuarios() {

		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");

		assertEquals(3, listaDeUsuarios.size());
		
		assertTrue(listaDeUsuarios.get(0).getNome().equals("João da Silva"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Manuela da Silva"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Adriana da Silva"));
		
	}

	
	@AfterAll
	public void end() {
		usuarioRepository.deleteAll();
	}
	
}