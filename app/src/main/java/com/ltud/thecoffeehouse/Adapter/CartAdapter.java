package com.ltud.thecoffeehouse.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ltud.thecoffeehouse.Model.Cart;
import com.ltud.thecoffeehouse.Model.OrderItems;
import com.ltud.thecoffeehouse.R;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends BaseAdapter {
    Context context;
    List<Cart> cartItems;
    private LayoutInflater layoutInflater;
    public CartAdapter(Context c, ArrayList<Cart> cartItems) {
        this.context = c;
        this.cartItems = cartItems;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return cartItems.size();
    }

    @Override
    public Object getItem(int position) {
        return cartItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = convertView;
        if(rowView == null){
            rowView = layoutInflater.inflate(R.layout.items_cart, null, true);
        }
        TextView name = rowView.findViewById(R.id.nameproduct);
        TextView price = rowView.findViewById(R.id.price);
        TextView quantity = rowView.findViewById(R.id.quantity);
        Cart itm = cartItems.get(position);
        name.setText("Tên sản phẩm: " + itm.getOrderName());
        price.setText(String.valueOf("Giá: " + itm.getTotal() + "000 đ"));
        quantity.setText(String.valueOf("Số lượng: "+itm.getQuantity()));


        return rowView;
    }
}
