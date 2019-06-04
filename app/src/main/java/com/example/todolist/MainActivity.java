package com.example.todolist;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ExpandableListView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Todo_List> aaaa;
    private Categorys flex;

    void connect(){

        Zalupa.getInstance().sendRequest().call().enqueue(new Callback<ArrayList<Todo_List>>() {
            @Override
            public void onResponse(Call<ArrayList<Todo_List>> call, Response<ArrayList<Todo_List>> response) {
                aaaa = response.body();
                Log.d("ВЫВОД",aaaa.get(0).getTodos().get(0).getText());
                ExpandableListView category = (ExpandableListView)findViewById(R.id.Categorys);
                CategoricalAdapter adapter = new CategoricalAdapter(getApplicationContext(), aaaa);
                category.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Todo_List>> call, Throwable t) {
                Log.d("ОШИБКА",t.toString());
            }
        });



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
//        if (android.os.Build.VERSION.SDK_INT > 9)
//        {
//            StrictMode.ThreadPolicy policy = new
//                    StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//        }
        TypefaceUtil.overrideFont(getApplicationContext(), "SANS_SERIF", "fonts/OpenSans-Ligth.ttf");
        setTitle("Задачи");
//        Retrofit.getInstance().sendRequest().call().enqueue();
//        getUnsafeOkHttpClient();
//        connect();

        Todo a =  new Todo();
        a.setId(100);
        a.setText("Посмотреть WWDC");
        a.setProjectId(1);

        Ion.with(this)
                .load("https://ancient-bayou-98389.herokuapp.com/projects.json")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        System.out.println(result);
//                        Log.d("Результат", result.);
//                        Log.d("Результат", e.getMessage());
                    }
                });
//        Zalupa.getInstance().sendRequest().sendTODO_object(a).enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//            }
//        });

        //Создаем адаптер и передаем context и список с данными
//        CategoricalAdapter adapter = new CategoricalAdapter(getApplicationContext(), table);
//        category.setAdapter(adapter);
    }


}
