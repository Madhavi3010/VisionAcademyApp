package ran.com.visionacademy.notification;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import ran.com.visionacademy.R;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewAdapter> {

    private Context context;
    private ArrayList<NotificationData> list1;

    public NotificationAdapter(Context context, ArrayList<NotificationData> list1) {
        this.context = context;
        this.list1 = list1;
    }

    @NonNull
    @Override
    public NotificationViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notification_item_layout,parent,false);

        return new NotificationViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewAdapter holder, final int position) {

        final NotificationData currentItem = list1.get(position);

        holder.NotificationTitle.setText(currentItem.getTitle());
        holder.NotificationContent.setText(currentItem.getContent());
        holder.dateNotify.setText(currentItem.getDate());
        holder.timeNotify.setText(currentItem.getTime());

        holder.copyNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData data = (ClipData) ClipData.newPlainText("text",list1.get(position).getContent());
                clipboardManager.setPrimaryClip(data);

                Toast.makeText(context, "Text Copied!!!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public class NotificationViewAdapter extends RecyclerView.ViewHolder {


        private TextView NotificationTitle , dateNotify, timeNotify;
        private TextView NotificationContent;
        private ImageView copyNotification;

        public NotificationViewAdapter(@NonNull View itemView) {
            super(itemView);

            NotificationTitle = itemView.findViewById(R.id.NotificationTitle);
            NotificationContent = itemView.findViewById(R.id.NotificationContent);
            dateNotify = itemView.findViewById(R.id.dateNotify);
            timeNotify = itemView.findViewById(R.id.timeNotify);
            copyNotification = itemView.findViewById(R.id.copyNotification);
        }
    }

}
