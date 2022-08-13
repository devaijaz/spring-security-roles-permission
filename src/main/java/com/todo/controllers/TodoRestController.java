package com.todo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.dto.TodoCreateRequest;
import com.todo.dto.TodoUpdateRequest;
import com.todo.entities.Todo;
import com.todo.services.TodoService;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoRestController {
	
	@Autowired
	private TodoService service;
	
	@GetMapping
	public List<Todo> getAll() {
		return service.getAllTodos();
	}
	
	@GetMapping("{id}")
	//@PreAuthorize(value = "hasRole('ADMIN') or hasAuthority('read:permission')")
	public Todo getById(@PathVariable("id") Integer id) {
		return this.service.getById(id);
	}
	
	@PostMapping
	//@PreAuthorize(value = "hasRole('ADMIN') or hasAuthority('create:permission')")
	public Todo create(@RequestBody TodoCreateRequest request) {
		System.out.println(request);
		return this.service.createTodo(request);
	}
	@DeleteMapping("{id}")
	//@PreAuthorize(value = "hasRole('ADMIN') or hasAuthority('delete:permission')")
	public void delete(@PathVariable("id") Integer id) {
		this.service.deleteTodo(id);
	}
	
	@PutMapping("{id}")
	//@PreAuthorize(value = "hasRole('ADMIN') or hasAuthority('update:permission')")
	public Todo update(@PathVariable("id") Integer id, @RequestBody TodoUpdateRequest request) {
		return this.service.updateTodo(id, request);
	}
}
