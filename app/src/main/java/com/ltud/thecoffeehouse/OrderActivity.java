package com.ltud.thecoffeehouse;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ltud.thecoffeehouse.Adapter.CartModel;

import java.util.List;

public class OrderActivity extends AppCompatActivity {

    public static List<CartModel> data;
    Button minus, plus, total;
    TextView amount, price;
    RadioGroup radioGroup;
    int count, gia, tong;
    double SizePrice;
    ImageView detailImg;
    TextView detailName;
    TextView detailPrice;

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

//        data = new ArrayList<>();
//        final CartAdapter customAdapter = new CartAdapter(this, data);
//
//        Intent i = getIntent();
//        int position = i.getIntExtra("Position", -1);
//        detailName.setText(data.get(position).getName_product());
//        detailPrice.setText(data.get(position).getPrice());
//        Picasso.with(this)
//                .load(data.get(position).getImg_product())
//                .resize(1000, 1000)
//                .centerCrop()
//                .into(detailImg);

        InitCounter();

        minus.setOnClickListener(clickListener);
        plus.setOnClickListener(clickListener);

        radioGroup.setOnCheckedChangeListener(
            new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    int priceTong = 0;
                    System.out.println(tong);
                    System.out.println(priceTong);
                    int SPrice=0;
                    switch (radioGroup.getCheckedRadioButtonId()){
                        case R.id.checkSize1:
                            SPrice=0;
                            priceTong = tong + SPrice;
                            total.setText(String.valueOf(priceTong)+".000 đ");
                            break;
                        case R.id.checkSize2:
                            SPrice=3;
                            priceTong = tong + SPrice;
                            total.setText(String.valueOf(priceTong)+".000 đ");
                            break;
                        case R.id.checkSize3:
                            SPrice=6;
                            priceTong = tong + SPrice;
                            total.setText(String.valueOf(priceTong)+".000 đ");
                            break;
                    }
                    SizePrice = SPrice;
                }
            }
        );

        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this, CartActivity.class);
                startActivity(intent);
                finish();
            }
        });

        if(count <= 1) {
            minus.setBackgroundColor(Color.GRAY);
        } else {
            minus.setBackgroundColor(Color.RED);
        }
    }

    private void InitCounter() {
        count = 1;
        amount.setText(count + "");
    }

    private void plusCounter() {
        count++;
        tong = (int) (count * (gia + SizePrice));
        amount.setText(count + "");
        total.setText(String.valueOf(tong) + ".000 đ");
    }

    private void minusCounter() {
        if(count>1){
            count--;
            tong = (int) (count * (gia+SizePrice));
            amount.setText(count + "");
            total.setText(String.valueOf(tong) + ".000 đ");
        }
        else if(count <= 1){
            Toast.makeText(OrderActivity.this, "Xin lỗi không thể chọn ít hơn 1 sản phẩm", Toast.LENGTH_SHORT).show();
            amount.setText("1");
        }
    }
}
