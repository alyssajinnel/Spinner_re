package com.alyssajinnellibed.spinner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;


public class MainActivity extends AppCompatActivity  {

    private final AppCompatActivity activity = MainActivity.this;

    private NestedScrollView nestedScrollView;

    private AppCompatButton appCompatButtonAddDiner;
    private AppCompatButton appCompatButtonList;
    private AppCompatButton appCompatButtonChoose;
    private AppCompatButton appCompatButtonSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        appCompatButtonAddDiner = (AppCompatButton) findViewById(R.id.appCompatButtonAddDiner);
        appCompatButtonList = (AppCompatButton) findViewById(R.id.appCompatButtonList);
        appCompatButtonChoose = (AppCompatButton) findViewById(R.id.appCompatButtonChoose);
        appCompatButtonSpinner = (AppCompatButton) findViewById(R.id.appCompatButtonSpinner);


        appCompatButtonAddDiner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(getApplicationContext(), AddDiner.class);
                startActivity(add);
            }
        });

        appCompatButtonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent list = new Intent(getApplicationContext(), DinerListActivity.class);
                startActivity(list);
            }
        });

        /**appCompatButtonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent choose = new Intent(getApplicationContext(), AddDiner.class);
                startActivity(choose);
            }
        });**/

        appCompatButtonSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent spin = new Intent(getApplicationContext(), FoodSpinner.class);
                startActivity(spin);
            }
        });
    }




}