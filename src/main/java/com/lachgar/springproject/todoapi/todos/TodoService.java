package com.lachgar.springproject.todoapi.todos;

import com.lachgar.springproject.todoapi.error.ConflictException;
import com.lachgar.springproject.todoapi.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Todo getById(String id) {
        try{
            return todoRepository.findById(id).get();
        }
        catch (NoSuchElementException ex){
            throw new NotFoundException(String.format("There is no recorde with the id [%s] was found in our database",id));
        }
    }

    public Todo save(Todo todo) {
        if (todoRepository.findByTitle(todo.getTitle()) != null){
            throw new ConflictException("Another record with the same title existes");
        }
        return todoRepository.insert(todo);
    }

    public void delete(String id){
        todoRepository.deleteById(id);
    }
}
