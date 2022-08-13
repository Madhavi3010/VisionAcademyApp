package ran.com.visionacademy.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

import ran.com.visionacademy.CoursesActivity;
import ran.com.visionacademy.FullImageView;
import ran.com.visionacademy.R;
import ran.com.visionacademy.ui.about.BranchModel;
import ran.com.visionacademy.ui.gallery.GalleryAdapter;

public class HomeAdapter extends PagerAdapter {

    private Context context;
    private List<BranchModel> list1;
    private Object view1;

    public HomeAdapter(Context context, List<BranchModel> list1) {
        this.context = context;
        this.list1 = list1;
    }

    @Override
    public int getCount() {
        return list1.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.home_item_layout, container, false);

        ImageView brIcon;
        TextView brTitle;
        TextView brDesc;

        brIcon = view.findViewById(R.id.br_icon1);
        brTitle = view.findViewById(R.id.br_title1);
        brDesc = view.findViewById(R.id.br_desc1);

        brDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                Intent intent = new Intent(context, CoursesActivity.class);
                context.startActivity(intent);
            }
        });

        brIcon.setImageResource(list1.get(position).getImg());

        brTitle.setText(list1.get(position).getTitle());
        brDesc.setText(list1.get(position).getTitleDescription());


        container.addView(view, 0);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
