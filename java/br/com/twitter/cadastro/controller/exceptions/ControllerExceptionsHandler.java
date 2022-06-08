package br.com.twitter.cadastro.controller.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.twitter.cadastro.entity.dto.UsuarioDTO;
import br.com.twitter.cadastro.services.exception.DataIntegrityViolationException;
import br.com.twitter.cadastro.services.exception.ObjectNotFoundException;

@ControllerAdvice 
public class ControllerExceptionsHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandartError> objectNotFoundException(ObjectNotFoundException object, HttpServletRequest request) {
		StandartError erro = new StandartError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), 
				"Objeto não emcontrado", object.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandartError> dataIntegrityViolationException(DataIntegrityViolationException object, HttpServletRequest request) {
		StandartError erro = new StandartError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), 
				"Violação de dados!", object.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandartError> methodArgumentNotValidException(MethodArgumentNotValidException object, HttpServletRequest request) {
		ValidationError erro = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), 
				"Erro de Validação", "Erro na validação dos campos!", request.getRequestURI());
		for(FieldError x : object.getBindingResult().getFieldErrors()) {
			erro.addList(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	
}
