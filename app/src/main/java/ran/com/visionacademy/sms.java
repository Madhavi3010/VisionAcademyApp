package ran.com.visionacademy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sms extends AppCompatActivity {

    private EditText phone, sms;
    private Button sendSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        phone = findViewById(R.id.phone);
        sms = findViewById(R.id.sms);
        sendSms = findViewById(R.id.sendSms);

        sendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(sms.this, Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){
                    sendMessage();
                }else {
                    ActivityCompat.requestPermissions(ran.com.visionacademy.sms.this,new String[]{Manifest.permission.SEND_SMS},100);
                }
            }
        });
    }

    private void sendMessage() {
        String sPhone = phone.getText().toString().trim();
        String sMessage = sms.getText().toString().trim();

        if (!sPhone.equals("") && !sMessage.equals("")){
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(sPhone,null,sMessage,null,null);

            Toast.makeText(this, "Thanks for review !!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Enter Value", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            sendMessage();
        }else {
            Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
        }
    }
}