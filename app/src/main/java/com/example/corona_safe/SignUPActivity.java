package com.example.corona_safe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class SignUPActivity extends AppCompatActivity {

    private  TextView textSignInMini;
    private Context context;
    EditText editTextTextUserName,editTextTextEmailAddress,editTextTextPassword,editTextTextPassword2;
    Button btnSignUp;
    CustomerDBHelper CustomersDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_upactivity);
        context = this;
        editTextTextUserName = (EditText) findViewById(R.id.editTextTextUserName);
        editTextTextEmailAddress = (EditText) findViewById(R.id.editTextTextUsername1);
        editTextTextPassword = (EditText) findViewById(R.id.editTextTextPassword3);
        editTextTextPassword2 = (EditText) findViewById(R.id.editTextTextPassword2);
        btnSignUp = (Button) findViewById((R.id.btnSignUp));
        textSignInMini = findViewById(R.id.textSignInMini);
        CustomersDB = new CustomerDBHelper(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = editTextTextUserName.getText().toString();
                String email = editTextTextEmailAddress.getText().toString();
                String pass = editTextTextPassword.getText().toString();
                String repass = editTextTextPassword2.getText().toString();

                if(user.equals("")||email.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(SignUPActivity.this, "Please enter all the fields", LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = CustomersDB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = CustomersDB.insertData(user, email, pass);
                            if(insert==true){
                                Toast.makeText(SignUPActivity.this, "Registered successfully", LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(SignUPActivity.this, "Registration failed", LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(SignUPActivity.this, "User already exists! please sign in", LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SignUPActivity.this, "Passwords not matching", LENGTH_SHORT).show();
                    }
                }

            }
        });

        textSignInMini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,SignInActivity.class);
                startActivity(intent);
            }
        });

    }
}
