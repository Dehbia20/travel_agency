package fr.lernejo.travelsite;

import java.util.List;

public class TemperatureByCountry {
    private String country;
    private List<Temperature> temperatures;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Temperature> getTemperatures() {
        return temperatures;
    }

    public void setTemperatures(List<Temperature> temperatures) {
        this.temperatures = temperatures;
    }
}
