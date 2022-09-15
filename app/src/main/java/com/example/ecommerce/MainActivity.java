package com.example.ecommerce;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    //step 5
   public FirebaseAuth mauth;


    // Step -1 -> create objects

    EditText ET_username,ET_password,ET_Email;
    Button BTN_login;
    TextView TV_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // step 6

        mauth = FirebaseAuth.getInstance();

        //  Step-2 -> connect components of xml file with java file
        ET_username = findViewById(R.id.ET_Username);
        ET_password = findViewById(R.id.ET_Password);
        ET_Email = findViewById(R.id.ET_Email);
        BTN_login = findViewById(R.id.BTN_Signup);
        TV_Login = findViewById(R.id.TV_Login);

        BTN_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getinputdata();

            }
        });
        TV_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);
            }
        });

        //   Step-3 ->
           //1- get data from components to variable
          //2-  create Account in fire base
         //3-   save data in fire base

    }

    //step 4
    String username,email,password;
    public void getinputdata(){

        username = ET_username.getText().toString().trim();
        email = ET_Email.getText().toString().trim();
        password = ET_password.getText().toString().trim();
         createaccount();
    }

    // step 7
    public void createaccount(){
        mauth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(
                new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(getApplicationContext(),"Account create successfully",Toast.LENGTH_SHORT).show();

                        savefirebasedata();
                    }
                }
        )
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),""+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

    }
     // step 8
    public void savefirebasedata(){
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("User_Id",mauth.getUid());
        hashMap.put("User_Name",username);
        hashMap.put("Email",email);
        DatabaseReference refrence = FirebaseDatabase.getInstance().getReference("Accounts");
        refrence.child(mauth.getUid()).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(),"Account created Successfuly",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Sorry Account not created",Toast.LENGTH_SHORT).show();
            }
        });

    }
}