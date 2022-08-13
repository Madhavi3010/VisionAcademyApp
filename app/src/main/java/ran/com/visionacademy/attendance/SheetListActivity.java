package ran.com.visionacademy.attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import ran.com.visionacademy.R;

public class SheetListActivity extends AppCompatActivity {

    private ListView sheetList;
    private ArrayAdapter sheetAdapter;
    private ArrayList<String> listItems = new ArrayList();
    private long cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet_list);

        cid = getIntent().getLongExtra("cid",-1);
        Log.i("1234567890","onCreate: "+cid);
        loadListItems();
        sheetList = findViewById(R.id.sheetList);
        sheetAdapter = new ArrayAdapter(this,R.layout.sheet_list,R.id.date_list_item,listItems);
        sheetList.setAdapter(sheetAdapter);

        sheetList.setOnItemClickListener((parent, view, position, id) -> openSheetActivity(position));
    }

    private void openSheetActivity(int position) {
        long [] idArray = getIntent().getLongArrayExtra("idArray");
        int [] rollArray = getIntent().getIntArrayExtra("rollArray");
        String [] nameArray = getIntent().getStringArrayExtra("nameArray");
        Intent intent = new Intent(this,SheetActivity.class);
        intent.putExtra("idArray",idArray);
        intent.putExtra("rollArray",rollArray);
        intent.putExtra("nameArray",nameArray);
        intent.putExtra("month",listItems.get(position));

        startActivity(intent);
    }

    private void loadListItems() {
        Cursor cursor = new DbHelper(this).getDistinctMonths(cid);

        while (cursor.moveToNext()){
            String date = cursor.getString(cursor.getColumnIndex(DbHelper.DATE_KEY));//01.04.2020
            listItems.add(date.substring(3));
        }
    }
}