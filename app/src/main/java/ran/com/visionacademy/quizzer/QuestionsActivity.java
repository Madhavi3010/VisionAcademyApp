//package ran.com.visionacademy.quizzer;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//
//import android.animation.Animator;
//import android.app.Dialog;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.res.ColorStateList;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.view.View;
//import android.view.animation.DecelerateInterpolator;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.List;
//
//import ran.com.visionacademy.R;
//
//public class QuestionsActivity extends AppCompatActivity {
//
//    FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference myRef = database.getReference();
//
//    public static final String FILE_NAME = "QUIZZER";
//    public static final String KEY_NAME = "QUESTIONS";
//
//    private TextView question, no_indicator;
//    private FloatingActionButton bookmark_btn;
//    private LinearLayout options_container;
//    private Button share_btn;
//    private Button next_btn;
//    private Integer count = 0;
//    private List<QuestionModel> list;
//    private int position = 0;
//    private int score = 0;
//
//    private String setId;
//    private Dialog loadingDialog;
//    private int matchedQuestionPosition;
//
//    private List<QuestionModel> bookmarkList;
//
//    private SharedPreferences preferences;
//    private SharedPreferences.Editor editor;
//    private Gson gson;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_questions);
//        Toolbar toolbar = findViewById(R.id.t3);
//        setSupportActionBar(toolbar);
//
//        question = findViewById(R.id.question);
//        no_indicator = findViewById(R.id.no_indicator);
//        bookmark_btn = findViewById(R.id.bookmark_btn);
//        options_container = findViewById(R.id.options_container);
//        next_btn = findViewById(R.id.next_btn);
//
//        preferences = getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
//        editor = preferences.edit();
//        gson = new Gson();
//
//        getBookmarks();
//
//        bookmark_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (modelMatch()) {
//                    bookmarkList.remove(matchedQuestionPosition);
//                    bookmark_btn.setImageDrawable(getDrawable(R.drawable.bookmark_border));
//                } else {
//                    bookmarkList.add(list.get(position));
//                    bookmark_btn.setImageDrawable(getDrawable(R.drawable.bookmark));
//                }
//            }
//        });
//
//
//        setId = getIntent().getStringExtra("setId");
//
//        loadingDialog = new Dialog(this);
//        loadingDialog.setContentView(R.layout.loading);
//        loadingDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.rounded_corner));
//        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        loadingDialog.setCancelable(false);
//
//        list = new ArrayList<>();
//
//        loadingDialog.show();
//        myRef
//                .child("SETS").child(setId).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                    String id = dataSnapshot1.getKey();
//                    String question = dataSnapshot1.child("question").getValue().toString();
//                    String a = dataSnapshot1.child("optionA").getValue().toString();
//                    String b = dataSnapshot1.child("optionB").getValue().toString();
//                    String c = dataSnapshot1.child("optionC").getValue().toString();
//                    String d = dataSnapshot1.child("optionD").getValue().toString();
//                    String correctANS = dataSnapshot1.child("correctANS").getValue().toString();
//
//                    list.add(new QuestionModel(id, question, a, b, c, d, correctANS, setId));
//                }
//                if (list.size() > 0) {
//
//
//                    for (int i = 0; i < 4; i++) {
//                        options_container.getChildAt(i).setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                checkAnswer((Button)v);
//                            }
//                        });
//                    }
//                    playAnim(question, 0, list.get(position).getQuestion());
//                    next_btn.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            next_btn.setEnabled(false);
//                            next_btn.setAlpha(0.7f);
//                            enableOption(true);
//                            position++;
//                            if (position == list.size()) {
//                                Intent scoreIntent = new Intent(QuestionsActivity.this, ScoreActivity.class);
//                                scoreIntent.putExtra("score", score);
//                                scoreIntent.putExtra("total", list.size());
//                                startActivity(scoreIntent);
//                                finish();
//                                return;
//                            }
//                            count = 0;
//                            playAnim(question, 0, list.get(position).getQuestion());
//                        }
//                    });
//
//                } else {
//                    finish();
//                    Toast.makeText(QuestionsActivity.this, "no questions", Toast.LENGTH_SHORT).show();
//                }
//                loadingDialog.dismiss();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(QuestionsActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//                loadingDialog.dismiss();
//                finish();
//            }
//        });
//
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        storeBookmarks();
//    }
//
//
//
//    private void playAnim(final View view, final int value, final String data) {
//        for (int i = 0; i < 4; i++) {
//            options_container.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFD6D6D6")));
//        }
//
//        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
//                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//                if (value == 0 && count < 4) {
//                    String option = "";
//                    if (count == 0) {
//                        option = list.get(position).getA();
//                    } else if (count == 1) {
//                        option = list.get(position).getB();
//                    } else if (count == 2) {
//                        option = list.get(position).getC();
//                    } else if (count == 3) {
//                        option = list.get(position).getD();
//
//                    }
//                    playAnim(options_container.getChildAt(count), 0, option);
//                    count++;
//                }
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                if (value == 0) {
//                    try {
//                        ((TextView) view).setText(data);
//                        no_indicator.setText(position + 1 + "/" + list.size());
//                        if (modelMatch()) {
//                            bookmark_btn.setImageDrawable(getDrawable(R.drawable.bookmark));
//                        } else {
//                            bookmark_btn.setImageDrawable(getDrawable(R.drawable.bookmark_border));
//                        }
//                    } catch (ClassCastException ex) {
//                        ((Button) view).setText(data);
//                    }
//                    view.setTag(data);
//                    playAnim(view, 1, data);
//                }else {
//                    enableOption(true);
//                }
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
//    }
//
//    private void checkAnswer(Button selectedOption) {
//        enableOption(false);
//        next_btn.setEnabled(true);
//        next_btn.setAlpha(1);
//        if (selectedOption.getText().toString().equals(list.get(position).getAnswer())) {
//            //correct
//            score++;
//            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00E676")));
//        } else {
//            //incorrect
//            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
//            Button correctoption = (Button) options_container.findViewWithTag(list.get(position).getAnswer());
//
//
//        }
//    }
//
//    private void enableOption(boolean enable) {
//        for (int i = 0; i < 4; i++) {
//            options_container.getChildAt(i).setEnabled(enable);
//        }
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
//    private boolean modelMatch() {
//        boolean matched = false;
//        int i = 0;
//        for (QuestionModel model : bookmarkList) {
//            if (model.getQuestion().equals(list.get(position).getQuestion())
//                    && model.getAnswer().equals(list.get(position).getAnswer())
//                    && model.getSet().equals(list.get(position).getSet())) {
//                matched = true;
//                matchedQuestionPosition = i;
//            }
//            i++;
//        }
//        return matched;
//    }
//
//    private void storeBookmarks() {
//        String json = gson.toJson(bookmarkList);
//
//        editor.putString(KEY_NAME, json);
//        editor.commit();
//    }
//}