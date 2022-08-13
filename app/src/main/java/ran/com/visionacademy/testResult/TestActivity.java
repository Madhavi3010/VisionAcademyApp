package ran.com.visionacademy.testResult;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import ran.com.visionacademy.R;
import ran.com.visionacademy.attendanceList.AttendanceAdapter;
import ran.com.visionacademy.attendanceList.AttendanceData;
import ran.com.visionacademy.attendanceList.AttendanceListActivity;

public class TestActivity extends AppCompatActivity {

    private RecyclerView xiScienceTestRecycler, xiiScienceTestRecycler, engineeringTestRecycler;
    private DatabaseReference reference;
    private List<TestData> testList;
    private TestAdapter adapter;

    ShimmerFrameLayout shimmerFrameLayout;
    LinearLayout shimmerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        reference = FirebaseDatabase.getInstance().getReference().child("Test Result");

        xiScienceTestRecycler = findViewById(R.id.xiScienceTestRecycler);
        xiiScienceTestRecycler = findViewById(R.id.xiiScienceTestRecycler);
        engineeringTestRecycler = findViewById(R.id.engineeringTestRecycler);

        shimmerFrameLayout = findViewById(R.id.shimmer_view_container);
        shimmerLayout = findViewById(R.id.shimmer_layout);

        getXiScienceTest();

        getXiiScienceTest();

        getEngineeringTest();

    }

    private void getXiScienceTest() {
        reference.child("XI SCIENCE").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                testList = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    TestData data = snapshot.getValue(TestData.class);
                    testList.add(data);
                }

                adapter = new TestAdapter(TestActivity.this, testList);
                xiScienceTestRecycler.setLayoutManager(new LinearLayoutManager(TestActivity.this));
                xiScienceTestRecycler.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(TestActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getXiiScienceTest() {
        reference.child("XII SCIENCE").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                testList = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    TestData data = snapshot.getValue(TestData.class);
                    testList.add(data);
                }

                adapter = new TestAdapter(TestActivity.this, testList);
                xiiScienceTestRecycler.setLayoutManager(new LinearLayoutManager(TestActivity.this));
                xiiScienceTestRecycler.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(TestActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getEngineeringTest() {
        reference.child("ENGINEERING").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                testList = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    TestData data = snapshot.getValue(TestData.class);
                    testList.add(data);
                }
                adapter = new TestAdapter(TestActivity.this, testList);
                engineeringTestRecycler.setLayoutManager(new LinearLayoutManager(TestActivity.this));
                engineeringTestRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(TestActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}