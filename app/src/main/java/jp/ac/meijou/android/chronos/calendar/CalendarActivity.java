package jp.ac.meijou.android.chronos.calendar;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import jp.ac.meijou.android.chronos.R;
import jp.ac.meijou.android.chronos.activity_percent;
import jp.ac.meijou.android.chronos.MainActivity;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // 初回だけ CalendarFragment をコンテナに入れる
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.calendarContainer, new CalendarFragment())
                    .commit();
        }

        // BottomNav の現在地 & 遷移
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.tab_calendar);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.tab_calendar) {
                return true; // 今この画面
            } else if (id == R.id.tab_home) {
                startActivity(new Intent(this, MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                return true;
            } else if (id == R.id.tab_simulation) {
                startActivity(new Intent(this, activity_percent.class)
                        .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                return true;
            } else if (id == R.id.tab_reflection) {
                // TODO: Reflection 画面へ
                return true;
            }
            return false;
        });
    }
}
