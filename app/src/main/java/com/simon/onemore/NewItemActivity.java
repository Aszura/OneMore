package com.simon.onemore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class NewItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        getSupportActionBar().setSubtitle("New Item");

        // Button action
        Button createButton = findViewById(R.id.buttonCreateNewItem);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView newItemName = findViewById(R.id.textNewItemName);
                ItemData newItem = new ItemData();
                newItem.name = newItemName.getText().toString();
                newItem.count = 1;
                DataLayer.getInstance().getActiveSession().items.add(newItem);
                DataLayer.getInstance().saveData(NewItemActivity.this);
                finish();
            }
        });

        // Enable button only if session name is set
        EditText newItemNameTextField = findViewById(R.id.textNewItemName);
        newItemNameTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                createButton.setEnabled(editable.length() > 0);
            }
        });
    }
}