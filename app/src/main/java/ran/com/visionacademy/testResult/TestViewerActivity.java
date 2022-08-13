package ran.com.visionacademy.testResult;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import ran.com.visionacademy.R;
import ran.com.visionacademy.attendanceList.AttendanceViewerActivity;

public class TestViewerActivity extends AppCompatActivity {

    private String url;
    private PDFView testView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_viewer);

        url = getIntent().getStringExtra("pdfUrl");

        testView = findViewById(R.id.testView);

        new PdfDownload().execute(url);

    }

    private class PdfDownload extends AsyncTask<String, Void, InputStream> {

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                if (urlConnection.getResponseCode() == 200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            testView.fromStream(inputStream).load();
        }


    }
}