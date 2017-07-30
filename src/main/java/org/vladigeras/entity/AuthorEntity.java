package org.vladigeras.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Author", schema = "OnlineLibrary")
public class AuthorEntity {
    private long id;
    private String firstname;
    private String middlename;
    private String lastname;
    private String fio;
    private Collection<BookAuthorEntity> bookAuthorsById;

    public AuthorEntity() {
    }

    public AuthorEntity(String firstname, String middlename, String lastname, String fio) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.fio = fio;
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
    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "middlename")
    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    @Basic
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "fio")
    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorEntity that = (AuthorEntity) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (middlename != null ? !middlename.equals(that.middlename) : that.middlename != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (fio != null ? !fio.equals(that.fio) : that.fio != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (middlename != null ? middlename.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (fio != null ? fio.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "authorByAuthorId")
    public Collection<BookAuthorEntity> getBookAuthorsById() {
        return bookAuthorsById;
    }

    public void setBookAuthorsById(Collection<BookAuthorEntity> bookAuthorsById) {
        this.bookAuthorsById = bookAuthorsById;
    }
}
