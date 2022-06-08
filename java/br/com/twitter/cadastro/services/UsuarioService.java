package br.com.twitter.cadastro.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.twitter.cadastro.entity.Usuario;
import br.com.twitter.cadastro.entity.dto.UsuarioDTO;
import br.com.twitter.cadastro.repository.UsuarioRepository;
import br.com.twitter.cadastro.services.exception.DataIntegrityViolationException;
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
		validaEmail(usDTO);
		Usuario obj = new Usuario(usDTO);
		return usuarioRepository.save(obj);
	}
	
	public void validaEmail(UsuarioDTO usDTO) {
		Optional<Usuario> obj = usuarioRepository.findByEmail(usDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() != usDTO.getId()) {
		throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
		}
	}
	
	public Usuario update(Integer id, @Valid UsuarioDTO usDTO) {
		usDTO.setId(id);
		Usuario us = findById(id);
		
		if(!us.getSenha().equals(us.getSenha())) {
			usDTO.setSenha(usDTO.getSenha());
		}
		
		validaEmail(usDTO);
		us = new Usuario(usDTO);
		return usuarioRepository.save(us);
	}
	
	public void delete(Integer id) {
		Usuario us = findById(id);
		usuarioRepository.deleteById(id);
	}
	
	
}
