package ru.job4j.chat.serviсe;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> findAllPersons() {
        return repository.findAll();
    }

    public ResponseEntity<Person> findById(int id) {
        Optional<Person> person = repository.findById(id);
        return new ResponseEntity<>(
                person.orElse(new Person()),
                person.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Person> create(Person person) {
        return new ResponseEntity<>(
                this.repository.save(person),
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Void> update(Person person) {
        repository.save(person);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> delete(int id) {
        Person person = new Person();
        person.setId(id);
        repository.delete(person);
        return ResponseEntity.ok().build();
    }
}
