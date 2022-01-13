package fr.lernejo.travelsite;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class InscriptionService {
    private List<Inscription> inscriptions = new ArrayList<>();

    public ResponseEntity<Void> create(Inscription inscription) {
        if (inscription == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if (inscription.getUserEmail() == null || "".equals(inscription.getUserEmail())) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if (inscription.getUserName() == null || "".equals(inscription.getUserName())) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if (inscription.getWeatherExpectation() == null || Arrays.asList("WARMER", "COLDER").stream().noneMatch(s -> s.equals(inscription.getWeatherExpectation()))) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if (inscription.getMinimumTemperatureDistance()  < 0 || inscription.getMinimumTemperatureDistance() > 40) {
            return ResponseEntity.unprocessableEntity().build();
        }
        inscriptions.add(inscription);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public Inscription findByUsername(String username) {
        return inscriptions.stream().filter(i -> i.getUserName().equalsIgnoreCase(username)).findFirst().orElse(null);
    }

    public List<Inscription> inscriptionsCache() {
        return this.inscriptions;
    }
}
