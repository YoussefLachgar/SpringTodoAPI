package com.lachgar.springproject.todoapi.todos;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    //  this is just a smple test to macke sure that the end point work well
    @Test
    public void testGetAllTodos() throws Exception {
        mockMvc.perform(get("/api/v1/todos")).andExpect(status().isOk());
    }
    @Test
    public void whenGetAllTodos_thenReturnJsonArray() throws Exception {
        Todo todo1 = new Todo("1","the first todo","tis is the description of the first todo");
        Todo todo2 = new Todo("2","the secend todo","tis is the description of the second todo");
        List<Todo> data = Arrays.asList(todo1,todo2);

        given(todoService.findAll()).willReturn(data);

        mockMvc.perform(get("/api/v1/todos").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", equalTo(todo1.getTitle())));
    }

    @Test
    public void whenPostTodo_thenCreateTodo() throws Exception {
        Todo todo = new Todo("1","the first todo","tis is the description of the first todo");

        given(todoService.save(Mockito.any(Todo.class))).willReturn(todo);

        ObjectMapper mapper = new ObjectMapper();


        mockMvc.perform(
                post("/api/v1/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(todo))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is(todo.getTitle())))
                .andExpect(jsonPath("$.description",is(todo.getDescription())));


    }

}
