package ru.job4j.chat.serviсe;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.chat.dto.PersonDTO;
import ru.job4j.chat.model.Authority;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.repository.PersonRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements UserDetailsService {

    private final PersonRepository repository;
    private final AuthorityService authorityService;

    public PersonService(PersonRepository repository, AuthorityService authorityService) {
        this.repository = repository;
        this.authorityService = authorityService;
    }

    public ResponseEntity<List<Person>> findAllPersons() {
        List<Person> persons = repository.findAll();
        return new ResponseEntity<>(persons,
                persons.size() != 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person user = repository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getLogin(), user.getPassword(), Collections.emptyList());
    }

    public ResponseEntity<Person> patch(PersonDTO personDTO) {
        Person person = Person.of(personDTO.getNickName(), personDTO.getLogin(), personDTO.getPassword());
        Authority auth = authorityService.findById(personDTO.getAuthorityId()).getBody();
        person.setId(personDTO.getId());
        person.setAuthority(auth);
        return new ResponseEntity<>(
                this.repository.save(person),
                HttpStatus.CREATED);
    }
}
