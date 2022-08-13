package ran.com.visionacademy.ui.faculty;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import ran.com.visionacademy.R;

public class FacultyFragment extends Fragment {

    private RecyclerView physicsXiXii, chemistryXiXii, mathematicsXiXii, biologyXiXii, engineering;
    private LinearLayout phyNoData, cheNoData, matNoData, bioNoData, enggNoData;
    private List<TeacherData> list1, list2, list3, list4, list5;
    private TeacherAdapter adapter;

    private DatabaseReference reference, dbRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_faculty, container, false);

        engineering = view.findViewById(R.id.engineering);
        biologyXiXii = view.findViewById(R.id.biologyXiXii);
        mathematicsXiXii = view.findViewById(R.id.mathematicsXiXii);
        chemistryXiXii = view.findViewById(R.id.chemistryXiXii);
        physicsXiXii = view.findViewById(R.id.physicsXiXii);


        enggNoData = view.findViewById(R.id.enggNoData);
        bioNoData = view.findViewById(R.id.bioNoData);
        matNoData = view.findViewById(R.id.matNoData);
        cheNoData = view.findViewById(R.id.cheNoData);
        phyNoData = view.findViewById(R.id.phyNoData);

        reference = FirebaseDatabase.getInstance().getReference().child("teacher");

        physicsXiXii();
        chemistryXiXii();
        mathematicsXiXii();
        biologyXiXii();
        engineering();

        return view;
    }

    private void physicsXiXii() {
        dbRef = reference.child("PHYSICS XI-XII");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    phyNoData.setVisibility(View.VISIBLE);
                    physicsXiXii.setVisibility(View.GONE);
                } else {
                    phyNoData.setVisibility(View.GONE);
                    physicsXiXii.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list1.add(data);
                    }
                    physicsXiXii.setHasFixedSize(true);
                    physicsXiXii.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list1,getContext(),"PHYSICS XI-XII");
                    physicsXiXii.setAdapter(adapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void chemistryXiXii() {
        dbRef = reference.child("CHEMISTRY XI-XII");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    cheNoData.setVisibility(View.VISIBLE);
                    chemistryXiXii.setVisibility(View.GONE);
                } else {
                    cheNoData.setVisibility(View.GONE);
                    chemistryXiXii.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list2.add(data);
                    }
                    chemistryXiXii.setHasFixedSize(true);
                    chemistryXiXii.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list2, getContext(),"CHEMISTRY XI-XII");
                    chemistryXiXii.setAdapter(adapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void biologyXiXii() {
        dbRef = reference.child("BIOLOGY XI-XII");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list3 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    bioNoData.setVisibility(View.VISIBLE);
                    biologyXiXii.setVisibility(View.GONE);
                } else {
                    bioNoData.setVisibility(View.GONE);
                    biologyXiXii.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list3.add(data);
                    }
                    biologyXiXii.setHasFixedSize(true);
                    biologyXiXii.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list3, getContext(),"BIOLOGY XI-XII");
                    biologyXiXii.setAdapter(adapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void mathematicsXiXii() {
        dbRef = reference.child("MATHEMATICS XI-XII");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list4 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    matNoData.setVisibility(View.VISIBLE);
                    mathematicsXiXii.setVisibility(View.GONE);
                } else {
                    matNoData.setVisibility(View.GONE);
                    mathematicsXiXii.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list4.add(data);
                    }
                    mathematicsXiXii.setHasFixedSize(true);
                    mathematicsXiXii.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list4, getContext(),"MATHEMATICS XI-XII");
                    mathematicsXiXii.setAdapter(adapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void engineering() {
        dbRef = reference.child("ENGINEERING");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list5 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    enggNoData.setVisibility(View.VISIBLE);
                    engineering.setVisibility(View.GONE);
                } else {
                    enggNoData.setVisibility(View.GONE);
                    engineering.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list5.add(data);
                    }
                    engineering.setHasFixedSize(true);
                    engineering.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list5, getContext(),"ENGINEERING");
                    engineering.setAdapter(adapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

}