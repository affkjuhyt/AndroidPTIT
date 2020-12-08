package com.ltud.thecoffeehouse;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationBar;
    FrameLayout mainFrame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDefaultFragment();
        bottomNavigationBar = findViewById(R.id.bottom_nav_bar);
        mainFrame = findViewById(R.id.bottom_wrapper);
        bottomNavigationBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.news:
                        selectedFragment = new NewsFragment();
                        break;
                    case R.id.order:
                        selectedFragment = new OrderFragment();
                        break;
                    case R.id.store:
                        selectedFragment = new MapsFragment();
                        break;
                    case R.id.user:
                        selectedFragment = new UserFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.bottom_wrapper,selectedFragment).commit();
                return true;
            }
        });
    }
    private void setDefaultFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.bottom_wrapper, new NewsFragment()).commit();
    }

}