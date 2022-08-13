package com.todo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.todo.dto.TodoCreateRequest;
import com.todo.dto.TodoUpdateRequest;
import com.todo.entities.Todo;
import com.todo.exceptions.TodoNotFoundException;
import com.todo.repository.TodoRepository;

@Service
public class TodoService {
  private TodoRepository repository;

  public TodoService(TodoRepository repository) {
    this.repository = repository;
  }

  public List<Todo> getAllTodos() {
    List<Todo> todos = new ArrayList<>();
    this.repository.findAll().forEach(todos::add);
    return todos;
  }

  public Todo getById(Integer id) {
    return this.repository.findById(id).orElseThrow(() -> new TodoNotFoundException(id));
  }

  public Todo createTodo(TodoCreateRequest request) {
    Todo todo = new Todo();
    todo.setTitle(request.getTitle());
    todo.setCompleted(false);
    return this.repository.save(todo);
  }

  public void deleteTodo(Integer id) {
    this.getById(id);
    this.repository.deleteById(id);
  }

  public Todo updateTodo(Integer id, TodoUpdateRequest request) {
    Todo todo = this.repository.findById(id).orElseThrow(() -> new TodoNotFoundException(id));

    todo.setCompleted(request.isCompleted());
    todo.setTitle(request.getTitle());
    this.repository.save(todo);
    return todo;
  }
}
