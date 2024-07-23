package com.cysoft.todo.controller;

import com.cysoft.todo.dto.TodoDto;
import com.cysoft.todo.service.impl.TodoServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class TodoController {

    private TodoServiceImpl todoService;

    @PostMapping
    public ResponseEntity<TodoDto> createTodoDto(@RequestBody TodoDto todoDto) {
        TodoDto savedDto = todoService.addTodo(todoDto);
        return new ResponseEntity<TodoDto>(savedDto, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long id) {
        TodoDto todo = todoService.getTodo(id);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {
       return new ResponseEntity<>(todoService.getAllTodos(), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable Long id) {
        return new ResponseEntity<>( todoService.updateTodo(todoDto, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return new ResponseEntity<>("Todo deleted successfully", HttpStatus.OK);
    }

    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long todoId) {
        return ResponseEntity.ok(todoService.completeTodo(todoId));
    }

    @PatchMapping("{id}/in-complete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") Long todoId) {
        return ResponseEntity.ok(todoService.inCompleteTodo(todoId));
    }
}
