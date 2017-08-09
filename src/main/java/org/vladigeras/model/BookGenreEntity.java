package org.vladigeras.model;

import javax.persistence.*;

@Entity
@Table(name = "Book_Genre", schema = "OnlineLibrary")
public class BookGenreEntity {

    private long id;
    private long bookId;
    private long genreId;
    private BookEntity bookByBookId;
    private GenreEntity genreByGenreId;

    public  BookGenreEntity() {

    }

    public BookGenreEntity(long bookId, long genreId) {
        this.bookId = bookId;
        this.genreId = genreId;
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
    @Column(name = "book_id")
    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "genre_id")
    public long getGenreId() {
        return genreId;
    }

    public void setGenreId(long genreId) {
        this.genreId = genreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookGenreEntity that = (BookGenreEntity) o;

        if (id != that.id) return false;
        if (bookId != that.bookId) return false;
        if (genreId != that.genreId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (bookId ^ (bookId >>> 32));
        result = 31 * result + (int) (genreId ^ (genreId >>> 32));
        return result;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", referencedColumnName = "id", insertable = false, updatable = false)
    public BookEntity getBookByBookId() {
        return bookByBookId;
    }

    public void setBookByBookId(BookEntity bookByBookId) {
        this.bookByBookId = bookByBookId;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id", referencedColumnName = "id", insertable = false, updatable = false)
    public GenreEntity getGenreByGenreId() {
        return genreByGenreId;
    }

    public void setGenreByGenreId(GenreEntity genreByGenreId) {
        this.genreByGenreId = genreByGenreId;
    }
}
