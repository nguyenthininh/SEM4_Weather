package com.demo.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView tvTemperature, tvIconPhrase;
    RecyclerView recyclerView;
    List<Weather> listWeather;
    HourAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvIconPhrase = findViewById(R.id.tvIconPhrase);
        tvTemperature = findViewById(R.id.tvTemperature);

        //B1: Data source
        getListData();

        //B2: Adapter
       listWeather = new ArrayList<>();
       adapter = new HourAdapter(this,listWeather);


        //B3: layout Manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false);

        //B4: Recycleview
       recyclerView = findViewById(R.id.rvTemperature);
       recyclerView.setLayoutManager(layoutManager);
       recyclerView.setAdapter(adapter);

    }

    public  void getListData(){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(APIManager.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                 .build();
        APIManager service = retrofit.create(APIManager.class);
        service.getListData().enqueue(new Callback<List<Weather>>() {
            @Override
            public void onResponse(Call<List<Weather>> call, Response<List<Weather>> response) {
                if (response.body() != null){
                    listWeather = response.body();
                    Weather weather = listWeather.get(0);
                    tvTemperature.setText(weather.getTemperature().getValue().toString() + "°");
                    tvIconPhrase.setText(weather.getIconPhrase());
                    adapter.reloadData(listWeather);

                }
            }

            @Override
            public void onFailure(Call<List<Weather>> call, Throwable t) {
                tvIconPhrase.setText("lỗi");
            }
        });
    }


}