package com.example.todolist;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface TodoRepository extends CrudRepository<Todolist, Long> {
    // Filter todos by priority
    List<Todolist> findByPriority(boolean priority);

    // Filter todos by completion status
    List<Todolist> findByStatus(boolean status);

    // Filter todos by both priority and status
    List<Todolist> findByPriorityAndStatus(boolean priority, boolean status);
}

