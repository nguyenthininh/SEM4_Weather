package com.demo.assignment;

public class Weather {
    private String DateTime;
    private int WeatherIcon;

    public com.demo.assignment.Temperature getTemperature() {
        return Temperature;
    }

    public void setTemperature(com.demo.assignment.Temperature temperature) {
        Temperature = temperature;
    }

    private String IconPhrase;
    private Temperature Temperature;

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public int getWeatherIcon() {
        return WeatherIcon;
    }

    public void setWeatherIcon(int weatherIcon) {
        WeatherIcon = weatherIcon;
    }

    public String getIconPhrase() {
        return IconPhrase;
    }

    public void setIconPhrase(String iconPhrase) {
        IconPhrase = iconPhrase;
    }


}
