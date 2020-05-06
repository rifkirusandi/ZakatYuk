package com.si410702.java.zakatyuk;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.si410702.java.zakatyuk.adapter.AlokasiZakatAdapter;

public class AlokasiZakatMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alokasi_zakat_menu);

        setTitle("List Zakat");

        TabLayout tabLayout = findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText("Zakat Fitrah"));
        tabLayout.addTab(tabLayout.newTab().setText("Zakat Penghasilan"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager);
        final AlokasiZakatAdapter adapter = new AlokasiZakatAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);


        viewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

            @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }

            @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeLembagaActivity.class);
        finish();
        startActivity(intent);
    }
}
