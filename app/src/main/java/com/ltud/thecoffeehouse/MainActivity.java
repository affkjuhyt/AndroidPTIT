package com.ltud.thecoffeehouse;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationBar = findViewById(R.id.bottom_nav_bar);
        bottomNavigationBar.setOnNavigationItemReselectedListener(navListener);
    }
    private BottomNavigationView.OnNavigationItemReselectedListener navListener =
            new BottomNavigationView.OnNavigationItemReselectedListener() {
                @Override
                public void onNavigationItemReselected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()){
                        case R.id.news:
                            selectedFragment = new NewsFragment();
                            break;
                        case R.id.order:
                            selectedFragment = new OrderFragment();
                            break;
                        case R.id.store:
                            selectedFragment = new StoreFragment();
                            break;
                        case R.id.user:
                            selectedFragment = new UserFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.bottom_wrapper,selectedFragment).commit();
                }
            };
}