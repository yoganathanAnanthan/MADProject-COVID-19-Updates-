package com.example.corona_safe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    EditText district_input, tier_input, days_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //hide status bar


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        district_input = findViewById(R.id.district_input);
        tier_input = findViewById(R.id.tier_input);
        days_input = findViewById(R.id.days_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.add_lockdown(district_input.getText().toString().trim(),
                        tier_input.getText().toString().trim(),
                        Integer.valueOf(days_input.getText().toString().trim()));
            }
        });
    }
}