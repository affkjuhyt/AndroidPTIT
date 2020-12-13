package com.ltud.thecoffeehouse;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsFragment extends Fragment {

    private Spinner spnLocation;
    private GoogleMap mMap;
    private LocationManager locationManager;
    private String provider;

    ArrayList<LatLng> arrayList = new ArrayList<LatLng>();
    LatLng coffee1 = new LatLng(21.0479885, 105.790680218);
    LatLng coffee2 = new LatLng(21.0479524, 105.795407118);
    LatLng coffee3 = new LatLng(21.0479524,    105.795407118);

    ArrayList<String> title = new ArrayList<String>();



    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            for(int i = 0; i < arrayList.size(); i++) {
                for (int j =0; j<title.size(); j++) {
                    System.out.println(i);
                    mMap.addMarker(new MarkerOptions().position(arrayList.get(i)).title(String.valueOf(title.get(j))).icon(BitmapDescriptorFactory.fromResource(R.drawable.the_cofee)));
                }
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(arrayList.get(i), 15f));
            }

            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    String markertitle = marker.getTitle();

                    Intent i = new Intent(getActivity().getApplicationContext(), DetailStore.class);
                    i.putExtra("title", markertitle);
                    startActivity(i);
                    return false;
                }
            });

            UiSettings uiSettings = googleMap.getUiSettings();
            uiSettings.setCompassEnabled(true);
            uiSettings.setZoomControlsEnabled(true);
            uiSettings.setMyLocationButtonEnabled(true);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }

        // Array marker
        arrayList.add(coffee1);
        arrayList.add(coffee2);
        arrayList.add(coffee3);

        title.add("The coffe Nguyen Van Tuan");
        title.add("The coffe Nguyen Van Loc");
        title.add("The coffe Truong Tring");

        spnLocation = (Spinner) view.findViewById(R.id.spnLocation);

        final ArrayList<String> location_list = new ArrayList<String>();
        location_list.add("Chọn thể loại hiển thị");
        location_list.add("Bản đồ tự nhiên");
        location_list.add("Bản đồ đô thị");
        location_list.add("Bản đồ hành chính");
        location_list.add("Bản đồ vinh");

        ArrayAdapter locationAdapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_item, location_list);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnLocation.setAdapter(locationAdapter);

        spnLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: // Hybrid
                        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        break;
                    case 1: // None
                        mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                        break;
                    case 2: // Normal
                        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        break;
                    case 3: // Statellite
                        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                        break;
                    default:
                        mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                        break;
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(), "Vui long chon dung dia diem", Toast.LENGTH_LONG).show();
            }
        });
    }
}