package ru.job4j.chat.dto;

import ru.job4j.chat.exceptionHandling.Operation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class PersonDTO {

    @Min(value = 1, message = "id должно быть больше единицы",
            groups = {Operation.OnUpdate.class, Operation.OnDelete.class})
    private int id;

    @NotBlank(message = "Поле не должно быть пустым")
    private String nickName;

    @NotBlank(message = "Поле не должно быть пустым")
    private String login;

    @Min(value = 6, message = "Пароль должен быть больше 6 символов")
    private String password;

    @Min(value = 1, message = "id должно быть больше единицы")
    private int authorityId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PersonDTO personDTO = (PersonDTO) o;
        return id == personDTO.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
