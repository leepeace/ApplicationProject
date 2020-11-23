package com.example.application_smart_umbrella;
import com.example.application_smart_umbrella.model.Weather;
import com.example.application_smart_umbrella.model.Location;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONWeatherParser {
    public static Weather getWeather(String data) throws JSONException {
        Weather weather = new Weather();

        JSONObject object = new JSONObject(data);

        Location location = new Location();

        JSONObject CoordObject = getObject("coord",object);
        location.setLatitude(getFloat("lat", CoordObject));
        location.setLongitude(getFloat("lon", CoordObject));

        JSONObject sysObject = getObject("sys", object);
        location.setCountry(getString("country", sysObject));
       // location.setSunrise(getInt("sunrise", sysObject));
        //location.setSunset(getInt("sunset", sysObject));
        location.setCity(getString("name", object));

        weather.location = location;

        // We get weather info (This is an array)
        JSONArray jArray = object.getJSONArray("weather");

        // We use only the first value
        JSONObject JSONWeather = jArray.getJSONObject(0);
       // weather.currentCondition.setWeatherId(getInt("id", JSONWeather));
        weather.currentCondition.setDescription(getString("description", JSONWeather));
        weather.currentCondition.setCondition(getString("main", JSONWeather));

        JSONObject mainObject = getObject("main", object);
        weather.temperature.setTemperature(getFloat("temp", mainObject));

        return weather;
    }//getWeather

    private static JSONObject getObject(String tagName, JSONObject jObject)  throws JSONException {
        JSONObject subObject = jObject.getJSONObject(tagName);
        return subObject;
    }

    private static String getString(String tagName, JSONObject jObject) throws JSONException {
        return jObject.getString(tagName);
    }

    private static float  getFloat(String tagName, JSONObject jObject) throws JSONException {
        return (float)jObject.getDouble(tagName);
    }

    private static int  getInt(String tagName, JSONObject jObject) throws JSONException {
        return jObject.getInt(tagName);
    }

}//JSONWeatherParser
