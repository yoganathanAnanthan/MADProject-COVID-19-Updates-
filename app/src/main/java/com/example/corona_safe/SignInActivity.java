    package com.example.corona_safe;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


    public class SignInActivity extends AppCompatActivity {

    private TextView textSignUpSmall;
    private Context context;
    EditText editTextTextUsername1, editTextTextPassword3;
    Button btnSignIn, btnSignInAdmin;
    CustomerDBHelper CustomersDB;
    Cursor c = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        editTextTextUsername1 = (EditText) findViewById(R.id.editTextTextUsername1);
        editTextTextPassword3 = (EditText) findViewById(R.id.editTextTextPassword3);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignInAdmin = (Button) findViewById(R.id.btnSignInAdmin);
        CustomersDB = new CustomerDBHelper(this);

        textSignUpSmall = findViewById(R.id.textSignUpSmall);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = editTextTextUsername1.getText().toString();
                String pass = editTextTextPassword3.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(SignInActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = CustomersDB.checkusernamepassword(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(SignInActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.putExtra("username",user);
                        startActivity(intent);
                    }else{
                        Toast.makeText(SignInActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        textSignUpSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUPActivity.class);
                startActivity(intent);
            }
        });


        btnSignInAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminHomeActivity.class);
                startActivity(intent);
            }
        });

    }
}
