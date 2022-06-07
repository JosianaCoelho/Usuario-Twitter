package br.com.twitter.cadastro.controller.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.twitter.cadastro.services.exception.ObjectNotFoundException;

@ControllerAdvice 
public class ControllerExceptionsHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandartError> objectNotFoundException(ObjectNotFoundException object, HttpServletRequest request) {
		StandartError erro = new StandartError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), 
				"Objeto não emcontrado", object.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
		
	}
	

}
