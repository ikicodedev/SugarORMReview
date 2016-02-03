package com.example.markez.sugarormreview.model;

import com.orm.SugarRecord;

public class AuthorEditorial extends SugarRecord {

    Author author;
    Editorial editorial;

    public AuthorEditorial(){
    }

    public AuthorEditorial(Author author, Editorial editorial) {
        this.author = author;
        this.editorial = editorial;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }
}
