package com.example.corona_safe;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

import static android.view.View.*;

public class HomeActivity  extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    Toolbar toolbar;
    TextView disNIC, disVaccinename, disusernameid, disUserNav;
    CustomerDBHelper CustomersDB;
    View navHeaderView;
    ImageView frstPos, frstNeg,scndPos,scndNeg,thrdPos,thrdNeg;
    Button majicbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);

        toolbar=findViewById(R.id.toolbar);
        disNIC = findViewById(R.id.disNIC);
        disVaccinename = findViewById(R.id.disVaccinename);
        disusernameid = findViewById(R.id.disusername);
        disUserNav = findViewById(R.id.disUserNav);
        navHeaderView= navigationView.inflateHeaderView(R.layout.headerid);
        disUserNav= (TextView) navHeaderView.findViewById(R.id.disUserNav);
        frstNeg=(ImageView) findViewById(R.id.frstNeg);
        scndPos=(ImageView) findViewById(R.id.frstPos);
        scndNeg = (ImageView) findViewById(R.id.scndNeg);
        scndPos=(ImageView) findViewById(R.id.scndPos);
        thrdNeg=(ImageView) findViewById(R.id.thrdNeg);
        thrdPos=(ImageView) findViewById(R.id.thrdPos);
        majicbtn=(Button)findViewById((R.id.majicbtn)) ;


        Intent intentusername = getIntent();
        String disusername = intentusername.getStringExtra("username");

        disusernameid.setText(disusername);
        disUserNav.setText(disusername);


        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);


       CustomersDB = new CustomerDBHelper(this);
        Cursor cursor = CustomersDB.displayuserdetails(disusername);
        if(cursor==null){
            Toast.makeText(this,"empty",Toast.LENGTH_SHORT).show();
        }else {
            cursor.moveToFirst();
            String disnic = cursor.getString(3);
            String disvaccinename = cursor.getString(4);
            int disvaccinestatus = cursor.getInt(5);

            disNIC.setText(disnic);
            disVaccinename.setText(disvaccinename);
            // ii.equals(disvaccinestatus);
            //  int i=ii;
            // if(disvaccinestatus==null)
            // { i= i+0; }
                if (disvaccinestatus == 1) {
                    frstNeg.setVisibility(INVISIBLE);
                    scndPos.setVisibility(INVISIBLE);
                    thrdPos.setVisibility(INVISIBLE);
                    majicbtn.setVisibility(GONE);

                } else if(disvaccinestatus== 2){
                    frstNeg.setVisibility(INVISIBLE);
                    scndNeg.setVisibility(INVISIBLE);
                    thrdPos.setVisibility(INVISIBLE);
                    majicbtn.setVisibility(GONE);

                }else {
                    frstNeg.setVisibility(INVISIBLE);
                    scndNeg.setVisibility(INVISIBLE);
                    thrdNeg.setVisibility(INVISIBLE);
                    majicbtn.setVisibility(GONE);
                }

            }

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
    public boolean onNavigationItemSelected(@NonNull  MenuItem menuitem) {

        switch (menuitem.getItemId()){
            case R.id.nav_home:
                break;
            case  R.id.nav_settings:
                Intent intent0 =new Intent(HomeActivity.this,SettingsActivity.class);
                startActivity(intent0);
                break;
            case  R.id.nav_signout:
                Toast.makeText(this,"Sign out",Toast.LENGTH_SHORT).show();
                Intent intent1 =new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intent1);
                break;
            case  R.id.nav_updates:
                Toast.makeText(this,"covid updates",Toast.LENGTH_SHORT).show();
                Intent intent2 =new Intent(HomeActivity.this,srilankafigures.class);
                startActivity(intent2);
                break;
            case  R.id.nav_lockdownR:

                Intent intent3 =new Intent(HomeActivity.this,MainActivity_Tiers.class);
                startActivity(intent3);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }



}
