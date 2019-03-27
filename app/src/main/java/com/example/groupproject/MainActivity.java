package com.example.groupproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import Controller.IO;
import Model.AllUsers;

import static Controller.IO.readFromFile;

public class MainActivity extends AppCompatActivity {
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;
    private Button butSignIn;
    private Button butSignUp;
    private AllUsers allUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize the list of users (load from file)
        Context context = this; //Context to access file system
        try {
            allUsers = readFromFile(context);
            Toast.makeText(MainActivity.this, "Loading data...", Toast.LENGTH_SHORT).show();

        } catch (ClassNotFoundException | IOException o) {
            allUsers = new AllUsers();
        }

        //Set up the buttons
        //Sign In
        butSignIn = findViewById(R.id.sign_in);
        butSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //Sign Up
        butSignUp = findViewById(R.id.sign_up);
        butSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                intent.putExtra("AllUsers", allUsers);
                startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                allUsers = (AllUsers)data.getSerializableExtra("AllUsers");
            }
        }
    }
}
