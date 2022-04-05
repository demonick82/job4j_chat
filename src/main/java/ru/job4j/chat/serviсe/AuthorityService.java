package ru.job4j.chat.serviсe;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.job4j.chat.model.Authority;
import ru.job4j.chat.repository.AuthorityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorityService {
    private final AuthorityRepository repository;

    public AuthorityService(AuthorityRepository repository) {
        this.repository = repository;
    }

    public List<Authority> findAllAuth() {
        return repository.findAll();
    }

    public ResponseEntity<Authority> findById(int id) {
        Optional<Authority> auth = repository.findById(id);
        return new ResponseEntity<>(
                auth.orElse(new Authority()),
                auth.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Authority> create(Authority authority) {
        return new ResponseEntity<>(
                this.repository.save(authority),
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Void> update(Authority auth) {
        repository.save(auth);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> delete(int id) {
        Authority auth = new Authority();
        auth.setId(id);
        repository.delete(auth);
        return ResponseEntity.ok().build();
    }
}
