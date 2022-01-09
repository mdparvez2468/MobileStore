package com.example.dreamprojectten;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout phoneET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phoneET = findViewById(R.id.emailETSignIn);
    }

    public void signUpHere(View view) {
    }

    public void signInHere(View view) {
        String phone = phoneET.getEditText().getText().toString().trim();
        if (phone.isEmpty()){
            phoneET.setError("Enter Your Phone Number Here");
        }else if (phone.length()!=11){
            phoneET.setError("Your Phone Number Have To 11 Digit");
        }else {
            Intent intent = new Intent(LoginActivity.this,VerifyActivity.class);
            intent.putExtra("phone",phone);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}