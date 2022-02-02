package com.muhsantech.poetryappapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.muhsantech.poetryappapi.Adapter.PoetryAdapter;
import com.muhsantech.poetryappapi.Api.ApiClient;
import com.muhsantech.poetryappapi.Api.ApiInterface;
import com.muhsantech.poetryappapi.Model.PoetryModel;
import com.muhsantech.poetryappapi.Response.GetPoetryResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PoetryAdapter poetryAdapter;
    List<PoetryModel> list = new ArrayList<>();
    Retrofit retrofit;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.poetryRecyclerView);
        retrofit = ApiClient.getClient();
        apiInterface = retrofit.create(ApiInterface.class);

        apiInterface.getPoetry().enqueue(new Callback<GetPoetryResponse>() {
            @Override
            public void onResponse(Call<GetPoetryResponse> call, Response<GetPoetryResponse> response) {
                try {
                    if (response != null){
                        if (response.body().getStatus().equals("1")){
                          setAdapter(response.body().getData());
                        }else {
                            Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (Exception e) {
                    Log.e("exp",e.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call<GetPoetryResponse> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
            }
        });

//        list.add(new PoetryModel(1,"Muhabat mukamal milne ka nahi \n Muhabat mukamal hone ka naam hai","Miraz Galib","02-02-2022"));
//        list.add(new PoetryModel(2,"Muhabat mukamal milne ka nahi \n Muhabat mukamal hone ka naam hai","Miraz Galib","02-02-2022"));
//        list.add(new PoetryModel(3,"Muhabat mukamal milne ka nahi \n Muhabat mukamal hone ka naam hai","Miraz Galib","02-02-2022"));
//        list.add(new PoetryModel(4,"Muhabat mukamal milne ka nahi \n Muhabat mukamal hone ka naam hai","Miraz Galib","02-02-2022"));
//        list.add(new PoetryModel(5,"Muhabat mukamal milne ka nahi \n Muhabat mukamal hone ka naam hai","Miraz Galib","02-02-2022"));
//        list.add(new PoetryModel(6,"Muhabat mukamal milne ka nahi \n Muhabat mukamal hone ka naam hai","Miraz Galib","02-02-2022"));
//        list.add(new PoetryModel(7,"Muhabat mukamal milne ka nahi \n Muhabat mukamal hone ka naam hai","Miraz Galib","02-02-2022"));


    }

    public void setAdapter(List<PoetryModel> poetryModelList){
        poetryAdapter = new PoetryAdapter(MainActivity.this,poetryModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(poetryAdapter);
    }
}