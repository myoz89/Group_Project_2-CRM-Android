package com.example.groupproject;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import Model.AllUsers;
import Model.Owner;

import static Controller.IO.writeToFile;


public class OwnerMainMenuActivity extends AppCompatActivity {
    private static final int APPT_ACTIVITY_REQUEST_CODE = 0;
    AllUsers allUsers;
    Owner owner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_main_menu);
        final Intent intent = getIntent();
        String ownerId = intent.getStringExtra("ownerid");
        allUsers = (AllUsers)intent.getSerializableExtra("alluser");
        owner = allUsers.getOwnerBasedOnID(ownerId);
        final Context context = this;

        // can remove it later, just add for testing
        Toast.makeText(getBaseContext(),"it is apts list size" + owner.getaptssize(),Toast.LENGTH_SHORT).show();

        //manage credits
        Button butCredits = findViewById(R.id.manage_credits);
        butCredits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerMainMenuActivity.this, CreditActivity.class);
                //send string id via intent so credit activity can get either customer or owner
                intent.putExtra("id",owner.getID());
                intent.putExtra("alluser",allUsers);
                startActivity(intent);
                }
            });



        //cancel button
        Button butCancel = findViewById(R.id.sign_out_button);
        butCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send data back to MainActivity
                intent.putExtra("AllUsers", allUsers);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        // add appointment button
        Button addApts = findViewById(R.id.addapts);
        addApts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerMainMenuActivity.this, AddAppointmentActivity.class);
                intent.putExtra("ownerid",owner.getID());
                intent.putExtra("alluser", allUsers);
                startActivityForResult(intent, APPT_ACTIVITY_REQUEST_CODE);
            }
        });

        // view appointment button
        Button viewApts = findViewById(R.id.viewapts);
        viewApts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerMainMenuActivity.this, DisplayOwnerAppointmentListActivity.class);
                intent.putExtra("ownerid",owner.getID());
                intent.putExtra("alluser", allUsers);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == APPT_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                allUsers = (AllUsers)data.getSerializableExtra("AllUsers");
            }
        }
    }
}
