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
    TextView total1;
    long totalPrice = 0;
    TextView checkout;
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
                    order = findViewById(R.id.order);
                    totalprice = findViewById(R.id.total_price1);
                    totalprice.setText(String.valueOf(totalPrice)+ "000 đ");
                    total1 = findViewById(R.id.total);
                    total1.setText(String.valueOf(totalPrice)+ "000 đ");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        checkout = findViewById(R.id.order);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef = FirebaseDatabase.getInstance().getReference("order");
                mRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                            dataSnapshot.getRef().removeValue();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                Intent intent = new Intent(CartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
