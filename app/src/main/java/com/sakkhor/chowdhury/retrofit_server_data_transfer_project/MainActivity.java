package com.sakkhor.chowdhury.retrofit_server_data_transfer_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    String url = "http://10.0.2.2/";
    TextView text_id_1, text_name_1, text_marks_1 ;
    TextView text_id_2, text_name_2, text_marks_2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        text_id_1 = (TextView) findViewById(R.id.text_id_1);
        text_name_1 = (TextView) findViewById(R.id.text_name_1);
        text_marks_1 = (TextView) findViewById(R.id.text_marks_1);

        text_id_2 = (TextView) findViewById(R.id.text_id_2);
        text_name_2 = (TextView) findViewById(R.id.text_name_2);
        text_marks_2 = (TextView) findViewById(R.id.text_marks_2);

        Button ButtonArray= (Button) findViewById(R.id.RetrofitArray);
        //  Button ButtonObject= (Button) findViewById(R.id.RetrofitObject);

        ButtonArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View VisibleArray = findViewById(R.id.RetrofitArray);
                VisibleArray.setVisibility(View.GONE);
                //View VisibleObject = findViewById(R.id.RetrofitObject);
                //VisibleObject.setVisibility(View.GONE);
                getRetrofitArray();
            }
        });

  /*      ButtonObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View VisibleArray = findViewById(R.id.RetrofitArray);
                VisibleArray.setVisibility(View.GONE);
                View VisibleObject = findViewById(R.id.RetrofitObject);
                VisibleObject.setVisibility(View.GONE);
                getRetrofitObject();
            }
        });*/


    }

    void getRetrofitArray() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitArrayAPI service = retrofit.create(RetrofitArrayAPI.class);

        Call<List<Student>> call = service.getStudentDetails();

        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Response<List<Student>> response, Retrofit retrofit) {

                try {

                    List<Student> StudentData = response.body();

                    for (int i = 0; i < StudentData.size(); i++) {

                        if (i == 5) {
                            text_id_1.setText("StudentId  :  " + StudentData.get(i).getId());
                            text_name_1.setText("StudentName  :  " + StudentData.get(i).getName());
                            text_marks_1.setText("StudentMarks  : " + StudentData.get(i).getDesignation());
                        } else if (i == 3) {
                            text_id_2.setText("StudentId  :  " + StudentData.get(i).getId());
                            text_name_2.setText("StudentName  :  " + StudentData.get(i).getName());
                            text_marks_2.setText("StudentMarks  : " + StudentData.get(i).getDesignation());
                        }
                    }


                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });
    }

    /*void getRetrofitObject() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitObjectAPI service = retrofit.create(RetrofitObjectAPI.class);

        Call<Student> call = service.getStudentDetails();
        //Call<List<Student>> call = service.getStudentDetails();

        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Response<Student> response, Retrofit retrofit) {

                try {

                    text_id_1.setText("StudentId  :  " + response.body().getId());
                    text_name_1.setText("StudentName  :  " + response.body().getName());
                    text_marks_1.setText("StudentMarks  : " + response.body().getDesignation());

                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });
    }*/



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}

