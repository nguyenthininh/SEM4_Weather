package com.demo.assignment;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HourAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<Weather> listWeather;

    public HourAdapter(Activity activity, List<Weather> listWeather) {
        this.activity = activity;
        this.listWeather = listWeather ;
    }
    public  void reloadData(List<Weather> list){
        this.listWeather = list;   //nãy mk ghi chỗ này là Listweather hay sao ấy
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = activity.getLayoutInflater().inflate(R.layout.item_weather,parent,false);
        HourHolder holder = new HourHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HourHolder hd = (HourHolder) holder;
        Weather model = listWeather.get(position);
        hd.tvTime.setText(convertTime(model.getDateTime()));
        hd.tvTem.setText(model.getTemperature().getValue().toString());
        Glide.with(activity).load(convertIcon(model.getWeatherIcon())).into(hd.tvIcon);

    }

    @Override
    public int getItemCount() {
        return listWeather.size();
    }

    public class HourHolder extends RecyclerView.ViewHolder{
        TextView tvTime, tvTem;
        ImageView tvIcon;
        public HourHolder(@NonNull View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvIcon = itemView.findViewById(R.id.tvIcon);
            tvTem = itemView.findViewById(R.id.tvTem);
        }
    }

    public String convertTime(String inputTime) {
        SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = inFormat.parse(inputTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outFormat = new SimpleDateFormat("ha");
        String goal = outFormat.format(date);
        return goal;
    }
    public String convertIcon(int input){
        String Url = "https://developer.accuweather.com/sites/default/files/";
        if(input < 10){
            return  Url+"0"+input+"-s.png";
        }
        return  Url+input+"-s.png";
    }



}
