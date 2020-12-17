package com.ltud.thecoffeehouse.OrderSubFragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ltud.thecoffeehouse.Adapter.GridviewAdapter;
import com.ltud.thecoffeehouse.Model.OrderItems;
import com.ltud.thecoffeehouse.R;

import java.util.ArrayList;

public class PopularFragment extends Fragment {
    GridView popularGridView;
    GridviewAdapter gridviewAdapter;
    DatabaseReference mRef;
    ArrayList<OrderItems> orderPopularItems;
    public PopularFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_popular_fragment, container,false);
        popularGridView = (GridView) view.findViewById(R.id.popularGridView);
        popularGridView = view.findViewById(R.id.popularGridView);
        orderPopularItems = new ArrayList<>();
        mRef = FirebaseDatabase.getInstance().getReference();
        Query query= mRef.child("product").orderByChild("group_name").equalTo("Phổ biến");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                orderPopularItems.clear();
                for (DataSnapshot childDataSnapshot : snapshot.getChildren()) {
                    OrderItems items = childDataSnapshot.getValue(OrderItems.class);
                    orderPopularItems.add(items);
                }
                GridviewAdapter gridviewAdapter = new GridviewAdapter(getActivity(),orderPopularItems);
                popularGridView.setAdapter(gridviewAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        gridviewAdapter = new GridviewAdapter(getContext(), orderPopularItems);
        popularGridView.setAdapter(gridviewAdapter);
        return view;
    }
}