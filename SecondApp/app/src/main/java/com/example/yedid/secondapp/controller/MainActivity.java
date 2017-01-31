package com.example.yedid.secondapp.controller;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.yedid.secondapp.R;
import com.example.yedid.secondapp.model.entities.Activity;
import com.example.yedid.secondapp.model.entities.Business;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,ActivityFragment.OnFragmentInteractionListener,BusinessFragment.OnListFragmentInteractionListener {

    public FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager.beginTransaction().add(R.id.fregament_container,BusinessFragment.newInstance(1)).commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rand = new Random();
                Snackbar.make(view, messages.get(rand.nextInt(messages.size() - 1)), 3000)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        messages.add("Wining isn't everything ,it's just the only thing that matters");
        messages.add("Life is a game ,the question is ,do you know how to play?");
        messages.add("שונא מתנות יחיה ואוהב מתנות יחיה הרבה יותר טוב");
        messages.add("תמיד שמעתי שהנקמה לא משתלמת ,שאחרי שאתה משיג סוף סוף את המטרה אתה מרגיש מדוכודך ולא מסופק.זה טמטום בריבוע!!!!!(קרב אש)");
        messages.add("יש שני סוגי אנשים בכולם,כאלה שראו אווטאר וכאלה שלא מודים בזה");
        messages.add("thanks to erelf7 for the entertaining idea");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            //if drawer is open
            drawer.closeDrawer(GravityCompat.START); //close navigation bar
        } else if (fragmentManager.getBackStackEntryCount() > 0) {
            //if it's not the last fragment
            fragmentManager.popBackStack(); //go back to the last fragment
        } else {
            super.onBackPressed(); //go back normally
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = BusinessFragment.newInstance(0);

        if (id == R.id.nav_actvities) {
            fragment = ActivityFragment.newInstance("","");
        } else if (id == R.id.nav_businesses) {
            fragment = BusinessFragment.newInstance(0);
        } else if (id == R.id.nav_exit) {
            this.finish();
        } else  {

        }

        //switch fragment content
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fregament_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private ArrayList<String> messages = new ArrayList<>();
    protected void ShowMessage(View view)
    {
        Random rand = new Random();
        Toast.makeText(this,messages.get(rand.nextInt(messages.size() - 1)),Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onFragmentInteraction(Activity activity) {

    }

    @Override
    public void onListFragmentInteraction(Business item) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fregament_container,BusinessInfo.newInstance("",""));
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
