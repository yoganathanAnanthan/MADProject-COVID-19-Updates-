package com.example.corona_safe;

import android.os.Bundle;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.view.SearchEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class AdminUpdateActivity extends AppCompatActivity {

    EditText editNICUP, editUserUP, editEmailUP, editVaccUP, editVacSTUP;
    SearchEvent searchUser;
    Button btnV0,btnV1,btnV2,btnV3, btnUpdateUP, btndeleteUP;
    CustomerDBHelper CustomersDB;
    String editVacst = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminupdate);

        editNICUP = (EditText) findViewById(R.id.editNICUP);
        editUserUP = (EditText) findViewById(R.id.editUserUP);
        editEmailUP = (EditText) findViewById(R.id.editEmailUP);
        editVaccUP = (EditText) findViewById(R.id.editVaccUP);
        editVacSTUP = (EditText) findViewById(R.id.editVacSTUP);
        CustomersDB = new CustomerDBHelper(this);
        btnUpdateUP = (Button) findViewById(R.id.btnupdateUP);
        btndeleteUP = (Button) findViewById(R.id.btndeleteUP);
        //btnV0 = (Button) findViewById(R.id.btnV0);
        // btnV1 = (Button) findViewById(R.id.btnV1);
        // btnV2 = (Button) findViewById(R.id.btnV2);
        // btnV3 = (Button) findViewById(R.id.btnV3);


        btnUpdateUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = editUserUP.getText().toString();
                String mail = editEmailUP.getText().toString();
                String nic = editNICUP.getText().toString();
                String vacc = editVaccUP.getText().toString();
                Integer  vacst = Integer.valueOf(editVacSTUP.getText().toString());

                if(user.equals("")||mail.equals("")||nic.equals("")||vacc.equals(""))
                    Toast.makeText(AdminUpdateActivity.this, "Please enter all the fields", LENGTH_SHORT).show();
                else{
                    Boolean checkusermail = CustomersDB.checkusernameemail(user, mail);
                    if(checkusermail==true){
                        Boolean insert = CustomersDB.updateDataAsAdmin(user, nic, vacc, vacst);
                        if(insert==true){
                            Toast.makeText(AdminUpdateActivity.this, "Added successfully", LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),AdminHomeActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(AdminUpdateActivity.this, "Registration failed", LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(AdminUpdateActivity.this, "username not matching", LENGTH_SHORT).show();
                    }
                }

            }
        });

        btndeleteUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = editUserUP.getText().toString();
                String mail = editEmailUP.getText().toString();
                String nic = editNICUP.getText().toString();
                String vacc = editVaccUP.getText().toString();
                Integer  vacst = Integer.valueOf(editVacSTUP.getText().toString());

                if(user.equals("")||mail.equals("")||nic.equals("")||vacc.equals(""))
                    Toast.makeText(AdminUpdateActivity.this, "Please enter all the fields", LENGTH_SHORT).show();
                else{
                    Boolean checkusermail = CustomersDB.checkusernameemail(user, mail);
                    if(checkusermail==true){
                        Boolean insert = CustomersDB.deleteDataAsAdmin(user, nic, vacc, vacst);
                        if(insert==true){
                            Toast.makeText(AdminUpdateActivity.this, "Added successfully", LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),AdminHomeActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(AdminUpdateActivity.this, "Registration failed", LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(AdminUpdateActivity.this, "username not matching", LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}
