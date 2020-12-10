package category_news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ltud.thecoffeehouse.NewsFragment;
import com.ltud.thecoffeehouse.R;

import java.util.List;

import news.NewsAdapter;

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
        CategoryNews category = mListCategoryNews.get(position);
        if(category == null){
            return;
        }

        holder.tvNamCategory.setText(category.getNameCategory());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext,RecyclerView.HORIZONTAL,false);
        holder.rcvBook.setLayoutManager(linearLayoutManager);

        NewsAdapter newsAdapter = new NewsAdapter();
        newsAdapter.setData(category.getBooks());

        holder.rcvBook.setAdapter(newsAdapter);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CategoryNewsViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNamCategory;
        private RecyclerView rcvBook;

        public CategoryNewsViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamCategory = itemView.findViewById(R.id.tv_name_category);
            rcvBook = itemView.findViewById(R.id.rcv_book);
        }
    }
}
