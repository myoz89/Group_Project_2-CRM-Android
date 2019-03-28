package com.example.groupproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;
import java.util.GregorianCalendar;

import Model.AllUsers;
import Model.Owner;

public class AddAppointmentActivity extends AppCompatActivity {

    private Owner owner;
    private String ownerId;
    private AllUsers allUsers;

    private EditText mm,dd,yy,custId;
    private int month,day,year;
    private String customerId;

    private Button cretApt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);

        ownerId = (String) getIntent().getSerializableExtra("ownerid");
        allUsers = (AllUsers) getIntent().getSerializableExtra("AllUsers");
        owner = allUsers.getOwnerBasedOnID(ownerId);

        mm = findViewById(R.id.mm);
        dd = findViewById(R.id.dd);
        yy = findViewById(R.id.yy);
        custId = findViewById(R.id.editText5);

        cretApt = findViewById(R.id.crtapt);
        cretApt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                month = Integer.valueOf(mm.getText().toString());
                day = Integer.valueOf(dd.getText().toString());
                year = Integer.valueOf(yy.getText().toString());
                customerId = custId.getText().toString();

                Calendar cal = new GregorianCalendar(month,day,year);
                owner.addAppointment(cal,allUsers.getCustomerBasedOnID(customerId));

                Intent intent = new Intent(AddAppointmentActivity.this, OwnerMainMenuActivity.class);
                startActivity(intent);
            }
        });

    }
}
