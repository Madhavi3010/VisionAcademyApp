package ran.com.visionacademy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class ReadSmsActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_RECEIVE_SMS = 0;
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    TextView msg1,number;

    MyReceiver receiver = new MyReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);
            msg1.setText(msg);
            number.setText(phoneNo);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(SMS_RECEIVED));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_sms);

        ActivityCompat.requestPermissions(this,
        new String[]{Manifest.permission.READ_SMS},PackageManager.PERMISSION_GRANTED);

        msg1 = findViewById(R.id.msg1);
        number = findViewById(R.id.number);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)!= PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.RECEIVE_SMS)){

            }
            else {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECEIVE_SMS}, MY_PERMISSIONS_REQUEST_RECEIVE_SMS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[] , int[] grantResults)
    {
        switch(requestCode)
        {
            case MY_PERMISSIONS_REQUEST_RECEIVE_SMS:
            {
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "Thank you for permitting!!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(this, "Well I can't do anything until you permit me", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}