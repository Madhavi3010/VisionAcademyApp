package ran.com.vision.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;

import ran.com.vision.R;


public class HomeFragment extends Fragment {

    private SliderLayout sliderLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        sliderLayout = view.findViewById(R.id.slider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(3);

        setSliderView();

        return view;


    }

    private void setSliderView() {
        for (int i = 0; i<4; i++){
            DefaultSliderView sliderView = new DefaultSliderView(getContext());

            switch (i){
                case 0:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/vision-academy-860c5.appspot.com/o/galery%2F%5BB%40799fbaejpg?alt=media&token=78c93677-b9bc-4d95-8eab-271d32d60628");
                    break;
                case 1:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/vision-academy-860c5.appspot.com/o/galery%2F%5BB%403d58e5cjpg?alt=media&token=7bfb700d-bfbc-4ed7-a836-6e8628824b04");
                    break;
                case 2:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/vision-academy-860c5.appspot.com/o/gallery%2F%5BB%40384dafcjpg?alt=media&token=4542b544-df5f-4919-9115-bf46d4e7db98");
                    break;
                case 3:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/vision-academy-860c5.appspot.com/o/galery%2F%5BB%40ff3e779jpg?alt=media&token=d78206d3-1371-4a27-b126-8ab065b11299");
                    break;
            }
            sliderView.setImageScaleType(ImageView.ScaleType.FIT_CENTER);

            sliderLayout.addSliderView(sliderView);
        }
    }
}