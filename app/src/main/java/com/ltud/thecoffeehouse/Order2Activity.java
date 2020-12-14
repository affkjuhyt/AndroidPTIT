package com.ltud.thecoffeehouse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Order2Activity extends AppCompatActivity {
    Button minus, plus, total;
    TextView amount, price;
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
        setContentView(R.layout.activity_order2);

        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
        amount = findViewById(R.id.amount);
        total = findViewById(R.id.total);
        price =  findViewById(R.id.price);

        count = 1;
        gia = 30;
        tong = gia;
        amount.setText(String.valueOf(count));
        price.setText(String.valueOf(gia)+".000 đ");
        total.setText(String.valueOf(tong)+".000 đ");

        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Order2Activity.this, CartActivity.class);
                startActivity(intent);
                finish();
            }
        });

        minus.setOnClickListener(clickListener);
        plus.setOnClickListener(clickListener);
        InitCounter();
    }

    private void InitCounter() {
        count = 0;
        amount.setText(count + "");
    }

    private void plusCounter() {
        count++;
        tong = (int) (count *gia);
        amount.setText(count + "");
        total.setText(String.valueOf(tong) + ".000 đ");
    }

    private void minusCounter() {
        if(count>1){
            count--;
            tong = (int) (count *gia);
            amount.setText(count + "");
            total.setText(String.valueOf(tong) + ".000 đ");
        }
        else if(count<=1){
            Toast.makeText(Order2Activity.this, "Xin lỗi không thể chọn ít hơn 1 sản phẩm", Toast.LENGTH_SHORT).show();
            amount.setText("1");
        }
    }
}
