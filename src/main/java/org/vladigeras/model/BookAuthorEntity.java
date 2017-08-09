package org.vladigeras.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Book_Author", schema = "OnlineLibrary")
public class BookAuthorEntity {

    private long id;
    private long bookId;
    private long authorId;
    private BookEntity bookByBookId;
    private AuthorEntity authorByAuthorId;

    public BookAuthorEntity() {
    }

    public BookAuthorEntity(long bookId, long authorId) {
        this.bookId = bookId;
        this.authorId = authorId;
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
    @Column(name = "author_id")
    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookAuthorEntity that = (BookAuthorEntity) o;

        if (id != that.id) return false;
        if (bookId != that.bookId) return false;
        if (authorId != that.authorId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (bookId ^ (bookId >>> 32));
        result = 31 * result + (int) (authorId ^ (authorId >>> 32));
        return result;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public BookEntity getBookByBookId() {
        return bookByBookId;
    }

    public void setBookByBookId(BookEntity bookByBookId) {
        this.bookByBookId = bookByBookId;
    }

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public AuthorEntity getAuthorByAuthorId() {
        return authorByAuthorId;
    }

    public void setAuthorByAuthorId(AuthorEntity authorByAuthorId) {
        this.authorByAuthorId = authorByAuthorId;
    }
}
