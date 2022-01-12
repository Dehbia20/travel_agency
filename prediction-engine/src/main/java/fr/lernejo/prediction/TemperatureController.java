package fr.lernejo.prediction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/temperature")
public class TemperatureController {

    @Autowired
    private TemperatureService temperatureService;
    @GetMapping
    public ResponseEntity<TemperatureByCountry> getTemperature(@RequestParam("country") String country) {
        try {
            return ResponseEntity.ok(temperatureService.getTemperatureOn2DaySliding(country));
        } catch (UnknownCountryException e) {
            return ResponseEntity.status(417).build();
        }
    }
}
