package com.ltud.thecoffeehouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.ltud.thecoffeehouse.OrderSubFragments.PopularFragment;

import java.util.ArrayList;

public class DetailStore extends AppCompatActivity {
    private TextView txtAddress, txtAddressDetail;
    ArrayList<LatLng> lat_lng_list;
    private ImageView imgCancelStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_store);

        txtAddress = findViewById(R.id.txtAddress);
        txtAddressDetail = findViewById(R.id.txtAddressDetail);
        imgCancelStore = findViewById(R.id.imgCancelStore);

        imgCancelStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailStore.this, MainActivity.class);
                startActivity(intent);
            }
        });


//        System.out.println(lat_lng_list.size());
    }
}