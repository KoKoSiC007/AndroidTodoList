package com.example.todolist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Category {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("todos")
    @Expose
    private ArrayList<Todo> todos;
    @SerializedName("todos")
    @Expose
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @SerializedName("url")
    @Expose
    private String url;

    public Category(String title, ArrayList<Todo> todos) {
        this.title = title;
        this.todos = todos;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTodos() {
        return todos.size();
    }

    public Todo getTodo(int index){
        return todos.get(index);
    }
    public void setTodo(Todo todo) {
        this.todos.add(todo);
    }
}
