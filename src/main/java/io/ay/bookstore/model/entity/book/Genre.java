package io.ay.bookstore.model.entity.book;

import lombok.Getter;

@Getter
public enum Genre {

    FICTION("FICTION"),
    THRILLER("THRILLER"),
    MYSTERY("MYSTERY"),
    POETRY("POETRY"),
    HORROR("HORROR"),
    SATIRE("SATIRE");

    private final String genreName;

    Genre(String genreName) {
        this.genreName = genreName;
    }
}
