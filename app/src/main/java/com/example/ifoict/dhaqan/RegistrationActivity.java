package com.example.ifoict.dhaqan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    EditText editTextEmailRegister, editTextPasswordRegister;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();
        editTextPasswordRegister = (EditText) findViewById(R.id.editTextPasswordRegister);
        editTextEmailRegister = (EditText) findViewById(R.id.editTextEmailRegister);

        currentUser = mAuth.getCurrentUser();

        if(currentUser != null){
            openProfileActivity();
        }
    }

    public void openLoginActivity (View view){
        Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
        finish();
        startActivity(i);

    }
    public void openProfileActivity (){
        Intent i = new Intent(RegistrationActivity.this, ProfileActivity.class);
        finish();
        startActivity(i);
    }

    public void registerUser(View v){
        email = editTextEmailRegister.getText().toString().trim();
        password = editTextPasswordRegister.getText().toString().trim();

        if (email.equals("") || password.equals("")){
            Toast.makeText(RegistrationActivity.this, "Fill in all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegistrationActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    openProfileActivity();
                } else {
                    Toast.makeText(RegistrationActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
