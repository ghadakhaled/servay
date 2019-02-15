package com.example.ghada.newdesigndentelclinic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Signin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
    }

    public void Login(View view)
    {
        startActivity(new Intent(Signin.this,Homeclinic.class));
    }
}
