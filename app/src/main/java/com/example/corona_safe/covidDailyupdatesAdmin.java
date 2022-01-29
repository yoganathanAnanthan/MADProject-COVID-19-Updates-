package com.example.corona_safe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.corona_safe.database.DBHelper;

import java.util.List;

import android.os.Bundle;

public class covidDailyupdatesAdmin extends AppCompatActivity {
    EditText edtProvince,edtConfirmed,edtDeaths,edtRecovered;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_dailyupdates_admin);
        edtProvince = findViewById(R.id.etProvince);
        edtConfirmed = findViewById(R.id.etConfirmed);
        edtDeaths = findViewById(R.id.etDeaths);
        edtRecovered = findViewById(R.id.etRecovered);
    }
    public void AddProvince(View view){
        String province = edtProvince.getText().toString();
        String confirmed = edtConfirmed.getText().toString();
        String deaths = edtDeaths.getText().toString();
        String recovered = edtRecovered.getText().toString();
        DBHelper dbHelper = new DBHelper(this);

        if(province.isEmpty()||confirmed.isEmpty()||deaths.isEmpty()||recovered.isEmpty()){
            Toast.makeText(this,"Please enter values",Toast.LENGTH_SHORT).show();
        }else{
            long inserted = dbHelper.addInfo(province,confirmed,deaths,recovered);

            if(inserted>0){
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                edtProvince.setText("");
                edtConfirmed.setText("");
                edtDeaths.setText("");
                edtRecovered.setText("");
            }else{
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void viewAll(View view){
        DBHelper dbHelper = new DBHelper(this);

        List info = dbHelper.readAll();

        String[] infoArray = (String[]) info.toArray(new String[0]);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Province Details");

        builder.setItems(infoArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String province = infoArray[i].split(":")[0];
                String confirmed = infoArray[i].split(":")[1];
                String deaths = infoArray[i].split(":")[2];
                String recovered = infoArray[i].split(":")[3];

                edtProvince.setText(province);
                edtConfirmed.setText(confirmed);
                edtDeaths.setText(deaths);
                edtRecovered.setText(recovered);

            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void deleteProvince(View view){
        DBHelper dbHelper = new DBHelper(this);
        String province = edtProvince.getText().toString();

        if(province.isEmpty()){
            Toast.makeText(this, "Select a province", Toast.LENGTH_SHORT).show();
        }else{
            dbHelper.deleteInfo(province);
            Toast.makeText(this, "province is deleted", Toast.LENGTH_SHORT).show();
            edtProvince.setText("");
            edtConfirmed.setText("");
            edtDeaths.setText("");
            edtRecovered.setText("");
        }
    }

    public void updateProvince(View view){
        DBHelper dbHelper = new DBHelper(this);
        String province = edtProvince.getText().toString();
        String confirmed = edtConfirmed.getText().toString();
        String deaths = edtDeaths.getText().toString();
        String recovered = edtRecovered.getText().toString();

        if(province.isEmpty()||confirmed.isEmpty()||deaths.isEmpty()||recovered.isEmpty()){
            Toast.makeText(this, "Select or type province", Toast.LENGTH_SHORT).show();
        }else{
            dbHelper.updateInfo(view,province,confirmed,deaths,recovered);
            edtProvince.setText("");
            edtConfirmed.setText("");
            edtDeaths.setText("");
            edtRecovered.setText("");
        }
    }

    public void goHome(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}