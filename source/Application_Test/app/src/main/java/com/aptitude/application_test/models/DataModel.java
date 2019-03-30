package com.aptitude.application_test.models;

import com.google.gson.annotations.SerializedName;

public class DataModel {

    @SerializedName("commit")
    private CommitModel commmit;

    public DataModel(CommitModel commmit) {
        this.commmit = commmit;
    }

    public CommitModel getCommmit() {
        return commmit;
    }

    public void setCommmit(CommitModel commmit) {
        this.commmit = commmit;
    }
}
