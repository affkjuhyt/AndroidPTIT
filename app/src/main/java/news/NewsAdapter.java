package news;

import android.content.Context;
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
    private List<News> mNews;

    public NewsAdapter() {
    }

    public void setData(List<News> list){
        this.mNews = list;
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
        News news = mNews.get(position);
        if(news==null){
            return;
        }

        holder.imgBook.setImageResource(news.getResourceId());
        holder.tvTitle.setText(news.getTitle());

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
        if(mNews !=null){
            return mNews.size();
        }
        return 0;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgBook;
        private TextView tvTitle;
        private Button btnGoToDetailNews;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            btnGoToDetailNews = itemView.findViewById(R.id.btn_go_to_detail_news);
            imgBook = itemView.findViewById(R.id.img_book);
            tvTitle = itemView.findViewById(R.id.tv_title);

        }
    }
}
