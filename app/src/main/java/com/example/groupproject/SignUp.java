package com.example.groupproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import Model.AllUsers;
import Model.ReObject;

public class SignUp extends AppCompatActivity {
    Button cancel;
    AllUsers allUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //Initialize the sign up menu
        cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected ReObject signUpAsOwner(String name, String id, String pw) {
        ReObject ret = new ReObject();

        return ret;
    }

    protected ReObject signUpAsCustomer(String name, String id, String pw, String aName) {
        ReObject ret = new ReObject();

        return ret;
    }
}
