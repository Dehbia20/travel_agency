package fr.lernejo.travelsite;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TemperatureByCountry {
    private final String country;
    private final List<Temperature> temperatures;

    public TemperatureByCountry(@JsonProperty("country") String country, @JsonProperty("temperatures") List<Temperature> temperatures) {
        this.country = country;
        this.temperatures = temperatures;
    }

    public String getCountry() {
        return country;
    }
    public List<Temperature> getTemperatures() {
        return temperatures;
    }
}
