package com.todo.app.dto;

import com.todo.app.model.Todo;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class TodoDTO {

    @Data
    public static class TodoRequest {
        @NotBlank
        private String title;
        private String description;
        private Todo.Priority priority = Todo.Priority.MEDIUM;
        private LocalDateTime dueDate;
    }

    @Data
    public static class TodoResponse {
        private Long id;
        private String title;
        private String description;
        private boolean completed;
        private Todo.Priority priority;
        private LocalDateTime createdAt;
        private LocalDateTime dueDate;

        public static TodoResponse fromTodo(Todo todo) {
            TodoResponse response = new TodoResponse();
            response.setId(todo.getId());
            response.setTitle(todo.getTitle());
            response.setDescription(todo.getDescription());
            response.setCompleted(todo.isCompleted());
            response.setPriority(todo.getPriority());
            response.setCreatedAt(todo.getCreatedAt());
            response.setDueDate(todo.getDueDate());
            return response;
        }
    }
}
