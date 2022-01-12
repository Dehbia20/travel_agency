package fr.lernejo.travelsite;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/inscription")
public class InscriptionController {
    private List<Inscription> inscriptions = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Inscription inscription) {
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



}
