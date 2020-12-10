package com.ltud.thecoffeehouse;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import category_news.CategoryNews;
import category_news.CategoryNewsAdapter;
import news.News;

import android.widget.ListView;

public class NewsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView rcvCategory;
    private CategoryNewsAdapter categoryNewsAdapter;
    private Context mContext;
    public NewsFragment() {
        // Required empty public constructor
    }

    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvCategory = view.findViewById(R.id.rcv_category);
        categoryNewsAdapter = new CategoryNewsAdapter(mContext);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext,RecyclerView.VERTICAL,false);
        rcvCategory.setLayoutManager(linearLayoutManager);

        categoryNewsAdapter.setData(getListCategory());
        rcvCategory.setAdapter(categoryNewsAdapter);
    }

    private List<CategoryNews> getListCategory(){
        List<CategoryNews> listCategory = new ArrayList<>();

        List<News> listBook = new ArrayList<>();
        listBook.add(new News(R.drawable.avatar,"Book 1"));
        listBook.add(new News(R.drawable.avatar,"Book 2"));
        listBook.add(new News(R.drawable.avatar,"Book 3"));
        listBook.add(new News(R.drawable.avatar,"Book 4"));

        listCategory.add(new CategoryNews("Category 1",listBook));
        listCategory.add(new CategoryNews("Category 2",listBook));
        listCategory.add(new CategoryNews("Category 3",listBook));

        return listCategory;
    }
}
