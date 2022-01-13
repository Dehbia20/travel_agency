package fr.lernejo.travelsite;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Inscription {
    private final String userEmail;
    private final String userName;
    private final String weatherExpectation;
    private final int minimumTemperatureDistance;
    private final String userCountry;

    public String getUserCountry() {
        return userCountry;
    }

    public String getWeatherExpectation() {
        return weatherExpectation;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public int getMinimumTemperatureDistance() {
        return minimumTemperatureDistance;
    }

    public Inscription(@JsonProperty("userEmail") String userEmail,
                       @JsonProperty("userName") String userName,
                       @JsonProperty("weatherExpectation") String weatherExpectation,
                       @JsonProperty("minimumTemperatureDistance") int minimumTemperatureDistance,
                       @JsonProperty("userCountry") String userCountry) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.weatherExpectation = weatherExpectation;
        this.minimumTemperatureDistance = minimumTemperatureDistance;
        this.userCountry = userCountry;
    }

}
