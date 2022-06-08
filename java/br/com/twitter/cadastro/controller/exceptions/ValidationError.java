package br.com.twitter.cadastro.controller.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandartError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> list = new ArrayList<>();

	public ValidationError() {
		super();
	}

	public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public List<FieldMessage> getList() {
		return list;
	}

	public void addList(String fieldName, String message) {
		this.list.add(new FieldMessage(fieldName, message)); 
	}
	
	
	
	
	
	
}
