package com.todo.app.controller;

import com.todo.app.dto.TodoDTO.*;
import com.todo.app.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping
    public ResponseEntity<List<TodoResponse>> getAllTodos() {
        return ResponseEntity.ok(todoService.getAllTodos());
    }

    @PostMapping
    public ResponseEntity<TodoResponse> createTodo(@Valid @RequestBody TodoRequest request) {
        return ResponseEntity.ok(todoService.createTodo(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> updateTodo(@PathVariable Long id,
                                                    @Valid @RequestBody TodoRequest request) {
        try {
            return ResponseEntity.ok(todoService.updateTodo(id, request));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/toggle")
    public ResponseEntity<TodoResponse> toggleComplete(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(todoService.toggleComplete(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable Long id) {
        try {
            todoService.deleteTodo(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
