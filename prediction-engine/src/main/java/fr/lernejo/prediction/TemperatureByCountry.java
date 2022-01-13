package fr.lernejo.prediction;

import java.util.List;

public class TemperatureByCountry {
    private final String country;
    private final List<Temperature> temperatures;

    public TemperatureByCountry(List<Temperature> list, String country) {
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
