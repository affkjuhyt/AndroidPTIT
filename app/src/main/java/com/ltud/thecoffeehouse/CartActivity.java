package com.ltud.thecoffeehouse;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CartActivity extends AppCompatActivity {
    TextView order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        order = findViewById(R.id.order);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
    }
    public void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Ban da dat hang thanh cong");
        builder.setCancelable(false);
        builder.setPositiveButton("Quay lai", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Intent intent = new Intent(CartActivity.this, CartActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("Ve trang chu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(CartActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
