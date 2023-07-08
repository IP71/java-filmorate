package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.controller.UserController;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserControllerTest {

    public UserController controller = new UserController();

    @DisplayName("User с некорректным email")
    @Test
    public void createUserWithInvalidEmail() {
        User user = new User("wrong", "login", LocalDate.of(2000, 1, 1));
        ValidationException e = assertThrows(ValidationException.class, () -> controller.validate(user));
        assertEquals("Email должен содержать @ и не быть пустым", e.getMessage());
    }

    @DisplayName("User с некорректным логином")
    @Test
    public void createUserWithInvalidLogin() {
        User user = new User("right@", "log in", LocalDate.of(2000, 1, 1));
        ValidationException e = assertThrows(ValidationException.class, () -> controller.validate(user));
        assertEquals("Логин не должен быть пустым или содержать пробелы", e.getMessage());
    }

    @DisplayName("User с пустым именем")
    @Test
    public void createUserWithEmptyName() {
        User user = new User("right@", "login", LocalDate.of(2000, 1, 1));
        controller.validate(user);
        assertEquals("login", user.getName());
    }

    @DisplayName("User с некорректной датой рождения")
    @Test
    public void createUserWithInvalidBirthday() {
        User user = new User("right@", "login", LocalDate.of(3000, 1, 1));
        ValidationException e = assertThrows(ValidationException.class, () -> controller.validate(user));
        assertEquals("Дата рождения не может быть в будущем", e.getMessage());
    }
}
