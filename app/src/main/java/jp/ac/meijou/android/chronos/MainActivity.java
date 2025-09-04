package jp.ac.meijou.android.chronos;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import jp.ac.meijou.android.chronos.ui.ScheduleListView;
import jp.ac.meijou.android.chronos.ui.SchedulePayload;
import okio.Okio;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // ステータスバー分だけ上のパディングを確保
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left, bars.top, bars.right, 0);
            return insets;
        });

        // 今日の日付をセット（例：2025/9/5）
        TextView date = findViewById(R.id.txtDate);
        String today = new SimpleDateFormat("yyyy/M/d", Locale.JAPAN).format(new Date());
        date.setText(today);

        // 予定リスト部品（どの画面でも再利用できるコンポーネント）
        ScheduleListView scheduleList = findViewById(R.id.scheduleList);

        // res/raw の JSON を読み込んで部品に渡す
        try {
            Moshi moshi = new Moshi.Builder().build();
            JsonAdapter<SchedulePayload> adapter = moshi.adapter(SchedulePayload.class);

            // 例：app/src/main/res/raw/schedule_morning.json
            try (okio.BufferedSource src = Okio.buffer(
                    Okio.source(getResources().openRawResource(R.raw.schedule_morning)))) {

                SchedulePayload payload = adapter.fromJson(src);
                if (payload != null && payload.items != null) {
                    scheduleList.submit(payload.items);
                }
            }
        } catch (Exception e) {
            // 失敗時はとりあえず空のまま（必要ならトーストなどで通知）
            e.printStackTrace();
        }
    }
}
