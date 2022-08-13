package ran.com.visionacademy.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import ran.com.visionacademy.CoursesActivity;
import ran.com.visionacademy.MainActivity;
import ran.com.visionacademy.R;
import ran.com.visionacademy.SliderAdapter;
import ran.com.visionacademy.authentication.LoginActivity;
import ran.com.visionacademy.ebook.EbookActivity;
import ran.com.visionacademy.ui.about.BranchAdapter;
import ran.com.visionacademy.ui.about.BranchModel;

public class HomeFragment extends Fragment {

    private int[] images;
    private SliderAdapter adapter;
    private SliderView sliderView;
    private TextView studentLink;
    private ViewPager viewPager1;
    private HomeAdapter homeAdapter;
    private List<BranchModel> list1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        studentLink = view.findViewById(R.id.studentLink);
        sliderView = view.findViewById(R.id.slider);

        images = new int[]{R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5};

        adapter = new SliderAdapter(images);

        sliderView.setSliderAdapter(adapter);

        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.DROP);
        sliderView.startAutoCycle();

        list1 = new ArrayList<>();
        list1.add(new BranchModel(R.drawable.ic_engineering, "ENGINEERING"," MATHEMATICS-I&II&III&IV                PHYSICS ENGINEERING                          MATERIAL OF CHEMISTRY"));
        list1.add(new BranchModel(R.drawable.ic_engineering, "E&TC ENGINEERING", "DIGITAL TECHNIQUES                           BASICS OF ELECTRONIC ENGINEERING               MICROPROCESSOR-X8086             MICRO-CONTROLLER-X8085     "));
        list1.add(new BranchModel(R.drawable.ic_engineering,"ELECTRICAL ENGINEERING"," FUNDAMENTAL OF ELECTRICAL ENGINEERING                                  ENGINEERING MECHANICS                                             UTILIZATION OF ELECTRICAL ENGINEERING"));
        list1.add(new BranchModel(R.drawable.ic_science, "XI AND XII SCIENCE","SCIENCE(PCMB)                                  PHYSICS                                           CHEMISTRY                              MATHEMATICS                                  BIOLOGY       "));
        list1.add(new BranchModel(R.drawable.ic_school, "8TH TO 10TH SSC/CBSE","       MARATHI/SEMI MARATHI/ENGLISH           MH CBSE BOARDS  (JEE/IIT FOUNDATION)"));

        homeAdapter = new HomeAdapter(getContext(),list1);

        viewPager1 = view.findViewById(R.id.viewPager2);

        viewPager1.setAdapter(homeAdapter);

        studentLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),CoursesActivity.class));
            }
        });

        viewPager1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),CoursesActivity.class));
            }
        });

        return view;
    }


}