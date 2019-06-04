package com.example.todolist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class ServerAnswer {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("login")
    @Expose
    private String login;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("surname")
    @Expose
    private String surname;

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}

