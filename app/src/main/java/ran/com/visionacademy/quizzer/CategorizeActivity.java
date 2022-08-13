//package ran.com.visionacademy.quizzer;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.app.Dialog;
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.Toast;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import ran.com.visionacademy.R;
//
//public class CategorizeActivity extends AppCompatActivity {
//
//    private RecyclerView recyclerView;
//    FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference myRef = database.getReference();
//    public static List<CategoryModel> list;
//
//    private Dialog loadingDialog;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_categorize);
//        Toolbar toolbar = findViewById(R.id.t1);
//
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Categories");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        loadingDialog = new Dialog(this);
//        loadingDialog.setContentView(R.layout.loading);
//        loadingDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.rounded_corner));
//        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
//        loadingDialog.setCancelable(false);
//
//        recyclerView = findViewById(R.id.rv);
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(RecyclerView.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);
//
//        list = new ArrayList<>();
//        CategoryAdapter adapter = new CategoryAdapter(list);
//        recyclerView.setAdapter(adapter);
//
//        loadingDialog.show();
//        myRef.child("Categories").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//
//                    List<String> sets = new ArrayList<>();
//                    for (DataSnapshot dataSnapshot2 : dataSnapshot1.child("sets").getChildren()) {
//                        sets.add(dataSnapshot2.getKey());
//                    }
//                    list.add(new CategoryModel(dataSnapshot1.child("name").getValue().toString(),
//                            sets,
//                            dataSnapshot1.getKey()
//                    ));
//                }
//                adapter.notifyDataSetChanged();
//                loadingDialog.dismiss();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(CategorizeActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//                loadingDialog.dismiss();
//                finish();
//            }
//        });
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
//}