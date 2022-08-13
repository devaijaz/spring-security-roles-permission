package com.todo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.todo.entities.Todo;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Integer> {

}
