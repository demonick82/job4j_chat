package ru.job4j.chat.dto;


import java.sql.Timestamp;
import java.util.Objects;

public class MessageDTO {
    private int id;
    private String description;
    private Timestamp created;
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
