package com.example.groupproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import Model.AllUsers;
import Model.Customer;
import Model.Owner;

public class CustomerMainMenuActivity extends AppCompatActivity {

    AllUsers allUsers;
    Customer customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_main_menu);

        final Intent intent = getIntent();
        allUsers = (AllUsers)intent.getSerializableExtra("AllUsers");
        customer = (Customer) intent.getSerializableExtra("customer");
        final Context context = this;
        // can remove it later, just add for testing
        //Toast.makeText(getBaseContext(),"signed in as owner!",Toast.LENGTH_SHORT).show();


        //manage credits
        Button butConfirm = findViewById(R.id.manage_credits);
        butConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerMainMenuActivity.this, CreditActivity.class);
                //send string id via intent so credit activity can get either customer or owner
                intent.putExtra("id",customer.getID());
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
    }
}
