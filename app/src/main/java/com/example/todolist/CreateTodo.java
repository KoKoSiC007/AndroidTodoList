package com.example.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class CreateTodo extends AppCompatActivity implements View.OnClickListener{
    ArrayAdapter<String> adapter;
    Spinner spinner;
    EditText text;
    Button create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crate_todo);
        getSupportActionBar().hide();
        spinner = (Spinner)findViewById(R.id.categorys);
        text = (EditText)findViewById(R.id.text);
        create = (Button)findViewById(R.id.create);
        create.setOnClickListener(this);
        Intent i = getIntent();
        ArrayList<String> list = i.getStringArrayListExtra("titls");
        System.out.print(list);
        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create:
                JsonObject json = new JsonObject();
                JsonObject todo = new JsonObject();
                todo.addProperty("todo_id",""+(spinner.getSelectedItemPosition()+1));
                todo.addProperty("text", String.valueOf(text.getText()));
                System.out.print(todo);
                json.add("todo", todo);
                Ion.with(this)
                        .load("https://ancient-bayou-98389.herokuapp.com/todos")
                        .setJsonObjectBody(json)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                            }
                        });
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }
}
