package ma.emsi.covoiturage.controller;

import lombok.RequiredArgsConstructor;
import ma.emsi.covoiturage.model.Passager;
import ma.emsi.covoiturage.security.Registration.RegistrationPassagerRequest;
import ma.emsi.covoiturage.security.Registration.RegistrationRequest;
import ma.emsi.covoiturage.security.Registration.RegistrationService;
import ma.emsi.covoiturage.service.PassagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/passager")
public class PassagerController {
    private final PassagerService passagerService;

    @GetMapping("/passagers")
    public ResponseEntity<List<Passager>> getAllPassagers(){
        return ResponseEntity.ok().body(passagerService.getAllPassager());
    }

    @PostMapping("/savePassager")
    public ResponseEntity<Passager> SavePassager(@RequestBody Passager passager)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/passager/savePassager").toUriString());
        return ResponseEntity.created(uri).body(passagerService.savePassager(passager));
    }
    @PostMapping("/update") //PUT
    public ResponseEntity<Passager> updatePassager(@RequestBody Passager passager)
    {
        passagerService.updatePassager(passager);
        return new ResponseEntity<>(passager, HttpStatus.OK);
    }
    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id)
    {
        passagerService.deletePassagee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    private final RegistrationService service;
    @PostMapping("/register")
    public  String register(@RequestBody RegistrationPassagerRequest request)
    {
        return service.registerPassager(request);
    }
    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return service.confirmTokenP(token);
    }
}
