package com.ltud.thecoffeehouse.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ltud.thecoffeehouse.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends BaseAdapter {
    Context context;
    List<CartModel> datas;

    public CartAdapter(Context context, List<CartModel> datas) {
        this.context = context;
        this.datas = datas;
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = convertView;
        if(rowView == null){
            rowView = layoutInflater.inflate(R.layout.activity_order, null, true);
        }
        ImageView imgproduct = rowView.findViewById(R.id.img_product);
        TextView nameproduct = rowView.findViewById(R.id.name_product);
        TextView price = rowView.findViewById(R.id.price);

        CartModel itm = datas.get(position);

        Picasso.with(context)
                .load(itm.getImg_product())
                .resize(1000, 1000)
                .centerCrop()
                .into(imgproduct);

        nameproduct.setText(itm.getName_product());
        price.setText(itm.getPrice());

        return rowView;
    }
}
