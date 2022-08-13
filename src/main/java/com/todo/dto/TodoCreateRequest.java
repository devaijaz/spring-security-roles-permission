package com.todo.dto;

public class TodoCreateRequest {
	private String title;
	
	public TodoCreateRequest() {
	}

	public TodoCreateRequest(String title) {
		super();
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
