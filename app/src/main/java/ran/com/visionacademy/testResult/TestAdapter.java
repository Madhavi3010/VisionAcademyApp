package ran.com.visionacademy.testResult;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.List;

import ran.com.visionacademy.R;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {

    private Context context;
    private List<TestData> testList;

    public TestAdapter(Context context, List<TestData> testList) {
        this.context = context;
        this.testList = testList;
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.test_item_layout,parent,false);
        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, final int position) {
        holder.testName.setText(testList.get(position).getPdfTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TestViewerActivity.class);
                intent.putExtra("pdfUrl", testList.get(position).getPdfUrl());
                context.startActivity(intent);
            }
        });

        holder.testDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(testList.get(position).getPdfUrl()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return testList.size();
    }

    public class TestViewHolder extends RecyclerView.ViewHolder {
        public TextView testName;
        public ImageView testDownload;


        public TestViewHolder(@NonNull View itemView) {
            super(itemView);

            testName = itemView.findViewById(R.id.testName);
            testDownload = itemView.findViewById(R.id.testDownload);
        }
    }
}
