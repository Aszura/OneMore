package com.simon.onemore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.Debug;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SessionActivity extends AppCompatActivity {
    ItemRVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);
        getSupportActionBar().setSubtitle(DataLayer.getInstance().getActiveSession().name);

        RecyclerView itemList = findViewById(R.id.itemList);
        itemList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ItemRVAdapter(this);
        itemList.setAdapter(adapter);

        // Button action
        Button addItemButton = findViewById(R.id.buttonToAddNewItem);
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SessionActivity.this, NewItemActivity.class));
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.notifyDataSetChanged();
    }
}