package fr.lernejo.travelsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inscription")
public class InscriptionController {
    @Autowired
    private InscriptionService inscriptionService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Inscription inscription) {
        return inscriptionService.create(inscription);
    }



}
