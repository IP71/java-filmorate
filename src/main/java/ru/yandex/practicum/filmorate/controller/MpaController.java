package ru.yandex.practicum.filmorate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.MpaStorage;

import java.util.List;

/*
Класс-контроллер для работы с Mpa (возрастными рейтингами)
Метод get() возвращает информацию о всех Mpa (GET /mpa)
Метод getMpaById(int id) возвращает информацию о Mpa по id (GET /mpa/{id})
*/

@RestController
@RequestMapping("/mpa")
public class MpaController {

    private final MpaStorage storage;

    @Autowired
    public MpaController(MpaStorage storage) {
        this.storage = storage;
    }

    @GetMapping
    public List<Mpa> get() {
        return storage.get();
    }

    @GetMapping("/{id}")
    public Mpa getMpaById(@PathVariable int id) {
        return storage.getMpaById(id);
    }
}
