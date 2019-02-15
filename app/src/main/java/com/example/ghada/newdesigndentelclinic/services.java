package com.example.ghada.newdesigndentelclinic;


import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class services extends AppCompatActivity implements View.OnClickListener {

    // List<Book> lstBook;
    RecyclerView myrv;
    Adapterservy adapter;
    List<servy> servyList;
    String title, des;

    ImageView imageView;
    Toolbar toolbar;
    DrawerLayout drawer;
    FirebaseAuth auth;
    ProgressBar bar;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serves);

        bar=findViewById(R.id.progre_services);

//
//        lstBook = new ArrayList<>();
//        lstBook.add(new Book("The Vegitarian", "Categorie Book", "Description book", R.drawable.thevigitarian));
//        lstBook.add(new Book("The Wild Robot", "Categorie Book", "Description book", R.drawable.thewildrobot));
//        lstBook.add(new Book("Maria Semples", "Categorie Book", "Description book", R.drawable.mariasemples));
//        lstBook.add(new Book("The Vegitarian", "Categorie Book", "Description book", R.drawable.thevigitarian));
//
//        myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
//
//        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, lstBook);
//        myrv.setLayoutManager(new GridLayoutManager(this, 2));
//        myrv.setAdapter(myAdapter);


        imageView = findViewById(R.id.backhomebage);
        imageView.setOnClickListener(this);

        services.DownloadTask task = new services.DownloadTask();
        task.execute("https://fireapp-22f8a.firebaseio.com/services.json");

        //Navgation
        drawer = findViewById(R.id.draw);
        toolbar = findViewById(R.id.toolbar);

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
                                startActivity(new Intent(services.this, Profile.class));
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
                                startActivity(new Intent(services.this, MainActivity.class));
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

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls)
        {
            bar.setVisibility(View.VISIBLE);

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {

                    char current = (char) data;

                    result += current;

                    data = reader.read();

                }

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);
            String jsonString = result;//your json string here
            try {
                JSONObject jObject = new JSONObject(jsonString);
                Log.i("Data1", jObject.toString());
                Iterator<String> keys = jObject.keys();
                servyList = new ArrayList<>();

                while (keys.hasNext()) {
                    String key = keys.next();
                    Log.i("Data2", key);
                    JSONObject innerJObject = jObject.getJSONObject(key);
                    Log.i("Data3", innerJObject.get("title").toString());
                    Log.i("Data3", innerJObject.get("description").toString());
                    title = innerJObject.get("title").toString();
                    des = innerJObject.get("description").toString();
                    servyList.add(new servy(R.drawable.smileteet, title, des));
                    bar.setVisibility(View.VISIBLE);
                }
                myrv = findViewById(R.id.recyclerview_id);
                adapter = new Adapterservy(servyList, services.this);
                myrv.setLayoutManager(new GridLayoutManager(services.this, 2));
                adapter.notifyDataSetChanged();
                myrv.setAdapter(adapter);
                bar.setVisibility(View.GONE);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menue, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backhomebage:
                startActivity(new Intent(services.this, Homepage.class));
                break;
        }
    }
    }


