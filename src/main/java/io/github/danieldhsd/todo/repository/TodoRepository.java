package io.github.danieldhsd.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.danieldhsd.todo.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
