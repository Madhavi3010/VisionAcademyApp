//package ran.com.visionacademy.quizzer;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.MenuItem;
//
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.List;
//
//import ran.com.visionacademy.R;
//
//public class BookmarkActivity extends AppCompatActivity {
//
//    public static final String FILE_NAME = "QUIZZER";
//    public static final String KEY_NAME = "QUESTIONS";
//    private RecyclerView rv_bookmarks;
//    private List<QuestionModel> bookmarkList;
//
//    private SharedPreferences preferences;
//    private SharedPreferences.Editor editor;
//    private Gson gson;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bookmark);
//        Toolbar toolbar = findViewById(R.id.t4);
//
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Bookmark Questions List");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        rv_bookmarks = findViewById(R.id.rv_bookmarks);
//
//        preferences = getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
//        editor = preferences.edit();
//        gson = new Gson();
//
//        getBookmarks();
//
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(RecyclerView.VERTICAL);
//
//        rv_bookmarks.setLayoutManager(layoutManager);
//
//
//        BookmarkAdapter adapter = new BookmarkAdapter(bookmarkList);
//        rv_bookmarks.setAdapter(adapter);
//
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        storeBookmarks();
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        if (item.getItemId() == android.R.id.home){
//            finish();
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void getBookmarks() {
//        String json = preferences.getString(KEY_NAME, "");
//
//        Type type = new TypeToken<List<QuestionModel>>() {
//        }.getType();
//
//        bookmarkList = gson.fromJson(json, type);
//
//        if (bookmarkList == null) {
//            bookmarkList = new ArrayList<>();
//        }
//    }
//
//    private void storeBookmarks() {
//        String json = gson.toJson(bookmarkList);
//
//        editor.putString(KEY_NAME, json);
//        editor.commit();
//    }
//}