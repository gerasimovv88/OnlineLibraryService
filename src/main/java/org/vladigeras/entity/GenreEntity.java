package org.vladigeras.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Genre", schema = "OnlineLibrary")
public class GenreEntity {
    private long id;
    private String title;
    private Collection<BookGenreEntity> bookGenresById;

    public GenreEntity() {

    }

    public GenreEntity(String title) {
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenreEntity that = (GenreEntity) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "genreByGenreId")
    public Collection<BookGenreEntity> getBookGenresById() {
        return bookGenresById;
    }

    public void setBookGenresById(Collection<BookGenreEntity> bookGenresById) {
        this.bookGenresById = bookGenresById;
    }
}
