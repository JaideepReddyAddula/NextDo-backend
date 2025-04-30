package com.example.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://jaideepreddyaddula.github.io")
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    // Get all todos
    @GetMapping
    public List<Todolist> getTodos(
            @RequestParam(required = false) Boolean priority, 
            @RequestParam(required = false) Boolean status) {

        // If both priority and status are provided, filter by both
        if (priority != null && status != null) {
            return todoService.getTodosByPriorityAndStatus(priority, status);
        }

        // If only priority is provided, filter by priority
        if (priority != null) {
            return todoService.getTodosByPriority(priority);
        }

        // If only status is provided, filter by status
        if (status != null) {
            return todoService.getTodosByStatus(status);
        }

        // If no filter is provided, return all todos
        return todoService.getTodos();
    }

    // Fetch a single todo by id
    @GetMapping("/{id}")
    public ResponseEntity<Todolist> getTodoById(@PathVariable Long id) {
        Todolist todo = todoService.getTodoById(id);
        if (todo != null) {
            return ResponseEntity.ok(todo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Add a new todo
    @PostMapping
    public Todolist addTodo(@RequestBody Todolist todo) {
        return todoService.addTodo(todo);
    }

    // Mark a todo as completed or update
    @PutMapping("/{id}")
    public Todolist updateTodo(@PathVariable Long id, @RequestBody Todolist updatedTodo) {
        return todoService.updateTodo(id, updatedTodo);
    }

    // Delete a todo
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
    }
}
