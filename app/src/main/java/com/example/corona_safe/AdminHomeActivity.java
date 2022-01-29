package com.example.corona_safe;

import android.os.Bundle;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.MenuItem;
import android.view.SearchEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import static android.widget.Toast.LENGTH_SHORT;

public class AdminHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView disAdminNav;
    Toolbar toolbar;
    View navHeaderView;
    EditText editNIC, editUser, editEmail, editVacc, editVacST;
    SearchEvent searchUser;
    Button btnV0,btnV1,btnV2,btnV3, btnAddnew;
    CustomerDBHelper CustomersDB;
    String editVacst = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminhome);

        drawerLayout = findViewById(R.id.drawer_layout1);
        navigationView=findViewById(R.id.nav_view1);
        navHeaderView= navigationView.inflateHeaderView(R.layout.headerid);
        //disAdminNav= (TextView) navHeaderView.findViewById(R.id.disAdminNav);
        toolbar=findViewById(R.id.toolbar11);
        editNIC = (EditText) findViewById(R.id.editNIC);
        editUser = (EditText) findViewById(R.id.editUser);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editVacc = (EditText) findViewById(R.id.editVacc);
        editVacST = (EditText) findViewById(R.id.editVacST);
        CustomersDB = new CustomerDBHelper(this);
        btnAddnew = (Button) findViewById(R.id.btnAddnew);
        //btnV0 = (Button) findViewById(R.id.btnV0);
       // btnV1 = (Button) findViewById(R.id.btnV1);
       // btnV2 = (Button) findViewById(R.id.btnV2);
       // btnV3 = (Button) findViewById(R.id.btnV3);


        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_homea);


        btnAddnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = editUser.getText().toString();
                String mail = editEmail.getText().toString();
                String nic = editNIC.getText().toString();
                String vacc = editVacc.getText().toString();
                Integer vacst = Integer.valueOf(editVacST.getText().toString());

                if(user.equals("")||mail.equals("")||nic.equals("")||vacc.equals("")|| vacst<0||vacst>3)
                    Toast.makeText(AdminHomeActivity.this, "Please enter all the fields", LENGTH_SHORT).show();
                else{
                    Boolean checkusermail = CustomersDB.checkusernameemail(user, mail);
                    if(checkusermail==true){
                            Boolean insert = CustomersDB.updateDataAsAdmin(user, nic, vacc, vacst);
                            if(insert==true){
                                Toast.makeText(AdminHomeActivity.this, "Added successfully", LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),AdminHomeActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(AdminHomeActivity.this, "Registration failed", LENGTH_SHORT).show();
                            }
                    }else{
                        Toast.makeText(AdminHomeActivity.this, "username not matching", LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {

        switch (menuitem.getItemId()){
            case R.id.nav_homea:
                break;
            case  R.id.nav_updateuser:
                Toast.makeText(this,"Update Uesr",Toast.LENGTH_SHORT).show();
                Intent intent0 =new Intent(AdminHomeActivity.this,AdminUpdateActivity.class);
                startActivity(intent0);
                break;

            case  R.id.nav_signouta:
                Toast.makeText(this,"Sign out",Toast.LENGTH_SHORT).show();
                Intent intent1 =new Intent(AdminHomeActivity.this,MainActivity.class);
                startActivity(intent1);
                break;

            case  R.id.nav_updatesa:
                Toast.makeText(this,"Covid detail updates",Toast.LENGTH_SHORT).show();
                Intent intent2 =new Intent(AdminHomeActivity.this,covidDailyupdatesAdmin.class);
                startActivity(intent2);
                break;

            case  R.id.nav_lockdownDDa:
                Toast.makeText(this,"Covid detail updates",Toast.LENGTH_SHORT).show();
                Intent intent3 =new Intent(AdminHomeActivity.this,AddLockdownDashboard.class);
                startActivity(intent3);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
