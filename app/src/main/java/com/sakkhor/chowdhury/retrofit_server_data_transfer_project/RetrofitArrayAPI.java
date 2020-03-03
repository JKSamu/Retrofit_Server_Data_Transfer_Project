package com.sakkhor.chowdhury.retrofit_server_data_transfer_project;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;


public interface RetrofitArrayAPI {

    /*
     * Retrofit get annotation with our URL
     * And our method that will return us details of student.
    */
    @GET("retrofit_object_array_show/getAllEmpbyArray.php")
    Call<List<Student>> getStudentDetails();

}
