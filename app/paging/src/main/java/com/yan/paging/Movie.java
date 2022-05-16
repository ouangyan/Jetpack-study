package com.yan.paging;


import java.util.Objects;

public class Movie {
    public int id;
    public String img;
    public String title;

    @Override
    public String toString() {
        return "Moive{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie moive = (Movie) o;
        return id == moive.id &&
                img.equals(moive.img) &&
                title.equals(moive.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, img, title);
    }
}
