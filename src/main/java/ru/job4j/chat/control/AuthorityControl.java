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
        ResponseEntity<Authority> authority = service.findById(id);
        if (authority.getStatusCodeValue() == 404) {
            throw new NullPointerException("Role with it id=" + id + " not found in database");
        }
        return authority;
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
