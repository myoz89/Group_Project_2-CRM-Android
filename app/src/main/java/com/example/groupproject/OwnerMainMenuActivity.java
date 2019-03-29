package com.example.groupproject;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import Model.AllUsers;
import Model.Owner;


public class OwnerMainMenuActivity extends AppCompatActivity {
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 2;
    AllUsers allUsers;
    Owner owner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_main_menu);
        final Intent intent = getIntent();
        allUsers = (AllUsers)intent.getSerializableExtra("alluser");
        owner = (Owner) intent.getSerializableExtra("owner");
        final Context context = this;
        // can remove it later, just add for testing
        //Toast.makeText(getBaseContext(),"signed in as owner!",Toast.LENGTH_SHORT).show();


        TextView textView = (TextView) findViewById(R.id.owner_credits);
        textView.setText(Double.toString(owner.getCredit()));

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



        //SignOut button
        Button butCancel = findViewById(R.id.sign_out_button);
        butCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send data back
                Intent intent1 = new Intent(OwnerMainMenuActivity.this,MainActivity.class);
                intent1.putExtra("AllUsers", allUsers);
                setResult(RESULT_OK, intent1);
                startActivityForResult(intent1,SECOND_ACTIVITY_REQUEST_CODE);
                finish();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                allUsers = (AllUsers)data.getSerializableExtra("AllUsers");
                owner = (Owner)data.getSerializableExtra("owner");

            }
        }
    }
}
