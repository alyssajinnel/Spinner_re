package com.alyssajinnellibed.spinner;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import com.alyssajinnellibed.spinner.adapter.DinerRecyclerAdapter;
import com.alyssajinnellibed.spinner.model.Diners;
import com.alyssajinnellibed.spinner.sql.DatabaseHelper;

import java.util.ArrayList;

public class DinerListActivity extends AppCompatActivity {

    private AppCompatActivity activity = DinerListActivity.this;
    Context context = DinerListActivity.this;
    private RecyclerView recyclerViewDiner;
    private ArrayList<Diners> listDiners;
    private DinerRecyclerAdapter dinerRecyclerAdapter;
    private DatabaseHelper databaseHelper;
    SearchView searchBox;
    private ArrayList<Diners> filteredList;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.alyssajinnellibed.spinner.R.layout.activity_diner_list);
        Toolbar toolbar = (Toolbar) findViewById(com.alyssajinnellibed.spinner.R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();
        initObjects();

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra("LOCATION")) {

            //get all needed extras intent

            int id = getIntent().getExtras().getInt("ID");
            String category = getIntent().getExtras().getString("CATEGORY");
            String dinername = getIntent().getExtras().getString("DINER NAME");
            String pricerange = getIntent().getExtras().getString("PRICE RANGE");



        }else{

            //Toast.makeText(this, "No API Data", Toast.LENGTH_SHORT).show();

        }

    }



    /**
     * This method is to initialize views
     */
    private void initViews() {
        recyclerViewDiner = (RecyclerView) findViewById(com.alyssajinnellibed.spinner.R.id.recyclerViewDiner);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listDiners = new ArrayList<>();
        dinerRecyclerAdapter = new DinerRecyclerAdapter(listDiners, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewDiner.setLayoutManager(mLayoutManager);
        recyclerViewDiner.setItemAnimator(new DefaultItemAnimator());
        recyclerViewDiner.setHasFixedSize(true);
        recyclerViewDiner.setAdapter(dinerRecyclerAdapter);
        databaseHelper = new DatabaseHelper(activity);

        getDataFromSQLite();

    }





    /**
     * This method is to fetch all user records from SQLite
     */
    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listDiners.clear();
                listDiners.addAll(databaseHelper.getAllDiners());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                dinerRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
