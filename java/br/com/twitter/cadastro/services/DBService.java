package br.com.twitter.cadastro.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.twitter.cadastro.entity.Usuario;
import br.com.twitter.cadastro.repository.UsuarioRepository;

@Service
public class DBService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public void instanciaDB() {
			
		Usuario us = new Usuario();
		
		usuarioRepository.saveAll(Arrays.asList(us));
	}
}
