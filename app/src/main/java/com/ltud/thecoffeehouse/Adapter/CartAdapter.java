//package com.ltud.thecoffeehouse.Adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.ltud.thecoffeehouse.R;
//import com.squareup.picasso.Picasso;
//
//import java.text.DecimalFormat;
//import java.util.List;
//
//public class CartAdapter<CartModel> extends BaseAdapter {
//    Context context;
//    List<CartModel> datas;
//
//    public CartAdapter(Context context, List<CartModel> datas) {
//        this.context = context;
//        this.datas = datas;
//    }
//    @Override
//    public int getCount() {
//        return datas.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return datas.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    public class ViewHolder{
//        public TextView nameproduct, priceproduct;
//        public ImageView imgproduct;
//        public Button minus, plus, total;
//    }
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder = null;
//        if (convertView == null) {
//            viewHolder = new ViewHolder();
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.activity_order, null);
//            viewHolder.nameproduct = (TextView) convertView.findViewById(R.id.name_product);
//            viewHolder.priceproduct = (TextView) convertView.findViewById(R.id.price_product);
//            viewHolder.imgproduct = (ImageView) convertView.findViewById(R.id.img_product);
//            viewHolder.minus = (Button) convertView.findViewById(R.id.minus);
//            viewHolder.plus = (Button) convertView.findViewById(R.id.plus);
//            viewHolder.total = (Button) convertView.findViewById(R.id.total);
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//
//        CartModel cart = (CartModel) getItem(position);
//        viewHolder.nameproduct.setText(cart.getName_product());
//        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//        viewHolder.priceproduct.setText(decimalFormat.format(cart.getPrice()) + "đ");
//
//
//        Picasso.with(context)
//                .load(cart.getImg_product())
//                .resize(1000, 1000)
//                .centerCrop()
//                .into(imgproduct);
//        viewHolder.total.setText(cart.getTotal());
//
//        return convertView;
//    }
//}
