package com.ltud.thecoffeehouse.news;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ltud.thecoffeehouse.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private Context mContext;
    private List<News> mBooks;
    private RecyclerView.RecyclerListener listener;

    public NewsAdapter() {
    }

    public void setData(List<News> list){
        this.mBooks = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News book = mBooks.get(position);
        if(book==null){
            return;
        }

        holder.imgBook.setImageResource(book.getResourceId());
        holder.tvTitle.setText(book.getTitle());

        holder.btnGoToDetailNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(String.valueOf(position) !=null){

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        if(mBooks !=null){
            return mBooks.size();
        }
        return 0;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgBook;
        private TextView tvTitle;
        private TextView btnGoToDetailNews;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            btnGoToDetailNews = itemView.findViewById(R.id.btn_go_to_detail_news);
            imgBook = itemView.findViewById(R.id.img_book);
            tvTitle = itemView.findViewById(R.id.tv_title);

        }
    }
}
