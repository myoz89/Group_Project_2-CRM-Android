package com.example.groupproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import Model.AllUsers;

public class MainActivity extends AppCompatActivity {
    private Button bSignIn;
    private Button bSignUp;
    private AllUsers allUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize the list of users


        //Set up the buttons
        //Sign In
        bSignIn = (Button)findViewById(R.id.sign_in);
        bSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //Sign Up
        bSignUp = (Button)findViewById(R.id.sign_up);
        bSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

    }
}
