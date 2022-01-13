package fr.lernejo.travelsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/travels")
public class TravelController {

    @Autowired
    private SuggestionService suggestionService;
    @Autowired
    private InscriptionService inscriptionService;

    @GetMapping
    public ResponseEntity<List<Travel>> suggestion(@RequestParam(name = "userName") String userName) {
        Inscription inscription = inscriptionService.findByUsername(userName);
        if (inscription == null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(suggestionService.getExpectation(inscription.getWeatherExpectation(), inscription.getMinimumTemperatureDistance()));
    }
}
