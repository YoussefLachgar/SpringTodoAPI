package com.lachgar.springproject.todoapi.todos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Document
public class Todo {
    @Id
    private String id;

    @NotNull(message = "title is required")
    @Size(min= 3, message = "The title must be at least 3 charcters")
    private String title;

    @NotNull(message = "Description is required")
    private String description;

    private long timestamp;

    public Todo(){
        this.timestamp = System.currentTimeMillis();
    }
    public Todo(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.timestamp = System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
