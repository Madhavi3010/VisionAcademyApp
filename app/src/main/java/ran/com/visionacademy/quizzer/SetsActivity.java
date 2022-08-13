//package ran.com.visionacademy.quizzer;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.widget.Adapter;
//import android.widget.GridView;
//
//import java.util.List;
//
//import ran.com.visionacademy.R;
//
//public class SetsActivity extends AppCompatActivity {
//
//    private GridView quiz_grid;
//    private List<String> sets;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sets);
//        Toolbar toolbar = findViewById(R.id.t2);
//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
//
//        quiz_grid = findViewById(R.id.quiz_grid);
//
//        sets = CategorizeActivity.list.get(getIntent().getIntExtra("position",1)).getSets();
//
//        GridAdapter adapter = new GridAdapter(sets,getIntent().getStringExtra("title"));
//        quiz_grid.setAdapter(adapter);
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(item.getItemId() == android.R.id.home){
//            finish();
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}