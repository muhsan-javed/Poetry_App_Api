package com.muhsantech.poetryappapi.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.muhsantech.poetryappapi.Api.ApiClient;
import com.muhsantech.poetryappapi.Api.ApiInterface;
import com.muhsantech.poetryappapi.Model.PoetryModel;
import com.muhsantech.poetryappapi.R;
import com.muhsantech.poetryappapi.Response.DeleteResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PoetryAdapter extends RecyclerView.Adapter<PoetryAdapter.ViewHolder> {
    Context context;
    List<PoetryModel> poetryModelList;
    ApiInterface apiInterface;

    public PoetryAdapter(Context context, List<PoetryModel> poetryModelList) {
        this.context = context;
        this.poetryModelList = poetryModelList;
        Retrofit retrofit = ApiClient.getClient();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.poetry_list_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.poetName.setText(poetryModelList.get(position).getPoet_name());
        holder.poetryData.setText(poetryModelList.get(position).getPoetry_data());
        holder.date_time.setText(poetryModelList.get(position).getDateTime());

        holder.deleteBtn.setOnClickListener(view -> {
            deletePoetry(poetryModelList.get(position).getId() + "" , position);
        });
    }

    @Override
    public int getItemCount() {
        return poetryModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView poetName, poetryData, date_time;
        Button updateBtn, deleteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            poetName = itemView.findViewById(R.id.poetryName);
            poetryData = itemView.findViewById(R.id.poetry_data);
            date_time = itemView.findViewById(R.id.poetryDateTime);
            updateBtn = itemView.findViewById(R.id.updateBtn);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
        }
    }

    private void deletePoetry(String id, int pose) {
        apiInterface.deletePoetry(id).enqueue(new Callback<DeleteResponse>() {
            @Override
            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                try {
                    if (response != null) {
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();

                        if (response.body().getStatus().equals("1")) {
                            poetryModelList.remove(pose);
                            notifyDataSetChanged();
                        }
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
}
