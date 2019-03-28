package com.example.groupproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import Model.AllUsers;
import Model.Owner;

public class OwnerMainMenuActivity extends AppCompatActivity {

    private Button addApts;
    private AllUsers allUsers;
    private  String ownerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_main_menu);

        // userid and allusers from main activity
        ownerId = (String) getIntent().getSerializableExtra("userid");
        allUsers = (AllUsers) getIntent().getSerializableExtra("alluser");

        // can remove it later, just add for testing
        Toast.makeText(getBaseContext(),"signed in as owner!",Toast.LENGTH_SHORT).show();

        addApts = findViewById(R.id.addapts);
        addApts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerMainMenuActivity.this, AddAppointmentActivity.class);
                intent.putExtra("ownerid",ownerId);
                intent.putExtra("AllUsers", allUsers);
                startActivity(intent);
            }
        });


    }
}
