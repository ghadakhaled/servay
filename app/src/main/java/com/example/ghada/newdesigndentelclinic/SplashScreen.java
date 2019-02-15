package com.example.ghada.newdesigndentelclinic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity
{

    private ImageView imageView;
    public Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imageView= findViewById(R.id.useLogo);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.transation);
        animation.setDuration(1200);
        imageView.startAnimation(animation);
        intent = new Intent(this,MainActivity.class);
        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    sleep(4000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    startActivity(intent);
                    finish();
                }
            }
        };
        thread.start();
    }
}


