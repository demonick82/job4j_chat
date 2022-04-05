package ru.job4j.chat.control;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.serviсe.PersonService;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonControl {

    private final PersonService service;

    public PersonControl(PersonService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Person> findAllAuth() {
        return service.findAllPersons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable int id) {
        return service.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        return service.create(person);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        return service.update(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        return service.delete(id);
    }

}
