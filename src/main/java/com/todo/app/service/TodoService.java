package com.todo.app.service;

import com.todo.app.dto.TodoDTO.*;
import com.todo.app.model.Todo;
import com.todo.app.model.User;
import com.todo.app.repository.TodoRepository;
import com.todo.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    UserRepository userRepository;

    private User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
    }

    public List<TodoResponse> getAllTodos() {
        User user = getCurrentUser();
        return todoRepository.findByUserId(user.getId())
                .stream()
                .map(TodoResponse::fromTodo)
                .collect(Collectors.toList());
    }

    public TodoResponse createTodo(TodoRequest request) {
        User user = getCurrentUser();
        Todo todo = new Todo();
        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        todo.setPriority(request.getPriority());
        todo.setDueDate(request.getDueDate());
        todo.setUser(user);
        return TodoResponse.fromTodo(todoRepository.save(todo));
    }

    public TodoResponse updateTodo(Long id, TodoRequest request) {
        User user = getCurrentUser();
        Todo todo = todoRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        todo.setPriority(request.getPriority());
        todo.setDueDate(request.getDueDate());
        return TodoResponse.fromTodo(todoRepository.save(todo));
    }

    public TodoResponse toggleComplete(Long id) {
        User user = getCurrentUser();
        Todo todo = todoRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        todo.setCompleted(!todo.isCompleted());
        return TodoResponse.fromTodo(todoRepository.save(todo));
    }

    public void deleteTodo(Long id) {
        User user = getCurrentUser();
        Todo todo = todoRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        todoRepository.delete(todo);
    }
}
