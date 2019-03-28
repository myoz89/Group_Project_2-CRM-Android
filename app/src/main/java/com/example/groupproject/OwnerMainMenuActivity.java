package com.example.groupproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class OwnerMainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_main_menu);

        // can remove it later, just add for testing
        Toast.makeText(getBaseContext(),"signed in as owner!",Toast.LENGTH_SHORT).show();
    }
}
