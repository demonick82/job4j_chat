package ru.job4j.chat.control;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.dto.PersonDTO;
import ru.job4j.chat.exceptionHandling.IllegalPasswordException;
import ru.job4j.chat.exceptionHandling.Operation;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.serviсe.PersonService;

import javax.validation.Valid;
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
    public ResponseEntity<List<Person>> findAllPersons() {
        return service.findAllPersons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable int id) {
        ResponseEntity<Person> person = service.findById(id);
        if (person.getStatusCodeValue() == 404) {
            throw new NullPointerException("Person with it id=" + id + " not found in database");
        }
        return person;
    }

    @PutMapping("/")
    @Validated(Operation.OnUpdate.class)
    public ResponseEntity<Void> update(@Valid @RequestBody Person person) {
        return service.update(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        return service.delete(id);
    }

    @PostMapping("/sign-up")
    @Validated(Operation.OnCreate.class)
    public void signUp(@Valid @RequestBody Person person) {
        if (person.getPassword().length() < 6) {
            throw new IllegalPasswordException("password must be min 6 characters");
        }
        person.setPassword(encoder.encode(person.getPassword()));
        service.create(person);
    }

    @PatchMapping("/")
    @Validated(Operation.OnUpdate.class)
    public ResponseEntity<Person> patch(@Valid @RequestBody PersonDTO personDTO) {
        return service.patch(personDTO);
    }
}
