package com.digitox.authapplicationv3;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.digitox.authapplicationv3.API_Controller.APIClient2;
import com.digitox.authapplicationv3.pojo.SignUpResponse;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIActivity2 extends AppCompatActivity {

    SignUpResponse signUpResponsesData;
    TextInputEditText fullNameText, emailId, passwordText;
    MaterialButton signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiactivity2);

        fullNameText = findViewById(R.id.textInput_fullname);
        emailId = findViewById(R.id.textInput_emailId);
        passwordText = findViewById(R.id.textInput_password);
        signUpButton = findViewById(R.id.button_signIn);

        // implement setOnClickListener event on sign up Button
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // validate the fields and call sign method to implement the api
                if (validate(fullNameText) && validateEmail() && validate(passwordText)) {
                    signUp();
                }
            }
        });

    }

    private boolean validateEmail() {
        String email = emailId.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            emailId.setError("Email is not valid.");
            emailId.requestFocus();
            return false;
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validate(TextInputEditText editText) {
        // check the lenght of the enter data in EditText and give error if its empty
        if (editText.getText().toString().trim().length() > 0) {
            return true; // returns true if field is not empty
        }
        editText.setError("Please Fill This");
        editText.requestFocus();
        return false;
    }

    private void signUp() {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(APIActivity2.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        // Api is a class in which we define a method getClient() that returns the API Interface class object
        // registration is a POST request type method in which we are sending our field's data
        // enqueue is used for callback response and error
        (APIClient2.getClient().registration(fullNameText.getText().toString().trim(),
                emailId.getText().toString().trim(),
                passwordText.getText().toString().trim(),
                "email")).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                signUpResponsesData = response.body();
                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Log.d("response", t.getStackTrace().toString());
                progressDialog.dismiss();

            }
        });
    }
}