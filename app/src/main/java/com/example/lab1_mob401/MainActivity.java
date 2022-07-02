package com.example.lab1_mob401;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.lab1_mob401.Fragment.Fragment_Bai1;
import com.example.lab1_mob401.Fragment.Fragment_Bai2;
import com.example.lab1_mob401.Fragment.Fragment_Bai3;
import com.example.lab1_mob401.Fragment.Fragment_Bai4;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initId();

        initBottomNav();
    }

    private void initBottomNav() {
        this.bottomNavigationView.setOnNavigationItemSelectedListener(this);
        this.bottomNavigationView.getMenu().findItem(R.id.nav_bai1).setChecked(true);
        loadFragment(new Fragment_Bai1());
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_view_tag, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void initId() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }

    private void positionFragment(int item) {
        bottomNavigationView.getMenu().findItem(item).setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_bai1:
                loadFragment(new Fragment_Bai1());
                positionFragment(R.id.nav_bai1);
                break;
            case R.id.nav_bai2:
                loadFragment(new Fragment_Bai2());
                positionFragment(R.id.nav_bai2);
                break;
            case R.id.nav_bai3:
                loadFragment(new Fragment_Bai3());
                positionFragment(R.id.nav_bai3);
                break;
            case R.id.nav_bai4:
                loadFragment(new Fragment_Bai4());
                positionFragment(R.id.nav_bai4);
                break;
        }
        return true;
    }
}