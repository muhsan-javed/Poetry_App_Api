package com.muhsantech.poetryappapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.muhsantech.poetryappapi.Api.ApiClient;
import com.muhsantech.poetryappapi.Api.ApiInterface;
import com.muhsantech.poetryappapi.Response.DeleteResponse;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UpdatePoetry extends AppCompatActivity {

    Toolbar toolbar;
    EditText poetryData;
    AppCompatButton updateBtn;
    ApiInterface apiInterface;
    int poetryId;
    String poetryDataString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_poetry);
        intialization();
        setupToolbar();

        updateBtn.setOnClickListener(view -> {
            String updatePoetryDataString = poetryData.getText().toString();

            if (updatePoetryDataString.equals("")) {
                poetryData.setError("Field is empty");
            } else {
                //Toast.makeText(this, "call api", Toast.LENGTH_SHORT).show();
                callapi(updatePoetryDataString, poetryId + "");
                Toast.makeText(this, "Added your Poetry Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }

        });
    }

    private void intialization() {
        toolbar = findViewById(R.id.update_poetry_toolbar);
        poetryData = findViewById(R.id.update_poetry_data);
        updateBtn = findViewById(R.id.updateBTN);

        poetryId = getIntent().getIntExtra("p_id", 0);
        poetryDataString = getIntent().getStringExtra("p_data");

        poetryData.setText(poetryDataString);

        Retrofit retrofit = ApiClient.getClient();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> finish());
    }

    private void callapi(String poetryData, String id) {
        apiInterface.updatePoetry(poetryData, id).enqueue(new Callback<DeleteResponse>() {
            @Override
            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                try {
                    assert response.body() != null;
                    if (response.body().getStatus().equals("1")) {
                        Toast.makeText(UpdatePoetry.this, "Update Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(UpdatePoetry.this, "Not Update", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e("Update exp", e.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call<DeleteResponse> call, Throwable t) {
                Log.e("Update failure", t.getLocalizedMessage());
            }
        });
    }
}