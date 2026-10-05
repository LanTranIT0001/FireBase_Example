package vn.edu.ueh.thanhdnh.firebase_example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ArticleViewAdapter extends RecyclerView.Adapter<ArticleViewHolder> {
  private LayoutInflater mInflater;
  private List<Article> articles;

  public ArticleViewAdapter(Context context, List<Article> articles) {
    this.mInflater = LayoutInflater.from(context);
    this.articles = articles;
  }

  public void update(List<Article> articles) {
    this.articles = articles;
  }

  @NonNull
  @Override
  public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View customView = mInflater.inflate(R.layout.item_article, parent, false);
    return new ArticleViewHolder(customView, this);
  }

  @Override
  public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
    Article current = articles.get(position);
    holder.getTxtTitle().setText(current.getTitle());
    holder.getTxtAuthor().setText("Tác giả: " + current.getAuthor());
    holder.getTxtContent().setText(current.getContent());

    // Ảnh lấy trực tiếp từ link web, không upload
    Glide.with(holder.itemView.getContext())
            .load(current.getImageUrl())
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_menu_report_image)
            .into(holder.getImgArticle());
  }

  @Override
  public int getItemCount() {
    return articles.size();
  }
}