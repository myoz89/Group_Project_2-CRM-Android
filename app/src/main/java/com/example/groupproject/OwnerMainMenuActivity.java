package com.example.groupproject;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import Model.AllUsers;
import Model.Owner;

import static Controller.IO.writeToFile;


public class OwnerMainMenuActivity extends AppCompatActivity {
    AllUsers allUsers;
    Owner owner;

    //Toggles for display
    private static final int ZERO_ACTIVITY_REQUEST_CODE = 0;
    boolean dName;
    boolean dCredit;
    TextView textName;
    TextView textCredit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_main_menu);
        final Intent intent = getIntent();
        allUsers = (AllUsers)intent.getSerializableExtra("alluser");
        String ownerID = intent.getStringExtra("ownerID");
        owner = allUsers.getOwnerBasedOnID(ownerID);
        final Context context = this;
        // can remove it later, just add for testing
        //Toast.makeText(getBaseContext(),"signed in as owner!",Toast.LENGTH_SHORT).show();

        //Display Info
        dName = owner.getdName();
        dCredit = owner.getdCredit();
        textName = findViewById(R.id.owner_name);
        textCredit = findViewById(R.id.owner_credits);
        if (dName) {
            textName.setText(owner.getCompanyName());
        }
        if (dCredit) {
            textCredit.setText(Double.toString(owner.getCredit()));
        }

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


        //Setting button
        Button butSettings = findViewById(R.id.setting_button);
        butSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerMainMenuActivity.this, SettingsMenu.class);
                intent.putExtra("dName", dName);
                intent.putExtra("dCredit", dCredit);
                startActivityForResult(intent, ZERO_ACTIVITY_REQUEST_CODE);
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ZERO_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                //Get data
                dName = data.getBooleanExtra("dName", false);
                dCredit = data.getBooleanExtra("dCredit", false);
                //Update and save owner
                owner.setdName(dName);
                owner.setdCredit(dCredit);

                // Save data to file
                Context context = OwnerMainMenuActivity.this;
                try {
                    writeToFile(context,allUsers);
                } catch (IOException e) {
                    e.getStackTrace();
                }

                //Up date texts
                textName.setText("");
                textCredit.setText("");
                if (dName) {
                    textName.setText(owner.getCompanyName());
                }
                if (dCredit) {
                    textCredit.setText(Double.toString(owner.getCredit()));
                }
            }
        }
    }
}
