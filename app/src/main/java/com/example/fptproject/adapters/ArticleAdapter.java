package com.example.fptproject.adapters;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fptproject.R;
import com.example.fptproject.models.Article;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    private List<Article> articles;

    public ArticleAdapter(List<Article> articles) {
        this.articles = articles;
    }


    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articles.get(position);
        if (article == null){
            return;
        }
        holder.titleTextView.setText(article.getTitle());
        holder.contentTextView.setText(article.getContent());

    }

    @Override
    public int getItemCount() {
        if (articles != null){
            return articles.size();
        }
        return 0;
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView titleTextView;
        private TextView contentTextView;


        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            contentTextView = itemView.findViewById(R.id.contentTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String url = contentTextView.getText().toString();
            if (url != null && !url.isEmpty()) {
                // Tạo Intent để mở trang web
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                // Bắt đầu Activity mới để mở trang web
                v.getContext().startActivity(intent);
            }
        }
    }
}

