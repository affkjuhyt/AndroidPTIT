package com.ltud.thecoffeehouse;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ltud.thecoffeehouse.Adapter.CartAdapter;
import com.ltud.thecoffeehouse.Model.Cart;
import com.ltud.thecoffeehouse.OrderSubFragments.PopularFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
public class CartActivity extends AppCompatActivity {
    TextView order, shipfee, cost, totalprice, priceproduct, sizeprice, size;
    int fee, price, total;
    private Dialog dialog;
    ImageView cancel;
    DatabaseReference mRef;
    ArrayList<Cart> cartItems;
    ListView cartView;
    long totalPrice = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        cartItems = new ArrayList<>();
        cartView = findViewById(R.id.list);

        mRef = FirebaseDatabase.getInstance().getReference("order");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cartItems.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Cart cart = dataSnapshot.getValue(Cart.class);
                    cartItems.add(cart);
                }
                CartAdapter cartAdapter = new CartAdapter(CartActivity.this,cartItems);
                cartView.setAdapter(cartAdapter);
                for(int i = 0 ;i < cartItems.size(); i++){
                    totalPrice = totalPrice + cartItems.get(i).getTotal();
                }
                order = findViewById(R.id.order);
                totalprice = findViewById(R.id.total_price);
                totalprice.setText(String.valueOf(totalPrice));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        priceproduct = findViewById(R.id.price_product);
//        sizeprice = findViewById(R.id.sizeprice);
//        size = findViewById(R.id.size);
//
//        order.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDialog();
//            }
//        });
//
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(CartActivity.this, PopularFragment.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//    }
//
//    public void showDialog(){
//        dialog = new Dialog(CartActivity.this);
//        dialog.setContentView(R.layout.dialogcart);
//        final Button ok = (Button) dialog.findViewById(R.id.ok);
//        final Button home = (Button) dialog.findViewById(R.id.home);
//        dialog.show();
//        ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(CartActivity.this, CartActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(CartActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//    }
    }
}
