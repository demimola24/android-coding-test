package com.aptitude.application_test.models;

import com.google.gson.annotations.SerializedName;

public class AuthorModel {
    @SerializedName("name")
    private String name;

    public AuthorModel(String name) {
        this.name = name; }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


}
