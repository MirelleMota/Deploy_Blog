package org.generation.blogpessoal.repository;

import java.util.List;

import org.generation.blogpessoal.model.postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface postagemRepository extends JpaRepository<postagem, Long> {

	//SELECT * FROM tb_postagem WHERE titulo LIKE "%%" ;
	public List<postagem> findAllByTituloContainingIgnoreCase (String titulo);
	//public List<postagem> findAllByTextoContainingIgnoreCase (String texto);
}
