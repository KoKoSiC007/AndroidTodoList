package com.example.todolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ExpandableListView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Category> tasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
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

    void parsing(JsonArray json){
        for( JsonElement category: json){
            Category target = new Category();
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
