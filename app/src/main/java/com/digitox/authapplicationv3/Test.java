package com.digitox.authapplicationv3;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

public class Test extends AppCompatActivity {

    // TextLayouts
    TextInputLayout layoutFirstName, layoutLastName, layoutEmailId, layoutMobile, layoutDOB, layoutGender, layoutLanguage, layoutPassword, layoutConfirmPass;
    //

    // Input Fields
    private ShapeableImageView imageViewProfile;
    private TextInputEditText inputFirstName, inputLastName, inputEmailId, inputMobile, inputPassword, inputConfirmPass;
    MaterialButton buttonProfile, buttonSignUp;
    private MaterialAutoCompleteTextView autoTextDOB;
    private MaterialAutoCompleteTextView autoTextGender;
    private MaterialAutoCompleteTextView autoTextLanguages;
    MaterialTextView signInTextView;
    //

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
        setContentView(R.layout.activity_test);


        imageViewProfile = findViewById(R.id.imageView_profile);
        buttonProfile = findViewById(R.id.button_uploadProfile);
        autoTextDOB = findViewById(R.id.autoComplete_dob);
        autoTextGender = findViewById(R.id.autoComplete_gender);
        autoTextLanguages = findViewById(R.id.autoComplete_language);
        signInTextView = findViewById(R.id.button_signIn);


        // [Validation]






        // [End Validation]


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
                Test.this,
                R.layout.dropdown_item_menu,
                languagesArray
        );

        //ArrayAdapter for Gender
        autoTextLanguages.setAdapter(adapterLanguage);

        ArrayAdapter<String> adapterGender = new ArrayAdapter<>(
                Test.this,
                R.layout.dropdown_item_menu,
                genderArray
        );
        autoTextGender.setAdapter(adapterGender);

        //Sign Up Listener
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 submitData();
            }
        });

        // Sign in Listener
        signInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void submitData() {
        if(checkValidation()) {

        }
    }

    private boolean checkValidation() {

        return true;
    }

}