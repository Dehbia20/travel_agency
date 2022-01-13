package fr.lernejo.travelsite;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Date;

public class Temperature {
    @JsonFormat(pattern = "dd/MM/yyyy")
    private final Date date;
    private final double temperature;

    public Date getDate() {
        return date;
    }

    public Temperature(@JsonProperty("date") Date date, @JsonProperty("temperature") double temperature) {
        this.date = date;
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }

}
