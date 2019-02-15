package com.example.ghada.newdesigndentelclinic;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class Resrvation extends AppCompatActivity
{
    RecyclerView recycler;
    ArrayList<ReservationClinic>Reserv;
    DatabaseReference reference;
    Adapter_reservation adapter;
    ReservationClinic Reservclinic;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resrvation);

        recycler=findViewById(R.id.recycle);
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        Reserv=new ArrayList<ReservationClinic>();
        reference= FirebaseDatabase.getInstance().getReference().child("ReservationClinic");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                Reserv.clear();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    Reservclinic = dataSnapshot1.getValue(ReservationClinic.class);
                    Reserv.add(Reservclinic);
                }
                adapter=new Adapter_reservation(Resrvation.this,Reserv);
                recycler.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });

    }

}
