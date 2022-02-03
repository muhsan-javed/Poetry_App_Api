package com.muhsantech.poetryappapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import com.muhsantech.poetryappapi.Api.ApiClient;
import com.muhsantech.poetryappapi.Api.ApiInterface;
import com.muhsantech.poetryappapi.Response.DeleteResponse;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddPoetry extends AppCompatActivity {

    Toolbar toolbar;
    EditText poetName, poetryData;
    AppCompatButton submitBtn;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_poetry);
        setSupportActionBar(toolbar);
        intialization();
        setupToolbar();

        submitBtn.setOnClickListener(view -> {
            String poetryDataString = poetryData.getText().toString();
            String poetNameString = poetName.getText().toString();

            if (poetryDataString.equals("")) {
                poetryData.setError("Field is empty");
            } else {

                if (poetNameString.equals("")) {
                    poetName.setError("field is empty");
                } else {
                    //Toast.makeText(this, "call api", Toast.LENGTH_SHORT).show();
                    callapi(poetryDataString, poetNameString);
                    Toast.makeText(this, "Added your Poetry Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }
            }

        });
    }

    private void intialization() {
        toolbar = findViewById(R.id.add_poetry_toolbar);
        poetName = findViewById(R.id.add_poetry_data);
        poetryData = findViewById(R.id.add_poetry_name);
        submitBtn = findViewById(R.id.submit_data_btn);
        Retrofit retrofit = ApiClient.getClient();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    private void callapi(String poetryData, String poetName) {
        apiInterface.addPoetry(poetryData, poetName).enqueue(new Callback<DeleteResponse>() {
            @Override
            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                try {
                    assert response.body() != null;
                    if (response.body().getStatus().equals("1")) {
                        Toast.makeText(AddPoetry.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddPoetry.this, "Not Added", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e("exp", e.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call<DeleteResponse> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
            }
        });
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(view -> finish());
    }
}