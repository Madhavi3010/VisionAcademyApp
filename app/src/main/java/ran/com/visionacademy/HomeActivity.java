package ran.com.visionacademy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import ran.com.visionacademy.attendance.AttendanceLoginActivity;
import ran.com.visionacademy.attendanceList.AttendanceListActivity;
import ran.com.visionacademy.authentication.LoginActivity;
import ran.com.visionacademy.ebook.EbookActivity;
import ran.com.visionacademy.notification.NotifyActivity;
//import ran.com.visionacademy.quizzer.Quizzer;
import ran.com.visionacademy.testResult.TestActivity;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private FirebaseAuth firebaseAuth;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int checkedItem;
    private String selected;
    private final String CHECKEDITEM = "checked_item";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedPreferences = this.getSharedPreferences("themes", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        switch (getCheckedItem()) {
            case 0:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            case 1:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case 2:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
        }

        firebaseAuth = FirebaseAuth.getInstance();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navController = Navigation.findNavController(this, R.id.frame_layout);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_view);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.start, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    private void Logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_video:
                Toast.makeText(this, "Video Lectures", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_ebook:
                startActivity(new Intent(this, EbookActivity.class));
                break;
            case R.id.navigation_website:
                Toast.makeText(this, "Website", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_share:
                try {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "VISION ACADEMY");
                    intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=ran.com.visionacademy" + getApplicationContext().getPackageName());
                    startActivity(Intent.createChooser(intent, "Share"));
                } catch (Exception e) {
                    Toast.makeText(this, "Unable to share!!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.navigation_rate:
                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=ran.com.visionacademy" + getApplicationContext().getPackageName());
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    startActivity(i);
                } catch (Exception e) {
                    Toast.makeText(this, "Unable to open!!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.navigation_contact:
                startActivity(new Intent(HomeActivity.this, ContactUs.class));
                break;
            case R.id.navigation_developer:
                Toast.makeText(this, "developer", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_notification:
                startActivity(new Intent(this, NotifyActivity.class));
                break;
            case R.id.navigation_theme:
                showDialog();
                break;
            case R.id.navigation_attendance:
                startActivity(new Intent(this, AttendanceLoginActivity.class));
                break;
            case R.id.navigation_attendanceStu:
                startActivity(new Intent(this, AttendanceListActivity.class));
                break;
//            case R.id.navigation_testResult:
//                startActivity(new Intent(this, TestActivity.class));
//                break;
//            case R.id.navigation_quiz:
//                startActivity(new Intent(this, Quizzer.class));
//                break;
            case R.id.navigation_sms:
                startActivity(new Intent(this, sms.class));
                break;
            case R.id.navigation_read:
                startActivity(new Intent(this, ReadSmsActivity.class));
                break;
            case R.id.navigation_privacy:
                startActivity(new Intent(this, Privacy.class));
                break;
            case R.id.navigation_logout:
                Logout();
                break;
        }
        return true;
    }

    private void showDialog() {

        final String[] themes = this.getResources().getStringArray(R.array.theme);

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("Select Theme");
        builder.setSingleChoiceItems(R.array.theme, getCheckedItem(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                selected = themes[i];
                checkedItem = i;
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (selected == null) {
                    selected = themes[i];
                    checkedItem = i;
                }
                switch (selected) {
                    case "System Default":
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                        break;
                    case "Dark":
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        break;
                    case "Light":
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        break;
                }
                setCheckedItem(checkedItem);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private int getCheckedItem() {
        return sharedPreferences.getInt(CHECKEDITEM, 0);
    }

    private void setCheckedItem(int i) {
        editor.putInt(CHECKEDITEM, i);
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }
}