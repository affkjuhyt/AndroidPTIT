package com.ltud.thecoffeehouse;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.ltud.thecoffeehouse.Adapter.OrderAdapter;
import com.ltud.thecoffeehouse.Model.OrderItems;
import com.ltud.thecoffeehouse.OrderSubFragments.DrinkFragment;
import com.ltud.thecoffeehouse.OrderSubFragments.FoodFragment;
import com.ltud.thecoffeehouse.OrderSubFragments.PopularFragment;

import java.util.ArrayList;


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

    public void setUpViewPager(ViewPager viewPager) {
        OrderAdapter adapter = new OrderAdapter(getChildFragmentManager());
        adapter.addFragment(new PopularFragment(), "Phổ biến");
        adapter.addFragment(new DrinkFragment(), "Đồ uống");
        adapter.addFragment(new FoodFragment(), "Đồ ăn");
        viewPager.setAdapter(adapter);
//        Fragment currentFragment = getFragmentManager().findFragmentByTag("Phổ biến");
//        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//        fragmentTransaction.detach(currentFragment);
//        fragmentTransaction.attach(currentFragment);
//        fragmentTransaction.commit();
    }
}

