package ru.job4j.chat.control;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.model.Authority;
import ru.job4j.chat.serviсe.AuthorityService;

import java.util.List;

@RestController
@RequestMapping("/authority")
public class AuthorityControl {

    private final AuthorityService service;

    public AuthorityControl(AuthorityService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Authority> findAllAuth() {
        return service.findAllAuth();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Authority> findById(@PathVariable int id) {
        return service.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Authority> create(@RequestBody Authority auth) {
        return service.create(auth);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Authority auth) {
        return service.update(auth);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        return service.delete(id);
    }

}
