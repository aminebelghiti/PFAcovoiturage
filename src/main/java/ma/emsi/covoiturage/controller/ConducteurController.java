package ma.emsi.covoiturage.controller;

import ma.emsi.covoiturage.model.Conducteur;
import ma.emsi.covoiturage.service.ConducteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Conducteur")
public class ConducteurController {
    @Autowired
    private ConducteurService service;

    @GetMapping("/ListConducteur")
    public ResponseEntity<List<Conducteur>> AllConducteur()
    {
        return ResponseEntity.ok().body(service.getAllConducteur());
    }

}
