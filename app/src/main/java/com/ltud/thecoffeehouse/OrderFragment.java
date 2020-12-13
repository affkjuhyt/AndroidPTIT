package com.ltud.thecoffeehouse;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ltud.thecoffeehouse.Adapter.GridviewAdapter;
import com.ltud.thecoffeehouse.Adapter.OrderAdapter;
import com.ltud.thecoffeehouse.Model.OrderItems;
import com.ltud.thecoffeehouse.OrderSubFragments.DrinkFragment;
import com.ltud.thecoffeehouse.OrderSubFragments.FoodFragment;
import com.ltud.thecoffeehouse.OrderSubFragments.PopularFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class OrderFragment extends Fragment {
    View view;
    GridView drinkGridView;
    ViewPager viewPager;
    TabLayout tabLayout;
    DatabaseReference mRef;
    ArrayList<OrderItems> orderPopularItems;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order, container, false);
        orderPopularItems = new ArrayList<>();
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);
//        drinkGridView = view.findViewById(R.id.drinkGridView);
//        mRef = FirebaseDatabase.getInstance().getReference();
//            Query query = mRef.child("product").orderByChild("group_name").equalTo("Phổ biến");
//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                orderPopularItems.clear();
//                    for (DataSnapshot childDataSnapshot : snapshot.getChildren()) {
//                        OrderItems items = childDataSnapshot.getValue(OrderItems.class);
//                        orderPopularItems.add(items);
//                    }
//                    GridviewAdapter gridviewAdapter = new GridviewAdapter(getActivity(),orderPopularItems);
//                    drinkGridView.setAdapter(gridviewAdapter);
//                }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    public void setUpViewPager(ViewPager viewPager){
        OrderAdapter adapter = new OrderAdapter(getChildFragmentManager());
        adapter.addFragment(new PopularFragment(), "Phổ biến");
        adapter.addFragment(new DrinkFragment(), "Đồ uống");
        adapter.addFragment(new FoodFragment(), "Đồ ăn");
        viewPager.setAdapter(adapter);
    }
}