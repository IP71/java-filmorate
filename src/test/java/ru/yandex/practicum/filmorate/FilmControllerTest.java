package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FilmControllerTest {

    public FilmController controller = new FilmController();

    @DisplayName("Film без имени")
    @Test
    public void createFilmWithNoName() {
        Film film = new Film(null, "description", LocalDate.of(2000, 1, 1), 120);
        ValidationException e = assertThrows(ValidationException.class, () -> controller.validate(film));
        assertEquals("Название фильма не может быть пустым", e.getMessage());
    }

    @DisplayName("Film с длинным описанием")
    @Test
    public void createFilmWithTooBigDescription() {
        Film film = new Film("name", "description".repeat(50), LocalDate.of(2000, 1, 1), 120);
        ValidationException e = assertThrows(ValidationException.class, () -> controller.validate(film));
        assertEquals("Описание фильма не может превышать 200 символов", e.getMessage());
    }

    @DisplayName("Film со слишком ранней датой")
    @Test
    public void createFilmWithTooEarlyReleaseDate() {
        Film film = new Film("name", "description", LocalDate.of(1000, 1, 1), 120);
        ValidationException e = assertThrows(ValidationException.class, () -> controller.validate(film));
        assertEquals("Дата релиза не должна быть ранее 28.12.1895", e.getMessage());
    }

    @DisplayName("Film с неположительной продолжительностью")
    @Test
    public void createFilmWithoutPositiveDuration() {
        Film film = new Film("name", "description", LocalDate.of(2000, 1, 1), -1);
        ValidationException e = assertThrows(ValidationException.class, () -> controller.validate(film));
        assertEquals("Продолжительность фильма должна быть положительна", e.getMessage());
    }
}
