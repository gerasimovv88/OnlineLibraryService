package org.vladigeras.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "Genre", schema = "OnlineLibrary")
public class GenreEntity implements Serializable {
    private static final long serialVersionUID = 8510430326080416609L;
    private long id;
    private String title;
    private Collection<BookEntity> bookEntityCollection;

    public GenreEntity() {
    }

    public GenreEntity(String title) {
        this.title = title;
    }

    @Id
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

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "Book_Genre",
            joinColumns = @JoinColumn(name = "genre_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    public Collection<BookEntity> getBookEntityCollection() {
        return bookEntityCollection;
    }

    public void setBookEntityCollection(Collection<BookEntity> bookEntityCollection) {
        this.bookEntityCollection = bookEntityCollection;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }
}
