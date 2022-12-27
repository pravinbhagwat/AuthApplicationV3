package com.digitox.authapplicationv3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActivity extends AppCompatActivity {

    MaterialTextView textViewName, textViewEmail;
    MaterialButton buttonSignOut, buttonLocation, buttonAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        textViewName = findViewById(R.id.text_name);
        textViewEmail = findViewById(R.id.text_emailId);
        buttonSignOut = findViewById(R.id.button_signOut);
        buttonAPI  = findViewById(R.id.button_api);
        buttonLocation  = findViewById(R.id.button_location);

        FirebaseAuth.getInstance();
        textViewName.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        textViewEmail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        buttonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        });

        buttonAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });

        buttonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}