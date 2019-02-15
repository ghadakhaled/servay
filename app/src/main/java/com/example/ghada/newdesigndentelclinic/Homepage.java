package com.example.ghada.newdesigndentelclinic;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

public class Homepage extends AppCompatActivity {
    LinearLayout mdotlayout;
    public DrawerLayout drawer;
    FirebaseAuth auth;
    Toolbar toolbar;
    TextView[] mDot;
    FirebaseAuth firebaseAuth;
    private String images[] = {"https://aladinia.a.ssl.fastly.net/media/catalog/product/cache/1/thumbnail/408x272/9df78eab33525d08d6e5fb8d27136e95/b/l/sec_blanqueamiento-dental-led-barcelona-1.jpg",
            "http://www.apexdentalcare.net/wp-content/uploads/2016/10/apex-dental-2.jpg",
            "http://www.darpannepal.com/wp-content/uploads/2018/05/teeth-1.jpg"};
    ViewPager viewPager;
    viewpaperAdapter viewpaperAdapter;

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.Mydrawer2);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage2);
        drawer = findViewById(R.id.Mydrawer2);
        toolbar = findViewById(R.id.toolbar);
        viewPager = findViewById(R.id.viewpapper);
        viewpaperAdapter = new viewpaperAdapter(Homepage.this, images);
        viewPager.setAdapter(viewpaperAdapter);
       // addDot();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (
                        this, drawer, toolbar, R.string.navigation_drawer_close, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(android.R.color.white));
        toggle.syncState();
        toolbar.setNavigationIcon(R.drawable.menu);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);

                        switch (menuItem.getItemId()) {
                            case R.id.id_profile:
                                startActivity(new Intent(Homepage.this, Profile.class));
                                break;
                            case R.id.id_serves:
                                startActivity(new Intent(Homepage.this,services.class));
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
                                startActivity(new Intent(Homepage.this, MainActivity.class));
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


    public void addDot() {
        mDot = new TextView[3];
        for (int i = 0; i < mDot.length; i++) {
            mDot[i] = new TextView(this);
            mDot[i].setText(Html.fromHtml("6#8226;"));
            mDot[i].setTextSize(35);
            mDot[i].setTextColor(getResources().getColor(R.color.colorTransparent));
            mdotlayout.addView(mDot[i]);

        }
    }

}










