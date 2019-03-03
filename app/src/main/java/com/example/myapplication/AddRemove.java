package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddRemove extends AppCompatActivity {

    String link="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_remove);

        ImageButton b1=findViewById(R.id.btn2);
        ImageButton b2=findViewById(R.id.btn1);
        ImageButton b3= findViewById(R.id.see);
        final ImageView imageView=findViewById(R.id.iview);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddRemove.this, SeeResults.class);
                startActivity(i);
            }
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dog.ceo/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<GetDat> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<GetDat>() {
            @Override
            public void onResponse(Call<GetDat> call, Response<GetDat> response) {
                if(!response.isSuccessful()){
                    return;
                }

                link=response.body().getMessage();
                new ImageLoadTask(response.body().getMessage(), imageView).execute();

            }

            @Override
            public void onFailure(Call<GetDat> call, Throwable t) {
            }

        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseAcces da=DatabaseAcces.getInstance(getApplicationContext());
                da.open();
                if(da.addtotable(link)!=-1){
                    Toast.makeText(AddRemove.this,"Added Successfully",Toast.LENGTH_SHORT).show();
                }
                da.close();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://dog.ceo/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
                Call<GetDat> call = jsonPlaceHolderApi.getPosts();
                call.enqueue(new Callback<GetDat>() {
                    @Override
                    public void onResponse(Call<GetDat> call, Response<GetDat> response) {
                        if(!response.isSuccessful()){
                            return;
                        }
                        link=response.body().getMessage();
                        new ImageLoadTask(response.body().getMessage(), imageView).execute();
                    }

                    @Override
                    public void onFailure(Call<GetDat> call, Throwable t) {

                    }

                });

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://dog.ceo/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                DatabaseAcces da=DatabaseAcces.getInstance(getApplicationContext());
                da.open();
                da.close();

                JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
                Call<GetDat> call = jsonPlaceHolderApi.getPosts();
                call.enqueue(new Callback<GetDat>() {
                    @Override
                    public void onResponse(Call<GetDat> call, Response<GetDat> response) {
                        if(!response.isSuccessful()){
                            return;
                        }
                        

                        link=response.body().getMessage();
                        new ImageLoadTask(response.body().getMessage(), imageView).execute();

                    }

                    @Override
                    public void onFailure(Call<GetDat> call, Throwable t) {


                    }

                });
            }
        });
    }
}
