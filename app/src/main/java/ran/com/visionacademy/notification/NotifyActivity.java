package ran.com.visionacademy.notification;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ran.com.visionacademy.R;

public class NotifyActivity extends AppCompatActivity {

    private RecyclerView deleteNotificationRecycler;
    private ProgressBar progressNotification;
    private ArrayList<NotificationData> list1;
    private NotificationAdapter adapter;
    private DatabaseReference reference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);

        deleteNotificationRecycler = findViewById(R.id.deleteNotificationRecycler);
        progressNotification = findViewById(R.id.progressNotification);

        reference = FirebaseDatabase.getInstance().getReference().child("Notification");

        deleteNotificationRecycler.setLayoutManager(new LinearLayoutManager(this));
        deleteNotificationRecycler.setHasFixedSize(true);

        getNotification();

    }

    private void getNotification() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                list1 = new ArrayList<NotificationData>();
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    NotificationData data = snapshot.getValue(NotificationData.class);
                    list1.add(data);

                }
                adapter = new NotificationAdapter(NotifyActivity.this, list1);
                adapter.notifyDataSetChanged();
                progressNotification.setVisibility(View.GONE);

                deleteNotificationRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressNotification.setVisibility(View.GONE);

                Toast.makeText(NotifyActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}