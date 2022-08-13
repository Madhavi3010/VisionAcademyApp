//package ran.com.visionacademy.quizzer;
//
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//
//import java.util.List;
//import java.util.zip.Inflater;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//import ran.com.visionacademy.R;
//
//public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewholder> {
//
//    private List<CategoryModel> categoryModelList;
//
//    public CategoryAdapter(List<CategoryModel> categoryModelList) {
//        this.categoryModelList = categoryModelList;
//    }
//
//    @NonNull
//    @Override
//    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);
//        return new viewholder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull viewholder holder, int position) {
//
//        holder.setData(categoryModelList.get(position).getUrl(),categoryModelList.get(position).getName(),position);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return categoryModelList.size();
//    }
//
//    class viewholder extends RecyclerView.ViewHolder{
//
//        private CircleImageView circle;
//        private TextView c_title;
//
//        public viewholder(@NonNull View itemView) {
//            super(itemView);
//
//            circle = itemView.findViewById(R.id.circle);
//            c_title = itemView.findViewById(R.id.c_title);
//        }
//
//        private void setData(String url,final String c_title,final int position){
//            Glide.with(itemView.getContext()).load(url).into(circle);
//            this.c_title.setText(c_title);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent setIntent = new Intent(itemView.getContext(),SetsActivity.class);
//                    setIntent.putExtra("title",c_title);
//                    setIntent.putExtra("position",position);
//                    itemView.getContext().startActivity(setIntent);
//                }
//            });
//        }
//    }
//
//
//
//}
