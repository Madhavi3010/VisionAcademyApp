package ran.com.visionacademy.attendanceList;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import ran.com.visionacademy.FullImageView;
import ran.com.visionacademy.R;
import ran.com.visionacademy.ebook.PdfViewerActivity;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder> {

    private Context context;
    private List<AttendanceData> attendanceList;

    public AttendanceAdapter(Context context, List<AttendanceData> attendanceList) {
        this.context = context;
        this.attendanceList = attendanceList;
    }

    @NonNull
    @Override
    public AttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.attendance_item_layout, parent, false);
        return new AttendanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceViewHolder holder, final int position) {

        holder.attendanceName.setText(attendanceList.get(position).getPdfTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AttendanceViewerActivity.class);
                intent.putExtra("pdfUrl", attendanceList.get(position).getPdfUrl());
                context.startActivity(intent);
            }
        });

        holder.attendanceDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(attendanceList.get(position).getPdfUrl()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return attendanceList.size();
    }

    public class AttendanceViewHolder extends RecyclerView.ViewHolder {

        private TextView attendanceName;
        private ImageView attendanceDownload;

        public AttendanceViewHolder(@NonNull View itemView) {
            super(itemView);

            attendanceName = itemView.findViewById(R.id.attendanceName);
            attendanceDownload = itemView.findViewById(R.id.attendanceDownload);
        }
    }
}
