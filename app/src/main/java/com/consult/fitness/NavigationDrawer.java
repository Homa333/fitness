package com.consult.fitness;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class NavigationDrawer extends AppCompatActivity {

    private NavigationView nav;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private TextView username, email;
    private ImageView nav_header_image;
    private boolean doubleBackToExitPressedOnce = false;
    private Bundle bundle;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        initViews();

        bundle = new Bundle();

        View vi = nav.getHeaderView(0);

        String name_user = getIntent().getStringExtra("name");
        String email_user  =getIntent().getStringExtra("email");
        String id_user = getIntent().getStringExtra("id");

        username = vi.findViewById(R.id.username);
        username.setText(name_user);
        email = vi.findViewById(R.id.email);
        email.setText(email_user);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_section,
                    new HomeFragment()).commit();
            nav.getMenu().getItem(0).setChecked(true);
        }

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_home:
                        Toast.makeText(getApplicationContext(), "Home Panel is Open", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_section,
                                new HomeFragment()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_shop:
                        bundle.putString("id", id_user);
                        ShopFragment shopFragment= new ShopFragment();
                        shopFragment.setArguments(bundle);
                        Toast.makeText(getApplicationContext(), "Shop is Open", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_section,
                                shopFragment).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_settings:
                        bundle.putString("id", id_user);
                        SettingsFragment settingsFragment = new SettingsFragment();
                        settingsFragment.setArguments(bundle);
                        Toast.makeText(getApplicationContext(), "Settings is Open", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_section,
                                settingsFragment).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case  R.id.menu_logout:
                        new AlertDialog.Builder(NavigationDrawer.this)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setTitle("Logout")
                                .setMessage("Are you sure you want to Logout?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                        Intent intent = new Intent(NavigationDrawer.this, MainActivity.class);
                                        startActivity(intent);
                                    }

                                })
                                .setNegativeButton("No", null)
                                .show();

                }
                return true;
            }
        });



    }

    void initViews(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nav= (NavigationView) findViewById(R.id.nav_menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);

//        nav_header_image = (ImageView) vi.findViewById(R.id.nav_header_image);
//
//
//        email =(TextView) vi.findViewById(R.id.email);

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);
        }
    }

}