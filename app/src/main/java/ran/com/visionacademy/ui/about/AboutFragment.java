package ran.com.visionacademy.ui.about;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

import ran.com.visionacademy.R;

public class AboutFragment extends Fragment {

    private ViewPager viewPager;
    private BranchAdapter adapter;
    private List<BranchModel> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        list = new ArrayList<>();
        list.add(new BranchModel(R.drawable.ic_engineering, "ENGINEERING","                   DEGREE/DIPLOMA                          ELECTRICAL ENGINEERING     ELECTRONIC ENGINEERING      ELECTRONIC AND TELECOMMUNICATION ENGINEERING"));
        list.add(new BranchModel(R.drawable.ic_science, "XI AND XII SCIENCE","                          SCIENCE(PCMB)                           MH-CET/JEE/NEET                                      Special Batches for CBSE                                  Seperate Batches for Physics and Mathematics      MH-Board & CBSE Board       "));
        list.add(new BranchModel(R.drawable.ic_school, "8TH TO 10TH SSC/CBSE","       MARATHI/SEMI MARATHI/ENGLISH           MH CBSE BOARDS  (JEE/IIT FOUNDATION)"));

        adapter = new BranchAdapter(getContext(),list);

        viewPager = view.findViewById(R.id.viewPager);

        viewPager.setAdapter(adapter);

        return view;

    }

}