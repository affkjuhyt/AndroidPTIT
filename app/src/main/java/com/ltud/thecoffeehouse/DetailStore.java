package com.ltud.thecoffeehouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class DetailStore extends AppCompatActivity {
    private TextView txtAddress, txtAddressDetail;
    ArrayList<LatLng> lat_lng_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_store);

        txtAddress = findViewById(R.id.txtAddress);
        txtAddressDetail = findViewById(R.id.txtAddressDetail);


        System.out.println(lat_lng_list.size());
    }
}