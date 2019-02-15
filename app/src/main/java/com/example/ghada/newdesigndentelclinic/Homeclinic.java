package com.example.ghada.newdesigndentelclinic;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Homeclinic extends AppCompatActivity implements View.OnClickListener
{
    TextView username,user_login;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homeclinic);
        username = findViewById(R.id.username);
        user_login =findViewById(R.id.user_Login);
        username.setOnClickListener(this);
      }

    @Override
    protected void onStart()
    {
        super.onStart();
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null) {
            //profile activity here
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
        else
        {
            database=FirebaseDatabase.getInstance();
            reference=FirebaseDatabase.getInstance().getReference("Patient").child(auth.getUid());
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {
                    Patient patient = dataSnapshot.getValue(Patient.class);
                    user_login.setText(patient.getName());
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError)
                {
                    Toast.makeText(Homeclinic.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }

    }

    public void NewReservation(View view)
    {
        startActivity(new Intent(Homeclinic.this,Resrvation.class));
    }

    @Override
    public void onClick(View v)
    {
        auth.signOut();
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }
}
