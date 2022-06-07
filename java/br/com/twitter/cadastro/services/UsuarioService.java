package br.com.twitter.cadastro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.twitter.cadastro.entity.Usuario;
import br.com.twitter.cadastro.entity.dto.UsuarioDTO;
import br.com.twitter.cadastro.repository.UsuarioRepository;
import br.com.twitter.cadastro.services.exception.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario findById(Integer id) {
		Optional<Usuario> obj = usuarioRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado Id: " + id));
	}
	
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();		
	}

	public Usuario create(UsuarioDTO usDTO) {
		usDTO.setId(null);
		
		Usuario obj = new Usuario(usDTO);
		return usuarioRepository.save(obj);
	}
	
//	public void validaEmail() {
//		return null;
//	}
	
	
}
