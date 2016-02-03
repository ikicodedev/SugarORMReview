package com.example.markez.sugarormreview.model;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

public class Author extends SugarRecord {

    String name;
    String surname;
    Address address;

    public Author(){
    }

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Editorial> getEditorials() {
        List<Editorial> result = new ArrayList<>();

        for (AuthorEditorial authorEditorial : AuthorEditorial.find(AuthorEditorial.class, "author = ?", getId().toString())){
            result.add(authorEditorial.getEditorial());
        }

        return result;
    }

    public List<Book> getBooks() {
        return Book.find(Book.class, "author = ?", getId().toString());
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
