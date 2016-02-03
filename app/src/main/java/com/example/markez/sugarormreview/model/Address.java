package com.example.markez.sugarormreview.model;

import com.orm.SugarRecord;

import java.util.List;

public class Address extends SugarRecord {

    String name;

    public Address(){
    }

    public Address(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
