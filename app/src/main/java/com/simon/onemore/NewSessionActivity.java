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
import java.util.List;

public class NewSessionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_session);
        getSupportActionBar().setSubtitle("New Session");

        // Button action
        final Button createButton = findViewById(R.id.buttonNewSession);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TextView newSessionName = findViewById(R.id.textNewSessionName);
                final SessionData newSession = new SessionData();
                newSession.name = newSessionName.getText().toString();
                newSession.items = new ArrayList<>();
                DataLayer.getInstance().getSessions().add(newSession);
                DataLayer.getInstance().setActiveSession(newSession);
                DataLayer.getInstance().saveData(NewSessionActivity.this);

                startActivity(new Intent(NewSessionActivity.this, SessionActivity.class));
                finish();
            }
        });

        // Enable button only if session name is set
        final EditText newSessionNameTextField = findViewById(R.id.textNewSessionName);
        newSessionNameTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                List<SessionData> sessions = DataLayer.getInstance().getSessions();
                final boolean existsAlready = sessions.stream().anyMatch(x -> x.name.equals(editable.toString()));
                createButton.setEnabled(editable.length() > 0 && !existsAlready);
            }
        });
    }
}