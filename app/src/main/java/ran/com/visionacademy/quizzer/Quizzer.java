//package ran.com.visionacademy.quizzer;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//
//import ran.com.visionacademy.R;
//
//public class Quizzer extends AppCompatActivity {
//    private Button start_quiz , bookmarkBtn;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_quizzer);
//
//        start_quiz = findViewById(R.id.start_quiz);
//        bookmarkBtn = findViewById(R.id.bookmark);
//
//        start_quiz.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent categoryIntent = new Intent(Quizzer.this,CategorizeActivity.class);
//                startActivity(categoryIntent);
//            }
//        });
//
//        bookmarkBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent bookmarkIntent = new Intent(Quizzer.this,BookmarkActivity.class);
//                startActivity(bookmarkIntent);
//            }
//        });
//    }
//}