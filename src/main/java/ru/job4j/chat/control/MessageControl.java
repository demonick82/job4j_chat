package ru.job4j.chat.control;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.serviсe.MessageService;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageControl {

    private final MessageService service;

    public MessageControl(MessageService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Message> findAllAuth() {
        return service.findAllMessages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> findById(@PathVariable int id) {
        return service.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Message> create(@RequestBody Message message) {
        return service.create(message);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Message message) {
        return service.update(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        return service.delete(id);
    }
}