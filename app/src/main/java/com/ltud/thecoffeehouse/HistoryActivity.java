package com.ltud.thecoffeehouse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HistoryActivity extends AppCompatActivity {
    ImageView imgCancelHistory;
    ListView listOrder;
    RelativeLayout emptyOrder;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listOrder = (ListView) findViewById(R.id.listOrder);
        emptyOrder = findViewById(R.id.emptyOrder);

        imgCancelHistory = findViewById(R.id.imgCancelHistory);
        imgCancelHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null) {
            emptyOrder.setVisibility(View.GONE);
        } else {
            listOrder.setVisibility(View.GONE);
        }
    }
}
