package io.github.danieldhsd.todo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.danieldhsd.todo.model.Todo;
import io.github.danieldhsd.todo.repository.TodoRepository;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin("http://localhost:4200")
public class TodoContoller {

	@Autowired
	private TodoRepository todoRepository;
	
	@PostMapping
	public Todo create(@RequestBody Todo todo) {
		return todoRepository.save(todo);
	}
	
	@GetMapping("{id}")
	public Todo getById(@PathVariable Long id) {
		return todoRepository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping
	public List<Todo> getAll() {
		return todoRepository.findAll();
	}
	
	@DeleteMapping("{id}")
	public void deleteById(@PathVariable Long id) {
		todoRepository.deleteById(id);
	}
	
	@PatchMapping("{id}/done")
	public Todo markAsDone(@PathVariable Long id) {
		return this.todoRepository.findById(id).map(todo -> {
			todo.setDone(true);
			todo.setDoneAt(LocalDateTime.now());
			return this.todoRepository.save(todo);
		
		}).orElse(null);
	}
}
