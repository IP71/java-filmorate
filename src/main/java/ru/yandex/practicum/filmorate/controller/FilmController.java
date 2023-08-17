package ru.yandex.practicum.filmorate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import javax.validation.Valid;
import java.util.List;

/*
Класс-контроллер для работы с фильмами
Метод create(Film film) добавляет информацию о фильме в базу (POST /films)
Метод update(Film film) обновляет информацию о фильме в базе (PUT /films)
Метод get() возвращает информацию о всех фильмах в базе (GET /films)
Метод getFilmById(int id) возвращает информацию о фильме по id (GET /films/{id})
Метод addLike(int filmId, long userId) добавляет фильму лайк пользователя по id (PUT /films/{id}/like/{userId})
Метод removeLike(int filmId, long userId) убирает лайк пользователя по id (DELETE /films/{id}/like/{userId})
Метод getPopularMovies(int count) возвращает информацию о {count} самых популярных фильмах (GET /films/popular)
*/

@RestController
@RequestMapping("/films")
public class FilmController {

    private final FilmService service;

    @Autowired
    public FilmController(FilmService service) {
        this.service = service;
    }

    @PostMapping
    public Film create(@Valid @RequestBody Film film) {
        Validator.validateFilm(film);
        return service.create(film);
    }

    @PutMapping
    public Film update(@Valid @RequestBody Film film) {
        Validator.validateFilm(film);
        return service.update(film);
    }

    @GetMapping
    public List<Film> get() {
        return service.get();
    }

    @GetMapping("/{id}")
    public Film getFilmById(@PathVariable int id) {
        return service.getFilmById(id);
    }

    @PutMapping("/{id}/like/{userId}")
    public void addLike(@PathVariable("id") int filmId, @PathVariable long userId) {
        service.addLike(filmId, userId);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public void removeLike(@PathVariable("id") int filmId, @PathVariable long userId) {
        service.removeLike(filmId, userId);
    }

    @GetMapping("/popular")
    public List<Film> getPopularMovies(@RequestParam(required = false, defaultValue = "10") int count) {
        return service.getPopularMovies(count);
    }
}
