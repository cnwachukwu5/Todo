package com.cysoft.todo.service.impl;

import com.cysoft.todo.dto.TodoDto;
import com.cysoft.todo.entity.Todo;
import com.cysoft.todo.exception.ResourceNotFoundException;
import com.cysoft.todo.mapper.TodoMapper;
import com.cysoft.todo.repository.TodoRepository;
import com.cysoft.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        //Convert TodoDto into Todo JPA entity
        Todo todo = TodoMapper.createTodoFromDto(todoDto, new Todo());

        //Save Todo entity
        Todo savedTodo = todoRepository.save(todo);

        //Convert Todo to TodoDto
        return TodoMapper.createTodoDtoFromTodo(savedTodo, new TodoDto());
    }

    public TodoDto getTodo(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        return todo.map(value -> TodoMapper.createTodoDtoFromTodo(value, new TodoDto()))
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));
    }

    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream().map((todo) -> TodoMapper.createTodoDtoFromTodo(todo, new TodoDto())).collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));

        todo.setCompleted(todoDto.isCompleted());
        todo.setDescription(todoDto.getDescription());
        todo.setTitle(todoDto.getTitle());

        Todo savedTodo = todoRepository.save(todo);
        return TodoMapper.createTodoDtoFromTodo(savedTodo, new TodoDto());
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));
        todoRepository.delete(todo);
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));
        todo.setCompleted(Boolean.TRUE);
        Todo updatedTodo = todoRepository.save(todo);
        return TodoMapper.createTodoDtoFromTodo(updatedTodo, new TodoDto());
    }

    @Override
    public TodoDto inCompleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));
        todo.setCompleted(Boolean.FALSE);
        Todo updatedTodo = todoRepository.save(todo);
        return TodoMapper.createTodoDtoFromTodo(updatedTodo, new TodoDto());
    }
}
