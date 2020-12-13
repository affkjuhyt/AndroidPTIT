package com.ltud.thecoffeehouse;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationBar;
    FrameLayout mainFrame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
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

        FirebaseMessaging.getInstance().subscribeToTopic("weather")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Done";
                        if (!task.isSuccessful()) {
                            msg = "Fail";
                        }
                    }
                });
    }
    private void setDefaultFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.bottom_wrapper, new NewsFragment()).commit();
    }
}