package com.ltud.thecoffeehouse;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Order2Activity extends AppCompatActivity {
    Button minus, plus, total;
    TextView amount, price;
    int count;
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
        amount.setText(count + "");
    }

    private void minusCounter() {
        if(count>0){
            count--;
            amount.setText(count + "");
        }
        else if(count<0){
            amount.setText(0);
        }
    }
}
