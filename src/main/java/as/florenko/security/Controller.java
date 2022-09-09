package as.florenko.security;

import as.florenko.security.repository.Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class Controller {
    private final Repository repository;

    public Controller(Repository repository) {
        this.repository = repository;
    }

    @GetMapping("/persons/by-city")
    public ResponseEntity<Object> getUserByCity(@RequestParam String city) {
        return repository.findByCity(city);
    }

    @GetMapping("/persons/by-age")
    public ResponseEntity<Object> getUserByAge(@RequestParam int age) {
        return repository.findByAge(age);
    }

    @GetMapping("/persons/by-name")
    public ResponseEntity<Object> getUserByName(@RequestParam String name, String surname) {
        return repository.findByNameAdnSurname(name, surname);
    }
}
