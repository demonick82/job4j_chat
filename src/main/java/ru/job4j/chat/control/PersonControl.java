package ru.job4j.chat.control;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.serviсe.PersonService;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonControl {

    private final PersonService service;
    private final BCryptPasswordEncoder encoder;

    public PersonControl(PersonService service, BCryptPasswordEncoder encoder) {
        this.service = service;
        this.encoder = encoder;
    }

    @GetMapping("/")
    public List<Person> findAllAuth() {
        return service.findAllPersons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable int id) {
        return service.findById(id);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        return service.update(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        return service.delete(id);
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody Person person) {
        System.out.println("person=" + person);
        person.setPassword(encoder.encode(person.getPassword()));
        service.create(person);
    }
}
