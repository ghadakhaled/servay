package com.example.ghada.newdesigndentelclinic;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Signup extends AppCompatActivity implements View.OnClickListener {
    EditText email, password,name,phone;
    Button signup;
    ProgressDialog dialog;
    FirebaseUser user;
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;
    String Email, Password,Name,Phone;
    Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            //profile activity here
            finish();
            startActivity(new Intent(this, Homeclinic.class));
        }

        dialog = new ProgressDialog(this);
        email = findViewById(R.id.Eidtemail);
        password = findViewById(R.id.Editpassword);
        name= findViewById(R.id.editName);
        phone = findViewById(R.id.editphone);
        signup = findViewById(R.id.Signup);
        user= firebaseAuth.getCurrentUser();
        signup.setOnClickListener(this);


    }

    private void Registeruser()
    {
        Email = email.getText().toString().trim();
        Password = password.getText().toString().trim();
        Name =name.getText().toString().trim();
        Phone=phone.getText().toString().trim();

        if (TextUtils.isEmpty(Name)) {
            Toast.makeText(this, "Plz enter your Name", Toast.LENGTH_LONG).show();
            //email is empty
            return;
        } else {
            if (TextUtils.isEmpty(Phone)) {
                Toast.makeText(this, "Plz enter your phone", Toast.LENGTH_LONG).show();
                //email is empty
                return;
            } else {
                if (TextUtils.isEmpty(Email)) {
                    Toast.makeText(this, "Plz enter your email", Toast.LENGTH_LONG).show();
                    //email is empty
                    return;
                } else {
                    if (TextUtils.isEmpty(Password)) {
                        // pasword is empty
                        Toast.makeText(this, "plz enter your password", Toast.LENGTH_LONG).show();
                        return;
                    }
                    else
                    {
                       if (TextUtils.isEmpty(Phone)&&TextUtils.isEmpty(Email)&&TextUtils.isEmpty(Password)&&TextUtils.isEmpty(Name))
                       {
                           Toast.makeText(this, "plz enter your Information", Toast.LENGTH_LONG).show();
                           return;
                       }
                    }
                }
            }
        }
        dialog.setMessage("User is signup...");
        dialog.show();

        firebaseAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    patient=new Patient(Email,Name,Password,Phone);
                     FirebaseDatabase.getInstance().getReference("Patient")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                             .setValue(patient) .addOnCompleteListener(new OnCompleteListener<Void>()
                     {
                         @Override
                         public void onComplete(@NonNull Task<Void> task)
                         {
                             if (task.isSuccessful())
                             {
                                 Toast.makeText(Signup.this, "regster sucsses", Toast.LENGTH_SHORT).show();
                                 dialog.dismiss();
                            finish();
                            startActivity(new Intent(Signup.this, Homeclinic.class));

                             }
                         }
                     });


                }
                else
                {
                    Toast.makeText(Signup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
                
            }
        });


    }






    @Override
    public void onClick(View v) {
        if (v == signup) {
            Registeruser();

        }
    }



}
