package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/*
Класс, описывающий сущность "Фильм"
*/

@Data
@Builder
public class Film {

    private int id;
    @NotBlank
    private String name;
    @Size(max = 200)
    private String description;
    private LocalDate releaseDate;
    @Positive
    private int duration;
    private Set<Long> likes = new HashSet<>();
    private Mpa mpa;
    private Set<Genre> genres = new HashSet<>();

    public Film(int id, String name, String description, LocalDate releaseDate, int duration, Set<Long> likes, Mpa mpa, Set<Genre> genres) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.likes = likes;
        this.mpa = mpa;
        this.genres = genres;
    }

    public Film(String name, String description, LocalDate releaseDate, int duration, Set<Long> likes, Mpa mpa, Set<Genre> genres) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.likes = likes;
        this.mpa = mpa;
        this.genres = genres;
    }

    public Film() {
    }

    public int numberOfLikes() {
        return likes.size();
    }
}
