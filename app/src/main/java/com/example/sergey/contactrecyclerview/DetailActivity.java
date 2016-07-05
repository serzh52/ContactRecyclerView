package com.example.sergey.contactrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private TextView name;
    private TextView phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        name = (TextView) findViewById(R.id.details_name);
        phone = (TextView) findViewById(R.id.details_phone);

        name.setText(getIntent().getStringExtra("name"));
        phone.setText(getIntent().getStringExtra("phone"));

    }

    }
