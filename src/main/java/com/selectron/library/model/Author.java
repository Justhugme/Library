package com.selectron.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.bind.annotation.Mapping;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "author")
public class Author {
    private Integer id;
    private String firstName;
    private String lastName;
    private Set<Book> books;
    private Date birthdayDate;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "author_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @ManyToMany(mappedBy = "authors")
    @JsonIgnore
    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }
}
