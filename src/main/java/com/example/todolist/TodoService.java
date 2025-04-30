package com.example.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    // Add a new todo
    public Todolist addTodo(Todolist todo) {
        return todoRepository.save(todo); // Save new todo
    }

    // Get all todos
    public List<Todolist> getTodos() {
        return (List<Todolist>) todoRepository.findAll(); // Get all todos
    }

    // Get a todo by ID
    public Todolist getTodoById(Long id) {
        return todoRepository.findById(id).orElse(null);
    }

    // Update a todo
    public Todolist updateTodo(Long id, Todolist updatedTodo) {
        Todolist existingTodo = todoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Todo not found"));

        existingTodo.setTitle(updatedTodo.getTitle());
        existingTodo.setDescription(updatedTodo.getDescription());
        existingTodo.setPriority(updatedTodo.getPriority());
        existingTodo.setStatus(updatedTodo.getStatus());

        return todoRepository.save(existingTodo);
    }

    // Delete a todo
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    // Filter todos by priority
    public List<Todolist> getTodosByPriority(boolean priority) {
        return todoRepository.findByPriority(priority);
    }


    // Filter todos by completed status
    public List<Todolist> getTodosByStatus(boolean status) {
        return todoRepository.findByStatus(status);
    }


    // Filter todos by both priority and status
    public List<Todolist> getTodosByPriorityAndStatus(boolean priority, boolean status) {
        return todoRepository.findByPriorityAndStatus(priority, status);
    }

}
