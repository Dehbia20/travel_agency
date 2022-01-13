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

    public SuggestionService(@Autowired PredictionEngineClient client) {
        this.client = client;
    }

    public List<Travel> getExpectation(String weatherExpectation, int minimumTemperatureDistance) {
        String content = null;
        List<Travel> matching = new ArrayList<>();
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("countries.txt");
        ) {
            content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            List<String> lines = content.lines().toList();

            for (String l : lines) {
                Response<TemperatureByCountry> response = client.getTemperatureByCountry(l).execute();
                if (!response.isSuccessful()) {
                    TemperatureByCountry t = response.body();

                    double avg = t.getTemperatures().stream().mapToDouble(Temperature::getTemperature).average().getAsDouble();
                    boolean matches = matches(avg, weatherExpectation, minimumTemperatureDistance);

                    if (matches) {
                        Travel travel = new Travel(t.getCountry(), avg);
                        matching.add(travel);
                    }
                }
            }

            return matching;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean matches(double avg, String weatherExpectation, int minimumTemperatureDistance) {
        boolean matches = false;
        if (weatherExpectation.equalsIgnoreCase("WARMER")) {
            matches = avg > minimumTemperatureDistance;
        } else {
            matches = avg < minimumTemperatureDistance;
        }
        return matches;
    }
}
