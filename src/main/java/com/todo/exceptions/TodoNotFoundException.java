package com.todo.exceptions;

public class TodoNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private Integer id;
	public TodoNotFoundException(Integer id) {
		super(String.format("Todo with Id %s not found", id));
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
}
