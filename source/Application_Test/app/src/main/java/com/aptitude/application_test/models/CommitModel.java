package com.aptitude.application_test.models;

import com.google.gson.annotations.SerializedName;

public class CommitModel {
    @SerializedName("author")
    private AuthorModel author;
    @SerializedName("message")
    private String commitMessage;
    @SerializedName("url")
    private String commitUrl;

    public CommitModel(AuthorModel author,String commitMessage, String commitUrl) {
        this.author = author;
        this.commitMessage = commitMessage;
        this.commitUrl = commitUrl;
    }

    public String getCommitUrl() {
        return commitUrl;
    }

    public void setCommitUrl(String commitUrl) {
        this.commitUrl = commitUrl;
    }

    public String getCommitMessage() {
        return commitMessage;
    }

    public void setCommitMessage(String commitMessage) {
        this.commitMessage = commitMessage;
    }

    public AuthorModel getAuthor() {
        return author;
    }

    public void setAuthor(AuthorModel author) {
        this.author = author;
    }

}
