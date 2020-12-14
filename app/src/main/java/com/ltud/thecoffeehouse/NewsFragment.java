package com.ltud.thecoffeehouse;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ltud.thecoffeehouse.Adapter.GridviewAdapter;
import com.ltud.thecoffeehouse.Model.OrderItems;
import com.ltud.thecoffeehouse.category_news.CategoryNews;
//import com.ltud.thecoffeehouse.category_news.CategoryNewsAdapter;
import com.ltud.thecoffeehouse.model.Notification;
import com.ltud.thecoffeehouse.news.News;
import com.ltud.thecoffeehouse.news.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {

    private RecyclerView rcvCategory;
//    private CategoryNewsAdapter categoryNewsAdapter;
    private Context mContext;
    RecyclerView discountNews;
    RecyclerView updateNews;
    RecyclerView newNews;
    ArrayList<News> discountItems;
    ArrayList<News> updateItems;
    ArrayList<News> newsItems;
    DatabaseReference mRef1;
    DatabaseReference mRef2;
    DatabaseReference mRef3;
    private ImageView btnNoti, imgUserLogout, imgUserLogin;
    private TextView txtDangNhap, txtKhachMoi, textdashboard;
    private FirebaseAuth mAuth;
    private String imgUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
//        rcvCategory = view.findViewById(R.id.rcv_category);
//        categoryNewsAdapter = new CategoryNewsAdapter(mContext);
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext,RecyclerView.VERTICAL,false);
//        rcvCategory.setLayoutManager(linearLayoutManager);
//
//        categoryNewsAdapter.setData(getListCategory());
//        rcvCategory.setAdapter(categoryNewsAdapter);
        discountNews = (RecyclerView) view.findViewById(R.id.discountView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext,RecyclerView.HORIZONTAL,false);
        discountNews.setLayoutManager(linearLayoutManager);
        discountNews = view.findViewById(R.id.discountView);
        discountItems = new ArrayList<>();

        mRef1 = FirebaseDatabase.getInstance().getReference();
        Query query = mRef1.child("news").orderByChild("role").equalTo("Ưu đãi đặc biệt");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                discountItems.clear();
                for (DataSnapshot childDataSnapshot : snapshot.getChildren()) {
                    News items = childDataSnapshot.getValue(News.class);
                    discountItems.add(items);
                }
                NewsAdapter newsAdapter = new NewsAdapter(getActivity(),discountItems);
                discountNews.setAdapter(newsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(mContext,RecyclerView.HORIZONTAL,false);
        updateNews = (RecyclerView) view.findViewById(R.id.updateView);
        updateNews.setLayoutManager(linearLayoutManager1);
        updateNews = view.findViewById(R.id.updateView);
        updateItems = new ArrayList<>();

        mRef1 = FirebaseDatabase.getInstance().getReference();
        query = mRef1.child("news").orderByChild("role").equalTo("Cập nhật từ nhà");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                updateItems.clear();
                for (DataSnapshot childDataSnapshot : snapshot.getChildren()) {
                    News items2 = childDataSnapshot.getValue(News.class);
                    updateItems.add(items2);
                }
                NewsAdapter newsAdapter1 = new NewsAdapter(getActivity(),updateItems);
                updateNews.setAdapter(newsAdapter1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(mContext,RecyclerView.HORIZONTAL,false);
        newNews = (RecyclerView) view.findViewById(R.id.newsView);
        newNews.setLayoutManager(linearLayoutManager2);
        newNews = view.findViewById(R.id.newsView);
        newsItems = new ArrayList<>();

        mRef1 = FirebaseDatabase.getInstance().getReference();
        query = mRef1.child("news").orderByChild("role").equalTo("#CoffeeLover");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                newsItems.clear();
                for (DataSnapshot childDataSnapshot : snapshot.getChildren()) {
                    News items2 = childDataSnapshot.getValue(News.class);
                    newsItems.add(items2);
                }
                NewsAdapter newsAdapter2 = new NewsAdapter(getActivity(),newsItems);
                newNews.setAdapter(newsAdapter2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        // Visible text view
        imgUserLogin = view.findViewById(R.id.imgUserLogin);
        imgUserLogout = view.findViewById(R.id.imgUserLogout);
        txtDangNhap = view.findViewById(R.id.txtDangNhap);
        txtKhachMoi = view.findViewById(R.id.txtKhachMoi);
        textdashboard = view.findViewById(R.id.textdashboard);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            imgUserLogout.setVisibility(View.GONE);
            txtDangNhap.setVisibility(View.GONE);
        } else {
            imgUserLogin.setVisibility(View.GONE);
            txtKhachMoi.setVisibility(View.GONE);
            textdashboard.setVisibility(View.GONE);
        }

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(getActivity().getApplicationContext());
        if(signInAccount != null) {
            textdashboard.setText(signInAccount.getDisplayName());
            imgUrl = (signInAccount.getPhotoUrl()).toString();

            Glide.with(this)
                    .load(imgUrl)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(imgUserLogin);
        }

        btnNoti = view.findViewById(R.id.imgIconNoti);
        btnNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), NotificationActivity.class);
                startActivity(i);
            }
        });

        textdashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InfomationActivity.class);
                startActivity(intent);
            }
        });

        txtDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SignInActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

//    private List<CategoryNews> getListCategory(){
//        List<CategoryNews> listCategory = new ArrayList<>();
//
//        List<News> listNews = new ArrayList<>();
//        listNews.add(new News(R.drawable.avatar,"Book 1"));
//        listNews.add(new News(R.drawable.avatar,"Book 2"));
//        listNews.add(new News(R.drawable.avatar,"Book 3"));
//        listNews.add(new News(R.drawable.avatar,"Book 4"));
//
//        listCategory.add(new CategoryNews("Category 1",listNews));
//        listCategory.add(new CategoryNews("Category 2",listNews));
//        listCategory.add(new CategoryNews("Category 3",listNews));
//
//        return listCategory;
//    }
}
