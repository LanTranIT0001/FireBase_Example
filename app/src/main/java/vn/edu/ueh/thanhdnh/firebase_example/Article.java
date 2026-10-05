package vn.edu.ueh.thanhdnh.firebase_example;

public class Article {
  private String title;
  private String content;
  private String author;
  private String imageUrl;

  // Constructor rỗng: bắt buộc để Firestore đọc dữ liệu về
  public Article() {
  }

  public Article(String title, String content, String author, String imageUrl) {
    this.title = title;
    this.content = content;
    this.author = author;
    this.imageUrl = imageUrl;
  }

  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }

  public String getContent() { return content; }
  public void setContent(String content) { this.content = content; }

  public String getAuthor() { return author; }
  public void setAuthor(String author) { this.author = author; }

  public String getImageUrl() { return imageUrl; }
  public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

  @Override
  public String toString() {
    return "Article{" +
            "title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", author='" + author + '\'' +
            ", imageUrl='" + imageUrl + '\'' +
            '}';
  }
}