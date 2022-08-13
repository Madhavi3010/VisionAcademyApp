package ran.com.visionacademy.attendanceList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
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
import java.util.Objects;

import ran.com.visionacademy.R;
import ran.com.visionacademy.ui.gallery.GalleryAdapter;

public class AttendanceListActivity extends AppCompatActivity {

    private RecyclerView xiScienceRecycler , xiiScienceRecycler , engineeringRecycler;
    private DatabaseReference reference;
    private List<AttendanceData> attendanceList;
    private AttendanceAdapter adapter;
    ShimmerFrameLayout shimmerFrameLayout;
    LinearLayout shimmerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_list);

        reference = FirebaseDatabase.getInstance().getReference().child("Attendance Pdf");

        xiScienceRecycler = findViewById(R.id.xiScienceRecycler);
        xiiScienceRecycler = findViewById(R.id.xiiScienceRecycler);
        engineeringRecycler = findViewById(R.id.engineeringRecycler);

        shimmerFrameLayout = findViewById(R.id.shimmer_view_container);
        shimmerLayout = findViewById(R.id.shimmer_layout);

        getXiScience();

        getXiiScience();

        getEngineering();

    }

    private void getXiScience() {
        reference.child("XI SCIENCE").addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                attendanceList = new ArrayList<>();

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    AttendanceData data = snapshot.getValue(AttendanceData.class);
                    attendanceList.add(data);
                }

                adapter = new AttendanceAdapter(AttendanceListActivity.this, attendanceList);
                xiScienceRecycler.setLayoutManager(new LinearLayoutManager(AttendanceListActivity.this));
                xiScienceRecycler.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AttendanceListActivity.this,databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getXiiScience() {
        reference.child("XII SCIENCE").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                attendanceList = new ArrayList<>();

                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    AttendanceData data = snapshot.getValue(AttendanceData.class);
                    attendanceList.add(data);
                }

                adapter = new AttendanceAdapter(AttendanceListActivity.this, attendanceList);
                xiiScienceRecycler.setLayoutManager(new LinearLayoutManager(AttendanceListActivity.this));
                xiiScienceRecycler.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AttendanceListActivity.this,databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getEngineering() {
        reference.child("ENGINEERING").addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                attendanceList = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    AttendanceData data = snapshot.getValue(AttendanceData.class);
                    attendanceList.add(data);
                }

                adapter = new AttendanceAdapter(AttendanceListActivity.this, attendanceList);
                engineeringRecycler.setLayoutManager(new LinearLayoutManager(AttendanceListActivity.this));
                engineeringRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AttendanceListActivity.this,databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}