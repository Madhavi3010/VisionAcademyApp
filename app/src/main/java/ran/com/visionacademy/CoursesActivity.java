package ran.com.visionacademy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CoursesActivity extends AppCompatActivity {
    DatabaseHelper myDb;

    private EditText et_cName , et_cStandard , et_cCourses , et_cPhone;
    private Button btn_course , btn_admin_courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        myDb = new DatabaseHelper(this);

        et_cName = (EditText) findViewById(R.id.et_cName);
        et_cStandard = (EditText) findViewById(R.id.et_cStandard);
        et_cCourses = (EditText) findViewById(R.id.et_cCourses);
        et_cPhone = (EditText) findViewById(R.id.et_cPhone);
        btn_course = (Button) findViewById(R.id.btn_course);
        btn_admin_courses = (Button) findViewById(R.id.btn_admin_courses);

        AddData();
        viewAll();

    }

    public void AddData() {
        btn_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = myDb.insertData(et_cName.getText().toString(),
                        et_cStandard.getText().toString(),
                        et_cCourses.getText().toString(),
                        et_cPhone.getText().toString() );
                if (isInserted == true) {
                    Toast.makeText(CoursesActivity.this, "Data Inserted..", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(CoursesActivity.this, PaymentActivity.class));

                }else
                    Toast.makeText(CoursesActivity.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void viewAll() {
        btn_admin_courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getAllData();
                if(res.getCount() == 0){
                    //show message
                    showMessage("Error","No Data Found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("NAME :"+ res.getString(0)+"\n");
                    buffer.append("STANDARD :"+ res.getString(1)+"\n");
                    buffer.append("COURSE :"+ res.getString(2)+"\n");
                    buffer.append("PHONE :"+ res.getString(3)+"\n\n");
                }
                //show all data
                showMessage("Data",buffer.toString());
            }
        });
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}