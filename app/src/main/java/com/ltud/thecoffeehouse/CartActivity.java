package com.ltud.thecoffeehouse;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.ltud.thecoffeehouse.OrderSubFragments.PopularFragment;
import androidx.appcompat.app.AppCompatActivity;

public class CartActivity extends AppCompatActivity {
    TextView order, shipfee, cost, totalprice, priceproduct, sizeprice, size;
    int fee, price, total;
    private Dialog dialog;
    ImageView cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        order = findViewById(R.id.order);
        shipfee = findViewById(R.id.shipfee);
        cost = findViewById(R.id.cost);
        totalprice =  findViewById(R.id.total_price);
        priceproduct = findViewById(R.id.price_product);
        sizeprice = findViewById(R.id.sizeprice);
        size = findViewById(R.id.size);
        cancel = findViewById(R.id.cancel);

        fee = 10;
        price = 30;
        total = price;

        sizeprice.setText(String.valueOf(price)+".000 đ");
        priceproduct.setText(String.valueOf(price)+".000 đ");
        shipfee.setText(String.valueOf(fee) + ".000 đ");
        cost.setText(String.valueOf(price)+".000 đ");
        total = fee + price;
        totalprice.setText(String.valueOf(total)+".000 đ");

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, PopularFragment.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void showDialog(){
        dialog = new Dialog(CartActivity.this);
        dialog.setContentView(R.layout.dialogcart);
        final Button ok = (Button) dialog.findViewById(R.id.ok);
        final Button home = (Button) dialog.findViewById(R.id.home);
        dialog.show();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, CartActivity.class);
                startActivity(intent);
                finish();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
