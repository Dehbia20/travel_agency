package fr.lernejo.travelsite;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/travels")
public class TravelController {

    @GetMapping
    public ResponseEntity<List<Travel>> suggestion(@RequestParam(name = "userName") String userName) {
        Travel travel = new Travel();
        travel.setName("test");
        travel.setTemperature(12.2f);
        return ResponseEntity.ok(Arrays.asList(travel));
    }
}
