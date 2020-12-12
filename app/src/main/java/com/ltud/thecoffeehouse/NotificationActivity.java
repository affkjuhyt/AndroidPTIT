package com.ltud.thecoffeehouse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ltud.thecoffeehouse.adapter.NotificationAdapter;
import com.ltud.thecoffeehouse.model.Notification;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private ListView listView;

    // Array list
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_notification_view);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView = (ListView) findViewById(R.id.noti_list);

        listView.setAdapter(adapter);

        ImageView imageCancel = findViewById(R.id.imgCancelNoti);

        imageCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NotificationActivity.this, NewsFragment.class);
                startActivity(i);
            }
        });
    }
}
