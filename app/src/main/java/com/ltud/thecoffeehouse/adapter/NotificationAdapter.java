package com.ltud.thecoffeehouse.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.ltud.thecoffeehouse.R;
import com.ltud.thecoffeehouse.model.Notification;

import org.w3c.dom.Text;

import java.util.List;

public class NotificationAdapter extends ArrayAdapter {

    Activity mContext;
    String url;
    List<Notification> notifications;

    public NotificationAdapter(Activity mContext, List<Notification> notifications) {
        super(mContext, R.layout.row_noti, notifications);
        this.mContext = mContext;
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemNotification = inflater.inflate(R.layout.row_noti, null, true);

        ImageView imgNoti = listItemNotification.findViewById(R.id.imgNoti);
        TextView txtTitle = listItemNotification.findViewById(R.id.txtTitle);
        TextView shortTitle = listItemNotification.findViewById(R.id.shortTitle);
        TextView dateNoti = listItemNotification.findViewById(R.id.dateNoti);

        Notification notification = notifications.get(position);

        Glide.with(mContext).load(notification.getImage()).into(imgNoti);
        txtTitle.setText(notification.getTitle());
        shortTitle.setText("" + notification.getShort_title());
        dateNoti.setText("" + notification.getDate_time());
        url = notification.getUrl();

        Button btnReadmore = listItemNotification.findViewById(R.id.btnReadmore);
        btnReadmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                mContext.startActivity(intent);
            }
        });

        return listItemNotification;
    }
}
