package ma.emsi.covoiturage.controller;

import lombok.AllArgsConstructor;
import ma.emsi.covoiturage.security.Registration.RegistrationRequest;
import ma.emsi.covoiturage.security.Registration.RegistrationService;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping( "/conducteur/register")
public class RegistrationController {

    private final RegistrationService service;
    @PostMapping
    public  String register(@RequestBody RegistrationRequest request)
    {
        return service.register(request);
    }
    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return service.confirmToken(token);
    }
}
