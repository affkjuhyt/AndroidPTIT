package com.ltud.thecoffeehouse;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ltud.thecoffeehouse.Adapter.CartModel;

import java.util.List;

public class OrderActivity extends AppCompatActivity {

    private List<CartModel> data;
//    CartAdapter cartAdapter;
    Button minus, plus, total;
    TextView amount, price;
    RadioGroup radioGroup;
    RadioButton radioButton;
    int count, gia, tong;
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.minus:
                    minusCounter();
                    break;
                case R.id.plus:
                    plusCounter();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
        amount = findViewById(R.id.amount);
        total = findViewById(R.id.total);
        price =  findViewById(R.id.price);
        radioGroup = findViewById(R.id.radioGroup);
        count = 1;
        gia = 30;
        tong = gia;
        amount.setText(String.valueOf(count));
        price.setText(String.valueOf(gia)+".000 đ");
        total.setText(String.valueOf(tong)+".000 đ");
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.checkSize1:
                        break;
                    case R.id.checkSize2:
                        tong = (tong +3)*count;
                        break;
                    case R.id.checkSize3:
                        tong = (tong +6)*count;
                        break;
                }
            }
        });

        minus.setOnClickListener(clickListener);
        plus.setOnClickListener(clickListener);
        InitCounter();
    }

    private void InitCounter() {
        count = 1;
        amount.setText(count + "");
    }

    private void plusCounter() {
        count++;
        tong = count * gia;
        amount.setText(count + "");
        total.setText(String.valueOf(tong) + ".000 đ");
    }

    private void minusCounter() {
        if(count>0){
            count--;
            tong = count * gia;
            amount.setText(count + "");
            total.setText(String.valueOf(tong) + ".000 đ");
        }
        else if(count<=0){
            amount.setText(0);
        }
    }

//    data = new ArrayList<>();
//    cartAdapter = new CartAdapter(this, data);
//
//    String url = "https://fakestoreapi.com/products";
//    RequestQueue queue = Volley.newRequestQueue(this);
//    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//            new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    Type listType = new TypeToken<List<CartModel>>() {}.getType();
//                    List<CartModel> mylist = new Gson().fromJson(response, listType);
//                    data.clear();
//                    data.addAll(mylist);
//                    cartAdapter.notifyDataSetChanged();
//                }
//            },
//            new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Toast.makeText(OrderActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
//                }
//            });
//        queue.add(stringRequest);
}
