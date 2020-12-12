package com.ltud.thecoffeehouse.category_news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ltud.thecoffeehouse.R;
import com.ltud.thecoffeehouse.news.NewsAdapter;

import java.util.List;

public class CategoryNewsAdapter extends RecyclerView.Adapter<CategoryNewsAdapter.CategoryNewsViewHolder> {

    private Context mContext;
    private List<CategoryNews> mListCategoryNews;

    public CategoryNewsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<CategoryNews> List){
        this.mListCategoryNews = List;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_news,parent,false);

        return new CategoryNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryNewsViewHolder holder, int position) {
        CategoryNews categoryNews = mListCategoryNews.get(position);
        if(categoryNews == null){
            return;
        }

        holder.tvNamCategory.setText(categoryNews.getNameCategory());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext,RecyclerView.HORIZONTAL,false);
        holder.rcvNews.setLayoutManager(linearLayoutManager);

        NewsAdapter newsAdapter = new NewsAdapter();
        newsAdapter.setData(categoryNews.getBooks());

        holder.rcvNews.setAdapter(newsAdapter);
    }

    @Override
    public int getItemCount() {
        return mListCategoryNews.size();
    }

    public class CategoryNewsViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNamCategory;
        private RecyclerView rcvNews;

        public CategoryNewsViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamCategory = itemView.findViewById(R.id.tv_name_category);
            rcvNews = itemView.findViewById(R.id.rcv_book);
        }
    }
}
