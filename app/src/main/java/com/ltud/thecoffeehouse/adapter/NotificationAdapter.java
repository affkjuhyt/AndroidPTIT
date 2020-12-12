package com.ltud.thecoffeehouse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ltud.thecoffeehouse.R;
import com.ltud.thecoffeehouse.model.Notification;

import org.w3c.dom.Text;

import java.util.List;

public class NotificationAdapter extends BaseAdapter {

    Context context;
    List<Notification> notifications;

    public NotificationAdapter(Context context, List<Notification> notifications) {
        this.context = context;
        this.notifications = notifications;
    }

    @Override
    public int getCount() {
        return notifications.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = convertView;
        if(rowView == null) {
            rowView = layoutInflater.inflate(R.layout.row_noti, null, true);
        }

        ImageView notiImage = rowView.findViewById(R.id.imgNoti);
        TextView notiName = rowView.findViewById(R.id.txtTitle);
        TextView notiShortTitle = rowView.findViewById(R.id.shortTitle);
        TextView notiDate = rowView.findViewById(R.id.dateNoti);

        Notification itm = notifications.get(position);

        return rowView;
    }
}
