package com.simon.onemore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    SessionRVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setSubtitle("Sessions");

        final Button toAddSessionButton = findViewById(R.id.buttonToAddSession);
        toAddSessionButton.setOnClickListener(view -> {
            startActivity(new Intent(this, NewSessionActivity.class));
        });

        RecyclerView sessionList = findViewById(R.id.sessionList);
        sessionList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SessionRVAdapter(this);
        sessionList.setAdapter(adapter);

        DataLayer.getInstance().loadData(this);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        DataLayer.getInstance().setActiveSession(null);
        adapter.notifyDataSetChanged();
    }
}