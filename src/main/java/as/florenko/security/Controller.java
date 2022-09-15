package as.florenko.security;

import as.florenko.security.repository.Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/")
public class Controller {
    private final Repository repository;

    public Controller(Repository repository) {
        this.repository = repository;
    }

    @Secured("ROLE_CITY")
    @GetMapping("/persons/by-city")
    public ResponseEntity<Object> getUserByCity(@RequestParam String city) {
        return repository.findByCity(city);
    }

    @RolesAllowed("ROLE_AGE")
    @GetMapping("/persons/by-age")
    public ResponseEntity<Object> getUserByAge(@RequestParam int age) {
        return repository.findByAge(age);
    }

    @PreAuthorize("hasRole('CITY') or hasRole('NAME')")
    @GetMapping("/persons/by-name")
    public ResponseEntity<Object> getUserByName(@RequestParam String name, String surname) {
        return repository.findByNameAdnSurname(name, surname);
    }

    @PostAuthorize("#username == authentication.principal.username")
    @GetMapping("/persons/checkname")
    public String getUserName(@RequestParam String username) {
        return "Пользователь " + username + " подтвержден";
    }
}
