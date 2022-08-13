package com.todo.dto;

public class TodoUpdateRequest {
	private String title;
	private boolean completed;
	public TodoUpdateRequest(String title, boolean completed) {
		super();
		this.title = title;
		this.completed = completed;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	
}
