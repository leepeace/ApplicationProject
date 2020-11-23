package com.example.application_smart_umbrella;

import java.io.BufferedReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStreamReader;

public class WeatherHttpClient {
    private static String WeatherURL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static String API_KEY = "2f8a4c107da4024108d3f707127dabae";
    public String getWeatherData(String location){
        HttpURLConnection connect = null;
        InputStream input = null;
        try{
            connect = (HttpURLConnection)(new URL(WeatherURL + location + "&APPID=" + API_KEY + "&units=metric")).openConnection();
            connect.setRequestMethod("GET");
            connect.setDoInput(true);
            connect.setDoInput(true);
            connect.connect();

            StringBuffer buffer = new StringBuffer();
            input = connect.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(input));
            String line = null;
            while( (line = read.readLine()) != null){
                buffer.append(line + "\r\n");
            }
            input.close();
            connect.disconnect();
            return buffer.toString();
        }catch(Throwable t){
            t.printStackTrace();
        }finally{
            try{
                input.close();
            }catch(Throwable t){}
            try{
                connect.disconnect();
            }catch(Throwable t){}
        }
        return null;
    }//getWeatherData

}//WeatherHttpClient
