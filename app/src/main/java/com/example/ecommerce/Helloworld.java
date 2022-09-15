package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Helloworld extends AppCompatActivity {
   TextView id;
   FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mauth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_helloworld);
        id =findViewById(R.id.myid);

        id.setText(mauth.getUid());

    }
}