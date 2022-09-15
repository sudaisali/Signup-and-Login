package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
EditText ET_Email,ET_Password;
Button BTN_Login;
TextView TV_Signup;
FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ET_Email =findViewById(R.id.ET_Email);
        ET_Password = findViewById(R.id.ET_Password);
        BTN_Login = findViewById(R.id.BTN_Login);
        TV_Signup = findViewById(R.id.TV_Signup);
        mauth = FirebaseAuth.getInstance();

        TV_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,MainActivity.class);
                startActivity(intent);
            }
        });
        BTN_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdata();
            }
        });


    }
    String email , password;
    public void getdata(){
        email=ET_Email.getText().toString().trim();
        password = ET_Password.getText().toString();
        login();
    }
    public void login(){
        mauth.signInWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(getApplicationContext(),"SuccessFully Login",Toast.LENGTH_SHORT).show();
                  Intent intent = new Intent(Login.this , Helloworld.class);
                  startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Failed to login",Toast.LENGTH_SHORT).show();

            }
        });
    }
}