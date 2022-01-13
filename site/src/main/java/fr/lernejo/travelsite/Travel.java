package fr.lernejo.travelsite;

public class Travel {
    private final String name;
    private final double temperature;

    public String getName() {
        return name;
    }


    public double getTemperature() {
        return temperature;
    }

    public Travel(String name, double temperature) {
        this.name = name;
        this.temperature = temperature;
    }
}
