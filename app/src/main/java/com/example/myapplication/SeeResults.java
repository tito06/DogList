package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class SeeResults extends AppCompatActivity {

    List<GetDat> datalist1;
    RecyclerView recyclerView1;
    GetAdapter adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_results);

        recyclerView1=(RecyclerView)findViewById(R.id.recycler_view);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        displayData();
    }

    public void displayData() {

        DatabaseAcces da=DatabaseAcces.getInstance(getApplicationContext());
        da.open();
        datalist1=new ArrayList<>();

        String s="select distinct Image_Link from my_table;";
        String[] data=da.displayRecycler(s);
        da.close();
        for(int i=0;i<data.length;i++){
            String x=data[i];
            datalist1.add(new GetDat(data[i]));
        }
        adapter1=new GetAdapter(this,datalist1);
        recyclerView1.setAdapter(adapter1);

    }
}
