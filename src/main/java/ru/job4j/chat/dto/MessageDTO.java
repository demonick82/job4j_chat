package ru.job4j.chat.dto;


import ru.job4j.chat.exceptionHandling.Operation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.Objects;

public class MessageDTO {

    @Min(value = 1, message = "id должно быть больше единицы",
            groups = {Operation.OnUpdate.class, Operation.OnDelete.class})
    private int id;

    @NotBlank(message = "Поле не должно быть пустым")
    private String description;
    private Timestamp created;

    @Min(value = 1, message = "id должно быть больше единицы")
    private int personId;

    public static MessageDTO of(String description) {
        MessageDTO message = new MessageDTO();
        message.description = description;
        message.created = new Timestamp(System.currentTimeMillis());
        return message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MessageDTO that = (MessageDTO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
