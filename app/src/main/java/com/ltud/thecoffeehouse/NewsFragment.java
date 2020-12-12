package com.ltud.thecoffeehouse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.ltud.thecoffeehouse.category_news.CategoryNews;
import com.ltud.thecoffeehouse.category_news.CategoryNewsAdapter;
import com.ltud.thecoffeehouse.model.Notification;
import com.ltud.thecoffeehouse.news.News;

public class NewsFragment extends Fragment {

    private RecyclerView rcvCategory;
    private CategoryNewsAdapter categoryNewsAdapter;
    private Context mContext;
    private TextView btnNoti;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        rcvCategory = view.findViewById(R.id.rcv_category);
        categoryNewsAdapter = new CategoryNewsAdapter(mContext);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext,RecyclerView.VERTICAL,false);
        rcvCategory.setLayoutManager(linearLayoutManager);

        categoryNewsAdapter.setData(getListCategory());
        rcvCategory.setAdapter(categoryNewsAdapter);

        btnNoti = view.findViewById(R.id.txtIcon);
        btnNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), NotificationActivity.class);
                startActivity(i);
            }
        });

        return view;
    }

    private List<CategoryNews> getListCategory(){
        List<CategoryNews> listCategory = new ArrayList<>();

        List<News> listNews = new ArrayList<>();
        listNews.add(new News(R.drawable.avatar,"Book 1"));
        listNews.add(new News(R.drawable.avatar,"Book 2"));
        listNews.add(new News(R.drawable.avatar,"Book 3"));
        listNews.add(new News(R.drawable.avatar,"Book 4"));

        listCategory.add(new CategoryNews("Category 1",listNews));
        listCategory.add(new CategoryNews("Category 2",listNews));
        listCategory.add(new CategoryNews("Category 3",listNews));

        return listCategory;
    }
}
