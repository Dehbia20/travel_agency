package fr.lernejo.travelsite;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class InscriptionService {
    private final List<Inscription> inscriptions = new ArrayList<>();

    public ResponseEntity<Void> create(Inscription inscription) {
        try {
            validate(inscription == null);
            validate(inscription.getUserEmail() == null || "".equals(inscription.getUserEmail()));
            validate(inscription.getUserName() == null || "".equals(inscription.getUserName()));
            validate(inscription.getWeatherExpectation() == null || Arrays.asList("WARMER", "COLDER").stream().noneMatch(s -> s.equals(inscription.getWeatherExpectation())));
            validate(inscription.getMinimumTemperatureDistance()  < 0 || inscription.getMinimumTemperatureDistance() > 40);
            validate(inscription.getUserCountry() == null || "".equals(inscription.getUserCountry()));
        } catch(IllegalArgumentException e) {
            return ResponseEntity.unprocessableEntity().build();
        }

        inscriptions.add(inscription);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private void validate(boolean condition) {
        if (condition) {
            throw new IllegalArgumentException("Invalid argument");
        }
    }

    public Inscription findByUsername(String username) {
        return inscriptions.stream().filter(i -> i.getUserName().equalsIgnoreCase(username)).findFirst().orElse(null);
    }

    public List<Inscription> inscriptionsCache() {
        return this.inscriptions;
    }
}
