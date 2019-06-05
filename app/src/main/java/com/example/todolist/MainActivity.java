package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Category> tasks = new ArrayList<>();
    FloatingActionButton create;
    private ArrayList<String> titls = new ArrayList<>();

    public Context getContext1() {
        return getApplicationContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        create = (FloatingActionButton) findViewById(R.id.add);
        create.setOnClickListener(this);



        TypefaceUtil.overrideFont(getApplicationContext(), "SANS_SERIF", "fonts/OpenSans-Ligth.ttf");
        Ion.with(this)
                .load("https://ancient-bayou-98389.herokuapp.com/projects.json")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        parsing(result);
                        ExpandableListView category = (ExpandableListView)findViewById(R.id.Categorys);
                        CategoricalAdapter adapter = new CategoricalAdapter(getApplicationContext(), tasks);
                        category.setAdapter(adapter);
                        System.out.print(tasks);
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                Intent intent = new Intent(this, CreateTodo.class);
                intent.putStringArrayListExtra("titls", titls);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }
    void parsing(JsonArray json){
        for( JsonElement category: json){
            Category target = new Category();
            titls.add(category.getAsJsonObject().get("title").getAsString());
            target.setId(category.getAsJsonObject().get("id").getAsInt());
            target.setTitle(category.getAsJsonObject().get("title").getAsString());
            ArrayList<Todo> todos = new ArrayList<>();
            for (JsonElement todo: category.getAsJsonObject().get("todos").getAsJsonArray()){
                Todo task = new Todo();
                task.setId(todo.getAsJsonObject().get("id").getAsInt());
                task.setText(todo.getAsJsonObject().get("text").getAsString());
                task.setIsCompleted(todo.getAsJsonObject().get("isCompleted").getAsBoolean());
                task.setProjectId(todo.getAsJsonObject().get("project_id").getAsInt());
                todos.add(task);
            }
            target.setTodos(todos);
            System.out.print(target.getId());
            tasks.add(target);
        }
    }
}
