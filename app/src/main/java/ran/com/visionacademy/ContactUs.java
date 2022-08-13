package ran.com.visionacademy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ContactUs extends AppCompatActivity {

    private ImageView instagram, facebook, twitter, map1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

        instagram = findViewById(R.id.instagram);
        twitter = findViewById(R.id.twitter);
        facebook = findViewById(R.id.facebook);

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.instagram.com/ganeshmore37/");
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://twitter.com/GANESHM85562377?s=20");
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://m.facebook.com/profile.php?id=100012696039051&ref=content_filter");
            }
        });

        map1 = findViewById(R.id.map1);
        map1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMap();
            }
        });

    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    private void openMap() {
        Uri uri = Uri.parse("geo:0, 0?q=Vision Academy, Powai Naka, Satara, Maharashtra");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }
}