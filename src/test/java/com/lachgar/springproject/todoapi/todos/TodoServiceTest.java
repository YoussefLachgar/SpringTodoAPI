package com.lachgar.springproject.todoapi.todos;

import com.lachgar.springproject.todoapi.error.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;


@RunWith(SpringRunner.class)
public class TodoServiceTest {

    @MockBean
    private TodoRepository todoRepository;

    @Autowired
    private TodoService todoService;

    @TestConfiguration
    static class TodoServiceContextConfiguration {
        @Bean
        public TodoService todoService() {
            return new TodoService();
        }
    }

    @Test
    public void whenFindAll_ReturnTodoList() {
        //Mockup
        Todo todo1 = new Todo("1","todo 1" , "welcom to the first todo");
        Todo todo2 = new Todo("2","todo 2" , "welcom to the second todo");
        List<Todo> data = Arrays.asList(todo1,todo2);

        given(todoRepository.findAll()).willReturn(data);

        assertThat(todoService.findAll())
                .hasSize(2)
                .contains(todo1,todo2);

    }

    @Test
    public void whenGetById_TodoShouldBeFound() {
        Todo todo = new Todo("1","todo 1" , "welcom to the first todo");

        given(todoRepository.findById(anyString())).willReturn(Optional.ofNullable(todo));

        Todo result = todoService.getById("1");

        assertThat(result.getTitle()).containsIgnoringCase("todo");
    }

    @Test(expected = NotFoundException.class)
    public void whenInvalidId_TodoShouldNotBeFound() {
        given(todoRepository.findById(anyString())).willReturn(Optional.empty());

        todoService.getById("1");
    }
}
