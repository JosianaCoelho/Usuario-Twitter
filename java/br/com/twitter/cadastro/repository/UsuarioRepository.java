package br.com.twitter.cadastro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.twitter.cadastro.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>  {
	
	 Optional<Usuario> findByEmail(String email); 
		
}
