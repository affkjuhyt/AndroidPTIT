package com.ltud.thecoffeehouse.news;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ltud.thecoffeehouse.Model.OrderItems;
import com.ltud.thecoffeehouse.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private Context context ;
    private RecyclerView.RecyclerListener listener;
    private LayoutInflater layoutInflater;
    private ArrayList<News> newsItem;
    News news;
    public NewsAdapter(Context c, ArrayList<News> newsItem) {
        this.context = c;
        this.newsItem = newsItem;

    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false);
        return new NewsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        news = newsItem.get(position);
        if(news==null){
            holder.tvTitle.setText("Loi me roi");
        }


        holder.tvTitle.setText(news.getTitle());
        holder.tvDescription.setText(news.getDescription());
        Glide.with(context).load(news.getImage()).into(holder.imageView);
        holder.btnGoToDetailNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(String.valueOf(position) !=null){
                    Intent openURL = new Intent(android.content.Intent.ACTION_VIEW);
                    openURL.setData(Uri.parse(news.getLink()));
                    context.startActivity(openURL);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
//        if(newsItem !=null){
//            return newsItem.size();
//        }
//        return 0;
        return newsItem.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tvTitle;
        TextView btnGoToDetailNews;
        TextView tvDescription;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            btnGoToDetailNews = itemView.findViewById(R.id.btn_go_to_detail_news);
            imageView = itemView.findViewById(R.id.img_book);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
        }
    }
}

