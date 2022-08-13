package com.todo.handlers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.todo.exceptions.TodoNotFoundException;

@ControllerAdvice
public class TodoExceptionHandler{
	@ExceptionHandler(value= {TodoNotFoundException.class})
	public ResponseEntity<Object> exception(TodoNotFoundException ex) {
		Map<String, Object> response = new HashMap<>();
		response.put("code", 404);
		response.put("error", "Todo not found with id "+ ex.getId());
		response.put("status", "error");
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
}
