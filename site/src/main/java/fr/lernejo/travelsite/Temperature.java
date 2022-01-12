package fr.lernejo.travelsite;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class Temperature {
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    private double temperature;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
