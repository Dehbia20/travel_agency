package fr.lernejo.travelsite;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Date;

public class Temperature {
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date date;
    private double temperature;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
