package com.digitox.authapplicationv3;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class DataCatch extends AppCompatActivity {

    MaterialTextView id;
    MaterialTextView userId;
    MaterialTextView userTitle;
    MaterialTextView userBody;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_catch);

        Intent intent = getIntent();
        ArrayList<String> user = intent.getStringArrayListExtra("user");

        id = findViewById(R.id.Id);
        userId = findViewById(R.id.userId);
        userTitle = findViewById(R.id.userTitle);
        userBody = findViewById(R.id.userBody);


        id.setText(user.get(0));
        userId.setText(user.get(1));
        userTitle.setText(user.get(2));
        userBody.setText(user.get(3));


    }
}