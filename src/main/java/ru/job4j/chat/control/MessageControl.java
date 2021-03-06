package ru.job4j.chat.control;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.dto.MessageDTO;
import ru.job4j.chat.exceptionHandling.Operation;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.serviсe.MessageService;

import javax.validation.Valid;
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
        ResponseEntity<Message> message = service.findById(id);
        if (message.getStatusCodeValue() == 404) {
            throw new NullPointerException("Message with it id=" + id + " not found in database");
        }
        return message;
    }

    @PostMapping("/")
    @Validated(Operation.OnCreate.class)
    public ResponseEntity<Message> create(@Valid @RequestBody Message message) {
        return service.create(message);
    }

    @PutMapping("/")
    @Validated(Operation.OnUpdate.class)
    public ResponseEntity<Void> update(@Valid @RequestBody Message message) {
        return service.update(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        return service.delete(id);
    }

    @PatchMapping("/")
    @Validated(Operation.OnUpdate.class)
    public ResponseEntity<Message> patch(@Valid @RequestBody MessageDTO messageDTO) {
        return service.patch(messageDTO);
    }
}
