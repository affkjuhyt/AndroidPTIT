package com.ltud.thecoffeehouse.OrderSubFragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
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

public class DrinkFragment extends Fragment {
    Context context;
    GridView drinkGridView;
    GridviewAdapter gridviewAdapter;
    DatabaseReference mRef;
    ArrayList<OrderItems> orderDrinkItems;
    public DrinkFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_drink_fragment, container,false);
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_order2);
        drinkGridView = (GridView) view.findViewById(R.id.drinkGridView);
        drinkGridView = view.findViewById(R.id.drinkGridView);
        orderDrinkItems = new ArrayList<>();
        mRef = FirebaseDatabase.getInstance().getReference();
            Query query = mRef.child("product").orderByChild("group_name").equalTo("Đồ uống");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                orderDrinkItems.clear();
                    for (DataSnapshot childDataSnapshot : snapshot.getChildren()) {
                        OrderItems items = childDataSnapshot.getValue(OrderItems.class);
                        orderDrinkItems.add(items);
                    }
                    GridviewAdapter gridviewAdapter = new GridviewAdapter(getActivity(),orderDrinkItems);
                    drinkGridView.setAdapter(gridviewAdapter);
                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        gridviewAdapter = new GridviewAdapter(getContext(), orderDrinkItems);
        drinkGridView.setAdapter(gridviewAdapter);
        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}
