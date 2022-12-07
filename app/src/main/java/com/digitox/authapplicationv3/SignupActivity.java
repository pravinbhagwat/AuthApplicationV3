package com.digitox.authapplicationv3;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textview.MaterialTextView;

public class SignupActivity extends AppCompatActivity {

    MaterialTextView firstnameInput, lastnameInput, emailInput, mobileInput, passwordInput, confirmPassInput;

    private ShapeableImageView imageViewProfile;
    MaterialButton buttonProfile, buttonSignUp;
    private MaterialAutoCompleteTextView autoTextDOB;
    private MaterialAutoCompleteTextView autoTextGender;
    private MaterialAutoCompleteTextView autoTextLanguages;
    MaterialTextView signInTextView;

    private String[] languagesArray = new String[]{
            "English", "Hindi", "Marathi", "Spanish", "Russian", "Japanese", "German", "Chinese"
    };

    private String[] genderArray = new String[]{
            "Male", "Female", "Transgender"
    };

    ActivityResultLauncher<String> mTakePhoto;
    ActivityResultLauncher<Intent> CTakePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        imageViewProfile = findViewById(R.id.imageView_profile);
        buttonProfile = findViewById(R.id.button_uploadProfile);
        autoTextDOB = findViewById(R.id.autoComplete_dob);
        autoTextGender = findViewById(R.id.autoComplete_gender);
        autoTextLanguages = findViewById(R.id.autoComplete_language);
        signInTextView = findViewById(R.id.button_signIn);

        //DatePicker for DOB
        MaterialDatePicker datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date").setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();
        autoTextDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.show(getSupportFragmentManager(), "Material_Date_Picker");
                datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        autoTextDOB.setText(datePicker.getHeaderText());
                    }
                });
            }
        });

//        Selecting img from gallery
        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTakePhoto.launch("image/*");
            }
        });

        // Selecting Image from Gallery
        mTakePhoto = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                imageViewProfile.setImageURI(result);
            }
        });

        //ArrayAdapter for Language
        ArrayAdapter<String> adapterLanguage = new ArrayAdapter<>(
                SignupActivity.this,
                R.layout.dropdown_item_menu,
                languagesArray
        );

        //ArrayAdapter for Gender
        autoTextLanguages.setAdapter(adapterLanguage);

        ArrayAdapter<String> adapterGender = new ArrayAdapter<>(
                SignupActivity.this,
                R.layout.dropdown_item_menu,
                genderArray
        );

        autoTextGender.setAdapter(adapterGender);

        signInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}