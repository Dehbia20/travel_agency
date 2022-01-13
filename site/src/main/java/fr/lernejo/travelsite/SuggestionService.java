package fr.lernejo.travelsite;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class SuggestionService {
    private final PredictionEngineClient client;
    private final List<Travel> matching = new ArrayList<>();

    public SuggestionService(@Autowired PredictionEngineClient client) {
        this.client = client;
    }

    public List<Travel> getExpectation(String weatherExpectation, int minimumTemperatureDistance, String userCountry) {
        matching.clear();
        TemperatureByCountry userTbc = findByCountry(userCountry);
        if (userTbc == null) {
            throw new RuntimeException();
        }
        double userTemp = avg(userTbc.getTemperatures());
        List<String> lines = loadCountries();

        for (String l : lines) {
            TemperatureByCountry tbc = findByCountry(l);
            handle(tbc, weatherExpectation, minimumTemperatureDistance, userTemp);
        }
        return matching;
    }

    private void handle(TemperatureByCountry tbc, String weatherExpectation, int minimumTemperatureDistance, double userTemp) {
        if (tbc != null) {
            double avg = avg(tbc.getTemperatures());
            boolean matches = matches(avg, weatherExpectation, minimumTemperatureDistance, userTemp);
            if (matches) {
                Travel travel = new Travel(tbc.getCountry(), avg);
                matching.add(travel);
            }
        }
    }
    private List<String> loadCountries() {
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("countries.txt")) {
            String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            return content.lines().toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private TemperatureByCountry findByCountry(String country) {
        try {
            Response<TemperatureByCountry> response = client.getTemperatureByCountry(country).execute();
            if (!response.isSuccessful()) {
                return null;
            }
            return response.body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private double avg(List<Temperature> temperatures) {
        if (temperatures != null) {
            return temperatures.stream().mapToDouble(Temperature::getTemperature).average().getAsDouble();
        }
        return 0.0;
    }

    private boolean matches(double avg, String weatherExpectation, int minimumTemperatureDistance, double countryTemp) {
        boolean matches = false;
        if (weatherExpectation.equalsIgnoreCase("WARMER")) {
            matches = avg >= (countryTemp + minimumTemperatureDistance);
        } else {
            matches = avg <= (countryTemp - minimumTemperatureDistance);
        }
        return matches;
    }
}
