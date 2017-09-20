package com.example.ifoict.dhaqan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    EditText editTextEmail, editTextPassword;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);

        currentUser = mAuth.getCurrentUser();

        if(currentUser != null){
            openProfileActivity();
        }
    }
    public void openRegistrationActivity(View view){
        Intent i =new Intent(LoginActivity.this,RegistrationActivity.class);
        finish();
        startActivity(i);
    }
    public void openProfileActivity(){
        Intent i = new Intent(LoginActivity.this, ProfileActivity.class);
        finish();
        startActivity(i);
    }

    public void loginUser(View v){
        email = editTextEmail.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();

        if (email.equals("") || password.equals("")){
            Toast.makeText(LoginActivity.this, "Fill in all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                    openProfileActivity();
                } else {
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
