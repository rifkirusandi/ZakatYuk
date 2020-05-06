package com.si410702.java.zakatyuk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeLembagaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_lembaga);
        getSupportActionBar().hide(); // hide the title bar
        BottomNavigationView navView = findViewById(R.id.nav_view_lembaga);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        /*AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home_lembaga, R.id.navigation_list_lembaga, R.id.navigation_profile_lembaga)
                .build();*/
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_lembaga);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    public void goToAlokasiZakat(View view) {
        Intent intent = new Intent(this, AlokasiZakatMenu.class);
        startActivity(intent);
    }
}
