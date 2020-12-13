package com.ltud.thecoffeehouse.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ltud.thecoffeehouse.Model.OrderItems;
import com.ltud.thecoffeehouse.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class GridviewAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<OrderItems> orderItems;
    OrderItems items;
    public GridviewAdapter(Context c, ArrayList<OrderItems> orderItems) {
        this.context = c;
        this.orderItems = orderItems;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        if(orderItems == null) return 0;
        return orderItems.size();
    }

    @Override
    public Object getItem(int position) {
        return orderItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(layoutInflater==null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null){
            convertView = layoutInflater.inflate(R.layout.order_gridview_items,null);
        }
        ImageView itemImgView = convertView.findViewById(R.id.gridItemImg);
        TextView itemTitle = convertView.findViewById(R.id.orderTitle);
        TextView itemPrice = convertView.findViewById(R.id.orderPrice);
        items = orderItems.get(position);
        Glide.with(context).load(items.getImage()).into(itemImgView);
        itemTitle.setText(items.getName());
        itemPrice.setText(items.getPrice() + ".000 đ");
        return convertView;
    }
}
