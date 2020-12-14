package com.ltud.thecoffeehouse;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PopularFragment extends Fragment {
    Button btn;
    TextView gia, tong;
    int price, count, finalprice;
    public PopularFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.order_popular_fragment, container,false);
        count = 1;
        price = 50;
        finalprice = price;
        btn = v.findViewById(R.id.btn);
        gia = v.findViewById(R.id.gia);
        tong = v.findViewById(R.id.tong);
        tong.setText(String.valueOf(price));

        gia.setText(String.valueOf(price));
        gia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplication(), OrderActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
}