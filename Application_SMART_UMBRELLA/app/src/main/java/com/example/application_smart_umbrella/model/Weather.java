package com.example.application_smart_umbrella.model;

public class Weather {
    public CurrentCondition currentCondition = new CurrentCondition();
    public Temperature temperature = new Temperature();
    public Location location;

    public static class CurrentCondition{
        private int weatherId;
        private String condition;
        private String description;

        public int getWeatherId(){
            return weatherId;
        }
        public void setWeatherId(int weatherId){
            this.weatherId = weatherId;
        }
        public String getCondition() {
            return condition;
        }
        public void setCondition(String condition) {
            this.condition = condition;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }

    }//CurrentCondition

    public static class Temperature{
        private float temperature;
        private float minTemperature;
        private float maxTemperature;

        public float getTemperature() {
            return temperature;
        }
        public void setTemperature(float temperature) {
            this.temperature = temperature;
        }
    }//Temperature

}//Weather
