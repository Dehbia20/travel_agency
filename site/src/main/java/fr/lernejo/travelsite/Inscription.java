package fr.lernejo.travelsite;

public class Inscription {
    private final String userEmail;
    private final String userName;
    private final String weatherExpectation;
    private final int minimumTemperatureDistance;

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

    public Inscription(String userEmail, String userName, String weatherExpectation, int minimumTemperatureDistance) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.weatherExpectation = weatherExpectation;
        this.minimumTemperatureDistance = minimumTemperatureDistance;
    }
}
