package com.example.markez.sugarormreview.model;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

public class Editorial extends SugarRecord {

    String name;

    public Editorial(){
    }

    public Editorial(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Author> getAuthors() {
        List<Author> result = new ArrayList<>();

        for (AuthorEditorial authorEditorial : AuthorEditorial.find(AuthorEditorial.class, "editorial = ?", getId().toString())){
            result.add(authorEditorial.getAuthor());
        }

        return result;
    }
}
