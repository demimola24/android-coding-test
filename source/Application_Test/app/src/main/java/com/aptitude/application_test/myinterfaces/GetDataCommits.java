package com.aptitude.application_test.myinterfaces;

import com.aptitude.application_test.models.DataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataCommits {
    @GET("commits?page=1&sort=created&order=desc&per_page=30")
    Call<List<DataModel>> getAllCommits();
}
