package fr.lernejo.prediction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Temperature {
    @JsonFormat(pattern = "dd/MM/yyyy")
    private final Date date;
    private final double temperature;

    public Temperature(@JsonProperty("date") Date d, @JsonProperty("temperature") double t) {
        this.temperature = t;
        this.date = d;
    }

    public Date getDate() {
        return date;
    }

    public double getTemperature() {
        return temperature;
    }

}
