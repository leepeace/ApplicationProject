package com.example.application_smart_umbrella;

import androidx.appcompat.app.AppCompatActivity;
import com.example.application_smart_umbrella.model.Weather;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;



public class MainActivity extends AppCompatActivity {
    TextView weatherText,temperatureText,cityText;
    Button sensorButton,controlButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String city = "Seoul";

        sensorButton = (Button)findViewById(R.id.sensor);
        controlButton = (Button)findViewById(R.id.control);
        weatherText = (TextView)findViewById(R.id.weatherCondition);
        temperatureText = (TextView)findViewById(R.id.temperature);
        cityText = (TextView)findViewById(R.id.cityName);

        controlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ControlActivity.class);
                startActivity(intent);
            }
        });
        sensorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, SensorActivity.class);
                startActivity(intent2);
            }
        });

        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(city);

    }//onCreate
    @SuppressLint("StaticFieldLeak")
    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params){
            Weather weather = new Weather();
            String data = ( (new WeatherHttpClient()).getWeatherData(params[0]));

            try{
                weather = JSONWeatherParser.getWeather(data);
            }catch(JSONException e){
                e.printStackTrace();
            }
            return weather;

        }//doInBackground

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(Weather weather){
            super.onPostExecute(weather);

            cityText.setText(weather.location.getCity() + "," + weather.location.getCountry());
            weatherText.setText(weather.currentCondition.getCondition());
            temperatureText.setText("" + Math.round(weather.temperature.getTemperature()) + "°C");

        }//onPostExecute

    }//JSONWeatherTask

}//MainActivity

