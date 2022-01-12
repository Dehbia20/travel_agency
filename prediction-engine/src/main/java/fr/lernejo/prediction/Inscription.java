package fr.lernejo.prediction;

public class Inscription {
    private String userEmail;
    private String userName;
    private String weatherExpectation;
    private int minimumTemperatureDistance;

    public String getWeatherExpectation() {
        return weatherExpectation;
    }

    public void setWeatherExpectation(String weatherExpectation) {
        this.weatherExpectation = weatherExpectation;
    }
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public int getMinimumTemperatureDistance() {
        return minimumTemperatureDistance;
    }

    public void setMinimumTemperatureDistance(int minimumTemperatureDistance) {
        this.minimumTemperatureDistance = minimumTemperatureDistance;
    }
}
