package com.ltud.thecoffeehouse.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ltud.thecoffeehouse.Model.Order;
import com.ltud.thecoffeehouse.Model.OrderItems;
import com.ltud.thecoffeehouse.OrderActivity;
import com.ltud.thecoffeehouse.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class GridviewAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<OrderItems> orderItems;
    Order order;
    long tong;
    long count = 1;
    long gia;
    long id;
    long cartItemCount = 0;
    DatabaseReference mRef;
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

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_order2);
        Button minus = dialog.findViewById(R.id.minus);
        Button plus = dialog.findViewById(R.id.plus);
        TextView amount = dialog.findViewById(R.id.amount);
        TextView total = dialog.findViewById(R.id.total);
        items = orderItems.get(position);
        Glide.with(context).load(items.getImage()).into(itemImgView);
        itemTitle.setText(items.getName());
        itemPrice.setText(String.valueOf(items.getPrice()) + ".000 đ");
        gia = items.getPrice();
        order = new Order();
        ImageButton imageButton = convertView.findViewById(R.id.orderAddButton);
        imageButton.setTag(position);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tag = (Integer) imageButton.getTag();
                if(String.valueOf(position) != null){
                Window window = dialog.getWindow();
                if(window == null){
                    return;
                }
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                WindowManager.LayoutParams windowAttributes = window.getAttributes();
//        windowAttributes.gravity = ;
                window.setAttributes(windowAttributes);
                    dialog.setCancelable(true);

                    ImageView productImg = dialog.findViewById(R.id.product);
                    TextView description = dialog.findViewById(R.id.des_product);
                    TextView productName = dialog.findViewById(R.id.name_product);
                    TextView productPrice = dialog.findViewById(R.id.price);
                    Glide.with(context).load(orderItems.get(tag).getImage()).into(productImg);
                    description.setText(orderItems.get(tag).getDescription());
                    productName.setText(orderItems.get(tag).getName());
                    productPrice.setText(String.valueOf(orderItems.get(tag).getPrice())+".000 đ");
                    total.setText(String.valueOf(orderItems.get(tag).getPrice())+".000 đ");
                    dialog.show();
                    mRef = FirebaseDatabase.getInstance().getReference().child("order");
                    plus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count++;
                            tong = (int) (count * gia);
                            amount.setText(count + "");
                            total.setText(String.valueOf(tong) + ".000 đ");
                            System.out.println(tong);
                        }
                    });
                    minus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(count>1){
                                count--;
                                tong = (int) (count * gia);
                                amount.setText(count + "");
                                total.setText(String.valueOf(tong) + ".000 đ");
                                System.out.println(tong);

                            }
                            else if(count <= 1){
                                amount.setText("1");
                            }
                        }
                    });
                    total.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            order.setOrderName(orderItems.get(tag).getName());
                            order.setTotal(tong);
                            order.setQuantity(count);
                            order.setId(orderItems.get(tag).getId());
                            mRef.push().setValue(order);
                            count = 1;
                            amount.setText(String.valueOf(count));
                            dialog.dismiss();
                        }
                    });
                }
            }
        });
        return convertView;
    }

}
