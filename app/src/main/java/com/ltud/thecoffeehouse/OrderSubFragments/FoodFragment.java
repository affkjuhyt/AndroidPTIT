package com.ltud.thecoffeehouse.OrderSubFragments;


import android.content.Context;
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

public class FoodFragment extends Fragment {
    GridView foodGridView;
    GridviewAdapter gridviewAdapter;
    ArrayList<OrderItems> orderItems;
    DatabaseReference mRef;
    ArrayList<OrderItems> orderFoodItems;
    public FoodFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_food_fragment, container,false);
        foodGridView = (GridView) view.findViewById(R.id.foodGridView);
        foodGridView = view.findViewById(R.id.foodGridView);
        orderFoodItems = new ArrayList<>();
        mRef = FirebaseDatabase.getInstance().getReference();
        Query query= mRef.child("product").orderByChild("group_name").equalTo("Đồ ăn");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                orderFoodItems.clear();
                for (DataSnapshot childDataSnapshot : snapshot.getChildren()) {
                    OrderItems items = childDataSnapshot.getValue(OrderItems.class);
                    orderFoodItems.add(items);
                }
                GridviewAdapter gridviewAdapter = new GridviewAdapter(getActivity(),orderFoodItems);
                foodGridView.setAdapter(gridviewAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        gridviewAdapter = new GridviewAdapter(getContext(), orderFoodItems);
        foodGridView.setAdapter(gridviewAdapter);
        return view;
    }
}