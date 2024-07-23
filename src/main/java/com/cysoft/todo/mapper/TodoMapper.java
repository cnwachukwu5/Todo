package com.cysoft.todo.mapper;

import com.cysoft.todo.dto.TodoDto;
import com.cysoft.todo.entity.Todo;

public class TodoMapper {

    public static Todo createTodoFromDto(TodoDto todoDto, Todo todo) {
        todo.setId(todoDto.getId());
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());
        return todo;
    }

    public static TodoDto createTodoDtoFromTodo(Todo todo, TodoDto todoDto) {
        todoDto.setId(todo.getId());
        todoDto.setTitle(todo.getTitle());
        todoDto.setDescription(todo.getDescription());
        todoDto.setCompleted(todo.isCompleted());
        return todoDto;
    }
}
