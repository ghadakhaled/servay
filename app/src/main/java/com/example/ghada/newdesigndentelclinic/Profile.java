package com.example.ghada.newdesigndentelclinic;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

public class Profile extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    FirebaseAuth auth;
    TextView name, phone, email;
    FirebaseDatabase database;
    DatabaseReference reference,reference_checkup;
    ImageView imageView;
    Toolbar toolbar;
    Patient patient;
    DrawerLayout drawer;
    String date;
    ProgressBar bar;
    checkup check;
    CardView check_up,consult,Medication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_test);

        imageView = findViewById(R.id.backhomebage);
        imageView.setOnClickListener(this);
        name = findViewById(R.id.nameprofile);
        phone = findViewById(R.id.Phoneprofile);
        email = findViewById(R.id.Emailprofile);
        drawer = findViewById(R.id.drawer_profile);
        bar=findViewById(R.id.progre_profile);

         check = new checkup();

        check_up=findViewById(R.id.check_up);
        consult=findViewById(R.id.consult);
        Medication=findViewById(R.id.Medication);

        check_up.setOnClickListener(this);
        consult.setOnClickListener(this);
        Medication.setOnClickListener(this);
        toolbar = findViewById(R.id.toolbarid);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (
                        this, drawer, toolbar, R.string.navigation_drawer_close, R.string.navigation_drawer_close);


        drawer.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(android.R.color.white));
        toggle.syncState();
        toolbar.setNavigationIcon(R.drawable.menu);
        NavigationView navigationView = findViewById(R.id.nav_profile);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem)
                    {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);

                        switch (menuItem.getItemId()) {
                            case R.id.id_serves:
                                startActivity(new Intent(Profile.this, services.class));
                                break;
                            case R.id.checkin:
                                Toast.makeText(getApplicationContext(), "Checkin", Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.help:
                                Toast.makeText(getApplicationContext(), "Help", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.logout:
                                auth = FirebaseAuth.getInstance();
                                auth.signOut();
                                finish();
                                startActivity(new Intent(Profile.this, MainActivity.class));
                                break;
                        }

                        // close drawer when item is tapped
                        drawer.closeDrawers();
                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here
                        return true;
                    }
                });

    }

    public void onBackPressed() {
        drawer = findViewById(R.id.draw);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        super.onBackPressed();
    }

    @Override
    protected void onStart()
    {
        bar.setVisibility(View.VISIBLE);

        super.onStart();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("Patient").child(auth.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                patient = dataSnapshot.getValue(Patient.class);
                name.setText(patient.getName());
                phone.setText(patient.getPhone());
                email.setText(patient.getEmail());
                bar.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Profile.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void getcheck()
    {
        check.setName(patient.getName());
        check.setEmail(patient.getEmail());
        check.setPhone(patient.getPhone());
        check.setDate(date);
    }
    public void Signupprofile(View view)
    {
        auth.signOut();
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backhomebage:
                startActivity(new Intent(Profile.this, Homepage.class));
                break;
            case R.id.check_up:
                DialogFragment datepacker = new pop_up();
                datepacker.show(getSupportFragmentManager(),"date picker");
                    break;
            case R.id.consult:
                Toast.makeText(this, "consult", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Medication:
                Toast.makeText(this, "Medication", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
         date = DateFormat.getDateInstance().format(c.getTime());
        Toast.makeText(this, date, Toast.LENGTH_SHORT).show();
        reference_checkup = FirebaseDatabase.getInstance().getReference("checkup");
        reference_checkup.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                getcheck();
                reference_checkup.child(auth.getUid()).setValue(check);
                Toast.makeText(getApplicationContext(), "Database insert.....", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Profile.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}


