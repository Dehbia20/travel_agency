package fr.lernejo.prediction;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TemperatureByCountry {
    private final String country;
    private final List<Temperature> temperatures;

    public TemperatureByCountry(@JsonProperty("temperatures") List<Temperature> list, @JsonProperty("country") String country) {
        this.country = country;
        this.temperatures = list;
    }

    public String getCountry() {
        return country;
    }

    public List<Temperature> getTemperatures() {
        return temperatures;
    }

}
