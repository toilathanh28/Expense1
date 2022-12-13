package com.example.coursework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.coursework.adapter.AlltripsAdapter;
import com.example.coursework.adapter.RecentAdapter;
import com.example.coursework.model.AlltripsData;
import com.example.coursework.model.RecentData;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    RecyclerView recentRecycler, allTripsRecycler;
    RecentAdapter recentAdapter;
    AlltripsAdapter alltripsAdapter;
    EditText searchView;
    CharSequence search = "";
    private Button btn_managetrip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_managetrip = findViewById(R.id.btn_managetrip);

        btn_managetrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, SQLiteDatabase.class);
                startActivity(i);
            }
        });

        List<RecentData> recentDataList = new ArrayList<>();
        recentDataList.add(new RecentData("Am Lake", "LEGO", "2000$","Risk", R.drawable.recentimage1));
        recentDataList.add(new RecentData("Ho Tay", "HANOI", "1500$","Risk", R.drawable.recentimage2));
        recentDataList.add(new RecentData("BA Dao", "TAM DAO", "2100$","Risk", R.drawable.recentimage1));
        recentDataList.add(new RecentData("Am Lake", "LEGO", "2000$","Risk", R.drawable.recentimage1));
        recentDataList.add(new RecentData("Ho Tay", "HA NOI", "1500$","Risk", R.drawable.recentimage2));
        recentDataList.add(new RecentData("BA Dao", "TAM DAO", "2100$","Risk", R.drawable.recentimage1));
        setRecentRecycler(recentDataList);

        searchView = findViewById(R.id.search_bar);

        List<AlltripsData> alltripsDataList = new ArrayList<>();
        alltripsDataList.add(new AlltripsData("Am Lake", "LEGO", "2000$","Risk", R.drawable.topplaces));
        alltripsDataList.add(new AlltripsData("HoAy", "HANU", "1500$","Risk", R.drawable.topplaces));
        alltripsDataList.add(new AlltripsData("Bardao", "BARDAO", "2100$","Risk", R.drawable.topplaces));
        alltripsDataList.add(new AlltripsData("Good Lake", "LEGO", "2000$","Risk", R.drawable.topplaces));
        alltripsDataList.add(new AlltripsData("Ho Dong", "TPHCM", "1500$","Risk", R.drawable.topplaces));
        alltripsDataList.add(new AlltripsData("Bao Da", "NamDinh", "2100$","Risk", R.drawable.topplaces));
        setAllTripsRecycler(alltripsDataList);

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                alltripsAdapter.getFilter().filter(charSequence);
                search = charSequence;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setRecentRecycler(List<RecentData> recentDataList){

        recentRecycler = findViewById(R.id.recent_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recentRecycler.setLayoutManager(layoutManager);
        recentAdapter = new RecentAdapter(this, recentDataList);
        recentRecycler.setAdapter(recentAdapter);
    }

    private void setAllTripsRecycler(List<AlltripsData> alltripsDataList){

        allTripsRecycler = findViewById(R.id.all_trip_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        allTripsRecycler.setLayoutManager(layoutManager);
        alltripsAdapter = new AlltripsAdapter(this, alltripsDataList);
        allTripsRecycler.setAdapter(alltripsAdapter);
    }

}