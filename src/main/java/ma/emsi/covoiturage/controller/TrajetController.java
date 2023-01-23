package ma.emsi.covoiturage.controller;

import lombok.RequiredArgsConstructor;
import ma.emsi.covoiturage.model.Trajet;
import ma.emsi.covoiturage.service.TrajetService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/trajet")

public class TrajetController {
    private final TrajetService service;
    @GetMapping("/search")
    public ResponseEntity<List<Trajet>> searchTrajet(@RequestParam("vd") String ville_Depart,
                                                     @RequestParam("va") String ville_Arrivee,
                                                     @RequestParam("d") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date_Trajet)
    {
        service.searchTrajets(ville_Depart,ville_Arrivee, LocalDate.from(date_Trajet));
        return  ResponseEntity.ok().body(service.searchTrajets(ville_Depart,ville_Arrivee, LocalDate.from(date_Trajet)));


}
}
