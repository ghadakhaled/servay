package com.example.ghada.newdesigndentelclinic;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText email, password;
    Button signin;
    Button Signup;
    ProgressDialog dialog;
    FirebaseAuth firebaseAuth;
    String Email, Password;
    Patient patient;
    private FirebaseAuth.AuthStateListener authListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen1);
        firebaseAuth = FirebaseAuth.getInstance();

         if (firebaseAuth.getCurrentUser() != null )
        {
            //profile activity here
            finish();
            startActivity(new Intent(this, Homepage.class));
        }

        dialog = new ProgressDialog(this);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signin = findViewById(R.id.Signin);
        Signup = findViewById(R.id.sigup);

        signin.setOnClickListener(this);
        Signup.setOnClickListener(this);
    }


    private void UserLogin()
    {
        Email = email.getText().toString().trim();
        Password = password.getText().toString().trim();

        if (TextUtils.isEmpty(Email)) {
            Toast.makeText(this, "Plz enter your email", Toast.LENGTH_LONG).show();
            //email is empty
            return;
        }
        if (TextUtils.isEmpty(Password)) {
            // pasword is empty
            Toast.makeText(this, "plz enter your password", Toast.LENGTH_LONG).show();
            return;
        }
        dialog.setMessage("PLz wait...");
        dialog.show();

        firebaseAuth.signInWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        dialog.dismiss();
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(MainActivity.this, Homepage.class));
                        } else {
                            Toast.makeText(MainActivity.this, "couldnt register plz try agian", Toast.LENGTH_LONG).show();
                        }
                    }
                });
   }
    @Override
    public void onClick(View v) {
        if (v == signin) {
            UserLogin();
        }
        if (v == Signup) {
            finish();
            startActivity(new Intent(this, Signup.class));
        }
    }


    public void goHomepage(View view)
    {
        startActivity(new Intent(MainActivity.this, Homepage.class));

    }


    public class DownloadTask
    {
    }
}