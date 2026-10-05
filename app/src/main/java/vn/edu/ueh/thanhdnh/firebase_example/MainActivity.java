package vn.edu.ueh.thanhdnh.firebase_example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
  FirebaseFirestore db;
  Button btAdd, btShow;
  EditText etTitle, etContent, etAuthor, etImageUrl;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_main);
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
      Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
      return insets;
    });

    FirebaseApp.initializeApp(this);
    db = FirebaseFirestore.getInstance();
    btAdd = findViewById(R.id.btAdd);
    btShow = findViewById(R.id.btShow);
    etTitle = findViewById(R.id.etTitle);
    etContent = findViewById(R.id.etContent);
    etAuthor = findViewById(R.id.etAuthor);
    etImageUrl = findViewById(R.id.etImageUrl);
    btAdd.setOnClickListener(this);
    btShow.setOnClickListener(this);
  }

  @Override
  public void onClick(View view) {
    if (view.getId() == R.id.btAdd) {
      String title = etTitle.getText().toString().trim();
      if (title.isEmpty()) {
        Toast.makeText(this, "Vui lòng nhập tiêu đề", Toast.LENGTH_SHORT).show();
        return;
      }
      Article article = new Article(
              title,
              etContent.getText().toString().trim(),
              etAuthor.getText().toString().trim(),
              etImageUrl.getText().toString().trim());

      db.collection("articles").add(article)
              .addOnSuccessListener(ref ->
                      Toast.makeText(this, "Đã lưu bài viết lên Firebase", Toast.LENGTH_SHORT).show())
              .addOnFailureListener(e ->
                      Toast.makeText(this, "Lỗi: " + e.getMessage(), Toast.LENGTH_LONG).show());

      etTitle.setText("");
      etContent.setText("");
      etAuthor.setText("");
      etImageUrl.setText("");
    } else if (view.getId() == R.id.btShow) {
      startActivity(new Intent(getBaseContext(), ShowDataActivity.class));
    }
  }
}