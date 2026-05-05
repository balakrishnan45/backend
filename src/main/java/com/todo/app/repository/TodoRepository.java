package com.todo.app.repository;

import com.todo.app.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUserId(Long userId);
    List<Todo> findByUserIdAndCompleted(Long userId, boolean completed);
    Optional<Todo> findByIdAndUserId(Long id, Long userId);
}
