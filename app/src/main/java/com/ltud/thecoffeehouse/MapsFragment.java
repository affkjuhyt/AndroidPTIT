package com.ltud.thecoffeehouse;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
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
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsFragment extends Fragment {

    private Spinner spnLocation;
    private GoogleMap googleMap1, googleMap2, googleMap3, googleMap4;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng sydney = new LatLng(-34, 151);
            LatLng hadong = new LatLng(20.9791757,105.785067717);
            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            googleMap.addMarker(new MarkerOptions().position(hadong).title("PTIT University"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hadong, 15f));
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

        spnLocation = (Spinner) view.findViewById(R.id.spnLocation);

        final ArrayList<String> location = new ArrayList<String>();
        location.add("Chọn khu vực");
        location.add("Ninh Kiều");
        location.add("Thành phố Biên Hòa");
        location.add("Quận Hải Châu");
        location.add("Thành phố Vinh");
        location.add("Quận Lê Chân");

        ArrayAdapter locationAdapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_item, location);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnLocation.setAdapter(locationAdapter);

        spnLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getSelectedItem().toString();

                if (item.equals("Ninh Kiều")) {
                    googleMap1.addMarker(new MarkerOptions().position(new LatLng(10.0345357, 105.7543214)).title("Ninh Kieu"));
                    googleMap1.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(10.0345357, 105.7543214), 16));
                }
                else if (item.equals("Quận Lê Chân")) {
                    googleMap2.addMarker(new MarkerOptions().position(new LatLng(10.0345357,105.7543214)).title("Quan Le Chan"));
                    googleMap2.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(10.0345357, 105.7543214), 16));
                }
                else if (item.equals("Thành phố Biên Hòa")) {
                    googleMap3.addMarker(new MarkerOptions().position(new LatLng(10.0345357,105.7543214)).title("Thanh pho Bien Hoa"));
                    googleMap3.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(10.0345357, 105.7543214), 16));
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(), "Vui long chon dung dia diem", Toast.LENGTH_LONG).show();
            }
        });
    }
}