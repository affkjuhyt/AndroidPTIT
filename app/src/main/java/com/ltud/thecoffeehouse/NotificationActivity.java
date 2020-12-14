package com.ltud.thecoffeehouse;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ltud.thecoffeehouse.adapter.NotificationAdapter;
import com.ltud.thecoffeehouse.model.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    ListView myListView;
    List<Notification> notificationList;
    private Button btnReadmore;

    DatabaseReference notificationDbRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_view);

        myListView = findViewById(R.id.noti_list);
        notificationList = new ArrayList<>();

        notificationDbRef = FirebaseDatabase.getInstance().getReference("notification");

        notificationDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                notificationList.clear();
                for (DataSnapshot notificationDatasnap : snapshot.getChildren()) {
                    Notification notifications = notificationDatasnap.getValue(Notification.class);
                    notificationList.add(notifications);
                }

                NotificationAdapter adapter = new NotificationAdapter(NotificationActivity.this, notificationList);
                myListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ImageView imageCancel = findViewById(R.id.imgCancelNoti);
        imageCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NotificationActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
